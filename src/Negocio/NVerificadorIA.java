package Negocio;

import Modelos.InformeIA.AnalisisIA;
import Modelos.InformeIA.Hallazgo;
import Modelos.InformeIA.Recomendacion;
import Utilidades.Json;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Verifica que la respuesta de la IA esté "anclada" a los hallazgos (grounding):
 * <ul>
 *   <li>cada interpretación, alerta y recomendación cita hallazgos que existen;</li>
 *   <li>cada número que escribe la IA aparece en algún hallazgo (con tolerancia por redondeo).</li>
 * </ul>
 * Lo que no pasa la verificación se descarta. Así un modelo local chico no puede meter
 * en el informe un porcentaje inventado o una cuenta mal hecha.
 */
public final class NVerificadorIA {

    /** Números argentinos (1.234.567,89 · 12,5 · 0,012) o con punto decimal (12.5), con "mil"/"millones" opcional. */
    private static final Pattern NUMERO = Pattern.compile(
            "(?<![\\p{L}\\d])(\\d{1,3}(?:\\.\\d{3})+(?:,\\d+)?|\\d+(?:[.,]\\d+)?)(\\s*(?:millones|millón|mil)\\b)?",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    private static final Pattern ID_HALLAZGO = Pattern.compile(
            "\\s*[\\[(]\\s*H\\d+(?:\\s*[,;y]\\s*H\\d+)*\\s*[\\])]|\\bH\\d+\\b");
    private static final Set<String> PRIORIDADES = Set.of("Alta", "Media", "Baja");

    private NVerificadorIA() {
    }

    /** Resultado de la verificación: el análisis depurado y la lista de problemas encontrados. */
    public record Verificacion(AnalisisIA analisis, List<String> problemas) {}

    /** Esquema JSON que se le exige al modelo (Ollama lo aplica con decodificación restringida). */
    public static Map<String, Object> esquema() {
        Map<String, Object> textoConFuentes = Map.of(
                "type", "object",
                "properties", Map.of(
                        "texto", Map.of("type", "string"),
                        "basadoEn", Map.of("type", "array", "items", Map.of("type", "string"))),
                "required", List.of("texto", "basadoEn"));
        Map<String, Object> recomendacion = Map.of(
                "type", "object",
                "properties", Map.of(
                        "titulo", Map.of("type", "string"),
                        "detalle", Map.of("type", "string"),
                        "prioridad", Map.of("type", "string", "enum", List.of("Alta", "Media", "Baja")),
                        "basadoEn", Map.of("type", "array", "items", Map.of("type", "string"))),
                "required", List.of("titulo", "detalle", "prioridad", "basadoEn"));
        return Map.of(
                "type", "object",
                "properties", Map.of(
                        "resumen", Map.of("type", "string"),
                        "interpretacion", Map.of("type", "array", "items", textoConFuentes),
                        "recomendaciones", Map.of("type", "array", "items", recomendacion),
                        "alertas", Map.of("type", "array", "items", textoConFuentes),
                        "comentarioPronostico", Map.of("type", "string")),
                "required", List.of("resumen", "interpretacion", "recomendaciones", "alertas", "comentarioPronostico"));
    }

    /**
     * Lee y verifica la respuesta. Las partes inválidas se descartan; si el resumen o el
     * comentario del pronóstico tienen números inventados, se reemplazan por los automáticos.
     */
    public static Verificacion verificar(String respuesta, List<Hallazgo> hallazgos, AnalisisIA automatico) {
        List<String> problemas = new ArrayList<>();
        Map<?, ?> j;
        try {
            String limpio = respuesta.replaceAll("(?s)<think>.*?</think>", "");
            int inicio = limpio.indexOf('{');
            int fin = limpio.lastIndexOf('}');
            if (inicio < 0 || fin <= inicio) {
                throw new IllegalArgumentException("no hay un objeto JSON");
            }
            j = (Map<?, ?>) Json.leer(limpio.substring(inicio, fin + 1));
        } catch (RuntimeException ex) {
            problemas.add("La respuesta no es JSON válido (" + ex.getMessage() + ").");
            return new Verificacion(null, problemas);
        }

        Set<String> ids = hallazgos.stream().map(Hallazgo::id).collect(Collectors.toSet());
        List<Double> permitidos = new ArrayList<>();
        hallazgos.forEach(h -> permitidos.addAll(extraerNumeros(h.texto())));

        String resumen = texto(j.get("resumen"));
        List<String> malos = numerosNoVerificados(resumen, permitidos);
        if (resumen.isBlank() || !malos.isEmpty()) {
            problemas.add("El resumen tiene números que no están en los hallazgos: " + malos);
            resumen = automatico.resumen();
        }

        List<String> interpretacion = new ArrayList<>();
        for (Object o : lista(j.get("interpretacion"))) {
            String t = o instanceof Map<?, ?> m ? texto(m.get("texto")) : texto(o);
            List<String> fuentes = o instanceof Map<?, ?> m ? textos(m.get("basadoEn")) : List.of();
            if (valido("Interpretación", t, fuentes, ids, permitidos, problemas)) {
                interpretacion.add(sinIds(t));
            }
        }

        List<Recomendacion> recomendaciones = new ArrayList<>();
        for (Object o : lista(j.get("recomendaciones"))) {
            if (!(o instanceof Map<?, ?> m)) {
                continue;
            }
            String titulo = texto(m.get("titulo"));
            String detalle = texto(m.get("detalle"));
            List<String> fuentes = textos(m.get("basadoEn"));
            if (valido("Recomendación \"" + titulo + "\"", titulo + " " + detalle, fuentes, ids, permitidos, problemas)) {
                String prioridad = texto(m.get("prioridad"));
                recomendaciones.add(new Recomendacion(sinIds(titulo), sinIds(detalle),
                        PRIORIDADES.contains(prioridad) ? prioridad : "Media", normalizarIds(fuentes)));
            }
        }

        List<String> alertas = new ArrayList<>();
        for (Object o : lista(j.get("alertas"))) {
            String t = o instanceof Map<?, ?> m ? texto(m.get("texto")) : texto(o);
            List<String> fuentes = o instanceof Map<?, ?> m ? textos(m.get("basadoEn")) : List.of();
            if (valido("Alerta", t, fuentes, ids, permitidos, problemas)) {
                alertas.add(sinIds(t));
            }
        }

        String comentario = texto(j.get("comentarioPronostico"));
        malos = numerosNoVerificados(comentario, permitidos);
        if (comentario.isBlank() || !malos.isEmpty()) {
            problemas.add("El comentario del pronóstico tiene números que no están en los hallazgos: " + malos);
            comentario = automatico.comentarioPronostico();
        }

        if (interpretacion.size() < 2) {
            problemas.add("Quedaron menos de 2 interpretaciones válidas.");
        }
        if (recomendaciones.isEmpty()) {
            problemas.add("No quedó ninguna recomendación válida.");
        }
        return new Verificacion(new AnalisisIA(sinIds(resumen), interpretacion, recomendaciones, alertas,
                sinIds(comentario), problemas.isEmpty() ? "IA verificada" : "IA con correcciones"), problemas);
    }

    /** Completa con el análisis automático lo que haya quedado vacío después de descartar. */
    public static AnalisisIA completar(AnalisisIA ia, AnalisisIA automatico) {
        if (ia == null) {
            return automatico;
        }
        List<String> interp = ia.interpretacion().size() >= 2 ? ia.interpretacion() : automatico.interpretacion();
        List<Recomendacion> recs = new ArrayList<>(
                !ia.recomendaciones().isEmpty() ? ia.recomendaciones() : automatico.recomendaciones());
        // Las recomendaciones automáticas de prioridad Alta (stock crítico, caídas significativas)
        // se agregan si la IA no cubrió ese hallazgo: lo importante nunca se pierde.
        Set<String> citados = recs.stream().flatMap(r -> r.basadoEn().stream()).collect(Collectors.toSet());
        for (Recomendacion r : automatico.recomendaciones()) {
            if (r.prioridad().equals("Alta") && r.basadoEn().stream().noneMatch(citados::contains)) {
                recs.add(r);
            }
        }
        // Si la IA no dejó alertas válidas, se usan las automáticas (las de stock nunca se pierden)
        List<String> alertas = ia.alertas().isEmpty() ? automatico.alertas() : ia.alertas();
        return new AnalisisIA(ia.resumen(), interp, recs, alertas, ia.comentarioPronostico(), ia.fuente());
    }

    private static boolean valido(String que, String texto, List<String> fuentes, Set<String> ids,
                                  List<Double> permitidos, List<String> problemas) {
        if (texto.isBlank()) {
            return false;
        }
        List<String> normalizadas = normalizarIds(fuentes);
        if (normalizadas.isEmpty() || !ids.containsAll(normalizadas)) {
            problemas.add(que + ": no cita hallazgos existentes (" + fuentes + ").");
            return false;
        }
        List<String> malos = numerosNoVerificados(texto, permitidos);
        if (!malos.isEmpty()) {
            problemas.add(que + ": usa números que no están en los hallazgos " + malos + ".");
            return false;
        }
        return true;
    }

    /** Números del texto que no coinciden con ningún número de los hallazgos. */
    static List<String> numerosNoVerificados(String texto, List<Double> permitidos) {
        List<String> malos = new ArrayList<>();
        Matcher m = NUMERO.matcher(sinIds(texto));
        while (m.find()) {
            double v = valor(m.group(1), m.group(2));
            if (Double.isNaN(v) || esTrivial(v) || coincide(v, permitidos)) {
                continue;
            }
            malos.add(m.group().trim());
        }
        return malos;
    }

    static List<Double> extraerNumeros(String texto) {
        List<Double> nums = new ArrayList<>();
        Matcher m = NUMERO.matcher(texto);
        while (m.find()) {
            double v = valor(m.group(1), m.group(2));
            if (!Double.isNaN(v)) {
                nums.add(v);
            }
        }
        return nums;
    }

    /** Enteros chicos ("3 meses", "2 productos") y años no se verifican. */
    private static boolean esTrivial(double v) {
        boolean entero = v == Math.rint(v);
        return entero && (v <= 12 || (v >= 2000 && v <= 2100));
    }

    /** Tolerancia por redondeo: ±0,5 para valores chicos (porcentajes), ±3 % para montos. */
    private static boolean coincide(double v, List<Double> permitidos) {
        for (double p : permitidos) {
            double tolerancia = Math.abs(p) < 100 ? 0.5 : Math.abs(p) * 0.03;
            if (Math.abs(v - p) <= tolerancia) {
                return true;
            }
        }
        return false;
    }

    static double valor(String numero, String multiplicador) {
        String n = numero;
        if (n.matches("\\d{1,3}(\\.\\d{3})+(,\\d+)?")) {   // 1.234.567,89
            n = n.replace(".", "").replace(',', '.');
        } else if (n.contains(",")) {                         // 12,5
            n = n.replace(',', '.');
        }                                                     // 12.5 queda igual
        double v;
        try {
            v = Double.parseDouble(n);
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
        if (multiplicador != null) {
            String mult = multiplicador.trim().toLowerCase();
            v *= mult.startsWith("mill") ? 1_000_000 : 1_000;
        }
        return v;
    }

    private static List<String> normalizarIds(List<String> fuentes) {
        List<String> r = new ArrayList<>();
        for (String f : fuentes) {
            Matcher m = Pattern.compile("H\\d+").matcher(f.toUpperCase());
            while (m.find()) {
                r.add(m.group());
            }
        }
        return r;
    }

    private static String sinIds(String s) {
        return ID_HALLAZGO.matcher(s).replaceAll("").replaceAll("\\s+([.,;:])", "$1")
                .replaceAll("\\s{2,}", " ").trim();
    }

    private static List<?> lista(Object o) {
        return o instanceof List<?> l ? l : List.of();
    }

    private static List<String> textos(Object o) {
        return lista(o).stream().map(NVerificadorIA::texto).toList();
    }

    private static String texto(Object o) {
        return o == null ? "" : o.toString();
    }
}
