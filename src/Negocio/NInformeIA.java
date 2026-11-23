package Negocio;

import Conexiones.ConfigIA;
import Conexiones.ServicioIA;
import Datos.DAnalisisVentas;
import Datos.DCalidadDatos;
import Modelos.InformeIA;
import Modelos.InformeIA.AnalisisIA;
import Modelos.InformeIA.CalidadDatos;
import Modelos.InformeIA.Granularidad;
import Modelos.InformeIA.Hallazgo;
import Modelos.InformeIA.ItemRanking;
import Modelos.InformeIA.Kpis;
import Modelos.InformeIA.ProductoCritico;
import Modelos.InformeIA.Pronostico;
import Modelos.InformeIA.PuntoSerie;
import Modelos.InformeIA.VentasVendedor;
import Utilidades.FormatoAR;
import Utilidades.Json;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Arma el Informe con IA en este orden:
 * <ol>
 *   <li><b>Limpieza</b>: validaciones de calidad (DCalidadDatos) y detección robusta de atípicos (NLimpiezaDatos).</li>
 *   <li><b>Estadística</b>: KPIs, rankings, stock (DAnalisisVentas).</li>
 *   <li><b>Predicción</b>: el mejor de 5 modelos elegido por validación cruzada temporal (NPronosticoVentas).</li>
 *   <li><b>Hallazgos</b>: frases con números y pruebas de significancia, calculadas en Java (NHallazgos).</li>
 *   <li><b>IA</b>: solo redacta a partir de los hallazgos; su respuesta se verifica (NVerificadorIA).
 *       Si inventa datos se le pide que corrija, y si sigue fallando se usa el análisis automático.</li>
 * </ol>
 * Privacidad: a la IA no se le envían nombres de clientes, CUIT ni datos personales.
 */
public class NInformeIA {

    private static final int TOP_PRODUCTOS = 10;
    private static final int DIAS_ALERTA_STOCK = 15;
    private static final int INTENTOS_IA = 2;

    private final DAnalisisVentas datos = new DAnalisisVentas();
    private final DCalidadDatos calidadDatos = new DCalidadDatos();
    private final ServicioIA ia;

    public NInformeIA() {
        this(ConfigIA.crearServicio());
    }

    /** Permite inyectar otro servicio (útil para pruebas). */
    public NInformeIA(ServicioIA ia) {
        this.ia = ia;
    }

    public boolean iaDisponible() {
        return ia != null;
    }

    public String descripcionIA() {
        return ia == null ? "IA no configurada" : ia.descripcion();
    }

