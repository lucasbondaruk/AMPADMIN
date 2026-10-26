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
import Datos.DStockProducto;
import Modelos.MNegocio;
import Modelos.Proveedor;
import Modelos.StockProducto;
import Negocio.NOferta;
import Negocio.NPromocion;
import Negocio.NProveedor;
import Negocio.NStockProducto;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator; // Para gráficos de categorías (ej. Barras)
import org.jfree.chart.labels.StandardXYItemLabelGenerator;       // Para gráficos XY (ej. Dispersión)
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
import java.util.Comparator;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import Modelos.Rol;
import Negocio.NNegocio;
import Negocio.NRol;
import Negocio.NUsuario;
import javax.swing.ListSelectionModel;

public class PPanelAMP extends javax.swing.JFrame {

    private Cliente actual = null;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PPanelAMP.class.getName());
    private NDetalleVenta nDetalleVenta = new NDetalleVenta();
    private NProducto nProducto = new NProducto();
    DecimalFormat df = new DecimalFormat("#,##0.00");
    private Usuario usuarioLogueado;
    private Cliente clienteVenta;
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private int idSeleccionadoUsuario = 0;
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
    private Proveedor proveedorSeleccionado;
    private MNegocio n;

    public PPanelAMP(Usuario usuarioLogueado) {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/img/ampadmin_icon_64.png")).getImage());
        this.usuarioLogueado = usuarioLogueado;
        setLocationRelativeTo(null);
        iniciarPaneles();
        opc = false;
        cargarCbCondIva();
        cargarClientes();
        cargarMetodosPago();
        revalidate();
        repaint();
        actualizarGrafico();
        inicializarPaneles();
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
        InicioPanel = new javax.swing.JPanel();
        navbarADMInicio = new javax.swing.JPanel();
        lblimg4 = new javax.swing.JLabel();
        btnInformes = new javax.swing.JButton();
        btnCaja4 = new javax.swing.JButton();
        btnProveedores4 = new javax.swing.JButton();
        btnAdministracion4 = new javax.swing.JButton();
        btnVentas4 = new javax.swing.JButton();
        btnEstadisticas4 = new javax.swing.JButton();
        btnProductos4 = new javax.swing.JButton();
        barritaEncabezado = new javax.swing.JPanel();
        PanelInicioPresentacion = new javax.swing.JPanel();
        imgAmp1 = new javax.swing.JLabel();
        LeyendaAMPinicio = new javax.swing.JLabel();
        AmpLogo2 = new javax.swing.JLabel();
        btnConfiguracion = new javax.swing.JButton();
        PanelConfiguracion = new javax.swing.JPanel();
        PanelConfigSeleccion = new javax.swing.JPanel();
        btnAccesos = new javax.swing.JButton();
        btnMiNegocio = new javax.swing.JButton();
        btnPersonal = new javax.swing.JButton();
        btnRoles = new javax.swing.JButton();
        btnVolverAInicio = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        PanelConfigMiNegocio = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTelEmpresa = new javax.swing.JTextField();
        txtNomEmpresa = new javax.swing.JTextField();
        txtDomEmpresa = new javax.swing.JTextField();
        txtProvEmpresa = new javax.swing.JTextField();
        btnGuardarEmpresa = new javax.swing.JButton();
        btnVolverASeleccionC = new javax.swing.JButton();
        PanelConfigPersonal = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnVolverASeleccionC1 = new javax.swing.JButton();
        DatosEmpleado = new javax.swing.JPanel();
        txtNomUsuario = new javax.swing.JTextField();
        txtTelUsuario = new javax.swing.JTextField();
        txtDomUsuario = new javax.swing.JTextField();
        txtMailUsuario = new javax.swing.JTextField();
        cbRolesPersonal = new javax.swing.JComboBox<>();
        btnGuardarUsuario = new javax.swing.JButton();
        btnModificarUsuario = new javax.swing.JButton();
        txtClaveUsuario = new javax.swing.JPasswordField();
        HistorialEmpleados = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaMiPersonal = new javax.swing.JTable();
        btnEliminarPersonal = new javax.swing.JButton();
        administracionPanel = new javax.swing.JPanel();
        navbarADM = new javax.swing.JPanel();
        lblimg = new javax.swing.JLabel();
        btnInformes4 = new javax.swing.JButton();
        btnCaja = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnAdministracion = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnEstadisticas = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        InicioAdmPanel = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        GestionAdmPanel = new javax.swing.JPanel();
        regEmp = new javax.swing.JPanel();
        txtCuit = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        txtTel = new javax.swing.JTextField();
        txtDom = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDate = new com.toedter.calendar.JDateChooser();
        txtRS = new javax.swing.JTextField();
        cbCondIva = new javax.swing.JComboBox<>();
        PanelOpcAdm = new javax.swing.JPanel();
        btnAPersona = new javax.swing.JButton();
        btnAEmpresa = new javax.swing.JButton();
        btnAContinuar = new javax.swing.JButton();
        btnABuscar = new javax.swing.JButton();
        btnAModificar = new javax.swing.JButton();
        btnABorrar = new javax.swing.JButton();
        ListaPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListaCC = new javax.swing.JTable();
        PanelFiltroListasAdm = new javax.swing.JPanel();
        txtFiltroRS = new javax.swing.JTextField();
        txtFiltroCuit = new javax.swing.JTextField();
        cbTipo = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        btnFiltrar = new javax.swing.JButton();
        btnAGestionarCC = new javax.swing.JButton();
        btnADetalleCC = new javax.swing.JButton();
        btnAContinuar3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
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
        jPanel5 = new javax.swing.JPanel();
        btnAClientes = new javax.swing.JButton();
        btnAPagos = new javax.swing.JButton();
        btnAListas = new javax.swing.JButton();
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
        buscarPanel = new javax.swing.JPanel();
        txtDescV = new javax.swing.JTextField();
        BuscarDescV = new javax.swing.JButton();
        RehacerV = new javax.swing.JButton();
        BuscarV = new javax.swing.JButton();
        AgregarV = new javax.swing.JButton();
        txtCantV = new javax.swing.JTextField();
        txtCodV = new javax.swing.JTextField();
        lblFact = new javax.swing.JLabel();
        lblBuscarProd = new javax.swing.JLabel();
        resumenPanel = new javax.swing.JPanel();
        TotalV = new javax.swing.JLabel();
        SubtotalV = new javax.swing.JLabel();
        DescuentoV = new javax.swing.JLabel();
        IvaV = new javax.swing.JLabel();
        RehacerVenta = new javax.swing.JButton();
        DatosCobroVenta = new javax.swing.JButton();
        EliminarItemV = new javax.swing.JButton();
        ConfirmarVenta = new javax.swing.JButton();
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
        GestionProdPanel = new javax.swing.JPanel();
        btnsGestion = new javax.swing.JPanel();
        NuevoP = new javax.swing.JButton();
        ModificarP = new javax.swing.JButton();
        EliminarP = new javax.swing.JButton();
        globGestion = new javax.swing.JPanel();
        lblTotalProveedoresP = new javax.swing.JLabel();
        lblTotalProductosP = new javax.swing.JLabel();
        lblStockTotalP = new javax.swing.JLabel();
        btnBajoStockP = new javax.swing.JButton();
        btnEliminadosP = new javax.swing.JButton();
        btnSinStockP = new javax.swing.JButton();
        listaGestion = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaProductosP = new javax.swing.JTable();
        txtDescripcionPP = new javax.swing.JTextField();
        txtCodigoPP = new javax.swing.JTextField();
        cbRubroP1 = new javax.swing.JComboBox<>();
        cbStockP = new javax.swing.JComboBox<>();
        btnLimpiarFiltroProducto = new javax.swing.JButton();
        btnFiltrarProducto = new javax.swing.JButton();
        AccionGestion = new javax.swing.JPanel();
        lblHeaderAgregar = new javax.swing.JLabel();
        lblHeaderAgregar1 = new javax.swing.JLabel();
        DatosProdAPanel = new javax.swing.JPanel();
        txtPrecioP = new javax.swing.JTextField();
        txtCodP = new javax.swing.JTextField();
        cbProveedorP = new javax.swing.JComboBox<>();
        cbIVAP = new javax.swing.JComboBox<>();
        cbRubroP = new javax.swing.JComboBox<>();
        cbTipoP = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDescP = new javax.swing.JTextArea();
        jPanel11 = new javax.swing.JPanel();
        txtStockMinP = new javax.swing.JTextField();
        txtStockMaxP = new javax.swing.JTextField();
        txtStockActP = new javax.swing.JTextField();
        GVolver = new javax.swing.JButton();
        AGAceptar = new javax.swing.JButton();
        lblHeaderModificar1 = new javax.swing.JLabel();
        lblHeaderModificar = new javax.swing.JLabel();
        MGAceptar = new javax.swing.JButton();
        ListasPPanel = new javax.swing.JPanel();
        NAVPAGOS = new javax.swing.JPanel();
        btnAOfertas = new javax.swing.JButton();
        btnAPromociones = new javax.swing.JButton();
        PanelPromociones = new javax.swing.JPanel();
        PanelNuevaPromo = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        EstadodePromo = new javax.swing.JToggleButton();
        txtPromocion = new javax.swing.JTextField();
        txtDescuento = new javax.swing.JTextField();
        btGuardarPromo = new javax.swing.JButton();
        txtTipoCondicion = new javax.swing.JTextField();
        btActivarPromo = new javax.swing.JButton();
        btDesactivarPromo1 = new javax.swing.JButton();
        PanelPromoExistente = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        tablePromo = new javax.swing.JScrollPane();
        tblPromo = new javax.swing.JTable();
        PanelOfertas = new javax.swing.JPanel();
        PanelNuevaOferta = new javax.swing.JPanel();
        cbProductoOferta = new javax.swing.JComboBox<>();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        PrecioNuevo = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        FechaInicioOferta = new com.toedter.calendar.JDateChooser();
        jLabel66 = new javax.swing.JLabel();
        FechaFinOferta = new com.toedter.calendar.JDateChooser();
        jLabel67 = new javax.swing.JLabel();
        btGuardarOferta = new javax.swing.JButton();
        btPrevisualizarPrecio = new javax.swing.JButton();
        btLimpiarOferta = new javax.swing.JButton();
        PanelOfertaExistente = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        tableOferta = new javax.swing.JScrollPane();
        tblOferta = new javax.swing.JTable();
        txtPrecioOferta = new javax.swing.JLabel();
        btImprimirOferta = new javax.swing.JButton();
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
        panelGraficoStockInforme = new javax.swing.JPanel();
        btnExportarStockPdf = new javax.swing.JButton();
        cbTipoReporteStock = new javax.swing.JComboBox<>();
        btnActualizarInformeStock = new javax.swing.JButton();
        btnExportarStockExcel = new javax.swing.JButton();
        lblTituloInformeStock = new javax.swing.JLabel();
        lblDescripcionReporte = new javax.swing.JLabel();
        InicioEstPanel = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        proveedoresPanel = new javax.swing.JPanel();
        lblImgProveedores = new javax.swing.JLabel();
        btnInformesProveedores = new javax.swing.JButton();
        btnCajaProveedores = new javax.swing.JButton();
        btnSeccionProveedores = new javax.swing.JButton();
        btnAdministracionProveedores = new javax.swing.JButton();
        btnVentasProveedores = new javax.swing.JButton();
        btnEstadisticasProveedores = new javax.swing.JButton();
        btnProductosProveedores = new javax.swing.JButton();
        panelListaProveedores = new javax.swing.JPanel();
        scrollTablaProveedores = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();
        AccionesProveedores = new javax.swing.JPanel();
        btnRegistrarProveedor = new javax.swing.JButton();
        btnModificarProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        btnExportarProveedores = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        txtBuscarRazonSocial = new javax.swing.JTextField();
        txtBuscarRubro = new javax.swing.JTextField();
        cbBuscarEstado = new javax.swing.JComboBox<>();
        btnBuscarProveedor = new javax.swing.JButton();
        btnLimpiarFiltrosProveedor = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        panelRegistrarProveedor = new javax.swing.JPanel();
        lblNuevoProveedorR = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        panelDatosProveedorRegistro = new javax.swing.JPanel();
        txtNombreProveedor = new javax.swing.JTextField();
        txtCuitProveedor = new javax.swing.JTextField();
        txtTelefonoProveedor = new javax.swing.JTextField();
        txtEmailProveedor = new javax.swing.JTextField();
        txtDireccionProveedor = new javax.swing.JTextField();
        txtRubroProveedor = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        btnCancelarRegistroProveedor = new javax.swing.JButton();
        btnConfirmarRegistroProveedor = new javax.swing.JButton();
        lblNP2 = new javax.swing.JLabel();
        panelModificarProveedor = new javax.swing.JPanel();
        lblTituloModificarProveedor = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        panelDatosProveedorModificar = new javax.swing.JPanel();
        txtNombreModificarProveedor = new javax.swing.JTextField();
        txtCuitModificarProveedor = new javax.swing.JTextField();
        txtTelefonoModificarProveedor = new javax.swing.JTextField();
        btnGuardarModificacionProveedor = new javax.swing.JButton();
        btnCancelarModificacionProveedor = new javax.swing.JButton();
        txtEmailModificarProveedor = new javax.swing.JTextField();
        txtDireccionModificarProveedor = new javax.swing.JTextField();
        txtRubroModificarProveedor = new javax.swing.JTextField();
        iconmp324 = new javax.swing.JLabel();

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
        setTitle("AMPADMIN");
        setMinimumSize(new java.awt.Dimension(991, 777));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InicioPanel.setBackground(new java.awt.Color(255, 255, 255));
        InicioPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navbarADMInicio.setBackground(new java.awt.Color(225, 250, 255));
        navbarADMInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblimg4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NAVBAR.png"))); // NOI18N
        lblimg4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblimg4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblimg4MouseClicked(evt);
            }
        });
        navbarADMInicio.add(lblimg4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnInformes.setBackground(new java.awt.Color(204, 255, 255));
        btnInformes.setForeground(new java.awt.Color(0, 51, 102));
        btnInformes.setText("Informes");
        btnInformes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInformes.setBorderPainted(false);
        btnInformes.setContentAreaFilled(false);
        btnInformes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInformes.addActionListener(this::btnInformes4ActionPerformed);
        navbarADMInicio.add(btnInformes, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 90, 50));

        btnCaja4.setBackground(new java.awt.Color(204, 255, 255));
        btnCaja4.setForeground(new java.awt.Color(0, 51, 102));
        btnCaja4.setText("Caja");
        btnCaja4.setBorderPainted(false);
        btnCaja4.setContentAreaFilled(false);
        btnCaja4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navbarADMInicio.add(btnCaja4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 100, 50));

        btnProveedores4.setBackground(new java.awt.Color(204, 255, 255));
        btnProveedores4.setForeground(new java.awt.Color(0, 51, 102));
        btnProveedores4.setText("Proveedores");
        btnProveedores4.setBorderPainted(false);
        btnProveedores4.setContentAreaFilled(false);
        btnProveedores4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedores4.addActionListener(this::btnProveedores4ActionPerformed);
        navbarADMInicio.add(btnProveedores4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 120, 50));

        btnAdministracion4.setBackground(new java.awt.Color(204, 255, 255));
        btnAdministracion4.setForeground(new java.awt.Color(0, 51, 102));
        btnAdministracion4.setText("Administración");
        btnAdministracion4.setBorderPainted(false);
        btnAdministracion4.setContentAreaFilled(false);
        btnAdministracion4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdministracion4.addActionListener(this::btnAdministracion4ActionPerformed);
        navbarADMInicio.add(btnAdministracion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 120, 50));

        btnVentas4.setBackground(new java.awt.Color(204, 255, 255));
        btnVentas4.setForeground(new java.awt.Color(0, 51, 102));
        btnVentas4.setText("Ventas");
        btnVentas4.setBorderPainted(false);
        btnVentas4.setContentAreaFilled(false);
        btnVentas4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas4.addActionListener(this::btnVentas4ActionPerformed);
        navbarADMInicio.add(btnVentas4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 90, 50));

        btnEstadisticas4.setBackground(new java.awt.Color(204, 255, 255));
        btnEstadisticas4.setForeground(new java.awt.Color(0, 51, 102));
        btnEstadisticas4.setText("Estadísticas");
        btnEstadisticas4.setBorderPainted(false);
        btnEstadisticas4.setContentAreaFilled(false);
        btnEstadisticas4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEstadisticas4.addActionListener(this::btnEstadisticas4ActionPerformed);
        navbarADMInicio.add(btnEstadisticas4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 120, 50));

        btnProductos4.setBackground(new java.awt.Color(204, 255, 255));
        btnProductos4.setForeground(new java.awt.Color(0, 51, 102));
        btnProductos4.setText("Productos");
        btnProductos4.setBorderPainted(false);
        btnProductos4.setContentAreaFilled(false);
        btnProductos4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos4.addActionListener(this::btnProductos4ActionPerformed);
        navbarADMInicio.add(btnProductos4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 100, 50));

        InicioPanel.add(navbarADMInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, -1));

        barritaEncabezado.setBackground(new java.awt.Color(11, 55, 86));

        javax.swing.GroupLayout barritaEncabezadoLayout = new javax.swing.GroupLayout(barritaEncabezado);
        barritaEncabezado.setLayout(barritaEncabezadoLayout);
        barritaEncabezadoLayout.setHorizontalGroup(
            barritaEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );
        barritaEncabezadoLayout.setVerticalGroup(
            barritaEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        InicioPanel.add(barritaEncabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 10));

        PanelInicioPresentacion.setBackground(new java.awt.Color(255, 255, 255));
        PanelInicioPresentacion.setMaximumSize(new java.awt.Dimension(980, 680));
        PanelInicioPresentacion.setMinimumSize(new java.awt.Dimension(980, 680));
        PanelInicioPresentacion.setPreferredSize(new java.awt.Dimension(980, 680));
        PanelInicioPresentacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imgAmp1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inicio-img.jpg"))); // NOI18N
        PanelInicioPresentacion.add(imgAmp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 540, 380));

        LeyendaAMPinicio.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        LeyendaAMPinicio.setForeground(new java.awt.Color(1, 31, 51));
        LeyendaAMPinicio.setText("Tu software de gestión comercial");
        PanelInicioPresentacion.add(LeyendaAMPinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 350, -1, -1));

        AmpLogo2.setBackground(new java.awt.Color(255, 255, 255));
        AmpLogo2.setFont(new java.awt.Font("Georgia", 0, 70)); // NOI18N
        AmpLogo2.setForeground(new java.awt.Color(0, 0, 0));
        AmpLogo2.setText("AMPADMIN");
        PanelInicioPresentacion.add(AmpLogo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, -1, -1));

        btnConfiguracion.setBackground(new java.awt.Color(204, 204, 204));
        btnConfiguracion.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(0, 0, 76));
        btnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar.png"))); // NOI18N
        btnConfiguracion.setText("Configuración");
        btnConfiguracion.setBorder(null);
        btnConfiguracion.setBorderPainted(false);
        btnConfiguracion.setContentAreaFilled(false);
        btnConfiguracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfiguracion.addActionListener(this::btnConfiguracionActionPerformed);
        PanelInicioPresentacion.add(btnConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 640, -1, -1));

        InicioPanel.add(PanelInicioPresentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 980, 680));

        PanelConfiguracion.setBackground(new java.awt.Color(255, 255, 255));
        PanelConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        PanelConfiguracion.setMaximumSize(new java.awt.Dimension(980, 690));
        PanelConfiguracion.setMinimumSize(new java.awt.Dimension(980, 690));
        PanelConfiguracion.setPreferredSize(new java.awt.Dimension(980, 690));
        PanelConfiguracion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelConfigSeleccion.setBackground(new java.awt.Color(255, 255, 255));
        PanelConfigSeleccion.setMaximumSize(new java.awt.Dimension(980, 690));
        PanelConfigSeleccion.setMinimumSize(new java.awt.Dimension(980, 690));
        PanelConfigSeleccion.setPreferredSize(new java.awt.Dimension(980, 690));
        PanelConfigSeleccion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAccesos.setBackground(new java.awt.Color(255, 255, 255));
        btnAccesos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAccesos.setForeground(new java.awt.Color(0, 0, 0));
        btnAccesos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/accesos.png"))); // NOI18N
        btnAccesos.setText("ACCESOS");
        btnAccesos.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(37, 75, 143), new java.awt.Color(37, 75, 143)));
        btnAccesos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAccesos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAccesos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        PanelConfigSeleccion.add(btnAccesos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 400, 250));

        btnMiNegocio.setBackground(new java.awt.Color(255, 255, 255));
        btnMiNegocio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnMiNegocio.setForeground(new java.awt.Color(0, 0, 0));
        btnMiNegocio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/empresa.png"))); // NOI18N
        btnMiNegocio.setText("MI NEGOCIO");
        btnMiNegocio.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(37, 75, 143), new java.awt.Color(37, 75, 143)));
        btnMiNegocio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMiNegocio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMiNegocio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMiNegocio.addActionListener(this::btnMiNegocioActionPerformed);
        PanelConfigSeleccion.add(btnMiNegocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 400, 250));

        btnPersonal.setBackground(new java.awt.Color(255, 255, 255));
        btnPersonal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPersonal.setForeground(new java.awt.Color(0, 0, 0));
        btnPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personas.png"))); // NOI18N
        btnPersonal.setText("PERSONAL");
        btnPersonal.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(37, 75, 143), new java.awt.Color(37, 75, 143)));
        btnPersonal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPersonal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPersonal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPersonal.addActionListener(this::btnPersonalActionPerformed);
        PanelConfigSeleccion.add(btnPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 400, 250));

        btnRoles.setBackground(new java.awt.Color(255, 255, 255));
        btnRoles.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRoles.setForeground(new java.awt.Color(0, 0, 0));
        btnRoles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/roles.png"))); // NOI18N
        btnRoles.setText("ROLES");
        btnRoles.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(37, 75, 143), new java.awt.Color(37, 75, 143)));
        btnRoles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRoles.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRoles.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        PanelConfigSeleccion.add(btnRoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 340, 400, 250));

        btnVolverAInicio.setBackground(new java.awt.Color(117, 41, 27));
        btnVolverAInicio.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnVolverAInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnVolverAInicio.setText("Volver");
        btnVolverAInicio.setBorderPainted(false);
        btnVolverAInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolverAInicio.setFocusPainted(false);
        btnVolverAInicio.addActionListener(this::btnVolverAInicioActionPerformed);
        PanelConfigSeleccion.add(btnVolverAInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 610, 120, 40));

        jLabel10.setBackground(new java.awt.Color(11, 55, 86));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("SELECCIONE UNA OPCIÓN");
        jLabel10.setToolTipText("");
        jLabel10.setOpaque(true);
        PanelConfigSeleccion.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 50));

        PanelConfiguracion.add(PanelConfigSeleccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 690));

        PanelConfigMiNegocio.setBackground(new java.awt.Color(236, 255, 253));
        PanelConfigMiNegocio.setMaximumSize(new java.awt.Dimension(980, 690));
        PanelConfigMiNegocio.setMinimumSize(new java.awt.Dimension(980, 690));
        PanelConfigMiNegocio.setPreferredSize(new java.awt.Dimension(980, 690));
        PanelConfigMiNegocio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(11, 55, 86));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MI NEGOCIO");
        jLabel1.setToolTipText("");
        jLabel1.setOpaque(true);
        PanelConfigMiNegocio.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 50));

        txtTelEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtTelEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtTelEmpresa.setForeground(new java.awt.Color(0, 102, 153));
        txtTelEmpresa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelEmpresa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.darkGray), "Teléfono:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        PanelConfigMiNegocio.add(txtTelEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 860, 80));

        txtNomEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtNomEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtNomEmpresa.setForeground(new java.awt.Color(0, 102, 153));
        txtNomEmpresa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNomEmpresa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.darkGray), "Nombre/Razón Social: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        PanelConfigMiNegocio.add(txtNomEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 860, 80));

        txtDomEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtDomEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtDomEmpresa.setForeground(new java.awt.Color(0, 102, 153));
        txtDomEmpresa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDomEmpresa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.darkGray), "Domicilio: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        PanelConfigMiNegocio.add(txtDomEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 860, 80));

        txtProvEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtProvEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtProvEmpresa.setForeground(new java.awt.Color(0, 102, 153));
        txtProvEmpresa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProvEmpresa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.darkGray), "Provincia:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        PanelConfigMiNegocio.add(txtProvEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 860, 80));

        btnGuardarEmpresa.setBackground(new java.awt.Color(32, 143, 61));
        btnGuardarEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnGuardarEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarEmpresa.setText("Guardar");
        btnGuardarEmpresa.setBorderPainted(false);
        btnGuardarEmpresa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarEmpresa.setFocusPainted(false);
        btnGuardarEmpresa.addActionListener(this::btnGuardarEmpresaActionPerformed);
        PanelConfigMiNegocio.add(btnGuardarEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 600, 120, 40));

        btnVolverASeleccionC.setBackground(new java.awt.Color(117, 41, 27));
        btnVolverASeleccionC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnVolverASeleccionC.setForeground(new java.awt.Color(255, 255, 255));
        btnVolverASeleccionC.setText("Volver");
        btnVolverASeleccionC.setBorderPainted(false);
        btnVolverASeleccionC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolverASeleccionC.setFocusPainted(false);
        btnVolverASeleccionC.addActionListener(this::btnVolverASeleccionCActionPerformed);
        PanelConfigMiNegocio.add(btnVolverASeleccionC, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 613, 120, 40));

        PanelConfiguracion.add(PanelConfigMiNegocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 690));

        PanelConfigPersonal.setBackground(new java.awt.Color(236, 255, 253));
        PanelConfigPersonal.setMaximumSize(new java.awt.Dimension(980, 690));
        PanelConfigPersonal.setMinimumSize(new java.awt.Dimension(980, 690));
        PanelConfigPersonal.setPreferredSize(new java.awt.Dimension(980, 690));
        PanelConfigPersonal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(11, 55, 86));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("PERSONAL");
        jLabel6.setToolTipText("");
        jLabel6.setOpaque(true);
        PanelConfigPersonal.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 50));

        btnVolverASeleccionC1.setBackground(new java.awt.Color(117, 41, 27));
        btnVolverASeleccionC1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnVolverASeleccionC1.setForeground(new java.awt.Color(255, 255, 255));
        btnVolverASeleccionC1.setText("Volver");
        btnVolverASeleccionC1.setBorderPainted(false);
        btnVolverASeleccionC1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolverASeleccionC1.setFocusPainted(false);
        btnVolverASeleccionC1.addActionListener(this::btnVolverASeleccionC1ActionPerformed);
        PanelConfigPersonal.add(btnVolverASeleccionC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 613, 120, 40));

        DatosEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        DatosEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.darkGray), "DATOS DE LA PERSONA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        DatosEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNomUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtNomUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNomUsuario.setForeground(new java.awt.Color(0, 102, 153));
        txtNomUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNomUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.darkGray), "Nombre:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 16), new java.awt.Color(0, 0, 0))); // NOI18N
        DatosEmpleado.add(txtNomUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 500, 60));

        txtTelUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtTelUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTelUsuario.setForeground(new java.awt.Color(0, 102, 153));
        txtTelUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.darkGray), "Teléfono:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 16), new java.awt.Color(0, 0, 0))); // NOI18N
        DatosEmpleado.add(txtTelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 500, 60));

        txtDomUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtDomUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDomUsuario.setForeground(new java.awt.Color(0, 102, 153));
        txtDomUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDomUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.darkGray), "Domicilio:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 16), new java.awt.Color(0, 0, 0))); // NOI18N
        DatosEmpleado.add(txtDomUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 500, 60));

        txtMailUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtMailUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMailUsuario.setForeground(new java.awt.Color(0, 102, 153));
        txtMailUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMailUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.darkGray), "Mail:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 16), new java.awt.Color(0, 0, 0))); // NOI18N
        DatosEmpleado.add(txtMailUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 500, 60));

        cbRolesPersonal.setBackground(new java.awt.Color(255, 255, 255));
        cbRolesPersonal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbRolesPersonal.setForeground(new java.awt.Color(0, 102, 153));
        cbRolesPersonal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.darkGray), "Rol: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16), new java.awt.Color(0, 0, 0))); // NOI18N
        DatosEmpleado.add(cbRolesPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 500, 60));

        btnGuardarUsuario.setBackground(new java.awt.Color(32, 143, 61));
        btnGuardarUsuario.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnGuardarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btn-agregar.png"))); // NOI18N
        btnGuardarUsuario.setText("Agregar");
        btnGuardarUsuario.setBorderPainted(false);
        btnGuardarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarUsuario.setFocusPainted(false);
        btnGuardarUsuario.addActionListener(this::btnGuardarUsuarioActionPerformed);
        DatosEmpleado.add(btnGuardarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 465, 130, 40));

        btnModificarUsuario.setBackground(new java.awt.Color(153, 153, 153));
        btnModificarUsuario.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnModificarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btn-modificar.png"))); // NOI18N
        btnModificarUsuario.setText("Modificar");
        btnModificarUsuario.setBorderPainted(false);
        btnModificarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarUsuario.setFocusPainted(false);
        btnModificarUsuario.addActionListener(this::btnModificarUsuarioActionPerformed);
        DatosEmpleado.add(btnModificarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 465, 130, 40));

        txtClaveUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtClaveUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtClaveUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClaveUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.darkGray), "Clave de acceso: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 16), new java.awt.Color(0, 0, 0))); // NOI18N
        DatosEmpleado.add(txtClaveUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 500, -1));

        PanelConfigPersonal.add(DatosEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 540, 520));

        HistorialEmpleados.setBackground(new java.awt.Color(255, 255, 255));
        HistorialEmpleados.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.darkGray), "MI PERSONAL", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        HistorialEmpleados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaMiPersonal.setBackground(new java.awt.Color(255, 255, 255));
        tablaMiPersonal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Rol"
            }
        ));
        tablaMiPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMiPersonalMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablaMiPersonal);

        HistorialEmpleados.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 37, 280, 410));

        btnEliminarPersonal.setBackground(new java.awt.Color(255, 102, 102));
        btnEliminarPersonal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnEliminarPersonal.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btn-eliminar.png"))); // NOI18N
        btnEliminarPersonal.setText("Borrar");
        btnEliminarPersonal.setBorderPainted(false);
        btnEliminarPersonal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarPersonal.setFocusPainted(false);
        btnEliminarPersonal.addActionListener(this::btnEliminarPersonalActionPerformed);
        HistorialEmpleados.add(btnEliminarPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 465, 130, 40));

        PanelConfigPersonal.add(HistorialEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 310, 520));

        PanelConfiguracion.add(PanelConfigPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 690));

        InicioPanel.add(PanelConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 690));

        getContentPane().add(InicioPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 740));

        administracionPanel.setBackground(new java.awt.Color(255, 255, 255));
        administracionPanel.setMaximumSize(new java.awt.Dimension(980, 740));
        administracionPanel.setMinimumSize(new java.awt.Dimension(980, 740));
        administracionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navbarADM.setBackground(new java.awt.Color(225, 250, 255));
        navbarADM.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NAVBAR.png"))); // NOI18N
        lblimg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblimg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblimgMouseClicked(evt);
            }
        });
        navbarADM.add(lblimg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnInformes4.setBackground(new java.awt.Color(204, 255, 255));
        btnInformes4.setForeground(new java.awt.Color(0, 51, 102));
        btnInformes4.setText("Informes");
        btnInformes4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInformes4.setBorderPainted(false);
        btnInformes4.setContentAreaFilled(false);
        btnInformes4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInformes4.addActionListener(this::btnInformesActionPerformed);
        navbarADM.add(btnInformes4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 90, 50));

        btnCaja.setBackground(new java.awt.Color(204, 255, 255));
        btnCaja.setForeground(new java.awt.Color(0, 51, 102));
        btnCaja.setText("Caja");
        btnCaja.setBorderPainted(false);
        btnCaja.setContentAreaFilled(false);
        btnCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navbarADM.add(btnCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 100, 50));

        btnProveedores.setBackground(new java.awt.Color(204, 255, 255));
        btnProveedores.setForeground(new java.awt.Color(0, 51, 102));
        btnProveedores.setText("Proveedores");
        btnProveedores.setBorderPainted(false);
        btnProveedores.setContentAreaFilled(false);
        btnProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedores.addActionListener(this::btnProveedoresActionPerformed);
        navbarADM.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 120, 50));

        btnAdministracion.setBackground(new java.awt.Color(20, 52, 83));
        btnAdministracion.setForeground(new java.awt.Color(255, 255, 255));
        btnAdministracion.setText("Administración");
        btnAdministracion.setBorderPainted(false);
        btnAdministracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdministracion.addActionListener(this::btnAdministracionActionPerformed);
        navbarADM.add(btnAdministracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 120, 50));

        btnVentas.setBackground(new java.awt.Color(204, 255, 255));
        btnVentas.setForeground(new java.awt.Color(0, 51, 102));
        btnVentas.setText("Ventas");
        btnVentas.setBorderPainted(false);
        btnVentas.setContentAreaFilled(false);
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas.addActionListener(this::btnVentasActionPerformed);
        navbarADM.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 90, 50));

        btnEstadisticas.setBackground(new java.awt.Color(204, 255, 255));
        btnEstadisticas.setForeground(new java.awt.Color(0, 51, 102));
        btnEstadisticas.setText("Estadísticas");
        btnEstadisticas.setBorderPainted(false);
        btnEstadisticas.setContentAreaFilled(false);
        btnEstadisticas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEstadisticas.addActionListener(this::btnEstadisticasActionPerformed);
        navbarADM.add(btnEstadisticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 120, 50));

        btnProductos.setBackground(new java.awt.Color(204, 255, 255));
        btnProductos.setForeground(new java.awt.Color(0, 51, 102));
        btnProductos.setText("Productos");
        btnProductos.setBorderPainted(false);
        btnProductos.setContentAreaFilled(false);
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos.addActionListener(this::btnProductosActionPerformed);
        navbarADM.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 100, 50));

        administracionPanel.add(navbarADM, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, -1));

        InicioAdmPanel.setBackground(new java.awt.Color(255, 255, 255));
        InicioAdmPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/administracion-img.png"))); // NOI18N
        InicioAdmPanel.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 500, -1));

        jLabel45.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(20, 52, 83));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("ADMINISTRACIÓN");
        InicioAdmPanel.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, 430, 50));

        jLabel46.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel46.setText("En esta sección podrás gestionar las ventas y cobros");
        InicioAdmPanel.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 430, 20));

        jLabel56.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel56.setText("de las cuentas corrientes de la empresa.");
        InicioAdmPanel.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 330, 330, 20));

        administracionPanel.add(InicioAdmPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 980, 640));

        GestionAdmPanel.setBackground(new java.awt.Color(255, 255, 255));
        GestionAdmPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        regEmp.setBackground(new java.awt.Color(255, 255, 255));
        regEmp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCuit.setBackground(new java.awt.Color(255, 255, 255));
        txtCuit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCuit.setForeground(new java.awt.Color(14, 25, 80));
        txtCuit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuit.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "CUIT/CUIL:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        regEmp.add(txtCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 490, 70));

        txtMail.setBackground(new java.awt.Color(255, 255, 255));
        txtMail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtMail.setForeground(new java.awt.Color(14, 25, 80));
        txtMail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMail.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Mail: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        regEmp.add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 650, 70));

        txtTel.setBackground(new java.awt.Color(255, 255, 255));
        txtTel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTel.setForeground(new java.awt.Color(14, 25, 80));
        txtTel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Teléfono: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        regEmp.add(txtTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 430, 70));

        txtDom.setBackground(new java.awt.Color(255, 255, 255));
        txtDom.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDom.setForeground(new java.awt.Color(14, 25, 80));
        txtDom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDom.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Domicilio: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        regEmp.add(txtDom, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 650, 70));

        jLabel9.setBackground(new java.awt.Color(13, 48, 66));
        jLabel9.setFont(new java.awt.Font("Microsoft JhengHei", 3, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("    GESTIÓN DE CUENTAS CORRIENTES");
        jLabel9.setOpaque(true);
        regEmp.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 50));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Fecha de ingreso:");
        regEmp.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 250, -1, -1));

        txtDate.setBackground(new java.awt.Color(255, 255, 255));
        txtDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        regEmp.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 280, 240, 50));

        txtRS.setBackground(new java.awt.Color(255, 255, 255));
        txtRS.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtRS.setForeground(new java.awt.Color(14, 25, 80));
        txtRS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRS.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Razón Social/Nombre: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        regEmp.add(txtRS, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 490, 70));

        cbCondIva.setBackground(new java.awt.Color(255, 255, 255));
        cbCondIva.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cbCondIva.setForeground(new java.awt.Color(14, 25, 80));
        cbCondIva.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Condición frente al iva: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        cbCondIva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        regEmp.add(cbCondIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 450, 470, 70));

        PanelOpcAdm.setBackground(new java.awt.Color(255, 255, 255));
        PanelOpcAdm.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Seleccione una opción", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        PanelOpcAdm.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAPersona.setBackground(new java.awt.Color(0, 66, 115));
        btnAPersona.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnAPersona.setForeground(new java.awt.Color(255, 255, 255));
        btnAPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personita.png"))); // NOI18N
        btnAPersona.setText("PERSONA");
        btnAPersona.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAPersona.setBorderPainted(false);
        btnAPersona.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAPersona.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAPersona.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAPersona.addActionListener(this::btnAPersonaActionPerformed);
        PanelOpcAdm.add(btnAPersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 190, 120));

        btnAEmpresa.setBackground(new java.awt.Color(2, 33, 55));
        btnAEmpresa.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnAEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        btnAEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edificio.png"))); // NOI18N
        btnAEmpresa.setText("EMPRESA");
        btnAEmpresa.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAEmpresa.setBorderPainted(false);
        btnAEmpresa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAEmpresa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAEmpresa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAEmpresa.addActionListener(this::btnAEmpresaActionPerformed);
        PanelOpcAdm.add(btnAEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 180, 120));

        regEmp.add(PanelOpcAdm, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 410, 170));

        GestionAdmPanel.add(regEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 520));

        btnAContinuar.setBackground(new java.awt.Color(22, 84, 122));
        btnAContinuar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAContinuar.setForeground(new java.awt.Color(255, 255, 255));
        btnAContinuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btn-agregar.png"))); // NOI18N
        btnAContinuar.setText("Agregar");
        btnAContinuar.setBorderPainted(false);
        btnAContinuar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAContinuar.addActionListener(this::btnAContinuarActionPerformed);
        GestionAdmPanel.add(btnAContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 560, 170, 50));

        btnABuscar.setBackground(new java.awt.Color(35, 13, 45));
        btnABuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnABuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnABuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnABuscar.setText("Buscar ");
        btnABuscar.setBorderPainted(false);
        btnABuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnABuscar.addActionListener(this::btnABuscarActionPerformed);
        GestionAdmPanel.add(btnABuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 560, 170, 50));

        btnAModificar.setBackground(new java.awt.Color(102, 102, 102));
        btnAModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnAModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btn-modificar.png"))); // NOI18N
        btnAModificar.setText("Modificar");
        btnAModificar.setBorderPainted(false);
        btnAModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAModificar.addActionListener(this::btnAModificarActionPerformed);
        GestionAdmPanel.add(btnAModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 560, 170, 50));

        btnABorrar.setBackground(new java.awt.Color(51, 0, 0));
        btnABorrar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnABorrar.setForeground(new java.awt.Color(255, 255, 255));
        btnABorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btn-eliminar.png"))); // NOI18N
        btnABorrar.setText("Borrar");
        btnABorrar.setBorderPainted(false);
        btnABorrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnABorrar.addActionListener(this::btnABorrarActionPerformed);
        GestionAdmPanel.add(btnABorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 170, 50));

        administracionPanel.add(GestionAdmPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 980, 640));

        ListaPanel.setBackground(new java.awt.Color(255, 255, 255));
        ListaPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaListaCC.setBackground(new java.awt.Color(255, 255, 255));
        tablaListaCC.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaListaCC);

        ListaPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 960, 380));

        PanelFiltroListasAdm.setBackground(new java.awt.Color(255, 255, 255));
        PanelFiltroListasAdm.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros de Búsqueda: ", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        PanelFiltroListasAdm.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtFiltroRS.setBackground(new java.awt.Color(255, 255, 255));
        txtFiltroRS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFiltroRS.setForeground(new java.awt.Color(0, 0, 0));
        txtFiltroRS.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Nombre/Razón Social: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        txtFiltroRS.addActionListener(this::txtFiltroRSActionPerformed);
        PanelFiltroListasAdm.add(txtFiltroRS, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 290, 50));

        txtFiltroCuit.setBackground(new java.awt.Color(255, 255, 255));
        txtFiltroCuit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFiltroCuit.setForeground(new java.awt.Color(0, 0, 0));
        txtFiltroCuit.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "CUIT/CUIL: ", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        PanelFiltroListasAdm.add(txtFiltroCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 30, 210, 50));

        cbTipo.setBackground(new java.awt.Color(255, 255, 255));
        cbTipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbTipo.setForeground(new java.awt.Color(0, 0, 0));
        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Persona", "Empresa" }));
        cbTipo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo de cuenta: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        PanelFiltroListasAdm.add(cbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 170, 50));

        btnLimpiar.setBackground(new java.awt.Color(153, 153, 153));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rehacer_1.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        PanelFiltroListasAdm.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 110, 40));

        btnFiltrar.setBackground(new java.awt.Color(48, 114, 14));
        btnFiltrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tick.png"))); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(this::btnFiltrarActionPerformed);
        PanelFiltroListasAdm.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 110, 40));

        ListaPanel.add(PanelFiltroListasAdm, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 960, 90));

        btnAGestionarCC.setBackground(new java.awt.Color(102, 102, 102));
        btnAGestionarCC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAGestionarCC.setForeground(new java.awt.Color(255, 255, 255));
        btnAGestionarCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btn-modificar.png"))); // NOI18N
        btnAGestionarCC.setText("Gestionar");
        btnAGestionarCC.setActionCommand("<Volver>");
        btnAGestionarCC.setBorderPainted(false);
        btnAGestionarCC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAGestionarCC.addActionListener(this::btnAGestionarCCActionPerformed);
        ListaPanel.add(btnAGestionarCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 560, 170, 50));

        btnADetalleCC.setBackground(new java.awt.Color(22, 84, 122));
        btnADetalleCC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnADetalleCC.setForeground(new java.awt.Color(255, 255, 255));
        btnADetalleCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/documento.png"))); // NOI18N
        btnADetalleCC.setText("Ver detalle");
        btnADetalleCC.setToolTipText("");
        btnADetalleCC.setActionCommand("<Volver>");
        btnADetalleCC.setBorderPainted(false);
        btnADetalleCC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnADetalleCC.addActionListener(this::btnADetalleCCActionPerformed);
        ListaPanel.add(btnADetalleCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 560, 170, 50));

        btnAContinuar3.setBackground(new java.awt.Color(255, 255, 153));
        btnAContinuar3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAContinuar3.setForeground(new java.awt.Color(0, 0, 0));
        btnAContinuar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/descargar.png"))); // NOI18N
        btnAContinuar3.setText("Exportar");
        btnAContinuar3.setToolTipText("");
        btnAContinuar3.setActionCommand("<Volver>");
        btnAContinuar3.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        btnAContinuar3.setBorderPainted(false);
        btnAContinuar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAContinuar3.addActionListener(this::btnExportarCliente);
        ListaPanel.add(btnAContinuar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 560, 170, 50));

        jLabel2.setBackground(new java.awt.Color(13, 48, 66));
        jLabel2.setFont(new java.awt.Font("Microsoft JhengHei", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("   LISTA DE CUENTAS CORRIENTES");
        jLabel2.setOpaque(true);
        ListaPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 50));

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
        btnAVolver.setContentAreaFilled(false);
        btnAVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAVolver.addActionListener(this::btnAVolverActionPerformed);

        btnAExportar.setBackground(new java.awt.Color(22, 22, 22));
        btnAExportar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAExportar.setForeground(new java.awt.Color(255, 255, 255));
        btnAExportar.setText("<Exportar>");
        btnAExportar.setToolTipText("");
        btnAExportar.setActionCommand("<Volver>");
        btnAExportar.setBorderPainted(false);
        btnAExportar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAExportar.addActionListener(this::btnAExportarbtnExportarCliente);

        btnACobrar.setBackground(new java.awt.Color(22, 22, 22));
        btnACobrar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnACobrar.setForeground(new java.awt.Color(255, 255, 255));
        btnACobrar.setText("<Proceder al Cobro>");
        btnACobrar.setToolTipText("");
        btnACobrar.setActionCommand("<Volver>");
        btnACobrar.setBorderPainted(false);
        btnACobrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        btnAClientes.setBackground(new java.awt.Color(38, 90, 142));
        btnAClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnAClientes.setText("Clientes");
        btnAClientes.setBorderPainted(false);
        btnAClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAClientes.addActionListener(this::btnAClientesActionPerformed);

        btnAPagos.setBackground(new java.awt.Color(20, 52, 83));
        btnAPagos.setForeground(new java.awt.Color(255, 255, 255));
        btnAPagos.setText("Pagos");
        btnAPagos.setBorderPainted(false);
        btnAPagos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAPagos.addActionListener(this::btnAPagosActionPerformed);

        btnAListas.setBackground(new java.awt.Color(20, 52, 83));
        btnAListas.setForeground(new java.awt.Color(255, 255, 255));
        btnAListas.setText("Listas");
        btnAListas.setBorderPainted(false);
        btnAListas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        administracionPanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 50));

        getContentPane().add(administracionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 740));

        ventasPanel.setBackground(new java.awt.Color(20, 52, 83));
        ventasPanel.setMaximumSize(new java.awt.Dimension(980, 740));
        ventasPanel.setMinimumSize(new java.awt.Dimension(980, 740));
        ventasPanel.setPreferredSize(new java.awt.Dimension(980, 740));
        ventasPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navbarVNT.setBackground(new java.awt.Color(225, 250, 255));
        navbarVNT.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblimg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NAVBAR.png"))); // NOI18N
        lblimg1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblimg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblimg1MouseClicked(evt);
            }
        });
        navbarVNT.add(lblimg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnInformes1.setBackground(new java.awt.Color(204, 255, 255));
        btnInformes1.setForeground(new java.awt.Color(0, 51, 102));
        btnInformes1.setText("Informes");
        btnInformes1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInformes1.setBorderPainted(false);
        btnInformes1.setContentAreaFilled(false);
        btnInformes1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navbarVNT.add(btnInformes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 90, 50));

        btnCaja1.setBackground(new java.awt.Color(204, 255, 255));
        btnCaja1.setForeground(new java.awt.Color(0, 51, 102));
        btnCaja1.setText("Caja");
        btnCaja1.setBorderPainted(false);
        btnCaja1.setContentAreaFilled(false);
        btnCaja1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navbarVNT.add(btnCaja1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 100, 50));

        btnProveedores1.setBackground(new java.awt.Color(204, 255, 255));
        btnProveedores1.setForeground(new java.awt.Color(0, 51, 102));
        btnProveedores1.setText("Proveedores");
        btnProveedores1.setBorderPainted(false);
        btnProveedores1.setContentAreaFilled(false);
        btnProveedores1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedores1.addActionListener(this::btnProveedores1ActionPerformed);
        navbarVNT.add(btnProveedores1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 120, 50));

        btnAdministracion1.setBackground(new java.awt.Color(20, 52, 83));
        btnAdministracion1.setForeground(new java.awt.Color(0, 51, 102));
        btnAdministracion1.setText("Administración");
        btnAdministracion1.setBorderPainted(false);
        btnAdministracion1.setContentAreaFilled(false);
        btnAdministracion1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdministracion1.addActionListener(this::btnAdministracion1ActionPerformed);
        navbarVNT.add(btnAdministracion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 120, 50));

        btnVentas1.setBackground(new java.awt.Color(20, 52, 83));
        btnVentas1.setForeground(new java.awt.Color(255, 255, 255));
        btnVentas1.setText("Ventas");
        btnVentas1.setBorderPainted(false);
        btnVentas1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas1.addActionListener(this::btnVentas1ActionPerformed);
        navbarVNT.add(btnVentas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 90, 50));

        btnEstadisticas1.setBackground(new java.awt.Color(204, 255, 255));
        btnEstadisticas1.setForeground(new java.awt.Color(0, 51, 102));
        btnEstadisticas1.setText("Estadísticas");
        btnEstadisticas1.setBorderPainted(false);
        btnEstadisticas1.setContentAreaFilled(false);
        btnEstadisticas1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEstadisticas1.addActionListener(this::btnEstadisticas1ActionPerformed);
        navbarVNT.add(btnEstadisticas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 120, 50));

        btnProductos1.setBackground(new java.awt.Color(204, 255, 255));
        btnProductos1.setForeground(new java.awt.Color(0, 51, 102));
        btnProductos1.setText("Productos");
        btnProductos1.setBorderPainted(false);
        btnProductos1.setContentAreaFilled(false);
        btnProductos1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos1.addActionListener(this::btnProductos1ActionPerformed);
        navbarVNT.add(btnProductos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 100, 50));

        ventasPanel.add(navbarVNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, -1));

        detPanel.setBackground(new java.awt.Color(255, 255, 255));
        detPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DEL PRODUCTO", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        detPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtStock.setEditable(false);
        txtStock.setBackground(new java.awt.Color(255, 255, 255));
        txtStock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtStock.setForeground(new java.awt.Color(102, 102, 102));
        txtStock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStock.setText("Stock ");
        txtStock.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        detPanel.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 210, 40));

        txtPre1.setEditable(false);
        txtPre1.setBackground(new java.awt.Color(255, 255, 255));
        txtPre1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPre1.setForeground(new java.awt.Color(102, 102, 102));
        txtPre1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPre1.setText("Precio Unitario");
        txtPre1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        detPanel.add(txtPre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 210, 40));

        txtPre2.setEditable(false);
        txtPre2.setBackground(new java.awt.Color(255, 255, 255));
        txtPre2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPre2.setForeground(new java.awt.Color(102, 102, 102));
        txtPre2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPre2.setText("Precio Oferta");
        txtPre2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPre2.addActionListener(this::txtPre2ActionPerformed);
        detPanel.add(txtPre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 210, 40));

        jLabel31.setBackground(new java.awt.Color(51, 147, 102));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Stock Actual");
        jLabel31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jLabel31.setOpaque(true);
        detPanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 210, 30));

        txtIVA.setEditable(false);
        txtIVA.setBackground(new java.awt.Color(255, 255, 255));
        txtIVA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtIVA.setForeground(new java.awt.Color(102, 102, 102));
        txtIVA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIVA.setText("IVA");
        txtIVA.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        detPanel.add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 210, 40));

        jLabel57.setBackground(new java.awt.Color(34, 153, 200));
        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("IVA");
        jLabel57.setOpaque(true);
        detPanel.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 210, 30));

        jLabel59.setBackground(new java.awt.Color(20, 52, 83));
        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("Precio Unitario");
        jLabel59.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jLabel59.setOpaque(true);
        detPanel.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 210, 30));

        jLabel60.setBackground(new java.awt.Color(34, 153, 200));
        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Precio Oferta");
        jLabel60.setOpaque(true);
        detPanel.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 210, 30));

        ventasPanel.add(detPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 960, 100));

        listaPanel.setBackground(new java.awt.Color(255, 255, 255));
        listaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
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
        tablaVenta.setEnabled(false);
        tablaVenta.setRowHeight(25);
        tablaVenta.setShowHorizontalLines(true);
        jScrollPane2.setViewportView(tablaVenta);

        listaPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 940, 300));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel32.setText("Total:");
        listaPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 510, -1, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setText("Descuento:");
        listaPanel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 480, -1, -1));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listaPanel.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 510, 170, 20));

        ventasPanel.add(listaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 960, 320));

        buscarPanel.setBackground(new java.awt.Color(255, 255, 255));
        buscarPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        buscarPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDescV.setEditable(false);
        txtDescV.setBackground(new java.awt.Color(255, 255, 255));
        txtDescV.setFont(new java.awt.Font("Microsoft JhengHei", 0, 18)); // NOI18N
        txtDescV.setForeground(new java.awt.Color(0, 0, 0));
        txtDescV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Descripción", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 16), new java.awt.Color(25, 35, 51))); // NOI18N
        buscarPanel.add(txtDescV, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 380, 50));

        BuscarDescV.setBackground(new java.awt.Color(219, 229, 253));
        BuscarDescV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        BuscarDescV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BuscarDescV.addActionListener(this::BuscarDescVActionPerformed);
        buscarPanel.add(BuscarDescV, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 45, 40));

        RehacerV.setBackground(new java.awt.Color(126, 22, 2));
        RehacerV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        RehacerV.setForeground(new java.awt.Color(255, 255, 255));
        RehacerV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rehacer-blanco.png"))); // NOI18N
        RehacerV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RehacerV.addActionListener(this::RehacerVActionPerformed);
        buscarPanel.add(RehacerV, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 90, 40));

        BuscarV.setBackground(new java.awt.Color(219, 229, 253));
        BuscarV.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        BuscarV.setForeground(new java.awt.Color(255, 255, 255));
        BuscarV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        BuscarV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BuscarV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BuscarV.addActionListener(this::BuscarVActionPerformed);
        buscarPanel.add(BuscarV, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 45, 40));

        AgregarV.setBackground(new java.awt.Color(70, 105, 243));
        AgregarV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AgregarV.setForeground(new java.awt.Color(255, 255, 255));
        AgregarV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btn-agregar.png"))); // NOI18N
        AgregarV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AgregarV.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        AgregarV.addActionListener(this::AgregarVActionPerformed);
        buscarPanel.add(AgregarV, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, 90, 40));

        txtCantV.setBackground(new java.awt.Color(255, 255, 255));
        txtCantV.setFont(new java.awt.Font("Microsoft JhengHei", 0, 18)); // NOI18N
        txtCantV.setForeground(new java.awt.Color(0, 0, 0));
        txtCantV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cantidad", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 16), new java.awt.Color(25, 35, 51))); // NOI18N
        buscarPanel.add(txtCantV, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 120, 50));

        txtCodV.setBackground(new java.awt.Color(255, 255, 255));
        txtCodV.setFont(new java.awt.Font("Microsoft JhengHei", 0, 18)); // NOI18N
        txtCodV.setForeground(new java.awt.Color(0, 0, 0));
        txtCodV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Código", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 16), new java.awt.Color(25, 35, 51))); // NOI18N
        buscarPanel.add(txtCodV, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 130, 50));

        lblFact.setBackground(new java.awt.Color(20, 52, 83));
        lblFact.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        lblFact.setForeground(new java.awt.Color(255, 255, 255));
        lblFact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFact.setText("X");
        lblFact.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblFact.setOpaque(true);
        buscarPanel.add(lblFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 90, 40));

        lblBuscarProd.setFont(new java.awt.Font("Microsoft JhengHei", 3, 18)); // NOI18N
        lblBuscarProd.setForeground(new java.awt.Color(60, 63, 65));
        lblBuscarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        lblBuscarProd.setText("BUSCAR UN PRODUCTO");
        buscarPanel.add(lblBuscarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 260, -1));

        ventasPanel.add(buscarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 960, 100));

        resumenPanel.setBackground(new java.awt.Color(255, 255, 255));
        resumenPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RESUMEN DE LA VENTA", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        resumenPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TotalV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        TotalV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalV.setText("$");
        TotalV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "TOTAL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16), new java.awt.Color(20, 52, 83))); // NOI18N
        resumenPanel.add(TotalV, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 220, 50));

        SubtotalV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        SubtotalV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SubtotalV.setText("$");
        SubtotalV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "SUBTOTAL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16), new java.awt.Color(20, 52, 83))); // NOI18N
        resumenPanel.add(SubtotalV, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 220, 50));

        DescuentoV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        DescuentoV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DescuentoV.setText("$");
        DescuentoV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "DESCUENTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16), new java.awt.Color(20, 52, 83))); // NOI18N
        resumenPanel.add(DescuentoV, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 220, 50));

        IvaV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        IvaV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IvaV.setText("$");
        IvaV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "IVA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16), new java.awt.Color(20, 52, 83))); // NOI18N
        resumenPanel.add(IvaV, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 220, 50));

        ventasPanel.add(resumenPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, 960, 80));

        RehacerVenta.setBackground(new java.awt.Color(175, 112, 18));
        RehacerVenta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        RehacerVenta.setForeground(new java.awt.Color(255, 255, 255));
        RehacerVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rehacer_1.png"))); // NOI18N
        RehacerVenta.setText("Rehacer");
        RehacerVenta.setBorderPainted(false);
        RehacerVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RehacerVenta.addActionListener(this::RehacerVentaActionPerformed);
        ventasPanel.add(RehacerVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 700, 230, 35));

        DatosCobroVenta.setBackground(new java.awt.Color(35, 42, 55));
        DatosCobroVenta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DatosCobroVenta.setForeground(new java.awt.Color(255, 255, 255));
        DatosCobroVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tarjeta.png"))); // NOI18N
        DatosCobroVenta.setText("Datos del cobro");
        DatosCobroVenta.setBorderPainted(false);
        DatosCobroVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DatosCobroVenta.addActionListener(this::DatosCobroVentaActionPerformed);
        ventasPanel.add(DatosCobroVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 700, 230, 35));

        EliminarItemV.setBackground(new java.awt.Color(126, 22, 2));
        EliminarItemV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        EliminarItemV.setForeground(new java.awt.Color(255, 255, 255));
        EliminarItemV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/basura.png"))); // NOI18N
        EliminarItemV.setText("Eliminar item");
        EliminarItemV.setBorderPainted(false);
        EliminarItemV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EliminarItemV.addActionListener(this::EliminarItemVActionPerformed);
        ventasPanel.add(EliminarItemV, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 700, 230, 35));

        ConfirmarVenta.setBackground(new java.awt.Color(51, 147, 102));
        ConfirmarVenta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ConfirmarVenta.setForeground(new java.awt.Color(255, 255, 255));
        ConfirmarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tick.png"))); // NOI18N
        ConfirmarVenta.setText("Confirmar venta");
        ConfirmarVenta.setBorderPainted(false);
        ConfirmarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ConfirmarVenta.addActionListener(this::ConfirmarVentaActionPerformed);
        ventasPanel.add(ConfirmarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 700, 224, 35));

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
        cbMpVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbMpVentas.addActionListener(this::cbMpVentasActionPerformed);
        panelFact.add(cbMpVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 260, 30));

        factB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        factB.setText("B");
        factB.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        factB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        factB.addActionListener(this::factBActionPerformed);
        panelFact.add(factB, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 230, 70, 30));

        factA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        factA.setText("A");
        factA.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        factA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        txtCuotas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelFact.add(txtCuotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 260, 30));

        factX.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        factX.setText("X");
        factX.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        factX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        factX.addActionListener(this::factXActionPerformed);
        panelFact.add(factX, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 70, 30));

        metodoPago.add(panelFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 320, 460));

        panelCli.setBackground(new java.awt.Color(255, 255, 255));
        panelCli.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)), "Información del cliente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        panelCli.setForeground(new java.awt.Color(255, 255, 255));
        panelCli.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbCondIvaMp.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        cbCondIvaMp.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Condición frente al IVA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(20, 52, 83))); // NOI18N
        cbCondIvaMp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbCondIvaMp.addActionListener(this::cbCondIvaMpActionPerformed);
        panelCli.add(cbCondIvaMp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 540, 60));

        AgregarCliMP.setBackground(new java.awt.Color(20, 52, 83));
        AgregarCliMP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AgregarCliMP.setForeground(new java.awt.Color(255, 255, 255));
        AgregarCliMP.setText("+ Agregar");
        AgregarCliMP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        AgregarCliMP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AgregarCliMP.addActionListener(this::AgregarCliMPActionPerformed);
        panelCli.add(AgregarCliMP, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 140, 40));

        BuscarCliMP.setBackground(new java.awt.Color(20, 52, 83));
        BuscarCliMP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BuscarCliMP.setForeground(new java.awt.Color(255, 255, 255));
        BuscarCliMP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        BuscarCliMP.setText("Buscar");
        BuscarCliMP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BuscarCliMP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        LimpiarMp.setBackground(new java.awt.Color(175, 112, 18));
        LimpiarMp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LimpiarMp.setForeground(new java.awt.Color(255, 255, 255));
        LimpiarMp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rehacer_1.png"))); // NOI18N
        LimpiarMp.setText("Rehacer");
        LimpiarMp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        LimpiarMp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LimpiarMp.addActionListener(this::LimpiarMpActionPerformed);
        metodoPago.add(LimpiarMp, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 620, 140, 50));

        jButton7.setBackground(new java.awt.Color(51, 147, 102));
        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Continuar →");
        jButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(this::jButton7ActionPerformed);
        metodoPago.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 620, 140, 50));

        cancelarMP1.setBackground(new java.awt.Color(158, 33, 33));
        cancelarMP1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cancelarMP1.setForeground(new java.awt.Color(255, 255, 255));
        cancelarMP1.setText("← Volver");
        cancelarMP1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cancelarMP1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelarMP1.addActionListener(this::cancelarMP1ActionPerformed);
        metodoPago.add(cancelarMP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, 140, 50));

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
        btnActualizarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarP.addActionListener(this::btnActualizarPActionPerformed);
        jPanel9.add(btnActualizarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 0, 100, 50));

        btnListasP.setBackground(new java.awt.Color(20, 52, 83));
        btnListasP.setForeground(new java.awt.Color(255, 255, 255));
        btnListasP.setText("Listas");
        btnListasP.setBorderPainted(false);
        btnListasP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnListasP.addActionListener(this::btnListasPActionPerformed);
        jPanel9.add(btnListasP, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 0, 100, 50));

        btnGestionarP.setBackground(new java.awt.Color(20, 52, 83));
        btnGestionarP.setForeground(new java.awt.Color(255, 255, 255));
        btnGestionarP.setText("Gestionar");
        btnGestionarP.setBorderPainted(false);
        btnGestionarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGestionarP.addActionListener(this::btnGestionarPActionPerformed);
        jPanel9.add(btnGestionarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 100, 50));

        productosPanel.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 50));

        lblimg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NAVBAR.png"))); // NOI18N
        lblimg3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblimg3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblimg3MouseClicked(evt);
            }
        });
        productosPanel.add(lblimg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnInformes3.setBackground(new java.awt.Color(225, 250, 255));
        btnInformes3.setForeground(new java.awt.Color(0, 51, 102));
        btnInformes3.setText("Informes");
        btnInformes3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInformes3.setBorderPainted(false);
        btnInformes3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInformes3.setFocusPainted(false);
        productosPanel.add(btnInformes3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 90, 50));

        btnEstadisticas3.setBackground(new java.awt.Color(225, 250, 255));
        btnEstadisticas3.setForeground(new java.awt.Color(0, 51, 102));
        btnEstadisticas3.setText("Estadísticas");
        btnEstadisticas3.setBorderPainted(false);
        btnEstadisticas3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEstadisticas3.setFocusPainted(false);
        btnEstadisticas3.addActionListener(this::btnEstadisticas3ActionPerformed);
        productosPanel.add(btnEstadisticas3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 120, 50));

        btnVentas3.setBackground(new java.awt.Color(225, 250, 255));
        btnVentas3.setForeground(new java.awt.Color(0, 51, 102));
        btnVentas3.setText("Ventas");
        btnVentas3.setBorderPainted(false);
        btnVentas3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas3.setFocusPainted(false);
        btnVentas3.addActionListener(this::btnVentas3ActionPerformed);
        productosPanel.add(btnVentas3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 90, 50));

        btnAdministracion3.setBackground(new java.awt.Color(225, 250, 255));
        btnAdministracion3.setForeground(new java.awt.Color(0, 51, 102));
        btnAdministracion3.setText("Administración");
        btnAdministracion3.setBorderPainted(false);
        btnAdministracion3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdministracion3.setFocusPainted(false);
        btnAdministracion3.addActionListener(this::btnAdministracion3ActionPerformed);
        productosPanel.add(btnAdministracion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 120, 50));

        btnProductos3.setBackground(new java.awt.Color(20, 52, 83));
        btnProductos3.setForeground(new java.awt.Color(255, 255, 255));
        btnProductos3.setText("Productos");
        btnProductos3.setBorderPainted(false);
        btnProductos3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos3.setFocusPainted(false);
        btnProductos3.addActionListener(this::btnProductos3ActionPerformed);
        productosPanel.add(btnProductos3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 100, 50));

        btnProveedores3.setBackground(new java.awt.Color(225, 250, 255));
        btnProveedores3.setForeground(new java.awt.Color(0, 51, 102));
        btnProveedores3.setText("Proveedores");
        btnProveedores3.setBorderPainted(false);
        btnProveedores3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedores3.setFocusPainted(false);
        btnProveedores3.addActionListener(this::btnProveedores3ActionPerformed);
        productosPanel.add(btnProveedores3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 120, 50));

        btnCaja3.setBackground(new java.awt.Color(225, 250, 255));
        btnCaja3.setForeground(new java.awt.Color(0, 51, 102));
        btnCaja3.setText("Caja");
        btnCaja3.setBorderPainted(false);
        btnCaja3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCaja3.setFocusPainted(false);
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
        jLabel33.setText("los productos de tu negocio");
        InicioProdPanel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 320, 20));

        jLabel34.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel34.setText("Gestiona, consulta y actualiza las características de");
        InicioProdPanel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 430, 20));

        productosPanel.add(InicioProdPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 980, 640));

        GestionProdPanel.setBackground(new java.awt.Color(255, 255, 255));
        GestionProdPanel.setMaximumSize(new java.awt.Dimension(980, 640));
        GestionProdPanel.setMinimumSize(new java.awt.Dimension(980, 640));
        GestionProdPanel.setPreferredSize(new java.awt.Dimension(980, 640));
        GestionProdPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnsGestion.setBackground(new java.awt.Color(255, 255, 255));
        btnsGestion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "  Acciones ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        btnsGestion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NuevoP.setBackground(new java.awt.Color(228, 255, 228));
        NuevoP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        NuevoP.setForeground(new java.awt.Color(0, 0, 51));
        NuevoP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        NuevoP.setText("Nuevo ");
        NuevoP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        NuevoP.setFocusPainted(false);
        NuevoP.addActionListener(this::NuevoPActionPerformed);
        btnsGestion.add(NuevoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 33, 320, 40));

        ModificarP.setBackground(new java.awt.Color(240, 240, 240));
        ModificarP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ModificarP.setForeground(new java.awt.Color(0, 0, 51));
        ModificarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar.png"))); // NOI18N
        ModificarP.setText("Modificar ");
        ModificarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModificarP.setFocusPainted(false);
        ModificarP.addActionListener(this::ModificarPActionPerformed);
        btnsGestion.add(ModificarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 320, 40));

        EliminarP.setBackground(new java.awt.Color(255, 230, 230));
        EliminarP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        EliminarP.setForeground(new java.awt.Color(0, 0, 51));
        EliminarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        EliminarP.setText("Eliminar ");
        EliminarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EliminarP.setFocusPainted(false);
        EliminarP.addActionListener(this::EliminarPActionPerformed);
        btnsGestion.add(EliminarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, 320, 40));

        GestionProdPanel.add(btnsGestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 340, 200));

        globGestion.setBackground(new java.awt.Color(255, 255, 255));
        globGestion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos del inventario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        globGestion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalProveedoresP.setBackground(new java.awt.Color(237, 255, 255));
        lblTotalProveedoresP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTotalProveedoresP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalProveedoresP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(93, 157, 255), new java.awt.Color(93, 157, 255), null, null), "Proveedores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        lblTotalProveedoresP.setOpaque(true);
        globGestion.add(lblTotalProveedoresP, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 170, 70));

        lblTotalProductosP.setBackground(new java.awt.Color(237, 255, 255));
        lblTotalProductosP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTotalProductosP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalProductosP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(93, 157, 255), new java.awt.Color(93, 157, 255), null, null), "Total de Productos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        lblTotalProductosP.setOpaque(true);
        globGestion.add(lblTotalProductosP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 170, 70));

        lblStockTotalP.setBackground(new java.awt.Color(237, 255, 255));
        lblStockTotalP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblStockTotalP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStockTotalP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(93, 157, 255), new java.awt.Color(93, 157, 255), null, null), "Stock total", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        lblStockTotalP.setOpaque(true);
        globGestion.add(lblStockTotalP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 170, 70));

        btnBajoStockP.setBackground(new java.awt.Color(255, 255, 204));
        btnBajoStockP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBajoStockP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 0), new java.awt.Color(204, 204, 0), new java.awt.Color(204, 204, 0), new java.awt.Color(255, 255, 0)), "Productos bajo stock", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        btnBajoStockP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBajoStockP.setFocusPainted(false);
        globGestion.add(btnBajoStockP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 170, 80));

        btnEliminadosP.setBackground(new java.awt.Color(51, 51, 51));
        btnEliminadosP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnEliminadosP.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminadosP.setText("2");
        btnEliminadosP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(202, 202, 202), new java.awt.Color(2, 2, 25), new java.awt.Color(2, 2, 25), new java.awt.Color(202, 202, 202)), "Productos eliminados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        btnEliminadosP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminadosP.setFocusPainted(false);
        globGestion.add(btnEliminadosP, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 170, 80));

        btnSinStockP.setBackground(new java.awt.Color(255, 230, 230));
        btnSinStockP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnSinStockP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 204), new java.awt.Color(153, 0, 0), new java.awt.Color(153, 0, 0), new java.awt.Color(255, 204, 204)), "Productos sin stock", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        btnSinStockP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSinStockP.setFocusPainted(false);
        globGestion.add(btnSinStockP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 170, 80));

        GestionProdPanel.add(globGestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 590, 200));

        listaGestion.setBackground(new java.awt.Color(255, 255, 255));
        listaGestion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Inventario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        listaGestion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaProductosP.setBackground(new java.awt.Color(255, 255, 255));
        tablaProductosP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        listaGestion.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 930, 310));

        txtDescripcionPP.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcionPP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDescripcionPP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        listaGestion.add(txtDescripcionPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 320, 60));

        txtCodigoPP.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigoPP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtCodigoPP.setForeground(new java.awt.Color(0, 0, 0));
        txtCodigoPP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Código", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        listaGestion.add(txtCodigoPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, 60));

        cbRubroP1.setBackground(new java.awt.Color(255, 255, 255));
        cbRubroP1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbRubroP1.setForeground(new java.awt.Color(0, 0, 0));
        cbRubroP1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbRubroP1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rurbo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        cbRubroP1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listaGestion.add(cbRubroP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 26, 210, 65));

        cbStockP.setBackground(new java.awt.Color(255, 255, 255));
        cbStockP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbStockP.setForeground(new java.awt.Color(0, 0, 0));
        cbStockP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Ascendente", "Descendente" }));
        cbStockP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Stock", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        cbStockP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listaGestion.add(cbStockP, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 26, 170, 65));

        btnLimpiarFiltroProducto.setBackground(new java.awt.Color(42, 67, 117));
        btnLimpiarFiltroProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rehacer-blanco.png"))); // NOI18N
        btnLimpiarFiltroProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiarFiltroProducto.addActionListener(this::btnLimpiarFiltroProductoActionPerformed);
        listaGestion.add(btnLimpiarFiltroProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 40, 50, 40));

        btnFiltrarProducto.setBackground(new java.awt.Color(204, 204, 204));
        btnFiltrarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnFiltrarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFiltrarProducto.addActionListener(this::btnFiltrarProductoActionPerformed);
        listaGestion.add(btnFiltrarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 50, 40));

        GestionProdPanel.add(listaGestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 950, 420));

        productosPanel.add(GestionProdPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, -1, -1));

        AccionGestion.setBackground(new java.awt.Color(255, 255, 255));
        AccionGestion.setMaximumSize(new java.awt.Dimension(980, 640));
        AccionGestion.setMinimumSize(new java.awt.Dimension(980, 640));
        AccionGestion.setPreferredSize(new java.awt.Dimension(980, 640));
        AccionGestion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHeaderAgregar.setBackground(new java.awt.Color(226, 255, 226));
        lblHeaderAgregar.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        lblHeaderAgregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHeaderAgregar.setToolTipText("");
        lblHeaderAgregar.setOpaque(true);
        AccionGestion.add(lblHeaderAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 60));

        lblHeaderAgregar1.setBackground(new java.awt.Color(226, 255, 226));
        lblHeaderAgregar1.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        lblHeaderAgregar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHeaderAgregar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        lblHeaderAgregar1.setText(" Agregar nuevo producto");
        lblHeaderAgregar1.setToolTipText("");
        lblHeaderAgregar1.setOpaque(true);
        AccionGestion.add(lblHeaderAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 930, 60));

        DatosProdAPanel.setBackground(new java.awt.Color(255, 255, 255));
        DatosProdAPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(98, 135, 165), new java.awt.Color(0, 59, 108)), "DATOS DEL PRODUCTO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 59, 108))); // NOI18N
        DatosProdAPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPrecioP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPrecioP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Precio Unitario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        DatosProdAPanel.add(txtPrecioP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 450, 60));

        txtCodP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCodP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Código", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        DatosProdAPanel.add(txtCodP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 450, 60));

        cbProveedorP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbProveedorP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        cbProveedorP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DatosProdAPanel.add(cbProveedorP, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 450, 60));

        cbIVAP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbIVAP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "IVA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        cbIVAP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DatosProdAPanel.add(cbIVAP, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 450, 60));

        cbRubroP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbRubroP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Rubro del producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        cbRubroP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbRubroP.addActionListener(this::cbRubroPActionPerformed);
        DatosProdAPanel.add(cbRubroP, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 450, 60));

        cbTipoP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbTipoP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo de producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        cbTipoP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbTipoP.addActionListener(this::cbTipoPActionPerformed);
        DatosProdAPanel.add(cbTipoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 450, 60));

        txtDescP.setColumns(20);
        txtDescP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDescP.setRows(5);
        txtDescP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        jScrollPane5.setViewportView(txtDescP);

        DatosProdAPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 450, 110));

        AccionGestion.add(DatosProdAPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 960, 300));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 59, 108), new java.awt.Color(98, 135, 165)), "DATOS DE STOCK", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 59, 108))); // NOI18N
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtStockMinP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtStockMinP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStockMinP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Stock Mínimo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        jPanel11.add(txtStockMinP, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 300, 60));

        txtStockMaxP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtStockMaxP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStockMaxP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Stock Máximo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        jPanel11.add(txtStockMaxP, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, 300, 60));

        txtStockActP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtStockActP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStockActP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Stock Actual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        jPanel11.add(txtStockActP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 300, 60));

        AccionGestion.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 960, 120));

        GVolver.setBackground(new java.awt.Color(242, 76, 76));
        GVolver.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        GVolver.setForeground(new java.awt.Color(255, 255, 255));
        GVolver.setText("Volver");
        GVolver.setBorderPainted(false);
        GVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GVolver.addActionListener(this::GVolverActionPerformed);
        AccionGestion.add(GVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 590, 110, -1));

        AGAceptar.setBackground(new java.awt.Color(10, 72, 100));
        AGAceptar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AGAceptar.setForeground(new java.awt.Color(255, 255, 255));
        AGAceptar.setText("Aceptar");
        AGAceptar.setBorderPainted(false);
        AGAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AGAceptar.addActionListener(this::AGAceptarActionPerformed);
        AccionGestion.add(AGAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 590, 110, -1));

        lblHeaderModificar1.setBackground(new java.awt.Color(203, 203, 203));
        lblHeaderModificar1.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        lblHeaderModificar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHeaderModificar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar.png"))); // NOI18N
        lblHeaderModificar1.setText(" Modificar producto");
        lblHeaderModificar1.setToolTipText("");
        lblHeaderModificar1.setOpaque(true);
        AccionGestion.add(lblHeaderModificar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 930, 60));

        lblHeaderModificar.setBackground(new java.awt.Color(203, 203, 203));
        lblHeaderModificar.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        lblHeaderModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHeaderModificar.setToolTipText("");
        lblHeaderModificar.setOpaque(true);
        AccionGestion.add(lblHeaderModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 60));

        MGAceptar.setBackground(new java.awt.Color(10, 72, 100));
        MGAceptar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        MGAceptar.setForeground(new java.awt.Color(255, 255, 255));
        MGAceptar.setText("Aceptar");
        MGAceptar.setBorderPainted(false);
        MGAceptar.addActionListener(this::MGAceptarActionPerformed);
        AccionGestion.add(MGAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 590, 110, -1));

        productosPanel.add(AccionGestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, -1, -1));

        ListasPPanel.setBackground(new java.awt.Color(255, 255, 255));
        ListasPPanel.setMaximumSize(new java.awt.Dimension(980, 640));
        ListasPPanel.setMinimumSize(new java.awt.Dimension(980, 640));
        ListasPPanel.setPreferredSize(new java.awt.Dimension(980, 640));
        ListasPPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NAVPAGOS.setBackground(new java.awt.Color(255, 255, 255));
        NAVPAGOS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        NAVPAGOS.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAOfertas.setBackground(new java.awt.Color(204, 204, 204));
        btnAOfertas.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnAOfertas.setForeground(new java.awt.Color(51, 51, 51));
        btnAOfertas.setText("Ofertas");
        btnAOfertas.setBorderPainted(false);
        btnAOfertas.addActionListener(this::btnAOfertasActionPerformed);
        NAVPAGOS.add(btnAOfertas, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 450, 80));

        btnAPromociones.setBackground(new java.awt.Color(204, 204, 204));
        btnAPromociones.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnAPromociones.setForeground(new java.awt.Color(51, 51, 51));
        btnAPromociones.setText("Promociones");
        btnAPromociones.setBorderPainted(false);
        btnAPromociones.addActionListener(this::btnAPromocionesActionPerformed);
        NAVPAGOS.add(btnAPromociones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 80));

        ListasPPanel.add(NAVPAGOS, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 860, 80));

        PanelPromociones.setBackground(new java.awt.Color(255, 255, 255));
        PanelPromociones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelNuevaPromo.setBackground(new java.awt.Color(247, 247, 247));
        PanelNuevaPromo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel77.setText("Nueva Promoción");
        PanelNuevaPromo.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel78.setText("Nombre de promoción:");
        PanelNuevaPromo.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel79.setText("Tipo de Condición:");
        PanelNuevaPromo.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 120, -1));

        jLabel80.setText("Descuento:");
        PanelNuevaPromo.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel81.setText("Estado");
        PanelNuevaPromo.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        EstadodePromo.setText("Activa");
        EstadodePromo.addActionListener(this::EstadodePromoActionPerformed);
        PanelNuevaPromo.add(EstadodePromo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 140, -1));

        txtPromocion.addActionListener(this::txtPromocionActionPerformed);
        PanelNuevaPromo.add(txtPromocion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 230, -1));
        PanelNuevaPromo.add(txtDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 230, -1));

        btGuardarPromo.setBackground(new java.awt.Color(0, 102, 255));
        btGuardarPromo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btGuardarPromo.setText("Guardar");
        btGuardarPromo.addActionListener(this::btGuardarPromoActionPerformed);
        PanelNuevaPromo.add(btGuardarPromo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 310, 40));
        PanelNuevaPromo.add(txtTipoCondicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 230, -1));

        btActivarPromo.setBackground(new java.awt.Color(51, 255, 0));
        btActivarPromo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btActivarPromo.setText("Activar");
        btActivarPromo.addActionListener(this::btActivarPromoActionPerformed);
        PanelNuevaPromo.add(btActivarPromo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 150, 40));

        btDesactivarPromo1.setBackground(new java.awt.Color(255, 0, 0));
        btDesactivarPromo1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btDesactivarPromo1.setText("Desactivar");
        btDesactivarPromo1.addActionListener(this::btDesactivarPromo1ActionPerformed);
        PanelNuevaPromo.add(btDesactivarPromo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, 150, 40));

        PanelPromociones.add(PanelNuevaPromo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 360, 440));

        PanelPromoExistente.setBackground(new java.awt.Color(247, 247, 247));
        PanelPromoExistente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel82.setText("Promociones Existentes");
        PanelPromoExistente.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        tblPromo.setModel(new javax.swing.table.DefaultTableModel(
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
        tablePromo.setViewportView(tblPromo);

        PanelPromoExistente.add(tablePromo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 480, 390));

        PanelPromociones.add(PanelPromoExistente, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 510, 460));

        ListasPPanel.add(PanelPromociones, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 960, 500));

        PanelOfertas.setBackground(new java.awt.Color(255, 255, 255));
        PanelOfertas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelNuevaOferta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbProductoOferta.addItemListener(this::cbProductoOfertaItemStateChanged);
        PanelNuevaOferta.add(cbProductoOferta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 300, -1));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel83.setText("Nueva Oferta");
        PanelNuevaOferta.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel84.setText("Buscar Producto:");
        PanelNuevaOferta.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel64.setText("Porcentaje de Descuento: ");
        PanelNuevaOferta.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        PrecioNuevo.setText("Precio del producto en ofeta:");
        PrecioNuevo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PrecioNuevoKeyReleased(evt);
            }
        });
        PanelNuevaOferta.add(PrecioNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 160, -1));

        jTextField11.addActionListener(this::jTextField11ActionPerformed);
        PanelNuevaOferta.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 40, -1));

        FechaInicioOferta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PanelNuevaOferta.add(FechaInicioOferta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 280, 30));

        jLabel66.setText("Fecha Inicio");
        PanelNuevaOferta.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        FechaFinOferta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PanelNuevaOferta.add(FechaFinOferta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 280, 30));

        jLabel67.setText("Fecha Fin");
        PanelNuevaOferta.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        btGuardarOferta.setBackground(new java.awt.Color(0, 102, 255));
        btGuardarOferta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btGuardarOferta.setText("Guardar");
        btGuardarOferta.addActionListener(this::btGuardarOfertaActionPerformed);
        PanelNuevaOferta.add(btGuardarOferta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 310, 40));

        btPrevisualizarPrecio.setText("Previsualizar Precio");
        btPrevisualizarPrecio.addActionListener(this::btPrevisualizarPrecioActionPerformed);
        PanelNuevaOferta.add(btPrevisualizarPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 140, -1));

        btLimpiarOferta.setText("Limpiar");
        btLimpiarOferta.addActionListener(this::btLimpiarOfertaActionPerformed);
        PanelNuevaOferta.add(btLimpiarOferta, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, -1, -1));

        PanelOfertas.add(PanelNuevaOferta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 330, 450));

        PanelOfertaExistente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel68.setText("Ofertas Existentes");
        PanelOfertaExistente.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        tblOferta.setModel(new javax.swing.table.DefaultTableModel(
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
        tableOferta.setViewportView(tblOferta);

        PanelOfertaExistente.add(tableOferta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 540, 360));

        PanelOfertas.add(PanelOfertaExistente, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 560, 430));

        txtPrecioOferta.setText("Seleccione la oferta que desea imprimir:");
        PanelOfertas.add(txtPrecioOferta, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 450, -1, -1));

        btImprimirOferta.setText("Imprimir");
        btImprimirOferta.addActionListener(this::btImprimirOfertaActionPerformed);
        PanelOfertas.add(btImprimirOferta, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 450, -1, -1));

        ListasPPanel.add(PanelOfertas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 950, 480));

        productosPanel.add(ListasPPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, -1, -1));

        getContentPane().add(productosPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 740));

        estadísticaPanel.setBackground(new java.awt.Color(255, 255, 255));
        estadísticaPanel.setMaximumSize(new java.awt.Dimension(980, 740));
        estadísticaPanel.setMinimumSize(new java.awt.Dimension(980, 740));

        lblimg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NAVBAR.png"))); // NOI18N
        lblimg2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblimg2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblimg2MouseClicked(evt);
            }
        });

        btnInformes2.setBackground(new java.awt.Color(204, 255, 255));
        btnInformes2.setForeground(new java.awt.Color(0, 51, 102));
        btnInformes2.setText("Informes");
        btnInformes2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInformes2.setBorderPainted(false);
        btnInformes2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnCaja2.setBackground(new java.awt.Color(204, 255, 255));
        btnCaja2.setForeground(new java.awt.Color(0, 51, 102));
        btnCaja2.setText("Caja");
        btnCaja2.setBorderPainted(false);
        btnCaja2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnProveedores2.setBackground(new java.awt.Color(204, 255, 255));
        btnProveedores2.setForeground(new java.awt.Color(0, 51, 102));
        btnProveedores2.setText("Proveedores");
        btnProveedores2.setBorderPainted(false);
        btnProveedores2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedores2.addActionListener(this::btnProveedores2ActionPerformed);

        btnAdministracion2.setBackground(new java.awt.Color(204, 255, 255));
        btnAdministracion2.setForeground(new java.awt.Color(0, 51, 102));
        btnAdministracion2.setText("Administración");
        btnAdministracion2.setBorderPainted(false);
        btnAdministracion2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdministracion2.addActionListener(this::btnAdministracion2ActionPerformed);

        btnVentas2.setBackground(new java.awt.Color(204, 255, 255));
        btnVentas2.setForeground(new java.awt.Color(0, 51, 102));
        btnVentas2.setText("Ventas");
        btnVentas2.setBorderPainted(false);
        btnVentas2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas2.addActionListener(this::btnVentas2ActionPerformed);

        btnEstadisticas2.setBackground(new java.awt.Color(20, 52, 83));
        btnEstadisticas2.setForeground(new java.awt.Color(255, 255, 255));
        btnEstadisticas2.setText("Estadísticas");
        btnEstadisticas2.setBorderPainted(false);
        btnEstadisticas2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEstadisticas2.addActionListener(this::btnEstadisticas2ActionPerformed);

        btnProductos2.setBackground(new java.awt.Color(204, 255, 255));
        btnProductos2.setForeground(new java.awt.Color(0, 51, 102));
        btnProductos2.setText("Productos");
        btnProductos2.setBorderPainted(false);
        btnProductos2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos2.addActionListener(this::btnProductos2ActionPerformed);

        panelContenidoEstadisticas.setBackground(new java.awt.Color(0, 0, 0));
        panelContenidoEstadisticas.setPreferredSize(new java.awt.Dimension(980, 50));

        btnInformeVentas.setBackground(new java.awt.Color(20, 52, 83));
        btnInformeVentas.setForeground(new java.awt.Color(255, 255, 255));
        btnInformeVentas.setText("Ventas");
        btnInformeVentas.setBorderPainted(false);
        btnInformeVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInformeVentas.addActionListener(this::btnInformeVentasActionPerformed);

        btnInformeClientes.setBackground(new java.awt.Color(20, 52, 83));
        btnInformeClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnInformeClientes.setText("Clientes");
        btnInformeClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInformeClientes.addActionListener(this::btnInformeClientesActionPerformed);

        btnInformeStock.setBackground(new java.awt.Color(20, 52, 83));
        btnInformeStock.setForeground(new java.awt.Color(255, 255, 255));
        btnInformeStock.setText("Stock");
        btnInformeStock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        panelGraficoStockInforme.setName("panelGraficoStockInforme"); // NOI18N
        panelGraficoStockInforme.setPreferredSize(new java.awt.Dimension(1000, 1000));
        panelGraficoStockInforme.setLayout(new java.awt.BorderLayout());

        btnExportarStockPdf.setText("Exportar a PDF");
        btnExportarStockPdf.addActionListener(this::btnExportarStockPdfActionPerformed);

        cbTipoReporteStock.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stock total por rubro", "Productos con Stock Critico" }));
        cbTipoReporteStock.addActionListener(this::cbTipoReporteStockActionPerformed);

        btnActualizarInformeStock.setText("Actualizar grafico");
        btnActualizarInformeStock.addActionListener(this::btnActualizarInformeStockActionPerformed);

        btnExportarStockExcel.setText("Exportar a Excel");
        btnExportarStockExcel.addActionListener(this::btnExportarStockExcelActionPerformed);

        lblTituloInformeStock.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloInformeStock.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 40)); // NOI18N
        lblTituloInformeStock.setForeground(new java.awt.Color(0, 66, 115));
        lblTituloInformeStock.setText("Estadísticas de Ventas");
        lblTituloInformeStock.setToolTipText("");
        lblTituloInformeStock.setMaximumSize(new java.awt.Dimension(554, 54));
        lblTituloInformeStock.setMinimumSize(new java.awt.Dimension(554, 54));
        lblTituloInformeStock.setPreferredSize(new java.awt.Dimension(554, 54));

        lblDescripcionReporte.setText("jLabel28");

        javax.swing.GroupLayout panelInformeStockLayout = new javax.swing.GroupLayout(panelInformeStock);
        panelInformeStock.setLayout(panelInformeStockLayout);
        panelInformeStockLayout.setHorizontalGroup(
            panelInformeStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformeStockLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelInformeStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelGraficoStockInforme, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panelInformeStockLayout.createSequentialGroup()
                        .addGroup(panelInformeStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelInformeStockLayout.createSequentialGroup()
                                .addComponent(lblTituloInformeStock, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112)
                                .addComponent(cbTipoReporteStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnActualizarInformeStock))
                            .addGroup(panelInformeStockLayout.createSequentialGroup()
                                .addComponent(btnExportarStockPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExportarStockExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(lblDescripcionReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 100, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelInformeStockLayout.setVerticalGroup(
            panelInformeStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformeStockLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelInformeStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformeStockLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(panelInformeStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTipoReporteStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizarInformeStock)))
                    .addComponent(lblTituloInformeStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGraficoStockInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInformeStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformeStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExportarStockPdf)
                        .addComponent(btnExportarStockExcel))
                    .addComponent(lblDescripcionReporte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        InicioEstPanel.setBackground(new java.awt.Color(255, 255, 255));
        InicioEstPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/estadsiticas-img.jpg"))); // NOI18N
        InicioEstPanel.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel61.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(20, 52, 83));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("ESTADÍSTICAS");
        InicioEstPanel.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 340, 50));

        jLabel62.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel62.setText("la actividad comercial de tu empresa");
        InicioEstPanel.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 320, 290, 20));

        jLabel63.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel63.setText("Consulta los datos actualizados en tiempo real de");
        InicioEstPanel.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 400, 20));

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
            .addGroup(estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(InicioEstPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE))
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
                    .addGap(0, 131, Short.MAX_VALUE)
                    .addComponent(panelInformeStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, estadísticaPanelLayout.createSequentialGroup()
                    .addGap(0, 99, Short.MAX_VALUE)
                    .addComponent(panelInformeClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(estadísticaPanelLayout.createSequentialGroup()
                    .addGap(99, 99, 99)
                    .addComponent(InicioEstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(39, Short.MAX_VALUE)))
        );

        getContentPane().add(estadísticaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 740));

        proveedoresPanel.setBackground(new java.awt.Color(255, 255, 255));
        proveedoresPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        proveedoresPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImgProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NAVBAR.png"))); // NOI18N
        lblImgProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImgProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImgProveedoresMouseClicked(evt);
            }
        });
        proveedoresPanel.add(lblImgProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnInformesProveedores.setBackground(new java.awt.Color(204, 255, 255));
        btnInformesProveedores.setForeground(new java.awt.Color(0, 51, 102));
        btnInformesProveedores.setText("Informes");
        btnInformesProveedores.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInformesProveedores.setBorderPainted(false);
        btnInformesProveedores.addActionListener(this::btnInformesProveedoresActionPerformed);
        proveedoresPanel.add(btnInformesProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 90, 50));

        btnCajaProveedores.setBackground(new java.awt.Color(204, 255, 255));
        btnCajaProveedores.setForeground(new java.awt.Color(0, 51, 102));
        btnCajaProveedores.setText("Caja");
        btnCajaProveedores.setBorderPainted(false);
        proveedoresPanel.add(btnCajaProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 100, 50));

        btnSeccionProveedores.setBackground(new java.awt.Color(20, 52, 83));
        btnSeccionProveedores.setForeground(new java.awt.Color(255, 255, 255));
        btnSeccionProveedores.setText("Proveedores");
        btnSeccionProveedores.setBorderPainted(false);
        btnSeccionProveedores.addActionListener(this::btnSeccionProveedoresActionPerformed);
        proveedoresPanel.add(btnSeccionProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 120, 50));

        btnAdministracionProveedores.setBackground(new java.awt.Color(204, 255, 255));
        btnAdministracionProveedores.setForeground(new java.awt.Color(0, 51, 102));
        btnAdministracionProveedores.setText("Administración");
        btnAdministracionProveedores.setBorderPainted(false);
        btnAdministracionProveedores.addActionListener(this::btnAdministracionProveedoresActionPerformed);
        proveedoresPanel.add(btnAdministracionProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 120, 50));

        btnVentasProveedores.setBackground(new java.awt.Color(204, 255, 255));
        btnVentasProveedores.setForeground(new java.awt.Color(0, 51, 102));
        btnVentasProveedores.setText("Ventas");
        btnVentasProveedores.setBorderPainted(false);
        btnVentasProveedores.addActionListener(this::btnVentasProveedoresActionPerformed);
        proveedoresPanel.add(btnVentasProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 90, 50));

        btnEstadisticasProveedores.setBackground(new java.awt.Color(204, 255, 255));
        btnEstadisticasProveedores.setForeground(new java.awt.Color(0, 51, 102));
        btnEstadisticasProveedores.setText("Estadísticas");
        btnEstadisticasProveedores.setBorderPainted(false);
        btnEstadisticasProveedores.addActionListener(this::btnEstadisticasProveedoresActionPerformed);
        proveedoresPanel.add(btnEstadisticasProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 120, 50));

        btnProductosProveedores.setBackground(new java.awt.Color(204, 255, 255));
        btnProductosProveedores.setForeground(new java.awt.Color(0, 51, 102));
        btnProductosProveedores.setText("Productos");
        btnProductosProveedores.setBorderPainted(false);
        btnProductosProveedores.addActionListener(this::btnProductosProveedoresActionPerformed);
        proveedoresPanel.add(btnProductosProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 100, 50));

        panelListaProveedores.setBackground(new java.awt.Color(255, 255, 255));
        panelListaProveedores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaProveedores.setBackground(new java.awt.Color(255, 255, 255));
        tablaProveedores.setForeground(new java.awt.Color(0, 0, 0));
        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Razón Social", "CUIT", "Teléfono", "Email", "Dirección", "Rubro", "Estado"
            }
        ));
        scrollTablaProveedores.setViewportView(tablaProveedores);

        panelListaProveedores.add(scrollTablaProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 950, 420));

        AccionesProveedores.setBackground(new java.awt.Color(255, 255, 255));
        AccionesProveedores.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray), "Acciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 20), new java.awt.Color(0, 0, 0))); // NOI18N
        AccionesProveedores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegistrarProveedor.setBackground(new java.awt.Color(228, 255, 228));
        btnRegistrarProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRegistrarProveedor.setForeground(new java.awt.Color(0, 0, 0));
        btnRegistrarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnRegistrarProveedor.setText("  Registrar");
        btnRegistrarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarProveedor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnRegistrarProveedor.setPreferredSize(new java.awt.Dimension(76, 28));
        btnRegistrarProveedor.addActionListener(this::btnRegistrarProveedorActionPerformed);
        AccionesProveedores.add(btnRegistrarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 210, 50));

        btnModificarProveedor.setBackground(new java.awt.Color(222, 221, 221));
        btnModificarProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnModificarProveedor.setForeground(new java.awt.Color(0, 0, 0));
        btnModificarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar.png"))); // NOI18N
        btnModificarProveedor.setText(" Modificar");
        btnModificarProveedor.setPreferredSize(new java.awt.Dimension(76, 28));
        btnModificarProveedor.addActionListener(this::btnModificarProveedorActionPerformed);
        AccionesProveedores.add(btnModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 210, 50));

        btnEliminarProveedor.setBackground(new java.awt.Color(255, 230, 230));
        btnEliminarProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEliminarProveedor.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminarProveedor.setText(" Eliminar");
        btnEliminarProveedor.setPreferredSize(new java.awt.Dimension(76, 28));
        btnEliminarProveedor.addActionListener(this::btnEliminarProveedorActionPerformed);
        AccionesProveedores.add(btnEliminarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 210, 50));

        btnExportarProveedores.setBackground(new java.awt.Color(255, 255, 153));
        btnExportarProveedores.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnExportarProveedores.setForeground(new java.awt.Color(0, 0, 0));
        btnExportarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/descargar.png"))); // NOI18N
        btnExportarProveedores.setText(" Exportar");
        btnExportarProveedores.setPreferredSize(new java.awt.Dimension(76, 28));
        btnExportarProveedores.addActionListener(this::btnExportarProveedoresActionPerformed);
        AccionesProveedores.add(btnExportarProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 210, 50));

        panelListaProveedores.add(AccionesProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 950, 100));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray), "Filtros de Búsqueda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 20), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscarRazonSocial.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarRazonSocial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscarRazonSocial.setActionCommand("<Not Set>");
        txtBuscarRazonSocial.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray), "Razón Social:/CUIT: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        txtBuscarRazonSocial.setPreferredSize(new java.awt.Dimension(76, 28));
        jPanel12.add(txtBuscarRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 250, 50));

        txtBuscarRubro.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarRubro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscarRubro.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscarRubro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray), "Rubro:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        txtBuscarRubro.setPreferredSize(new java.awt.Dimension(76, 28));
        jPanel12.add(txtBuscarRubro, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 240, 50));

        cbBuscarEstado.setBackground(new java.awt.Color(255, 255, 255));
        cbBuscarEstado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbBuscarEstado.setForeground(new java.awt.Color(0, 0, 0));
        cbBuscarEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Activo", "Inactivo" }));
        cbBuscarEstado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.gray), "Estado:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel12.add(cbBuscarEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 160, 50));

        btnBuscarProveedor.setBackground(new java.awt.Color(0, 66, 115));
        btnBuscarProveedor.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnBuscarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnBuscarProveedor.setText("Buscar");
        btnBuscarProveedor.setPreferredSize(new java.awt.Dimension(76, 28));
        btnBuscarProveedor.addActionListener(this::btnBuscarProveedorActionPerformed);
        jPanel12.add(btnBuscarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 115, 40));

        btnLimpiarFiltrosProveedor.setBackground(new java.awt.Color(102, 102, 102));
        btnLimpiarFiltrosProveedor.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnLimpiarFiltrosProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarFiltrosProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rehacer-blanco.png"))); // NOI18N
        btnLimpiarFiltrosProveedor.setText("Limpiar");
        btnLimpiarFiltrosProveedor.setPreferredSize(new java.awt.Dimension(76, 28));
        btnLimpiarFiltrosProveedor.addActionListener(this::btnLimpiarFiltrosProveedorActionPerformed);
        jPanel12.add(btnLimpiarFiltrosProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 40, 115, 40));

        panelListaProveedores.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 950, 90));

        jPanel14.setBackground(new java.awt.Color(11, 55, 86));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 990, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        panelListaProveedores.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 20));

        proveedoresPanel.add(panelListaProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 50, 990, 690));

        panelRegistrarProveedor.setBackground(new java.awt.Color(255, 255, 255));
        panelRegistrarProveedor.setOpaque(false);
        panelRegistrarProveedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNuevoProveedorR.setBackground(new java.awt.Color(200, 255, 214));
        lblNuevoProveedorR.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblNuevoProveedorR.setForeground(new java.awt.Color(51, 51, 51));
        lblNuevoProveedorR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNuevoProveedorR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        lblNuevoProveedorR.setText(" Agregar nuevo proveedor");
        lblNuevoProveedorR.setMaximumSize(new java.awt.Dimension(75, 23));
        lblNuevoProveedorR.setMinimumSize(new java.awt.Dimension(75, 16));
        lblNuevoProveedorR.setOpaque(true);
        lblNuevoProveedorR.setPreferredSize(new java.awt.Dimension(75, 16));
        panelRegistrarProveedor.add(lblNuevoProveedorR, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 920, 70));

        jPanel15.setBackground(new java.awt.Color(11, 55, 86));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 990, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        panelRegistrarProveedor.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 20));

        panelDatosProveedorRegistro.setBackground(new java.awt.Color(255, 255, 255));
        panelDatosProveedorRegistro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(19, 42, 89), new java.awt.Color(19, 42, 89)), "Datos del nuevo proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 28), new java.awt.Color(19, 42, 89))); // NOI18N
        panelDatosProveedorRegistro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNombreProveedor.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, null), "Razón Social / Nombre: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        panelDatosProveedorRegistro.add(txtNombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 570, 70));

        txtCuitProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtCuitProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCuitProveedor.setForeground(new java.awt.Color(0, 0, 0));
        txtCuitProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuitProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, null), "CUIT / CUIL:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        panelDatosProveedorRegistro.add(txtCuitProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 570, 70));

        txtTelefonoProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefonoProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTelefonoProveedor.setForeground(new java.awt.Color(0, 0, 0));
        txtTelefonoProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefonoProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, null), "Teléfono: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        panelDatosProveedorRegistro.add(txtTelefonoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 570, 70));

        txtEmailProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtEmailProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtEmailProveedor.setForeground(new java.awt.Color(0, 0, 0));
        txtEmailProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmailProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, null), "Email: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        panelDatosProveedorRegistro.add(txtEmailProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 275, 890, 70));

        txtDireccionProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccionProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDireccionProveedor.setForeground(new java.awt.Color(0, 0, 0));
        txtDireccionProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccionProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, null), "Dirección:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        panelDatosProveedorRegistro.add(txtDireccionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 890, 70));

        txtRubroProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtRubroProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtRubroProveedor.setForeground(new java.awt.Color(0, 0, 0));
        txtRubroProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRubroProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, null), "Rubro: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        panelDatosProveedorRegistro.add(txtRubroProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 425, 890, 70));

        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon-mp-blue.png"))); // NOI18N
        panelDatosProveedorRegistro.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 140, 160));

        btnCancelarRegistroProveedor.setBackground(new java.awt.Color(167, 51, 51));
        btnCancelarRegistroProveedor.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnCancelarRegistroProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarRegistroProveedor.setText("Cancelar");
        btnCancelarRegistroProveedor.setBorder(null);
        btnCancelarRegistroProveedor.setPreferredSize(new java.awt.Dimension(76, 28));
        btnCancelarRegistroProveedor.addActionListener(this::btnCancelarRegistroProveedorActionPerformed);
        panelDatosProveedorRegistro.add(btnCancelarRegistroProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 510, 140, 50));

        btnConfirmarRegistroProveedor.setBackground(new java.awt.Color(10, 72, 100));
        btnConfirmarRegistroProveedor.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnConfirmarRegistroProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmarRegistroProveedor.setText("Registrar");
        btnConfirmarRegistroProveedor.setBorder(null);
        btnConfirmarRegistroProveedor.setPreferredSize(new java.awt.Dimension(76, 28));
        btnConfirmarRegistroProveedor.addActionListener(this::btnConfirmarRegistroProveedorActionPerformed);
        panelDatosProveedorRegistro.add(btnConfirmarRegistroProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 510, 140, 50));

        panelRegistrarProveedor.add(panelDatosProveedorRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 960, 580));

        lblNP2.setBackground(new java.awt.Color(200, 255, 214));
        lblNP2.setText("     ");
        lblNP2.setOpaque(true);
        panelRegistrarProveedor.add(lblNP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 60, 80));

        proveedoresPanel.add(panelRegistrarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 780));

        panelModificarProveedor.setBackground(new java.awt.Color(153, 153, 153));
        panelModificarProveedor.setMaximumSize(new java.awt.Dimension(980, 780));
        panelModificarProveedor.setMinimumSize(new java.awt.Dimension(980, 780));
        panelModificarProveedor.setOpaque(false);
        panelModificarProveedor.setPreferredSize(new java.awt.Dimension(980, 780));
        panelModificarProveedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTituloModificarProveedor.setBackground(new java.awt.Color(204, 204, 204));
        lblTituloModificarProveedor.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        lblTituloModificarProveedor.setForeground(new java.awt.Color(0, 0, 0));
        lblTituloModificarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar.png"))); // NOI18N
        lblTituloModificarProveedor.setText(" Modificar Proveedor");
        lblTituloModificarProveedor.setMaximumSize(new java.awt.Dimension(75, 23));
        lblTituloModificarProveedor.setMinimumSize(new java.awt.Dimension(75, 16));
        lblTituloModificarProveedor.setOpaque(true);
        lblTituloModificarProveedor.setPreferredSize(new java.awt.Dimension(75, 16));
        panelModificarProveedor.add(lblTituloModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 910, 70));

        jPanel16.setBackground(new java.awt.Color(11, 55, 86));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 990, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        panelModificarProveedor.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 10));

        jLabel65.setBackground(new java.awt.Color(204, 204, 204));
        jLabel65.setText(" ");
        jLabel65.setOpaque(true);
        panelModificarProveedor.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 70, 70));

        panelDatosProveedorModificar.setBackground(new java.awt.Color(255, 255, 255));
        panelDatosProveedorModificar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(19, 42, 89), new java.awt.Color(19, 42, 89)), "Datos del proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 28), new java.awt.Color(19, 42, 89))); // NOI18N
        panelDatosProveedorModificar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreModificarProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreModificarProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNombreModificarProveedor.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreModificarProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreModificarProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, null), "Razón Social/Nombre:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        panelDatosProveedorModificar.add(txtNombreModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 570, 70));

        txtCuitModificarProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtCuitModificarProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCuitModificarProveedor.setForeground(new java.awt.Color(0, 0, 0));
        txtCuitModificarProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuitModificarProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, null), "CUIT/CUIL:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        panelDatosProveedorModificar.add(txtCuitModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 135, 570, 70));

        txtTelefonoModificarProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefonoModificarProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTelefonoModificarProveedor.setForeground(new java.awt.Color(0, 0, 0));
        txtTelefonoModificarProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefonoModificarProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, null), "Teléfono:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        panelDatosProveedorModificar.add(txtTelefonoModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 205, 570, 70));

        btnGuardarModificacionProveedor.setBackground(new java.awt.Color(10, 72, 100));
        btnGuardarModificacionProveedor.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnGuardarModificacionProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarModificacionProveedor.setText("Modificar");
        btnGuardarModificacionProveedor.setPreferredSize(new java.awt.Dimension(76, 28));
        btnGuardarModificacionProveedor.addActionListener(this::btnGuardarModificacionProveedorActionPerformed);
        panelDatosProveedorModificar.add(btnGuardarModificacionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 520, 140, 50));

        btnCancelarModificacionProveedor.setBackground(new java.awt.Color(167, 51, 51));
        btnCancelarModificacionProveedor.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btnCancelarModificacionProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarModificacionProveedor.setText("Cancelar");
        btnCancelarModificacionProveedor.setPreferredSize(new java.awt.Dimension(76, 28));
        btnCancelarModificacionProveedor.addActionListener(this::btnCancelarModificacionProveedorActionPerformed);
        panelDatosProveedorModificar.add(btnCancelarModificacionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 520, 140, 50));

        txtEmailModificarProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtEmailModificarProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtEmailModificarProveedor.setForeground(new java.awt.Color(0, 0, 0));
        txtEmailModificarProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmailModificarProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, null), "Email:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        panelDatosProveedorModificar.add(txtEmailModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 345, 890, 70));

        txtDireccionModificarProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccionModificarProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDireccionModificarProveedor.setForeground(new java.awt.Color(0, 0, 0));
        txtDireccionModificarProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccionModificarProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, null), "Dirección:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        panelDatosProveedorModificar.add(txtDireccionModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 275, 890, 70));

        txtRubroModificarProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtRubroModificarProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtRubroModificarProveedor.setForeground(new java.awt.Color(0, 0, 0));
        txtRubroModificarProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRubroModificarProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, null), "Rubro:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        panelDatosProveedorModificar.add(txtRubroModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 415, 890, 70));

        iconmp324.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon-mp-blue.png"))); // NOI18N
        panelDatosProveedorModificar.add(iconmp324, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        panelModificarProveedor.add(panelDatosProveedorModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 940, 590));

        proveedoresPanel.add(panelModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 780));

        getContentPane().add(proveedoresPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Se muestran los paneles que estan en TRUE, los demas poner en FALSE para no mezclar
    private void iniciarPaneles() {
        ocultarProveedores();
        ventasPanel.setVisible(false);
        ListaPanel.setVisible(false);
        administracionPanel.setVisible(false);
        metodoPago.setVisible(false);
        PagosPanel.setVisible(false);
        PagosPanelCampos.setVisible(false);
        PanelCalculo.setVisible(false);
        PanelBotones.setVisible(false);
        estadísticaPanel.setVisible(false);
        productosPanel.setVisible(false);

        clickLogoAMP();
        iniciarActualizacionEstadisticas();
    }

    private void inicializarPaneles() {
// 1. Cargar Combo de Productos para Ofertas
        cbProductoOferta.setModel(nOferta.cargarComboProductos());
        cbProductoOferta.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Modelos.Producto) {
                    Modelos.Producto p = (Modelos.Producto) value;
                    setText(p.getDescripcion() + " - $" + String.format("%.2f", p.getPrecioUnitario()));
                }
                return this;
            }
        });

        // 2. Cargar Modelos de Datos en las Tablas
        tblPromo.setModel(nPromocion.cargarTablaPromociones());
        tblOferta.setModel(nOferta.cargarTablaOfertas());

        // 3. Bloquear reordenamiento de columnas en ambas tablas
        tblPromo.getTableHeader().setReorderingAllowed(false);
        tblOferta.getTableHeader().setReorderingAllowed(false);
    }

    //ADMINISTRACION - REGISTRO CUENTA CORRIENTE

    private void btnAEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAEmpresaActionPerformed
        btnAEmpresa.setBackground(new Color(2, 33, 55));
        btnAPersona.setBackground(new Color(0, 66, 115));
        opc = false;

    }//GEN-LAST:event_btnAEmpresaActionPerformed

    private void btnAPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAPersonaActionPerformed
        btnAPersona.setBackground(new Color(2, 33, 55));
        btnAEmpresa.setBackground(new Color(0, 66, 115));
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

        tablaListaCC.setModel(modelo);
        formateartablaListaCC();
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
        InicioAdmPanel.setVisible(false);
        PagosPanel.setVisible(false);
        ListaPanel.setVisible(true);
        btnAListas.setBackground(new Color(30, 90, 142));
        btnAClientes.setBackground(new Color(20, 52, 83));
        btnAPagos.setBackground(new Color(20, 52, 83));
        GestionAdmPanel.setVisible(false);
        jScrollPane1.setVisible(true);
        verDetallePanel.setVisible(false);
    }//GEN-LAST:event_btnAListasActionPerformed

    private void btnAClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAClientesActionPerformed
        InicioAdmPanel.setVisible(false);
        ListaPanel.setVisible(false);
        verDetallePanel.setVisible(false);
        PagosPanel.setVisible(false);
        btnAListas.setBackground(new Color(20, 52, 83));
        btnAClientes.setBackground(new Color(30, 90, 142));
        btnAPagos.setBackground(new Color(20, 52, 83));

        jScrollPane1.setVisible(false);
        GestionAdmPanel.setVisible(true);

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
        int fila = tablaListaCC.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una cuenta corriente");
            return;
        }

        try {

            String cuit = tablaListaCC.getValueAt(fila, 2).toString();
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
                GestionAdmPanel.setVisible(true);

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
                    = (javax.swing.table.DefaultTableModel) tablaListaCC.getModel();

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
                    = (javax.swing.table.DefaultTableModel) tablaListaCC.getModel();

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
        tablaListaCC.setModel(modelo);

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

        InicioAdmPanel.setVisible(false);
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
        GestionAdmPanel.setVisible(false);

        // Forzar actualización visual
        PagosPanel.revalidate();
        PagosPanel.repaint();

        getContentPane().revalidate();
        getContentPane().repaint();
    }//GEN-LAST:event_btnAPagosActionPerformed

    private void formatearTablaVenta() {
        tablaVenta.setRowSorter(null);
        tablaVenta.getColumnModel().getColumn(0).setPreferredWidth(150);
        tablaVenta.getColumnModel().getColumn(1).setPreferredWidth(405);
        tablaVenta.getColumnModel().getColumn(2).setPreferredWidth(100);
        tablaVenta.getColumnModel().getColumn(3).setPreferredWidth(150);
        tablaVenta.getColumnModel().getColumn(4).setPreferredWidth(132);
        tablaVenta.setRowHeight(30);
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        tablaVenta.getColumnModel().getColumn(0).setCellRenderer(centro);
        tablaVenta.getColumnModel().getColumn(1).setCellRenderer(centro);
        tablaVenta.getColumnModel().getColumn(2).setCellRenderer(centro);
        tablaVenta.getColumnModel().getColumn(3).setCellRenderer(centro);
        tablaVenta.getColumnModel().getColumn(4).setCellRenderer(centro);

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
        ocultarProveedores();
        InicioPanel.setVisible(false);
        metodoPago.setVisible(false);
        productosPanel.setVisible(false);
        ventasPanel.setVisible(false);
        estadísticaPanel.setVisible(false);
        administracionPanel.setVisible(true);
        InicioAdmPanel.setVisible(true);
        GestionAdmPanel.setVisible(false);
        ListaPanel.setVisible(false);
        PagosPanel.setVisible(false);
        verDetallePanel.setVisible(false);
        btnAClientes.setBackground(new Color(20, 52, 83));
        btnAPagos.setBackground(new Color(20, 52, 83));
        btnAListas.setBackground(new Color(20, 52, 83));
    }

    private void clickVentas() {
        ocultarProveedores();
        InicioPanel.setVisible(false);
        metodoPago.setVisible(false);
        productosPanel.setVisible(false);
        estadísticaPanel.setVisible(false);
        administracionPanel.setVisible(false);
        ventasPanel.setVisible(true);
        formatearTablaVenta();
    }

    private void clickProductos() {
        ocultarProveedores();
        InicioPanel.setVisible(false);
        metodoPago.setVisible(false);
        estadísticaPanel.setVisible(false);
        administracionPanel.setVisible(false);
        ventasPanel.setVisible(false);
        GestionProdPanel.setVisible(false);
        AccionGestion.setVisible(false);
        InicioProdPanel.setVisible(true);
        productosPanel.setVisible(true);
        btnGestionarP.setBackground(new Color(20, 52, 83));
        btnListasP.setBackground(new Color(20, 52, 83));
        btnActualizarP.setBackground(new Color(20, 52, 83));
        PanelOfertas.setVisible(false);
        PanelPromociones.setVisible(false);
        NAVPAGOS.setVisible(false);
    }

    private void clickLogoAMP() {
        administracionPanel.setVisible(false);
        ventasPanel.setVisible(false);
        metodoPago.setVisible(false);
        productosPanel.setVisible(false);
        estadísticaPanel.setVisible(false);
        proveedoresPanel.setVisible(false);
        InicioPanel.setVisible(true);
        PanelInicioPresentacion.setVisible(true);
        PanelConfiguracion.setVisible(false);
    }

    private void clickEstadisticas() {
        ocultarProveedores();
        InicioPanel.setVisible(false);
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

            n = NNegocio.getNegocioActual();
            Paragraph direccion = new Paragraph(n.getProvinciaNegocio() + " - " + n.getDomicilioNegocio(), subtituloFont);
            direccion.setAlignment(Element.ALIGN_RIGHT);
            Paragraph telefono = new Paragraph("Tel: " + n.getTelefonoNegocio(), subtituloFont);
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
                float porcentaje = p.getIdIVA().getPorcentaje();
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

    private void formateartablaListaCC() {
        tablaListaCC.setRowSorter(null);
        tablaListaCC.getColumnModel().getColumn(0).setPreferredWidth(250);
        tablaListaCC.getColumnModel().getColumn(1).setPreferredWidth(155);
        tablaListaCC.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaListaCC.getColumnModel().getColumn(3).setPreferredWidth(160);
        tablaListaCC.getColumnModel().getColumn(4).setPreferredWidth(240);
        tablaListaCC.getColumnModel().getColumn(5).setPreferredWidth(150);
        tablaListaCC.setRowHeight(30);
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        tablaListaCC.getColumnModel().getColumn(0).setCellRenderer(centro);
        tablaListaCC.getColumnModel().getColumn(1).setCellRenderer(centro);
        tablaListaCC.getColumnModel().getColumn(2).setCellRenderer(centro);
        tablaListaCC.getColumnModel().getColumn(3).setCellRenderer(centro);
        tablaListaCC.getColumnModel().getColumn(4).setCellRenderer(centro);
        tablaListaCC.getColumnModel().getColumn(5).setCellRenderer(centro);
    }
    private void btnADetalleCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADetalleCCActionPerformed

        ListaPanel.setVisible(false);
        verDetallePanel.setVisible(true);

        int fila = tablaListaCC.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Error al seleccionar una cuenta corriente");
            return;
        }

        String rs = tablaListaCC.getValueAt(fila, 0).toString();
        txtNomCC.setText(rs);

        try {

            String cuit = tablaListaCC.getValueAt(fila, 2).toString();

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
                    txtIVA.setText(p.getIdIVA().toString());
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
                p.getIdRubroProducto(),
                p.getIdTipoProducto()
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
                    && p.getIdRubroProducto().getIdRubroProducto() != rubro.getIdRubroProducto()) {
                coincide = false;
            }

            // tipo
            if (tipo != null
                    && p.getIdTipoProducto().getIdTipoProducto() != tipo.getIdTipoProducto()) {
                coincide = false;
            }

            if (coincide) {

                hayResultados = true;

                modelo.addRow(new Object[]{
                    p.getCodigo(),
                    p.getDescripcion(),
                    formatoMoneda.format(p.getPrecioUnitario()),
                    p.getIdRubroProducto(),
                    p.getIdTipoProducto()
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
                        p.getIdRubroProducto(),
                        p.getIdTipoProducto()
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
                txtIVA.setText(p.getIdIVA().toString());
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
        mostrarGraficoMetodosPago();
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

    private void btnExportarInformeVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarInformeVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportarInformeVentasActionPerformed

    // CARGAR DATOS DE GRAFICOS EN LA SECCION INFORMES DE STOCK
    private Datos.DStockProducto dStock = new Datos.DStockProducto();

    private void mostrarGraficoRubro() {
        // Obtener datos
        org.jfree.data.general.DefaultPieDataset dataset = dStock.obtenerStockPorRubro();

        // Crear gráfico de torta
        org.jfree.chart.JFreeChart chart = org.jfree.chart.ChartFactory.createPieChart(
                "Informe de Stock Total por Rubro",
                dataset,
                true,
                true,
                false
        );

        org.jfree.chart.plot.PiePlot plot = (org.jfree.chart.plot.PiePlot) chart.getPlot();

        /* 
       Formato de etiqueta: {0} = Nombre Rubro, {1} = Valor absoluto (Cantidad), {2} = Porcentaje
         */
        plot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
                "{0}: {1} unidades ({2})",
                new java.text.DecimalFormat("#,##0"),
                new java.text.DecimalFormat("0.0%")
        ));

        plot.setSimpleLabels(false);

        // Montar en el panel de la interfaz
        org.jfree.chart.ChartPanel chartPanel = new org.jfree.chart.ChartPanel(chart);
        panelGraficoStockInforme.removeAll();
        panelGraficoStockInforme.setLayout(new java.awt.BorderLayout());
        panelGraficoStockInforme.add(chartPanel, java.awt.BorderLayout.CENTER);
        panelGraficoStockInforme.revalidate();
        panelGraficoStockInforme.repaint();
    }

    private void mostrarGraficoStockCritico() {
        // obtengo los datos
        org.jfree.data.category.DefaultCategoryDataset dataset = dStock.obtenerStockCritico();

        // Creo el  gráfico de barras
        org.jfree.chart.JFreeChart chart = org.jfree.chart.ChartFactory.createBarChart(
                "Productos en Stock Crítico vs. Mínimo",
                "Productos",
                "Unidades en Stock",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        org.jfree.chart.plot.CategoryPlot plot = chart.getCategoryPlot();
        org.jfree.chart.renderer.category.BarRenderer renderer = (org.jfree.chart.renderer.category.BarRenderer) plot.getRenderer();

        renderer.setDefaultItemLabelsVisible(true);

        renderer.setDefaultItemLabelGenerator(
                new org.jfree.chart.labels.StandardCategoryItemLabelGenerator(
                        "{2}",
                        new java.text.DecimalFormat("#,##0")
                )
        );

        // Rotar etiquetas del Eje X para que el nombre del producto se lea completo si es largo
        plot.getDomainAxis().setCategoryLabelPositions(
                org.jfree.chart.axis.CategoryLabelPositions.UP_45
        );

        // Rotar etiquetas del Eje X para que el nombre del producto se lea completo si es largo
        plot.getDomainAxis().setCategoryLabelPositions(
                org.jfree.chart.axis.CategoryLabelPositions.UP_45
        );

        // 
        org.jfree.chart.ChartPanel chartPanel = new org.jfree.chart.ChartPanel(chart);
        panelGraficoStockInforme.removeAll();
        panelGraficoStockInforme.setLayout(new java.awt.BorderLayout());
        panelGraficoStockInforme.add(chartPanel, java.awt.BorderLayout.CENTER);
        panelGraficoStockInforme.revalidate();
        panelGraficoStockInforme.repaint();
    }

    private void actualizarDescripcionGrafico() {
        int seleccion = cbTipoReporteStock.getSelectedIndex();

        if (seleccion == 0) {
            // Descripción para la Torta bro
            lblDescripcionReporte.setText(
                    "<html><b>DESCRIPCIÓN DEL INFORME:</b><br>"
                    + "Este gráfico representa el volumen total acumulado de stock agrupado por <b>Rubro</b>.<br>"
                    + "Permite identificar qué rubros concentran el mayor nivel de inventario en el comercio.</html>"
            );
        } else {
            // Descripción para el Crítico 
            lblDescripcionReporte.setText(
                    "<html><b>DESCRIPCIÓN DEL INFORME:</b><br>"
                    + "Este gráfico compara visualmente el <b>Stock Actual</b> con el <b>Stock Mínimo</b> requerido.<br>"
                    + "Muestra únicamente los productos cuya existencia actual está por debajo o al límite del margen de seguridad.</html>"
            );
        }
    }

    private void actualizarGrafico() {
        if (cbTipoReporteStock.getSelectedIndex() == 0) {
            mostrarGraficoRubro();
        } else {
            mostrarGraficoStockCritico();
        }
        actualizarDescripcionGrafico();
    }


    private void btnExportarStockPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarStockPdfActionPerformed
        try {
            javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
            chooser.setDialogTitle("Guardar Informe de Stock (PDF)");
            chooser.setSelectedFile(new java.io.File("informe_stock_inventario.pdf"));

            if (chooser.showSaveDialog(this) != javax.swing.JFileChooser.APPROVE_OPTION) {
                return;
            }

            java.io.File archivo = chooser.getSelectedFile();
            if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
                archivo = new java.io.File(archivo.getAbsolutePath() + ".pdf");
            }

            com.itextpdf.text.Document documento = new com.itextpdf.text.Document(com.itextpdf.text.PageSize.A4, 36, 36, 36, 36);
            com.itextpdf.text.pdf.PdfWriter.getInstance(documento, new java.io.FileOutputStream(archivo));

            documento.open();

            com.itextpdf.text.Font tituloFont = new com.itextpdf.text.Font(
                    com.itextpdf.text.Font.FontFamily.HELVETICA, 16, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font fechaFont = new com.itextpdf.text.Font(
                    com.itextpdf.text.Font.FontFamily.HELVETICA, 10, com.itextpdf.text.Font.ITALIC, com.itextpdf.text.BaseColor.DARK_GRAY);

            com.itextpdf.text.Paragraph titulo = new com.itextpdf.text.Paragraph("Informe General de Stock de Productos", tituloFont);
            titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            documento.add(titulo);

            String fechaHoraActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            com.itextpdf.text.Paragraph fecha = new com.itextpdf.text.Paragraph("Fecha y Hora de Creación: " + fechaHoraActual, fechaFont);
            fecha.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            fecha.setSpacingAfter(15);
            documento.add(fecha);

            com.itextpdf.text.pdf.PdfPTable tablaPDF = new com.itextpdf.text.pdf.PdfPTable(5);
            tablaPDF.setWidthPercentage(100);
            tablaPDF.setWidths(new float[]{15f, 35f, 15f, 15f, 20f});

            tablaPDF.addCell(crearCeldaEncabezado("Código"));
            tablaPDF.addCell(crearCeldaEncabezado("Descripción"));
            tablaPDF.addCell(crearCeldaEncabezado("Cantidad"));
            tablaPDF.addCell(crearCeldaEncabezado("P. Unitario"));
            tablaPDF.addCell(crearCeldaEncabezado("Proveedor"));

            List<Producto> listaProductos = nProducto.listar();

            for (Producto p : listaProductos) {
                String codigo = String.valueOf(p.getCodigo());
                String descripcion = p.getDescripcion();

                String cantidad = (p.getIdStockProducto() != null)
                        ? String.valueOf(p.getIdStockProducto().getStockAct()) : "0";

                String precio = String.format("$%.2f", p.getPrecioUnitario());

                String proveedor = (p.getIdProveedor() != null)
                        ? p.getIdProveedor().getNombre() : "S/N";

                com.itextpdf.text.pdf.PdfPCell c1 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(codigo));
                c1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                c1.setPadding(5);
                tablaPDF.addCell(c1);

                com.itextpdf.text.pdf.PdfPCell c2 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(descripcion));
                c2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                c2.setPadding(5);
                tablaPDF.addCell(c2);

                com.itextpdf.text.pdf.PdfPCell c3 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(cantidad));
                c3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                c3.setPadding(5);
                tablaPDF.addCell(c3);

                com.itextpdf.text.pdf.PdfPCell c4 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(precio));
                c4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                c4.setPadding(5);
                tablaPDF.addCell(c4);

                com.itextpdf.text.pdf.PdfPCell c5 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(proveedor));
                c5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                c5.setPadding(5);
                tablaPDF.addCell(c5);
            }

            documento.add(tablaPDF);
            documento.close();

            javax.swing.JOptionPane.showMessageDialog(this, "Informe PDF generado correctamente en formato A4.");

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al generar PDF: " + e.getMessage());
        }
    }

    private com.itextpdf.text.pdf.PdfPCell crearCeldaEncabezado(String texto) {
        com.itextpdf.text.pdf.PdfPCell celda = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(texto));
        celda.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        celda.setBackgroundColor(com.itextpdf.text.BaseColor.LIGHT_GRAY);
        return celda;
    }//GEN-LAST:event_btnExportarStockPdfActionPerformed

    private void btnExportarStockExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarStockExcelActionPerformed
        try {
            javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
            chooser.setDialogTitle("Guardar Informe de Stock (Excel)");
            chooser.setSelectedFile(new java.io.File("informe_stock_inventario.xls"));

            if (chooser.showSaveDialog(this) != javax.swing.JFileChooser.APPROVE_OPTION) {
                return;
            }

            java.io.File archivo = chooser.getSelectedFile();
            if (!archivo.getName().toLowerCase().endsWith(".xls")) {
                archivo = new java.io.File(archivo.getAbsolutePath() + ".xls");
            }

            String fechaHoraActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            try (java.io.PrintWriter pw = new java.io.PrintWriter(
                    new java.io.OutputStreamWriter(
                            new java.io.FileOutputStream(archivo), "UTF-8"))) {

                pw.println("<html><head><meta charset='UTF-8'>");

                pw.println("<style>");
                pw.println("@page { size: A4 portrait; margin: 1cm; }");
                pw.println("body { font-family: Arial, sans-serif; font-size: 11pt; }");
                pw.println("h2 { text-align: center; color: #1e3a8a; }");
                pw.println(".fecha { text-align: center; font-style: italic; color: #555; margin-bottom: 20px; }");
                pw.println("table { border-collapse: collapse; width: 100%; }");
                pw.println("th, td { border: 1px solid #000; padding: 6px 8px; }");
                pw.println("th { background-color: #D9EAF7; text-align: center; font-weight: bold; }");
                pw.println("</style>");
                pw.println("</head><body>");

                pw.println("<h2>Informe General de Stock de Productos</h2>");
                pw.println("<div class='fecha'><b>Fecha y Hora de Creación:</b> " + fechaHoraActual + "</div>");

                pw.println("<table>");
                pw.println("<thead><tr>");
                pw.println("<th>Código</th>");
                pw.println("<th>Descripción</th>");
                pw.println("<th>Cantidad</th>");
                pw.println("<th>Precio Unitario</th>");
                pw.println("<th>Proveedor</th>");
                pw.println("</tr></thead>");
                pw.println("<tbody>");

                List<Producto> listaProductos = nProducto.listar();

                for (Producto p : listaProductos) {
                    String codigo = String.valueOf(p.getCodigo());
                    String descripcion = p.getDescripcion();
                    int cantidad = (p.getIdStockProducto() != null) ? p.getIdStockProducto().getStockAct() : 0;
                    float precio = p.getPrecioUnitario();
                    String proveedor = (p.getIdProveedor() != null) ? p.getIdProveedor().getNombre() : "S/N";

                    pw.println("<tr>");
                    pw.println("<td style='text-align:center; mso-number-format:\"\\@\";'>" + codigo + "</td>");
                    pw.println("<td>" + descripcion + "</td>");
                    pw.println("<td style='text-align:right;'>" + cantidad + "</td>");
                    pw.println("<td style='text-align:right; mso-number-format:\"\\$#,##0.00\";'>" + String.format(Locale.US, "%.2f", precio) + "</td>");
                    pw.println("<td>" + proveedor + "</td>");
                    pw.println("</tr>");
                }

                pw.println("</tbody></table></body></html>");
            }

            javax.swing.JOptionPane.showMessageDialog(this, "Informe Excel generado correctamente en formato A4.");

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al generar Excel: " + e.getMessage());
        }
    }//GEN-LAST:event_btnExportarStockExcelActionPerformed

    private void btnActualizarInformeStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarInformeStockActionPerformed
        actualizarGrafico();
    }//GEN-LAST:event_btnActualizarInformeStockActionPerformed

    private void cbTipoReporteStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoReporteStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoReporteStockActionPerformed
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
        clickProductos();
        PanelPromociones.setVisible(false);
        PanelOfertas.setVisible(false);
        NAVPAGOS.setVisible(false);
    }//GEN-LAST:event_btnProductos3ActionPerformed

    private void btnInformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInformesActionPerformed

    private void btnActualizarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarPActionPerformed

    private void btnListasPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListasPActionPerformed
        clickListasP();
    }//GEN-LAST:event_btnListasPActionPerformed

    private void clickListasP() {
        InicioProdPanel.setVisible(false);
        AccionGestion.setVisible(false);
        GestionProdPanel.setVisible(false);
        NAVPAGOS.setVisible(true);
        ListasPPanel.setVisible(true);
        btnListasP.setBackground(new Color(38, 90, 142));
        btnGestionarP.setBackground(new Color(20, 52, 83));
        btnActualizarP.setBackground(new Color(20, 52, 83));
        NAVPAGOS.setVisible(true);
        btnAPromociones.setBackground(new Color(204, 204, 255));
        btnAPromociones.setForeground(new Color(51, 0, 255));
        PanelPromociones.setVisible(true);
        PanelOfertas.setVisible(false);
    }
    private void btnGestionarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarPActionPerformed
        InicioProdPanel.setVisible(false);
        ListasPPanel.setVisible(false);
        AccionGestion.setVisible(false);
        GestionProdPanel.setVisible(true);
        PanelPromociones.setVisible(false);
        PanelOfertas.setVisible(false);
        btnGestionarP.setBackground(new Color(38, 90, 142));
        btnListasP.setBackground(new Color(20, 52, 83));
        cargarTablaProductosP();
        actualizarDatosInventario();
        cargarRubro(cbRubroP);
        cargarProveedor(cbProveedorP);
        cbIVAP.removeAllItems();
        cargarIva(cbIVAP);
        cargarRubro(cbRubroP1);
        RubroProducto rubro = (RubroProducto) cbRubroP1.getSelectedItem();
        if (rubro != null) {
            cargarTipoProducto(cbTipoP, rubro);
        }
    }//GEN-LAST:event_btnGestionarPActionPerformed

    private void NuevoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoPActionPerformed
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
        cargarRubro(cbRubroP);
        cargarProveedor(cbProveedorP);
        cbIVAP.removeAllItems();
        cargarIva(cbIVAP);
        RubroProducto rubro = (RubroProducto) cbRubroP.getSelectedItem();
        if (rubro != null) {
            cargarTipoProducto(cbTipoP, rubro);
        }
    }//GEN-LAST:event_NuevoPActionPerformed

    private void GVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GVolverActionPerformed
        AccionGestion.setVisible(false);
        GestionProdPanel.setVisible(true);
        cargarTablaProductosP();
    }//GEN-LAST:event_GVolverActionPerformed

    private void cbTipoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoPActionPerformed

    private boolean validarProductoAM() {
        try {
            if (txtCodP.getText().trim().isEmpty()
                    || txtDescP.getText().trim().isEmpty()
                    || txtPrecioP.getText().trim().isEmpty()
                    || txtStockMinP.getText().trim().isEmpty()
                    || txtStockActP.getText().trim().isEmpty()
                    || txtStockMaxP.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.");
                return false;
            }
            String codigoTexto = txtCodP.getText().trim();
            if (!codigoTexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El código debe contener solamente números.");
                txtCodP.requestFocus();
                return false;
            }
            if (codigoTexto.length() < 3) {
                JOptionPane.showMessageDialog(this, "El código debe tener al menos 3 números.");
                txtCodP.requestFocus();
                return false;
            }
            if (codigoTexto.length() > 5) {
                JOptionPane.showMessageDialog(this, "El código no puede superar los 5 caracteres.");
                txtCodP.requestFocus();
                return false;
            }
            int codigo = Integer.parseInt(codigoTexto);
            try {
                Producto productoExistente = nProducto.buscarPorCodigo(codigoTexto);
                JOptionPane.showMessageDialog(this, "Ya existe un producto con el código: " + codigo);
                txtCodP.requestFocus();
                return false;
            } catch (Exception e) {

            }
            String descripcion = txtDescP.getText().trim();
            if (descripcion.length() > 50) {
                JOptionPane.showMessageDialog(this, "La descripción no puede superar los 50 caracteres.");
                txtDescP.requestFocus();
                return false;
            }
            String precioTexto = txtPrecioP.getText().trim();
            if (!precioTexto.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.");
                txtPrecioP.requestFocus();
                return false;
            }
            float precio = Float.parseFloat(precioTexto);
            String stockMinTexto = txtStockMinP.getText().trim();
            if (!stockMinTexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El stock mínimo debe ser un número entero.");
                txtStockMinP.requestFocus();
                return false;
            }
            int stockMin = Integer.parseInt(stockMinTexto);
            String stockActTexto = txtStockActP.getText().trim();
            if (!stockActTexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El stock actual debe ser un número entero.");
                txtStockActP.requestFocus();
                return false;
            }
            int stockAct = Integer.parseInt(stockActTexto);
            String stockMaxTexto = txtStockMaxP.getText().trim();
            if (!stockMaxTexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El stock máximo debe ser un número entero.");
                txtStockMaxP.requestFocus();
                return false;
            }
            int stockMax = Integer.parseInt(stockMaxTexto);
            if (stockMin > stockMax) {
                JOptionPane.showMessageDialog(this, "El stock mínimo no puede ser mayor que el stock máximo.");
                txtStockMinP.requestFocus();
                return false;
            }
            if (stockAct > stockMax) {
                JOptionPane.showMessageDialog(this, "El stock actual no puede ser mayor que el stock máximo.");
                txtStockActP.requestFocus();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Uno de los valores numéricos no es válido.");
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            return false;
        }
    }
    private void AGAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGAceptarActionPerformed
        if (validarProductoAM()) {
            StockProducto sp = new StockProducto();
            sp.setStockMin(Integer.parseInt(txtStockMinP.getText()));
            sp.setStockMax(Integer.parseInt(txtStockMaxP.getText()));
            sp.setStockAct(Integer.parseInt(txtStockActP.getText()));
            try {
                NStockProducto nsp = new NStockProducto();
                int idStockProducto = nsp.insert(sp);

                if (idStockProducto > 0) {
                    sp.setIdStockProducto(idStockProducto);
                    Producto p = new Producto();
                    p.setCodigo(Integer.parseInt(txtCodP.getText()));
                    p.setDescripcion(txtDescP.getText().toString());
                    p.setPrecioUnitario(Float.parseFloat(txtPrecioP.getText()));
                    p.setIdRubroProducto((RubroProducto) cbRubroP.getSelectedItem());
                    p.setIdIVA((IVA) cbIVAP.getSelectedItem());
                    p.setIdTipoProducto((TipoProducto) cbTipoP.getSelectedItem());
                    p.setIdProveedor((Proveedor) cbProveedorP.getSelectedItem());
                    p.setIdStockProducto(sp);

                    NProducto np = new NProducto();
                    if (np.insertar(p)) {
                        JOptionPane.showMessageDialog(this, "Registrado con éxito");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al registrar el producto");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
        actualizarDatosInventario();
    }//GEN-LAST:event_AGAceptarActionPerformed

    private boolean validarProductoMM(Producto p) {
        try {
            if (txtCodP.getText().trim().isEmpty()
                    || txtDescP.getText().trim().isEmpty()
                    || txtPrecioP.getText().trim().isEmpty()
                    || txtStockMinP.getText().trim().isEmpty()
                    || txtStockActP.getText().trim().isEmpty()
                    || txtStockMaxP.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.");
                return false;
            }
            String codigoTexto = txtCodP.getText().trim();
            if (!codigoTexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El código debe contener solamente números.");
                txtCodP.requestFocus();
                return false;
            }
            if (codigoTexto.length() < 3) {
                JOptionPane.showMessageDialog(this, "El código debe tener al menos 3 números.");
                txtCodP.requestFocus();
                return false;
            }
            if (codigoTexto.length() > 5) {
                JOptionPane.showMessageDialog(this, "El código no puede superar los 5 caracteres.");
                txtCodP.requestFocus();
                return false;
            }
            int codigo = Integer.parseInt(codigoTexto);

            // Si el código no cambió, es el mismo producto
            if (codigo == p.getCodigo()) {
                // Continúa con las demás validaciones
            } else {

                // El código cambió, entonces verificamos si ya existe
                Producto productoExistente = nProducto.buscarPorCodigo(codigoTexto);

                if (productoExistente != null) {
                    JOptionPane.showMessageDialog(this,
                            "Ya existe otro producto con el código: " + codigo);

                    txtCodP.requestFocus();
                    return false;
                }
            }
            String descripcion = txtDescP.getText().trim();
            if (descripcion.length() > 50) {
                JOptionPane.showMessageDialog(this, "La descripción no puede superar los 50 caracteres.");
                txtDescP.requestFocus();
                return false;
            }
            String precioTexto = txtPrecioP.getText().trim();
            if (!precioTexto.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.");
                txtPrecioP.requestFocus();
                return false;
            }
            float precio = Float.parseFloat(precioTexto);
            String stockMinTexto = txtStockMinP.getText().trim();
            if (!stockMinTexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El stock mínimo debe ser un número entero.");
                txtStockMinP.requestFocus();
                return false;
            }
            int stockMin = Integer.parseInt(stockMinTexto);
            String stockActTexto = txtStockActP.getText().trim();
            if (!stockActTexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El stock actual debe ser un número entero.");
                txtStockActP.requestFocus();
                return false;
            }
            int stockAct = Integer.parseInt(stockActTexto);
            String stockMaxTexto = txtStockMaxP.getText().trim();
            if (!stockMaxTexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El stock máximo debe ser un número entero.");
                txtStockMaxP.requestFocus();
                return false;
            }
            int stockMax = Integer.parseInt(stockMaxTexto);
            if (stockMin > stockMax) {
                JOptionPane.showMessageDialog(this, "El stock mínimo no puede ser mayor que el stock máximo.");
                txtStockMinP.requestFocus();
                return false;
            }
            if (stockAct > stockMax) {
                JOptionPane.showMessageDialog(this, "El stock actual no puede ser mayor que el stock máximo.");
                txtStockActP.requestFocus();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Uno de los valores numéricos no es válido.");
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            return false;
        }
    }
    private void MGAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MGAceptarActionPerformed
        try {
            NProducto np = new NProducto();
            Producto p = np.buscarPorCodigo(txtCodP.getText());
            if (validarProductoMM(p)) {
                p.setCodigo(Integer.parseInt(txtCodP.getText()));
                p.setDescripcion(txtDescP.getText());
                p.setPrecioUnitario(Float.parseFloat(txtPrecioP.getText()));
                p.setIdRubroProducto((RubroProducto) cbRubroP.getSelectedItem());
                p.setIdIVA((IVA) cbIVAP.getSelectedItem());
                p.setIdTipoProducto((TipoProducto) cbTipoP.getSelectedItem());
                p.setIdProveedor((Proveedor) cbProveedorP.getSelectedItem());
                NStockProducto nsp = new NStockProducto();
                StockProducto sp = new StockProducto();
                sp.setIdStockProducto(p.getIdStockProducto().getIdStockProducto());
                sp.setStockAct(Integer.parseInt(txtStockActP.getText()));
                sp.setStockMax(Integer.parseInt(txtStockMaxP.getText()));
                sp.setStockMin(Integer.parseInt(txtStockMinP.getText()));
                if (np.modificar(p) && nsp.modificar(sp)) {
                    JOptionPane.showMessageDialog(this, "Modificado con éxito");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar el producto");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        actualizarDatosInventario();
    }//GEN-LAST:event_MGAceptarActionPerformed

    private void ModificarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarPActionPerformed

        int fila = tablaProductosP.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la lista para continuar");
        } else {
            int colCodigo = tablaProductosP.getColumn("Código").getModelIndex();
            String codigo = tablaProductosP.getValueAt(fila, colCodigo).toString();
            try {
                NProducto np = new NProducto();
                Producto p = np.buscarPorCodigo(codigo);
                txtCodP.setText(String.valueOf(p.getCodigo()));
                txtDescP.setText(p.getDescripcion());
                txtPrecioP.setText(String.valueOf(p.getPrecioUnitario()));

                int idRubro = p.getIdRubroProducto().getIdRubroProducto();
                for (int i = 0; i < cbRubroP.getItemCount(); i++) {
                    RubroProducto rp = cbRubroP.getItemAt(i);
                    if (rp.getIdRubroProducto() == idRubro) {
                        cbRubroP.setSelectedIndex(i);
                        break;
                    }
                }

                int idIva = p.getIdIVA().getIdIVA();
                for (int i = 0; i < cbIVAP.getItemCount(); i++) {
                    IVA iva = cbIVAP.getItemAt(i);
                    if (iva.getIdIVA() == idIva) {
                        cbIVAP.setSelectedIndex(i);
                        break;
                    }
                }

                int idProveedor = p.getIdProveedor().getIdProveedor();
                for (int i = 0; i < cbProveedorP.getItemCount(); i++) {
                    Proveedor prov = cbProveedorP.getItemAt(i);
                    if (prov.getIdProveedor() == idProveedor) {
                        cbProveedorP.setSelectedIndex(i);
                        break;
                    }
                }

                cargarTipoProducto(cbTipoP, p.getIdRubroProducto());
                int idTipo = p.getIdTipoProducto().getIdTipoProducto();
                for (int i = 0; i < cbTipoP.getItemCount(); i++) {
                    TipoProducto tp = cbTipoP.getItemAt(i);
                    if (tp.getIdTipoProducto() == idTipo) {
                        cbTipoP.setSelectedIndex(i);
                        break;
                    }
                }
                StockProducto sp = p.getIdStockProducto();
                if (sp != null) {
                    txtStockMinP.setText(String.valueOf(sp.getStockMin()));
                    txtStockMaxP.setText(String.valueOf(sp.getStockMax()));
                    txtStockActP.setText(String.valueOf(sp.getStockAct()));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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
        }
    }//GEN-LAST:event_ModificarPActionPerformed

    private void cbRubroPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRubroPActionPerformed
        RubroProducto rubro = (RubroProducto) cbRubroP.getSelectedItem();
        if (rubro != null) {
            cargarTipoProducto(cbTipoP, rubro);
        }
    }//GEN-LAST:event_cbRubroPActionPerformed

    private void actualizarDatosInventario() {
        NProducto np = new NProducto();
        int totalProductos = 0;
        int stockTotal = 0;
        int totalProveedores = 0;
        int stockBajo = 0;
        int sinStock = 0;
        List<Producto> listaProductos = np.listar();
        for (Producto p : listaProductos) {
            totalProductos += 1;
            stockTotal += p.getIdStockProducto().getStockAct();
            if (p.getIdStockProducto().getStockAct() < p.getIdStockProducto().getStockMin() && p.getIdStockProducto().getStockAct() > 0) {
                stockBajo += 1;
            }
            if (p.getIdStockProducto().getStockAct() == 0) {
                sinStock += 1;
            }
        }
        try {
            NProveedor npr = new NProveedor();
            List<Proveedor> listaProveedores = npr.listar();
            for (Proveedor pr : listaProveedores) {
                totalProveedores += 1;
            }
            lblTotalProductosP.setText(String.valueOf(totalProductos));
            lblStockTotalP.setText(String.valueOf(stockTotal));
            lblTotalProveedoresP.setText(String.valueOf(totalProveedores));
            btnBajoStockP.setText(String.valueOf(stockBajo));
            btnSinStockP.setText(String.valueOf(sinStock));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void EliminarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarPActionPerformed
        int fila = tablaProductosP.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la lista para eliminar un producto");
        } else {
            try {
                int colCodigo = tablaProductosP
                        .getColumn("Código")
                        .getModelIndex();
                int codigo = Integer.parseInt(
                        tablaProductosP.getValueAt(fila, colCodigo).toString()
                );
                int opcion = JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro que desea eliminar el producto?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );
                if (opcion != JOptionPane.YES_OPTION) {
                    return;
                }
                NProducto np = new NProducto();
                boolean eliminado = np.eliminarPorCodigo(codigo);
                if (eliminado) {
                    JOptionPane.showMessageDialog(this,
                            "Producto eliminado correctamente.");
                    cargarTablaProductosP();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "No se encontró el producto.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        actualizarDatosInventario();
    }//GEN-LAST:event_EliminarPActionPerformed

    private void btnInformes4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformes4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInformes4ActionPerformed

    private void btnAdministracion4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracion4ActionPerformed
        clickAdministracion();
    }//GEN-LAST:event_btnAdministracion4ActionPerformed

    private void btnVentas4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas4ActionPerformed
        clickVentas();
    }//GEN-LAST:event_btnVentas4ActionPerformed

    private void btnEstadisticas4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticas4ActionPerformed
        clickEstadisticas();
    }//GEN-LAST:event_btnEstadisticas4ActionPerformed

    private void btnProductos4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos4ActionPerformed
        clickProductos();
    }//GEN-LAST:event_btnProductos4ActionPerformed

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
            float porcentaje = p.getIdIVA().getPorcentaje();
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
                p.getIdRubroProducto().getDescripcion(),
                p.getIdTipoProducto().getDescripcion(),
                p.getIdStockProducto().getStockAct(),
                formatoMoneda.format(p.getPrecioUnitario()),
                p.getIdProveedor().getNombre()
            });
        }

        tablaProductosP.setModel(modelo);
        formatearTablaProductosP();
    }

    private void formatearTablaProductosP() {
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

    public JComboBox cargarRubro(JComboBox cb) {
        cb.removeAllItems();

        NRubroProducto nrp = new NRubroProducto();

        for (RubroProducto rp : nrp.listar()) {
            cb.addItem(rp);
        }

        if (cb.getItemCount() > 0) {
            cb.setSelectedIndex(0);
        }
        return cb;
    }

    public JComboBox cargarProveedor(JComboBox cb) {
        cb.removeAllItems();
        try {
            NProveedor np = new NProveedor();
            List<Proveedor> lista = np.listar();
            lista.sort((p1, p2)
                    -> p1.getNombre().compareToIgnoreCase(p2.getNombre())
            );
            for (Proveedor p : lista) {
                cb.addItem(p);
            }
            if (cb.getItemCount() > 0) {
                cb.setSelectedIndex(0);
            }
            return cb;
        } catch (Exception e) {
            e.printStackTrace();
            return cb;
        }
    }

    public JComboBox cargarTipoProducto(JComboBox cb, RubroProducto rubro) {
        cb.removeAllItems();
        NTipoProducto ntp = new NTipoProducto();
        List<TipoProducto> lista = ntp.listar();
        lista.sort((tp1, tp2)
                -> tp1.getDescripcion().compareToIgnoreCase(tp2.getDescripcion())
        );
        for (TipoProducto tp : lista) {
            if (tp.getIdRubroProducto() != null
                    && tp.getIdRubroProducto().getIdRubroProducto()
                    == rubro.getIdRubroProducto()) {
                cb.addItem(tp);
            }
        }
        if (cb.getItemCount() > 0) {
            cb.setSelectedIndex(0);
        }
        return cb;
    }

    public JComboBox cargarIva(JComboBox cb) {
        cb.removeAllItems();
        NIVA niva = new NIVA();
        for (IVA iva : niva.listar()) {
            cb.addItem(iva);
        }
        if (cb.getItemCount() > 0) {
            cb.setSelectedIndex(0);
        }
        return cb;
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new PLogin().setVisible(true));
    }

    private void btnCancelarRegistroProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRegistroProveedorActionPerformed
        panelRegistrarProveedor.setVisible(false);
        panelListaProveedores.setVisible(true);
    }//GEN-LAST:event_btnCancelarRegistroProveedorActionPerformed

    private void btnConfirmarRegistroProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarRegistroProveedorActionPerformed
        try {
            Proveedor proveedor = new Proveedor();

            proveedor.setNombre(txtNombreProveedor.getText().trim());
            proveedor.setCuit(txtCuitProveedor.getText().trim());
            proveedor.setTelefono(txtTelefonoProveedor.getText().trim());
            proveedor.setMail(txtEmailProveedor.getText().trim());
            proveedor.setDomicilio(txtDireccionProveedor.getText().trim());
            proveedor.setRubro(txtRubroProveedor.getText().trim());

            NProveedor negocioProveedor = new NProveedor();
            negocioProveedor.create(proveedor);

            JOptionPane.showMessageDialog(
                    this,
                    "Proveedor registrado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            txtNombreProveedor.setText("");
            txtCuitProveedor.setText("");
            txtTelefonoProveedor.setText("");
            txtEmailProveedor.setText("");
            txtDireccionProveedor.setText("");
            txtRubroProveedor.setText("");

            panelRegistrarProveedor.setVisible(false);
            panelListaProveedores.setVisible(true);

            cargarTablaProveedores();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error al registrar proveedor",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnConfirmarRegistroProveedorActionPerformed

    private void cargarTablaProveedores() {
        formatearTablaProveedores();
        try {
            NProveedor negocioProveedor = new NProveedor();

            ArrayList<Proveedor> proveedores = negocioProveedor.listar();

            mostrarProveedoresEnTabla(proveedores);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error al cargar proveedores",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void formatearTablaProveedores() {
        tablaProveedores.setRowSorter(null);
        tablaProveedores.getColumnModel().getColumn(0).setPreferredWidth(200);
        tablaProveedores.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaProveedores.getColumnModel().getColumn(2).setPreferredWidth(87);
        tablaProveedores.getColumnModel().getColumn(3).setPreferredWidth(200);
        tablaProveedores.getColumnModel().getColumn(4).setPreferredWidth(200);
        tablaProveedores.getColumnModel().getColumn(5).setPreferredWidth(150);
        tablaProveedores.getColumnModel().getColumn(6).setPreferredWidth(70);
        tablaProveedores.setRowHeight(30);
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        tablaProveedores.getColumnModel().getColumn(0).setCellRenderer(centro);
        tablaProveedores.getColumnModel().getColumn(1).setCellRenderer(centro);
        tablaProveedores.getColumnModel().getColumn(2).setCellRenderer(centro);
        tablaProveedores.getColumnModel().getColumn(3).setCellRenderer(centro);
        tablaProveedores.getColumnModel().getColumn(4).setCellRenderer(centro);
        tablaProveedores.getColumnModel().getColumn(5).setCellRenderer(centro);
        tablaProveedores.getColumnModel().getColumn(6).setCellRenderer(centro);
        tablaProveedores.setAutoResizeMode(tablaProveedores.AUTO_RESIZE_OFF);

    }

    private void mostrarProveedoresEnTabla(ArrayList<Proveedor> proveedores) {

        DefaultTableModel modelo
                = (DefaultTableModel) tablaProveedores.getModel();

        modelo.setRowCount(0);

        for (Proveedor proveedor : proveedores) {

            Object[] fila = {
                proveedor.getNombre(),
                proveedor.getCuit(),
                proveedor.getTelefono(),
                proveedor.getMail(),
                proveedor.getDomicilio(),
                proveedor.getRubro(),
                proveedor.getEstado()
            };

            modelo.addRow(fila);
        }
    }

    private void btnRegistrarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarProveedorActionPerformed
        panelListaProveedores.setVisible(false);
        panelRegistrarProveedor.setVisible(true);
        panelModificarProveedor.setVisible(false);
    }//GEN-LAST:event_btnRegistrarProveedorActionPerformed

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
        int filaSeleccionada = tablaProveedores.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un proveedor para desactivar.",
                    "Desactivar proveedor",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String cuitSeleccionado = tablaProveedores
                .getValueAt(filaSeleccionada, 1)
                .toString();

        try {

            NProveedor negocioProveedor = new NProveedor();

            Proveedor proveedorAEliminar = null;

            for (Proveedor proveedor : negocioProveedor.listar()) {

                if (cuitSeleccionado.equals(proveedor.getCuit())) {
                    proveedorAEliminar = proveedor;
                    break;
                }
            }

            if (proveedorAEliminar == null) {
                throw new Exception("No se encontró el proveedor seleccionado.");
            }

            // Evitar intentar desactivar un proveedor que ya está inactivo
            if ("Inactivo".equalsIgnoreCase(proveedorAEliminar.getEstado())) {

                JOptionPane.showMessageDialog(
                        this,
                        "El proveedor ya se encuentra inactivo.",
                        "Desactivar proveedor",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // Confirmar desactivación
            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de que desea desactivar al proveedor \""
                    + proveedorAEliminar.getNombre() + "\"?",
                    "Confirmar desactivación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (respuesta != JOptionPane.YES_OPTION) {
                return;
            }

            // Desactivar proveedor
            negocioProveedor.desactivar(proveedorAEliminar);

            JOptionPane.showMessageDialog(
                    this,
                    "Proveedor desactivado correctamente.",
                    "Desactivación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Actualizar listado
            cargarTablaProveedores();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error al desactivar proveedor",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnBuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProveedorActionPerformed
        try {
            String nombre = txtBuscarRazonSocial.getText();
            String rubro = txtBuscarRubro.getText();
            String estado = cbBuscarEstado.getSelectedItem().toString();

            NProveedor negocioProveedor = new NProveedor();

            ArrayList<Proveedor> proveedores
                    = negocioProveedor.buscar(nombre, rubro, estado);

            mostrarProveedoresEnTabla(proveedores);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error al buscar proveedores",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnBuscarProveedorActionPerformed

    private void btnExportarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarProveedoresActionPerformed

        if (tablaProveedores.getRowCount() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay proveedores para exportar.",
                    "Exportar proveedores",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar listado de proveedores");
            chooser.setSelectedFile(new File("listado_proveedores.xls"));

            if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File archivo = chooser.getSelectedFile();

            if (!archivo.getName().toLowerCase().endsWith(".xls")) {
                archivo = new File(archivo.getAbsolutePath() + ".xls");
            }

            DefaultTableModel modelo
                    = (DefaultTableModel) tablaProveedores.getModel();

            try (PrintWriter pw = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(archivo),
                            StandardCharsets.UTF_8))) {

                pw.println("<html>");
                pw.println("<head>");
                pw.println("<meta charset='UTF-8'>");
                pw.println("</head>");
                pw.println("<body>");

                pw.println("<h2>Listado de Proveedores</h2>");

                pw.println("<table border='1'>");

                // Encabezados
                pw.println(
                        "<tr style='font-weight:bold; background-color:#D9EAF7;'>"
                );

                for (int columna = 0;
                        columna < modelo.getColumnCount();
                        columna++) {

                    pw.println(
                            "<td>"
                            + modelo.getColumnName(columna)
                            + "</td>"
                    );
                }

                pw.println("</tr>");

                // Datos
                for (int fila = 0;
                        fila < modelo.getRowCount();
                        fila++) {

                    pw.println("<tr>");

                    for (int columna = 0;
                            columna < modelo.getColumnCount();
                            columna++) {

                        Object valor
                                = modelo.getValueAt(fila, columna);

                        String texto
                                = valor != null
                                        ? valor.toString()
                                        : "";

                        pw.println(
                                "<td style='mso-number-format:\"\\@\";'>"
                                + texto
                                + "</td>"
                        );
                    }

                    pw.println("</tr>");
                }

                pw.println("</table>");
                pw.println("</body>");
                pw.println("</html>");
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Listado de proveedores exportado correctamente.",
                    "Exportación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al generar Excel:\n" + e.getMessage(),
                    "Error al exportar",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnExportarProveedoresActionPerformed

    private void btnLimpiarFiltrosProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFiltrosProveedorActionPerformed
        txtBuscarRazonSocial.setText("");
        txtBuscarRubro.setText("");
        cbBuscarEstado.setSelectedItem("Todos");

        cargarTablaProveedores();
    }//GEN-LAST:event_btnLimpiarFiltrosProveedorActionPerformed

    private void btnCancelarModificacionProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModificacionProveedorActionPerformed
        panelModificarProveedor.setVisible(false);
        panelListaProveedores.setVisible(true);

        proveedorSeleccionado = null;
    }//GEN-LAST:event_btnCancelarModificacionProveedorActionPerformed

    private void btnGuardarModificacionProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarModificacionProveedorActionPerformed
        try {

            if (proveedorSeleccionado == null) {
                throw new Exception("No hay ningún proveedor seleccionado.");
            }

            // Actualizar el objeto con los datos ingresados
            proveedorSeleccionado.setNombre(
                    txtNombreModificarProveedor.getText().trim()
            );

            proveedorSeleccionado.setCuit(
                    txtCuitModificarProveedor.getText().trim()
            );

            proveedorSeleccionado.setTelefono(
                    txtTelefonoModificarProveedor.getText().trim()
            );

            proveedorSeleccionado.setMail(
                    txtEmailModificarProveedor.getText().trim()
            );

            proveedorSeleccionado.setDomicilio(
                    txtDireccionModificarProveedor.getText().trim()
            );

            proveedorSeleccionado.setRubro(
                    txtRubroModificarProveedor.getText().trim()
            );

            // Guardar modificación
            NProveedor negocioProveedor = new NProveedor();
            negocioProveedor.update(proveedorSeleccionado);

            JOptionPane.showMessageDialog(
                    this,
                    "Proveedor modificado correctamente.",
                    "Modificación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Actualizar tabla
            cargarTablaProveedores();

            // Volver al listado
            panelModificarProveedor.setVisible(false);
            panelListaProveedores.setVisible(true);

            // Ya terminamos de trabajar con este proveedor
            proveedorSeleccionado = null;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error al modificar proveedor",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnGuardarModificacionProveedorActionPerformed

    private void btnModificarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProveedorActionPerformed
        int filaSeleccionada = tablaProveedores.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un proveedor para modificar.",
                    "Modificar proveedor",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String cuitSeleccionado = tablaProveedores
                .getValueAt(filaSeleccionada, 1)
                .toString();

        try {
            NProveedor negocioProveedor = new NProveedor();

            proveedorSeleccionado = null;

            for (Proveedor proveedor : negocioProveedor.listar()) {

                if (cuitSeleccionado.equals(proveedor.getCuit())) {
                    proveedorSeleccionado = proveedor;
                    break;
                }
            }

            if (proveedorSeleccionado == null) {
                throw new Exception("No se encontró el proveedor seleccionado.");
            }

            // Cargar los datos en el formulario de modificación
            txtNombreModificarProveedor.setText(proveedorSeleccionado.getNombre());
            txtCuitModificarProveedor.setText(proveedorSeleccionado.getCuit());
            txtTelefonoModificarProveedor.setText(proveedorSeleccionado.getTelefono());
            txtEmailModificarProveedor.setText(proveedorSeleccionado.getMail());
            txtDireccionModificarProveedor.setText(proveedorSeleccionado.getDomicilio());
            txtRubroModificarProveedor.setText(proveedorSeleccionado.getRubro());

            // Cambiar de panel
            panelListaProveedores.setVisible(false);
            panelRegistrarProveedor.setVisible(false);
            panelModificarProveedor.setVisible(true);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error al cargar proveedor",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnModificarProveedorActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        clickProveedores();
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnProveedores1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedores1ActionPerformed
        clickProveedores();
    }//GEN-LAST:event_btnProveedores1ActionPerformed

    private void btnProveedores2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedores2ActionPerformed
        clickProveedores();
    }//GEN-LAST:event_btnProveedores2ActionPerformed

    private void btnProveedores3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedores3ActionPerformed
        clickProveedores();
    }//GEN-LAST:event_btnProveedores3ActionPerformed

    private void btnProveedores4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedores4ActionPerformed
        clickProveedores();
    }//GEN-LAST:event_btnProveedores4ActionPerformed

    private void btnSeccionProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeccionProveedoresActionPerformed
        clickProveedores();
    }//GEN-LAST:event_btnSeccionProveedoresActionPerformed

    private void btnAdministracionProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracionProveedoresActionPerformed
        clickAdministracion();
    }//GEN-LAST:event_btnAdministracionProveedoresActionPerformed

    private void btnVentasProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasProveedoresActionPerformed
        clickVentas();
    }//GEN-LAST:event_btnVentasProveedoresActionPerformed

    private void btnProductosProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosProveedoresActionPerformed
        clickProductos();
    }//GEN-LAST:event_btnProductosProveedoresActionPerformed

    private void btnEstadisticasProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticasProveedoresActionPerformed
        clickEstadisticas();
        actualizarEstadisticasVentas();
        mostrarGraficoVentasPorMes();
        mostrarGraficoMetodosPago();
    }//GEN-LAST:event_btnEstadisticasProveedoresActionPerformed

    private void btnInformesProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformesProveedoresActionPerformed
        btnInformesActionPerformed(evt);
    }//GEN-LAST:event_btnInformesProveedoresActionPerformed

    private void btnAOfertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAOfertasActionPerformed
        NAVPAGOS.setVisible(true);
        btnAPromociones.setBackground(new Color(204, 204, 204));
        btnAPromociones.setForeground(new Color(51, 51, 51));
        btnAOfertas.setForeground(new Color(51, 51, 255));
        btnAOfertas.setBackground(new Color(204, 204, 255));
        PanelPromociones.setVisible(false);
        PanelOfertas.setVisible(true);
    }//GEN-LAST:event_btnAOfertasActionPerformed

    private void btnAPromocionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAPromocionesActionPerformed

        NAVPAGOS.setVisible(true);
        btnAPromociones.setBackground(new Color(204, 204, 255));
        btnAPromociones.setForeground(new Color(51, 51, 255));
        btnAOfertas.setBackground(new Color(204, 204, 204));
        btnAOfertas.setForeground(new Color(51, 51, 51));
        PanelPromociones.setVisible(true);
        PanelOfertas.setVisible(false);
    }//GEN-LAST:event_btnAPromocionesActionPerformed

    private void EstadodePromoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadodePromoActionPerformed
        if (EstadodePromo.isSelected()) {
            EstadodePromo.setText("Activa");
            EstadodePromo.setBackground(new java.awt.Color(34, 197, 94)); // Verde opcional
        } else {
            EstadodePromo.setText("Inactiva");
            EstadodePromo.setBackground(new java.awt.Color(239, 68, 68)); // Rojo opcional
        }
    }//GEN-LAST:event_EstadodePromoActionPerformed

    private void txtPromocionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPromocionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPromocionActionPerformed

    private NPromocion nPromocion = new NPromocion();
    private NOferta nOferta = new NOferta();

    private void btGuardarPromoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarPromoActionPerformed
        // 1. Validar campos requeridos
        if (txtPromocion.getText().trim().isEmpty()
                || txtTipoCondicion.getText().trim().isEmpty()
                || txtDescuento.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos (Nombre, Condición y Descuento).", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 2. Obtener datos de los controles
            String nombre = txtPromocion.getText().trim();
            String condicion = txtTipoCondicion.getText().trim(); // Ahora es un JTextField
            double descuento = Double.parseDouble(txtDescuento.getText().trim().replace(",", "."));

            // Estado del JToggleButton (true = Activa, false = Inactiva)
            boolean estado = EstadodePromo.isSelected();

            // 3. Crear objeto y guardar
            Modelos.Promocion promo = new Modelos.Promocion(0, nombre, condicion, descuento, estado);

            if (nPromocion.guardarPromocion(promo)) {
                JOptionPane.showMessageDialog(this, "Promoción guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Actualizar tabla
                tblPromo.setModel(nPromocion.cargarTablaPromociones());

                // Limpiar formulario y resetear switch
                txtPromocion.setText("");
                txtTipoCondicion.setText("");
                txtDescuento.setText("");
                EstadodePromo.setSelected(true);
                EstadodePromo.setText("Activa");

            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la promoción en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El valor del porcentaje de descuento debe ser numérico.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btGuardarPromoActionPerformed

    private void btActivarPromoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActivarPromoActionPerformed
        int fila = tblPromo.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una promoción de la tabla.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idPromo = Integer.parseInt(tblPromo.getValueAt(fila, 0).toString());
        String estadoActual = tblPromo.getValueAt(fila, 4).toString();

        if ("Activa".equalsIgnoreCase(estadoActual)) {
            JOptionPane.showMessageDialog(this, "La promoción seleccionada ya se encuentra activa.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (nPromocion.cambiarEstadoPromocion(idPromo, true)) {
                JOptionPane.showMessageDialog(this, "La promoción ha sido activada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                tblPromo.setModel(nPromocion.cargarTablaPromociones());
            } else {
                JOptionPane.showMessageDialog(this, "Error al cambiar el estado de la promoción.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btActivarPromoActionPerformed

    private void btDesactivarPromo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDesactivarPromo1ActionPerformed
        int fila = tblPromo.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una promoción de la tabla.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idPromo = Integer.parseInt(tblPromo.getValueAt(fila, 0).toString());
        String estadoActual = tblPromo.getValueAt(fila, 4).toString();

        if ("Inactiva".equalsIgnoreCase(estadoActual)) {
            JOptionPane.showMessageDialog(this, "La promoción seleccionada ya se encuentra desactivada.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (nPromocion.cambiarEstadoPromocion(idPromo, false)) {
                JOptionPane.showMessageDialog(this, "La promoción ha sido desactivada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                tblPromo.setModel(nPromocion.cargarTablaPromociones());
            } else {
                JOptionPane.showMessageDialog(this, "Error al cambiar el estado de la promoción.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btDesactivarPromo1ActionPerformed

    private void cbProductoOfertaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProductoOfertaItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            Modelos.Producto prod = (Modelos.Producto) cbProductoOferta.getSelectedItem();
            if (prod != null) {
                txtPrecioOferta.setText(String.format(java.util.Locale.US, "%.2f", prod.getPrecioUnitario()));
                PrecioNuevo.setText("Precio Nuevo: $ --");
            }
        }
    }//GEN-LAST:event_cbProductoOfertaItemStateChanged

    private void PrecioNuevoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PrecioNuevoKeyReleased
        calcularPrecioNuevoOferta();
    }//GEN-LAST:event_PrecioNuevoKeyReleased

    private void calcularPrecioNuevoOferta() {
        Modelos.Producto prodSeleccionado = (Modelos.Producto) cbProductoOferta.getSelectedItem();
        if (prodSeleccionado == null) {
            return;
        }

        String textoDesc = jTextField11.getText().trim().replace(",", ".");
        if (textoDesc.isEmpty()) {
            PrecioNuevo.setText("Precio Nuevo: $" + String.format("%.2f", (double) prodSeleccionado.getPrecioUnitario()));
            return;
        }

        try {
            double descPorcentaje = Double.parseDouble(textoDesc);
            double precioBase = prodSeleccionado.getPrecioUnitario();
            double precioNuevo = precioBase - (precioBase * (descPorcentaje / 100.0));

            PrecioNuevo.setText("Precio Nuevo: $" + String.format(java.util.Locale.US, "%.2f", precioNuevo));
        } catch (NumberFormatException e) {
            PrecioNuevo.setText("Precio Nuevo: $ --");
        }
    }
    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private boolean esPorcentajeValido(String textoPorcentaje) {
        String texto = textoPorcentaje.trim();

        // 1. Validar que no esté vacío y contenga solo números enteros
        if (texto.isEmpty() || !texto.matches("^\\d+$")) {
            JOptionPane.showMessageDialog(this,
                    "El porcentaje de descuento debe ser un número entero válido (sin letras ni decimales).",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int porcentaje = Integer.parseInt(texto);

        // 2. Validar rango permitido (entre 1% y 100%)
        if (porcentaje <= 0 || porcentaje > 100) {
            JOptionPane.showMessageDialog(this,
                    "El porcentaje debe ser mayor a 0 y menor o igual a 100.",
                    "Rango Inválido",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
    private void btGuardarOfertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarOfertaActionPerformed
        Modelos.Producto prod = (Modelos.Producto) cbProductoOferta.getSelectedItem();

        if (prod == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (FechaInicioOferta.getDate() == null || FechaFinOferta.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione las fechas de inicio y fin.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar porcentaje antes de intentar crear el objeto
        if (!esPorcentajeValido(jTextField11.getText())) {
            return;
        }

        int porcentaje = Integer.parseInt(jTextField11.getText().trim());
        double precioBase = prod.getPrecioUnitario();
        double precioNuevo = precioBase - (precioBase * (porcentaje / 100.0));

        Modelos.Oferta oferta = new Modelos.Oferta();
        oferta.setIdProducto(prod.getIdProducto());
        oferta.setDescuentoPorcentaje(porcentaje);
        oferta.setPrecioPromocional(precioNuevo);
        oferta.setFechaInicio(FechaInicioOferta.getDate());
        oferta.setFechaFin(FechaFinOferta.getDate());

        // Control de vigencia según la fecha actual
        java.util.Date hoy = new java.util.Date();
        boolean esVigente = !oferta.getFechaInicio().after(hoy) && !oferta.getFechaFin().before(hoy);
        oferta.setEstado(esVigente);

        if (nOferta.guardarOferta(oferta)) {
            JOptionPane.showMessageDialog(this, "Oferta guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            tblOferta.setModel(nOferta.cargarTablaOfertas());

            // Limpiar controles
            jTextField11.setText("");
            PrecioNuevo.setText("Precio Nuevo:");
            FechaInicioOferta.setDate(null);
            FechaFinOferta.setDate(null);
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar la oferta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btGuardarOfertaActionPerformed

    private void btPrevisualizarPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrevisualizarPrecioActionPerformed
        Modelos.Producto prod = (Modelos.Producto) cbProductoOferta.getSelectedItem();
        if (prod == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar el texto del JTextField del descuento
        if (!esPorcentajeValido(jTextField11.getText())) {
            return; // Detiene la ejecución si no cumple las reglas
        }

        int porcentaje = Integer.parseInt(jTextField11.getText().trim());
        double precioBase = prod.getPrecioUnitario();
        double precioNuevo = precioBase - (precioBase * (porcentaje / 100.0));

        PrecioNuevo.setText("Precio Nuevo: $" + String.format(java.util.Locale.US, "%.2f", precioNuevo));
    }//GEN-LAST:event_btPrevisualizarPrecioActionPerformed

    private void btLimpiarOfertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimpiarOfertaActionPerformed
        // 1. Resetear la selección del ComboBox al primer elemento (si existe)
        if (cbProductoOferta.getItemCount() > 0) {
            cbProductoOferta.setSelectedIndex(0);
        }

        // 2. Limpiar campos de texto
        jTextField11.setText("");
        txtPrecioOferta.setText("");

        // 3. Resetear el label del precio nuevo
        PrecioNuevo.setText("Precio Nuevo: $ --");

        // 4. Limpiar los seleccionadores de fecha
        FechaInicioOferta.setDate(null);
        FechaFinOferta.setDate(null);
    }//GEN-LAST:event_btLimpiarOfertaActionPerformed

    private void btImprimirOfertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirOfertaActionPerformed
        int fila = tblOferta.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una oferta de la tabla para imprimir el cartel.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombreProducto = tblOferta.getValueAt(fila, 1).toString(); // Columna 1 -> Producto
        String descuentoStr = tblOferta.getValueAt(fila, 2).toString();   // Columna 2 -> Desc. (%)

        // Convertimos el descuento a número
        double descDouble = Double.parseDouble(descuentoStr);
        int porcentaje = (int) descDouble;
        // Diálogo emergente con el cartel promocional
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        dialog.setTitle("Cartel de Oferta - " + nombreProducto);
        dialog.setSize(450, 350);
        dialog.setLocationRelativeTo(this);
        dialog.setModal(true);

        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
        panel.setBackground(java.awt.Color.WHITE);
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        javax.swing.JLabel lblHeader = new javax.swing.JLabel("¡OFERTA IMPERDIBLE!");
        lblHeader.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
        lblHeader.setForeground(new java.awt.Color(220, 38, 38));
        lblHeader.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        javax.swing.JLabel lblProducto = new javax.swing.JLabel(nombreProducto.toUpperCase());
        lblProducto.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblProducto.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        javax.swing.JLabel lblDesc = new javax.swing.JLabel(porcentaje + "% OFF");
        lblDesc.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 40));
        lblDesc.setForeground(new java.awt.Color(37, 99, 235));
        lblDesc.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        javax.swing.JButton btnImprimir = new javax.swing.JButton("Exportar / Imprimir Cartel");
        btnImprimir.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        btnImprimir.addActionListener(e -> {
            java.awt.print.PrinterJob job = java.awt.print.PrinterJob.getPrinterJob();
            job.setJobName("Oferta_" + nombreProducto);
            job.setPrintable((graphics, pageFormat, pageIndex) -> {
                if (pageIndex > 0) {
                    return java.awt.print.Printable.NO_SUCH_PAGE;
                }
                java.awt.Graphics2D g2d = (java.awt.Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                panel.paint(g2d);
                return java.awt.print.Printable.PAGE_EXISTS;
            });
            if (job.printDialog()) {
                try {
                    job.print();
                    JOptionPane.showMessageDialog(dialog, "Cartel enviado a la impresora / guardado en PDF.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(lblHeader);
        panel.add(javax.swing.Box.createVerticalStrut(15));
        panel.add(lblProducto);
        panel.add(javax.swing.Box.createVerticalStrut(15));
        panel.add(lblDesc);
        panel.add(javax.swing.Box.createVerticalStrut(25));
        panel.add(btnImprimir);

        dialog.add(panel);
        dialog.setVisible(true);
    }//GEN-LAST:event_btImprimirOfertaActionPerformed
    private void btnFiltrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarProductoActionPerformed
        filtrarProductosP();
    }//GEN-LAST:event_btnFiltrarProductoActionPerformed

    private void btnLimpiarFiltroProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFiltroProductoActionPerformed
        txtCodigoPP.setText("");
        txtDescripcionPP.setText("");
        cbRubroP1.setSelectedIndex(0);
        cbStockP.setSelectedIndex(0);
        cargarTablaProductosP();
    }//GEN-LAST:event_btnLimpiarFiltroProductoActionPerformed

    private void lblimg4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblimg4MouseClicked
        clickLogoAMP();
    }//GEN-LAST:event_lblimg4MouseClicked

    private void lblimgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblimgMouseClicked
        clickLogoAMP();
    }//GEN-LAST:event_lblimgMouseClicked

    private void lblimg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblimg1MouseClicked
        clickLogoAMP();
    }//GEN-LAST:event_lblimg1MouseClicked

    private void lblimg3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblimg3MouseClicked
        clickLogoAMP();
    }//GEN-LAST:event_lblimg3MouseClicked

    private void lblimg2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblimg2MouseClicked
        clickLogoAMP();
    }//GEN-LAST:event_lblimg2MouseClicked

    private void lblImgProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgProveedoresMouseClicked
        clickLogoAMP();
    }//GEN-LAST:event_lblImgProveedoresMouseClicked

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        PanelInicioPresentacion.setVisible(false);
        PanelConfiguracion.setVisible(true);
        PanelConfigSeleccion.setVisible(true);
        PanelConfigMiNegocio.setVisible(false);
        PanelConfigPersonal.setVisible(false);
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnVolverAInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverAInicioActionPerformed
        PanelInicioPresentacion.setVisible(true);
        barritaEncabezado.setVisible(true);
        PanelConfiguracion.setVisible(false);
    }//GEN-LAST:event_btnVolverAInicioActionPerformed

    private void btnVolverASeleccionCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverASeleccionCActionPerformed
        PanelConfigSeleccion.setVisible(true);
        PanelConfigMiNegocio.setVisible(false);
    }//GEN-LAST:event_btnVolverASeleccionCActionPerformed

    private void btnVolverASeleccionC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverASeleccionC1ActionPerformed
        PanelConfigSeleccion.setVisible(true);
        PanelConfigPersonal.setVisible(false);
    }//GEN-LAST:event_btnVolverASeleccionC1ActionPerformed

    private void btnMiNegocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMiNegocioActionPerformed
        PanelConfigSeleccion.setVisible(false);
        PanelConfigMiNegocio.setVisible(true);
        barritaEncabezado.setVisible(false);
        n = NNegocio.getNegocioActual();
        if (n != null) {
            txtNomEmpresa.setText(n.getNombreNegocio());
            txtDomEmpresa.setText(n.getDomicilioNegocio());
            txtProvEmpresa.setText(n.getProvinciaNegocio());
            txtTelEmpresa.setText(n.getTelefonoNegocio());
        }
    }//GEN-LAST:event_btnMiNegocioActionPerformed

    private void btnPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalActionPerformed
        PanelConfigSeleccion.setVisible(false);
        PanelConfigPersonal.setVisible(true);
        barritaEncabezado.setVisible(false);
        cargarRolesPersonal();
        cargarTablaPersonal();
        limpiarCamposPersonal();

    }//GEN-LAST:event_btnPersonalActionPerformed

    private void cargarTablaPersonal() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Nombre", "Rol"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            listaUsuarios.clear();
            for (Usuario u : new NUsuario().listar()) {
                listaUsuarios.add(u);
                modelo.addRow(new Object[]{u.getUsuario(), u.getIdRol().getNomRol()});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar el personal: " + e.getMessage());
        }

        tablaMiPersonal.setModel(modelo);
        tablaMiPersonal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void cargarRolesPersonal() {
        try {
            cbRolesPersonal.removeAllItems();
            for (Rol r : new NRol().listar()) {
                cbRolesPersonal.addItem(r);
            }
            cbRolesPersonal.setSelectedIndex(-1); // que arranque sin nada seleccionado
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar roles: " + e.getMessage());
        }
    }
    private void btnGuardarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEmpresaActionPerformed
        try {
            MNegocio n = new MNegocio(
                    1,
                    txtNomEmpresa.getText(),
                    txtDomEmpresa.getText(),
                    txtProvEmpresa.getText(),
                    txtTelEmpresa.getText()
            );

            boolean eraNuevo = !NNegocio.hayNegocioCargado();

            new NNegocio().guardar(n);

            JOptionPane.showMessageDialog(this,
                    eraNuevo ? "Negocio registrado correctamente." : "Datos del negocio actualizados.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Error al guardar el negocio: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarEmpresaActionPerformed

    private Usuario leerFormularioPersonal() {
        Usuario u = new Usuario(
                txtNomUsuario.getText(),
                new String(txtClaveUsuario.getPassword()),
                "Activo",
                txtTelUsuario.getText(),
                txtDomUsuario.getText(),
                txtMailUsuario.getText(),
                (Rol) cbRolesPersonal.getSelectedItem()
        );
        u.setId(idSeleccionadoUsuario);
        return u;
    }

    private void limpiarCamposPersonal() {
        idSeleccionadoUsuario = 0;
        txtNomUsuario.setText("");
        txtClaveUsuario.setText("");
        txtTelUsuario.setText("");
        txtDomUsuario.setText("");
        txtMailUsuario.setText("");
        cbRolesPersonal.setSelectedIndex(-1);
    }
    private void btnGuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioActionPerformed
        try {
            new NUsuario().registrar(leerFormularioPersonal());
            JOptionPane.showMessageDialog(this, "Usuario registrado.");
            limpiarCamposPersonal();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        cargarTablaPersonal();
    }//GEN-LAST:event_btnGuardarUsuarioActionPerformed

    private void cargarSeleccionado() {
        int fila = tablaMiPersonal.getSelectedRow();
        if (fila < 0) {
            return;
        }

        Usuario u = listaUsuarios.get(tablaMiPersonal.convertRowIndexToModel(fila));

        idSeleccionadoUsuario = u.getId();
        txtNomUsuario.setText(u.getUsuario());
        txtClaveUsuario.setText("");  // vacía = se mantiene la contraseña actual
        txtTelUsuario.setText(u.getTelefono() == null ? "" : u.getTelefono());
        txtDomUsuario.setText(u.getDomicilio() == null ? "" : u.getDomicilio());
        txtMailUsuario.setText(u.getMail() == null ? "" : u.getMail());
        seleccionarRolUsuario(u.getIdRol().getIdRol());
    }

    private void seleccionarRolUsuario(int idRol) {
        for (int i = 0; i < cbRolesPersonal.getItemCount(); i++) {
            if (cbRolesPersonal.getItemAt(i).getIdRol() == idRol) {
                cbRolesPersonal.setSelectedIndex(i);
                return;
            }
        }
    }
    private void tablaMiPersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMiPersonalMouseClicked
        cargarSeleccionado();
    }//GEN-LAST:event_tablaMiPersonalMouseClicked

    private void btnEliminarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPersonalActionPerformed
        if (idSeleccionadoUsuario == 0) {
            JOptionPane.showMessageDialog(this, "Seleccioná un usuario de la tabla.");
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Dar de baja a " + txtNomUsuario.getText() + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            new NUsuario().eliminar(idSeleccionadoUsuario, usuarioLogueado.getId());
            JOptionPane.showMessageDialog(this, "Usuario dado de baja.");
            limpiarCamposPersonal();
            cargarTablaPersonal();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarPersonalActionPerformed

    private void btnModificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarUsuarioActionPerformed
        if (idSeleccionadoUsuario == 0) {
            JOptionPane.showMessageDialog(this, "Seleccioná un usuario de la tabla.");
            return;
        }
        try {
            new NUsuario().modificar(leerFormularioPersonal());
            JOptionPane.showMessageDialog(this, "Usuario modificado.");
            limpiarCamposPersonal();
            cargarTablaPersonal();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnModificarUsuarioActionPerformed

    private void ocultarProveedores() {
        proveedoresPanel.setVisible(false);
        panelRegistrarProveedor.setVisible(false);
        panelModificarProveedor.setVisible(false);
        proveedorSeleccionado = null;
    }

    private void clickProveedores() {
        InicioPanel.setVisible(false);
        administracionPanel.setVisible(false);
        ventasPanel.setVisible(false);
        metodoPago.setVisible(false);
        productosPanel.setVisible(false);
        estadísticaPanel.setVisible(false);
        panelRegistrarProveedor.setVisible(false);
        panelModificarProveedor.setVisible(false);
        proveedorSeleccionado = null;
        panelListaProveedores.setVisible(true);
        proveedoresPanel.setVisible(true);
        cargarTablaProveedores();
        proveedoresPanel.revalidate();
        proveedoresPanel.repaint();
    }

    private void filtrarProductosP() {

        NProducto np = new NProducto();
        List<Producto> lista = np.listar();

        String codigo = txtCodigoPP.getText().trim();
        String descripcion = txtDescripcionPP.getText().trim();

        if (!codigo.isEmpty()) {
            // 1) Solo código
            lista = lista.stream()
                    .filter(p -> String.valueOf(p.getCodigo()).toLowerCase()
                    .contains(codigo.toLowerCase()))
                    .collect(Collectors.toList());

        } else if (!descripcion.isEmpty()) {
            // 2) Solo descripción
            lista = lista.stream()
                    .filter(p -> p.getDescripcion() != null
                    && p.getDescripcion().toLowerCase()
                            .contains(descripcion.toLowerCase()))
                    .collect(Collectors.toList());

        } else {
            // 3) Rubro + Stock (van juntos)
            RubroProducto rubro = (RubroProducto) cbRubroP1.getSelectedItem();
            int opcionStock = cbStockP.getSelectedIndex(); // 0=Todo, 1=Ascendente, 2=Descendente

            if (rubro != null) {
                lista = lista.stream()
                        .filter(p -> p.getIdRubroProducto() != null
                        && p.getIdRubroProducto().getIdRubroProducto() == rubro.getIdRubroProducto())
                        .collect(Collectors.toList());
            }

            if (opcionStock == 1) {
                lista.sort(Comparator.comparingInt(this::obtenerStock));
            } else if (opcionStock == 2) {
                lista.sort(Comparator.comparingInt(this::obtenerStock).reversed());
            }
            // opcionStock == 0 -> no se ordena ni se toma en cuenta el stock
        }

        cargarTablaProductos(lista);
    }
    // Stock actual (con protección por si el producto no tiene StockProducto)

    private int obtenerStock(Producto p) {
        return p.getIdStockProducto() != null ? p.getIdStockProducto().getStockAct() : 0;
    }

    private void cargarTablaProductos(List<Producto> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tablaProductosP.getModel();
        modelo.setRowCount(0);

        for (Producto p : lista) {
            modelo.addRow(new Object[]{
                p.getCodigo(),
                p.getDescripcion(),
                p.getIdRubroProducto() != null ? p.getIdRubroProducto().getDescripcion() : "",
                p.getIdTipoProducto() != null ? p.getIdTipoProducto().getDescripcion() : "",
                obtenerStock(p),
                p.getPrecioUnitario(),
                p.getIdProveedor() != null ? p.getIdProveedor().getNombre() : ""
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AGAceptar;
    private javax.swing.JPanel AccionGestion;
    private javax.swing.JPanel AccionesProveedores;
    private javax.swing.JButton AgregarCliMP;
    private javax.swing.JButton AgregarProdBP;
    private javax.swing.JButton AgregarV;
    private javax.swing.JLabel AmpLogo2;
    private javax.swing.JPanel BtnDetalleCC;
    private javax.swing.JButton BuscarCliMP;
    private javax.swing.JButton BuscarDescV;
    private javax.swing.JButton BuscarProdBP;
    private javax.swing.JButton BuscarProdBPDesc;
    private javax.swing.JButton BuscarV;
    private javax.swing.JButton ConfirmarVenta;
    private javax.swing.JButton DatosCobroVenta;
    private javax.swing.JPanel DatosEmpleado;
    private javax.swing.JPanel DatosProdAPanel;
    private javax.swing.JLabel DescuentoV;
    private javax.swing.JButton EliminarItemV;
    private javax.swing.JButton EliminarP;
    private javax.swing.JToggleButton EstadodePromo;
    private com.toedter.calendar.JDateChooser FechaFinOferta;
    private com.toedter.calendar.JDateChooser FechaInicioOferta;
    private javax.swing.JButton GVolver;
    private javax.swing.JPanel GestionAdmPanel;
    private javax.swing.JPanel GestionProdPanel;
    private javax.swing.JPanel HistorialEmpleados;
    private javax.swing.JPanel IVAPanel;
    private javax.swing.JPanel InicioAdmPanel;
    private javax.swing.JPanel InicioEstPanel;
    private javax.swing.JPanel InicioPanel;
    private javax.swing.JPanel InicioProdPanel;
    private javax.swing.JLabel IvaV;
    private javax.swing.JLabel LeyendaAMPinicio;
    private javax.swing.JButton LimpiarFiltroBP;
    private javax.swing.JButton LimpiarMp;
    private javax.swing.JPanel ListaPanel;
    private javax.swing.JPanel ListasPPanel;
    private javax.swing.JButton MGAceptar;
    private javax.swing.JButton ModificarP;
    private javax.swing.JPanel NAVPAGOS;
    private javax.swing.JButton NuevoP;
    private javax.swing.JFrame PBuscarProductoV;
    private javax.swing.JPanel PagosPanel;
    private javax.swing.JPanel PagosPanelCampos;
    private javax.swing.JPanel PanelBotones;
    private javax.swing.JPanel PanelCalculo;
    private javax.swing.JPanel PanelConfigMiNegocio;
    private javax.swing.JPanel PanelConfigPersonal;
    private javax.swing.JPanel PanelConfigSeleccion;
    private javax.swing.JPanel PanelConfiguracion;
    private javax.swing.JPanel PanelFiltroListasAdm;
    private javax.swing.JPanel PanelInicioPresentacion;
    private javax.swing.JPanel PanelNuevaOferta;
    private javax.swing.JPanel PanelNuevaPromo;
    private javax.swing.JPanel PanelOfertaExistente;
    private javax.swing.JPanel PanelOfertas;
    private javax.swing.JPanel PanelOpcAdm;
    private javax.swing.JPanel PanelPromoExistente;
    private javax.swing.JPanel PanelPromociones;
    private javax.swing.JLabel PrecioNuevo;
    private javax.swing.JButton RehacerV;
    private javax.swing.JButton RehacerVenta;
    private javax.swing.JScrollPane ScrollDetalle;
    private javax.swing.JLabel SubtotalV;
    private javax.swing.JLabel TotalV;
    private javax.swing.JPanel actPanel;
    private javax.swing.JPanel actPanel1;
    private javax.swing.JPanel administracionPanel;
    private javax.swing.JPanel barritaEncabezado;
    private javax.swing.JButton btActivarPromo;
    private javax.swing.JButton btCalcular;
    private javax.swing.JButton btCalcularCuotas;
    private javax.swing.JButton btDesactivarPromo1;
    private javax.swing.JButton btGuardarOferta;
    private javax.swing.JButton btGuardarPromo;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btImprimirOferta;
    private javax.swing.JButton btLimpiarOferta;
    private javax.swing.JButton btPrevisualizarPrecio;
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
    private javax.swing.JButton btnAOfertas;
    private javax.swing.JButton btnAPagos;
    private javax.swing.JButton btnAPersona;
    private javax.swing.JButton btnAPromociones;
    private javax.swing.JButton btnAVolver;
    private javax.swing.JButton btnAccesos;
    private javax.swing.JButton btnActualizarInformeStock;
    private javax.swing.JButton btnActualizarP;
    private javax.swing.JButton btnAdministracion;
    private javax.swing.JButton btnAdministracion1;
    private javax.swing.JButton btnAdministracion2;
    private javax.swing.JButton btnAdministracion3;
    private javax.swing.JButton btnAdministracion4;
    private javax.swing.JButton btnAdministracionProveedores;
    private javax.swing.JButton btnBajoStockP;
    private javax.swing.JButton btnBuscarProveedor;
    private javax.swing.JButton btnCaja;
    private javax.swing.JButton btnCaja1;
    private javax.swing.JButton btnCaja2;
    private javax.swing.JButton btnCaja3;
    private javax.swing.JButton btnCaja4;
    private javax.swing.JButton btnCajaProveedores;
    private javax.swing.JButton btnCancelarModificacionProveedor;
    private javax.swing.JButton btnCancelarRegistroProveedor;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnConfirmarRegistroProveedor;
    private javax.swing.JButton btnEliminadosP;
    private javax.swing.JButton btnEliminarPersonal;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnEstadisticas;
    private javax.swing.JButton btnEstadisticas1;
    private javax.swing.JButton btnEstadisticas2;
    private javax.swing.JButton btnEstadisticas3;
    private javax.swing.JButton btnEstadisticas4;
    private javax.swing.JButton btnEstadisticasProveedores;
    private javax.swing.JButton btnExportarInformePDF;
    private javax.swing.JButton btnExportarInformePDF1;
    private javax.swing.JButton btnExportarProveedores;
    private javax.swing.JButton btnExportarStockExcel;
    private javax.swing.JButton btnExportarStockPdf;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnFiltrarProducto;
    private javax.swing.JButton btnGestionarP;
    private javax.swing.JButton btnGuardarEmpresa;
    private javax.swing.JButton btnGuardarModificacionProveedor;
    private javax.swing.JButton btnGuardarUsuario;
    private javax.swing.JButton btnInformeClientes;
    private javax.swing.JButton btnInformeStock;
    private javax.swing.JButton btnInformeVentas;
    private javax.swing.JButton btnInformes;
    private javax.swing.JButton btnInformes1;
    private javax.swing.JButton btnInformes2;
    private javax.swing.JButton btnInformes3;
    private javax.swing.JButton btnInformes4;
    private javax.swing.JButton btnInformesProveedores;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiarFiltroProducto;
    private javax.swing.JButton btnLimpiarFiltrosProveedor;
    private javax.swing.JButton btnListasP;
    private javax.swing.JButton btnMiNegocio;
    private javax.swing.JButton btnModificarProveedor;
    private javax.swing.JButton btnModificarUsuario;
    private javax.swing.JButton btnPersonal;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProductos1;
    private javax.swing.JButton btnProductos2;
    private javax.swing.JButton btnProductos3;
    private javax.swing.JButton btnProductos4;
    private javax.swing.JButton btnProductosProveedores;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnProveedores1;
    private javax.swing.JButton btnProveedores2;
    private javax.swing.JButton btnProveedores3;
    private javax.swing.JButton btnProveedores4;
    private javax.swing.JButton btnRegistrarProveedor;
    private javax.swing.JButton btnRoles;
    private javax.swing.JButton btnSeccionProveedores;
    private javax.swing.JButton btnSinStockP;
    private javax.swing.JButton btnVentas;
    private javax.swing.JButton btnVentas1;
    private javax.swing.JButton btnVentas2;
    private javax.swing.JButton btnVentas3;
    private javax.swing.JButton btnVentas4;
    private javax.swing.JButton btnVentasProveedores;
    private javax.swing.JButton btnVolverAInicio;
    private javax.swing.JButton btnVolverASeleccionC;
    private javax.swing.JButton btnVolverASeleccionC1;
    private javax.swing.JPanel btnsGestion;
    private javax.swing.JPanel buscarPanel;
    private javax.swing.JButton cancelarMP1;
    private javax.swing.JPanel cantidadPanel;
    private javax.swing.JComboBox<String> cbBuscarEstado;
    private javax.swing.JComboBox<CondIVA> cbCondIva;
    private javax.swing.JComboBox<CondIVA> cbCondIvaMp;
    private javax.swing.JCheckBox cbDescuento;
    private javax.swing.JComboBox<IVA> cbIVAP;
    private javax.swing.JComboBox<String> cbMetodosPagos;
    private javax.swing.JComboBox<MetodoPago> cbMpVentas;
    private javax.swing.JComboBox<String> cbNombre;
    private javax.swing.JComboBox<Producto> cbProductoOferta;
    private javax.swing.JComboBox<Proveedor> cbProveedorP;
    private javax.swing.JComboBox<Rol> cbRolesPersonal;
    private javax.swing.JComboBox<RubroProducto> cbRubroP;
    private javax.swing.JComboBox<String> cbRubroP1;
    private javax.swing.JComboBox<RubroProducto> cbRubroV;
    private javax.swing.JComboBox<String> cbStockP;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JComboBox<TipoProducto> cbTipoP;
    private javax.swing.JComboBox<String> cbTipoReporteStock;
    private javax.swing.JComboBox<TipoProducto> cbTipoV;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JPanel detPanel;
    private javax.swing.JPanel estadísticaPanel;
    private javax.swing.JPanel evolucionPanel;
    private javax.swing.JButton factA;
    private javax.swing.JButton factB;
    private javax.swing.JButton factX;
    private javax.swing.JPanel globGestion;
    private javax.swing.JLabel iconmp324;
    private javax.swing.JLabel imgAmp1;
    private javax.swing.JPanel ingresosPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
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
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JLabel labelFact;
    private javax.swing.JLabel lblBuscarProd;
    private javax.swing.JLabel lblCC;
    private javax.swing.JLabel lblCantClientes;
    private javax.swing.JLabel lblCantEmpresas;
    private javax.swing.JLabel lblCantPersonas;
    private javax.swing.JLabel lblCantVentas;
    private javax.swing.JLabel lblCantidadClientes;
    private javax.swing.JLabel lblCantidadEmpresas;
    private javax.swing.JLabel lblCantidadPersonas;
    private javax.swing.JLabel lblCantidadVentas;
    private javax.swing.JLabel lblDescripcionReporte;
    private javax.swing.JLabel lblFact;
    private javax.swing.JLabel lblHeaderAgregar;
    private javax.swing.JLabel lblHeaderAgregar1;
    private javax.swing.JLabel lblHeaderModificar;
    private javax.swing.JLabel lblHeaderModificar1;
    private javax.swing.JLabel lblImgProveedores;
    private javax.swing.JLabel lblIngTotales;
    private javax.swing.JLabel lblIngresosTotales;
    private javax.swing.JLabel lblNP2;
    private javax.swing.JLabel lblNuevoProveedorR;
    private javax.swing.JLabel lblStockTotalP;
    private javax.swing.JLabel lblTicketProm;
    private javax.swing.JLabel lblTicketPromedio;
    private javax.swing.JLabel lblTituloInformeStock;
    private javax.swing.JLabel lblTituloInformeVentas;
    private javax.swing.JLabel lblTituloInformeVentas1;
    private javax.swing.JLabel lblTituloModificarProveedor;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalProductosP;
    private javax.swing.JLabel lblTotalProveedoresP;
    private javax.swing.JLabel lblUltimaAct;
    private javax.swing.JLabel lblUltimaAct1;
    private javax.swing.JLabel lblUltimaActualizacion;
    private javax.swing.JLabel lblUltimaActualizacion1;
    private javax.swing.JLabel lblimg;
    private javax.swing.JLabel lblimg1;
    private javax.swing.JLabel lblimg2;
    private javax.swing.JLabel lblimg3;
    private javax.swing.JLabel lblimg4;
    private javax.swing.JPanel listaGestion;
    private javax.swing.JPanel listaPanel;
    private javax.swing.JPanel metodoPago;
    private javax.swing.JPanel metodosPanel;
    private javax.swing.JPanel navbarADM;
    private javax.swing.JPanel navbarADMInicio;
    private javax.swing.JPanel navbarVNT;
    private javax.swing.JPanel panelCli;
    private javax.swing.JPanel panelContenidoEstadisticas;
    private javax.swing.JPanel panelDatosProveedorModificar;
    private javax.swing.JPanel panelDatosProveedorRegistro;
    private javax.swing.JPanel panelFact;
    private javax.swing.JPanel panelGraficoClientesIVA;
    private javax.swing.JPanel panelGraficoClientesTipo;
    private javax.swing.JPanel panelGraficoMetodos;
    private javax.swing.JPanel panelGraficoStockInforme;
    private javax.swing.JPanel panelGraficoVentas;
    private javax.swing.JPanel panelInformeClientes;
    private javax.swing.JPanel panelInformePDF;
    private javax.swing.JPanel panelInformeStock;
    private javax.swing.JPanel panelListaProveedores;
    private javax.swing.JPanel panelModificarProveedor;
    private javax.swing.JPanel panelRegistrarProveedor;
    private javax.swing.JPanel productosPanel;
    private javax.swing.JPanel promedioPanel;
    private javax.swing.JPanel proveedoresPanel;
    private javax.swing.JPanel regEmp;
    private javax.swing.JPanel resumenPanel;
    private javax.swing.JScrollPane scrollTablaProveedores;
    private javax.swing.JTable tablaListaCC;
    private javax.swing.JTable tablaMiPersonal;
    private javax.swing.JTable tablaProductosP;
    private javax.swing.JTable tablaProductosV;
    private javax.swing.JTable tablaProveedores;
    private javax.swing.JTable tablaVenta;
    private javax.swing.JScrollPane tableOferta;
    private javax.swing.JScrollPane tablePromo;
    private javax.swing.JPanel tarjetaDetalle;
    private javax.swing.JTable tblOferta;
    private javax.swing.JTable tblPromo;
    private javax.swing.JPanel tipoPanel;
    private javax.swing.JPanel totClientesPanel;
    private javax.swing.JPanel totEmpresasPanel;
    private javax.swing.JPanel totPersonasPanel;
    private javax.swing.JTextField txtBuscarRazonSocial;
    private javax.swing.JTextField txtBuscarRubro;
    private javax.swing.JTextField txtCantV;
    private javax.swing.JPasswordField txtClaveUsuario;
    private javax.swing.JTextField txtCodP;
    private javax.swing.JTextField txtCodV;
    private javax.swing.JTextField txtCodigoPP;
    private javax.swing.JTextField txtComprasTotales;
    private javax.swing.JTextField txtCuil;
    private javax.swing.JTextField txtCuit;
    private javax.swing.JTextField txtCuitCliente;
    private javax.swing.JTextField txtCuitModificarProveedor;
    private javax.swing.JTextField txtCuitProveedor;
    private javax.swing.JTextField txtCuotas;
    private javax.swing.JTextField txtCuotasPagos;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtDescBP;
    private javax.swing.JTextArea txtDescP;
    private javax.swing.JTextField txtDescV;
    private javax.swing.JTextField txtDescripcionPP;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDireccionModificarProveedor;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtDom;
    private javax.swing.JTextField txtDomCliente;
    private javax.swing.JTextField txtDomEmpresa;
    private javax.swing.JTextField txtDomUsuario;
    private javax.swing.JTextField txtEmailModificarProveedor;
    private javax.swing.JTextField txtEmailProveedor;
    private javax.swing.JTextField txtFiltroCuit;
    private javax.swing.JTextField txtFiltroRS;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtInteres;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtMailUsuario;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JLabel txtNomCC;
    private javax.swing.JTextField txtNomEmpresa;
    private javax.swing.JTextField txtNomUsuario;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreModificarProveedor;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtPre1;
    private javax.swing.JTextField txtPre2;
    private javax.swing.JLabel txtPrecioOferta;
    private javax.swing.JTextField txtPrecioP;
    private javax.swing.JTextField txtPromocion;
    private javax.swing.JTextField txtProvEmpresa;
    private javax.swing.JTextField txtRS;
    private javax.swing.JTextField txtRubroModificarProveedor;
    private javax.swing.JTextField txtRubroProveedor;
    private javax.swing.JLabel txtSaldo1;
    private javax.swing.JLabel txtSaldoT;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtStockActP;
    private javax.swing.JTextField txtStockMaxP;
    private javax.swing.JTextField txtStockMinP;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTel;
    private javax.swing.JTextField txtTelEmpresa;
    private javax.swing.JTextField txtTelUsuario;
    private javax.swing.JTextField txtTelefonoModificarProveedor;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JTextField txtTipoCondicion;
    private javax.swing.JTextField txtUnidadCuota;
    private javax.swing.JPanel ventasPanel;
    private javax.swing.JPanel verDetallePanel;
    // End of variables declaration//GEN-END:variables
}
