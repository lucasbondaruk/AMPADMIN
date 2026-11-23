package Utilidades;

import Modelos.InformeIA;
import Modelos.InformeIA.ItemRanking;
import Modelos.InformeIA.Pronostico;
import Modelos.InformeIA.PuntoSerie;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;

/**
 * Gráficos JFreeChart del Informe con IA. Se usan tanto en pantalla (ChartPanel)
 * como en el PDF (ChartUtils.writeChartAsPNG), así ambos se ven idénticos.
 *
 * Paleta: azul = ventas reales, naranja = pronóstico, rojo = período atípico. Se usan barras horizontales
 * en lugar de tortas porque se comparan mejor y los nombres largos entran.
 */
public final class GraficosInformeIA {

    public static final Color AZUL = new Color(0x2a78d6);
    public static final Color NARANJA = new Color(0xeb6834);
    public static final Color ROJO = new Color(0xe34948);
    private static final Color FONDO = new Color(0xfcfcfb);
    private static final Color GRILLA = new Color(0xe4e3df);
    private static final Color TEXTO = new Color(0x52514e);
    private static final Locale ES_AR = Locale.forLanguageTag("es-AR");

    private GraficosInformeIA() {
    }

    public static NumberFormat formatoPesos() {
        NumberFormat f = NumberFormat.getCurrencyInstance(ES_AR);
        f.setMaximumFractionDigits(0);
        return f;
    }

    /** Línea de ventas reales + pronóstico con banda de confianza del 95 %. */
    public static JFreeChart ventasYPronostico(InformeIA informe) {
        TimeSeries reales = new TimeSeries("Ventas reales");
        for (PuntoSerie p : informe.historico()) {
            reales.add(dia(p.inicio()), p.valor());
        }
        TimeSeriesCollection colReales = new TimeSeriesCollection(reales);
        boolean hayAtipicos = informe.calidad() != null && !informe.calidad().periodosAtipicos().isEmpty();
        if (hayAtipicos) {
            TimeSeries atipicos = new TimeSeries(informe.calidad().serieCorregida()
                    ? "Atípico (corregido para el pronóstico)" : "Atípico");
            for (PuntoSerie p : informe.calidad().periodosAtipicos()) {
                atipicos.add(dia(p.inicio()), p.valor());
            }
            colReales.addSeries(atipicos);
        }
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Ventas por período " + informe.granularidad().toString().toLowerCase() + " y pronóstico",
                null, null, colReales, true, true, false);
        XYPlot plot = chart.getXYPlot();
        estilo(chart);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        // Grilla en los dos ejes: líneas verticales en cada fecha y horizontales en cada monto
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(LINEA_FONDO);
        plot.setDomainGridlineStroke(new BasicStroke(1f));
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(LINEA_FONDO);
        plot.setRangeGridlineStroke(new BasicStroke(1f));
        plot.setRangeMinorGridlinesVisible(true);
        plot.setRangeMinorGridlinePaint(LINEA_FONDO_SUAVE);
        plot.setRangeMinorGridlineStroke(new BasicStroke(1f));

