package Presentacion;

import Modelos.InformeIA;
import Modelos.InformeIA.AnalisisIA;
import Modelos.InformeIA.Granularidad;
import Modelos.InformeIA.ItemRanking;
import Modelos.InformeIA.VentasVendedor;
import Modelos.InformeIA.Kpis;
import Modelos.InformeIA.ProductoCritico;
import Modelos.InformeIA.Recomendacion;
import Negocio.NInformeIA;
import Utilidades.FormatoAR;
import Utilidades.GeneradorPDFInformeIA;
import Utilidades.GraficosInformeIA;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Window;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;

/**
 * Lógica de la pantalla "Informe con IA", separada del diseño.
 *
 * La pantalla se diseña en el diseñador de NetBeans (PInformeIADiseno.form) y este
 * controlador solo la llena: le pasás los componentes del diseño y él se encarga de
 * los eventos de los botones, generar el informe, dibujar los gráficos y exportar el PDF.
 *
 * Uso, en el constructor del JDialog diseñado, después de initComponents():
 * <pre>
 * new ControladorInformeIA(new ControladorInformeIA.Componentes(
 *         this, fechaDesde, fechaHasta, cmbAgrupar, spnPronosticar, chkIA, btnGenerar, btnPDF, lblEstado,
 *         lblIngresos, lblIngresosVar, lblVentas, lblVentasVar, lblTicket, lblTicketVar,
 *         txtAnalisis, panelPronostico, panelProductos, panelDias, panelTortaRubros, panelTortaPagos,
 *         tablaStock, txtCalidad));
 * </pre>
 * Los nombres de tus variables pueden ser otros: lo que importa es el orden y el tipo.
 */
public class ControladorInformeIA {

    /**
     * Componentes del diseño que usa el controlador.
     *
     * @param ventana          el JDialog (this)
     * @param fechaDesde       JDateChooser de la fecha inicial
     * @param fechaHasta       JDateChooser de la fecha final
     * @param cmbAgrupar       JComboBox de agrupación (los ítems los carga el controlador)
     * @param spnPronosticar   JSpinner de cantidad de períodos a pronosticar
     * @param chkIA            JCheckBox "Analizar con IA"
     * @param btnGenerar       botón "Generar informe"
     * @param btnPDF           botón "Exportar PDF"
     * @param lblEstado        label de la barra de estado (abajo)
     * @param lblIngresos      tarjeta Ingresos: label del valor grande
     * @param lblIngresosVar   tarjeta Ingresos: label de la variación vs. período anterior
     * @param lblVentas        tarjeta Cantidad de ventas: valor
     * @param lblVentasVar     tarjeta Cantidad de ventas: variación
     * @param lblTicket        tarjeta Ticket promedio: valor
     * @param lblTicketVar     tarjeta Ticket promedio: variación
     * @param txtAnalisis      JEditorPane de "Análisis y recomendaciones" (dentro de un JScrollPane)
     * @param panelPronostico  JPanel vacío para el gráfico de ventas y pronóstico
     * @param panelProductos   JPanel vacío para el gráfico de productos
     * @param panelDias        JPanel vacío para el gráfico de promedio por día
     * @param panelTortaRubros JPanel vacío para la torta de rubros
     * @param panelTortaPagos  JPanel vacío para la torta de medios de pago
     * @param tablaStock       JTable de stock crítico (dentro de un JScrollPane)
     * @param txtCalidad       JEditorPane de "Calidad de datos y método" (dentro de un JScrollPane)
     */
    public record Componentes(
            Window ventana,
            JDateChooser fechaDesde, JDateChooser fechaHasta,
            JComboBox<?> cmbAgrupar, JSpinner spnPronosticar, JCheckBox chkIA,
            JButton btnGenerar, JButton btnPDF, JLabel lblEstado,
            JLabel lblIngresos, JLabel lblIngresosVar,
            JLabel lblVentas, JLabel lblVentasVar,
            JLabel lblTicket, JLabel lblTicketVar,
            JEditorPane txtAnalisis,
            JPanel panelPronostico, JPanel panelProductos,
            JPanel panelDias, JPanel panelTortaRubros, JPanel panelTortaPagos,
            JTable tablaStock, JEditorPane txtCalidad) {}

