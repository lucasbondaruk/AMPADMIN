package Utilidades;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON mínimo para hablar con la API de IA sin agregar otra librería al proyecto.
 *
 * - {@link #escribir(Object)}: Map, List, String, Number, Boolean y null → texto JSON.
 * - {@link #leer(String)}: texto JSON → Map (LinkedHashMap), List, String, Double, Boolean o null.
 *
 * Si prefieren, se puede reemplazar por Gson; el resto del código solo usa estos dos métodos.
 */
public final class Json {

    private final String texto;
    private int pos;

    private Json(String texto) {
        this.texto = texto;
    }

    // ------------------------------------------------------------------ escritura

    public static String escribir(Object valor) {
        StringBuilder sb = new StringBuilder();
        escribir(valor, sb);
        return sb.toString();
    }

    private static void escribir(Object v, StringBuilder sb) {
        if (v == null) {
            sb.append("null");
        } else if (v instanceof String s) {
            escribirTexto(s, sb);
        } else if (v instanceof Double d && (d.isNaN() || d.isInfinite())) {
            sb.append("null");
        } else if (v instanceof Float f && (f.isNaN() || f.isInfinite())) {
            sb.append("null");
        } else if (v instanceof Number || v instanceof Boolean) {
            sb.append(v);
        } else if (v instanceof Map<?, ?> mapa) {
            sb.append('{');
            boolean primero = true;
            for (Map.Entry<?, ?> e : mapa.entrySet()) {
                if (!primero) {
                    sb.append(',');
                }
                primero = false;
                escribirTexto(String.valueOf(e.getKey()), sb);
                sb.append(':');
                escribir(e.getValue(), sb);
            }
            sb.append('}');
        } else if (v instanceof Iterable<?> lista) {
            sb.append('[');
            boolean primero = true;
            for (Object o : lista) {
                if (!primero) {
                    sb.append(',');
                }
                primero = false;
                escribir(o, sb);
            }
            sb.append(']');
        } else {
            escribirTexto(v.toString(), sb);
        }
    }

    private static void escribirTexto(String s, StringBuilder sb) {
        sb.append('"');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '"' -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                default -> {
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
                }
            }
        }
        sb.append('"');
    }

    // ------------------------------------------------------------------ lectura

    public static Object leer(String texto) {
        Json p = new Json(texto);
        p.espacios();
        Object v = p.valor();
        p.espacios();
        if (p.pos != texto.length()) {
            throw p.error("Texto sobrante después del JSON");
        }
        return v;
    }

    private Object valor() {
        espacios();
        if (pos >= texto.length()) {
            throw error("JSON incompleto");
        }
        char c = texto.charAt(pos);
        return switch (c) {
            case '{' -> objeto();
            case '[' -> arreglo();
            case '"' -> cadena();
            case 't' -> literal("true", Boolean.TRUE);
            case 'f' -> literal("false", Boolean.FALSE);
            case 'n' -> literal("null", null);
            default -> numero();
        };
    }

    private Map<String, Object> objeto() {
        Map<String, Object> mapa = new LinkedHashMap<>();
        pos++; // {
        espacios();
        if (mirar('}')) {
            pos++;
            return mapa;
        }
        while (true) {
            espacios();
            String clave = cadena();
            espacios();
            esperar(':');
            mapa.put(clave, valor());
            espacios();
            if (mirar(',')) {
                pos++;
            } else {
                esperar('}');
                return mapa;
            }
        }
    }

    private List<Object> arreglo() {
        List<Object> lista = new ArrayList<>();
        pos++; // [
        espacios();
        if (mirar(']')) {
            pos++;
            return lista;
        }
        while (true) {
            lista.add(valor());
            espacios();
            if (mirar(',')) {
                pos++;
            } else {
                esperar(']');
                return lista;
            }
        }
    }

    private String cadena() {
        esperar('"');
        StringBuilder sb = new StringBuilder();
        while (pos < texto.length()) {
            char c = texto.charAt(pos++);
            if (c == '"') {
                return sb.toString();
            }
            if (c != '\\') {
                sb.append(c);
                continue;
            }
            if (pos >= texto.length()) {
                break;
            }
            char e = texto.charAt(pos++);
            switch (e) {
                case 'n' -> sb.append('\n');
                case 't' -> sb.append('\t');
                case 'r' -> sb.append('\r');
                case 'b' -> sb.append('\b');
                case 'f' -> sb.append('\f');
                case 'u' -> {
                    if (pos + 4 > texto.length()) {
                        throw error("Escape \\u incompleto");
                    }
                    sb.append((char) Integer.parseInt(texto.substring(pos, pos + 4), 16));
                    pos += 4;
                }
                default -> sb.append(e); // \" \\ \/
            }
        }
        throw error("Cadena sin cerrar");
    }

    private Double numero() {
        int inicio = pos;
        while (pos < texto.length() && "+-0123456789.eE".indexOf(texto.charAt(pos)) >= 0) {
            pos++;
        }
        if (inicio == pos) {
            throw error("Carácter inesperado '" + texto.charAt(pos) + "'");
        }
        return Double.valueOf(texto.substring(inicio, pos));
    }

    private Object literal(String palabra, Object valor) {
        if (!texto.startsWith(palabra, pos)) {
            throw error("Se esperaba " + palabra);
        }
        pos += palabra.length();
        return valor;
    }

    private void espacios() {
        while (pos < texto.length() && Character.isWhitespace(texto.charAt(pos))) {
            pos++;
        }
    }

    private boolean mirar(char c) {
        return pos < texto.length() && texto.charAt(pos) == c;
    }

    private void esperar(char c) {
        if (!mirar(c)) {
            throw error("Se esperaba '" + c + "'");
        }
        pos++;
    }

    private IllegalArgumentException error(String mensaje) {
        return new IllegalArgumentException(mensaje + " (posición " + pos + ")");
    }
}
