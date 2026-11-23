package Modelos;

import java.time.LocalDate;
import java.util.List;

/**
 * Resultado completo de un "Informe con IA".
 *
 * Todos los números (KPIs, rankings, pronóstico) los calcula AMPADMIN en Java/SQL.
 * La IA solo recibe esos números ya calculados y devuelve texto: interpretación,
 * recomendaciones y alertas (campo {@code analisis}).
 */
public record InformeIA(
        LocalDate desde,
        LocalDate hasta,
        Granularidad granularidad,
        Kpis kpis,
        List<PuntoSerie> historico,
        Pronostico pronostico,
        List<ItemRanking> topProductos,
        List<ItemRanking> ventasPorRubro,
        List<ItemRanking> ventasPorMetodoPago,
        List<ItemRanking> promedioPorDiaSemana,
        List<ProductoCritico> stockCritico,
        CalidadDatos calidad,
        List<Hallazgo> hallazgos,
        List<VentasVendedor> vendedores,
        AnalisisIA analisis,
        String errorIA) {

    /** Cómo se agrupan las ventas para la serie temporal y el pronóstico. */
    public enum Granularidad {
        MENSUAL(12, "Mensual"),
        SEMANAL(52, "Semanal");

        /** Cantidad de períodos que forman un ciclo estacional (un año). */
        public final int periodoEstacional;
        private final String etiqueta;

        Granularidad(int periodoEstacional, String etiqueta) {
            this.periodoEstacional = periodoEstacional;
            this.etiqueta = etiqueta;
        }

        /** Primer día del período que contiene a la fecha (mes o semana que empieza el lunes). */
        public LocalDate inicioDePeriodo(LocalDate fecha) {
            return switch (this) {
                case MENSUAL -> fecha.withDayOfMonth(1);
                case SEMANAL -> fecha.minusDays(fecha.getDayOfWeek().getValue() - 1L);
            };
        }

        public LocalDate siguiente(LocalDate inicioPeriodo) {
            return switch (this) {
                case MENSUAL -> inicioPeriodo.plusMonths(1);
                case SEMANAL -> inicioPeriodo.plusWeeks(1);
            };
        }

        @Override
        public String toString() {
            return etiqueta;
        }
    }

    public record Kpis(double ingresos, long cantidadVentas,
                       double ingresosAnterior, long cantidadVentasAnterior) {

        public double ticketPromedio() {
            return cantidadVentas == 0 ? 0 : ingresos / cantidadVentas;
        }

        public double ticketPromedioAnterior() {
            return cantidadVentasAnterior == 0 ? 0 : ingresosAnterior / cantidadVentasAnterior;
        }

        /** Variación % contra el período anterior. NaN si no hay base de comparación. */
        public double variacionIngresos() {
            return variacion(ingresos, ingresosAnterior);
        }

        public double variacionCantidad() {
            return variacion(cantidadVentas, cantidadVentasAnterior);
        }

        public double variacionTicket() {
            return variacion(ticketPromedio(), ticketPromedioAnterior());
        }

        private static double variacion(double actual, double anterior) {
            return anterior == 0 ? Double.NaN : (actual - anterior) / anterior * 100.0;
        }
    }

    /** Un punto de la serie: período que empieza en {@code inicio} con {@code valor} vendido. */
    public record PuntoSerie(LocalDate inicio, double valor) {}

    public record ItemRanking(String nombre, double importe, double cantidad) {}

    public record ProductoCritico(int codigo, String descripcion, int stockActual,
                                  int stockMinimo, double ventaDiariaPromedio) {

        /** Días que alcanza el stock al ritmo de venta de los últimos 30 días. Infinito si no se vende. */
        public double diasCobertura() {
            return ventaDiariaPromedio <= 0 ? Double.POSITIVE_INFINITY : stockActual / ventaDiariaPromedio;
        }
    }

    /**
     * Pronóstico de ventas futuras.
     *
     * @param metodo         modelo elegido por validación cruzada
     * @param valores        valor esperado por período futuro
     * @param limiteInferior banda inferior aprox. 95 %
     * @param limiteSuperior banda superior aprox. 95 %
     * @param mape           error porcentual medio en validación (NaN si no se pudo medir)
     * @param errorMedio     error absoluto medio en pesos del modelo elegido (NaN si no se pudo medir)
     * @param evaluados      todos los modelos probados con su error, ordenados de mejor a peor
     * @param advertencia    aviso sobre la confiabilidad (null si no hay)
     */
    public record Pronostico(String metodo, List<PuntoSerie> valores,
                             List<PuntoSerie> limiteInferior, List<PuntoSerie> limiteSuperior,
                             double mape, double errorMedio, List<EvaluacionModelo> evaluados,
                             String advertencia) {

        public double totalEsperado() {
            return valores.stream().mapToDouble(PuntoSerie::valor).sum();
        }
    }

    /** Resultado de un modelo candidato en la validación cruzada temporal. */
    public record EvaluacionModelo(String nombre, double errorMedio, double mape) {}

    /** Problema de calidad de datos detectado (no siempre es un error: algunos son para revisar). */
    public record Problema(String tipo, String detalle, int cantidad, boolean excluido) {}

    /**
     * Resultado de la limpieza de datos.
     *
     * @param problemas        validaciones que fallaron
     * @param periodosAtipicos períodos con ventas anormalmente altas o bajas (valor real)
     * @param ticketsAtipicos  cantidad de ventas individuales con importe atípico
     * @param serieCorregida   true si los atípicos se reemplazaron por el valor esperado para entrenar el pronóstico
     */
    public record CalidadDatos(List<Problema> problemas, List<PuntoSerie> periodosAtipicos,
                               int ticketsAtipicos, boolean serieCorregida) {}

    /**
     * Hecho calculado por AMPADMIN (no por la IA). La IA solo puede usar estos números.
     *
     * @param importancia 3 = alta, 2 = media, 1 = baja
     */
    public record Hallazgo(String id, String categoria, String texto, int importancia) {}

    /** @param basadoEn ids de los hallazgos que justifican la recomendación (p. ej. ["H3","H7"]) */
    /**
     * Ventas de un usuario del sistema (vendedor, gerente, administrador...) en el período.
     *
     * @param porPeriodo ingresos por mes o semana dentro del período elegido (incluye períodos en 0)
     * @param ventasPorPeriodo cantidad de ventas en cada uno de esos períodos (mismo orden)
     */
    public record VentasVendedor(Integer id, String usuario, String rol, String estado,
                                 double ingresos, long cantidad,
                                 List<PuntoSerie> porPeriodo, List<Long> ventasPorPeriodo) {

        public double ticketPromedio() {
            return cantidad == 0 ? 0 : ingresos / cantidad;
        }

        /** Texto del combo: "jperez (Vendedor)" o "admin (Administrador) - Inactivo". */
        @Override
        public String toString() {
            String s = usuario + (rol == null ? "" : " (" + rol + ")");
            return estado != null && !estado.equalsIgnoreCase("Activo") ? s + " - " + estado : s;
        }
    }

    public record Recomendacion(String titulo, String detalle, String prioridad, List<String> basadoEn) {}

    /**
     * Texto del informe.
     *
     * @param fuente quién lo generó: "IA verificada", "IA con correcciones" o "Automático (sin IA)"
     */
    public record AnalisisIA(String resumen, List<String> interpretacion,
                             List<Recomendacion> recomendaciones, List<String> alertas,
                             String comentarioPronostico, String fuente) {}
}