    public InformeIA generar(LocalDate desde, LocalDate hasta, Granularidad g, int horizonte,
                             boolean usarIA) throws Exception {
        if (hasta.isBefore(desde)) {
            throw new IllegalArgumentException("La fecha 'hasta' es anterior a 'desde'.");
        }
        long dias = ChronoUnit.DAYS.between(desde, hasta) + 1;
        LocalDate antHasta = desde.minusDays(1);
        LocalDate antDesde = antHasta.minusDays(dias - 1);

        // 1) Estadística básica
        double[] actual = datos.totales(desde, hasta);
        double[] anterior = datos.totales(antDesde, antHasta);
        Kpis kpis = new Kpis(actual[0], (long) actual[1], anterior[0], (long) anterior[1]);
        List<ItemRanking> productos = datos.topProductos(desde, hasta, Integer.MAX_VALUE);
        List<ItemRanking> top = productos.subList(0, Math.min(TOP_PRODUCTOS, productos.size()));
        List<ItemRanking> rubros = datos.ventasPorRubro(desde, hasta);
        List<ItemRanking> pagos = datos.ventasPorMetodoPago(desde, hasta);
        List<ProductoCritico> stock = datos.stockCritico(LocalDate.now(), DIAS_ALERTA_STOCK);

        // 2) Serie histórica (hasta 3 años / 2 años de semanas)
        LocalDate inicioHistoria = g == Granularidad.MENSUAL
                ? g.inicioDePeriodo(hasta).minusMonths(36)
                : g.inicioDePeriodo(hasta).minusWeeks(104);
        if (antDesde.isBefore(inicioHistoria)) {
            inicioHistoria = g.inicioDePeriodo(antDesde);
        }
        Map<LocalDate, Double> diarias = datos.ventasDiarias(inicioHistoria, hasta);
        List<PuntoSerie> historico = agruparEnPeriodos(diarias, inicioHistoria, hasta, g);

        // 3) Limpieza: validaciones + atípicos
        NLimpiezaDatos.SerieLimpia limpia = NLimpiezaDatos.limpiarSerie(historico, g.periodoEstacional);
        int ticketsAtipicos = NLimpiezaDatos.contarTicketsAtipicos(calidadDatos.totalesDeVentas(desde, hasta));
        CalidadDatos calidad = new CalidadDatos(calidadDatos.validar(desde, hasta), limpia.atipicos(),
                ticketsAtipicos, limpia.corregida());

        // 4) Pronóstico sobre la serie limpia
        Pronostico pronostico = NPronosticoVentas.pronosticar(limpia.serie(), horizonte, g);
        List<ItemRanking> porDia = promedioPorDiaSemana(diarias, desde, hasta);

        // 5) Hallazgos (números + interpretación estadística, sin IA)
        NHallazgos.Resultado hallazgos = NHallazgos.calcular(new NHallazgos.Entrada(desde, hasta, g, kpis,
                ventasPorDia(diarias, desde, hasta), ventasPorDia(diarias, antDesde, antHasta),
                limpia.serie(), calidad, pronostico, productos, rubros, pagos, porDia, stock));

        // 6) Redacción con IA + verificación
        AnalisisIA analisis = hallazgos.analisisAutomatico();
        String errorIA = null;
        if (usarIA && ia != null) {
            try {
                analisis = redactarConIA(hallazgos.hallazgos(), analisis);
            } catch (Exception e) {
                errorIA = "No se pudo usar la IA (" + e.getMessage() + "). Se muestra el análisis automático.";
            }
        } else if (usarIA) {
            errorIA = "IA no configurada: creá " + ConfigIA.ARCHIVO.toAbsolutePath()
                    + " (ver ia.properties.ejemplo). Se muestra el análisis automático.";
        }

        List<VentasVendedor> vendedores = ventasPorVendedor(desde, hasta, g);

        return new InformeIA(desde, hasta, g, kpis, historico, pronostico, top, rubros, pagos, porDia, stock,
                calidad, hallazgos.hallazgos(), vendedores, analisis, errorIA);
    }

    // ------------------------------------------------------------------ IA

    private static final String INSTRUCCIONES = """
            Sos un analista comercial que asesora al dueño de un pequeño comercio minorista de Argentina.
            Vas a recibir una lista de HALLAZGOS calculados por el sistema AMPADMIN. Cada uno tiene un id (H1, H2...).
            Esos hallazgos ya incluyen las cuentas y las pruebas estadísticas: tu trabajo es redactar, priorizar y recomendar.

            Reglas obligatorias:
            1. Usá SOLO números que aparezcan textualmente en los hallazgos. No hagas cuentas nuevas
               (ni restas, ni sumas, ni porcentajes nuevos). Si querés comparar, usá palabras ("casi el doble", "menos").
            2. Cada interpretación, alerta y recomendación debe indicar en "basadoEn" los ids de los hallazgos que la justifican.
            3. Si un hallazgo dice que una diferencia NO es estadísticamente significativa, no la presentes como un cambio real.
            4. Si el pronóstico tiene una advertencia, mencionala en "comentarioPronostico".
            5. Recomendaciones concretas y accionables (stock, compras, promociones, días, medios de pago).
               Prioridad "Alta" solo para stock crítico, caídas significativas o tendencias a la baja.
            6. Español rioplatense, claro, sin tecnicismos (no expliques qué es Mann-Kendall).
            7. Montos con el mismo formato de los hallazgos: $ 1.234.567. Porcentajes: 12,5 %.

            Respondé solo con JSON:
            {"resumen": "2 o 3 oraciones",
             "interpretacion": [{"texto": "...", "basadoEn": ["H1"]}],
             "recomendaciones": [{"titulo": "...", "detalle": "...", "prioridad": "Alta|Media|Baja", "basadoEn": ["H3"]}],
             "alertas": [{"texto": "...", "basadoEn": ["H5"]}],
             "comentarioPronostico": "..."}
            Entre 3 y 6 interpretaciones y entre 2 y 5 recomendaciones. "alertas" puede estar vacío.
            """;