        XYLineAndShapeRenderer linea = new XYLineAndShapeRenderer(true, true);
        linea.setSeriesPaint(0, AZUL);
        linea.setSeriesStroke(0, new BasicStroke(2f));
        linea.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-4, -4, 8, 8));
        if (hayAtipicos) { // solo marcadores, sin línea
            linea.setSeriesLinesVisible(1, false);
            linea.setSeriesPaint(1, ROJO);
            linea.setSeriesShape(1, new java.awt.geom.Rectangle2D.Double(-5, -5, 10, 10));
        }
        plot.setRenderer(0, linea);

        Pronostico pr = informe.pronostico();
        if (pr != null) {
            YIntervalSeries serie = new YIntervalSeries("Pronóstico (banda 95 %)");
            // Une el último punto real con el primero pronosticado para que la línea sea continua
            if (!informe.historico().isEmpty()) {
                PuntoSerie ultimo = informe.historico().get(informe.historico().size() - 1);
                serie.add(millis(ultimo.inicio()), ultimo.valor(), ultimo.valor(), ultimo.valor());
            }
            for (int i = 0; i < pr.valores().size(); i++) {
                serie.add(millis(pr.valores().get(i).inicio()), pr.valores().get(i).valor(),
                        pr.limiteInferior().get(i).valor(), pr.limiteSuperior().get(i).valor());
            }
            YIntervalSeriesCollection datos = new YIntervalSeriesCollection();
            datos.addSeries(serie);
            DeviationRenderer banda = new DeviationRenderer(true, true);
            banda.setSeriesPaint(0, NARANJA);
            banda.setSeriesFillPaint(0, NARANJA);
            banda.setAlpha(0.18f);
            banda.setSeriesStroke(0, new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                    1f, new float[]{6f, 4f}, 0f));
            banda.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-4, -4, 8, 8));
            plot.setDataset(1, datos);
            plot.setRenderer(1, banda);
            plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        }

        NumberAxis eje = (NumberAxis) plot.getRangeAxis();
        eje.setNumberFormatOverride(formatoPesos());
        eje.setAutoRangeIncludesZero(true);
        eje.setMinorTickCount(2); // líneas secundarias entre montos
        estiloEje(eje);
        DateAxis fechas = (DateAxis) plot.getDomainAxis();
        fechas.setDateFormatOverride(new SimpleDateFormat(
                informe.granularidad() == InformeIA.Granularidad.MENSUAL ? "MMM yy" : "dd/MM/yy", ES_AR));
        estiloEje(fechas);
        return chart;
    }

    /** Barras horizontales por importe (top productos, rubros, medios de pago, días). */
    public static JFreeChart barras(String titulo, List<ItemRanking> items) {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        for (ItemRanking i : items) {
            ds.addValue(i.importe(), "Importe", recortar(i.nombre(), 32));
        }
        JFreeChart chart = ChartFactory.createBarChart(titulo, null, null, ds,
                PlotOrientation.HORIZONTAL, false, true, false);
        estilo(chart);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(FONDO);
        plot.setRangeGridlinePaint(GRILLA);
        plot.setOutlineVisible(false);
        BarRenderer r = (BarRenderer) plot.getRenderer();
        r.setBarPainter(new StandardBarPainter());
        r.setShadowVisible(false);
        r.setSeriesPaint(0, AZUL);
        r.setMaximumBarWidth(0.08);
        r.setItemMargin(0.1);
        NumberAxis eje = (NumberAxis) plot.getRangeAxis();
        eje.setNumberFormatOverride(formatoPesos());
        estiloEje(eje);
        estiloEje(plot.getDomainAxis());
        return chart;
    }

    // ------------------------------------------------------------------ pestaña "Rubros, pagos y días"

    /** Colores de los gráficos de barras por día y por producto. */
    public static final Color VERDE_MAXIMO = new Color(0x1f9d55);
    public static final Color ROJO_MINIMO = new Color(0xd93a3a);
    public static final Color NARANJA_BAJO = new Color(0xeb8a1f);
    public static final Color AZUL_ALTO = new Color(0x2a78d6);
    private static final Color LINEA_FONDO = new Color(0xc9c8c2);
    private static final Color LINEA_FONDO_SUAVE = new Color(0xe6e5e0);

    /** Paleta categórica fija para las tortas: cada porción un color distinto, siempre en este orden. */
    private static final Color[] PALETA = {
        new Color(0x2a78d6), new Color(0xeb6834), new Color(0x1baf7a), new Color(0xeda100),
        new Color(0xe87ba4), new Color(0x008300), new Color(0x6250d6), new Color(0xe34948)};

    /**
     * Barras horizontales: eje X = monto promedio, eje Y = días de la semana.
     * Verde = día más alto, rojo = día más bajo, azul = sobre el promedio, naranja = bajo el promedio.
     * Una línea punteada marca el promedio y el fondo tiene grilla para leer los montos.
     */
    public static JFreeChart promedioPorDia(List<ItemRanking> dias) {
        return barrasSemaforo("Promedio diario de ventas por día de la semana", "Promedio diario", dias,
                "Día más alto", "Día más bajo");
    }

    /**
     * Productos más vendidos: eje Y = productos, eje X = monto facturado.
     * Mismos colores que el gráfico por día: verde = el que más factura, rojo = el que menos
     * dentro del ranking, azul / naranja = sobre / bajo el promedio del ranking.
     */
    public static JFreeChart productos(List<ItemRanking> productos) {
        return barrasSemaforo("Productos que más facturan", "Importe", productos,
                "Mayor facturación", "Menor del ranking");
    }

    /**
     * Barras horizontales coloreadas según su valor: verde = máximo, rojo = mínimo (sin contar ceros),
     * azul = sobre el promedio, naranja = bajo el promedio. Incluye grilla de fondo, línea del
     * promedio, monto al final de cada barra y leyenda que explica los colores.
     */
    private static JFreeChart barrasSemaforo(String titulo, String serie, List<ItemRanking> items,
                                             String textoMaximo, String textoMinimo) {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        for (ItemRanking d : items) {
            ds.addValue(d.importe(), serie, recortar(d.nombre(), 40));
        }
        double promedio = items.stream().mapToDouble(ItemRanking::importe).average().orElse(0);
        double maximo = items.stream().mapToDouble(ItemRanking::importe).max().orElse(0);
        // El mínimo se toma entre los valores mayores a 0: un día cerrado (0) no es "el pico más bajo"
        double minimo = items.stream().mapToDouble(ItemRanking::importe).filter(v -> v > 0).min().orElse(0);

        JFreeChart chart = ChartFactory.createBarChart(titulo,
                null, null, ds, PlotOrientation.HORIZONTAL, true, true, false);
        estilo(chart);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        lineasDeFondo(plot);

        BarRenderer r = new BarRenderer() {
            @Override
            public java.awt.Paint getItemPaint(int fila, int columna) {
                Number n = ds.getValue(fila, columna);
                double v = n == null ? 0 : n.doubleValue();
                if (v == maximo && v > 0) {
                    return VERDE_MAXIMO;
                }
                if (v == minimo && v > 0) {
                    return ROJO_MINIMO;
                }
                return v >= promedio ? AZUL_ALTO : NARANJA_BAJO;
            }
        };
        r.setBarPainter(new StandardBarPainter());
        r.setShadowVisible(false);
        r.setDrawBarOutline(false);
        r.setMaximumBarWidth(0.12);
        // Monto al final de cada barra
        r.setDefaultItemLabelGenerator(new org.jfree.chart.labels.StandardCategoryItemLabelGenerator(
                "{2}", formatoPesos()));
        r.setDefaultItemLabelsVisible(true);
        r.setDefaultItemLabelPaint(TEXTO);
        r.setDefaultPositiveItemLabelPosition(new org.jfree.chart.labels.ItemLabelPosition(
                org.jfree.chart.labels.ItemLabelAnchor.OUTSIDE3, org.jfree.chart.ui.TextAnchor.CENTER_LEFT));
        plot.setRenderer(r);

        // Línea del promedio
        org.jfree.chart.plot.ValueMarker marca = new org.jfree.chart.plot.ValueMarker(promedio);
        marca.setPaint(new Color(0x52514e));
        marca.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1f,
                new float[]{6f, 4f}, 0f));
        marca.setLabel("Promedio " + formatoPesos().format(promedio));
        marca.setLabelPaint(new Color(0x52514e));
        marca.setLabelFont(new Font("SansSerif", Font.PLAIN, 11));
        marca.setLabelAnchor(org.jfree.chart.ui.RectangleAnchor.TOP_RIGHT);
        marca.setLabelTextAnchor(org.jfree.chart.ui.TextAnchor.TOP_LEFT);
        plot.addRangeMarker(marca, org.jfree.chart.ui.Layer.FOREGROUND);

        // Leyenda que explica los colores (el color nunca va solo)
        org.jfree.chart.LegendItemCollection leyenda = new org.jfree.chart.LegendItemCollection();
        leyenda.add(itemLeyenda(textoMaximo, VERDE_MAXIMO));
        leyenda.add(itemLeyenda("Sobre el promedio", AZUL_ALTO));
        leyenda.add(itemLeyenda("Bajo el promedio", NARANJA_BAJO));
        leyenda.add(itemLeyenda(textoMinimo, ROJO_MINIMO));
        plot.setFixedLegendItems(leyenda);

        NumberAxis eje = (NumberAxis) plot.getRangeAxis();
        eje.setNumberFormatOverride(formatoPesos());
        eje.setUpperMargin(0.18); // lugar para el monto al final de la barra más larga
        estiloEje(eje);
        estiloEje(plot.getDomainAxis());
        return chart;
    }

    /**
     * Gráfico de torta con un color por porción y la leyenda a la derecha:
     * [■] Herramientas (90 %) $ 1.100.000,00
     * Si hay más de 8 categorías, las más chicas se agrupan en "Otros".
     */
    public static JFreeChart torta(String titulo, List<ItemRanking> items) {
        java.text.NumberFormat pesos = java.text.NumberFormat.getCurrencyInstance(ES_AR); // con centavos
        return tortaGenerica(titulo, items, pesos, "{0} ({2}) {1}", "{0}: {1} ({2})");
    }

    /**
     * Torta de cantidades (por ejemplo ventas por vendedor). Leyenda: "jperez: 10 ventas (25%)".
     * Usa el campo importe de cada ItemRanking como cantidad.
     */
    public static JFreeChart tortaCantidades(String titulo, List<ItemRanking> items, String unidad) {
        java.text.NumberFormat entero = java.text.NumberFormat.getIntegerInstance(ES_AR);
        return tortaGenerica(titulo, items, entero, "{0}: {1} " + unidad + " ({2})", "{0}: {1} " + unidad + " ({2})");
    }

    /**
     * Ventas de un vendedor por mes o semana, con el mismo estilo que el gráfico por día:
     * eje Y = períodos, eje X = monto; verde = mejor período, rojo = peor, azul / naranja =
     * sobre / bajo su propio promedio.
     */
    public static JFreeChart ventasVendedor(Modelos.InformeIA.VentasVendedor v, Modelos.InformeIA.Granularidad g) {
        List<ItemRanking> items = new java.util.ArrayList<>();
        for (int i = 0; i < v.porPeriodo().size(); i++) {
            PuntoSerie p = v.porPeriodo().get(i);
            items.add(new ItemRanking(Negocio.NHallazgos.etiqueta(p.inicio(), g), p.valor(),
                    v.ventasPorPeriodo().get(i)));
        }
        boolean mensual = g == Modelos.InformeIA.Granularidad.MENSUAL;
        return barrasSemaforo("Ventas de " + v.usuario() + (mensual ? " por mes" : " por semana"), "Ventas",
                items, mensual ? "Mejor mes" : "Mejor semana", mensual ? "Peor mes" : "Peor semana");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static JFreeChart tortaGenerica(String titulo, List<ItemRanking> items, java.text.NumberFormat valores,
                                            String patronLeyenda, String patronTooltip) {
        org.jfree.data.general.DefaultPieDataset ds = new org.jfree.data.general.DefaultPieDataset();
        List<ItemRanking> orden = items.stream().filter(i -> i.importe() > 0)
                .sorted(java.util.Comparator.comparingDouble(ItemRanking::importe).reversed()).toList();
        double otros = 0;
        for (int i = 0; i < orden.size(); i++) {
            if (i < PALETA.length - 1 || orden.size() == PALETA.length) {
                ds.setValue(orden.get(i).nombre() == null ? "(sin nombre)" : orden.get(i).nombre(),
                        orden.get(i).importe());
            } else {
                otros += orden.get(i).importe();
            }
        }
        if (otros > 0) {
            ds.setValue("Otros", otros);
        }

        JFreeChart chart = ChartFactory.createPieChart(titulo, ds, true, true, false);
        estilo(chart);
        org.jfree.chart.plot.PiePlot plot = (org.jfree.chart.plot.PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setShadowPaint(null);
        plot.setLabelGenerator(null); // la información va en la leyenda, no sobre la torta
        plot.setInteriorGap(0.04);
        plot.setDefaultSectionOutlinePaint(Color.WHITE); // separación blanca entre porciones
        plot.setDefaultSectionOutlineStroke(new BasicStroke(2f));
        for (int i = 0; i < ds.getItemCount(); i++) {
            plot.setSectionPaint(ds.getKey(i), PALETA[i % PALETA.length]);
        }
        if (ds.getItemCount() == 1) {
            // Con una sola porción, el borde blanco dibuja una línea del centro hacia arriba que confunde
            plot.setSectionOutlinesVisible(false);
        }

        // "%" sin comillas en el patrón multiplica por 100: 0,9 → 90%
        java.text.NumberFormat porcentaje = new java.text.DecimalFormat("0.#%",
                java.text.DecimalFormatSymbols.getInstance(ES_AR));
        plot.setLegendLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
                patronLeyenda, valores, porcentaje));
        plot.setLegendItemShape(new java.awt.Rectangle(-6, -6, 12, 12));
        plot.setToolTipGenerator(new org.jfree.chart.labels.StandardPieToolTipGenerator(
                patronTooltip, valores, porcentaje));

        if (chart.getLegend() != null) {
            chart.getLegend().setPosition(org.jfree.chart.ui.RectangleEdge.RIGHT);
            chart.getLegend().setItemFont(new Font("SansSerif", Font.PLAIN, 12));
            chart.getLegend().setItemLabelPadding(new org.jfree.chart.ui.RectangleInsets(3, 4, 3, 8));
        }
        return chart;
    }

    /** Grilla visible en el fondo: líneas principales y secundarias en el eje de montos, y separadores entre días. */
    private static void lineasDeFondo(CategoryPlot plot) {
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(LINEA_FONDO);
        plot.setRangeGridlineStroke(new BasicStroke(1f));
        plot.setRangeMinorGridlinesVisible(true);
        plot.setRangeMinorGridlinePaint(LINEA_FONDO_SUAVE);
        plot.setRangeMinorGridlineStroke(new BasicStroke(1f));
        ((NumberAxis) plot.getRangeAxis()).setMinorTickCount(2);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePosition(org.jfree.chart.axis.CategoryAnchor.START);
        plot.setDomainGridlinePaint(LINEA_FONDO_SUAVE);
        plot.setDomainGridlineStroke(new BasicStroke(1f));
    }

    private static org.jfree.chart.LegendItem itemLeyenda(String texto, Color color) {
        return new org.jfree.chart.LegendItem(texto, null, null, null,
                new java.awt.Rectangle(-6, -6, 12, 12), color);
    }

    private static void estilo(JFreeChart chart) {
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 14));
        chart.getTitle().setPaint(new Color(0x0b0b0b));
        if (chart.getLegend() != null) {
            chart.getLegend().setItemPaint(TEXTO);
            chart.getLegend().setFrame(org.jfree.chart.block.BlockBorder.NONE);
        }
    }

    private static void estiloEje(org.jfree.chart.axis.Axis eje) {
        eje.setTickLabelPaint(TEXTO);
        eje.setAxisLinePaint(GRILLA);
        eje.setTickMarkPaint(GRILLA);
        eje.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 11));
    }

    private static Day dia(LocalDate d) {
        return new Day(d.getDayOfMonth(), d.getMonthValue(), d.getYear());
    }

    private static long millis(LocalDate d) {
        return d.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    private static String recortar(String s, int max) {
        if (s == null) {
            return "(sin nombre)";
        }
        return s.length() <= max ? s : s.substring(0, max - 1) + "…";
    }
}