    private final Componentes c;
    private final NInformeIA negocio = new NInformeIA();
    private final DefaultTableModel modeloStock = new DefaultTableModel(
            new Object[]{"Código", "Producto", "Stock", "Mínimo", "Venta diaria (30 d)", "Días de cobertura"}, 0) {
        @Override
        public boolean isCellEditable(int r, int col) {
            return false;
        }
    };
    private InformeIA informeActual;

    // Pestaña Vendedores (opcional: se conecta con agregarVendedores)
    private JComboBox<Object> cmbVendedor;
    private JLabel lblTotalVendedor;
    private JPanel panelVendedorBarras;
    private JPanel panelVendedorTorta;
    private boolean cargandoVendedores;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public ControladorInformeIA(Componentes componentes) {
        this.c = componentes;
        LocalDate hoy = LocalDate.now();
        c.fechaDesde().setDate(aDate(hoy.withDayOfMonth(1).minusMonths(2)));
        c.fechaHasta().setDate(aDate(hoy));
        c.fechaDesde().setDateFormatString("dd/MM/yyyy");
        c.fechaHasta().setDateFormatString("dd/MM/yyyy");
        ((JComboBox) c.cmbAgrupar()).setModel(new DefaultComboBoxModel(Granularidad.values()));
        c.spnPronosticar().setModel(new SpinnerNumberModel(3, 1, 12, 1));
        c.btnPDF().setEnabled(false);
        if (!negocio.iaDisponible()) {
            c.chkIA().setSelected(false);
            c.chkIA().setToolTipText("Configurá ia.properties para activar la IA");
        }

        // Contenido HTML y layouts que necesitan los gráficos (pisan lo que tenga el diseño)
        for (JEditorPane t : new JEditorPane[]{c.txtAnalisis(), c.txtCalidad()}) {
            t.setContentType("text/html");
            t.setEditable(false);
        }
        for (JPanel p : new JPanel[]{c.panelPronostico(), c.panelProductos(), c.panelDias(),
                c.panelTortaRubros(), c.panelTortaPagos()}) {
            p.setLayout(new BorderLayout());
        }
        formatearTablaStock(c.tablaStock());

        c.lblEstado().setText("IA: " + negocio.descripcionIA());
        c.btnGenerar().addActionListener(e -> generar());
        c.btnPDF().addActionListener(e -> exportarPDF());
        mostrarHtml("<p style='color:#666'>Elegí el período y presioná <b>Generar informe</b>.</p>");
    }