    AnalisisIA redactarConIA(List<Hallazgo> hallazgos, AnalisisIA automatico) throws Exception {
        List<Map<String, Object>> lista = new ArrayList<>();
        for (Hallazgo h : hallazgos) {
            Map<String, Object> x = new LinkedHashMap<>();
            x.put("id", h.id());
            x.put("tema", h.categoria());
            x.put("importancia", h.importancia() == 3 ? "alta" : h.importancia() == 2 ? "media" : "baja");
            x.put("texto", h.texto());
            lista.add(x);
        }
        String mensaje = "HALLAZGOS:\n" + Json.escribir(lista);

        NVerificadorIA.Verificacion v = null;
        for (int intento = 1; intento <= INTENTOS_IA; intento++) {
            String respuesta = ia.completar(INSTRUCCIONES, mensaje, NVerificadorIA.esquema());
            v = NVerificadorIA.verificar(respuesta, hallazgos, automatico);
            if (v.problemas().isEmpty()) {
                break;
            }
            // Autocorrección: se le devuelven los errores concretos para que los arregle
            mensaje = "HALLAZGOS:\n" + Json.escribir(lista)
                    + "\n\nTu respuesta anterior fue:\n" + respuesta
                    + "\n\nTenía estos problemas, corregilos respetando las reglas:\n- "
                    + String.join("\n- ", v.problemas());
        }
        return NVerificadorIA.completar(v.analisis(), automatico);
    }

    // ------------------------------------------------------------------ vendedores

    /**
     * Ventas de cada usuario del sistema dentro del período, agrupadas por mes o semana.
     * Aparecen todos los usuarios (aunque no hayan vendido) y, si hay ventas sin vendedor
     * o con un vendedor inexistente, una fila "Sin vendedor asignado".
     */
    private List<VentasVendedor> ventasPorVendedor(LocalDate desde, LocalDate hasta, Granularidad g)
            throws Exception {
        List<DAnalisisVentas.VentaDiariaVendedor> ventas = datos.ventasDiariasPorVendedor(desde, hasta);
        Map<Integer, List<DAnalisisVentas.VentaDiariaVendedor>> porUsuario = new LinkedHashMap<>();
        for (DAnalisisVentas.VentaDiariaVendedor v : ventas) {
            porUsuario.computeIfAbsent(v.idVendedor(), x -> new ArrayList<>()).add(v);
        }
        List<VentasVendedor> lista = new ArrayList<>();
        for (DAnalisisVentas.UsuarioResumen u : datos.usuarios()) {
            lista.add(armarVendedor(u.id(), u.usuario(), u.rol(), u.estado(),
                    porUsuario.remove(u.id()), desde, hasta, g));
        }
        // Lo que quedó: ventas sin vendedor (null) o con un id que no existe en Usuario
        List<DAnalisisVentas.VentaDiariaVendedor> sinVendedor = new ArrayList<>();
        porUsuario.values().forEach(sinVendedor::addAll);
        if (!sinVendedor.isEmpty()) {
            lista.add(armarVendedor(null, "Sin vendedor asignado", null, null, sinVendedor, desde, hasta, g));
        }
        return lista;
    }

