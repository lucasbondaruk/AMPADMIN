package Presentacion;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 * Versión de la pantalla armada en código (sin .form). Usa la misma lógica que la versión
 * diseñada en NetBeans (ControladorInformeIA), así que las dos se comportan igual.
 * Cuando PInformeIADiseno esté terminada, esta clase se puede borrar.
 */
public class PInformeIA extends JDialog {

    private static final Color AZUL_AMP = new Color(38, 90, 142);

    public PInformeIA(Frame padre) {
        super(padre, "Informe de ventas con IA", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(1180, 780));
        setLocationRelativeTo(padre);

        JDateChooser fechaDesde = new JDateChooser();
        JDateChooser fechaHasta = new JDateChooser();
        fechaDesde.setPreferredSize(new Dimension(130, 28));
        fechaHasta.setPreferredSize(new Dimension(130, 28));
        JComboBox<Object> cmbAgrupar = new JComboBox<>();
        JSpinner spnPronosticar = new JSpinner();
        JCheckBox chkIA = new JCheckBox("Analizar con IA", true);
        JButton btnGenerar = new JButton("Generar informe");
        JButton btnPDF = new JButton("Exportar PDF");
        btnGenerar.setBackground(AZUL_AMP);
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.setFocusPainted(false);

        JPanel filtros = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        filtros.add(new JLabel("Desde:"));
        filtros.add(fechaDesde);
        filtros.add(new JLabel("Hasta:"));
        filtros.add(fechaHasta);
        filtros.add(new JLabel("Agrupar:"));
        filtros.add(cmbAgrupar);
        filtros.add(new JLabel("Pronosticar:"));
        filtros.add(spnPronosticar);
        filtros.add(new JLabel("períodos"));
        filtros.add(chkIA);
        filtros.add(btnGenerar);
        filtros.add(btnPDF);

        JLabel lblIngresos = new JLabel("-");
        JLabel lblIngresosVar = new JLabel(" ");
        JLabel lblVentas = new JLabel("-");
        JLabel lblVentasVar = new JLabel(" ");
        JLabel lblTicket = new JLabel("-");
        JLabel lblTicketVar = new JLabel(" ");
        JPanel kpis = new JPanel(new GridLayout(1, 3, 10, 0));
        kpis.setBorder(BorderFactory.createEmptyBorder(4, 10, 8, 10));
        kpis.add(tarjeta("Ingresos", lblIngresos, lblIngresosVar));
        kpis.add(tarjeta("Cantidad de ventas", lblVentas, lblVentasVar));
        kpis.add(tarjeta("Ticket promedio", lblTicket, lblTicketVar));

        JPanel arriba = new JPanel(new BorderLayout());
        arriba.add(filtros, BorderLayout.NORTH);
        arriba.add(kpis, BorderLayout.CENTER);

        JEditorPane txtAnalisis = new JEditorPane();
        JEditorPane txtCalidad = new JEditorPane();
        JPanel panelPronostico = new JPanel();
        JPanel panelProductos = new JPanel();
        JPanel panelDias = new JPanel();
        JPanel panelTortaRubros = new JPanel();
        JPanel panelTortaPagos = new JPanel();
        JTable tablaStock = new JTable();
        tablaStock.setRowHeight(24);

        // Rubros, pagos y días: arriba las barras por día (más bajas), abajo las dos tortas (más grandes)
        JPanel tortas = new JPanel(new GridLayout(1, 2, 6, 0));
        tortas.add(panelTortaRubros);
        tortas.add(panelTortaPagos);
        panelDias.setPreferredSize(new Dimension(0, 230));
        JPanel distribucion = new JPanel(new BorderLayout(0, 6));
        distribucion.add(panelDias, BorderLayout.NORTH);
        distribucion.add(tortas, BorderLayout.CENTER);

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Análisis y recomendaciones", new JScrollPane(txtAnalisis));
        pestanas.addTab("Ventas y pronóstico", panelPronostico);
        pestanas.addTab("Productos", panelProductos);
        pestanas.addTab("Rubros, pagos y días", distribucion);
        // Vendedores: arriba combo + total, después barras por período y abajo la torta
        JComboBox<Object> cmbVendedor = new JComboBox<>();
        JLabel lblTotalVendedor = new JLabel(" ");
        JPanel filtroVendedor = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        filtroVendedor.add(new JLabel("Usuario:"));
        filtroVendedor.add(cmbVendedor);
        filtroVendedor.add(lblTotalVendedor);
        JPanel panelVendedorBarras = new JPanel();
        panelVendedorBarras.setPreferredSize(new Dimension(0, 260));
        JPanel panelVendedorTorta = new JPanel();
        JPanel graficosVendedor = new JPanel(new BorderLayout(0, 6));
        graficosVendedor.add(panelVendedorBarras, BorderLayout.NORTH);
        graficosVendedor.add(panelVendedorTorta, BorderLayout.CENTER);
        JPanel vendedores = new JPanel(new BorderLayout());
        vendedores.add(filtroVendedor, BorderLayout.NORTH);
        vendedores.add(graficosVendedor, BorderLayout.CENTER);
        pestanas.addTab("Vendedores", vendedores);
        pestanas.addTab("Stock crítico", new JScrollPane(tablaStock));
        pestanas.addTab("Calidad de datos y método", new JScrollPane(txtCalidad));

        JLabel lblEstado = new JLabel(" ");
        lblEstado.setBorder(BorderFactory.createEmptyBorder(4, 10, 6, 10));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(arriba, BorderLayout.NORTH);
        getContentPane().add(pestanas, BorderLayout.CENTER);
        getContentPane().add(lblEstado, BorderLayout.SOUTH);

        ControladorInformeIA controlador = new ControladorInformeIA(new ControladorInformeIA.Componentes(
                this, fechaDesde, fechaHasta, cmbAgrupar, spnPronosticar, chkIA, btnGenerar, btnPDF, lblEstado,
                lblIngresos, lblIngresosVar, lblVentas, lblVentasVar, lblTicket, lblTicketVar,
                txtAnalisis, panelPronostico, panelProductos, panelDias, panelTortaRubros, panelTortaPagos,
                tablaStock, txtCalidad));
        controlador.agregarVendedores(cmbVendedor, lblTotalVendedor, panelVendedorBarras, panelVendedorTorta);
    }

    private static JPanel tarjeta(String titulo, JLabel valor, JLabel variacion) {
        JPanel p = new JPanel(new GridLayout(3, 1));
        p.setBackground(new Color(0xf2f2f0));
        p.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        JLabel t = new JLabel(titulo);
        t.setForeground(new Color(0x52514e));
        valor.setFont(valor.getFont().deriveFont(Font.BOLD, 20f));
        valor.setForeground(AZUL_AMP);
        variacion.setForeground(new Color(0x52514e));
        p.add(t);
        p.add(valor);
        p.add(variacion);
        return p;
    }
}
