package Presentacion;

import Datos.DCliente;
import Datos.DProducto;
import java.util.Date;
import java.awt.Color;
import Modelos.Cliente;
import Modelos.DetalleVenta;
import Modelos.Producto;
import Negocio.NProducto;
import Negocio.NDetalleVenta;
import Negocio.NCliente;
import Negocio.NCondIVA;
import Modelos.CondIVA;
import Modelos.IVA;
import Modelos.MetodoPago;
import Modelos.RubroProducto;
import Modelos.TipoProducto;
import Modelos.Usuario;
import Modelos.Venta;
import Negocio.NIVA;
import Negocio.NMetodoPago;
import Negocio.NRubroProducto;
import Negocio.NTipoProducto;
import Negocio.NVenta;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.File;
import java.text.SimpleDateFormat;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import Datos.DEstadistica;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.Map;
import java.awt.Dimension;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.axis.NumberAxis;
import java.text.NumberFormat;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import Utilidades.GeneradorPDF;
import com.itextpdf.text.DocumentException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class PPanelAMP extends javax.swing.JFrame {

    private Cliente actual = null;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PPanelAMP.class.getName());
    private NDetalleVenta nDetalleVenta = new NDetalleVenta();
    private NProducto nProducto = new NProducto();
    DecimalFormat df = new DecimalFormat("#,##0.00");
    private Usuario usuarioLogueado;
    private Cliente clienteVenta;
    private float totalVenta = 0;
    private float descuentoTotal = 0;
    private float subtotalVenta = 0;
    private float interesCalculado = 0;
    private float totalConInteres = 0;
    private float ivaCalculado = 0;
    private boolean esCuota = false;
    private List<DetalleVenta> ListaVentas = new ArrayList<>();
    private final DEstadistica dEstadistica = new DEstadistica();
    private Timer timerEstadisticas;
    private JFreeChart graficoVentas;
    private JFreeChart graficoMetodosPago;
    private JFreeChart graficoClientesTipo;
    private JFreeChart graficoClientesIVA;

    private final Negocio.NCliente nCliente = new Negocio.NCliente();
    private final Negocio.NMetodoPago nMetodoPago = new Negocio.NMetodoPago();
    private final Negocio.NCobros nCobros = new Negocio.NCobros();
    private java.util.List<Modelos.Cliente> listaClientesGlobal;
    private java.util.List<Modelos.MetodoPago> listaMetodosPagoGlobal;
    private java.util.Date fechaVentaOriginal;

    public PPanelAMP(Usuario usuarioLogueado) {
        initComponents();
        this.usuarioLogueado = usuarioLogueado;
        setLocationRelativeTo(null);
        iniciarPaneles();
        opc = false;
        cargarCbCondIva();
        cargarClientes();
        cargarTabla();
        cargarMetodosPago();
        revalidate();
        repaint();
    }
    private boolean opc;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PBuscarProductoV = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaProductosV = new javax.swing.JTable();
        txtDescBP = new javax.swing.JTextField();
        cbTipoV = new javax.swing.JComboBox<>();
        cbRubroV = new javax.swing.JComboBox<>();
        BuscarProdBP = new javax.swing.JButton();
        AgregarProdBP = new javax.swing.JButton();
        LimpiarFiltroBP = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        BuscarProdBPDesc = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        administracionPanel = new javax.swing.JPanel();
        navbarADM = new javax.swing.JPanel();
        lblimg = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnAClientes = new javax.swing.JButton();
        btnAPagos = new javax.swing.JButton();
        btnAListas = new javax.swing.JButton();
        btnInformes = new javax.swing.JButton();
        btnCaja = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnAdministracion = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnEstadisticas = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        BtnsADM = new javax.swing.JPanel();
        btnABuscar = new javax.swing.JButton();
        btnAContinuar = new javax.swing.JButton();
        btnAModificar = new javax.swing.JButton();
        btnABorrar = new javax.swing.JButton();
        regEmp = new javax.swing.JPanel();
        btnAEmpresa = new javax.swing.JButton();
        btnAPersona = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCuit = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDom = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDate = new com.toedter.calendar.JDateChooser();
        txtRS = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbCondIva = new javax.swing.JComboBox<>();
        autPers = new javax.swing.JPanel();
        txtDNIRetiro1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtNomRetiro1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        autEmp = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtDNIRetiro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDNIPago = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNomRetiro = new javax.swing.JTextField();
        txtNomPago = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ListaPanel = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLista = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnAGestionarCC = new javax.swing.JButton();
        btnAContinuar3 = new javax.swing.JButton();
        btnADetalleCC = new javax.swing.JButton();
        txtFiltroCuit = new javax.swing.JTextField();
        txtFiltroRS = new javax.swing.JTextField();
        cbTipo = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        btnFiltrar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        PagosPanel = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        PagosPanelCampos = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        cbNombre = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtCuil = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        date = new com.toedter.calendar.JDateChooser();
        jLabel53 = new javax.swing.JLabel();
        txtComprasTotales = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        cbMetodosPagos = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        txtCuotasPagos = new javax.swing.JTextField();
        btCalcularCuotas = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        PanelCalculo = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txtUnidadCuota = new javax.swing.JTextField();
        txtInteres = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        btCalcular = new javax.swing.JButton();
        PanelBotones = new javax.swing.JPanel();
        btnAContinuar4 = new javax.swing.JButton();
        btnABuscar3 = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        txtMontoTotal = new javax.swing.JTextField();
        cbDescuento = new javax.swing.JCheckBox();
        btImprimir = new javax.swing.JButton();
        verDetallePanel = new javax.swing.JPanel();
        txtSaldo1 = new javax.swing.JLabel();
        txtSaldoT = new javax.swing.JLabel();
        txtNomCC = new javax.swing.JLabel();
        lblCC = new javax.swing.JLabel();
        ScrollDetalle = new javax.swing.JScrollPane();
        tarjetaDetalle = new javax.swing.JPanel();
        BtnDetalleCC = new javax.swing.JPanel();
        btnAVolver = new javax.swing.JButton();
        btnAExportar = new javax.swing.JButton();
        btnACobrar = new javax.swing.JButton();
        ventasPanel = new javax.swing.JPanel();
        navbarVNT = new javax.swing.JPanel();
        lblimg1 = new javax.swing.JLabel();
        btnInformes1 = new javax.swing.JButton();
        btnCaja1 = new javax.swing.JButton();
        btnProveedores1 = new javax.swing.JButton();
        btnAdministracion1 = new javax.swing.JButton();
        btnVentas1 = new javax.swing.JButton();
        btnEstadisticas1 = new javax.swing.JButton();
        btnProductos1 = new javax.swing.JButton();
        detPanel = new javax.swing.JPanel();
        txtStock = new javax.swing.JTextField();
        txtPre1 = new javax.swing.JTextField();
        txtPre2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtIVA = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        listaPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaVenta = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        BtnsVNT1 = new javax.swing.JPanel();
        EliminarItemV = new javax.swing.JButton();
        DatosCobroVenta = new javax.swing.JButton();
        RehacerVenta = new javax.swing.JButton();
        ConfirmarVenta = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblFact = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        buscarPanel = new javax.swing.JPanel();
        txtDescV = new javax.swing.JTextField();
        BuscarDescV = new javax.swing.JButton();
        RehacerV = new javax.swing.JButton();
        BuscarV = new javax.swing.JButton();
        AgregarV = new javax.swing.JButton();
        txtCantV = new javax.swing.JTextField();
        txtCodV = new javax.swing.JTextField();
        resumenPanel = new javax.swing.JPanel();
        TotalV = new javax.swing.JLabel();
        SubtotalV = new javax.swing.JLabel();
        DescuentoV = new javax.swing.JLabel();
        IvaV = new javax.swing.JLabel();
        metodoPago = new javax.swing.JPanel();
        panelFact = new javax.swing.JPanel();
        cbMpVentas = new javax.swing.JComboBox<>();
        factB = new javax.swing.JButton();
        factA = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        labelFact = new javax.swing.JLabel();
        txtCuotas = new javax.swing.JTextField();
        factX = new javax.swing.JButton();
        panelCli = new javax.swing.JPanel();
        cbCondIvaMp = new javax.swing.JComboBox<>();
        AgregarCliMP = new javax.swing.JButton();
        BuscarCliMP = new javax.swing.JButton();
        txtDomCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtCuitCliente = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        LimpiarMp = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        cancelarMP1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        productosPanel = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btnActualizarP = new javax.swing.JButton();
        btnListasP = new javax.swing.JButton();
        btnGestionarP = new javax.swing.JButton();
        lblimg3 = new javax.swing.JLabel();
        btnInformes3 = new javax.swing.JButton();
        btnEstadisticas3 = new javax.swing.JButton();
        btnVentas3 = new javax.swing.JButton();
        btnAdministracion3 = new javax.swing.JButton();
        btnProductos3 = new javax.swing.JButton();
        btnProveedores3 = new javax.swing.JButton();
        btnCaja3 = new javax.swing.JButton();
        InicioProdPanel = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        AccionGestion = new javax.swing.JPanel();
        lblHeaderAgregar = new javax.swing.JLabel();
        lblHeaderAgregar1 = new javax.swing.JLabel();
        DatosProdAPanel = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel11 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        GVolver = new javax.swing.JButton();
        AGAceptar = new javax.swing.JButton();
        lblHeaderModificar1 = new javax.swing.JLabel();
        lblHeaderModificar = new javax.swing.JLabel();
        MGAceptar = new javax.swing.JButton();
        GestionProdPanel = new javax.swing.JPanel();
        btnsGestion = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        globGestion = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        listaGestion = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaProductosP = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        estadísticaPanel = new javax.swing.JPanel();
        lblimg2 = new javax.swing.JLabel();
        btnInformes2 = new javax.swing.JButton();
        btnCaja2 = new javax.swing.JButton();
        btnProveedores2 = new javax.swing.JButton();
        btnAdministracion2 = new javax.swing.JButton();
        btnVentas2 = new javax.swing.JButton();
        btnEstadisticas2 = new javax.swing.JButton();
        btnProductos2 = new javax.swing.JButton();
        panelContenidoEstadisticas = new javax.swing.JPanel();
        btnInformeVentas = new javax.swing.JButton();
        btnInformeClientes = new javax.swing.JButton();
        btnInformeStock = new javax.swing.JButton();
        panelInformePDF = new javax.swing.JPanel();
        lblTituloInformeVentas = new javax.swing.JLabel();
        cantidadPanel = new javax.swing.JPanel();
        lblCantVentas = new javax.swing.JLabel();
        lblCantidadVentas = new javax.swing.JLabel();
        ingresosPanel = new javax.swing.JPanel();
        lblIngTotales = new javax.swing.JLabel();
        lblIngresosTotales = new javax.swing.JLabel();
        promedioPanel = new javax.swing.JPanel();
        lblTicketProm = new javax.swing.JLabel();
        lblTicketPromedio = new javax.swing.JLabel();
        evolucionPanel = new javax.swing.JPanel();
        panelGraficoVentas = new javax.swing.JPanel();
        metodosPanel = new javax.swing.JPanel();
        panelGraficoMetodos = new javax.swing.JPanel();
        actPanel = new javax.swing.JPanel();
        lblUltimaAct = new javax.swing.JLabel();
        lblUltimaActualizacion = new javax.swing.JLabel();
        btnExportarInformePDF = new javax.swing.JButton();
        panelInformeClientes = new javax.swing.JPanel();
        lblTituloInformeVentas1 = new javax.swing.JLabel();
        totClientesPanel = new javax.swing.JPanel();
        lblCantClientes = new javax.swing.JLabel();
        lblCantidadClientes = new javax.swing.JLabel();
        totEmpresasPanel = new javax.swing.JPanel();
        lblCantEmpresas = new javax.swing.JLabel();
        lblCantidadEmpresas = new javax.swing.JLabel();
        totPersonasPanel = new javax.swing.JPanel();
        lblCantPersonas = new javax.swing.JLabel();
        lblCantidadPersonas = new javax.swing.JLabel();
        actPanel1 = new javax.swing.JPanel();
        lblUltimaAct1 = new javax.swing.JLabel();
        lblUltimaActualizacion1 = new javax.swing.JLabel();
        tipoPanel = new javax.swing.JPanel();
        panelGraficoClientesTipo = new javax.swing.JPanel();
        IVAPanel = new javax.swing.JPanel();
        panelGraficoClientesIVA = new javax.swing.JPanel();
        btnExportarInformePDF1 = new javax.swing.JButton();
        panelInformeStock = new javax.swing.JPanel();

        PBuscarProductoV.setMinimumSize(new java.awt.Dimension(870, 464));
        PBuscarProductoV.setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaProductosV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaProductosV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Precio U.", "Rubro", "Tipo"
            }
        ));
        jScrollPane3.setViewportView(tablaProductosV);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 870, 300));

        txtDescBP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescBP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ingrese una descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel2.add(txtDescBP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 230, 50));

        cbTipoV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbTipoV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel2.add(cbTipoV, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 170, 50));

        cbRubroV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbRubroV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rubro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        cbRubroV.addActionListener(this::cbRubroVActionPerformed);
        jPanel2.add(cbRubroV, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 210, 50));

        BuscarProdBP.setBackground(new java.awt.Color(219, 229, 253));
        BuscarProdBP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BuscarProdBP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        BuscarProdBP.addActionListener(this::BuscarProdBPActionPerformed);
        jPanel2.add(BuscarProdBP, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 50, 40));

        AgregarProdBP.setBackground(new java.awt.Color(98, 189, 98));
        AgregarProdBP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AgregarProdBP.setForeground(new java.awt.Color(255, 255, 255));
        AgregarProdBP.setText("+");
        AgregarProdBP.addActionListener(this::AgregarProdBPActionPerformed);
        jPanel2.add(AgregarProdBP, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 70, 50, 40));

        LimpiarFiltroBP.setBackground(new java.awt.Color(142, 6, 6));
        LimpiarFiltroBP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LimpiarFiltroBP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rehacer-blanco.png"))); // NOI18N
        LimpiarFiltroBP.addActionListener(this::LimpiarFiltroBPActionPerformed);
        jPanel2.add(LimpiarFiltroBP, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 70, 50, 40));

        jPanel6.setBackground(new java.awt.Color(38, 90, 142));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Búsqueda de productos");
        jPanel6.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, -1, -1));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 30));

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 870, 50));

        BuscarProdBPDesc.setBackground(new java.awt.Color(219, 229, 253));
        BuscarProdBPDesc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BuscarProdBPDesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        BuscarProdBPDesc.addActionListener(this::BuscarProdBPDescActionPerformed);
        jPanel2.add(BuscarProdBPDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 50, 40));

        javax.swing.GroupLayout PBuscarProductoVLayout = new javax.swing.GroupLayout(PBuscarProductoV.getContentPane());
        PBuscarProductoV.getContentPane().setLayout(PBuscarProductoVLayout);
        PBuscarProductoVLayout.setHorizontalGroup(
            PBuscarProductoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PBuscarProductoVLayout.setVerticalGroup(
            PBuscarProductoVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(991, 777));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        administracionPanel.setBackground(new java.awt.Color(255, 255, 255));
        administracionPanel.setMaximumSize(new java.awt.Dimension(976, 738));
        administracionPanel.setMinimumSize(new java.awt.Dimension(976, 738));
        administracionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navbarADM.setBackground(new java.awt.Color(225, 250, 255));
        navbarADM.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NAVBAR.png"))); // NOI18N
        navbarADM.add(lblimg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        btnAClientes.setBackground(new java.awt.Color(38, 90, 142));
        btnAClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnAClientes.setText("Clientes");
        btnAClientes.setBorderPainted(false);
        btnAClientes.addActionListener(this::btnAClientesActionPerformed);

        btnAPagos.setBackground(new java.awt.Color(20, 52, 83));
        btnAPagos.setForeground(new java.awt.Color(255, 255, 255));
        btnAPagos.setText("Pagos");
        btnAPagos.setBorderPainted(false);
        btnAPagos.addActionListener(this::btnAPagosActionPerformed);

        btnAListas.setBackground(new java.awt.Color(20, 52, 83));
        btnAListas.setForeground(new java.awt.Color(255, 255, 255));
        btnAListas.setText("Listas");
        btnAListas.setBorderPainted(false);
        btnAListas.addActionListener(this::btnAListasActionPerformed);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(441, Short.MAX_VALUE)
                .addComponent(btnAPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAListas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(227, 227, 227))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAListas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        navbarADM.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 50));

        btnInformes.setBackground(new java.awt.Color(204, 255, 255));
        btnInformes.setForeground(new java.awt.Color(0, 51, 102));
        btnInformes.setText("Informes");
        btnInformes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInformes.setBorderPainted(false);
        btnInformes.setContentAreaFilled(false);
        btnInformes.addActionListener(this::btnInformesActionPerformed);
        navbarADM.add(btnInformes, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 90, 50));

        btnCaja.setBackground(new java.awt.Color(204, 255, 255));
        btnCaja.setForeground(new java.awt.Color(0, 51, 102));
        btnCaja.setText("Caja");
        btnCaja.setBorderPainted(false);
        btnCaja.setContentAreaFilled(false);
        navbarADM.add(btnCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 100, 50));

        btnProveedores.setBackground(new java.awt.Color(204, 255, 255));
        btnProveedores.setForeground(new java.awt.Color(0, 51, 102));
        btnProveedores.setText("Proveedores");
        btnProveedores.setBorderPainted(false);
        btnProveedores.setContentAreaFilled(false);
        navbarADM.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 120, 50));

        btnAdministracion.setBackground(new java.awt.Color(20, 52, 83));
        btnAdministracion.setForeground(new java.awt.Color(255, 255, 255));
        btnAdministracion.setText("Administración");
        btnAdministracion.setBorderPainted(false);
        btnAdministracion.addActionListener(this::btnAdministracionActionPerformed);
        navbarADM.add(btnAdministracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 120, 50));

        btnVentas.setBackground(new java.awt.Color(204, 255, 255));
        btnVentas.setForeground(new java.awt.Color(0, 51, 102));
        btnVentas.setText("Ventas");
        btnVentas.setBorderPainted(false);
        btnVentas.setContentAreaFilled(false);
        btnVentas.addActionListener(this::btnVentasActionPerformed);
        navbarADM.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 90, 50));

        btnEstadisticas.setBackground(new java.awt.Color(204, 255, 255));
        btnEstadisticas.setForeground(new java.awt.Color(0, 51, 102));
        btnEstadisticas.setText("Estadísticas");
        btnEstadisticas.setBorderPainted(false);
        btnEstadisticas.setContentAreaFilled(false);
        btnEstadisticas.addActionListener(this::btnEstadisticasActionPerformed);
        navbarADM.add(btnEstadisticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 120, 50));

        btnProductos.setBackground(new java.awt.Color(204, 255, 255));
        btnProductos.setForeground(new java.awt.Color(0, 51, 102));
        btnProductos.setText("Productos");
        btnProductos.setBorderPainted(false);
        btnProductos.setContentAreaFilled(false);
        btnProductos.addActionListener(this::btnProductosActionPerformed);
        navbarADM.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 100, 50));

        administracionPanel.add(navbarADM, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, -1));

        BtnsADM.setBackground(new java.awt.Color(22, 22, 22));

        btnABuscar.setBackground(new java.awt.Color(22, 22, 22));
        btnABuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnABuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnABuscar.setText("<Buscar Cliente>");
        btnABuscar.setBorderPainted(false);
        btnABuscar.addActionListener(this::btnABuscarActionPerformed);

        btnAContinuar.setBackground(new java.awt.Color(22, 22, 22));
        btnAContinuar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAContinuar.setForeground(new java.awt.Color(255, 255, 255));
        btnAContinuar.setText("<Registrar>");
        btnAContinuar.setBorderPainted(false);
        btnAContinuar.addActionListener(this::btnAContinuarActionPerformed);

        btnAModificar.setBackground(new java.awt.Color(22, 22, 22));
        btnAModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnAModificar.setText("<Modificar Cliente>");
        btnAModificar.setBorderPainted(false);
        btnAModificar.addActionListener(this::btnAModificarActionPerformed);

        btnABorrar.setBackground(new java.awt.Color(22, 22, 22));
        btnABorrar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnABorrar.setForeground(new java.awt.Color(255, 255, 255));
        btnABorrar.setText("<Borrar Cliente>");
        btnABorrar.setBorderPainted(false);
        btnABorrar.addActionListener(this::btnABorrarActionPerformed);

        javax.swing.GroupLayout BtnsADMLayout = new javax.swing.GroupLayout(BtnsADM);
        BtnsADM.setLayout(BtnsADMLayout);
        BtnsADMLayout.setHorizontalGroup(
            BtnsADMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnsADMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnABuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnAModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnABorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        BtnsADMLayout.setVerticalGroup(
            BtnsADMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnsADMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnABuscar)
                .addComponent(btnAContinuar)
                .addComponent(btnAModificar)
                .addComponent(btnABorrar))
        );

        administracionPanel.add(BtnsADM, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 980, 40));

        regEmp.setBackground(new java.awt.Color(255, 255, 255));
        regEmp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAEmpresa.setBackground(new java.awt.Color(2, 33, 55));
        btnAEmpresa.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 16)); // NOI18N
        btnAEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        btnAEmpresa.setText("EMPRESA");
        btnAEmpresa.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAEmpresa.setBorderPainted(false);
        btnAEmpresa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAEmpresa.addActionListener(this::btnAEmpresaActionPerformed);
        regEmp.add(btnAEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 116, 40));

        btnAPersona.setBackground(new java.awt.Color(0, 66, 115));
        btnAPersona.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 16)); // NOI18N
        btnAPersona.setForeground(new java.awt.Color(255, 255, 255));
        btnAPersona.setText("PERSONA");
        btnAPersona.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAPersona.setBorderPainted(false);
        btnAPersona.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAPersona.addActionListener(this::btnAPersonaActionPerformed);
        regEmp.add(btnAPersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 116, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("CUIT/CUIL:");
        regEmp.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, -1, -1));

        txtCuit.setBackground(new java.awt.Color(226, 225, 225));
        txtCuit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCuit.setBorder(null);
        regEmp.add(txtCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, 240, 33));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mail:");
        regEmp.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, -1, -1));

        txtMail.setBackground(new java.awt.Color(226, 225, 225));
        txtMail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMail.setBorder(null);
        regEmp.add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 320, 33));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Condición IVA:");
        regEmp.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, -1, -1));

        txtTel.setBackground(new java.awt.Color(226, 225, 225));
        txtTel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTel.setBorder(null);
        regEmp.add(txtTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 150, 33));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Domicilio:");
        regEmp.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, -1, -1));

        txtDom.setBackground(new java.awt.Color(226, 225, 225));
        txtDom.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDom.setBorder(null);
        regEmp.add(txtDom, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 255, 33));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 40)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 66, 115));
        jLabel9.setText("Registro de cuentas corrientes");
        regEmp.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, 60));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Fecha de ingreso:");
        regEmp.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 180, -1, -1));

        txtDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        regEmp.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 200, 180, 30));

        txtRS.setBackground(new java.awt.Color(226, 225, 225));
        txtRS.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtRS.setBorder(null);
        regEmp.add(txtRS, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 490, 33));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Razón Social/Nombre:");
        regEmp.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1, -1));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Teléfono:");
        regEmp.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, -1, -1));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Serif", 0, 130)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setText("AMPADMIN");
        regEmp.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, 116));

        cbCondIva.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        regEmp.add(cbCondIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 320, 350, 30));

        administracionPanel.add(regEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 980, 400));

        autPers.setBackground(new java.awt.Color(255, 255, 255));
        autPers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDNIRetiro1.setBackground(new java.awt.Color(226, 225, 225));
        txtDNIRetiro1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDNIRetiro1.setBorder(null);
        autPers.add(txtDNIRetiro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 255, 33));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(2, 43, 73));
        jLabel21.setText("Autorizados a retiro de productos");
        autPers.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, 40));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Nombre:");
        autPers.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        txtNomRetiro1.setBackground(new java.awt.Color(226, 225, 225));
        txtNomRetiro1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNomRetiro1.setBorder(null);
        autPers.add(txtNomRetiro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 255, 33));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("DNI:");
        autPers.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, -1, -1));

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton6.setText("+");
        autPers.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 204));
        jLabel6.setText("Para BUSCAR a una cuenta, ingrese el CUIT/CUIL.");
        autPers.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        administracionPanel.add(autPers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 980, 200));

        autEmp.setBackground(new java.awt.Color(255, 255, 255));
        autEmp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(2, 43, 73));
        jLabel15.setText("Autorizados al pago de la cuenta");
        autEmp.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, -1, 40));

        txtDNIRetiro.setBackground(new java.awt.Color(226, 225, 225));
        txtDNIRetiro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDNIRetiro.setBorder(null);
        autEmp.add(txtDNIRetiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 255, 33));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("DNI:");
        autEmp.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, -1, -1));

        txtDNIPago.setBackground(new java.awt.Color(226, 225, 225));
        txtDNIPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDNIPago.setBorder(null);
        autEmp.add(txtDNIPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 255, 33));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Nombre:");
        autEmp.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setText("+");
        autEmp.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, -1, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(2, 43, 73));
        jLabel17.setText("Autorizados a retiro de productos");
        autEmp.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, -1, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Nombre:");
        autEmp.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        txtNomRetiro.setBackground(new java.awt.Color(226, 225, 225));
        txtNomRetiro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNomRetiro.setBorder(null);
        autEmp.add(txtNomRetiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 255, 33));

        txtNomPago.setBackground(new java.awt.Color(226, 225, 225));
        txtNomPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNomPago.setBorder(null);
        autEmp.add(txtNomPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 255, 33));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("DNI:");
        autEmp.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("+");
        autEmp.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setText("Para BUSCAR a una cuenta, ingrese el CUIT/CUIL.");
        autEmp.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        administracionPanel.add(autEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 980, 200));

        ListaPanel.setBackground(new java.awt.Color(255, 255, 255));
        ListaPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 66, 115));
        jLabel25.setText("Búsqueda por:");
        ListaPanel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, -1));

        tablaLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaLista);

        ListaPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 980, 520));

        jPanel1.setBackground(new java.awt.Color(22, 22, 22));

        btnAGestionarCC.setBackground(new java.awt.Color(22, 22, 22));
        btnAGestionarCC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAGestionarCC.setForeground(new java.awt.Color(255, 255, 255));
        btnAGestionarCC.setText("<Gestionar>");
        btnAGestionarCC.setActionCommand("<Volver>");
        btnAGestionarCC.setBorderPainted(false);
        btnAGestionarCC.addActionListener(this::btnAGestionarCCActionPerformed);

        btnAContinuar3.setBackground(new java.awt.Color(22, 22, 22));
        btnAContinuar3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAContinuar3.setForeground(new java.awt.Color(255, 255, 255));
        btnAContinuar3.setText("<Exportar>");
        btnAContinuar3.setToolTipText("");
        btnAContinuar3.setActionCommand("<Volver>");
        btnAContinuar3.setBorderPainted(false);
        btnAContinuar3.addActionListener(this::btnExportarCliente);

        btnADetalleCC.setBackground(new java.awt.Color(22, 22, 22));
        btnADetalleCC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnADetalleCC.setForeground(new java.awt.Color(255, 255, 255));
        btnADetalleCC.setText("<Ver detalle>");
        btnADetalleCC.setToolTipText("");
        btnADetalleCC.setActionCommand("<Volver>");
        btnADetalleCC.setBorderPainted(false);
        btnADetalleCC.addActionListener(this::btnADetalleCCActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(btnAGestionarCC, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(btnADetalleCC, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(btnAContinuar3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAGestionarCC)
                    .addComponent(btnAContinuar3)
                    .addComponent(btnADetalleCC))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        ListaPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 980, 40));
        ListaPanel.add(txtFiltroCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 110, -1));

        txtFiltroRS.addActionListener(this::txtFiltroRSActionPerformed);
        ListaPanel.add(txtFiltroRS, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 110, -1));

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Persona", "Empresa" }));
        ListaPanel.add(cbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 140, 30));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        ListaPanel.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 80, 30));

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(this::btnFiltrarActionPerformed);
        ListaPanel.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 80, 30));

        jLabel12.setText("Razón Social");
        ListaPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        jLabel19.setText("Tipo");
        ListaPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        jLabel20.setText("CUIT/CUIL");
        ListaPanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        administracionPanel.add(ListaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 980, 640));

        PagosPanel.setBackground(new java.awt.Color(255, 255, 255));
        PagosPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 40)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 66, 115));
        jLabel24.setText("Pago de cuentas corrientes");
        PagosPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, 60));

        PagosPanelCampos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setText("Razón Social/Nombre:");
        PagosPanelCampos.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        cbNombre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNombre.addItemListener(this::cbNombreItemStateChanged);
        cbNombre.addActionListener(this::cbNombreActionPerformed);
        PagosPanelCampos.add(cbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 270, -1));

        jLabel7.setText("Cuit-Cuil:");
        PagosPanelCampos.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 137, -1));
        PagosPanelCampos.add(txtCuil, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 77, 260, -1));

        jLabel51.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel51.setText("Seleccione la empresa a la que se va a cobrar:");
        PagosPanelCampos.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 259, -1));

        jLabel52.setText("Fecha de pago:");
        PagosPanelCampos.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        date.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PagosPanelCampos.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 260, 30));

        jLabel53.setText("Compras totales:");
        PagosPanelCampos.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));
        PagosPanelCampos.add(txtComprasTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 165, 267, -1));

        jLabel54.setText("Metodos de pagos:");
        PagosPanelCampos.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        cbMetodosPagos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMetodosPagos.addActionListener(this::cbMetodosPagosActionPerformed);
        PagosPanelCampos.add(cbMetodosPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 260, -1));

        jLabel55.setText("Cantidad de Pagos:");
        PagosPanelCampos.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));
        PagosPanelCampos.add(txtCuotasPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 270, -1));

        btCalcularCuotas.setText("Calcular Cuotas");
        btCalcularCuotas.addActionListener(this::btCalcularCuotasActionPerformed);
        PagosPanelCampos.add(btCalcularCuotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 210, 20));

        jButton1.setText("Un solo pago");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        PagosPanelCampos.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 170, -1));

        PagosPanel.add(PagosPanelCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 490, 350));

        PanelCalculo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setText("Subtotal:");
        PanelCalculo.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setText("Cada cuota se cobrará:");
        PanelCalculo.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setText("Intereses:");
        PanelCalculo.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        txtUnidadCuota.addActionListener(this::txtUnidadCuotaActionPerformed);
        PanelCalculo.add(txtUnidadCuota, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 130, 20));
        PanelCalculo.add(txtInteres, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 130, 20));
        PanelCalculo.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 130, 20));

        btCalcular.setText("Volver a calcular");
        btCalcular.addActionListener(this::btCalcularActionPerformed);
        PanelCalculo.add(btCalcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 250, 20));

        PagosPanel.add(PanelCalculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 360, 200));

        PanelBotones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAContinuar4.setBackground(new java.awt.Color(22, 22, 22));
        btnAContinuar4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAContinuar4.setForeground(new java.awt.Color(255, 255, 255));
        btnAContinuar4.setText("<Registrar pago>");
        btnAContinuar4.setBorderPainted(false);
        btnAContinuar4.addActionListener(this::btnAContinuar4ActionPerformed);
        PanelBotones.add(btnAContinuar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 230, 40));

        btnABuscar3.setBackground(new java.awt.Color(22, 22, 22));
        btnABuscar3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnABuscar3.setForeground(new java.awt.Color(255, 255, 255));
        btnABuscar3.setText("<Buscar Cliente>");
        btnABuscar3.setBorderPainted(false);
        btnABuscar3.addActionListener(this::btnABuscar3ActionPerformed);
        PanelBotones.add(btnABuscar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 180, 40));

        jLabel50.setText("Monto total:");
        PanelBotones.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 25, -1, -1));
        PanelBotones.add(txtMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(621, 22, 247, -1));

        cbDescuento.setText("Aplicar Descuento");
        cbDescuento.addActionListener(this::cbDescuentoActionPerformed);
        PanelBotones.add(cbDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 70, 132, -1));

        btImprimir.setText("Imprimir");
        btImprimir.addActionListener(this::btImprimirActionPerformed);
        PanelBotones.add(btImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(709, 69, 159, -1));

        PagosPanel.add(PanelBotones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 960, 110));

        administracionPanel.add(PagosPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 980, 600));

        verDetallePanel.setBackground(new java.awt.Color(255, 255, 255));
        verDetallePanel.setMaximumSize(new java.awt.Dimension(980, 738));
        verDetallePanel.setMinimumSize(new java.awt.Dimension(980, 738));
        verDetallePanel.setPreferredSize(new java.awt.Dimension(980, 738));
        verDetallePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSaldo1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        txtSaldo1.setText("Saldo total: $");
        verDetallePanel.add(txtSaldo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 640, -1, -1));

        txtSaldoT.setBackground(new java.awt.Color(255, 255, 255));
        txtSaldoT.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        txtSaldoT.setForeground(new java.awt.Color(0, 0, 238));
        txtSaldoT.setText("<saldo>");
        verDetallePanel.add(txtSaldoT, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 633, 710, 30));

        txtNomCC.setBackground(new java.awt.Color(255, 255, 255));
        txtNomCC.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        txtNomCC.setForeground(new java.awt.Color(0, 0, 238));
        txtNomCC.setText("<NombreCC>");
        verDetallePanel.add(txtNomCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 540, -1));

        lblCC.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        lblCC.setText("Cuenta Corriente:");
        verDetallePanel.add(lblCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        tarjetaDetalle.setBackground(new java.awt.Color(219, 246, 255));
        tarjetaDetalle.setMaximumSize(new java.awt.Dimension(100, 100));
        tarjetaDetalle.setMinimumSize(new java.awt.Dimension(100, 100));
        tarjetaDetalle.setName(""); // NOI18N
        tarjetaDetalle.setPreferredSize(new java.awt.Dimension(100, 100));
        tarjetaDetalle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        ScrollDetalle.setViewportView(tarjetaDetalle);

        verDetallePanel.add(ScrollDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 890, 460));

        BtnDetalleCC.setBackground(new java.awt.Color(22, 22, 22));

        btnAVolver.setBackground(new java.awt.Color(22, 22, 22));
        btnAVolver.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnAVolver.setText("<Volver>");
        btnAVolver.setBorderPainted(false);
        btnAVolver.addActionListener(this::btnAVolverActionPerformed);

        btnAExportar.setBackground(new java.awt.Color(22, 22, 22));
        btnAExportar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAExportar.setForeground(new java.awt.Color(255, 255, 255));
        btnAExportar.setText("<Exportar>");
        btnAExportar.setToolTipText("");
        btnAExportar.setActionCommand("<Volver>");
        btnAExportar.setBorderPainted(false);
        btnAExportar.addActionListener(this::btnAExportarbtnExportarCliente);

        btnACobrar.setBackground(new java.awt.Color(22, 22, 22));
        btnACobrar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnACobrar.setForeground(new java.awt.Color(255, 255, 255));
        btnACobrar.setText("<Proceder al Cobro>");
        btnACobrar.setToolTipText("");
        btnACobrar.setActionCommand("<Volver>");
        btnACobrar.setBorderPainted(false);
        btnACobrar.addActionListener(this::btnACobrarActionPerformed);

        javax.swing.GroupLayout BtnDetalleCCLayout = new javax.swing.GroupLayout(BtnDetalleCC);
        BtnDetalleCC.setLayout(BtnDetalleCCLayout);
        BtnDetalleCCLayout.setHorizontalGroup(
            BtnDetalleCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtnDetalleCCLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btnAVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156)
                .addComponent(btnACobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(btnAExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        BtnDetalleCCLayout.setVerticalGroup(
            BtnDetalleCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnDetalleCCLayout.createSequentialGroup()
                .addGroup(BtnDetalleCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAVolver)
                    .addComponent(btnAExportar)
                    .addComponent(btnACobrar))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        verDetallePanel.add(BtnDetalleCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 980, 40));

        administracionPanel.add(verDetallePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 738));

        getContentPane().add(administracionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 976, 738));

        ventasPanel.setBackground(new java.awt.Color(255, 255, 255));
        ventasPanel.setMaximumSize(new java.awt.Dimension(980, 740));
        ventasPanel.setMinimumSize(new java.awt.Dimension(980, 740));
        ventasPanel.setPreferredSize(new java.awt.Dimension(980, 740));
        ventasPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navbarVNT.setBackground(new java.awt.Color(225, 250, 255));
        navbarVNT.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblimg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NAVBAR.png"))); // NOI18N
        navbarVNT.add(lblimg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnInformes1.setBackground(new java.awt.Color(204, 255, 255));
        btnInformes1.setForeground(new java.awt.Color(0, 51, 102));
        btnInformes1.setText("Informes");
        btnInformes1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInformes1.setBorderPainted(false);
        btnInformes1.setContentAreaFilled(false);
        navbarVNT.add(btnInformes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 90, 50));

        btnCaja1.setBackground(new java.awt.Color(204, 255, 255));
        btnCaja1.setForeground(new java.awt.Color(0, 51, 102));
        btnCaja1.setText("Caja");
        btnCaja1.setBorderPainted(false);
        btnCaja1.setContentAreaFilled(false);
        navbarVNT.add(btnCaja1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 100, 50));

        btnProveedores1.setBackground(new java.awt.Color(204, 255, 255));
        btnProveedores1.setForeground(new java.awt.Color(0, 51, 102));
        btnProveedores1.setText("Proveedores");
        btnProveedores1.setBorderPainted(false);
        btnProveedores1.setContentAreaFilled(false);
        navbarVNT.add(btnProveedores1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 120, 50));

        btnAdministracion1.setBackground(new java.awt.Color(20, 52, 83));
        btnAdministracion1.setForeground(new java.awt.Color(0, 51, 102));
        btnAdministracion1.setText("Administración");
        btnAdministracion1.setBorderPainted(false);
        btnAdministracion1.setContentAreaFilled(false);
        btnAdministracion1.addActionListener(this::btnAdministracion1ActionPerformed);
        navbarVNT.add(btnAdministracion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 120, 50));

        btnVentas1.setBackground(new java.awt.Color(20, 52, 83));
        btnVentas1.setForeground(new java.awt.Color(255, 255, 255));
        btnVentas1.setText("Ventas");
        btnVentas1.setBorderPainted(false);
        btnVentas1.addActionListener(this::btnVentas1ActionPerformed);
        navbarVNT.add(btnVentas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 90, 50));

        btnEstadisticas1.setBackground(new java.awt.Color(204, 255, 255));
        btnEstadisticas1.setForeground(new java.awt.Color(0, 51, 102));
        btnEstadisticas1.setText("Estadísticas");
        btnEstadisticas1.setBorderPainted(false);
        btnEstadisticas1.setContentAreaFilled(false);
        btnEstadisticas1.addActionListener(this::btnEstadisticas1ActionPerformed);
        navbarVNT.add(btnEstadisticas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 120, 50));

        btnProductos1.setBackground(new java.awt.Color(204, 255, 255));
        btnProductos1.setForeground(new java.awt.Color(0, 51, 102));
        btnProductos1.setText("Productos");
        btnProductos1.setBorderPainted(false);
        btnProductos1.setContentAreaFilled(false);
        btnProductos1.addActionListener(this::btnProductos1ActionPerformed);
        navbarVNT.add(btnProductos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 100, 50));

        ventasPanel.add(navbarVNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, -1));

        detPanel.setBackground(new java.awt.Color(255, 255, 255));
        detPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.darkGray), "Datos del producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        detPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtStock.setEditable(false);
        txtStock.setBackground(new java.awt.Color(255, 255, 255));
        txtStock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtStock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStock.setText("Stock ");
        txtStock.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        detPanel.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 120, 50));

        txtPre1.setEditable(false);
        txtPre1.setBackground(new java.awt.Color(255, 255, 255));
        txtPre1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPre1.setText("Precio Unitario");
        txtPre1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        detPanel.add(txtPre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, 50));

        txtPre2.setEditable(false);
        txtPre2.setBackground(new java.awt.Color(255, 255, 255));
        txtPre2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPre2.setForeground(new java.awt.Color(102, 102, 102));
        txtPre2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPre2.setText("Precio Oferta");
        txtPre2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPre2.addActionListener(this::txtPre2ActionPerformed);
        detPanel.add(txtPre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, 50));

        jLabel31.setBackground(new java.awt.Color(20, 52, 83));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Stock Actual");
        jLabel31.setOpaque(true);
        detPanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 120, 40));

        txtIVA.setEditable(false);
        txtIVA.setBackground(new java.awt.Color(255, 255, 255));
        txtIVA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtIVA.setForeground(new java.awt.Color(102, 102, 102));
        txtIVA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIVA.setText("IVA");
        txtIVA.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        detPanel.add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 120, 50));

        jLabel57.setBackground(new java.awt.Color(34, 153, 200));
        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("IVA");
        jLabel57.setOpaque(true);
        detPanel.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 120, 40));

        jLabel59.setBackground(new java.awt.Color(20, 52, 83));
        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("Precio Unitario");
        jLabel59.setOpaque(true);
        detPanel.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, 40));

        jLabel60.setBackground(new java.awt.Color(34, 153, 200));
        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Precio Oferta");
        jLabel60.setOpaque(true);
        detPanel.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 120, 40));

        ventasPanel.add(detPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 290, 300));

        listaPanel.setBackground(new java.awt.Color(255, 255, 255));
        listaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(51, 51, 51)), " Lista de productos ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        listaPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Cantidad", "P.Unitario", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVenta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaVenta.setRowHeight(25);
        tablaVenta.setShowHorizontalLines(true);
        jScrollPane2.setViewportView(tablaVenta);

        listaPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 640, 420));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel32.setText("Total:");
        listaPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 510, -1, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setText("Descuento:");
        listaPanel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 480, -1, -1));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listaPanel.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 510, 170, 20));

        ventasPanel.add(listaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 660, 470));

        BtnsVNT1.setBackground(new java.awt.Color(22, 22, 22));

        EliminarItemV.setBackground(new java.awt.Color(22, 22, 22));
        EliminarItemV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        EliminarItemV.setForeground(new java.awt.Color(255, 255, 255));
        EliminarItemV.setText("<Eliminar item>");
        EliminarItemV.setBorderPainted(false);
        EliminarItemV.addActionListener(this::EliminarItemVActionPerformed);

        DatosCobroVenta.setBackground(new java.awt.Color(22, 22, 22));
        DatosCobroVenta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DatosCobroVenta.setForeground(new java.awt.Color(255, 255, 255));
        DatosCobroVenta.setText("<Datos del cobro>");
        DatosCobroVenta.setBorderPainted(false);
        DatosCobroVenta.addActionListener(this::DatosCobroVentaActionPerformed);

        RehacerVenta.setBackground(new java.awt.Color(22, 22, 22));
        RehacerVenta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        RehacerVenta.setForeground(new java.awt.Color(255, 255, 255));
        RehacerVenta.setText("<Rehacer>");
        RehacerVenta.setBorderPainted(false);
        RehacerVenta.addActionListener(this::RehacerVentaActionPerformed);

        ConfirmarVenta.setBackground(new java.awt.Color(22, 22, 22));
        ConfirmarVenta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ConfirmarVenta.setForeground(new java.awt.Color(255, 255, 255));
        ConfirmarVenta.setText("<Confirmar venta>");
        ConfirmarVenta.setBorderPainted(false);
        ConfirmarVenta.addActionListener(this::ConfirmarVentaActionPerformed);

        javax.swing.GroupLayout BtnsVNT1Layout = new javax.swing.GroupLayout(BtnsVNT1);
        BtnsVNT1.setLayout(BtnsVNT1Layout);
        BtnsVNT1Layout.setHorizontalGroup(
            BtnsVNT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnsVNT1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(DatosCobroVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(EliminarItemV, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(RehacerVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(ConfirmarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        BtnsVNT1Layout.setVerticalGroup(
            BtnsVNT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnsVNT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(EliminarItemV)
                .addComponent(DatosCobroVenta)
                .addComponent(RehacerVenta)
                .addComponent(ConfirmarVenta))
        );

        ventasPanel.add(BtnsVNT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 980, 40));

        jPanel4.setBackground(new java.awt.Color(20, 52, 83));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFact.setFont(new java.awt.Font("Serif", 0, 60)); // NOI18N
        lblFact.setForeground(new java.awt.Color(255, 255, 255));
        lblFact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFact.setText("X");
        jPanel4.add(lblFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 80));

        ventasPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 90, 80));

        jPanel3.setBackground(new java.awt.Color(20, 52, 83));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        ventasPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 30));

        buscarPanel.setBackground(new java.awt.Color(255, 255, 255));
        buscarPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray), "Buscar Producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        buscarPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDescV.setEditable(false);
        txtDescV.setBackground(new java.awt.Color(255, 255, 255));
        txtDescV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDescV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Descripción", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        buscarPanel.add(txtDescV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 220, 60));

        BuscarDescV.setBackground(new java.awt.Color(219, 229, 253));
        BuscarDescV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        BuscarDescV.addActionListener(this::BuscarDescVActionPerformed);
        buscarPanel.add(BuscarDescV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 95, 40, 40));

        RehacerV.setBackground(new java.awt.Color(142, 6, 6));
        RehacerV.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        RehacerV.setForeground(new java.awt.Color(255, 255, 255));
        RehacerV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rehacer-blanco.png"))); // NOI18N
        RehacerV.addActionListener(this::RehacerVActionPerformed);
        buscarPanel.add(RehacerV, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 70, 40));

        BuscarV.setBackground(new java.awt.Color(219, 229, 253));
        BuscarV.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        BuscarV.setForeground(new java.awt.Color(255, 255, 255));
        BuscarV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        BuscarV.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        BuscarV.addActionListener(this::BuscarVActionPerformed);
        buscarPanel.add(BuscarV, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 80, 40));

        AgregarV.setBackground(new java.awt.Color(98, 189, 98));
        AgregarV.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        AgregarV.setForeground(new java.awt.Color(255, 255, 255));
        AgregarV.setText("+");
        AgregarV.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        AgregarV.addActionListener(this::AgregarVActionPerformed);
        buscarPanel.add(AgregarV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 80, 40));

        txtCantV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCantV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cantidad", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        buscarPanel.add(txtCantV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 250, 50));

        txtCodV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCodV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Código", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        buscarPanel.add(txtCodV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 250, 50));

        ventasPanel.add(buscarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 290, 250));

        resumenPanel.setBackground(new java.awt.Color(255, 255, 255));
        resumenPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(51, 51, 51)), "Resumen de la venta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        resumenPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TotalV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        TotalV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalV.setText("$");
        TotalV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "TOTAL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16), new java.awt.Color(20, 52, 83))); // NOI18N
        resumenPanel.add(TotalV, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 140, 80));

        SubtotalV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        SubtotalV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SubtotalV.setText("$");
        SubtotalV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "SUBTOTAL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16), new java.awt.Color(20, 52, 83))); // NOI18N
        resumenPanel.add(SubtotalV, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 140, 80));

        DescuentoV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        DescuentoV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DescuentoV.setText("$");
        DescuentoV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "DESCUENTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16), new java.awt.Color(20, 52, 83))); // NOI18N
        resumenPanel.add(DescuentoV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 140, 80));

        IvaV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        IvaV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IvaV.setText("$");
        IvaV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "IVA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16), new java.awt.Color(20, 52, 83))); // NOI18N
        resumenPanel.add(IvaV, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 140, 80));

        ventasPanel.add(resumenPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 560, 660, 130));

        getContentPane().add(ventasPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 740));

        metodoPago.setBackground(new java.awt.Color(255, 255, 255));
        metodoPago.setMaximumSize(new java.awt.Dimension(980, 740));
        metodoPago.setMinimumSize(new java.awt.Dimension(980, 740));
        metodoPago.setName(""); // NOI18N
        metodoPago.setPreferredSize(new java.awt.Dimension(980, 740));
        metodoPago.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelFact.setBackground(new java.awt.Color(255, 255, 255));
        panelFact.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)), "Detalles del pago", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        panelFact.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbMpVentas.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        cbMpVentas.setForeground(new java.awt.Color(1, 25, 48));
        cbMpVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cbMpVentas.addActionListener(this::cbMpVentasActionPerformed);
        panelFact.add(cbMpVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 260, 30));

        factB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        factB.setText("B");
        factB.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        factB.addActionListener(this::factBActionPerformed);
        panelFact.add(factB, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 230, 70, 30));

        factA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        factA.setText("A");
        factA.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        factA.addActionListener(this::factAActionPerformed);
        panelFact.add(factA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 70, 30));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(1, 25, 48));
        jLabel42.setText("Tipo de factura");
        panelFact.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        jLabel40.setBackground(new java.awt.Color(1, 25, 48));
        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(1, 25, 48));
        jLabel40.setText("Método de pago"); // NOI18N
        panelFact.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, -1, -1));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(1, 25, 48));
        jLabel38.setText("Cuotas");
        panelFact.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, -1, -1));

        labelFact.setFont(new java.awt.Font("Serif", 0, 90)); // NOI18N
        labelFact.setForeground(new java.awt.Color(20, 52, 83));
        labelFact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFact.setText("X");
        labelFact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 52, 83), 2));
        panelFact.add(labelFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 50, 110, 110));

        txtCuotas.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        txtCuotas.setForeground(new java.awt.Color(0, 0, 0));
        txtCuotas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelFact.add(txtCuotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 260, 30));

        factX.setBackground(new java.awt.Color(255, 255, 255));
        factX.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        factX.setForeground(new java.awt.Color(0, 0, 0));
        factX.setText("X");
        factX.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        factX.addActionListener(this::factXActionPerformed);
        panelFact.add(factX, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 70, 30));

        metodoPago.add(panelFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 320, 460));

        panelCli.setBackground(new java.awt.Color(255, 255, 255));
        panelCli.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)), "Información del cliente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        panelCli.setForeground(new java.awt.Color(255, 255, 255));
        panelCli.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbCondIvaMp.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        cbCondIvaMp.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Condición frente al IVA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(20, 52, 83))); // NOI18N
        cbCondIvaMp.addActionListener(this::cbCondIvaMpActionPerformed);
        panelCli.add(cbCondIvaMp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 540, 60));

        AgregarCliMP.setBackground(new java.awt.Color(20, 52, 83));
        AgregarCliMP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AgregarCliMP.setForeground(new java.awt.Color(255, 255, 255));
        AgregarCliMP.setText("+ Agregar");
        AgregarCliMP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        AgregarCliMP.addActionListener(this::AgregarCliMPActionPerformed);
        panelCli.add(AgregarCliMP, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 140, 40));

        BuscarCliMP.setBackground(new java.awt.Color(20, 52, 83));
        BuscarCliMP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BuscarCliMP.setForeground(new java.awt.Color(255, 255, 255));
        BuscarCliMP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        BuscarCliMP.setText("Buscar");
        BuscarCliMP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BuscarCliMP.addActionListener(this::BuscarCliMPActionPerformed);
        panelCli.add(BuscarCliMP, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 140, 40));

        txtDomCliente.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        txtDomCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Domicilio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(20, 52, 83))); // NOI18N
        panelCli.add(txtDomCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 540, -1));

        txtNombreCliente.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        txtNombreCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Nombre/Razón Social", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(20, 52, 83))); // NOI18N
        panelCli.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 350, -1));

        txtCuitCliente.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        txtCuitCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "CUIT/CUIL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(20, 52, 83))); // NOI18N
        panelCli.add(txtCuitCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 350, -1));

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon-mp-blue.png"))); // NOI18N
        panelCli.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 170, 150));

        metodoPago.add(panelCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 600, 460));

        LimpiarMp.setBackground(new java.awt.Color(219, 229, 253));
        LimpiarMp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LimpiarMp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rehacer.png"))); // NOI18N
        LimpiarMp.setText("Limpiar");
        LimpiarMp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        LimpiarMp.addActionListener(this::LimpiarMpActionPerformed);
        metodoPago.add(LimpiarMp, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 660, 140, 50));

        jButton7.setBackground(new java.awt.Color(189, 247, 255));
        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton7.setText("Continuar →");
        jButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton7.addActionListener(this::jButton7ActionPerformed);
        metodoPago.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 660, 140, 50));

        cancelarMP1.setBackground(new java.awt.Color(158, 33, 33));
        cancelarMP1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cancelarMP1.setForeground(new java.awt.Color(255, 255, 255));
        cancelarMP1.setText("← Volver");
        cancelarMP1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cancelarMP1.addActionListener(this::cancelarMP1ActionPerformed);
        metodoPago.add(cancelarMP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 660, 140, 50));

        jPanel8.setBackground(new java.awt.Color(11, 28, 44));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Serif", 0, 48)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("DATOS DEL PAGO");
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, -1, -1));

        metodoPago.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 70));

        getContentPane().add(metodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 740));

        productosPanel.setBackground(new java.awt.Color(204, 255, 255));
        productosPanel.setMaximumSize(new java.awt.Dimension(980, 740));
        productosPanel.setMinimumSize(new java.awt.Dimension(980, 740));
        productosPanel.setPreferredSize(new java.awt.Dimension(980, 740));
        productosPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActualizarP.setBackground(new java.awt.Color(20, 52, 83));
        btnActualizarP.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarP.setText("Actualizar");
        btnActualizarP.setBorderPainted(false);
        btnActualizarP.addActionListener(this::btnActualizarPActionPerformed);
        jPanel9.add(btnActualizarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 0, 100, 50));

        btnListasP.setBackground(new java.awt.Color(20, 52, 83));
        btnListasP.setForeground(new java.awt.Color(255, 255, 255));
        btnListasP.setText("Listas");
        btnListasP.setBorderPainted(false);
        btnListasP.addActionListener(this::btnListasPActionPerformed);
        jPanel9.add(btnListasP, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 0, 100, 50));

        btnGestionarP.setBackground(new java.awt.Color(20, 52, 83));
        btnGestionarP.setForeground(new java.awt.Color(255, 255, 255));
        btnGestionarP.setText("Gestionar");
        btnGestionarP.setBorderPainted(false);
        btnGestionarP.addActionListener(this::btnGestionarPActionPerformed);
        jPanel9.add(btnGestionarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 100, 50));

        productosPanel.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 50));

        lblimg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NAVBAR.png"))); // NOI18N
        productosPanel.add(lblimg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnInformes3.setBackground(new java.awt.Color(204, 255, 255));
        btnInformes3.setForeground(new java.awt.Color(0, 51, 102));
        btnInformes3.setText("Informes");
        btnInformes3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInformes3.setBorderPainted(false);
        btnInformes3.setContentAreaFilled(false);
        productosPanel.add(btnInformes3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 90, 50));

        btnEstadisticas3.setBackground(new java.awt.Color(204, 255, 255));
        btnEstadisticas3.setForeground(new java.awt.Color(0, 51, 102));
        btnEstadisticas3.setText("Estadísticas");
        btnEstadisticas3.setBorderPainted(false);
        btnEstadisticas3.setContentAreaFilled(false);
        btnEstadisticas3.addActionListener(this::btnEstadisticas3ActionPerformed);
        productosPanel.add(btnEstadisticas3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 120, 50));

        btnVentas3.setBackground(new java.awt.Color(204, 255, 255));
        btnVentas3.setForeground(new java.awt.Color(0, 51, 102));
        btnVentas3.setText("Ventas");
        btnVentas3.setBorderPainted(false);
        btnVentas3.addActionListener(this::btnVentas3ActionPerformed);
        productosPanel.add(btnVentas3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 90, 50));

        btnAdministracion3.setBackground(new java.awt.Color(20, 52, 83));
        btnAdministracion3.setForeground(new java.awt.Color(0, 51, 102));
        btnAdministracion3.setText("Administración");
        btnAdministracion3.setBorderPainted(false);
        btnAdministracion3.setContentAreaFilled(false);
        btnAdministracion3.addActionListener(this::btnAdministracion3ActionPerformed);
        productosPanel.add(btnAdministracion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 120, 50));

        btnProductos3.setBackground(new java.awt.Color(20, 52, 83));
        btnProductos3.setForeground(new java.awt.Color(255, 255, 255));
        btnProductos3.setText("Productos");
        btnProductos3.setBorderPainted(false);
        btnProductos3.addActionListener(this::btnProductos3ActionPerformed);
        productosPanel.add(btnProductos3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 100, 50));

        btnProveedores3.setBackground(new java.awt.Color(204, 255, 255));
        btnProveedores3.setForeground(new java.awt.Color(0, 51, 102));
        btnProveedores3.setText("Proveedores");
        btnProveedores3.setBorderPainted(false);
        btnProveedores3.setContentAreaFilled(false);
        productosPanel.add(btnProveedores3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 120, 50));

        btnCaja3.setBackground(new java.awt.Color(204, 255, 255));
        btnCaja3.setForeground(new java.awt.Color(0, 51, 102));
        btnCaja3.setText("Caja");
        btnCaja3.setBorderPainted(false);
        btnCaja3.setContentAreaFilled(false);
        productosPanel.add(btnCaja3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 100, 50));

        InicioProdPanel.setBackground(new java.awt.Color(255, 255, 255));
        InicioProdPanel.setMaximumSize(new java.awt.Dimension(980, 640));
        InicioProdPanel.setMinimumSize(new java.awt.Dimension(980, 640));
        InicioProdPanel.setPreferredSize(new java.awt.Dimension(980, 640));
        InicioProdPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/productos-logo.jpg"))); // NOI18N
        InicioProdPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 420, 320));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(20, 52, 83));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("PRODUCTOS");
        InicioProdPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 340, 50));

        jLabel33.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("los productos de tu negocio");
        InicioProdPanel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 320, 20));

        jLabel34.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Gestiona, consulta y actualiza las características de");
        InicioProdPanel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 430, 20));

        productosPanel.add(InicioProdPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 980, 640));

        AccionGestion.setBackground(new java.awt.Color(255, 255, 255));
        AccionGestion.setMaximumSize(new java.awt.Dimension(980, 640));
        AccionGestion.setMinimumSize(new java.awt.Dimension(980, 640));
        AccionGestion.setPreferredSize(new java.awt.Dimension(980, 640));
        AccionGestion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHeaderAgregar.setBackground(new java.awt.Color(226, 255, 226));
        lblHeaderAgregar.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        lblHeaderAgregar.setForeground(new java.awt.Color(0, 0, 0));
        lblHeaderAgregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHeaderAgregar.setToolTipText("");
        lblHeaderAgregar.setOpaque(true);
        AccionGestion.add(lblHeaderAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 60));

        lblHeaderAgregar1.setBackground(new java.awt.Color(226, 255, 226));
        lblHeaderAgregar1.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        lblHeaderAgregar1.setForeground(new java.awt.Color(0, 0, 0));
        lblHeaderAgregar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHeaderAgregar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        lblHeaderAgregar1.setText(" Agregar nuevo producto");
        lblHeaderAgregar1.setToolTipText("");
        lblHeaderAgregar1.setOpaque(true);
        AccionGestion.add(lblHeaderAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 930, 60));

        DatosProdAPanel.setBackground(new java.awt.Color(255, 255, 255));
        DatosProdAPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(98, 135, 165), new java.awt.Color(0, 59, 108)), "DATOS DEL PRODUCTO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 59, 108))); // NOI18N
        DatosProdAPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(0, 0, 0));
        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Precio Unitario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        DatosProdAPanel.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 450, 60));

        jTextField7.setBackground(new java.awt.Color(255, 255, 255));
        jTextField7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(0, 0, 0));
        jTextField7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Código", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        DatosProdAPanel.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 450, 60));

        jComboBox5.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        DatosProdAPanel.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 450, 60));

        jComboBox6.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "IVA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        DatosProdAPanel.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 450, 60));

        jComboBox7.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Rubro del producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        DatosProdAPanel.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 450, 60));

        jComboBox8.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo de producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        jComboBox8.addActionListener(this::jComboBox8ActionPerformed);
        DatosProdAPanel.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 450, 60));

        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        jScrollPane5.setViewportView(jTextArea1);

        DatosProdAPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 450, 110));

        AccionGestion.add(DatosProdAPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 960, 300));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 59, 108), new java.awt.Color(98, 135, 165)), "DATOS DE STOCK", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 59, 108))); // NOI18N
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(0, 0, 0));
        jTextField5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Stock Mínimo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        jPanel11.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 300, 50));

        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(0, 0, 0));
        jTextField6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Stock Máximo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        jPanel11.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 300, 50));

        jTextField8.setBackground(new java.awt.Color(255, 255, 255));
        jTextField8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(0, 0, 0));
        jTextField8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Stock Actual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        jPanel11.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 300, 50));

        AccionGestion.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 960, 120));

        GVolver.setBackground(new java.awt.Color(242, 76, 76));
        GVolver.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        GVolver.setForeground(new java.awt.Color(255, 255, 255));
        GVolver.setText("Cancelar");
        GVolver.setBorderPainted(false);
        GVolver.addActionListener(this::GVolverActionPerformed);
        AccionGestion.add(GVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 590, 110, -1));

        AGAceptar.setBackground(new java.awt.Color(100, 216, 100));
        AGAceptar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AGAceptar.setForeground(new java.awt.Color(255, 255, 255));
        AGAceptar.setText("Aceptar");
        AGAceptar.setBorderPainted(false);
        AGAceptar.addActionListener(this::AGAceptarActionPerformed);
        AccionGestion.add(AGAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 590, 110, -1));

        lblHeaderModificar1.setBackground(new java.awt.Color(240, 240, 240));
        lblHeaderModificar1.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        lblHeaderModificar1.setForeground(new java.awt.Color(0, 0, 0));
        lblHeaderModificar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHeaderModificar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar.png"))); // NOI18N
        lblHeaderModificar1.setText(" Modificar producto");
        lblHeaderModificar1.setToolTipText("");
        lblHeaderModificar1.setOpaque(true);
        AccionGestion.add(lblHeaderModificar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 930, 60));

        lblHeaderModificar.setBackground(new java.awt.Color(240, 240, 240));
        lblHeaderModificar.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        lblHeaderModificar.setForeground(new java.awt.Color(0, 0, 0));
        lblHeaderModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHeaderModificar.setToolTipText("");
        lblHeaderModificar.setOpaque(true);
        AccionGestion.add(lblHeaderModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 60));

        MGAceptar.setBackground(new java.awt.Color(107, 107, 107));
        MGAceptar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        MGAceptar.setForeground(new java.awt.Color(255, 255, 255));
        MGAceptar.setText("Aceptar");
        MGAceptar.setBorderPainted(false);
        MGAceptar.addActionListener(this::MGAceptarActionPerformed);
        AccionGestion.add(MGAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 590, 110, -1));

        productosPanel.add(AccionGestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, -1, -1));

        GestionProdPanel.setBackground(new java.awt.Color(255, 255, 255));
        GestionProdPanel.setMaximumSize(new java.awt.Dimension(980, 640));
        GestionProdPanel.setMinimumSize(new java.awt.Dimension(980, 640));
        GestionProdPanel.setPreferredSize(new java.awt.Dimension(980, 640));
        GestionProdPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnsGestion.setBackground(new java.awt.Color(255, 255, 255));
        btnsGestion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "  Acciones ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        btnsGestion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(255, 245, 228));
        jButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 51));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar-stock.png"))); // NOI18N
        jButton2.setText("Agregar Stock");
        btnsGestion.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 153, 320, 40));

        jButton5.setBackground(new java.awt.Color(228, 255, 228));
        jButton5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 51));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        jButton5.setText("Nuevo ");
        jButton5.addActionListener(this::jButton5ActionPerformed);
        btnsGestion.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 33, 320, 40));

        jButton8.setBackground(new java.awt.Color(240, 240, 240));
        jButton8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 0, 51));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar.png"))); // NOI18N
        jButton8.setText("Modificar ");
        jButton8.addActionListener(this::jButton8ActionPerformed);
        btnsGestion.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 73, 320, 40));

        jButton9.setBackground(new java.awt.Color(255, 239, 239));
        jButton9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(0, 0, 51));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        jButton9.setText("Eliminar ");
        btnsGestion.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 113, 320, 40));

        GestionProdPanel.add(btnsGestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 340, 200));

        globGestion.setBackground(new java.awt.Color(255, 255, 255));
        globGestion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos del inventario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        globGestion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setBackground(new java.awt.Color(237, 255, 255));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("43");
        jLabel35.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(93, 157, 255), new java.awt.Color(93, 157, 255), null, null), "Proveedores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jLabel35.setOpaque(true);
        globGestion.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 170, 70));

        jLabel36.setBackground(new java.awt.Color(237, 255, 255));
        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("165");
        jLabel36.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(93, 157, 255), new java.awt.Color(93, 157, 255), null, null), "Total de Productos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jLabel36.setOpaque(true);
        globGestion.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 170, 70));

        jLabel39.setBackground(new java.awt.Color(237, 255, 255));
        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("642");
        jLabel39.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(93, 157, 255), new java.awt.Color(93, 157, 255), null, null), "Stock total", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jLabel39.setOpaque(true);
        globGestion.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 170, 70));

        jButton10.setBackground(new java.awt.Color(255, 255, 204));
        jButton10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton10.setForeground(new java.awt.Color(0, 0, 0));
        jButton10.setText("15");
        jButton10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 0), new java.awt.Color(204, 204, 0), new java.awt.Color(204, 204, 0), new java.awt.Color(255, 255, 0)), "Productos bajo stock", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        globGestion.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 160, 80));

        jButton11.setBackground(new java.awt.Color(255, 230, 230));
        jButton11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 0, 0));
        jButton11.setText("3");
        jButton11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 204), new java.awt.Color(153, 0, 0), new java.awt.Color(153, 0, 0), new java.awt.Color(255, 204, 204)), "Productos sin stock", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        globGestion.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 160, 80));

        GestionProdPanel.add(globGestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 590, 200));

        listaGestion.setBackground(new java.awt.Color(255, 255, 255));
        listaGestion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Inventario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        listaGestion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaProductosP.setBackground(new java.awt.Color(255, 255, 255));
        tablaProductosP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaProductosP.setForeground(new java.awt.Color(0, 0, 0));
        tablaProductosP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Descripción", "Rubro", "Tipo", "Stock actual", "Precio", "Proveedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tablaProductosP);

        listaGestion.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 81, 940, 310));

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        listaGestion.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 170, 40));

        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 0, 0));
        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Código", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        listaGestion.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 100, 40));

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        listaGestion.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 160, 50));

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rurbo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        listaGestion.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 150, 50));

        jComboBox3.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tipo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        listaGestion.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 150, 50));

        jComboBox4.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Ascendente", "Descendente" }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Stock", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        listaGestion.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 100, 50));

        jButton12.setBackground(new java.awt.Color(42, 67, 117));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rehacer-blanco.png"))); // NOI18N
        listaGestion.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 40, 50, 40));

        jButton13.setBackground(new java.awt.Color(204, 204, 204));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        listaGestion.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 50, 40));

        GestionProdPanel.add(listaGestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 950, 420));

        productosPanel.add(GestionProdPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, -1, -1));

        getContentPane().add(productosPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 740));

        estadísticaPanel.setBackground(new java.awt.Color(255, 255, 255));
        estadísticaPanel.setMaximumSize(new java.awt.Dimension(980, 740));
        estadísticaPanel.setMinimumSize(new java.awt.Dimension(980, 740));

        lblimg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NAVBAR.png"))); // NOI18N

        btnInformes2.setBackground(new java.awt.Color(204, 255, 255));
        btnInformes2.setForeground(new java.awt.Color(0, 51, 102));
        btnInformes2.setText("Informes");
        btnInformes2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInformes2.setBorderPainted(false);

        btnCaja2.setBackground(new java.awt.Color(204, 255, 255));
        btnCaja2.setForeground(new java.awt.Color(0, 51, 102));
        btnCaja2.setText("Caja");
        btnCaja2.setBorderPainted(false);

        btnProveedores2.setBackground(new java.awt.Color(204, 255, 255));
        btnProveedores2.setForeground(new java.awt.Color(0, 51, 102));
        btnProveedores2.setText("Proveedores");
        btnProveedores2.setBorderPainted(false);

        btnAdministracion2.setBackground(new java.awt.Color(204, 255, 255));
        btnAdministracion2.setForeground(new java.awt.Color(0, 51, 102));
        btnAdministracion2.setText("Administración");
        btnAdministracion2.setBorderPainted(false);
        btnAdministracion2.addActionListener(this::btnAdministracion2ActionPerformed);

        btnVentas2.setBackground(new java.awt.Color(204, 255, 255));
        btnVentas2.setForeground(new java.awt.Color(0, 51, 102));
        btnVentas2.setText("Ventas");
        btnVentas2.setBorderPainted(false);
        btnVentas2.addActionListener(this::btnVentas2ActionPerformed);

        btnEstadisticas2.setBackground(new java.awt.Color(20, 52, 83));
        btnEstadisticas2.setForeground(new java.awt.Color(255, 255, 255));
        btnEstadisticas2.setText("Estadísticas");
        btnEstadisticas2.setBorderPainted(false);
        btnEstadisticas2.addActionListener(this::btnEstadisticas2ActionPerformed);

        btnProductos2.setBackground(new java.awt.Color(204, 255, 255));
        btnProductos2.setForeground(new java.awt.Color(0, 51, 102));
        btnProductos2.setText("Productos");
        btnProductos2.setBorderPainted(false);
        btnProductos2.addActionListener(this::btnProductos2ActionPerformed);

        panelContenidoEstadisticas.setBackground(new java.awt.Color(0, 0, 0));
        panelContenidoEstadisticas.setPreferredSize(new java.awt.Dimension(980, 50));

        btnInformeVentas.setBackground(new java.awt.Color(38, 90, 142));
        btnInformeVentas.setForeground(new java.awt.Color(255, 255, 255));
        btnInformeVentas.setText("Ventas");
        btnInformeVentas.setBorderPainted(false);
        btnInformeVentas.addActionListener(this::btnInformeVentasActionPerformed);

        btnInformeClientes.setBackground(new java.awt.Color(20, 52, 83));
        btnInformeClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnInformeClientes.setText("Clientes");
        btnInformeClientes.addActionListener(this::btnInformeClientesActionPerformed);

        btnInformeStock.setBackground(new java.awt.Color(20, 52, 83));
        btnInformeStock.setForeground(new java.awt.Color(255, 255, 255));
        btnInformeStock.setText("Stock");
        btnInformeStock.addActionListener(this::btnInformeStockActionPerformed);

        javax.swing.GroupLayout panelContenidoEstadisticasLayout = new javax.swing.GroupLayout(panelContenidoEstadisticas);
        panelContenidoEstadisticas.setLayout(panelContenidoEstadisticasLayout);
        panelContenidoEstadisticasLayout.setHorizontalGroup(
            panelContenidoEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenidoEstadisticasLayout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(btnInformeVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInformeClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInformeStock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(433, Short.MAX_VALUE))
        );
        panelContenidoEstadisticasLayout.setVerticalGroup(
            panelContenidoEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnInformeVentas, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(btnInformeClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnInformeStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelInformePDF.setBackground(new java.awt.Color(255, 255, 255));

        lblTituloInformeVentas.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloInformeVentas.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 40)); // NOI18N
        lblTituloInformeVentas.setForeground(new java.awt.Color(0, 66, 115));
        lblTituloInformeVentas.setText("Estadísticas de Ventas");
        lblTituloInformeVentas.setToolTipText("");
        lblTituloInformeVentas.setMaximumSize(new java.awt.Dimension(554, 54));
        lblTituloInformeVentas.setMinimumSize(new java.awt.Dimension(554, 54));
        lblTituloInformeVentas.setPreferredSize(new java.awt.Dimension(554, 54));

        lblCantVentas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCantVentas.setText("TOTAL DE VENTAS");

        lblCantidadVentas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCantidadVentas.setText("0");

        javax.swing.GroupLayout cantidadPanelLayout = new javax.swing.GroupLayout(cantidadPanel);
        cantidadPanel.setLayout(cantidadPanelLayout);
        cantidadPanelLayout.setHorizontalGroup(
            cantidadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cantidadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cantidadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCantVentas)
                    .addGroup(cantidadPanelLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblCantidadVentas)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cantidadPanelLayout.setVerticalGroup(
            cantidadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cantidadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCantVentas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCantidadVentas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblIngTotales.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblIngTotales.setText("INGRESOS TOTALES");

        lblIngresosTotales.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblIngresosTotales.setText("$0,00");

        javax.swing.GroupLayout ingresosPanelLayout = new javax.swing.GroupLayout(ingresosPanel);
        ingresosPanel.setLayout(ingresosPanelLayout);
        ingresosPanelLayout.setHorizontalGroup(
            ingresosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ingresosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ingresosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIngTotales)
                    .addComponent(lblIngresosTotales))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ingresosPanelLayout.setVerticalGroup(
            ingresosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ingresosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIngTotales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIngresosTotales)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTicketProm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTicketProm.setText("TICKET PROMEDIO");

        lblTicketPromedio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTicketPromedio.setText("$0,00");

        javax.swing.GroupLayout promedioPanelLayout = new javax.swing.GroupLayout(promedioPanel);
        promedioPanel.setLayout(promedioPanelLayout);
        promedioPanelLayout.setHorizontalGroup(
            promedioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(promedioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(promedioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTicketProm)
                    .addComponent(lblTicketPromedio))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        promedioPanelLayout.setVerticalGroup(
            promedioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(promedioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTicketProm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTicketPromedio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        evolucionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Evolución de ventas"));

        javax.swing.GroupLayout panelGraficoVentasLayout = new javax.swing.GroupLayout(panelGraficoVentas);
        panelGraficoVentas.setLayout(panelGraficoVentasLayout);
        panelGraficoVentasLayout.setHorizontalGroup(
            panelGraficoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );
        panelGraficoVentasLayout.setVerticalGroup(
            panelGraficoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout evolucionPanelLayout = new javax.swing.GroupLayout(evolucionPanel);
        evolucionPanel.setLayout(evolucionPanelLayout);
        evolucionPanelLayout.setHorizontalGroup(
            evolucionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(evolucionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGraficoVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        evolucionPanelLayout.setVerticalGroup(
            evolucionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(evolucionPanelLayout.createSequentialGroup()
                .addComponent(panelGraficoVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        metodosPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Métodos de pago"));

        javax.swing.GroupLayout panelGraficoMetodosLayout = new javax.swing.GroupLayout(panelGraficoMetodos);
        panelGraficoMetodos.setLayout(panelGraficoMetodosLayout);
        panelGraficoMetodosLayout.setHorizontalGroup(
            panelGraficoMetodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );
        panelGraficoMetodosLayout.setVerticalGroup(
            panelGraficoMetodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout metodosPanelLayout = new javax.swing.GroupLayout(metodosPanel);
        metodosPanel.setLayout(metodosPanelLayout);
        metodosPanelLayout.setHorizontalGroup(
            metodosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(metodosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGraficoMetodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        metodosPanelLayout.setVerticalGroup(
            metodosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(metodosPanelLayout.createSequentialGroup()
                .addComponent(panelGraficoMetodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblUltimaAct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUltimaAct.setText("Última actualización:");

        lblUltimaActualizacion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUltimaActualizacion.setText("--");

        javax.swing.GroupLayout actPanelLayout = new javax.swing.GroupLayout(actPanel);
        actPanel.setLayout(actPanelLayout);
        actPanelLayout.setHorizontalGroup(
            actPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUltimaAct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUltimaActualizacion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        actPanelLayout.setVerticalGroup(
            actPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUltimaAct)
                    .addComponent(lblUltimaActualizacion))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnExportarInformePDF.setText("Exportar informe PDF");
        btnExportarInformePDF.addActionListener(this::btnExportarInformePDFActionPerformed);

        javax.swing.GroupLayout panelInformePDFLayout = new javax.swing.GroupLayout(panelInformePDF);
        panelInformePDF.setLayout(panelInformePDFLayout);
        panelInformePDFLayout.setHorizontalGroup(
            panelInformePDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformePDFLayout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(lblTituloInformeVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformePDFLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(panelInformePDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformePDFLayout.createSequentialGroup()
                        .addComponent(cantidadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127)
                        .addComponent(ingresosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132)
                        .addComponent(promedioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(149, 149, 149))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformePDFLayout.createSequentialGroup()
                        .addComponent(evolucionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(panelInformePDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(actPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metodosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExportarInformePDF, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))))
        );
        panelInformePDFLayout.setVerticalGroup(
            panelInformePDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformePDFLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTituloInformeVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelInformePDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ingresosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(promedioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(panelInformePDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(evolucionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelInformePDFLayout.createSequentialGroup()
                        .addComponent(metodosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(actPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExportarInformePDF, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        panelInformeClientes.setBackground(new java.awt.Color(255, 255, 255));
        panelInformeClientes.setPreferredSize(new java.awt.Dimension(980, 740));

        lblTituloInformeVentas1.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloInformeVentas1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 40)); // NOI18N
        lblTituloInformeVentas1.setForeground(new java.awt.Color(0, 66, 115));
        lblTituloInformeVentas1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloInformeVentas1.setText("Estadísticas de Clientes");
        lblTituloInformeVentas1.setToolTipText("");
        lblTituloInformeVentas1.setMaximumSize(new java.awt.Dimension(554, 54));
        lblTituloInformeVentas1.setMinimumSize(new java.awt.Dimension(554, 54));
        lblTituloInformeVentas1.setPreferredSize(new java.awt.Dimension(554, 54));

        lblCantClientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCantClientes.setText("TOTAL DE CLIENTES");

        lblCantidadClientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCantidadClientes.setText("0");

        javax.swing.GroupLayout totClientesPanelLayout = new javax.swing.GroupLayout(totClientesPanel);
        totClientesPanel.setLayout(totClientesPanelLayout);
        totClientesPanelLayout.setHorizontalGroup(
            totClientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totClientesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(totClientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCantClientes)
                    .addGroup(totClientesPanelLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblCantidadClientes)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        totClientesPanelLayout.setVerticalGroup(
            totClientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totClientesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCantClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCantidadClientes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCantEmpresas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCantEmpresas.setText("EMPRESAS");

        lblCantidadEmpresas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCantidadEmpresas.setText("0");

        javax.swing.GroupLayout totEmpresasPanelLayout = new javax.swing.GroupLayout(totEmpresasPanel);
        totEmpresasPanel.setLayout(totEmpresasPanelLayout);
        totEmpresasPanelLayout.setHorizontalGroup(
            totEmpresasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totEmpresasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(totEmpresasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCantEmpresas)
                    .addGroup(totEmpresasPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblCantidadEmpresas)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        totEmpresasPanelLayout.setVerticalGroup(
            totEmpresasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totEmpresasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCantEmpresas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCantidadEmpresas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCantPersonas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCantPersonas.setText("PERSONAS");

        lblCantidadPersonas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCantidadPersonas.setText("0");

        javax.swing.GroupLayout totPersonasPanelLayout = new javax.swing.GroupLayout(totPersonasPanel);
        totPersonasPanel.setLayout(totPersonasPanelLayout);
        totPersonasPanelLayout.setHorizontalGroup(
            totPersonasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totPersonasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(totPersonasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCantPersonas)
                    .addGroup(totPersonasPanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblCantidadPersonas)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        totPersonasPanelLayout.setVerticalGroup(
            totPersonasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totPersonasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCantPersonas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCantidadPersonas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblUltimaAct1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUltimaAct1.setText("Última actualización:");

        lblUltimaActualizacion1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUltimaActualizacion1.setText("--");

        javax.swing.GroupLayout actPanel1Layout = new javax.swing.GroupLayout(actPanel1);
        actPanel1.setLayout(actPanel1Layout);
        actPanel1Layout.setHorizontalGroup(
            actPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUltimaAct1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUltimaActualizacion1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        actPanel1Layout.setVerticalGroup(
            actPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUltimaAct1)
                    .addComponent(lblUltimaActualizacion1))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tipoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Clientes por Tipo"));

        javax.swing.GroupLayout panelGraficoClientesTipoLayout = new javax.swing.GroupLayout(panelGraficoClientesTipo);
        panelGraficoClientesTipo.setLayout(panelGraficoClientesTipoLayout);
        panelGraficoClientesTipoLayout.setHorizontalGroup(
            panelGraficoClientesTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );
        panelGraficoClientesTipoLayout.setVerticalGroup(
            panelGraficoClientesTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 182, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout tipoPanelLayout = new javax.swing.GroupLayout(tipoPanel);
        tipoPanel.setLayout(tipoPanelLayout);
        tipoPanelLayout.setHorizontalGroup(
            tipoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tipoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGraficoClientesTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tipoPanelLayout.setVerticalGroup(
            tipoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tipoPanelLayout.createSequentialGroup()
                .addComponent(panelGraficoClientesTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        IVAPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Clientes por IVA"));

        javax.swing.GroupLayout panelGraficoClientesIVALayout = new javax.swing.GroupLayout(panelGraficoClientesIVA);
        panelGraficoClientesIVA.setLayout(panelGraficoClientesIVALayout);
        panelGraficoClientesIVALayout.setHorizontalGroup(
            panelGraficoClientesIVALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        panelGraficoClientesIVALayout.setVerticalGroup(
            panelGraficoClientesIVALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout IVAPanelLayout = new javax.swing.GroupLayout(IVAPanel);
        IVAPanel.setLayout(IVAPanelLayout);
        IVAPanelLayout.setHorizontalGroup(
            IVAPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IVAPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGraficoClientesIVA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        IVAPanelLayout.setVerticalGroup(
            IVAPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IVAPanelLayout.createSequentialGroup()
                .addComponent(panelGraficoClientesIVA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnExportarInformePDF1.setText("Exportar informe PDF");
        btnExportarInformePDF1.addActionListener(this::btnExportarInformePDF1ActionPerformed);

        javax.swing.GroupLayout panelInformeClientesLayout = new javax.swing.GroupLayout(panelInformeClientes);
        panelInformeClientes.setLayout(panelInformeClientesLayout);
        panelInformeClientesLayout.setHorizontalGroup(
            panelInformeClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformeClientesLayout.createSequentialGroup()
                .addGroup(panelInformeClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformeClientesLayout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(lblTituloInformeVentas1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInformeClientesLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(panelInformeClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInformeClientesLayout.createSequentialGroup()
                                .addComponent(btnExportarInformePDF1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(actPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInformeClientesLayout.createSequentialGroup()
                                .addComponent(tipoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(IVAPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformeClientesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(totClientesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130)
                .addComponent(totEmpresasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130)
                .addComponent(totPersonasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194))
        );
        panelInformeClientesLayout.setVerticalGroup(
            panelInformeClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformeClientesLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblTituloInformeVentas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInformeClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totEmpresasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totClientesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totPersonasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(panelInformeClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IVAPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(panelInformeClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExportarInformePDF1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout panelInformeStockLayout = new javax.swing.GroupLayout(panelInformeStock);
        panelInformeStock.setLayout(panelInformeStockLayout);
        panelInformeStockLayout.setHorizontalGroup(
            panelInformeStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );
        panelInformeStockLayout.setVerticalGroup(
            panelInformeStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout estadísticaPanelLayout = new javax.swing.GroupLayout(estadísticaPanel);
        estadísticaPanel.setLayout(estadísticaPanelLayout);
        estadísticaPanelLayout.setHorizontalGroup(
            estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(estadísticaPanelLayout.createSequentialGroup()
                .addGroup(estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelInformePDF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(estadísticaPanelLayout.createSequentialGroup()
                        .addComponent(lblimg2)
                        .addGap(0, 0, 0)
                        .addComponent(btnInformes2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnEstadisticas2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnVentas2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnAdministracion2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnProductos2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnProveedores2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnCaja2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelContenidoEstadisticas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
            .addGroup(estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelInformeStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelInformeClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        estadísticaPanelLayout.setVerticalGroup(
            estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(estadísticaPanelLayout.createSequentialGroup()
                .addGroup(estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblimg2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEstadisticas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVentas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdministracion2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProductos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProveedores2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCaja2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInformes2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(panelContenidoEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelInformePDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, estadísticaPanelLayout.createSequentialGroup()
                    .addGap(0, 89, Short.MAX_VALUE)
                    .addComponent(panelInformeStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, estadísticaPanelLayout.createSequentialGroup()
                    .addGap(0, 99, Short.MAX_VALUE)
                    .addComponent(panelInformeClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(estadísticaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Se muestran los paneles que estan en TRUE, los demas poner en FALSE para no mezclar
    private void iniciarPaneles() {
        ventasPanel.setVisible(false);
        ListaPanel.setVisible(false);
        metodoPago.setVisible(false);
        autPers.setVisible(false);
        autEmp.setVisible(true);
        regEmp.setVisible(true);
        BtnsADM.setVisible(true);
        PagosPanel.setVisible(false);
        PagosPanelCampos.setVisible(false);
        PanelCalculo.setVisible(false);
        PanelBotones.setVisible(false);
        estadísticaPanel.setVisible(false);
        productosPanel.setVisible(false);
        iniciarActualizacionEstadisticas();
    }
    //ADMINISTRACION - REGISTRO CUENTA CORRIENTE

    private void btnAEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAEmpresaActionPerformed
        btnAEmpresa.setBackground(new Color(2, 33, 55));
        btnAPersona.setBackground(new Color(0, 66, 115));
        autEmp.setVisible(true);
        autPers.setVisible(false);
        opc = false;

    }//GEN-LAST:event_btnAEmpresaActionPerformed

    private void btnAPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAPersonaActionPerformed
        btnAPersona.setBackground(new Color(2, 33, 55));
        btnAEmpresa.setBackground(new Color(0, 66, 115));
        autPers.setVisible(true);
        autEmp.setVisible(false);
        opc = true;

    }//GEN-LAST:event_btnAPersonaActionPerformed

    public void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Razón Social");
        modelo.addColumn("Teléfono");
        modelo.addColumn("CUIT/CUIL");
        modelo.addColumn("Domicilio");
        modelo.addColumn("Mail");
        modelo.addColumn("Tipo de Persona");

        NCliente nc = new NCliente();

        for (Cliente c : nc.listar()) {

            if ("Cuenta Corriente".equals(c.getTipoCliente())) {
                modelo.addRow(new Object[]{
                    c.getRazonSocial(),
                    c.getTelefono(),
                    c.getCuit(),
                    c.getDomicilio(),
                    c.getMail(),
                    c.getTipoPersona()
                });
            }
        }

        tablaLista.setModel(modelo);
    }

    private boolean verificarCampos() {
        if (txtRS.getText() == null || txtRS.getText().trim().equals("")
                || txtTel.getText() == null || txtTel.getText().trim().equals("")
                || txtCuit.getText() == null || txtCuit.getText().trim().equals("")
                || txtDate.getDate() == null
                || txtDom.getText() == null || txtDom.getText().trim().equals("")
                || txtMail.getText() == null || txtMail.getText().trim().equals("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return false;
        }
        if (!txtTel.getText().matches("\\d+")) {
            javax.swing.JOptionPane.showMessageDialog(this, "El teléfono debe ser numérico");
            return false;
        }
        if (!txtCuit.getText().matches("\\d{11}")) {
            javax.swing.JOptionPane.showMessageDialog(this, "El CUIT debe tener 11 dígitos numéricos");
            return false;
        }
        if (!txtMail.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe ingresar un Mail válido");
            return false;
        }
        if (txtDate.getDate().after(new Date())) {
            javax.swing.JOptionPane.showMessageDialog(this, "La fecha no puede ser futura");
            return false;
        }
        return true;
    }

    //CARGAR CHECKBOXS DE PAGOS
    private void cargarClientesEnCombo() {
        cbNombre.removeAllItems();
        cbNombre.addItem("-- Seleccione un Cliente --");

        Negocio.NCliente nCliente = new Negocio.NCliente();
        java.util.List<Modelos.Cliente> listaTodos = nCliente.listar();

        for (Modelos.Cliente c : listaTodos) {
            if ("Activo".equalsIgnoreCase(c.getEstado()) && "Cuenta Corriente".equalsIgnoreCase(c.getTipoCliente())) {

                cbNombre.addItem(c.getRazonSocial());
            }
        }
    }

    private void cargarClientes() {
        try {
            // 1. Obtener la lista de clientes desde la capa de negocio
            listaClientesGlobal = nCliente.listar();

            // 2. Crear el modelo para el ComboBox de Clientes
            DefaultComboBoxModel<String> modeloClientes = new DefaultComboBoxModel<>();
            modeloClientes.addElement("Seleccione un Cliente..."); // Elemento por defecto

            // 3. Poblar el modelo con la Razón Social o Nombre del cliente
            for (Modelos.Cliente cliente : listaClientesGlobal) {
                modeloClientes.addElement(cliente.getRazonSocial());
            }

            // 4. ASIGNAR AL COMBOBOX CORRECTO (cbNombre)
            cbNombre.setModel(modeloClientes);

        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error al cargar clientes en el combobox", e);
            JOptionPane.showMessageDialog(this, "Error al cargar el listado de clientes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarMetodosPago() {
        try {
            // CAMBIAR AQUÍ: de nMetodoPago.listar() a nMetodoPago.GetList()
            listaMetodosPagoGlobal = nMetodoPago.GetList();

            DefaultComboBoxModel<String> modeloMetodos = new DefaultComboBoxModel<>();
            modeloMetodos.addElement("Seleccione un método...");

            for (Modelos.MetodoPago mp : listaMetodosPagoGlobal) {
                modeloMetodos.addElement(mp.getDescripcion());
            }

            cbMetodosPagos.setModel(modeloMetodos);

        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error al cargar métodos de pago en el combobox", e);
            JOptionPane.showMessageDialog(this, "Error al cargar los métodos de pago.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void btnAContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAContinuarActionPerformed
        try {
            if (verificarCampos()) {
                String razonSocial = txtRS.getText();
                String telefono = txtTel.getText();
                String cuit = txtCuit.getText();
                Date fecha = txtDate.getDate();
                String domicilio = txtDom.getText();
                String mail = txtMail.getText();
                String tipoCliente = "Cuenta Corriente";
                String estado = "Activo";
                String tipoPersona;
                if (!opc) {
                    tipoPersona = "Empresa";
                } else {
                    tipoPersona = "Persona";
                }
                CondIVA cond = (CondIVA) cbCondIva.getSelectedItem();
                int idCondIva = cond.getIdCondIva();

                Cliente cliente = new Cliente(
                        razonSocial,
                        telefono,
                        cuit,
                        fecha,
                        domicilio,
                        mail,
                        tipoCliente,
                        estado,
                        tipoPersona,
                        idCondIva
                );

                NCliente nuevaCuenta = new NCliente();
                nuevaCuenta.create(cliente);
                javax.swing.JOptionPane.showMessageDialog(this, "Registrado con éxito!");
                txtRS.setText("");
                txtTel.setText("");
                txtDom.setText("");
                txtCuit.setText("");
                txtMail.setText("");
                txtDate.setDate(null);

            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnAContinuarActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        clickProductos();

    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnABuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnABuscarActionPerformed
        try {
            String cuit = txtCuit.getText().trim();
            NCliente negocio = new NCliente();
            Cliente c = negocio.select(cuit);

            if (c != null) {
                actual = c;
                txtRS.setText(c.getRazonSocial());
                txtTel.setText(c.getTelefono());
                txtCuit.setText(c.getCuit());
                txtDom.setText(c.getDomicilio());
                txtMail.setText(c.getMail());
                String tipoPersona = c.getTipoPersona();
                if (tipoPersona.equals("Persona")) {
                    btnAPersonaActionPerformed(null);
                } else {
                    btnAEmpresaActionPerformed(null);
                }
                txtDate.setDate(c.getfchaIngreso());
                int idCondIva = c.getIdCondIva();
                for (int i = 0; i < cbCondIva.getItemCount(); i++) {

                    CondIVA cond = cbCondIva.getItemAt(i);

                    if (cond.getIdCondIva() == idCondIva) {
                        cbCondIva.setSelectedIndex(i);
                        break;
                    }
                }

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Cliente no encontrado");
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnABuscarActionPerformed

    private void btnAModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAModificarActionPerformed
        try {

            if (actual == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe buscar la cuenta por CUIT/CUIL");
                return;
            }

            if (!verificarCampos()) {
                return;
            }

            actual.setRazonSocial(txtRS.getText());
            actual.setTelefono(txtTel.getText());
            actual.setCuit(txtCuit.getText());
            actual.setfchaIngreso(txtDate.getDate());
            actual.setDomicilio(txtDom.getText());
            actual.setMail(txtMail.getText());
            actual.setTipoPersona(opc ? "Persona" : "Empresa");
            CondIVA cond = (CondIVA) cbCondIva.getSelectedItem();
            int idCondIva = cond.getIdCondIva();
            actual.setIdCondIva(idCondIva);

            NCliente negocio = new NCliente();
            negocio.update(actual);

            javax.swing.JOptionPane.showMessageDialog(this, "Cuenta modificada correctamente");

            actual = null;
            txtRS.setText("");
            txtTel.setText("");
            txtDom.setText("");
            txtCuit.setText("");
            txtMail.setText("");
            txtDate.setDate(null);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnAModificarActionPerformed

    private void btnAListasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAListasActionPerformed
        cargarTabla();

        PagosPanel.setVisible(false);
        ListaPanel.setVisible(true);
        btnAListas.setBackground(new Color(30, 90, 142));
        btnAClientes.setBackground(new Color(20, 52, 83));
        btnAPagos.setBackground(new Color(20, 52, 83));
        BtnsADM.setVisible(false);
        regEmp.setVisible(false);
        autEmp.setVisible(false);
        autPers.setVisible(false);
        jScrollPane1.setVisible(true);
        verDetallePanel.setVisible(false);
    }//GEN-LAST:event_btnAListasActionPerformed

    private void btnAClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAClientesActionPerformed
        cargarTabla();
        ListaPanel.setVisible(false);
        verDetallePanel.setVisible(false);
        PagosPanel.setVisible(false);
        btnAListas.setBackground(new Color(20, 52, 83));
        btnAClientes.setBackground(new Color(30, 90, 142));
        btnAPagos.setBackground(new Color(20, 52, 83));

        BtnsADM.setVisible(true);
        jScrollPane1.setVisible(false);
        regEmp.setVisible(true);

        if (opc) {
            autPers.setVisible(true);
            autEmp.setVisible(false);
        } else {
            autEmp.setVisible(true);
            autPers.setVisible(false);
        }

        revalidate();
        repaint();
    }//GEN-LAST:event_btnAClientesActionPerformed

    public void cargarCbCondIva() {
        cbCondIva.removeAllItems();
        NCondIVA nCondIVA = new NCondIVA();
        for (CondIVA c : nCondIVA.getLista()) {
            cbCondIva.addItem(c);
        }
    }

    public void cargarCbMpVentas() {
        cbMpVentas.removeAllItems();
        NMetodoPago nmp = new NMetodoPago();
        for (MetodoPago mp : nmp.GetListActivo()) {
            cbMpVentas.addItem(mp);
        }
    }


    private void btnAGestionarCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAGestionarCCActionPerformed
        int fila = tablaLista.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una cuenta corriente");
            return;
        }

        try {

            String cuit = tablaLista.getValueAt(fila, 2).toString();
            DCliente dCliente = new DCliente();
            Cliente c = dCliente.select(cuit);

            if (c != null) {

                txtRS.setText(c.getRazonSocial());
                txtTel.setText(c.getTelefono());
                txtCuit.setText(c.getCuit());
                txtDom.setText(c.getDomicilio());
                txtMail.setText(c.getMail());

                String tipoPersona = c.getTipoPersona();

                if ("Persona".equals(tipoPersona)) {
                    btnAPersonaActionPerformed(null);
                } else {
                    btnAEmpresaActionPerformed(null);
                }

                txtDate.setDate(c.getfchaIngreso());

                int idCondIva = c.getIdCondIva();

                for (int i = 0; i < cbCondIva.getItemCount(); i++) {

                    CondIVA cond = cbCondIva.getItemAt(i);

                    if (cond.getIdCondIva() == idCondIva) {
                        cbCondIva.setSelectedIndex(i);
                        break;
                    }
                }

                ListaPanel.setVisible(false);
                jScrollPane1.setVisible(false);
                regEmp.setVisible(true);

                if ("Empresa".equals(tipoPersona)) {
                    autEmp.setVisible(true);
                    autPers.setVisible(false);
                } else {
                    autEmp.setVisible(false);
                    autPers.setVisible(true);
                }

                BtnsADM.setVisible(true);
                btnAListas.setBackground(new Color(20, 52, 83));
                btnAClientes.setBackground(new Color(30, 90, 142));
                btnAPagos.setBackground(new Color(20, 52, 83));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }//GEN-LAST:event_btnAGestionarCCActionPerformed

    private void btnExportarCliente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarCliente
        String formato = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el formato de exportación:",
                "Exportar listado",
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Excel (.xls)", "PDF (.pdf)"},
                "Excel (.xls)"
        );

        if (formato == null) {
            return;
        }

        if (formato.startsWith("Excel")) {
            exportarExcel();
        } else {
            exportarPDF();
        }
    }//GEN-LAST:event_btnExportarCliente

    private void exportarExcel() {
        try {
            javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
            chooser.setDialogTitle("Guardar listado");
            chooser.setSelectedFile(new java.io.File("listado_cuentas_corrientes.xls"));

            if (chooser.showSaveDialog(this) != javax.swing.JFileChooser.APPROVE_OPTION) {
                return;
            }

            java.io.File archivo = chooser.getSelectedFile();

            if (!archivo.getName().toLowerCase().endsWith(".xls")) {
                archivo = new java.io.File(archivo.getAbsolutePath() + ".xls");
            }

            javax.swing.table.DefaultTableModel modelo
                    = (javax.swing.table.DefaultTableModel) tablaLista.getModel();

            try (java.io.PrintWriter pw = new java.io.PrintWriter(
                    new java.io.OutputStreamWriter(
                            new java.io.FileOutputStream(archivo), "UTF-8"))) {

                pw.println("<html><head><meta charset='UTF-8'></head><body>");
                pw.println("<h2>Listado de Cuentas Corrientes</h2>");
                pw.println("<table border='1'>");

                pw.println("<tr style='font-weight:bold; background-color:#D9EAF7;'>");
                for (int col = 0; col < modelo.getColumnCount(); col++) {
                    pw.println("<td>" + modelo.getColumnName(col) + "</td>");
                }
                pw.println("</tr>");

                for (int fila = 0; fila < modelo.getRowCount(); fila++) {
                    pw.println("<tr>");
                    for (int col = 0; col < modelo.getColumnCount(); col++) {
                        Object valor = modelo.getValueAt(fila, col);
                        String texto = valor != null ? valor.toString() : "";
                        pw.println("<td style='mso-number-format:\"\\@\";'>" + texto + "</td>");
                    }
                    pw.println("</tr>");
                }

                pw.println("</table></body></html>");
            }

            JOptionPane.showMessageDialog(this, "Listado Excel generado correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar Excel: " + e.getMessage());
        }
    }

    private void exportarPDF() {
        try {
            javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
            chooser.setDialogTitle("Guardar listado");
            chooser.setSelectedFile(new java.io.File("listado_cuentas_corrientes.pdf"));

            if (chooser.showSaveDialog(this) != javax.swing.JFileChooser.APPROVE_OPTION) {
                return;
            }

            java.io.File archivo = chooser.getSelectedFile();

            if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
                archivo = new java.io.File(archivo.getAbsolutePath() + ".pdf");
            }

            javax.swing.table.DefaultTableModel modelo
                    = (javax.swing.table.DefaultTableModel) tablaLista.getModel();

            com.itextpdf.text.Document documento
                    = new com.itextpdf.text.Document(com.itextpdf.text.PageSize.A4.rotate());

            com.itextpdf.text.pdf.PdfWriter.getInstance(
                    documento,
                    new java.io.FileOutputStream(archivo)
            );

            documento.open();

            com.itextpdf.text.Font tituloFont
                    = new com.itextpdf.text.Font(
                            com.itextpdf.text.Font.FontFamily.HELVETICA,
                            16,
                            com.itextpdf.text.Font.BOLD
                    );

            com.itextpdf.text.Paragraph titulo
                    = new com.itextpdf.text.Paragraph("Listado de Cuentas Corrientes", tituloFont);

            titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            titulo.setSpacingAfter(15);
            documento.add(titulo);

            com.itextpdf.text.pdf.PdfPTable tablaPDF
                    = new com.itextpdf.text.pdf.PdfPTable(modelo.getColumnCount());

            tablaPDF.setWidthPercentage(100);

            for (int col = 0; col < modelo.getColumnCount(); col++) {
                com.itextpdf.text.pdf.PdfPCell celda
                        = new com.itextpdf.text.pdf.PdfPCell(
                                new com.itextpdf.text.Phrase(modelo.getColumnName(col))
                        );
                celda.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tablaPDF.addCell(celda);
            }

            for (int fila = 0; fila < modelo.getRowCount(); fila++) {
                for (int col = 0; col < modelo.getColumnCount(); col++) {
                    Object valor = modelo.getValueAt(fila, col);
                    String texto = valor != null ? valor.toString() : "";
                    tablaPDF.addCell(new com.itextpdf.text.Phrase(texto));
                }
            }

            documento.add(tablaPDF);
            documento.close();

            JOptionPane.showMessageDialog(this, "Listado PDF generado correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar PDF: " + e.getMessage());
        }
    }


    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        String rs = txtFiltroRS.getText().trim().toLowerCase();
        String cuit = txtFiltroCuit.getText().trim();
        String tipo = cbTipo.getSelectedItem().toString();

        NCliente ncc = new NCliente();
        List<Cliente> lista = ncc.listar();

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Razón Social");
        modelo.addColumn("Teléfono");
        modelo.addColumn("CUIT/CUIL");
        modelo.addColumn("Domicilio");
        modelo.addColumn("Mail");
        modelo.addColumn("Tipo de Persona");

        boolean hayResultados = false;

        for (Cliente c : lista) {
            if (!"Cuenta Corriente".equalsIgnoreCase(c.getTipoCliente())) {
                continue;
            }

            boolean coincide = true;

            if (!rs.isEmpty() && !c.getRazonSocial().toLowerCase().contains(rs)) {
                coincide = false;
            }

            if (!cuit.isEmpty() && !c.getCuit().contains(cuit)) {
                coincide = false;
            }

            if (tipo != null && !tipo.isEmpty() && !c.getTipoPersona().equalsIgnoreCase(tipo)) {
                coincide = false;
            }

            if (coincide) {
                hayResultados = true;

                modelo.addRow(new Object[]{
                    c.getRazonSocial(),
                    c.getTelefono(),
                    c.getCuit(),
                    c.getDomicilio(),
                    c.getMail(),
                    c.getTipoPersona()
                });
            }
        }

        // SIEMPRE setear el modelo (aunque esté vacío)
        tablaLista.setModel(modelo);

        // opcional: avisar si no hay resultados
        if (!hayResultados) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron resultados");
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtFiltroRS.setText("");
        txtFiltroCuit.setText("");
        cbTipo.setSelectedIndex(0);

        cargarTabla();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnABorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnABorrarActionPerformed
        try {
            if (actual == null) {
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Debe buscar la cuenta antes de desactivarla"
                );
                return;
            }

            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro que desea desactivar esta cuenta corriente?",
                    "Confirmar desactivación",
                    javax.swing.JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                NCliente negocio = new NCliente();
                negocio.delete(actual.getIdCliente());
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Cuenta corriente desactivada correctamente"
                );
                actual = null;

                txtRS.setText("");
                txtTel.setText("");
                txtDom.setText("");
                txtCuit.setText("");
                txtMail.setText("");
                txtDate.setDate(null);

                cargarTabla();
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

        }
    }//GEN-LAST:event_btnABorrarActionPerformed

    private void btnAPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAPagosActionPerformed
        navbarADM.setVisible(true);

        btnAListas.setBackground(new Color(20, 52, 83));
        btnAClientes.setBackground(new Color(20, 52, 83));
        btnAPagos.setBackground(new Color(30, 90, 142));

        // Mostrar panel de pagos
        PagosPanel.setVisible(true);
        PagosPanelCampos.setVisible(true);
        PanelCalculo.setVisible(true);
        PanelBotones.setVisible(true);

        // Ocultar los demás
        administracionPanel.setVisible(true);
        ventasPanel.setVisible(false);
        ListaPanel.setVisible(false);
        metodoPago.setVisible(false);
        autPers.setVisible(false);
        autEmp.setVisible(false);
        regEmp.setVisible(false);
        BtnsADM.setVisible(false);

        // Forzar actualización visual
        PagosPanel.revalidate();
        PagosPanel.repaint();

        getContentPane().revalidate();
        getContentPane().repaint();
    }//GEN-LAST:event_btnAPagosActionPerformed

    private void formatearTablaVenta() {
        tablaVenta.setRowSorter(null);
        tablaVenta.getColumnModel().getColumn(0).setPreferredWidth(70);
        tablaVenta.getColumnModel().getColumn(1).setPreferredWidth(296);
        tablaVenta.getColumnModel().getColumn(2).setPreferredWidth(70);
        tablaVenta.getColumnModel().getColumn(3).setPreferredWidth(100);
        tablaVenta.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaVenta.setRowHeight(30);
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        tablaVenta.getColumnModel().getColumn(0).setCellRenderer(centro);
        tablaVenta.getColumnModel().getColumn(1).setCellRenderer(centro);
        tablaVenta.getColumnModel().getColumn(2).setCellRenderer(centro);
        tablaVenta.getColumnModel().getColumn(3).setCellRenderer(centro);
        tablaVenta.getColumnModel().getColumn(4).setCellRenderer(centro);
        tablaVenta.setAutoResizeMode(tablaVenta.AUTO_RESIZE_OFF);
    }

    private void formatearTablaProductosV() {
        tablaProductosV.setRowSorter(null);
        tablaProductosV.getColumnModel().getColumn(0).setPreferredWidth(70);
        tablaProductosV.getColumnModel().getColumn(1).setPreferredWidth(320);
        tablaProductosV.getColumnModel().getColumn(2).setPreferredWidth(100);
        tablaProductosV.getColumnModel().getColumn(3).setPreferredWidth(180);
        tablaProductosV.getColumnModel().getColumn(4).setPreferredWidth(180);
        tablaProductosV.setRowHeight(30);
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        tablaProductosV.getColumnModel().getColumn(0).setCellRenderer(centro);
        tablaProductosV.getColumnModel().getColumn(1).setCellRenderer(centro);
        tablaProductosV.getColumnModel().getColumn(2).setCellRenderer(centro);
        tablaProductosV.getColumnModel().getColumn(3).setCellRenderer(centro);
        tablaProductosV.getColumnModel().getColumn(4).setCellRenderer(centro);
        tablaProductosV.setAutoResizeMode(tablaVenta.AUTO_RESIZE_OFF);

    }

    private void formatearTablaVentas() {
        tablaProductosV.setRowSorter(null);
        tablaProductosV.getColumnModel().getColumn(0).setPreferredWidth(70);
        tablaProductosV.getColumnModel().getColumn(1).setPreferredWidth(320);
        tablaProductosV.getColumnModel().getColumn(2).setPreferredWidth(100);
        tablaProductosV.getColumnModel().getColumn(3).setPreferredWidth(180);
        tablaProductosV.getColumnModel().getColumn(4).setPreferredWidth(180);
        tablaProductosV.setRowHeight(30);
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        tablaProductosV.getColumnModel().getColumn(0).setCellRenderer(centro);
        tablaProductosV.getColumnModel().getColumn(1).setCellRenderer(centro);
        tablaProductosV.getColumnModel().getColumn(2).setCellRenderer(centro);
        tablaProductosV.getColumnModel().getColumn(3).setCellRenderer(centro);
        tablaProductosV.getColumnModel().getColumn(4).setCellRenderer(centro);
        tablaProductosV.setAutoResizeMode(tablaVenta.AUTO_RESIZE_OFF);
    }

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        clickVentas();
        formatearTablaVenta();
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnVentas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas1ActionPerformed
        administracionPanel.setVisible(false);
        metodoPago.setVisible(false);
        ventasPanel.setVisible(true);
        estadísticaPanel.setVisible(false);
        formatearTablaVenta();
    }//GEN-LAST:event_btnVentas1ActionPerformed

    private void btnProductos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos1ActionPerformed
        clickProductos();
    }//GEN-LAST:event_btnProductos1ActionPerformed

    private void clickAdministracion() {
        metodoPago.setVisible(false);
        productosPanel.setVisible(false);
        ventasPanel.setVisible(false);
        estadísticaPanel.setVisible(false);
        administracionPanel.setVisible(true);
    }

    private void clickVentas() {
        metodoPago.setVisible(false);
        productosPanel.setVisible(false);
        estadísticaPanel.setVisible(false);
        administracionPanel.setVisible(false);
        ventasPanel.setVisible(true);
    }

    private void clickProductos() {
        metodoPago.setVisible(false);
        estadísticaPanel.setVisible(false);
        administracionPanel.setVisible(false);
        ventasPanel.setVisible(false);
        GestionProdPanel.setVisible(false);
        AccionGestion.setVisible(false);
        InicioProdPanel.setVisible(true);
        productosPanel.setVisible(true);
        btnGestionarP.setBackground(new Color(20,52,83));
        btnListasP.setBackground(new Color(20,52,83));
        btnActualizarP.setBackground(new Color(20,52,83));
    }

    private void clickEstadisticas() {
        metodoPago.setVisible(false);
        administracionPanel.setVisible(false);
        ventasPanel.setVisible(false);
        productosPanel.setVisible(false);
        estadísticaPanel.setVisible(true);
    }
    private void btnAdministracion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracion1ActionPerformed
        clickAdministracion();
    }//GEN-LAST:event_btnAdministracion1ActionPerformed

    private void EliminarItemVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarItemVActionPerformed
        int fila = tablaVenta.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un producto.");
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar el producto seleccionado de la venta?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            ListaVentas.remove(fila);
            calcularDescuento();
            calcularIVA();
            determinarCondicionIva();
        }
    }//GEN-LAST:event_EliminarItemVActionPerformed

    //SECCION VENTAS
    private void DatosCobroVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DatosCobroVentaActionPerformed
        cargarCbCondIva();
        cargarCbMpVentas();
        metodoPago.setVisible(true);
        ventasPanel.setVisible(false);
        cbCondIvaMp.removeAllItems();
        NCondIVA nCondIVA = new NCondIVA();
        for (CondIVA c : nCondIVA.getLista()) {
            cbCondIvaMp.addItem(c);
            // Buscamos dinámicamente por descripción para evitar depender de un ID rígido
            if ("Consumidor Final".equalsIgnoreCase(c.getDescripcion())) {
                cbCondIvaMp.setSelectedItem(c);
            }
        }
    }//GEN-LAST:event_DatosCobroVentaActionPerformed

    private void RehacerVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RehacerVentaActionPerformed
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea rehacer la venta?\nSe eliminarán todos los productos agregados.",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            ListaVentas.clear();
            totalVenta = 0;
            cargarTablaVenta();
            rehacerBuscarProductoV();
        }
    }//GEN-LAST:event_RehacerVentaActionPerformed

    private void ConfirmarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmarVentaActionPerformed
        if (ListaVentas == null || ListaVentas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos cargados en la venta.");
            return;
        }
        if (clienteVenta == null) {
            JOptionPane.showMessageDialog(this, "Debe buscar y seleccionar un cliente antes de confirmar la venta.");
            return;
        }
        CondIVA condCliente = (CondIVA) cbCondIvaMp.getSelectedItem();
        MetodoPago mp = (MetodoPago) cbMpVentas.getSelectedItem();
        String tipoComprobante = "Factura " + lblFact.getText();
        boolean esProforma = tipoComprobante.equals("Factura X");

        int cuotas = 1;
        if (esCuota) {
            if (!validarDatosCobro()) {
                return;
            }
            cuotas = Integer.parseInt(txtCuotas.getText().trim());
        }
        float interes = interesCalculado;

        Venta venta = new Venta();
        venta.setFecha(new java.util.Date());
        venta.setTotal(totalVenta);
        venta.setCuotas(cuotas);
        venta.setInteres(interes);
        venta.setTipoComprobante(tipoComprobante);
        venta.setIdMetodoPago(mp.getIdMetodoPago());
        venta.setIdIVA(condCliente.getIdCondIva());
        venta.setIdCliente(clienteVenta.getIdCliente());
        venta.setIdVendedor(usuarioLogueado.getId());

        if (esProforma) {
            pdfComprobante(venta, condCliente.getDescripcion(), true);
            ListaVentas.clear();
            cargarTablaVenta();
            rehacerBuscarProductoV();
            txtCuotas.setText("");
            return;
        }

        try {
            int idVenta = NVenta.insertarConId(venta);
            if (idVenta > 0) {
                limpiarMP();
                confirmarDetalleVenta(idVenta);
                Object[] opciones = {"Imprimir", "Cerrar"};
                int opcion = JOptionPane.showOptionDialog(
                        this,
                        "Venta registrada correctamente.",
                        "Venta",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]);
                if (opcion == 0) {
                    venta.setIdVenta(idVenta);
                    pdfComprobante(venta, condCliente.getDescripcion(), false);
                }
                ListaVentas.clear();
                cargarTablaVenta();
                rehacerBuscarProductoV();
                txtCuotas.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo registrar la venta.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_ConfirmarVentaActionPerformed

    public void confirmarDetalleVenta(int idVenta) {
        try {

            NProducto np = new NProducto();

            for (DetalleVenta dv : ListaVentas) {

                dv.setIdVenta(idVenta);

                nDetalleVenta.insertar(dv);

                np.descontarStock(
                        dv.getIdProducto(),
                        dv.getCantidad());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void pdfComprobante(Venta venta, String condicionIva, boolean esProforma) {

        try {

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Comprobante");
            chooser.setSelectedFile(new File("Venta_" + venta.getIdVenta() + ".pdf"));

            if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File archivo = chooser.getSelectedFile();

            if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
                archivo = new File(archivo.getAbsolutePath() + ".pdf");
            }

            Document documento = new Document(PageSize.A4, 36, 36, 36, 36);
            PdfWriter.getInstance(documento, new FileOutputStream(archivo));
            documento.open();

            BaseColor colorPrimario = new BaseColor(30, 41, 59);    // Azul oscuro (slate)
            BaseColor colorAcento = new BaseColor(37, 99, 235);   // Azul vivo
            BaseColor colorGrisClaro = new BaseColor(241, 245, 249); // Gris muy claro (fondo filas)
            BaseColor colorGrisTexto = new BaseColor(100, 116, 139); // Gris para texto secundario
            BaseColor colorBlanco = BaseColor.WHITE;

            com.itextpdf.text.Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, colorPrimario);
            com.itextpdf.text.Font subtituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, colorGrisTexto);
            com.itextpdf.text.Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10, colorPrimario);
            com.itextpdf.text.Font normalGrisFont = FontFactory.getFont(FontFactory.HELVETICA, 10, colorGrisTexto);
            com.itextpdf.text.Font headerTablaFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, colorBlanco);
            com.itextpdf.text.Font celdaTablaFont = FontFactory.getFont(FontFactory.HELVETICA, 9.5f, colorPrimario);
            com.itextpdf.text.Font totalLabelFont = FontFactory.getFont(FontFactory.HELVETICA, 11, colorGrisTexto);
            com.itextpdf.text.Font totalValueFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, colorAcento);

            PdfPTable encabezado = new PdfPTable(2);
            encabezado.setWidthPercentage(100);
            encabezado.setWidths(new float[]{1.2f, 1f});

            Image logo = Image.getInstance(getClass().getResource("/img/logo-empresa.jpg"));
            logo.scaleToFit(160, 70);

            PdfPCell celdaLogo = new PdfPCell(logo, false);
            celdaLogo.setBorder(Rectangle.NO_BORDER);
            celdaLogo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            encabezado.addCell(celdaLogo);

            PdfPCell celdaDatos = new PdfPCell();
            celdaDatos.setBorder(Rectangle.NO_BORDER);
            celdaDatos.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celdaDatos.setHorizontalAlignment(Element.ALIGN_RIGHT);

            Paragraph direccion = new Paragraph("Formosa - España 317", subtituloFont);
            direccion.setAlignment(Element.ALIGN_RIGHT);
            Paragraph telefono = new Paragraph("Tel: 3704-384028", subtituloFont);
            telefono.setAlignment(Element.ALIGN_RIGHT);

            celdaDatos.addElement(direccion);
            celdaDatos.addElement(telefono);
            encabezado.addCell(celdaDatos);
            documento.add(new Paragraph(" ", normalFont));

            if (esProforma) {
                com.itextpdf.text.Font proformaFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.RED);
                Paragraph proforma = new Paragraph("PROFORMA - VÁLIDO HASTA 7 DÍAS DEPUES DE LA EMISIÓN", proformaFont);
                proforma.setAlignment(Element.ALIGN_CENTER);
                proforma.setSpacingAfter(12);
                documento.add(proforma);
            }

            documento.add(encabezado);

            LineSeparator linea = new LineSeparator();
            linea.setLineColor(colorPrimario);
            linea.setLineWidth(1.5f);
            documento.add(new Chunk(linea));

            documento.add(new Paragraph(" ", normalFont));

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            NumberFormat nf = NumberFormat.getNumberInstance(new Locale("es", "AR"));
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);

            PdfPTable datos = new PdfPTable(2);
            datos.setWidthPercentage(100);
            datos.setWidths(new float[]{1f, 1f});
            datos.setSpacingAfter(15);

            agregarDato(datos, "Fecha", sdf.format(venta.getFecha()), subtituloFont, normalFont);
            agregarDato(datos, "Cliente", clienteVenta.getRazonSocial(), subtituloFont, normalFont);
            agregarDato(datos, "Vendedor", usuarioLogueado.getUsuario(), subtituloFont, normalFont);
            agregarDato(datos, "CUIT", clienteVenta.getCuit(), subtituloFont, normalFont);
            agregarDato(datos, "Factura a emitir", String.valueOf(venta.getTipoComprobante()), subtituloFont, normalFont);
            agregarDato(datos, "Dirección", clienteVenta.getDomicilio(), subtituloFont, normalFont);
            datos.completeRow();
            documento.add(datos);

            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{1.2f, 3f, 1f, 1.2f, 1.3f});
            tabla.setSpacingBefore(5);

            String[] encabezados = {"Código", "Descripción", "Cant.", "Precio", "Importe"};
            for (String texto : encabezados) {
                PdfPCell celda = new PdfPCell(new Phrase(texto, headerTablaFont));
                celda.setBackgroundColor(colorPrimario);
                celda.setPadding(8);
                celda.setBorder(Rectangle.NO_BORDER);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(celda);
            }

            NProducto np = new NProducto();
            double iva105 = 0;
            double iva21 = 0;
            double iva27 = 0;
            boolean filaClara = true;

            for (DetalleVenta dv : ListaVentas) {

                Producto p = np.select(dv.getIdProducto());
                BaseColor fondoFila = filaClara ? colorGrisClaro : colorBlanco;

                agregarCeldaFila(tabla, String.valueOf(p.getCodigo()), celdaTablaFont, fondoFila, Element.ALIGN_CENTER);
                agregarCeldaFila(tabla, p.getDescripcion(), celdaTablaFont, fondoFila, Element.ALIGN_LEFT);
                agregarCeldaFila(tabla, String.valueOf(dv.getCantidad()), celdaTablaFont, fondoFila, Element.ALIGN_CENTER);
                agregarCeldaFila(tabla, "$" + nf.format(dv.getPrecioUnitario()), celdaTablaFont, fondoFila, Element.ALIGN_RIGHT);
                agregarCeldaFila(tabla, "$" + nf.format(dv.getImporte()), celdaTablaFont, fondoFila, Element.ALIGN_RIGHT);

                filaClara = !filaClara;

                double base = dv.getImporte();
                float porcentaje = p.getIva().getPorcentaje();
                double importeIVA = base * porcentaje / 100;

                if (porcentaje == 10.5f) {
                    iva105 += importeIVA;
                } else if (porcentaje == 21f) {
                    iva21 += importeIVA;
                } else if (porcentaje == 27f) {
                    iva27 += importeIVA;
                }
            }

            documento.add(tabla);
            documento.add(new Paragraph(" ", normalFont));

            PdfPTable resumen = new PdfPTable(2);
            resumen.setWidthPercentage(55);
            resumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
            resumen.setWidths(new float[]{1.4f, 1f});
            resumen.setSpacingBefore(5);

            boolean esExento = "Exento".equals(condicionIva);
            boolean esResponsableInscripto = "Responsable Inscripto".equals(condicionIva);

            agregarResumenFila(resumen, "Método de Pago:", String.valueOf(cbMpVentas.getSelectedItem()), totalLabelFont, normalFont);
            agregarResumenFila(resumen, "Cuotas:", String.valueOf(venta.getCuotas()), totalLabelFont, normalFont);
            agregarResumenFila(resumen, "Interés:", "$" + nf.format(venta.getInteres()), totalLabelFont, normalFont);
            agregarResumenFila(resumen, "Descuento:", "$" + nf.format(descuentoTotal), totalLabelFont, normalFont);

            if (esResponsableInscripto) {
                agregarResumenFila(resumen, "IVA 10.5%:", "$" + nf.format(iva105), totalLabelFont, normalGrisFont);
                agregarResumenFila(resumen, "IVA 21%:", "$" + nf.format(iva21), totalLabelFont, normalGrisFont);
                agregarResumenFila(resumen, "IVA 27%:", "$" + nf.format(iva27), totalLabelFont, normalGrisFont);
            } else if (!esExento) {
                agregarResumenFila(resumen, "IVA:", "Incluido", totalLabelFont, normalGrisFont);
            } else if (esExento) {
                agregarResumenFila(resumen, "IVA:", "No incluye (Excento)", totalLabelFont, normalGrisFont);
            }

            documento.add(resumen);

            // Línea antes del total final
            LineSeparator lineaTotal = new LineSeparator();
            lineaTotal.setLineColor(colorGrisTexto);
            lineaTotal.setLineWidth(0.5f);
            Paragraph parLinea = new Paragraph(" ");
            parLinea.setSpacingBefore(8);
            documento.add(parLinea);

            PdfPTable totalBox = new PdfPTable(2);
            totalBox.setWidthPercentage(55);
            totalBox.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalBox.setWidths(new float[]{1f, 1.3f});

            PdfPCell celdaLabelTotal = new PdfPCell(new Phrase("TOTAL", totalLabelFont));
            celdaLabelTotal.setBorder(Rectangle.TOP);
            celdaLabelTotal.setBorderColor(colorAcento);
            celdaLabelTotal.setPadding(8);
            celdaLabelTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celdaValorTotal = new PdfPCell(new Phrase("$" + nf.format(venta.getTotal()), totalValueFont));
            celdaValorTotal.setNoWrap(true);
            celdaValorTotal.setBorder(Rectangle.TOP);
            celdaValorTotal.setBorderColor(colorAcento);
            celdaValorTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
            celdaValorTotal.setPadding(8);

            totalBox.addCell(celdaLabelTotal);
            totalBox.addCell(celdaValorTotal);

            documento.add(totalBox);

            Paragraph pie = new Paragraph("Gracias por su compra", subtituloFont);
            pie.setAlignment(Element.ALIGN_CENTER);
            pie.setSpacingBefore(30);
            documento.add(pie);

            documento.close();

            JOptionPane.showMessageDialog(this, "Comprobante PDF generado correctamente.");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Error al generar PDF:\n" + e.getMessage());

            e.printStackTrace();
        }
    }

//==========================
// MÉTODOS AUXILIARES
//==========================
    private void agregarDato(PdfPTable tabla, String etiqueta, String valor,
            com.itextpdf.text.Font etiquetaFont, com.itextpdf.text.Font valorFont) {

        Paragraph contenido = new Paragraph();
        contenido.add(new Chunk(etiqueta + ": ", etiquetaFont));
        contenido.add(new Chunk(valor, valorFont));

        PdfPCell celda = new PdfPCell(contenido);
        celda.setBorder(Rectangle.NO_BORDER);
        celda.setPaddingBottom(4);
        tabla.addCell(celda);
    }

    private void agregarCeldaFila(PdfPTable tabla, String texto, com.itextpdf.text.Font font,
            BaseColor fondo, int alineacion) {

        PdfPCell celda = new PdfPCell(new Phrase(texto, font));
        celda.setBackgroundColor(fondo);
        celda.setPadding(6);
        celda.setBorder(Rectangle.NO_BORDER);
        celda.setHorizontalAlignment(alineacion);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tabla.addCell(celda);
    }

    private void agregarResumenFila(PdfPTable tabla, String etiqueta, String valor,
            com.itextpdf.text.Font etiquetaFont, com.itextpdf.text.Font valorFont) {

        PdfPCell celdaEtiqueta = new PdfPCell(new Phrase(etiqueta, etiquetaFont));
        celdaEtiqueta.setBorder(Rectangle.NO_BORDER);
        celdaEtiqueta.setPaddingBottom(4);

        PdfPCell celdaValor = new PdfPCell(new Phrase(valor, valorFont));
        celdaValor.setBorder(Rectangle.NO_BORDER);
        celdaValor.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celdaValor.setPaddingBottom(4);

        tabla.addCell(celdaEtiqueta);
        tabla.addCell(celdaValor);
    }

    private void txtPre2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPre2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPre2ActionPerformed

    private void LimpiarMpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarMpActionPerformed
        limpiarMP();
    }//GEN-LAST:event_LimpiarMpActionPerformed

    private void limpiarMP() {
        txtCuitCliente.setText("");
        txtNombreCliente.setText("");
        txtDomCliente.setText("");
        txtCuotas.setText("");
        noSeleccionFactura();
        factA.setEnabled(true);
        factB.setEnabled(true);
    }

    private void noSeleccionFactura() {
        labelFact.setText("X");
        lblFact.setText("X");
        factX.setBackground(new Color(16, 51, 89));
        factX.setForeground(Color.WHITE);
        factA.setEnabled(true);
        factA.setBackground(Color.WHITE);
        factA.setForeground(Color.BLACK);
        factB.setBackground(Color.WHITE);
        factB.setForeground(Color.BLACK);
    }

    private void seleccionarFacturaA() {
        labelFact.setText("A");
        lblFact.setText("A");
        factA.setBackground(new Color(16, 51, 89));
        factA.setForeground(Color.WHITE);
        factX.setBackground(Color.WHITE);
        factX.setForeground(Color.BLACK);
        factB.setBackground(Color.WHITE);
        factB.setForeground(Color.BLACK);
    }

    private void seleccionarFacturaB() {
        labelFact.setText("B");
        lblFact.setText("B");
        factA.setBackground(Color.WHITE);
        factA.setForeground(Color.BLACK);
        factX.setBackground(Color.WHITE);
        factX.setForeground(Color.BLACK);
        factB.setBackground(new Color(16, 51, 89));
        factB.setForeground(Color.WHITE);
    }


    private void factAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factAActionPerformed
        seleccionarFacturaA();
        cbCondIvaMp.setSelectedIndex(0);
    }//GEN-LAST:event_factAActionPerformed

    private void factBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factBActionPerformed
        seleccionarFacturaB();
        cbCondIvaMp.setSelectedIndex(1);
    }//GEN-LAST:event_factBActionPerformed

    private void BuscarCliMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarCliMPActionPerformed
        BuscarClienteV();
    }//GEN-LAST:event_BuscarCliMPActionPerformed

    private void BuscarClienteV() {
        try {
            String cuit = txtCuitCliente.getText().trim();
            NCliente negocio = new NCliente();
            Cliente c = negocio.select(cuit);

            if (c != null) {
                this.clienteVenta = c;
                txtNombreCliente.setText(c.getRazonSocial());
                txtCuitCliente.setText(c.getCuit());
                txtDomCliente.setText(c.getDomicilio());
                int idCondIva = c.getIdCondIva();
                for (int i = 0; i < cbCondIvaMp.getItemCount(); i++) {

                    CondIVA cond = cbCondIvaMp.getItemAt(i);

                    if (cond.getIdCondIva() == idCondIva) {
                        cbCondIvaMp.setSelectedIndex(i);
                        break;
                    }
                }

                CondIVA cond = (CondIVA) cbCondIvaMp.getSelectedItem();

                if (cond != null && cond.getDescripcion().equals("Responsable Inscripto")) {
                    seleccionarFacturaA();
                    factB.setEnabled(false);
                } else {
                    seleccionarFacturaB();
                    factA.setEnabled(false);
                }

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Cliente no encontrado");
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cbMpVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMpVentasActionPerformed
        MetodoPago mp = (MetodoPago) cbMpVentas.getSelectedItem();

        if (mp == null) {
            return;
        }

        String descripcion = mp.getDescripcion();

        if (descripcion.equals("Efectivo")
                || descripcion.equals("Transferencia")
                || descripcion.equals("Cheque")
                || descripcion.equals("Débito")) {

            txtCuotas.setText("");
            txtCuotas.setEnabled(false);
            esCuota = false;
        } else {
            txtCuotas.setEnabled(true);
            esCuota = true;
        }
        calcularDescuento();
    }//GEN-LAST:event_cbMpVentasActionPerformed

    private void AgregarCliMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarCliMPActionPerformed
        String cuit = txtCuitCliente.getText().trim();
        if (!cuit.matches("\\d{11}")) {
            javax.swing.JOptionPane.showMessageDialog(this, "El CUIT debe tener 11 dígitos numéricos");
        } else {
            try {
                if (!txtNombreCliente.getText().trim().isEmpty()
                        && !txtCuitCliente.getText().trim().isEmpty()
                        && !txtDomCliente.getText().trim().isEmpty()) {
                    String razonSocial = txtNombreCliente.getText();
                    String telefono = "";
                    Date fchaIngreso = new Date();
                    String domicilio = txtDomCliente.getText();
                    String mail = "";
                    String tipoCliente = "Cliente";
                    String estado = "Activo";
                    String tipoPersona = "";
                    CondIVA cond = (CondIVA) cbCondIvaMp.getSelectedItem();
                    int idCondIva = cond.getIdCondIva();

                    Cliente cliente = new Cliente(
                            razonSocial,
                            telefono,
                            cuit,
                            fchaIngreso,
                            domicilio,
                            mail,
                            tipoCliente,
                            estado,
                            tipoPersona,
                            idCondIva
                    );

                    NCliente nuevaCuenta = new NCliente();
                    nuevaCuenta.create(cliente);
                    javax.swing.JOptionPane.showMessageDialog(this, "Registrado con éxito!");
                    BuscarClienteV();

                }
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            }
        }

    }//GEN-LAST:event_AgregarCliMPActionPerformed

    private void cancelarMP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarMP1ActionPerformed
        metodoPago.setVisible(false);
        ventasPanel.setVisible(true);
    }//GEN-LAST:event_cancelarMP1ActionPerformed

    private void btnAContinuar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAContinuar4ActionPerformed
        // 1. Validaciones de seguridad en la interfaz gráfica
        if (cbNombre.getSelectedItem() == null || cbNombre.getSelectedIndex() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione un cliente válido.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (date.getDate() == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione la fecha actual de pago.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtMontoTotal.getText().trim().isEmpty() || txtMontoTotal.getText().equals("0.00")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Primero debe calcular las cuotas y los montos a cobrar.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {

            String nombreSeleccionado = cbNombre.getSelectedItem().toString();
            int idCliente = -1;
            if (listaClientesGlobal != null) {
                for (Modelos.Cliente c : listaClientesGlobal) {
                    if (c.getRazonSocial().equals(nombreSeleccionado)) {
                        idCliente = c.getIdCliente();
                        break;
                    }
                }
            }

            int indexSeleccionado = cbMetodosPagos.getSelectedIndex() - 1;
            int idMetodoPago = 1; // Valor por defecto seguro

            if (indexSeleccionado >= 0 && listaMetodosPagoGlobal != null && indexSeleccionado < listaMetodosPagoGlobal.size()) {
                idMetodoPago = listaMetodosPagoGlobal.get(indexSeleccionado).getIdMetodoPago(); // Usamos el ID real de la BD
            }

            double totalCompras = Double.parseDouble(txtComprasTotales.getText().trim().replace(",", "."));
            int cuotas = Integer.parseInt(txtCuotasPagos.getText().trim());
            double interes = Double.parseDouble(txtInteres.getText().trim().replace(",", "."));
            boolean aplicarDescuento = cbDescuento.isSelected();
            double totalFinal = Double.parseDouble(txtMontoTotal.getText().trim().replace(",", "."));

            boolean exito = nCobros.procesarCobro(idCliente, idMetodoPago, totalCompras, cuotas, interes, aplicarDescuento, totalFinal);

            if (exito) {
                javax.swing.JOptionPane.showMessageDialog(this, "El pago de la cuenta corriente fue registrado con éxito en la base de datos.", "Registro Exitoso", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                txtComprasTotales.setText("");
                txtCuil.setText("");
                txtSubtotal.setText("0.00");
                txtInteres.setText("0.00");
                txtUnidadCuota.setText("0.00");
                txtMontoTotal.setText("0.00");
                txtCuotasPagos.setText("1");
                cbNombre.setSelectedIndex(0);
                cbMetodosPagos.setSelectedIndex(0);
                cbDescuento.setSelected(false);
                date.setDate(null);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar el cobro. Verifique las reglas de negocio en la consola.", "Error de Almacenamiento", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error en el formato de los datos numéricos.", "Error de Datos", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAContinuar4ActionPerformed

    private void btnABuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnABuscar3ActionPerformed
        if (cbNombre.getSelectedItem() == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione un cliente de la lista.");
            return;
        }

        String nombreSeleccionado = cbNombre.getSelectedItem().toString();
        Modelos.Cliente clienteSeleccionado = null;

        if (listaClientesGlobal != null) {
            for (Modelos.Cliente c : listaClientesGlobal) {
                if (c.getRazonSocial().equals(nombreSeleccionado)) {
                    clienteSeleccionado = c;
                    break;
                }
            }
        }

        if (clienteSeleccionado != null) {
            txtCuil.setText(clienteSeleccionado.getCuit());

            int idCli = clienteSeleccionado.getIdCliente();
            NVenta nVenta = new NVenta();

            // 1. Traemos AMBOS datos de la base de datos
            int ventasPendientes = nVenta.obtenerCantidadVentasPendientes(idCli);
            double deudaPendiente = nVenta.obtenerMontoVentasPendientes(idCli);

            // 2. Simulación de Fecha Vieja
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.add(java.util.Calendar.MONTH, -3);
            this.fechaVentaOriginal = cal.getTime();

            // 3. CORRECCIÓN: Compras totales DEBE ser el monto en dinero para que las operaciones matemáticas no rompan
            txtComprasTotales.setText(String.format(java.util.Locale.US, "%.2f", deudaPendiente));
            txtSubtotal.setText(String.format(java.util.Locale.US, "%.2f", deudaPendiente));
            txtMontoTotal.setText(String.format(java.util.Locale.US, "%.2f", deudaPendiente));
            txtCuotasPagos.setText("1"); // Predeterminado

            // 4. El cartel le informa al usuario de manera clara e inequívoca las dos métricas
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Datos cargados para: " + nombreSeleccionado
                    + "\nCantidad de ventas pendientes: " + ventasPendientes
                    + "\nMonto total de la deuda: $" + String.format(java.util.Locale.US, "%.2f", deudaPendiente));

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron los datos del cliente.");
        }
    }//GEN-LAST:event_btnABuscar3ActionPerformed

    private void btCalcularCuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCalcularCuotasActionPerformed
        calcularTotalesYCuotas();
    }//GEN-LAST:event_btCalcularCuotasActionPerformed

    private void calcularTotalesYCuotas() {
        try {
            // 1. Validar subtotal base desde el monto de dinero real
            if (txtComprasTotales.getText().trim().isEmpty()) {
                return;
            }
            double subtotal = Double.parseDouble(txtComprasTotales.getText().trim());

            // 2. Lógica de Interés por Plazo Vencido (Límite Máximo de 3 Meses)
            double interesCalculado = 0.0;

            if (date.getDate() != null && this.fechaVentaOriginal != null) {
                java.time.LocalDate fVenta = this.fechaVentaOriginal.toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDate();

                java.time.LocalDate fCobro = date.getDate().toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDate();

                java.time.Period periodo = java.time.Period.between(fVenta, fCobro);
                long totalMesesDiferencia = periodo.toTotalMonths();

                if (totalMesesDiferencia >= 3) {
                    double porcentajeInteres = 0.15;
                    interesCalculado = subtotal * porcentajeInteres;
                }
            }

            txtSubtotal.setText(String.format(java.util.Locale.US, "%.2f", subtotal));
            txtInteres.setText(String.format(java.util.Locale.US, "%.2f", interesCalculado));

            // 3. Validación del Descuento (Aplicado estrictamente sobre el SUBTOTAL base)
            double descuentoCalculado = 0.0;

            if (cbDescuento.isSelected()) {
                String metodoSeleccionado = (cbMetodosPagos.getSelectedItem() != null)
                        ? cbMetodosPagos.getSelectedItem().toString()
                        : "";

                if (metodoSeleccionado.equalsIgnoreCase("Efectivo")) {
                    double porcentajeDescuento = 0.15;
                    descuentoCalculado = subtotal * porcentajeDescuento;
                } else {
                    cbDescuento.setSelected(false);
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "El descuento del 15% solo puede aplicarse para pagos en Efectivo.",
                            "Advertencia",
                            javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }

            // 4. Calcular el monto total definitivo de forma sana y segura
            double montoFinal = subtotal - descuentoCalculado + interesCalculado;
            txtMontoTotal.setText(String.format(java.util.Locale.US, "%.2f", montoFinal));

            // 5. Cálculo de cuotas sobre el valor definitivo real
            int cantidadCuotas = 1;
            if (!txtCuotasPagos.getText().trim().isEmpty()) {
                try {
                    cantidadCuotas = Integer.parseInt(txtCuotasPagos.getText().trim());
                    if (cantidadCuotas <= 0) {
                        cantidadCuotas = 1;
                    }
                } catch (NumberFormatException e) {
                    cantidadCuotas = 1;
                }
            }

            double valorCuota = montoFinal / cantidadCuotas;
            txtUnidadCuota.setText(String.format(java.util.Locale.US, "%.2f", valorCuota));

        } catch (NumberFormatException ex) {
            txtMontoTotal.setText("0.00");
            txtUnidadCuota.setText("0.00");
        }
    }
    private void cbMetodosPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMetodosPagosActionPerformed
        if (cbNombre.getSelectedItem() != null && listaClientesGlobal != null) {
            String nombreSeleccionado = cbNombre.getSelectedItem().toString();

            Modelos.Cliente clienteSeleccionado = null;
            for (Modelos.Cliente c : listaClientesGlobal) {
                if (c.getRazonSocial().equals(nombreSeleccionado)) {
                    clienteSeleccionado = c;
                    break;
                }
            }

            if (clienteSeleccionado != null) {
                txtCuil.setText(clienteSeleccionado.getCuit());
                int idCli = clienteSeleccionado.getIdCliente();

                // CORRECCIÓN INTEGRAL: Cambiado nCobros.obtenerDeudaCliente por nVenta.obtenerMontoVentasPendientes
                // para que no se pisen ni se mezclen los números al interactuar con el combo de métodos de pago
                NVenta nVenta = new NVenta();
                double deudaPendiente = nVenta.obtenerMontoVentasPendientes(idCli);

                txtComprasTotales.setText(String.format(java.util.Locale.US, "%.2f", deudaPendiente));
                txtSubtotal.setText(String.format(java.util.Locale.US, "%.2f", deudaPendiente));
            }
        }

        if (cbDescuento.isSelected()) {
            calcularTotalesYCuotas();
        }
    }//GEN-LAST:event_cbMetodosPagosActionPerformed

    private void calcularDescuentoFinal() {
        if (txtMontoTotal.getText().isEmpty()) {
            return;
        }

        double comprasTotales = Double.parseDouble(txtComprasTotales.getText());
        int cuotas = txtCuotasPagos.getText().isEmpty() ? 1 : Integer.parseInt(txtCuotasPagos.getText());
        double porcentajeInteres = (cuotas > 1) ? (cuotas * 5.0) : 0.0;
        double totalConInteres = comprasTotales + (comprasTotales * (porcentajeInteres / 100.0));

        // Si está seleccionado, restamos un 10%, sino dejamos el valor base con interés
        if (cbDescuento.isSelected()) {
            double descuento = totalConInteres * 0.10; // 10% Descuento
            double totalFinal = totalConInteres - descuento;
            txtMontoTotal.setText(String.format("%.2f", totalFinal).replace(",", "."));
        } else {
            txtMontoTotal.setText(String.format("%.2f", totalConInteres).replace(",", "."));
        }
    }
    private void cbDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDescuentoActionPerformed
        calcularTotalesYCuotas();
    }//GEN-LAST:event_cbDescuentoActionPerformed

    private void btCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCalcularActionPerformed
        // Limpia los textfields del panel de cálculo para permitir una nueva operación
        txtCuotasPagos.setText("");
        txtUnidadCuota.setText("");
        txtInteres.setText("");
        txtSubtotal.setText("");
        txtMontoTotal.setText("");

        // Destilda el descuento por seguridad al reiniciar
        cbDescuento.setSelected(false);

        // Le devuelve el foco al campo de cuotas para comodidad del usuario
        txtCuotasPagos.requestFocus();
    }//GEN-LAST:event_btCalcularActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // 1. Fijamos la cantidad de pagos en 1 en la interfaz
        txtCuotasPagos.setText("1");

        // 2. Ejecutamos el método de cálculo para actualizar de forma automática
        calcularTotalesYCuotas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed
        javax.swing.JOptionPane.showMessageDialog(this,
                "¡Impreso con éxito!",
                "Impresión de Comprobante",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btImprimirActionPerformed

    private void cbNombreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbNombreItemStateChanged
        // Validamos que sea un evento de selección
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {

            // Si seleccionó la opción por defecto, no hacemos nada
            if (cbNombre.getSelectedIndex() == 0) {
                return;
            }

            // Recuperamos el objeto seleccionado
            Object seleccionado = cbNombre.getSelectedItem();

            // Validamos que efectivamente sea un objeto Cliente
            if (seleccionado instanceof Modelos.Cliente) {
                Modelos.Cliente clienteSeleccionado = (Modelos.Cliente) seleccionado;

                // Seteamos la interfaz directamente usando los métodos del objeto
                txtCuil.setText(clienteSeleccionado.getCuit());

                // Aquí ya podés disparar la búsqueda de sus deudas o lo que necesites
                int idCli = clienteSeleccionado.getIdCliente();
                NVenta nVenta = new NVenta();
                int ventasPendientes = nVenta.obtenerCantidadVentasPendientes(idCli);
                double deudaPendiente = nVenta.obtenerMontoVentasPendientes(idCli);

                txtComprasTotales.setText(String.format(java.util.Locale.US, "%.2f", deudaPendiente));
                txtSubtotal.setText(String.format(java.util.Locale.US, "%.2f", deudaPendiente));
                txtMontoTotal.setText(String.format(java.util.Locale.US, "%.2f", deudaPendiente));
            }
        }
    }//GEN-LAST:event_cbNombreItemStateChanged

    private void btnADetalleCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADetalleCCActionPerformed

        ListaPanel.setVisible(false);
        verDetallePanel.setVisible(true);

        int fila = tablaLista.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Error al seleccionar una cuenta corriente");
            return;
        }

        String rs = tablaLista.getValueAt(fila, 0).toString();
        txtNomCC.setText(rs);

        try {

            String cuit = tablaLista.getValueAt(fila, 2).toString();

            DCliente dCliente = new DCliente();
            Cliente cliente = dCliente.select(cuit);

            if (cliente != null) {
                this.actual = cliente;
                cargarCardsVentas(cliente.getIdCliente());
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnADetalleCCActionPerformed

    private void cargarCardsVentas(int idCliente) {

        tarjetaDetalle.removeAll();

        tarjetaDetalle.setLayout(new FlowLayout(
                FlowLayout.LEFT,
                10,
                10
        ));

        NVenta nVenta = new NVenta();
        NDetalleVenta nDetalle = new NDetalleVenta();

        List<Venta> ventas = nVenta.listarVentasCliente(idCliente);
        double saldoTotal = 0;

        if (ventas.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El cliente no tiene ventas registradas.");
            return;
        }

        for (Venta venta : ventas) {

            saldoTotal += venta.getTotal();

            List<DetalleVenta> detalles
                    = nDetalle.listarPorVenta(venta.getIdVenta());

            int cantidadProductos = 0;

            for (DetalleVenta d : detalles) {
                cantidadProductos += d.getCantidad();
            }

            JPanel card = crearCardVenta(venta, cantidadProductos);

            tarjetaDetalle.add(card);
        }
        txtSaldoT.setText(
                String.format("%,.2f", saldoTotal)
                        .replace(",", "X")
                        .replace(".", ",")
                        .replace("X", ".")
        );
        tarjetaDetalle.revalidate();
        tarjetaDetalle.repaint();
    }

    private JPanel crearCardVenta(Venta venta, int cantidadProductos) {

        JPanel card = new JPanel();

        card.setPreferredSize(new Dimension(280, 140));
        card.setBackground(Color.WHITE);
        card.setLayout(new BorderLayout());

        card.setBorder(BorderFactory.createLineBorder(
                new Color(200, 200, 200), 1));

        // HEADER
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        header.setBackground(new Color(30, 90, 142));
        header.setPreferredSize(new Dimension(280, 35));

        JLabel lblVenta = new JLabel("Venta #" + venta.getNumVenta());
        lblVenta.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblVenta.setForeground(Color.WHITE);

        header.add(lblVenta);

        // CONTENIDO
        JPanel contenido = new JPanel();
        contenido.setBackground(Color.WHITE);
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblFecha = new JLabel("Fecha: " + venta.getFecha());
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JLabel lblProductos = new JLabel(
                "Productos: " + cantidadProductos);
        lblProductos.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JLabel lblSubtotal = new JLabel(
                "Subtotal: $" + venta.getTotal());
        lblSubtotal.setFont(new Font("Segoe UI", Font.BOLD, 16));

        lblFecha.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblProductos.setAlignmentX(Component.LEFT_ALIGNMENT);

        // PANEL INFERIOR
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(Color.WHITE);
        panelInferior.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnVer = new JButton("Ver");
        btnVer.addActionListener(e -> {

            try {

                NDetalleVenta nDetalle = new NDetalleVenta();

                DProducto dProducto = new DProducto();

                List<DetalleVenta> detalles
                        = nDetalle.listarPorVenta(venta.getIdVenta());

                StringBuilder mensaje = new StringBuilder();

                mensaje.append("Venta N°: ")
                        .append(venta.getNumVenta())
                        .append("\n");

                mensaje.append("Fecha: ")
                        .append(venta.getFecha())
                        .append("\n");
                mensaje.append("/////////////////////////////////////////////////\n");
                mensaje.append("DETALLES\n");
                mensaje.append("/////////////////////////////////////////////////\n");

                for (DetalleVenta d : detalles) {

                    Producto p = dProducto.buscarPorId(d.getIdProducto());

                    mensaje.append(p.getDescripcion())
                            .append(" - Cant: ")
                            .append(d.getCantidad())
                            .append("\n");
                }

                mensaje.append("\n/////////////////////////////////////////////////\n");
                mensaje.append("Subtotal: $")
                        .append(venta.getTotal());

                JOptionPane.showMessageDialog(
                        this,
                        mensaje.toString(),
                        "Detalle de Venta",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );
            }
        });
        btnVer.setFocusPainted(false);

        panelInferior.add(lblSubtotal, BorderLayout.WEST);
        panelInferior.add(btnVer, BorderLayout.EAST);

        // ARMADO
        contenido.add(lblFecha);
        contenido.add(Box.createVerticalStrut(5));
        contenido.add(lblProductos);
        contenido.add(Box.createVerticalStrut(10));
        contenido.add(panelInferior);

        card.add(header, BorderLayout.NORTH);
        card.add(contenido, BorderLayout.CENTER);

        return card;
    }


    private void btnAVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAVolverActionPerformed
        verDetallePanel.setVisible(false);
        ListaPanel.setVisible(true);

    }//GEN-LAST:event_btnAVolverActionPerformed

    private void btnAExportarbtnExportarCliente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAExportarbtnExportarCliente
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAExportarbtnExportarCliente

    private void btnACobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnACobrarActionPerformed
        // 1. Validar que exista una cuenta corriente activa en el detalle
        if (this.actual == null) {
            JOptionPane.showMessageDialog(this,
                    "No hay ninguna cuenta corriente seleccionada para proceder al cobro.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 2. Redirección visual a la sección de pagos (ocultar detalle, mostrar pagos)
            verDetallePanel.setVisible(false);
            PagosPanel.setVisible(true);
            PagosPanelCampos.setVisible(true);
            PanelCalculo.setVisible(true);
            PanelBotones.setVisible(true);

            // Ajustar estéticamente los botones del Navbar de Administración para simular el click en "Pagos"
            btnAPagos.setBackground(new Color(38, 90, 142)); // Color activo [cite: 824]
            btnAListas.setBackground(new Color(20, 52, 83)); // Color inactivo [cite: 825]
            btnAClientes.setBackground(new Color(20, 52, 83)); // Color inactivo

            // 3. Forzar la selección del cliente en el ComboBox de la sección Pagos
            cbNombre.setSelectedItem(this.actual.getRazonSocial());

            // 4. Autocompletar el resto de campos imitando la búsqueda manual por Razón Social
            txtCuil.setText(this.actual.getCuit()); // 
            int idCli = this.actual.getIdCliente(); // 

            // Obtener la deuda real histórica/actual registrada en la CC mediante las capas de negocio
            double deudaTotal = nCobros.obtenerDeudaCliente(idCli); // [cite: 580]

            // Rellenar montos base e inicializar el formulario de cobro
            txtComprasTotales.setText(String.format(java.util.Locale.US, "%.2f", deudaTotal)); // [cite: 581]
            txtSubtotal.setText(String.format(java.util.Locale.US, "%.2f", deudaTotal)); // [cite: 581]
            txtMontoTotal.setText(String.format(java.util.Locale.US, "%.2f", deudaTotal));
            txtCuotasPagos.setText("1"); // Predeterminado 
            date.setDate(new java.util.Date()); // Setea la fecha actual para el cobro por defecto

            // Intereses y cuotas en cero/vacío hasta que se ejecute un cálculo específico si fuera necesario
            txtInteres.setText("0.00"); // 
            txtUnidadCuota.setText("0.00"); // 

            // 5. Mostrar cartel informativo tal como se solicitó
            JOptionPane.showMessageDialog(this,
                    "Se transfirieron los datos correctamente.\n"
                    + "Cliente: " + this.actual.getRazonSocial() + "\n"
                    + "CUIT/CUIL: " + this.actual.getCuit() + "\n"
                    + "Saldo de Cuenta Corriente cargado: $" + String.format(java.util.Locale.US, "%.2f", deudaTotal),
                    "Sección Pagos Autocompletada",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error al redireccionar y autocompletar cobro de CC", e);
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error al procesar el recorrido del cobro automatizado.",
                    "Error de Navegación",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnACobrarActionPerformed

    private void txtFiltroRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroRSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltroRSActionPerformed

    private void BuscarDescVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarDescVActionPerformed
        cargarTablaProductosV();
        formatearTablaProductosV();

        cargarCbRubroV();

        PBuscarProductoV.setLocationRelativeTo(null);
        PBuscarProductoV.setVisible(true);
    }//GEN-LAST:event_BuscarDescVActionPerformed

    private void BuscarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarVActionPerformed
        Locale localeAR = new Locale("es", "AR");
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(localeAR);
        String cod = txtCodV.getText();
        if (validarNumero(cod)) {
            try {
                NProducto np = new NProducto();
                Producto p = np.buscarPorCodigo(cod);
                if (p != null) {
                    txtCodV.setText(cod);
                    txtDescV.setText(p.getDescripcion());
                    float preU = p.getPrecioUnitario();
                    txtPre1.setText(formatoMoneda.format(preU));
                    txtPre2.setText(formatoMoneda.format(preU - preU / 10));
                    txtIVA.setText(p.getIva().toString());
                    txtStock.setText(String.valueOf(p.getIdStockProducto().getStockAct()));
                }
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "No existe producto con el código: " + cod);
                rehacerBuscarProductoV();
            }
        } else {
            rehacerBuscarProductoV();
        }
    }//GEN-LAST:event_BuscarVActionPerformed

    private void cargarCbTipoV(RubroProducto rubro) {
        cbTipoV.removeAllItems();

        if (rubro == null) {
            return;
        }

        NTipoProducto ntp = new NTipoProducto();

        for (TipoProducto tp : ntp.listarPorRubro(rubro.getIdRubroProducto())) {
            cbTipoV.addItem(tp);
        }
    }

    public void cargarCbRubroV() {
        cbRubroV.removeAllItems();

        NRubroProducto nrp = new NRubroProducto();

        for (RubroProducto rp : nrp.listar()) {
            cbRubroV.addItem(rp);
        }

        if (cbRubroV.getItemCount() > 0) {
            cbRubroV.setSelectedIndex(0);
        }
    }

    private void cargarTablaProductosV() {
        Locale localeAR = new Locale("es", "AR");
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(localeAR);
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Código");
        modelo.addColumn("Descripción");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Rubro");
        modelo.addColumn("Tipo");

        NProducto np = new NProducto();

        for (Producto p : np.listar()) {
            modelo.addRow(new Object[]{
                p.getCodigo(),
                p.getDescripcion(),
                formatoMoneda.format(p.getPrecioUnitario()),
                p.getRubroProducto(),
                p.getTipoProducto()
            });
        }

        tablaProductosV.setModel(modelo);
        formatearTablaProductosV();

    }

    private boolean validarNumero(String num) {
        if (num != null && !num.trim().equals("")) {
            if (!num.matches("\\d+")) {
                javax.swing.JOptionPane.showMessageDialog(this, "El código debe ser numérico");
                return false;
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe ingresar un código o buscar por 'Descripción'.");
            return false;
        }
        return true;
    }

    private void AgregarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarVActionPerformed
        Locale localeAR = new Locale("es", "AR");
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(localeAR);

        String cod = txtCodV.getText().trim();

        try {
            if (cod.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Ingrese el código de un producto.");
                return;
            }

            NProducto np = new NProducto();
            Producto p = np.buscarPorCodigo(cod);

            if (p == null) {
                JOptionPane.showMessageDialog(this,
                        "No existe un producto con el código: " + cod);
                return;
            }

            int cant = Integer.parseInt(txtCantV.getText());

            if (cant <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Debe ingresar una cantidad mayor a 0.");
                return;
            }

            int stockAct = Integer.parseInt(txtStock.getText());

            if (cant > stockAct) {
                JOptionPane.showMessageDialog(this,
                        "Debe ingresar una cantidad menor o igual a " + stockAct);
                return;
            }
            DetalleVenta dv = new DetalleVenta();

            dv.setIdProducto(p.getIdProducto());
            dv.setCantidad(cant);
            dv.setPrecioUnitario(p.getPrecioUnitario());
            dv.setImporte(cant * p.getPrecioUnitario());
            dv.setDescuento(0);
            ListaVentas.add(dv);
            cargarTablaVenta();
            calcularDescuento();
            calcularIVA();
            determinarCondicionIva();
            rehacerBuscarProductoV();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "Ingrese una cantidad.");

        } catch (Exception ex) {

            System.getLogger(PPanelAMP.class.getName())
                    .log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_AgregarVActionPerformed

    private void cargarTablaVenta() {

        Locale localeAR = new Locale("es", "AR");
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(localeAR);

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Código");
        modelo.addColumn("Descripción");
        modelo.addColumn("Cantidad");
        modelo.addColumn("P. Unitario");
        modelo.addColumn("Importe");

        totalVenta = 0;
        subtotalVenta = 0;

        NProducto np = new NProducto();

        for (DetalleVenta dv : ListaVentas) {

            Producto p = np.select(dv.getIdProducto());

            modelo.addRow(new Object[]{
                p.getCodigo(),
                p.getDescripcion(),
                dv.getCantidad(),
                formatoMoneda.format(dv.getPrecioUnitario()),
                formatoMoneda.format(dv.getImporte())
            });

            subtotalVenta += dv.getImporte();
        }

        tablaVenta.setModel(modelo);
        SubtotalV.setText(formatoMoneda.format(subtotalVenta));

        formatearTablaVenta();
    }
    private void cbRubroVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRubroVActionPerformed
        if (cbRubroV.getSelectedItem() == null) {
            return;
        }

        RubroProducto rp = (RubroProducto) cbRubroV.getSelectedItem();
        cargarCbTipoV(rp);
    }//GEN-LAST:event_cbRubroVActionPerformed

    private void BuscarProdBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarProdBPActionPerformed

        RubroProducto rubro = (RubroProducto) cbRubroV.getSelectedItem();
        TipoProducto tipo = (TipoProducto) cbTipoV.getSelectedItem();

        NProducto np = new NProducto();
        List<Producto> lista = np.listar();

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Código");
        modelo.addColumn("Descripción");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Rubro");
        modelo.addColumn("Tipo");

        Locale localeAR = new Locale("es", "AR");
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(localeAR);

        boolean hayResultados = false;

        for (Producto p : lista) {

            boolean coincide = true;

            // rubro
            if (rubro != null
                    && p.getRubroProducto().getIdRubroProducto() != rubro.getIdRubroProducto()) {
                coincide = false;
            }

            // tipo
            if (tipo != null
                    && p.getTipoProducto().getIdTipoProducto() != tipo.getIdTipoProducto()) {
                coincide = false;
            }

            if (coincide) {

                hayResultados = true;

                modelo.addRow(new Object[]{
                    p.getCodigo(),
                    p.getDescripcion(),
                    formatoMoneda.format(p.getPrecioUnitario()),
                    p.getRubroProducto(),
                    p.getTipoProducto()
                });
            }
        }

        tablaProductosV.setModel(modelo);
        formatearTablaProductosV();

        if (!hayResultados) {
            JOptionPane.showMessageDialog(this, "No se encontraron productos.");
        }
    }//GEN-LAST:event_BuscarProdBPActionPerformed

    private void LimpiarFiltroBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarFiltroBPActionPerformed
        txtDescBP.setText("");
        cbRubroV.setSelectedIndex(0);
        cbTipoV.setSelectedIndex(0);
        cargarTablaProductosV();
    }//GEN-LAST:event_LimpiarFiltroBPActionPerformed

    private void RehacerVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RehacerVActionPerformed
        rehacerBuscarProductoV();
    }//GEN-LAST:event_RehacerVActionPerformed

    private void BuscarProdBPDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarProdBPDescActionPerformed
        String desc = txtDescBP.getText().trim().toLowerCase();
        if (!"".equals(desc)) {
            NProducto np = new NProducto();
            List<Producto> lista = np.listar();

            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("Código");
            modelo.addColumn("Descripción");
            modelo.addColumn("Precio Unitario");
            modelo.addColumn("Rubro");
            modelo.addColumn("Tipo");

            Locale localeAR = new Locale("es", "AR");
            NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(localeAR);

            boolean hayResultados = false;

            for (Producto p : lista) {

                boolean coincide = true;

                // descripción
                if (!desc.isEmpty()
                        && !p.getDescripcion().toLowerCase().contains(desc)) {
                    coincide = false;
                }

                if (coincide) {

                    hayResultados = true;

                    modelo.addRow(new Object[]{
                        p.getCodigo(),
                        p.getDescripcion(),
                        formatoMoneda.format(p.getPrecioUnitario()),
                        p.getRubroProducto(),
                        p.getTipoProducto()
                    });
                }
            }

            tablaProductosV.setModel(modelo);
            formatearTablaProductosV();

            if (!hayResultados) {
                JOptionPane.showMessageDialog(this, "No se encontraron productos.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del producto.");
        }

    }//GEN-LAST:event_BuscarProdBPDescActionPerformed

    private void AgregarProdBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarProdBPActionPerformed
        Locale localeAR = new Locale("es", "AR");
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(localeAR);
        int fila = tablaProductosV.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un producto");
            return;
        }
        String cod = tablaProductosV.getValueAt(fila, 0).toString();
        DProducto dp = new DProducto();
        Producto p;
        try {
            p = dp.buscarPorCodigo(cod);
            if (p != null) {
                txtCodV.setText(cod);
                txtDescV.setText(p.getDescripcion());
                float preU = p.getPrecioUnitario();
                txtPre1.setText(formatoMoneda.format(preU));
                txtPre2.setText(formatoMoneda.format(preU - preU / 10));
                txtIVA.setText(p.getIva().toString());
                txtStock.setText(String.valueOf(p.getIdStockProducto().getStockAct()));
                PBuscarProductoV.setVisible(false);
                ventasPanel.setVisible(true);
            }
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al agregar el producto seleccionado ");
        }
    }//GEN-LAST:event_AgregarProdBPActionPerformed

    private void cbCondIvaMpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCondIvaMpActionPerformed
        CondIVA cond = (CondIVA) cbCondIvaMp.getSelectedItem();

        if (cond == null) {
            return;
        }

        if (cond.getDescripcion().equals("Responsable Inscripto")) {
            seleccionarFacturaA();
        } else {
            seleccionarFacturaB();
        }

    }//GEN-LAST:event_cbCondIvaMpActionPerformed
    private boolean validarDatosCobro() {
        if (txtCuotas.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar la cantidad de cuotas.");
            return false;
        }
        try {
            int cuotas = Integer.parseInt(txtCuotas.getText().trim());

            if (cuotas <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad de cuotas debe ser mayor a cero.");
                return false;
            }
            if (cuotas > 12) {
                JOptionPane.showMessageDialog(this, "La cantidad de cuotas debe ser menor a 12.");
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad de cuotas es inválida.");
            return false;
        }
        return true;
    }

    private void calcularInteresesCobro() {
        if (!validarDatosCobro()) {
            return;
        }

        Locale localeAR = new Locale("es", "AR");
        NumberFormat formato = NumberFormat.getCurrencyInstance(localeAR);

        int cuotas = Integer.parseInt(txtCuotas.getText().trim());
        float porcentajeInteres = (cuotas - 1) * 2f;
        interesCalculado = totalVenta * porcentajeInteres / 100f;
        float totalFinanciado = totalVenta + interesCalculado;
        float valorCuota = totalFinanciado / cuotas;
        TotalV.setText(formato.format(valorCuota));
        JOptionPane.showMessageDialog(this,
                "Plan de Pago\n"
                + "-------------------------" + "\n"
                + "Cuotas: " + cuotas + "\n"
                + "-------------------------" + "\n"
                + "Interés aplicado: " + porcentajeInteres + "%\n"
                + "Interés total: " + formato.format(interesCalculado) + "\n"
                + "-------------------------" + "\n"
                + "Total: " + formato.format(totalFinanciado) + "\n"
                + "Valor de cada cuota: " + formato.format(valorCuota),
                "Financiación",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        calcularDescuento();
        calcularIVA();
        determinarCondicionIva();
        metodoPago.setVisible(false);
        ventasPanel.setVisible(true);
        if (esCuota) {
            calcularInteresesCobro();
        }
    }//GEN-LAST:event_jButton7ActionPerformed


    private void factXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factXActionPerformed

        txtCuitCliente.setText("0");
        BuscarClienteV();
        noSeleccionFactura();
    }//GEN-LAST:event_factXActionPerformed

    private void btnVentas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas2ActionPerformed
        clickVentas();
        formatearTablaVenta();
    }//GEN-LAST:event_btnVentas2ActionPerformed

    private void btnProductos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos2ActionPerformed
        clickProductos();
    }//GEN-LAST:event_btnProductos2ActionPerformed

    private void btnEstadisticas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticas2ActionPerformed
        administracionPanel.setVisible(false);
        ventasPanel.setVisible(false);
        metodoPago.setVisible(false);
        estadísticaPanel.setVisible(true);

        actualizarEstadisticasVentas();
        mostrarGraficoVentasPorMes();
        mostrarGraficoMetodosPago();
    }//GEN-LAST:event_btnEstadisticas2ActionPerformed

    private void btnInformeClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeClientesActionPerformed
        cambiarColorBotonesEstadisticas(false);

        panelInformePDF.setVisible(false);
        panelInformeClientes.setVisible(true);
        panelInformeStock.setVisible(false);

        cargarKPIsClientes();
        mostrarGraficoClientesTipo();
        mostrarGraficoClientesIVA();
    }//GEN-LAST:event_btnInformeClientesActionPerformed

    private void btnInformeVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeVentasActionPerformed
        cambiarColorBotonesEstadisticas(true);
        panelInformePDF.setVisible(true);
        panelInformeClientes.setVisible(false);
        panelInformeStock.setVisible(false);
    }//GEN-LAST:event_btnInformeVentasActionPerformed

    private void btnEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticasActionPerformed
        clickEstadisticas();

        actualizarEstadisticasVentas();
        mostrarGraficoVentasPorMes();
        mostrarGraficoMetodosPago();
    }//GEN-LAST:event_btnEstadisticasActionPerformed

    private void btnAdministracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracionActionPerformed
        clickAdministracion();
    }//GEN-LAST:event_btnAdministracionActionPerformed

    private void btnAdministracion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracion2ActionPerformed
        clickAdministracion();
    }//GEN-LAST:event_btnAdministracion2ActionPerformed

    private void btnEstadisticas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticas1ActionPerformed
        clickEstadisticas();

        actualizarEstadisticasVentas();
        mostrarGraficoVentasPorMes();
        mostrarGraficoMetodosPago();
    }//GEN-LAST:event_btnEstadisticas1ActionPerformed

    private void btnInformeStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeStockActionPerformed
        cambiarColorBotonesEstadisticas(false);
        panelInformeStock.setVisible(true);
        panelInformePDF.setVisible(false);
    }//GEN-LAST:event_btnInformeStockActionPerformed

    private void btnExportarInformePDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarInformePDFActionPerformed
        JFileChooser selector = new JFileChooser();

        selector.setDialogTitle("Guardar informe PDF");

        selector.setSelectedFile(
                new File("Informe_Estadisticas_Ventas.pdf")
        );

        selector.setFileFilter(
                new FileNameExtensionFilter(
                        "Documento PDF (*.pdf)",
                        "pdf"
                )
        );

        int opcion = selector.showSaveDialog(this);

        if (opcion == JFileChooser.APPROVE_OPTION) {

            try {

                File archivo = selector.getSelectedFile();

                if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
                    archivo = new File(
                            archivo.getAbsolutePath() + ".pdf"
                    );
                }

                GeneradorPDF.generarInformeVentas(
                        archivo,
                        lblCantidadVentas.getText(),
                        lblIngresosTotales.getText(),
                        lblTicketPromedio.getText(),
                        graficoVentas,
                        graficoMetodosPago,
                        generarInterpretacionVentas(),
                        generarInterpretacionMetodosPago()
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Informe exportado correctamente.",
                        "PDF generado",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (DocumentException | IOException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error al generar el PDF:\n" + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

            }

        }
    }//GEN-LAST:event_btnExportarInformePDFActionPerformed

    private void btnExportarInformePDF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarInformePDF1ActionPerformed
        JFileChooser selector = new JFileChooser();

        selector.setDialogTitle(
                "Guardar informe de clientes"
        );

        selector.setSelectedFile(
                new File(
                        "Informe_Estadisticas_Clientes.pdf"
                )
        );

        selector.setFileFilter(
                new FileNameExtensionFilter(
                        "Documento PDF (*.pdf)",
                        "pdf"
                )
        );

        int opcion = selector.showSaveDialog(this);

        if (opcion == JFileChooser.APPROVE_OPTION) {

            try {

                File archivo
                        = selector.getSelectedFile();

                if (!archivo.getName()
                        .toLowerCase()
                        .endsWith(".pdf")) {

                    archivo = new File(
                            archivo.getAbsolutePath()
                            + ".pdf"
                    );
                }

                if (graficoClientesTipo == null
                        || graficoClientesIVA == null) {

                    mostrarGraficoClientesTipo();
                    mostrarGraficoClientesIVA();
                }

                GeneradorPDF.generarInformeClientes(
                        archivo,
                        lblCantidadClientes.getText(),
                        lblCantidadEmpresas.getText(),
                        lblCantidadPersonas.getText(),
                        graficoClientesTipo,
                        graficoClientesIVA,
                        generarInterpretacionClientesTipo(),
                        generarInterpretacionClientesIVA()
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Informe de clientes exportado correctamente."
                );

            } catch (DocumentException | IOException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo generar el informe:\n"
                        + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_btnExportarInformePDF1ActionPerformed

    private void btnEstadisticas3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticas3ActionPerformed
        clickEstadisticas();
    }//GEN-LAST:event_btnEstadisticas3ActionPerformed

    private void btnVentas3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas3ActionPerformed
        clickVentas();
    }//GEN-LAST:event_btnVentas3ActionPerformed

    private void btnAdministracion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracion3ActionPerformed
        clickAdministracion();
    }//GEN-LAST:event_btnAdministracion3ActionPerformed

    private void btnProductos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProductos3ActionPerformed

    private void btnInformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInformesActionPerformed

    private void btnActualizarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarPActionPerformed

    private void btnListasPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListasPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnListasPActionPerformed

    private void btnGestionarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarPActionPerformed
        InicioProdPanel.setVisible(false);
        AccionGestion.setVisible(false);
        GestionProdPanel.setVisible(true);
        btnGestionarP.setBackground(new Color(38, 90, 142));
        cargarTablaProductosP();
    }//GEN-LAST:event_btnGestionarPActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        GestionProdPanel.setVisible(false);
        AccionGestion.setVisible(true);
         lblHeaderAgregar.setVisible(true);
        lblHeaderAgregar1.setVisible(true);
        AGAceptar.setVisible(true);
        AGAceptar.setEnabled(true);
        lblHeaderModificar.setVisible(false);
        lblHeaderModificar1.setVisible(false);
        MGAceptar.setVisible(false);
        MGAceptar.setEnabled(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void GVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GVolverActionPerformed
        AccionGestion.setVisible(false);
        GestionProdPanel.setVisible(true);
    }//GEN-LAST:event_GVolverActionPerformed

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox8ActionPerformed

    private void AGAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AGAceptarActionPerformed

    private void MGAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MGAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MGAceptarActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       GestionProdPanel.setVisible(false);
        AccionGestion.setVisible(true);
        lblHeaderAgregar.setVisible(false);
        lblHeaderAgregar1.setVisible(false);
        AGAceptar.setVisible(false);
        AGAceptar.setEnabled(false);
        lblHeaderModificar.setVisible(true);
        lblHeaderModificar1.setVisible(true);
        MGAceptar.setVisible(true);
        MGAceptar.setEnabled(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void calcularDescuento() {
        Locale localeAR = new Locale("es", "AR");
        NumberFormat formato = NumberFormat.getCurrencyInstance(localeAR);
        MetodoPago mp = (MetodoPago) cbMpVentas.getSelectedItem();
        boolean aplicaDescuento = mp != null
                && (mp.getDescripcion().equals("Efectivo") || mp.getDescripcion().equals("Transferencia"));
        NProducto np = new NProducto();
        descuentoTotal = 0;
        for (DetalleVenta dv : ListaVentas) {
            Producto p = np.select(dv.getIdProducto());
            float precioOriginal = p.getPrecioUnitario();
            float descuentoUnitario = 0;
            float precioFinal = precioOriginal;
            if (aplicaDescuento) {
                descuentoUnitario = precioOriginal * 0.10f;
                precioFinal = precioOriginal - descuentoUnitario;
                descuentoTotal += descuentoUnitario * dv.getCantidad();
            }
            dv.setDescuento(descuentoUnitario);
            dv.setImporte(precioFinal * dv.getCantidad());
        }
        DescuentoV.setText(formato.format(descuentoTotal));
        cargarTablaVenta();
    }

    private void calcularIVA() {
        NProducto np = new NProducto();
        float ivaTotal = 0;
        for (DetalleVenta dv : ListaVentas) {
            Producto p = np.select(dv.getIdProducto());
            float porcentaje = p.getIva().getPorcentaje();
            ivaTotal += dv.getImporte() * porcentaje / 100f;
        }
        ivaCalculado = ivaTotal;
    }

    public void rehacerBuscarProductoV() {
        txtCodV.setText("");
        txtDescV.setText("");
        txtCantV.setText("");
        txtPre1.setText("Precio Unitario");
        txtPre2.setText("Precio Oferta");
        txtIVA.setText("IVA");
        txtStock.setText("Stock");
    }

    private void determinarCondicionIva() {
        CondIVA condCliente = (CondIVA) cbCondIvaMp.getSelectedItem();
        Locale localeAR = new Locale("es", "AR");
        NumberFormat formato = NumberFormat.getCurrencyInstance(localeAR);

        if (condCliente == null) {
            return;
        }

        String desc = condCliente.getDescripcion();
        boolean exento = desc.equals("Exento");
        boolean discrimina = desc.equals("Responsable Inscripto");

        if (exento) {
            totalVenta = subtotalVenta;
            IvaV.setText(formato.format(0));
        } else if (discrimina) {
            totalVenta = subtotalVenta + ivaCalculado;
            IvaV.setText(formato.format(ivaCalculado));
        } else {
            totalVenta = subtotalVenta + ivaCalculado;
            IvaV.setText(formato.format(ivaCalculado));
        }

        TotalV.setText(formato.format(totalVenta));
    }

    /* -- Bruno: Estadisticas Ventas --*/
    private void cambiarColorBotonesEstadisticas(boolean ventasActivo) {

        Color activo = new Color(38, 90, 142);
        Color inactivo = new Color(20, 52, 83);

        if (ventasActivo) {
            btnInformeVentas.setBackground(activo);
            btnInformeStock.setBackground(inactivo);
        } else {
            btnInformeVentas.setBackground(inactivo);
            btnInformeStock.setBackground(activo);
        }
    }

    private void actualizarEstadisticasVentas() {

        try {
            int cantidadVentas
                    = dEstadistica.obtenerCantidadVentas();

            double ingresosTotales
                    = dEstadistica.obtenerIngresosTotales();

            double ticketPromedio
                    = dEstadistica.obtenerTicketPromedio();

            DecimalFormat formatoMoneda
                    = new DecimalFormat("$ #,##0.00");

            lblCantidadVentas.setText(
                    String.valueOf(cantidadVentas)
            );

            lblIngresosTotales.setText(
                    formatoMoneda.format(ingresosTotales)
            );

            lblTicketPromedio.setText(
                    formatoMoneda.format(ticketPromedio)
            );

            DateTimeFormatter formatoFecha
                    = DateTimeFormatter.ofPattern(
                            "dd/MM/yyyy HH:mm:ss"
                    );

            lblUltimaActualizacion.setText(
                    LocalDateTime.now().format(formatoFecha)
            );

            mostrarGraficoVentasPorMes();

        } catch (Exception e) {

            System.out.println(
                    "Error al actualizar estadísticas: "
                    + e.getMessage()
            );
        }
    }

    private void iniciarActualizacionEstadisticas() {

        actualizarEstadisticasVentas();

        timerEstadisticas = new Timer(5000, e -> {

            if (estadísticaPanel.isVisible()
                    && panelInformePDF.isVisible()) {

                actualizarEstadisticasVentas();
            }
        });

        timerEstadisticas.start();
    }

    private void mostrarGraficoVentasPorMes() {

        Map<String, Double> datos = dEstadistica.obtenerVentasPorDia();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Double> dato : datos.entrySet()) {
            dataset.addValue(
                    dato.getValue(),
                    "Ventas",
                    dato.getKey()
            );
        }

        graficoVentas = ChartFactory.createBarChart(
                "Evolución diaria de ventas",
                "Fecha",
                "Ingresos",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        CategoryPlot plot = graficoVentas.getCategoryPlot();

        graficoVentas.setBackgroundPaint(Color.WHITE);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(new Color(220, 220, 220));
        plot.setDomainGridlinesVisible(false);
        plot.setOutlineVisible(false);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(38, 90, 142));
        renderer.setMaximumBarWidth(0.12);
        renderer.setShadowVisible(false);

        NumberFormat formatoMoneda
                = NumberFormat.getCurrencyInstance(
                        new Locale("es", "AR")
                );

        NumberAxis ejeIngresos
                = (NumberAxis) plot.getRangeAxis();

        ejeIngresos.setNumberFormatOverride(formatoMoneda);

        ChartPanel chartPanel = new ChartPanel(graficoVentas, false);

        chartPanel.setBorder(null);

        Dimension tamañoGrafico = new Dimension(494, 322);

        chartPanel.setPreferredSize(tamañoGrafico);
        chartPanel.setMinimumSize(tamañoGrafico);
        chartPanel.setMaximumSize(tamañoGrafico);

        chartPanel.setMinimumDrawWidth(0);
        chartPanel.setMinimumDrawHeight(0);
        chartPanel.setMaximumDrawWidth(451);
        chartPanel.setMaximumDrawHeight(322);

        panelGraficoVentas.removeAll();
        panelGraficoVentas.setLayout(new BorderLayout());

        panelGraficoVentas.setPreferredSize(tamañoGrafico);
        panelGraficoVentas.setMinimumSize(tamañoGrafico);
        panelGraficoVentas.setMaximumSize(tamañoGrafico);

        panelGraficoVentas.add(
                chartPanel,
                BorderLayout.CENTER
        );

        panelGraficoVentas.revalidate();
        panelGraficoVentas.repaint();
    }

    private String generarInterpretacionVentas() {

        Map<String, Double> datos = dEstadistica.obtenerVentasPorDia();

        if (datos.isEmpty()) {
            return "No hay ventas registradas para interpretar.";
        }

        Map.Entry<String, Double> mayor = null;
        Map.Entry<String, Double> menor = null;

        for (Map.Entry<String, Double> dato : datos.entrySet()) {

            if (mayor == null || dato.getValue() > mayor.getValue()) {
                mayor = dato;
            }

            if (menor == null || dato.getValue() < menor.getValue()) {
                menor = dato;
            }
        }

        NumberFormat moneda
                = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));

        return "El día con mayores ingresos fue el "
                + mayor.getKey()
                + ", con "
                + moneda.format(mayor.getValue())
                + ". El menor ingreso se registró el "
                + menor.getKey()
                + ", con "
                + moneda.format(menor.getValue())
                + ".";
    }

    private String generarInterpretacionMetodosPago() {

        Map<String, Integer> datos
                = dEstadistica.obtenerVentasPorMetodoPago();

        if (datos.isEmpty()) {
            return "No hay métodos de pago registrados para interpretar.";
        }

        int total = 0;
        Map.Entry<String, Integer> masUsado = null;
        Map.Entry<String, Integer> menosUsado = null;

        for (Map.Entry<String, Integer> dato : datos.entrySet()) {

            total += dato.getValue();

            if (masUsado == null || dato.getValue() > masUsado.getValue()) {
                masUsado = dato;
            }

            if (menosUsado == null || dato.getValue() < menosUsado.getValue()) {
                menosUsado = dato;
            }
        }

        double porcentajeMayor
                = masUsado.getValue() * 100.0 / total;

        double porcentajeMenor
                = menosUsado.getValue() * 100.0 / total;

        return String.format(
                Locale.forLanguageTag("es-AR"),
                "El método de pago más utilizado fue %s, con %d operaciones "
                + "(%.0f%%). El menos utilizado fue %s, con %d operaciones "
                + "(%.0f%%).",
                masUsado.getKey(),
                masUsado.getValue(),
                porcentajeMayor,
                menosUsado.getKey(),
                menosUsado.getValue(),
                porcentajeMenor
        );
    }

    private void mostrarGraficoMetodosPago() {

        Map<String, Integer> datos
                = dEstadistica.obtenerVentasPorMetodoPago();

        DefaultPieDataset<String> dataset
                = new DefaultPieDataset<>();

        for (Map.Entry<String, Integer> dato : datos.entrySet()) {
            dataset.setValue(
                    dato.getKey(),
                    dato.getValue()
            );
        }

        graficoMetodosPago = ChartFactory.createPieChart(
                "Métodos de pago",
                dataset,
                false,
                true,
                false
        );

        PiePlot<String> plot
                = (PiePlot<String>) graficoMetodosPago.getPlot();

        graficoMetodosPago.setBackgroundPaint(Color.WHITE);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setShadowPaint(null);
        plot.setInteriorGap(0.02);

        plot.setLabelGenerator(
                new StandardPieSectionLabelGenerator(
                        "{0}: {1} ({2})"
                )
        );

        plot.setSectionPaint(
                "Crédito",
                new Color(38, 90, 142)
        );

        plot.setSectionPaint(
                "Efectivo",
                new Color(82, 140, 196)
        );

        plot.setSectionPaint(
                "Débito",
                new Color(163, 198, 226)
        );

        ChartPanel chartPanel
                = new ChartPanel(graficoMetodosPago, false);

        chartPanel.setBorder(null);

        Dimension tamañoGrafico = new Dimension(347, 237);

        chartPanel.setPreferredSize(tamañoGrafico);
        chartPanel.setMinimumSize(tamañoGrafico);
        chartPanel.setMaximumSize(tamañoGrafico);

        chartPanel.setMinimumDrawWidth(0);
        chartPanel.setMinimumDrawHeight(0);
        chartPanel.setMaximumDrawWidth(465);
        chartPanel.setMaximumDrawHeight(322);

        panelGraficoMetodos.removeAll();
        panelGraficoMetodos.setLayout(new BorderLayout());

        panelGraficoMetodos.setPreferredSize(tamañoGrafico);
        panelGraficoMetodos.setMinimumSize(tamañoGrafico);
        panelGraficoMetodos.setMaximumSize(tamañoGrafico);

        panelGraficoMetodos.add(
                chartPanel,
                BorderLayout.CENTER
        );

        panelGraficoMetodos.revalidate();
        panelGraficoMetodos.repaint();
    }

    /* -- Bruno: Estadisticas Clientes -- */
    private void cargarKPIsClientes() {

        DEstadistica dEstadistica = new DEstadistica();

        lblCantidadClientes.setText(
                String.valueOf(
                        dEstadistica.obtenerTotalClientes()
                )
        );

        lblCantidadEmpresas.setText(
                String.valueOf(
                        dEstadistica.obtenerCantidadEmpresas()
                )
        );

        lblCantidadPersonas.setText(
                String.valueOf(
                        dEstadistica.obtenerCantidadPersonas()
                )
        );

        lblUltimaActualizacion1.setText(
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern(
                                "dd/MM/yyyy HH:mm:ss"
                        )
                )
        );
    }

    private void mostrarGraficoClientesTipo() {

        Map<String, Integer> datos
                = dEstadistica.obtenerClientesPorTipo();

        DefaultPieDataset<String> dataset
                = new DefaultPieDataset<>();

        for (Map.Entry<String, Integer> dato : datos.entrySet()) {

            dataset.setValue(
                    dato.getKey(),
                    dato.getValue()
            );
        }

        graficoClientesTipo = ChartFactory.createPieChart(
                "Clientes por tipo",
                dataset,
                false,
                true,
                false
        );

        PiePlot<String> plot
                = (PiePlot<String>) graficoClientesTipo.getPlot();

        graficoClientesTipo.setBackgroundPaint(Color.WHITE);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setShadowPaint(null);
        plot.setInteriorGap(0.02);

        plot.setLabelGenerator(
                new StandardPieSectionLabelGenerator(
                        "{0}: {1} ({2})"
                )
        );

        plot.setSectionPaint(
                "Persona",
                new Color(38, 90, 142)
        );

        plot.setSectionPaint(
                "Empresa",
                new Color(82, 140, 196)
        );

        ChartPanel chartPanel
                = new ChartPanel(graficoClientesTipo, false);

        chartPanel.setBorder(null);

        Dimension tamañoGrafico
                = new Dimension(347, 237);

        chartPanel.setPreferredSize(tamañoGrafico);
        chartPanel.setMinimumSize(tamañoGrafico);
        chartPanel.setMaximumSize(tamañoGrafico);

        chartPanel.setMinimumDrawWidth(0);
        chartPanel.setMinimumDrawHeight(0);
        chartPanel.setMaximumDrawWidth(465);
        chartPanel.setMaximumDrawHeight(322);

        panelGraficoClientesTipo.removeAll();
        panelGraficoClientesTipo.setLayout(
                new BorderLayout()
        );

        panelGraficoClientesTipo.setPreferredSize(
                tamañoGrafico
        );

        panelGraficoClientesTipo.setMinimumSize(
                tamañoGrafico
        );

        panelGraficoClientesTipo.setMaximumSize(
                tamañoGrafico
        );

        panelGraficoClientesTipo.add(
                chartPanel,
                BorderLayout.CENTER
        );

        panelGraficoClientesTipo.revalidate();
        panelGraficoClientesTipo.repaint();
    }

    private void mostrarGraficoClientesIVA() {

        Map<String, Integer> datos
                = dEstadistica.obtenerClientesPorCondicionIVA();

        DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> dato : datos.entrySet()) {

            dataset.addValue(
                    dato.getValue(),
                    "Clientes",
                    dato.getKey()
            );
        }

        graficoClientesIVA = ChartFactory.createBarChart(
                "Clientes por condición IVA",
                "Condición IVA",
                "Cantidad",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        CategoryPlot plot
                = graficoClientesIVA.getCategoryPlot();

        graficoClientesIVA.setBackgroundPaint(
                Color.WHITE
        );

        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(
                new Color(220, 220, 220)
        );

        plot.setDomainGridlinesVisible(false);
        plot.setOutlineVisible(false);

        BarRenderer renderer
                = (BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(
                0,
                new Color(38, 90, 142)
        );

        renderer.setMaximumBarWidth(0.15);
        renderer.setShadowVisible(false);

        NumberAxis ejeCantidad
                = (NumberAxis) plot.getRangeAxis();

        ejeCantidad.setStandardTickUnits(
                NumberAxis.createIntegerTickUnits()
        );

        ChartPanel chartPanel
                = new ChartPanel(graficoClientesIVA, false);

        chartPanel.setBorder(null);

        Dimension tamañoGrafico
                = new Dimension(494, 322);

        chartPanel.setPreferredSize(tamañoGrafico);
        chartPanel.setMinimumSize(tamañoGrafico);
        chartPanel.setMaximumSize(tamañoGrafico);

        chartPanel.setMinimumDrawWidth(0);
        chartPanel.setMinimumDrawHeight(0);
        chartPanel.setMaximumDrawWidth(494);
        chartPanel.setMaximumDrawHeight(322);

        panelGraficoClientesIVA.removeAll();

        panelGraficoClientesIVA.setLayout(
                new BorderLayout()
        );

        panelGraficoClientesIVA.setPreferredSize(
                tamañoGrafico
        );

        panelGraficoClientesIVA.setMinimumSize(
                tamañoGrafico
        );

        panelGraficoClientesIVA.setMaximumSize(
                tamañoGrafico
        );

        panelGraficoClientesIVA.add(
                chartPanel,
                BorderLayout.CENTER
        );

        panelGraficoClientesIVA.revalidate();
        panelGraficoClientesIVA.repaint();
    }

    private String generarInterpretacionClientesTipo() {

        Map<String, Integer> datos
                = dEstadistica.obtenerClientesPorTipo();

        if (datos.isEmpty()) {
            return "No hay clientes registrados para interpretar.";
        }

        int total = 0;
        Map.Entry<String, Integer> mayor = null;
        Map.Entry<String, Integer> menor = null;

        for (Map.Entry<String, Integer> dato : datos.entrySet()) {

            total += dato.getValue();

            if (mayor == null || dato.getValue() > mayor.getValue()) {
                mayor = dato;
            }

            if (menor == null || dato.getValue() < menor.getValue()) {
                menor = dato;
            }
        }

        double porcentajeMayor
                = mayor.getValue() * 100.0 / total;

        double porcentajeMenor
                = menor.getValue() * 100.0 / total;

        return String.format(
                Locale.forLanguageTag("es-AR"),
                "El tipo de cliente predominante es %s, con %d registros "
                + "(%.0f%%). El tipo menos frecuente es %s, con %d registros "
                + "(%.0f%%).",
                mayor.getKey(),
                mayor.getValue(),
                porcentajeMayor,
                menor.getKey(),
                menor.getValue(),
                porcentajeMenor
        );
    }

    private String generarInterpretacionClientesIVA() {

        Map<String, Integer> datos
                = dEstadistica.obtenerClientesPorCondicionIVA();

        if (datos.isEmpty()) {
            return "No hay condiciones de IVA registradas para interpretar.";
        }

        int total = 0;
        Map.Entry<String, Integer> mayor = null;
        Map.Entry<String, Integer> menor = null;

        for (Map.Entry<String, Integer> dato : datos.entrySet()) {

            total += dato.getValue();

            if (mayor == null || dato.getValue() > mayor.getValue()) {
                mayor = dato;
            }

            if (menor == null || dato.getValue() < menor.getValue()) {
                menor = dato;
            }
        }

        double porcentajeMayor
                = mayor.getValue() * 100.0 / total;

        double porcentajeMenor
                = menor.getValue() * 100.0 / total;

        return String.format(
                Locale.forLanguageTag("es-AR"),
                "La condición de IVA más frecuente es %s, con %d clientes "
                + "(%.0f%%). La menos frecuente es %s, con %d clientes "
                + "(%.0f%%).",
                mayor.getKey(),
                mayor.getValue(),
                porcentajeMayor,
                menor.getKey(),
                menor.getValue(),
                porcentajeMenor
        );
    }

    /*------------------------------------*/
    private void cbNombreActionPerformed(java.awt.event.ActionEvent evt) {
        // Método vacío para el evento del combo cbNombre
    }

    private void txtUnidadCuotaActionPerformed(java.awt.event.ActionEvent evt) {
        // Método vacío para el evento del campo txtUnidadCuota
    }

    private void cargarTablaProductosP() {
        Locale localeAR = new Locale("es", "AR");
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(localeAR);
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Código");
        modelo.addColumn("Descripción");
        modelo.addColumn("Rubro");
        modelo.addColumn("Tipo");
        modelo.addColumn("Stock");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Proveedor");

        NProducto np = new NProducto();

        for (Producto p : np.listar()) {
            modelo.addRow(new Object[]{
                p.getCodigo(),
                p.getDescripcion(),
                p.getRubroProducto().getDescripcion(),
                p.getTipoProducto().getDescripcion(),
                p.getIdStockProducto().getStockAct(),
                formatoMoneda.format(p.getPrecioUnitario()),
                p.getProveedor().getNombre()
            });
        }

        tablaProductosP.setModel(modelo);
        formatearTablaProductosP();
    }
    private void formatearTablaProductosP(){
        tablaProductosP.setRowSorter(null);
        tablaProductosP.getColumnModel().getColumn(0).setPreferredWidth(70);
        tablaProductosP.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaProductosP.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaProductosP.getColumnModel().getColumn(3).setPreferredWidth(150);
        tablaProductosP.getColumnModel().getColumn(4).setPreferredWidth(50);
        tablaProductosP.getColumnModel().getColumn(5).setPreferredWidth(120);
        tablaProductosP.getColumnModel().getColumn(6).setPreferredWidth(180);
        tablaProductosP.setRowHeight(30);
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        tablaProductosP.getColumnModel().getColumn(0).setCellRenderer(centro);
        tablaProductosP.getColumnModel().getColumn(1).setCellRenderer(centro);
        tablaProductosP.getColumnModel().getColumn(2).setCellRenderer(centro);
        tablaProductosP.getColumnModel().getColumn(3).setCellRenderer(centro);
        tablaProductosP.getColumnModel().getColumn(4).setCellRenderer(centro);
        tablaProductosP.getColumnModel().getColumn(5).setCellRenderer(centro);
        tablaProductosP.getColumnModel().getColumn(6).setCellRenderer(centro);
        tablaProductosP.setAutoResizeMode(tablaProductosP.AUTO_RESIZE_OFF);
        tablaProductosP.getTableHeader().setResizingAllowed(false);
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new PLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AGAceptar;
    private javax.swing.JPanel AccionGestion;
    private javax.swing.JButton AgregarCliMP;
    private javax.swing.JButton AgregarProdBP;
    private javax.swing.JButton AgregarV;
    private javax.swing.JPanel BtnDetalleCC;
    private javax.swing.JPanel BtnsADM;
    private javax.swing.JPanel BtnsVNT1;
    private javax.swing.JButton BuscarCliMP;
    private javax.swing.JButton BuscarDescV;
    private javax.swing.JButton BuscarProdBP;
    private javax.swing.JButton BuscarProdBPDesc;
    private javax.swing.JButton BuscarV;
    private javax.swing.JButton ConfirmarVenta;
    private javax.swing.JButton DatosCobroVenta;
    private javax.swing.JPanel DatosProdAPanel;
    private javax.swing.JLabel DescuentoV;
    private javax.swing.JButton EliminarItemV;
    private javax.swing.JButton GVolver;
    private javax.swing.JPanel GestionProdPanel;
    private javax.swing.JPanel IVAPanel;
    private javax.swing.JPanel InicioProdPanel;
    private javax.swing.JLabel IvaV;
    private javax.swing.JButton LimpiarFiltroBP;
    private javax.swing.JButton LimpiarMp;
    private javax.swing.JPanel ListaPanel;
    private javax.swing.JButton MGAceptar;
    private javax.swing.JFrame PBuscarProductoV;
    private javax.swing.JPanel PagosPanel;
    private javax.swing.JPanel PagosPanelCampos;
    private javax.swing.JPanel PanelBotones;
    private javax.swing.JPanel PanelCalculo;
    private javax.swing.JButton RehacerV;
    private javax.swing.JButton RehacerVenta;
    private javax.swing.JScrollPane ScrollDetalle;
    private javax.swing.JLabel SubtotalV;
    private javax.swing.JLabel TotalV;
    private javax.swing.JPanel actPanel;
    private javax.swing.JPanel actPanel1;
    private javax.swing.JPanel administracionPanel;
    private javax.swing.JPanel autEmp;
    private javax.swing.JPanel autPers;
    private javax.swing.JButton btCalcular;
    private javax.swing.JButton btCalcularCuotas;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btnABorrar;
    private javax.swing.JButton btnABuscar;
    private javax.swing.JButton btnABuscar3;
    private javax.swing.JButton btnAClientes;
    private javax.swing.JButton btnACobrar;
    private javax.swing.JButton btnAContinuar;
    private javax.swing.JButton btnAContinuar3;
    private javax.swing.JButton btnAContinuar4;
    private javax.swing.JButton btnADetalleCC;
    private javax.swing.JButton btnAEmpresa;
    private javax.swing.JButton btnAExportar;
    private javax.swing.JButton btnAGestionarCC;
    private javax.swing.JButton btnAListas;
    private javax.swing.JButton btnAModificar;
    private javax.swing.JButton btnAPagos;
    private javax.swing.JButton btnAPersona;
    private javax.swing.JButton btnAVolver;
    private javax.swing.JButton btnActualizarP;
    private javax.swing.JButton btnAdministracion;
    private javax.swing.JButton btnAdministracion1;
    private javax.swing.JButton btnAdministracion2;
    private javax.swing.JButton btnAdministracion3;
    private javax.swing.JButton btnCaja;
    private javax.swing.JButton btnCaja1;
    private javax.swing.JButton btnCaja2;
    private javax.swing.JButton btnCaja3;
    private javax.swing.JButton btnEstadisticas;
    private javax.swing.JButton btnEstadisticas1;
    private javax.swing.JButton btnEstadisticas2;
    private javax.swing.JButton btnEstadisticas3;
    private javax.swing.JButton btnExportarInformePDF;
    private javax.swing.JButton btnExportarInformePDF1;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnGestionarP;
    private javax.swing.JButton btnInformeClientes;
    private javax.swing.JButton btnInformeStock;
    private javax.swing.JButton btnInformeVentas;
    private javax.swing.JButton btnInformes;
    private javax.swing.JButton btnInformes1;
    private javax.swing.JButton btnInformes2;
    private javax.swing.JButton btnInformes3;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnListasP;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProductos1;
    private javax.swing.JButton btnProductos2;
    private javax.swing.JButton btnProductos3;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnProveedores1;
    private javax.swing.JButton btnProveedores2;
    private javax.swing.JButton btnProveedores3;
    private javax.swing.JButton btnVentas;
    private javax.swing.JButton btnVentas1;
    private javax.swing.JButton btnVentas2;
    private javax.swing.JButton btnVentas3;
    private javax.swing.JPanel btnsGestion;
    private javax.swing.JPanel buscarPanel;
    private javax.swing.JButton cancelarMP1;
    private javax.swing.JPanel cantidadPanel;
    private javax.swing.JComboBox<CondIVA> cbCondIva;
    private javax.swing.JComboBox<CondIVA> cbCondIvaMp;
    private javax.swing.JCheckBox cbDescuento;
    private javax.swing.JComboBox<String> cbMetodosPagos;
    private javax.swing.JComboBox<MetodoPago> cbMpVentas;
    private javax.swing.JComboBox<String> cbNombre;
    private javax.swing.JComboBox<RubroProducto> cbRubroV;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JComboBox<TipoProducto> cbTipoV;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JPanel detPanel;
    private javax.swing.JPanel estadísticaPanel;
    private javax.swing.JPanel evolucionPanel;
    private javax.swing.JButton factA;
    private javax.swing.JButton factB;
    private javax.swing.JButton factX;
    private javax.swing.JPanel globGestion;
    private javax.swing.JPanel ingresosPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel labelFact;
    private javax.swing.JLabel lblCC;
    private javax.swing.JLabel lblCantClientes;
    private javax.swing.JLabel lblCantEmpresas;
    private javax.swing.JLabel lblCantPersonas;
    private javax.swing.JLabel lblCantVentas;
    private javax.swing.JLabel lblCantidadClientes;
    private javax.swing.JLabel lblCantidadEmpresas;
    private javax.swing.JLabel lblCantidadPersonas;
    private javax.swing.JLabel lblCantidadVentas;
    private javax.swing.JLabel lblFact;
    private javax.swing.JLabel lblHeaderAgregar;
    private javax.swing.JLabel lblHeaderAgregar1;
    private javax.swing.JLabel lblHeaderModificar;
    private javax.swing.JLabel lblHeaderModificar1;
    private javax.swing.JLabel lblIngTotales;
    private javax.swing.JLabel lblIngresosTotales;
    private javax.swing.JLabel lblTicketProm;
    private javax.swing.JLabel lblTicketPromedio;
    private javax.swing.JLabel lblTituloInformeVentas;
    private javax.swing.JLabel lblTituloInformeVentas1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblUltimaAct;
    private javax.swing.JLabel lblUltimaAct1;
    private javax.swing.JLabel lblUltimaActualizacion;
    private javax.swing.JLabel lblUltimaActualizacion1;
    private javax.swing.JLabel lblimg;
    private javax.swing.JLabel lblimg1;
    private javax.swing.JLabel lblimg2;
    private javax.swing.JLabel lblimg3;
    private javax.swing.JPanel listaGestion;
    private javax.swing.JPanel listaPanel;
    private javax.swing.JPanel metodoPago;
    private javax.swing.JPanel metodosPanel;
    private javax.swing.JPanel navbarADM;
    private javax.swing.JPanel navbarVNT;
    private javax.swing.JPanel panelCli;
    private javax.swing.JPanel panelContenidoEstadisticas;
    private javax.swing.JPanel panelFact;
    private javax.swing.JPanel panelGraficoClientesIVA;
    private javax.swing.JPanel panelGraficoClientesTipo;
    private javax.swing.JPanel panelGraficoMetodos;
    private javax.swing.JPanel panelGraficoVentas;
    private javax.swing.JPanel panelInformeClientes;
    private javax.swing.JPanel panelInformePDF;
    private javax.swing.JPanel panelInformeStock;
    private javax.swing.JPanel productosPanel;
    private javax.swing.JPanel promedioPanel;
    private javax.swing.JPanel regEmp;
    private javax.swing.JPanel resumenPanel;
    private javax.swing.JTable tablaLista;
    private javax.swing.JTable tablaProductosP;
    private javax.swing.JTable tablaProductosV;
    private javax.swing.JTable tablaVenta;
    private javax.swing.JPanel tarjetaDetalle;
    private javax.swing.JPanel tipoPanel;
    private javax.swing.JPanel totClientesPanel;
    private javax.swing.JPanel totEmpresasPanel;
    private javax.swing.JPanel totPersonasPanel;
    private javax.swing.JTextField txtCantV;
    private javax.swing.JTextField txtCodV;
    private javax.swing.JTextField txtComprasTotales;
    private javax.swing.JTextField txtCuil;
    private javax.swing.JTextField txtCuit;
    private javax.swing.JTextField txtCuitCliente;
    private javax.swing.JTextField txtCuotas;
    private javax.swing.JTextField txtCuotasPagos;
    private javax.swing.JTextField txtDNIPago;
    private javax.swing.JTextField txtDNIRetiro;
    private javax.swing.JTextField txtDNIRetiro1;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtDescBP;
    private javax.swing.JTextField txtDescV;
    private javax.swing.JTextField txtDom;
    private javax.swing.JTextField txtDomCliente;
    private javax.swing.JTextField txtFiltroCuit;
    private javax.swing.JTextField txtFiltroRS;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtInteres;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JLabel txtNomCC;
    private javax.swing.JTextField txtNomPago;
    private javax.swing.JTextField txtNomRetiro;
    private javax.swing.JTextField txtNomRetiro1;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtPre1;
    private javax.swing.JTextField txtPre2;
    private javax.swing.JTextField txtRS;
    private javax.swing.JLabel txtSaldo1;
    private javax.swing.JLabel txtSaldoT;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTel;
    private javax.swing.JTextField txtUnidadCuota;
    private javax.swing.JPanel ventasPanel;
    private javax.swing.JPanel verDetallePanel;
    // End of variables declaration//GEN-END:variables
}
