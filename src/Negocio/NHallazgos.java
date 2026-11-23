package Negocio;

import Modelos.InformeIA.AnalisisIA;
import Modelos.InformeIA.CalidadDatos;
import Modelos.InformeIA.Granularidad;
import Modelos.InformeIA.Hallazgo;
import Modelos.InformeIA.ItemRanking;
import Modelos.InformeIA.Kpis;
import Modelos.InformeIA.ProductoCritico;
import Modelos.InformeIA.Problema;
import Modelos.InformeIA.Pronostico;
import Modelos.InformeIA.PuntoSerie;
import Modelos.InformeIA.Recomendacion;
import static Utilidades.FormatoAR.pct;
import static Utilidades.FormatoAR.pesos;
import Utilidades.FormatoAR;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Convierte los números del informe en "hallazgos": frases con datos ya calculados y
 * ya interpretados estadísticamente (¿el cambio es significativo o es ruido?).
 *
 * Es la base de las interpretaciones correctas: la IA solo redacta a partir de estos
 * hallazgos y cada recomendación tiene que citar en cuál se basa. Si la IA falla o
 * inventa datos, el informe usa directamente estos hallazgos y las reglas de abajo.
 */
public final class NHallazgos {

    private NHallazgos() {
    }

    /** Todo lo necesario para calcular hallazgos. */
    public record Entrada(LocalDate desde, LocalDate hasta, Granularidad granularidad, Kpis kpis,
                          double[] ventasDiariasActual, double[] ventasDiariasAnterior,
                          List<PuntoSerie> serieLimpia, CalidadDatos calidad, Pronostico pronostico,
                          List<ItemRanking> productos, List<ItemRanking> rubros, List<ItemRanking> pagos,
                          List<ItemRanking> porDiaSemana, List<ProductoCritico> stock) {}

    /** Hallazgos + un análisis completo generado por reglas (respaldo si la IA falla). */
    public record Resultado(List<Hallazgo> hallazgos, AnalisisIA analisisAutomatico) {}