    /**
     * Conecta la pestaña Vendedores. Llamar después de crear el controlador:
     * <pre>
     * ControladorInformeIA ctrl = new ControladorInformeIA(...);
     * ctrl.agregarVendedores(cmbVendedor, lblTotalVendedor, panelVendedorBarras, panelVendedorTorta);
     * </pre>
     *
     * @param cmbVendedor         JComboBox con todos los usuarios (los ítems los carga el controlador)
     * @param lblTotalVendedor    JLabel con el total de ingresos del usuario elegido
     * @param panelVendedorBarras JPanel vacío para las barras por mes/semana del usuario elegido
     * @param panelVendedorTorta  JPanel vacío para la torta de cantidad de ventas por usuario
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public ControladorInformeIA agregarVendedores(JComboBox<?> cmbVendedor, JLabel lblTotalVendedor,
                                                  JPanel panelVendedorBarras, JPanel panelVendedorTorta) {
        this.cmbVendedor = (JComboBox) cmbVendedor;
        this.lblTotalVendedor = lblTotalVendedor;
        this.panelVendedorBarras = panelVendedorBarras;
        this.panelVendedorTorta = panelVendedorTorta;
        this.cmbVendedor.setModel(new DefaultComboBoxModel<>());
        this.lblTotalVendedor.setText("Generá el informe para ver las ventas por usuario.");
        panelVendedorBarras.setLayout(new BorderLayout());
        panelVendedorTorta.setLayout(new BorderLayout());
        // El gráfico cambia apenas se elige otro usuario (los datos ya están en memoria, no consulta la BD)
        this.cmbVendedor.addActionListener(e -> {
            if (!cargandoVendedores) {
                mostrarVendedorSeleccionado();
            }
        });
        if (informeActual != null) {
            mostrarVendedores(informeActual);
        }
        return this;
    }

    private void mostrarVendedores(InformeIA inf) {
        if (cmbVendedor == null) {
            return;
        }
        cargandoVendedores = true;
        DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<>();
        VentasVendedor mejor = null;
        for (VentasVendedor v : inf.vendedores()) {
            modelo.addElement(v);
            if (mejor == null || v.ingresos() > mejor.ingresos()) {
                mejor = v;
            }
        }
        cmbVendedor.setModel(modelo);
        if (mejor != null) {
            cmbVendedor.setSelectedItem(mejor); // arranca mostrando al que más vendió
        }
        cargandoVendedores = false;

        // Torta: cantidad de ventas de cada usuario en el período
        List<ItemRanking> cantidades = inf.vendedores().stream()
                .filter(v -> v.cantidad() > 0)
                .map(v -> new ItemRanking(v.usuario(), v.cantidad(), v.cantidad()))
                .toList();
        poner(panelVendedorTorta, new ChartPanel(
                GraficosInformeIA.tortaCantidades("Total de ventas por usuario", cantidades, "ventas")));
        mostrarVendedorSeleccionado();
    }

    private void mostrarVendedorSeleccionado() {
        if (informeActual == null || !(cmbVendedor.getSelectedItem() instanceof VentasVendedor v)) {
            return;
        }
        lblTotalVendedor.setText("<html>Total de ingresos de <b>" + esc(v.usuario()) + "</b>: <b>"
                + FormatoAR.pesos(v.ingresos()) + "</b> &nbsp;·&nbsp; " + v.cantidad() + " ventas"
                + " &nbsp;·&nbsp; Ticket promedio: " + FormatoAR.pesos(v.ticketPromedio())
                + (v.cantidad() == 0 ? " &nbsp;<i>(sin ventas en el período)</i>" : "") + "</html>");
        poner(panelVendedorBarras, new ChartPanel(GraficosInformeIA.ventasVendedor(v, informeActual.granularidad())));
    }

    private void generar() {
        if (c.fechaDesde().getDate() == null || c.fechaHasta().getDate() == null) {
            JOptionPane.showMessageDialog(c.ventana(), "Completá las dos fechas.");
            return;
        }
        LocalDate desde = aLocalDate(c.fechaDesde().getDate());
        LocalDate hasta = aLocalDate(c.fechaHasta().getDate());
        Granularidad g = (Granularidad) c.cmbAgrupar().getSelectedItem();
        int horizonte = (Integer) c.spnPronosticar().getValue();
        boolean usarIA = c.chkIA().isSelected();

        c.btnGenerar().setEnabled(false);
        c.btnPDF().setEnabled(false);
        c.ventana().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        c.lblEstado().setText(usarIA
                ? "Calculando estadísticas y redactando con la IA (con Ollama puede tardar un par de minutos)..."
                : "Calculando estadísticas...");

        // SwingWorker: la consulta a la BD y a la IA no congela la pantalla
        new SwingWorker<InformeIA, Void>() {
            @Override
            protected InformeIA doInBackground() throws Exception {
                return negocio.generar(desde, hasta, g, horizonte, usarIA);
            }

            @Override
            protected void done() {
                c.ventana().setCursor(Cursor.getDefaultCursor());
                c.btnGenerar().setEnabled(true);
                try {
                    informeActual = get();
                    mostrar(informeActual);
                    c.btnPDF().setEnabled(true);
                    c.lblEstado().setText(informeActual.errorIA() != null ? informeActual.errorIA()
                            : "Informe generado · IA: " + (usarIA ? negocio.descripcionIA() : "no usada"));
                } catch (Exception ex) {
                    Throwable causa = ex.getCause() != null ? ex.getCause() : ex;
                    c.lblEstado().setText("Error: " + causa.getMessage());
                    JOptionPane.showMessageDialog(c.ventana(),
                            "No se pudo generar el informe:\n" + causa.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    private void mostrar(InformeIA inf) {
        Kpis k = inf.kpis();
        c.lblIngresos().setText(FormatoAR.pesos(k.ingresos()));
        c.lblIngresosVar().setText(variacion(k.variacionIngresos(), FormatoAR.pesos(k.ingresosAnterior())));
        c.lblVentas().setText(String.format(FormatoAR.ES_AR, "%,d", k.cantidadVentas()));
        c.lblVentasVar().setText(variacion(k.variacionCantidad(),
                String.format(FormatoAR.ES_AR, "%,d", k.cantidadVentasAnterior())));
        c.lblTicket().setText(FormatoAR.pesos(k.ticketPromedio()));
        c.lblTicketVar().setText(variacion(k.variacionTicket(), FormatoAR.pesos(k.ticketPromedioAnterior())));
        colorear(c.lblIngresosVar(), k.variacionIngresos());
        colorear(c.lblVentasVar(), k.variacionCantidad());
        colorear(c.lblTicketVar(), k.variacionTicket());

        mostrarHtml(html(inf));
        c.txtCalidad().setText(htmlCalidad(inf));
        c.txtCalidad().setCaretPosition(0);

        JPanel pron = c.panelPronostico();
        pron.removeAll();
        pron.add(new ChartPanel(GraficosInformeIA.ventasYPronostico(inf)), BorderLayout.CENTER);
        pron.add(new JLabel(textoPronostico(inf)), BorderLayout.SOUTH);
        pron.revalidate();
        pron.repaint();

        poner(c.panelProductos(), new ChartPanel(GraficosInformeIA.productos(inf.topProductos())));
        poner(c.panelDias(), new ChartPanel(GraficosInformeIA.promedioPorDia(inf.promedioPorDiaSemana())));
        poner(c.panelTortaRubros(), new ChartPanel(GraficosInformeIA.torta("Ventas por rubro", inf.ventasPorRubro())));
        poner(c.panelTortaPagos(), new ChartPanel(GraficosInformeIA.torta("Medios de pago", inf.ventasPorMetodoPago())));

        mostrarVendedores(inf);

        modeloStock.setRowCount(0);
        for (ProductoCritico p : inf.stockCritico()) {
            modeloStock.addRow(new Object[]{p.codigo(), p.descripcion(), p.stockActual(), p.stockMinimo(),
                String.format("%.2f", p.ventaDiariaPromedio()),
                Double.isInfinite(p.diasCobertura()) ? "Sin ventas" : String.format("%.0f", p.diasCobertura())});
        }
    }

    /** Anchos, alto de fila y contenido centrado en la tabla de stock crítico. */
    private void formatearTablaStock(JTable tabla) {
        tabla.setModel(modeloStock);
        tabla.setDefaultEditor(Object.class, null);
        tabla.setRowSorter(null);
        int[] anchos = {100, 405, 100, 100, 150, 150}; // Código, Producto, Stock, Mínimo, Venta diaria, Días
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < anchos.length; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            tabla.getColumnModel().getColumn(i).setCellRenderer(centro);
        }
        tabla.setRowHeight(30);
    }

    private static void poner(JPanel panel, ChartPanel grafico) {
        panel.removeAll();
        panel.add(grafico, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    private static final java.awt.Color VERDE_SUBA = new java.awt.Color(0x1f8a3b);
    private static final java.awt.Color ROJO_BAJA = new java.awt.Color(0xc62828);
    private static final java.awt.Color GRIS_NEUTRO = new java.awt.Color(0x52514e);

    /** Verde si la variación es positiva, rojo si es negativa, gris si es 0 o no hay datos. */
    private static void colorear(JLabel etiqueta, double variacion) {
        if (Double.isNaN(variacion) || variacion == 0) {
            etiqueta.setForeground(GRIS_NEUTRO);
        } else {
            etiqueta.setForeground(variacion > 0 ? VERDE_SUBA : ROJO_BAJA);
        }
    }

    private static String variacion(double v, String valorAnterior) {
        return GeneradorPDFInformeIA.textoVariacion(v) + " vs. período anterior (" + valorAnterior + ")";
    }

    private void mostrarHtml(String html) {
        c.txtAnalisis().setText(html);
        c.txtAnalisis().setCaretPosition(0);
    }

    private void exportarPDF() {
        if (informeActual == null) {
            return;
        }
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("PDF", "pdf"));
        fc.setSelectedFile(new File("Informe_IA_" + informeActual.desde() + "_" + informeActual.hasta() + ".pdf"));
        if (fc.showSaveDialog(c.ventana()) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File archivo = fc.getSelectedFile();
        if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
            archivo = new File(archivo.getParentFile(), archivo.getName() + ".pdf");
        }
        try {
            GeneradorPDFInformeIA.generar(archivo, informeActual, negocio.descripcionIA());
            JOptionPane.showMessageDialog(c.ventana(), "PDF guardado en:\n" + archivo.getAbsolutePath());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(c.ventana(), "No se pudo generar el PDF:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ------------------------------------------------------------------ HTML y helpers

    private static String textoPronostico(InformeIA inf) {
        if (inf.pronostico() == null) {
            return "  Sin pronóstico: se necesitan al menos 4 períodos completos de ventas.";
        }
        var p = inf.pronostico();
        String s = "  Modelo elegido por validación cruzada: " + p.metodo() + " · Total esperado: "
                + FormatoAR.pesos(p.totalEsperado());
        if (!Double.isNaN(p.errorMedio())) {
            s += " · Error medio: " + FormatoAR.pesos(p.errorMedio()) + " por período";
            if (!Double.isNaN(p.mape())) {
                s += " (" + FormatoAR.pct(p.mape()) + ")";
            }
        }
        return "<html>" + esc(s) + (p.advertencia() == null ? "" : "<br>&nbsp;&nbsp;<i>" + esc(p.advertencia()) + "</i>")
                + "</html>";
    }

    private static String html(InformeIA inf) {
        StringBuilder h = new StringBuilder("<html><body style='font-family:sans-serif; font-size:11px; padding:8px'>");
        AnalisisIA a = inf.analisis();
        if (a == null) {
            h.append("<p style='color:#8a5a00'>")
                    .append(inf.errorIA() != null ? esc(inf.errorIA()) : "Informe generado sin IA.")
                    .append("</p><p>Las estadísticas, gráficos y el pronóstico están en las otras pestañas.</p>");
        }
        if (a != null) {
            if (inf.errorIA() != null) {
                h.append("<p style='color:#8a5a00'>").append(esc(inf.errorIA())).append("</p>");
            }
            h.append("<p style='color:#52514e'>Generado por: <b>").append(esc(a.fuente())).append("</b></p>");
            h.append("<h2 style='color:#265a8e'>Resumen</h2><p>").append(esc(a.resumen())).append("</p>");
            h.append("<h2 style='color:#265a8e'>Interpretación</h2>").append(lista(a.interpretacion()));
            h.append("<h2 style='color:#265a8e'>Recomendaciones</h2>");
            for (Recomendacion r : a.recomendaciones()) {
                String color = switch (r.prioridad().toLowerCase()) {
                    case "alta" -> "#b3261e";
                    case "media" -> "#8a5a00";
                    default -> "#52514e";
                };
                h.append("<p><b style='color:").append(color).append("'>[").append(esc(r.prioridad()))
                        .append("]</b> <b>").append(esc(r.titulo())).append(":</b> ")
                        .append(esc(r.detalle()))
                        .append(" <span style='color:#888'>(basado en ").append(String.join(", ", r.basadoEn()))
                        .append(")</span></p>");
            }
            if (!a.alertas().isEmpty()) {
                h.append("<h2 style='color:#b3261e'>Alertas</h2>").append(lista(a.alertas()));
            }
            h.append("<h2 style='color:#265a8e'>Pronóstico</h2><p>").append(esc(a.comentarioPronostico())).append("</p>");
            h.append("<p style='color:#888; font-size:9px'>Los números provienen de los hallazgos calculados por "
                    + "AMPADMIN (pestaña Calidad de datos y método); la IA solo redacta y cada dato fue verificado. "
                    + "Revisalo antes de tomar decisiones.</p>");
        }
        return h.append("</body></html>").toString();
    }

    private static String htmlCalidad(InformeIA inf) {
        StringBuilder h = new StringBuilder("<html><body style='font-family:sans-serif; font-size:11px; padding:8px'>");
        h.append("<h2 style='color:#265a8e'>Calidad de datos</h2>");
        var cal = inf.calidad();
        if (cal.problemas().isEmpty() && cal.ticketsAtipicos() == 0 && cal.periodosAtipicos().isEmpty()) {
            h.append("<p>No se detectaron problemas.</p>");
        } else {
            h.append("<table border='0' cellpadding='4'><tr style='background:#265a8e; color:white'>"
                    + "<th>Problema</th><th>Cantidad</th><th>Tratamiento</th><th>Detalle</th></tr>");
            for (var p : cal.problemas()) {
                h.append("<tr><td>").append(esc(p.tipo())).append("</td><td align='right'>").append(p.cantidad())
                        .append("</td><td>").append(p.excluido() ? "Excluido" : "Para revisar")
                        .append("</td><td>").append(esc(p.detalle())).append("</td></tr>");
            }
            if (cal.ticketsAtipicos() > 0) {
                h.append("<tr><td>Ticket atípico</td><td align='right'>").append(cal.ticketsAtipicos())
                        .append("</td><td>Para revisar</td><td>Ventas con importe muy distinto al resto "
                        + "(z robusto &gt; 3,5 sobre el logaritmo del total)</td></tr>");
            }
            for (var p : cal.periodosAtipicos()) {
                h.append("<tr><td>Período atípico</td><td align='right'>1</td><td>")
                        .append(cal.serieCorregida() ? "Corregido para el pronóstico" : "Solo informado")
                        .append("</td><td>").append(esc(p.inicio().toString())).append(": ")
                        .append(FormatoAR.pesos(p.valor())).append("</td></tr>");
            }
            h.append("</table>");
        }

        h.append("<h2 style='color:#265a8e'>Hallazgos calculados (base de la interpretación)</h2><ul>");
        for (var x : inf.hallazgos()) {
            h.append("<li><b>").append(x.id()).append("</b> ").append(esc(x.texto())).append("</li>");
        }
        h.append("</ul>");

        var p = inf.pronostico();
        if (p != null && !p.evaluados().isEmpty()) {
            h.append("<h2 style='color:#265a8e'>Selección del modelo de pronóstico</h2>"
                    + "<p>Validación cruzada temporal: se simularon pronósticos en el pasado y se compararon con lo "
                    + "realmente vendido. Gana el de menor error medio.</p>"
                    + "<table border='0' cellpadding='4'><tr style='background:#265a8e; color:white'>"
                    + "<th>Modelo</th><th>Error medio por período</th><th>Error %</th></tr>");
            for (var e : p.evaluados()) {
                boolean elegido = e.nombre().equals(p.metodo());
                h.append("<tr").append(elegido ? " style='font-weight:bold'" : "").append("><td>")
                        .append(esc(e.nombre())).append(elegido ? " ✓" : "").append("</td><td align='right'>")
                        .append(FormatoAR.pesos(e.errorMedio())).append("</td><td align='right'>")
                        .append(Double.isNaN(e.mape()) ? "-" : FormatoAR.pct(e.mape())).append("</td></tr>");
            }
            h.append("</table>");
        }
        return h.append("</body></html>").toString();
    }

    private static String lista(List<String> items) {
        StringBuilder sb = new StringBuilder("<ul>");
        items.forEach(i -> sb.append("<li>").append(esc(i)).append("</li>"));
        return sb.append("</ul>").toString();
    }

    private static String esc(String s) {
        return s == null ? "" : s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private static Date aDate(LocalDate d) {
        return Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static LocalDate aLocalDate(Date d) {
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}