    static VentasVendedor armarVendedor(Integer id, String usuario, String rol, String estado,
                                        List<DAnalisisVentas.VentaDiariaVendedor> ventas,
                                        LocalDate desde, LocalDate hasta, Granularidad g) {
        Map<LocalDate, double[]> periodos = new LinkedHashMap<>(); // inicio → {ingresos, cantidad}
        for (LocalDate p = g.inicioDePeriodo(desde); !p.isAfter(hasta); p = g.siguiente(p)) {
            periodos.put(p, new double[2]);
        }
        double ingresos = 0;
        long cantidad = 0;
        if (ventas != null) {
            for (DAnalisisVentas.VentaDiariaVendedor v : ventas) {
                double[] acum = periodos.get(g.inicioDePeriodo(v.dia()));
                if (acum != null) {
                    acum[0] += v.total();
                    acum[1] += v.cantidad();
                }
                ingresos += v.total();
                cantidad += v.cantidad();
            }
        }
        List<PuntoSerie> montos = new ArrayList<>();
        List<Long> cantidades = new ArrayList<>();
        periodos.forEach((inicio, acum) -> {
            montos.add(new PuntoSerie(inicio, acum[0]));
            cantidades.add((long) acum[1]);
        });
        return new VentasVendedor(id, usuario, rol, estado, ingresos, cantidad, montos, cantidades);
    }

    // ------------------------------------------------------------------ estadística

    /**
     * Agrupa ventas diarias en meses/semanas, rellena con 0 los períodos sin ventas,
     * descarta los períodos anteriores a la primera venta y el último período si está
     * incompleto (p. ej. el mes en curso), porque bajaría artificialmente el pronóstico.
     */
    static List<PuntoSerie> agruparEnPeriodos(Map<LocalDate, Double> diarias, LocalDate desde,
                                              LocalDate hasta, Granularidad g) {
        Map<LocalDate, Double> porPeriodo = new LinkedHashMap<>();
        for (LocalDate p = g.inicioDePeriodo(desde); !p.isAfter(hasta); p = g.siguiente(p)) {
            if (!g.siguiente(p).minusDays(1).isAfter(hasta)) { // solo períodos completos
                porPeriodo.put(p, 0.0);
            }
        }
        diarias.forEach((dia, total) -> porPeriodo.computeIfPresent(g.inicioDePeriodo(dia),
                (k, v) -> v + total));

        List<PuntoSerie> serie = new ArrayList<>();
        boolean empezo = false;
        for (Map.Entry<LocalDate, Double> e : porPeriodo.entrySet()) {
            empezo |= e.getValue() > 0;
            if (empezo) {
                serie.add(new PuntoSerie(e.getKey(), e.getValue()));
            }
        }
        return serie;
    }

    /** Ventas de cada día del rango, con 0 los días sin ventas. */
    static double[] ventasPorDia(Map<LocalDate, Double> diarias, LocalDate desde, LocalDate hasta) {
        int n = (int) ChronoUnit.DAYS.between(desde, hasta) + 1;
        double[] v = new double[Math.max(0, n)];
        for (int i = 0; i < n; i++) {
            v[i] = diarias.getOrDefault(desde.plusDays(i), 0.0);
        }
        return v;
    }

    static List<ItemRanking> promedioPorDiaSemana(Map<LocalDate, Double> diarias,
                                                  LocalDate desde, LocalDate hasta) {
        double[] suma = new double[7];
        int[] cuenta = new int[7];
        for (LocalDate d = desde; !d.isAfter(hasta); d = d.plusDays(1)) {
            int i = d.getDayOfWeek().getValue() - 1;
            suma[i] += diarias.getOrDefault(d, 0.0);
            cuenta[i]++;
        }
        List<ItemRanking> lista = new ArrayList<>();
        for (DayOfWeek dia : DayOfWeek.values()) {
            int i = dia.getValue() - 1;
            if (cuenta[i] > 0) {
                String nombre = dia.getDisplayName(TextStyle.FULL, FormatoAR.ES_AR);
                nombre = Character.toUpperCase(nombre.charAt(0)) + nombre.substring(1);
                lista.add(new ItemRanking(nombre, suma[i] / cuenta[i], cuenta[i]));
            }
        }
        return lista;
    }
}