    public static Resultado calcular(Entrada e) {
        List<Hallazgo> h = new ArrayList<>();
        List<Recomendacion> recs = new ArrayList<>();
        List<String> alertas = new ArrayList<>();
        Kpis k = e.kpis();
        String unidad = e.granularidad() == Granularidad.MENSUAL ? "mes" : "semana";

        // ---- Ventas del período
        String hVentas = agregar(h, "ventas", 3, "Ingresos del período: " + pesos(k.ingresos()) + " en "
                + k.cantidadVentas() + " ventas, con un ticket promedio de " + pesos(k.ticketPromedio()) + ".");

        // ---- Variación contra el período anterior + significancia
        // Se usan dos pruebas porque responden preguntas distintas:
        //  · Poisson: ¿cambió la CANTIDAD de ventas más de lo que explica el azar?
        //  · Mann-Whitney: ¿cambió lo que se vende en un DÍA TÍPICO? (necesita varios días con ventas)
        if (k.ingresosAnterior() > 0) {
            double var = k.variacionIngresos();
            String texto = "Los ingresos " + (var >= 0 ? "subieron" : "bajaron") + " " + pct(Math.abs(var))
                    + " frente al período anterior de igual duración (" + pesos(k.ingresosAnterior())
                    + " → " + pesos(k.ingresos()) + ").";
            boolean significativa = false;

            int dias1 = e.ventasDiariasActual().length;
            int dias2 = e.ventasDiariasAnterior().length;
            NEstadistica.Comparacion cant = NEstadistica.poissonDosPeriodos(
                    k.cantidadVentas(), dias1, k.cantidadVentasAnterior(), dias2);
            if (k.cantidadVentas() + k.cantidadVentasAnterior() > 0) {
                boolean sigCant = cant.significativa();
                significativa |= sigCant;
                texto += " La cantidad de ventas pasó de " + k.cantidadVentasAnterior() + " a " + k.cantidadVentas()
                        + (sigCant
                        ? ": el cambio es estadísticamente significativo, no se explica por azar (prueba de Poisson, "
                        : ": el cambio no es estadísticamente significativo (prueba de Poisson, ")
                        + FormatoAR.p(cant.p()) + ").";
            }

            long conVentas1 = java.util.Arrays.stream(e.ventasDiariasActual()).filter(v -> v > 0).count();
            long conVentas2 = java.util.Arrays.stream(e.ventasDiariasAnterior()).filter(v -> v > 0).count();
            if (conVentas1 >= 5 && conVentas2 >= 5) {
                NEstadistica.Comparacion c = NEstadistica.mannWhitney(e.ventasDiariasActual(), e.ventasDiariasAnterior());
                significativa |= c.significativa();
                texto += c.significativa()
                        ? " Lo vendido en un día típico también cambió de forma significativa (Mann-Whitney, "
                        + FormatoAR.p(c.p()) + ")."
                        : " Lo vendido en un día típico no cambió de forma significativa (Mann-Whitney, "
                        + FormatoAR.p(c.p()) + "): la diferencia se concentra en pocos días.";
            } else {
                texto += " Hubo pocos días con ventas (" + conVentas2 + " en el período anterior y " + conVentas1
                        + " en el actual) para comparar los montos diarios.";
            }
            String id = agregar(h, "variacion", significativa ? 3 : 2, texto);
            if (significativa && var <= -10) {
                recs.add(new Recomendacion("Analizar la caída de ventas",
                        "Las ventas bajaron " + pct(Math.abs(var)) + " y la caída es significativa. Revisar si hubo "
                        + "faltantes de stock, cambios de precio o menos días abiertos en el período.", "Alta", List.of(id)));
            }
        }
        if (k.cantidadVentasAnterior() > 0 && Math.abs(k.variacionTicket()) >= 5) {
            agregar(h, "ticket", 2, "El ticket promedio pasó de " + pesos(k.ticketPromedioAnterior()) + " a "
                    + pesos(k.ticketPromedio()) + " (" + signo(k.variacionTicket()) + ").");
        }

        // ---- Tendencia (Mann-Kendall + pendiente de Sen) sobre la serie limpia
        double[] y = e.serieLimpia().stream().mapToDouble(PuntoSerie::valor).toArray();
        if (y.length >= 8) {
            NEstadistica.Tendencia t = NEstadistica.mannKendall(y);
            String id;
            if (t.significativa()) {
                id = agregar(h, "tendencia", 3, "En los últimos " + y.length + " períodos hay una tendencia "
                        + (t.pendienteSen() > 0 ? "creciente" : "decreciente") + " sostenida de unos "
                        + pesos(Math.abs(t.pendienteSen())) + " por " + unidad + " (Mann-Kendall, " + FormatoAR.p(t.p())
                        + "; pendiente de Sen).");
                if (t.pendienteSen() < 0) {
                    recs.add(new Recomendacion("Revertir la tendencia a la baja",
                            "Las ventas vienen cayendo de forma sostenida. Conviene revisar precios frente a la competencia "
                            + "y planificar promociones sobre los productos más vendidos.", "Alta", List.of(id)));
                }
            } else {
                agregar(h, "tendencia", 1, "En los últimos " + y.length + " períodos no hay una tendencia clara "
                        + "de subida ni de bajada (Mann-Kendall, " + FormatoAR.p(t.p()) + ").");
            }
        }

        // ---- Atípicos
        CalidadDatos cal = e.calidad();
        if (!cal.periodosAtipicos().isEmpty()) {
            StringBuilder sb = new StringBuilder("Se detectaron " + cal.periodosAtipicos().size()
                    + " períodos con ventas atípicas: ");
            for (int i = 0; i < cal.periodosAtipicos().size(); i++) {
                PuntoSerie p = cal.periodosAtipicos().get(i);
                sb.append(i > 0 ? ", " : "").append(etiqueta(p.inicio(), e.granularidad())).append(" (")
                        .append(pesos(p.valor())).append(")");
            }
            sb.append(cal.serieCorregida()
                    ? ". Para el pronóstico se reemplazaron por su valor esperado."
                    : ". Se informan pero no se corrigen porque falta historia para separar la estacionalidad.");
            agregar(h, "atipicos", 2, sb.toString());
        }

        // ---- Productos: concentración (Pareto) y producto líder
        double totalProductos = e.productos().stream().mapToDouble(ItemRanking::importe).sum();
        if (totalProductos > 0 && e.productos().size() >= 3) {
            List<ItemRanking> orden = e.productos().stream()
                    .sorted(Comparator.comparingDouble(ItemRanking::importe).reversed()).toList();
            double acumulado = 0;
            int kProd = 0;
            while (acumulado < 0.8 * totalProductos && kProd < orden.size()) {
                acumulado += orden.get(kProd++).importe();
            }
            double pctProductos = 100.0 * kProd / orden.size();
            String id = agregar(h, "productos", 2, kProd + " de " + orden.size() + " productos vendidos ("
                    + pct(pctProductos) + ") generan el 80 % de los ingresos por productos.");
            ItemRanking lider = orden.get(0);
            String idLider = agregar(h, "productos", 2, "El producto que más factura es \"" + lider.nombre() + "\": "
                    + pesos(lider.importe()) + " (" + pct(100 * lider.importe() / totalProductos)
                    + " de los ingresos por productos).");
            if (pctProductos <= 30) {
                recs.add(new Recomendacion("Asegurar los productos clave",
                        "Pocos productos explican la mayor parte de la facturación. Mantener siempre stock de ellos, "
                        + "empezando por \"" + lider.nombre() + "\", y vigilar su precio de compra.", "Media",
                        List.of(id, idLider)));
            }
        }

        // ---- Rubros
        double totalRubros = e.rubros().stream().mapToDouble(ItemRanking::importe).sum();
        if (totalRubros > 0 && e.rubros().size() >= 2) {
            ItemRanking r = e.rubros().get(0);
            agregar(h, "rubros", 1, "El rubro \"" + r.nombre() + "\" concentra el " + pct(100 * r.importe() / totalRubros)
                    + " de las ventas por productos (" + pesos(r.importe()) + ").");
        }

        // ---- Medios de pago
        double totalPagos = e.pagos().stream().mapToDouble(ItemRanking::importe).sum();
        if (totalPagos > 0) {
            ItemRanking p1 = e.pagos().get(0);
            String texto = "El " + pct(100 * p1.importe() / totalPagos) + " de lo facturado se cobró con "
                    + p1.nombre() + ".";
            if (e.pagos().size() >= 2 && p1.cantidad() > 0 && e.pagos().get(1).cantidad() > 0) {
                ItemRanking p2 = e.pagos().get(1);
                texto += " Ticket promedio: " + pesos(p1.importe() / p1.cantidad()) + " con " + p1.nombre() + " y "
                        + pesos(p2.importe() / p2.cantidad()) + " con " + p2.nombre() + ".";
            }
            agregar(h, "pagos", 1, texto);
        }

        // ---- Día de la semana
        long dias = e.ventasDiariasActual().length;
        if (dias >= 14 && e.porDiaSemana().size() >= 2) {
            double promedio = e.porDiaSemana().stream().mapToDouble(ItemRanking::importe).average().orElse(0);
            ItemRanking mejor = e.porDiaSemana().stream().max(Comparator.comparingDouble(ItemRanking::importe)).orElseThrow();
            ItemRanking peor = e.porDiaSemana().stream().filter(d -> d.importe() > 0)
                    .min(Comparator.comparingDouble(ItemRanking::importe)).orElse(null);
            if (promedio > 0 && peor != null && peor != mejor) {
                double pMejor = 100 * (mejor.importe() / promedio - 1);
                double pPeor = 100 * (1 - peor.importe() / promedio);
                String id = agregar(h, "dias", 2, "Los " + mejor.nombre().toLowerCase() + " son el día de mayor venta promedio ("
                        + pesos(mejor.importe()) + ", " + pct(pMejor) + " sobre el promedio diario) y los "
                        + peor.nombre().toLowerCase() + " el de menor (" + pesos(peor.importe()) + ", " + pct(pPeor)
                        + " debajo del promedio, sin contar días sin ventas).");
                if (pPeor >= 25) {
                    recs.add(new Recomendacion("Promoción para los " + peor.nombre().toLowerCase(),
                            "Es el día más flojo de la semana. Probar una oferta o descuento ese día y comparar "
                            + "el resultado en el próximo informe.", "Media", List.of(id)));
                }
            }
        }

        // ---- Stock crítico
        if (!e.stock().isEmpty()) {
            StringBuilder sb = new StringBuilder("Hay " + e.stock().size() + " productos en stock crítico. Los más urgentes: ");
            int limite = Math.min(3, e.stock().size());
            for (int i = 0; i < limite; i++) {
                ProductoCritico p = e.stock().get(i);
                sb.append(i > 0 ? "; " : "").append("\"").append(p.descripcion()).append("\" (stock ")
                        .append(p.stockActual()).append(", mínimo ").append(p.stockMinimo());
                if (p.stockActual() <= 0) {
                    sb.append(", sin stock");
                } else if (!Double.isInfinite(p.diasCobertura())) {
                    sb.append(", alcanza para ").append(Math.round(p.diasCobertura())).append(" días");
                }
                sb.append(")");
            }
            String id = agregar(h, "stock", 3, sb.append(".").toString());
            recs.add(new Recomendacion("Reponer stock crítico",
                    "Priorizar la compra de los productos marcados como urgentes, empezando por \""
                    + e.stock().get(0).descripcion() + "\".", "Alta", List.of(id)));
            alertas.add(h.get(h.size() - 1).texto());
        }

        // ---- Pronóstico
        Pronostico pr = e.pronostico();
        if (pr != null) {
            int horizonte = pr.valores().size();
            String texto = "Pronóstico para los próximos " + horizonte + " períodos: " + pesos(pr.totalEsperado())
                    + " en total (modelo elegido por validación cruzada: " + pr.metodo() + ")";
            if (!Double.isNaN(pr.errorMedio())) {
                texto += "; error medio en validación de " + pesos(pr.errorMedio()) + " por período";
                if (!Double.isNaN(pr.mape())) {
                    texto += " (" + pct(pr.mape()) + ")";
                }
            }
            texto += ".";
            if (y.length >= horizonte) {
                double previo = 0;
                for (int i = y.length - horizonte; i < y.length; i++) {
                    previo += y[i];
                }
                if (previo > 0) {
                    double cambio = 100 * (pr.totalEsperado() / previo - 1);
                    texto += " Frente a los últimos " + horizonte + " períodos (" + pesos(previo) + ") sería un cambio de "
                            + signo(cambio) + ".";
                    if (cambio >= 10 && (Double.isNaN(pr.mape()) || pr.mape() < 30)) {
                        recs.add(new Recomendacion("Anticipar compras",
                                "Se espera un aumento de ventas. Planificar las compras a proveedores con anticipación "
                                + "para no quedarse sin stock de los productos más vendidos.", "Media",
                                List.of("H" + (h.size() + 1))));
                    }
                }
            }
            if (pr.advertencia() != null) {
                texto += " Advertencia: " + pr.advertencia();
            }
            agregar(h, "pronostico", 3, texto);
        }

        // ---- Calidad de datos
        List<Problema> probs = cal.problemas();
        if (!probs.isEmpty() || cal.ticketsAtipicos() > 0) {
            StringBuilder sb = new StringBuilder("Calidad de datos: ");
            for (int i = 0; i < probs.size(); i++) {
                Problema p = probs.get(i);
                sb.append(i > 0 ? "; " : "").append(p.cantidad()).append(" × ").append(p.tipo().toLowerCase())
                        .append(p.excluido() ? " (excluidas)" : " (para revisar)");
            }
            if (cal.ticketsAtipicos() > 0) {
                sb.append(probs.isEmpty() ? "" : "; ").append(cal.ticketsAtipicos())
                        .append(" ventas con importe atípico (para revisar)");
            }
            String id = agregar(h, "calidad", 1, sb.append(".").toString());
            if (probs.stream().anyMatch(p -> !p.excluido())) {
                recs.add(new Recomendacion("Revisar la carga de ventas",
                        "Hay registros para revisar (ver pestaña Calidad de datos). Corregirlos mejora la precisión "
                        + "de los próximos informes.", "Baja", List.of(id)));
            }
        }

        if (recs.isEmpty()) {
            recs.add(new Recomendacion("Seguir monitoreando",
                    "No se detectaron problemas importantes. Generar este informe todos los meses para detectar cambios a tiempo.",
                    "Baja", List.of(hVentas)));
        }

        // ---- Análisis automático (respaldo)
        List<String> interpretacion = h.stream().filter(x -> x.importancia() >= 2 && !x.categoria().equals("stock"))
                .map(Hallazgo::texto).limit(6).toList();
        String resumen = h.get(0).texto() + (h.size() > 1 && h.get(1).categoria().equals("variacion")
                ? " " + h.get(1).texto() : "");
        String comentario = h.stream().filter(x -> x.categoria().equals("pronostico")).map(Hallazgo::texto)
                .findFirst().orElse("No hay historia suficiente para pronosticar.");
        AnalisisIA automatico = new AnalisisIA(resumen, interpretacion, recs, alertas, comentario, "Automático (sin IA)");
        return new Resultado(h, automatico);
    }

    private static String agregar(List<Hallazgo> lista, String categoria, int importancia, String texto) {
        String id = "H" + (lista.size() + 1);
        lista.add(new Hallazgo(id, categoria, texto, importancia));
        return id;
    }

    private static String signo(double v) {
        return (v >= 0 ? "+" : "-") + pct(Math.abs(v));
    }

    public static String etiqueta(LocalDate inicio, Granularidad g) {
        return g == Granularidad.MENSUAL
                ? inicio.format(DateTimeFormatter.ofPattern("MMMM yyyy", FormatoAR.ES_AR))
                : "semana del " + inicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
