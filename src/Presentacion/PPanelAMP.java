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
import Modelos.Venta;
import Negocio.NIVA;
import Negocio.NMetodoPago;
import Negocio.NRubroProducto;
import Negocio.NTipoProducto;
import Negocio.NVenta;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import Datos.DEstadistica;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Timer;

public class PPanelAMP extends javax.swing.JFrame {

    private Cliente actual = null;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PPanelAMP.class.getName());
    private NDetalleVenta nDetalleVenta = new NDetalleVenta();
    private NProducto nProducto = new NProducto();
    DecimalFormat df = new DecimalFormat("#,##0.00");
    private float totalVenta = 0;
    private float descuentoTotal = 0;
    private float subtotalVenta = 0;
    private float ivaCalculado = 0;
    private List<DetalleVenta> ListaVentas = new ArrayList<>();
    private final DEstadistica dEstadistica = new DEstadistica();
    private Timer timerEstadisticas;

    private final Negocio.NCliente nCliente = new Negocio.NCliente();
    private final Negocio.NMetodoPago nMetodoPago = new Negocio.NMetodoPago();
    private final Negocio.NCobros nCobros = new Negocio.NCobros();
    private java.util.List<Modelos.Cliente> listaClientesGlobal;
    private java.util.List<Modelos.MetodoPago> listaMetodosPagoGlobal;
    private java.util.Date fechaVentaOriginal;

    public PPanelAMP() {
        initComponents();
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
        txtCuotas = new javax.swing.JTextField();
        labelFact = new javax.swing.JLabel();
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
        panelInformeVentas = new javax.swing.JPanel();
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
        metodosPanel = new javax.swing.JPanel();
        actPanel = new javax.swing.JPanel();
        lblUltimaAct = new javax.swing.JLabel();
        lblUltimaActualizacion = new javax.swing.JLabel();
        btnExportarInformeVentas = new javax.swing.JButton();
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
        setPreferredSize(new java.awt.Dimension(980, 738));
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
                .addGap(0, 4, Short.MAX_VALUE))
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
        panelFact.add(cbMpVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 260, 30));

        factB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        factB.setText("B");
        factB.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        factB.addActionListener(this::factBActionPerformed);
        panelFact.add(factB, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 120, 30));

        factA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        factA.setText("A");
        factA.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        factA.addActionListener(this::factAActionPerformed);
        panelFact.add(factA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 120, 30));

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

        txtCuotas.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtCuotas.setForeground(new java.awt.Color(1, 25, 48));
        txtCuotas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuotas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelFact.add(txtCuotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 260, -1));

        labelFact.setFont(new java.awt.Font("Serif", 0, 90)); // NOI18N
        labelFact.setForeground(new java.awt.Color(20, 52, 83));
        labelFact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFact.setText("X");
        labelFact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 52, 83), 2));
        panelFact.add(labelFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 110, 110));

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
        panelContenidoEstadisticas.setPreferredSize(new java.awt.Dimension(976, 50));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelContenidoEstadisticasLayout.setVerticalGroup(
            panelContenidoEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnInformeVentas, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(btnInformeClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnInformeStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelInformeVentas.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout evolucionPanelLayout = new javax.swing.GroupLayout(evolucionPanel);
        evolucionPanel.setLayout(evolucionPanelLayout);
        evolucionPanelLayout.setHorizontalGroup(
            evolucionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
        );
        evolucionPanelLayout.setVerticalGroup(
            evolucionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );

        metodosPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Métodos de pago"));

        javax.swing.GroupLayout metodosPanelLayout = new javax.swing.GroupLayout(metodosPanel);
        metodosPanel.setLayout(metodosPanelLayout);
        metodosPanelLayout.setHorizontalGroup(
            metodosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );
        metodosPanelLayout.setVerticalGroup(
            metodosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
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

        btnExportarInformeVentas.setText("Exportar informe PDF");

        javax.swing.GroupLayout panelInformeVentasLayout = new javax.swing.GroupLayout(panelInformeVentas);
        panelInformeVentas.setLayout(panelInformeVentasLayout);
        panelInformeVentasLayout.setHorizontalGroup(
            panelInformeVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformeVentasLayout.createSequentialGroup()
                .addGroup(panelInformeVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformeVentasLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(actPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInformeVentasLayout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(lblTituloInformeVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformeVentasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInformeVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformeVentasLayout.createSequentialGroup()
                        .addComponent(cantidadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(ingresosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addComponent(promedioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(237, 237, 237))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformeVentasLayout.createSequentialGroup()
                        .addComponent(evolucionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInformeVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnExportarInformeVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metodosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))))
        );
        panelInformeVentasLayout.setVerticalGroup(
            panelInformeVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformeVentasLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTituloInformeVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelInformeVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ingresosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(promedioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(panelInformeVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(evolucionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(metodosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(panelInformeVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportarInformeVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelInformeStockLayout = new javax.swing.GroupLayout(panelInformeStock);
        panelInformeStock.setLayout(panelInformeStockLayout);
        panelInformeStockLayout.setHorizontalGroup(
            panelInformeStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );
        panelInformeStockLayout.setVerticalGroup(
            panelInformeStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout estadísticaPanelLayout = new javax.swing.GroupLayout(estadísticaPanel);
        estadísticaPanel.setLayout(estadísticaPanelLayout);
        estadísticaPanelLayout.setHorizontalGroup(
            estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(estadísticaPanelLayout.createSequentialGroup()
                .addGroup(estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelInformeVentas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(panelContenidoEstadisticas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE))
                .addGap(0, 0, 0))
            .addGroup(estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelInformeStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(panelInformeVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(estadísticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, estadísticaPanelLayout.createSequentialGroup()
                    .addGap(0, 99, Short.MAX_VALUE)
                    .addComponent(panelInformeStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        administracionPanel.setVisible(false);
        metodoPago.setVisible(false);
        ventasPanel.setVisible(true);
        estadísticaPanel.setVisible(false);
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProductos1ActionPerformed

    private void btnAdministracion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracion1ActionPerformed
        administracionPanel.setVisible(true);
        metodoPago.setVisible(false);
        ventasPanel.setVisible(false);
        estadísticaPanel.setVisible(false);
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
            cargarTablaVenta();
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
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmarVentaActionPerformed

    private void txtPre2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPre2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPre2ActionPerformed

    private void LimpiarMpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarMpActionPerformed
        txtCuitCliente.setText("");
        txtNombreCliente.setText("");
        txtDomCliente.setText("");
        txtCuotas.setText("");
        noSeleccionFactura();
        factA.setEnabled(true);
        factB.setEnabled(true);
    }//GEN-LAST:event_LimpiarMpActionPerformed

    private void noSeleccionFactura() {
        labelFact.setText("X");
        lblFact.setText("X");
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
        factB.setBackground(Color.WHITE);
        factB.setForeground(Color.BLACK);
    }

    private void seleccionarFacturaB() {
        labelFact.setText("B");
        lblFact.setText("B");
        factA.setBackground(Color.WHITE);
        factA.setForeground(Color.BLACK);
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
        try {
            String cuit = txtCuitCliente.getText().trim();
            System.out.println("CUIT buscado: " + cuit);
            NCliente negocio = new NCliente();
            Cliente c = negocio.select(cuit);
            System.out.println("Cliente obtenido: " + c);

            if (c != null) {
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
    }//GEN-LAST:event_BuscarCliMPActionPerformed

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
        } else {
            txtCuotas.setEnabled(true);
        }
        calcularDescuento();
    }//GEN-LAST:event_cbMpVentasActionPerformed

    private void AgregarCliMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarCliMPActionPerformed
        String cuit = txtCuitCliente.getText();
        if (!txtCuit.getText().matches("\\d{11}")) {
            javax.swing.JOptionPane.showMessageDialog(this, "El CUIT debe tener 11 dígitos numéricos");
        } else {
            try {
                if (txtNombreCliente.getText() != "" && txtCuitCliente.getText() != "" && txtDomCliente.getText() != "") {
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
                    txtRS.setText("");
                    txtTel.setText("");
                    txtDom.setText("");
                    txtCuit.setText("");
                    txtMail.setText("");
                    txtDate.setDate(null);

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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        calcularDescuento();
        calcularIVA();
        determinarCondicionIva();
        metodoPago.setVisible(false);
        ventasPanel.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnVentas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas2ActionPerformed
        administracionPanel.setVisible(false);
        metodoPago.setVisible(false);
        ventasPanel.setVisible(true);
        estadísticaPanel.setVisible(false);
        formatearTablaVenta();
    }//GEN-LAST:event_btnVentas2ActionPerformed

    private void btnProductos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProductos2ActionPerformed

    private void btnEstadisticas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticas2ActionPerformed
        administracionPanel.setVisible(false);
        ventasPanel.setVisible(false);
        metodoPago.setVisible(false);
        estadísticaPanel.setVisible(true);
        
        actualizarEstadisticasVentas();
    }//GEN-LAST:event_btnEstadisticas2ActionPerformed

    private void btnInformeClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInformeClientesActionPerformed

    private void btnInformeVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeVentasActionPerformed
        cambiarColorBotonesEstadisticas(true);
        panelInformeVentas.setVisible(true);
        panelInformeStock.setVisible(false);
    }//GEN-LAST:event_btnInformeVentasActionPerformed

    private void btnEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticasActionPerformed
        administracionPanel.setVisible(false);
        ventasPanel.setVisible(false);
        metodoPago.setVisible(false);
        estadísticaPanel.setVisible(true);
        
        actualizarEstadisticasVentas();
    }//GEN-LAST:event_btnEstadisticasActionPerformed

    private void btnAdministracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracionActionPerformed
        administracionPanel.setVisible(true);
        metodoPago.setVisible(false);
        ventasPanel.setVisible(false);
        estadísticaPanel.setVisible(false);
    }//GEN-LAST:event_btnAdministracionActionPerformed

    private void btnAdministracion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracion2ActionPerformed
        administracionPanel.setVisible(true);
        metodoPago.setVisible(false);
        ventasPanel.setVisible(false);
        estadísticaPanel.setVisible(false);
    }//GEN-LAST:event_btnAdministracion2ActionPerformed

    private void btnEstadisticas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticas1ActionPerformed
        administracionPanel.setVisible(false);
        ventasPanel.setVisible(false);
        metodoPago.setVisible(false);
        estadísticaPanel.setVisible(true);
        
        actualizarEstadisticasVentas();
    }//GEN-LAST:event_btnEstadisticas1ActionPerformed

    private void btnInformeStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeStockActionPerformed
        cambiarColorBotonesEstadisticas(false);
        panelInformeStock.setVisible(true);
        panelInformeVentas.setVisible(false);
    }//GEN-LAST:event_btnInformeStockActionPerformed

    private void calcularDescuento() {
        Locale localeAR = new Locale("es", "AR");
        NumberFormat formato = NumberFormat.getCurrencyInstance(localeAR);
        MetodoPago mp = (MetodoPago) cbMpVentas.getSelectedItem();
        boolean aplicaDescuento = mp != null
                && (mp.getDescripcion().equals("Efectivo") || mp.getDescripcion().equals("Transferencia"));
        NProducto np = new NProducto();
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
        CondIVA condCliente = (CondIVA) cbCondIva.getSelectedItem();
        Locale localeAR = new Locale("es", "AR");
        NumberFormat formato = NumberFormat.getCurrencyInstance(localeAR);

        if (condCliente == null) {
            return;
        }

        String desc = condCliente.getDescripcion();
        boolean exento = desc.equals("Exento");
        boolean discrimina = desc.equals("Responsable Inscripto");

        if (exento) {
            IvaV.setText(formato.format(0));
            totalVenta = subtotalVenta;
        } else if (discrimina) {
            IvaV.setText(formato.format(ivaCalculado));
            totalVenta = subtotalVenta + ivaCalculado;
        } else {
            IvaV.setText(formato.format(ivaCalculado) + " (incluido)");
            totalVenta = subtotalVenta;
        }

        TotalV.setText(formato.format(totalVenta)); // <- reemplazar por tu campo real
    }
    
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
            int cantidadVentas =
                    dEstadistica.obtenerCantidadVentas();

            double ingresosTotales =
                    dEstadistica.obtenerIngresosTotales();

            double ticketPromedio =
                    dEstadistica.obtenerTicketPromedio();

            DecimalFormat formatoMoneda =
                    new DecimalFormat("$ #,##0.00");

            lblCantidadVentas.setText(
                    String.valueOf(cantidadVentas)
            );

            lblIngresosTotales.setText(
                    formatoMoneda.format(ingresosTotales)
            );

            lblTicketPromedio.setText(
                    formatoMoneda.format(ticketPromedio)
            );

            DateTimeFormatter formatoFecha =
                    DateTimeFormatter.ofPattern(
                            "dd/MM/yyyy HH:mm:ss"
                    );

            lblUltimaActualizacion.setText(
                LocalDateTime.now().format(formatoFecha)
            );

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
                    && panelInformeVentas.isVisible()) {

                actualizarEstadisticasVentas();
            }
        });

        timerEstadisticas.start();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new PLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel DescuentoV;
    private javax.swing.JButton EliminarItemV;
    private javax.swing.JLabel IvaV;
    private javax.swing.JButton LimpiarFiltroBP;
    private javax.swing.JButton LimpiarMp;
    private javax.swing.JPanel ListaPanel;
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
    private javax.swing.JButton btnAdministracion;
    private javax.swing.JButton btnAdministracion1;
    private javax.swing.JButton btnAdministracion2;
    private javax.swing.JButton btnCaja;
    private javax.swing.JButton btnCaja1;
    private javax.swing.JButton btnCaja2;
    private javax.swing.JButton btnEstadisticas;
    private javax.swing.JButton btnEstadisticas1;
    private javax.swing.JButton btnEstadisticas2;
    private javax.swing.JButton btnExportarInformeVentas;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnInformeClientes;
    private javax.swing.JButton btnInformeStock;
    private javax.swing.JButton btnInformeVentas;
    private javax.swing.JButton btnInformes;
    private javax.swing.JButton btnInformes1;
    private javax.swing.JButton btnInformes2;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProductos1;
    private javax.swing.JButton btnProductos2;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnProveedores1;
    private javax.swing.JButton btnProveedores2;
    private javax.swing.JButton btnVentas;
    private javax.swing.JButton btnVentas1;
    private javax.swing.JButton btnVentas2;
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
    private javax.swing.JPanel ingresosPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelFact;
    private javax.swing.JLabel lblCC;
    private javax.swing.JLabel lblCantVentas;
    private javax.swing.JLabel lblCantidadVentas;
    private javax.swing.JLabel lblFact;
    private javax.swing.JLabel lblIngTotales;
    private javax.swing.JLabel lblIngresosTotales;
    private javax.swing.JLabel lblTicketProm;
    private javax.swing.JLabel lblTicketPromedio;
    private javax.swing.JLabel lblTituloInformeVentas;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblUltimaAct;
    private javax.swing.JLabel lblUltimaActualizacion;
    private javax.swing.JLabel lblimg;
    private javax.swing.JLabel lblimg1;
    private javax.swing.JLabel lblimg2;
    private javax.swing.JPanel listaPanel;
    private javax.swing.JPanel metodoPago;
    private javax.swing.JPanel metodosPanel;
    private javax.swing.JPanel navbarADM;
    private javax.swing.JPanel navbarVNT;
    private javax.swing.JPanel panelCli;
    private javax.swing.JPanel panelContenidoEstadisticas;
    private javax.swing.JPanel panelFact;
    private javax.swing.JPanel panelInformeStock;
    private javax.swing.JPanel panelInformeVentas;
    private javax.swing.JPanel promedioPanel;
    private javax.swing.JPanel regEmp;
    private javax.swing.JPanel resumenPanel;
    private javax.swing.JTable tablaLista;
    private javax.swing.JTable tablaProductosV;
    private javax.swing.JTable tablaVenta;
    private javax.swing.JPanel tarjetaDetalle;
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
