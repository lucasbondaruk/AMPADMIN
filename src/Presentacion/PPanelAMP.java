package Presentacion;

import java.util.Date;
import java.awt.Color;
import Modelos.Cliente;
import Negocio.NCliente;
import Negocio.NCondIVA;
import Modelos.CondIVA;
import Modelos.MetodoPago;
import Negocio.NMetodoPago;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class PPanelAMP extends javax.swing.JFrame {

    private Cliente actual = null;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PPanelAMP.class.getName());

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

        cargarTabla();

        cargarClientes();
        cargarMetodosPago();
        revalidate();
        repaint();
    }
    private boolean opc;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        btnAContinuar1 = new javax.swing.JButton();
        btnAContinuar3 = new javax.swing.JButton();
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
        cargaPanel = new javax.swing.JPanel();
        lblFact = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        cbProductosV = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        txtPre1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtPre2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtIVA = new javax.swing.JTextField();
        Agregar = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        listaPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        BtnsVNT1 = new javax.swing.JPanel();
        btnABuscar1 = new javax.swing.JButton();
        btnAContinuar2 = new javax.swing.JButton();
        btnAModificar1 = new javax.swing.JButton();
        btnABorrar1 = new javax.swing.JButton();
        metodoPago = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
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
        jLabel45 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        cbCondIvaMp = new javax.swing.JComboBox<>();
        AgregarCliMP = new javax.swing.JButton();
        BuscarCliMP = new javax.swing.JButton();
        txtDomCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtCuitCliente = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        LimpiarMp = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        cancelarMP1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(991, 777));
        setMinimumSize(new java.awt.Dimension(991, 777));
        setPreferredSize(new java.awt.Dimension(991, 777));
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
        btnAContinuar.setText("<Continuar>");
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

        btnAContinuar1.setBackground(new java.awt.Color(22, 22, 22));
        btnAContinuar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAContinuar1.setForeground(new java.awt.Color(255, 255, 255));
        btnAContinuar1.setText("<Gestionar>");
        btnAContinuar1.setActionCommand("<Volver>");
        btnAContinuar1.setBorderPainted(false);
        btnAContinuar1.addActionListener(this::btnAContinuar1ActionPerformed);

        btnAContinuar3.setBackground(new java.awt.Color(22, 22, 22));
        btnAContinuar3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAContinuar3.setForeground(new java.awt.Color(255, 255, 255));
        btnAContinuar3.setText("<Ver detalle>");
        btnAContinuar3.setToolTipText("");
        btnAContinuar3.setActionCommand("<Volver>");
        btnAContinuar3.setBorderPainted(false);
        btnAContinuar3.addActionListener(this::btnAContinuar3ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(btnAContinuar1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158)
                .addComponent(btnAContinuar3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAContinuar1)
                    .addComponent(btnAContinuar3))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        ListaPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 980, 40));
        ListaPanel.add(txtFiltroCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 110, -1));
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
        navbarVNT.add(btnEstadisticas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 120, 50));

        btnProductos1.setBackground(new java.awt.Color(204, 255, 255));
        btnProductos1.setForeground(new java.awt.Color(0, 51, 102));
        btnProductos1.setText("Productos");
        btnProductos1.setBorderPainted(false);
        btnProductos1.setContentAreaFilled(false);
        btnProductos1.addActionListener(this::btnProductos1ActionPerformed);
        navbarVNT.add(btnProductos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 100, 50));

        ventasPanel.add(navbarVNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, -1));

        cargaPanel.setBackground(new java.awt.Color(2, 33, 55));
        cargaPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFact.setFont(new java.awt.Font("Serif", 0, 100)); // NOI18N
        lblFact.setForeground(new java.awt.Color(255, 255, 255));
        lblFact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFact.setText("X");
        lblFact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        cargaPanel.add(lblFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 90));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("%");
        cargaPanel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, -1, -1));

        txtStock.setEditable(false);
        txtStock.setBackground(new java.awt.Color(204, 204, 204));
        txtStock.setForeground(new java.awt.Color(153, 153, 153));
        txtStock.setText("Stock");
        cargaPanel.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, 60, 30));

        cbProductosV.setEditable(true);
        cbProductosV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Data1", "Data2", "Data2", "Data3", "Data4" }));
        cbProductosV.setBorder(null);
        cargaPanel.add(cbProductosV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 250, 30));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Código ");
        cargaPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));
        cargaPanel.add(txtCant, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 40, 60, 30));

        txtPre1.setEditable(false);
        txtPre1.setBackground(new java.awt.Color(204, 204, 204));
        txtPre1.setForeground(new java.awt.Color(153, 153, 153));
        txtPre1.setText("PRECIO 1");
        cargaPanel.add(txtPre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 90, 30));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Precio Unit.");
        cargaPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, -1));

        txtPre2.setEditable(false);
        txtPre2.setBackground(new java.awt.Color(204, 204, 204));
        txtPre2.setForeground(new java.awt.Color(153, 153, 153));
        txtPre2.setText("PRECIO 2");
        txtPre2.addActionListener(this::txtPre2ActionPerformed);
        cargaPanel.add(txtPre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 90, 30));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Precio OF");
        cargaPanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, -1, -1));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Stock act.");
        cargaPanel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Descripción");
        cargaPanel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));
        cargaPanel.add(txtCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 50, 30));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("IVA");
        cargaPanel.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, -1));

        txtIVA.setEditable(false);
        txtIVA.setBackground(new java.awt.Color(204, 204, 204));
        txtIVA.setForeground(new java.awt.Color(153, 153, 153));
        txtIVA.setText("IVA");
        cargaPanel.add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, 40, 30));

        Agregar.setText("Agregar");
        Agregar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cargaPanel.add(Agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, 100, 30));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Cantidad");
        cargaPanel.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, -1, -1));

        ventasPanel.add(cargaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 110));

        listaPanel.setBackground(new java.awt.Color(255, 255, 255));
        listaPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Descripción", "Cantidad", "P.Unitario", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable1.setRowHeight(25);
        jScrollPane2.setViewportView(jTable1);

        listaPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 480));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Total:");
        listaPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 510, -1, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setText("Descuento:");
        listaPanel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 490, -1, -1));

        ventasPanel.add(listaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 980, 540));

        BtnsVNT1.setBackground(new java.awt.Color(22, 22, 22));

        btnABuscar1.setBackground(new java.awt.Color(22, 22, 22));
        btnABuscar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnABuscar1.setForeground(new java.awt.Color(255, 255, 255));
        btnABuscar1.setText("<Eliminar item>");
        btnABuscar1.setBorderPainted(false);
        btnABuscar1.addActionListener(this::btnABuscar1ActionPerformed);

        btnAContinuar2.setBackground(new java.awt.Color(22, 22, 22));
        btnAContinuar2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAContinuar2.setForeground(new java.awt.Color(255, 255, 255));
        btnAContinuar2.setText("<Continuar>");
        btnAContinuar2.setBorderPainted(false);
        btnAContinuar2.addActionListener(this::btnAContinuar2ActionPerformed);

        btnAModificar1.setBackground(new java.awt.Color(22, 22, 22));
        btnAModificar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAModificar1.setForeground(new java.awt.Color(255, 255, 255));
        btnAModificar1.setText("<Rehacer>");
        btnAModificar1.setBorderPainted(false);
        btnAModificar1.addActionListener(this::btnAModificar1ActionPerformed);

        btnABorrar1.setBackground(new java.awt.Color(22, 22, 22));
        btnABorrar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnABorrar1.setForeground(new java.awt.Color(255, 255, 255));
        btnABorrar1.setText("<Imprimir>");
        btnABorrar1.setBorderPainted(false);
        btnABorrar1.addActionListener(this::btnABorrar1ActionPerformed);

        javax.swing.GroupLayout BtnsVNT1Layout = new javax.swing.GroupLayout(BtnsVNT1);
        BtnsVNT1.setLayout(BtnsVNT1Layout);
        BtnsVNT1Layout.setHorizontalGroup(
            BtnsVNT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnsVNT1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAContinuar2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnABuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnAModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnABorrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        BtnsVNT1Layout.setVerticalGroup(
            BtnsVNT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnsVNT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnABuscar1)
                .addComponent(btnAContinuar2)
                .addComponent(btnAModificar1)
                .addComponent(btnABorrar1))
        );

        ventasPanel.add(BtnsVNT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 980, 40));

        getContentPane().add(ventasPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 740));

        metodoPago.setBackground(new java.awt.Color(0, 0, 22));
        metodoPago.setMaximumSize(new java.awt.Dimension(700, 650));
        metodoPago.setMinimumSize(new java.awt.Dimension(700, 650));
        metodoPago.setName(""); // NOI18N
        metodoPago.setPreferredSize(new java.awt.Dimension(700, 650));
        metodoPago.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Serif", 0, 48)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("DATOS DEL PAGO");
        metodoPago.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        panelFact.setBackground(new java.awt.Color(19, 107, 116));
        panelFact.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 98, 101), 5, true));
        panelFact.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbMpVentas.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cbMpVentas.setBorder(null);
        cbMpVentas.addActionListener(this::cbMpVentasActionPerformed);
        panelFact.add(cbMpVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 340, 30));

        factB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        factB.setText("B");
        factB.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        factB.addActionListener(this::factBActionPerformed);
        panelFact.add(factB, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 160, 30));

        factA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        factA.setText("A");
        factA.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        factA.addActionListener(this::factAActionPerformed);
        panelFact.add(factA, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 160, 30));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Tipo de factura:");
        panelFact.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Método de pago:"); // NOI18N
        panelFact.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Cuotas:");
        panelFact.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, -1));

        txtCuotas.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        panelFact.add(txtCuotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 160, -1));

        labelFact.setFont(new java.awt.Font("Serif", 0, 90)); // NOI18N
        labelFact.setForeground(new java.awt.Color(255, 255, 255));
        labelFact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFact.setText("X");
        labelFact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        panelFact.add(labelFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 160, 140));

        metodoPago.add(panelFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 580, 270));

        panelCli.setBackground(new java.awt.Color(15, 15, 52));
        panelCli.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(115, 115, 168), 5, true));
        panelCli.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("CUIT/CUIL:");
        panelCli.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Cond. IVA:");
        panelCli.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        cbCondIvaMp.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cbCondIvaMp.setBorder(null);
        panelCli.add(cbCondIvaMp, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 330, 30));

        AgregarCliMP.setBackground(new java.awt.Color(146, 186, 255));
        AgregarCliMP.setText("Agregar");
        AgregarCliMP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        AgregarCliMP.addActionListener(this::AgregarCliMPActionPerformed);
        panelCli.add(AgregarCliMP, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 100, 30));

        BuscarCliMP.setBackground(new java.awt.Color(146, 186, 255));
        BuscarCliMP.setText("Buscar");
        BuscarCliMP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BuscarCliMP.addActionListener(this::BuscarCliMPActionPerformed);
        panelCli.add(BuscarCliMP, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 100, 30));

        txtDomCliente.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        panelCli.add(txtDomCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 450, -1));

        txtNombreCliente.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        panelCli.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 450, -1));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Domicilio:");
        panelCli.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        txtCuitCliente.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        panelCli.add(txtCuitCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 320, -1));

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon-mp.png"))); // NOI18N
        jLabel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        panelCli.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 160, 160));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Nombre / R.S:");
        panelCli.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        metodoPago.add(panelCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 860, 290));

        LimpiarMp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LimpiarMp.setText("Limpiar");
        LimpiarMp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        LimpiarMp.addActionListener(this::LimpiarMpActionPerformed);
        metodoPago.add(LimpiarMp, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 670, 140, 40));

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton7.setText("Continuar");
        jButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        metodoPago.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 670, 140, 40));

        cancelarMP1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cancelarMP1.setText("Cancelar");
        cancelarMP1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cancelarMP1.addActionListener(this::cancelarMP1ActionPerformed);
        metodoPago.add(cancelarMP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 670, 140, 40));

        getContentPane().add(metodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 740));

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
            modelo.addRow(new Object[]{
                c.getRazonSocial(),
                c.getTelefono(),
                c.getCuit(),
                c.getDomicilio(),
                c.getMail(),
                c.getTipoPersona()
            });
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
    }//GEN-LAST:event_btnAListasActionPerformed

    private void btnAClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAClientesActionPerformed
        cargarTabla();
        ListaPanel.setVisible(false);
        PagosPanel.setVisible(false);
        btnAListas.setBackground(new Color(20, 52, 83));
        btnAClientes.setBackground(new Color(30, 90, 142));
        btnAPagos.setBackground(new Color(20, 52, 83));

        BtnsADM.setVisible(true);
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
    private void btnAContinuar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAContinuar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAContinuar1ActionPerformed

    private void btnAContinuar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAContinuar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAContinuar3ActionPerformed

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

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        administracionPanel.setVisible(false);
        metodoPago.setVisible(false);
        ventasPanel.setVisible(true);

    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnVentas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas1ActionPerformed

    private void btnProductos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProductos1ActionPerformed

    private void btnAdministracion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracion1ActionPerformed
        administracionPanel.setVisible(true);
        metodoPago.setVisible(false);
        ventasPanel.setVisible(false);
    }//GEN-LAST:event_btnAdministracion1ActionPerformed

    private void btnABuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnABuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnABuscar1ActionPerformed

    //SECCION VENTAS
    private void btnAContinuar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAContinuar2ActionPerformed
        cargarCbCondIva();
        cargarCbMpVentas();
        metodoPago.setVisible(true);
        ventasPanel.setVisible(false);
        cbCondIvaMp.removeAllItems();
        NCondIVA nCondIVA = new NCondIVA();
        for (CondIVA c : nCondIVA.getLista()) {
            cbCondIvaMp.addItem(c);
            if (c.getIdCondIva() == 3) {
                cbCondIvaMp.setSelectedItem(c);
            }
        }
    }//GEN-LAST:event_btnAContinuar2ActionPerformed

    private void btnAModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAModificar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAModificar1ActionPerformed

    private void btnABorrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnABorrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnABorrar1ActionPerformed

    private void txtPre2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPre2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPre2ActionPerformed

    private void LimpiarMpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarMpActionPerformed
        metodoPago.setVisible(false);
        ventasPanel.setVisible(true);
    }//GEN-LAST:event_LimpiarMpActionPerformed

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
        factA.setBackground(new Color(255, 255, 255));
        factA.setForeground(new Color(0, 0, 0));
        factB.setBackground(new Color(16, 51, 89));
        factB.setForeground(new Color(255, 255, 255));
    }
    private void factAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factAActionPerformed
        seleccionarFacturaA();

    }//GEN-LAST:event_factAActionPerformed

    private void factBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factBActionPerformed
        seleccionarFacturaB();

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
        if (mp != null && mp.getDescripcion().equals("Efectivo") || mp.getDescripcion().equals("Transferencia") || mp.getDescripcion().equals("Cheque") || mp.getDescripcion().equals("Débito")) {
            txtCuotas.setText("");
            txtCuotas.setEnabled(false);
        } else {
            txtCuotas.setEnabled(true);
        }
    }//GEN-LAST:event_cbMpVentasActionPerformed

    private void AgregarCliMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarCliMPActionPerformed
        try {
            if (txtNombreCliente.getText() != "" && txtCuitCliente.getText() != "" && txtDomCliente.getText() != "") {
                String razonSocial = txtNombreCliente.getText();
                String telefono = "";
                String cuit = txtCuitCliente.getText();
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
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_AgregarCliMPActionPerformed

    private void cancelarMP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarMP1ActionPerformed
        // TODO add your handling code here:
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
            // 2. Obtener ID del Cliente seleccionado
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

            // 3. Obtener ID del Método de Pago seleccionado mapeando con tu Modelo
            String metodoSeleccionado = (cbMetodosPagos.getSelectedItem() != null)
                    ? cbMetodosPagos.getSelectedItem().toString()
                    : "Efectivo";
            int idMetodoPago = 1; // Por defecto: Efectivo

            switch (metodoSeleccionado.toLowerCase().trim()) {
                case "efectivo":
                    idMetodoPago = 1;
                    break;
                case "débito":
                case "debito":
                    idMetodoPago = 2; // Asegurate de que coincida con el ID en tu tabla de SQL Server
                    break;
                case "crédito":
                case "credito":
                    idMetodoPago = 3;
                    break;
                case "transferencia":
                    idMetodoPago = 4;
                    break;
                default:
                    idMetodoPago = 1;
                    break;
            }

            // 4. Capturar y parsear los montos numéricos calculados
            double totalCompras = Double.parseDouble(txtComprasTotales.getText().trim().replace(",", "."));
            int cuotas = Integer.parseInt(txtCuotasPagos.getText().trim());
            double interes = Double.parseDouble(txtInteres.getText().trim().replace(",", "."));
            boolean aplicarDescuento = cbDescuento.isSelected();
            double totalFinal = Double.parseDouble(txtMontoTotal.getText().trim().replace(",", "."));

            // 5. Llamar al método de tu capa de negocio NCobros con la firma exacta
            boolean exito = nCobros.procesarCobro(idCliente, idMetodoPago, totalCompras, cuotas, interes, aplicarDescuento, totalFinal);

            if (exito) {
                javax.swing.JOptionPane.showMessageDialog(this, "El pago de la cuenta corriente fue registrado con éxito en la base de datos.", "Registro Exitoso", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                // 6. Limpieza automática de la interfaz para el siguiente cobro
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

        // Buscamos el objeto correspondiente en base al String seleccionado
        if (listaClientesGlobal != null) {
            for (Modelos.Cliente c : listaClientesGlobal) {
                if (c.getRazonSocial().equals(nombreSeleccionado)) {
                    clienteSeleccionado = c;
                    break;
                }
            }
        }

        if (clienteSeleccionado != null) {
            // 1. Autocompletamos los campos del panel izquierdo
            txtCuil.setText(clienteSeleccionado.getCuit());

            // 2. Traemos la deuda acumulada real
            int idCli = clienteSeleccionado.getIdCliente();
            double deudaTotal = nCobros.obtenerDeudaCliente(idCli);

            // 3. Simulación de Fecha Vieja (Para probar el cálculo de intereses de 3 meses)
            // Esto asume que la venta original ocurrió el 15 de Enero de 2026 de manera simulada
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(2026, java.util.Calendar.JANUARY, 15);
            this.fechaVentaOriginal = cal.getTime();

            // 4. Rellenamos montos de interfaz sin usar variables inexistentes
            txtComprasTotales.setText(String.format(java.util.Locale.US, "%.2f", deudaTotal));
            txtSubtotal.setText(String.format(java.util.Locale.US, "%.2f", deudaTotal));
            txtCuotasPagos.setText("1"); // Predeterminado

            javax.swing.JOptionPane.showMessageDialog(this, "Datos cargados para: " + nombreSeleccionado + "\nDeuda actual: $" + deudaTotal);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron los datos del cliente.");
        }
    }//GEN-LAST:event_btnABuscar3ActionPerformed

    private void btCalcularCuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCalcularCuotasActionPerformed
        calcularTotalesYCuotas();
    }//GEN-LAST:event_btCalcularCuotasActionPerformed

    private void calcularTotalesYCuotas() {
        try {
            // 1. Validar subtotal base
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

            double montoConInteres = subtotal + interesCalculado;

            // 3. Cálculo de cuotas
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
            double valorCuota = montoConInteres / cantidadCuotas;
            txtUnidadCuota.setText(String.format(java.util.Locale.US, "%.2f", valorCuota));

            // 4. Validación del Descuento (Solo Efectivo y 15%)
            double montoFinal = montoConInteres;
            if (cbDescuento.isSelected()) {
                String metodoSeleccionado = (cbMetodosPagos.getSelectedItem() != null)
                        ? cbMetodosPagos.getSelectedItem().toString()
                        : "";

                if (metodoSeleccionado.equalsIgnoreCase("Efectivo")) {
                    double porcentajeDescuento = 0.15;
                    montoFinal = montoConInteres * (1 - porcentajeDescuento);
                } else {
                    cbDescuento.setSelected(false);
                    JOptionPane.showMessageDialog(this,
                            "El descuento del 15% solo puede aplicarse para pagos en Efectivo.",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
            }

            // 5. Mostrar el monto total definitivo abajo
            txtMontoTotal.setText(String.format(java.util.Locale.US, "%.2f", montoFinal));

        } catch (NumberFormatException ex) {
            txtMontoTotal.setText("0.00");
        }
    }
    private void cbMetodosPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMetodosPagosActionPerformed
        if (cbNombre.getSelectedItem() != null && listaClientesGlobal != null) {
            String nombreSeleccionado = cbNombre.getSelectedItem().toString();

            // Buscamos el objeto cliente que coincida con el nombre del combo
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
                double deudaTotal = nCobros.obtenerDeudaCliente(idCli);

                txtComprasTotales.setText(String.valueOf(deudaTotal));
                txtSubtotal.setText(String.valueOf(deudaTotal));
            }
        }
        // Si cambia el método de pago, validamos de inmediato que el descuento siga siendo legal
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
        // Validamos que el evento sea de SELECCIÓN
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {

            // Ignoramos la opción por defecto
            if (cbNombre.getSelectedIndex() == 0) {
                return;
            }

            // Limpiamos espacios alrededor del nombre seleccionado
            String nombreSeleccionado = cbNombre.getSelectedItem().toString().trim();
            boolean esCuentaCorriente = false;

            
            switch (nombreSeleccionado) {
                case "Maria Gomez":
                case "Distribuidora Centro SRL":
                case "Ferreteria Industrial SA":
                case "Comercial Norte SRL":
                case "Electro Hogar SA":
                case "Mercado Central SRL":
                case "Servicios Integrales SA":
                case "Corralon del Sur":
                case "Tecnologia Global SA":
                case "Logistica Express SRL":
                    esCuentaCorriente = true;
                    break;

                default:
                    
                    esCuentaCorriente = false;
                    break;
            }

            // 2. Si NO es Cuenta Corriente, salta el cartel jeje
            if (!esCuentaCorriente) {

                // Mostramos el cartel de advertencia
                javax.swing.JOptionPane.showMessageDialog(this,
                        "El cliente '" + nombreSeleccionado + "' no está habilitado para operar en Cuenta Corriente.",
                        "Validación de Cuenta",
                        javax.swing.JOptionPane.WARNING_MESSAGE);

                
                // Usamos invokeLater para evitar conflictos de hilos con el evento
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        cbNombre.setSelectedIndex(0);
                        // Limpiamos los campos del formulario por seguridad
                        txtCuil.setText("");
                        txtComprasTotales.setText("");
                        txtSubtotal.setText("0.00");
                        txtInteres.setText("0.00");
                        txtUnidadCuota.setText("0.00");
                        txtMontoTotal.setText("0.00");
                    }
                });
            }
        }
    }//GEN-LAST:event_cbNombreItemStateChanged

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new PLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agregar;
    private javax.swing.JButton AgregarCliMP;
    private javax.swing.JPanel BtnsADM;
    private javax.swing.JPanel BtnsVNT1;
    private javax.swing.JButton BuscarCliMP;
    private javax.swing.JButton LimpiarMp;
    private javax.swing.JPanel ListaPanel;
    private javax.swing.JPanel PagosPanel;
    private javax.swing.JPanel PagosPanelCampos;
    private javax.swing.JPanel PanelBotones;
    private javax.swing.JPanel PanelCalculo;
    private javax.swing.JPanel administracionPanel;
    private javax.swing.JPanel autEmp;
    private javax.swing.JPanel autPers;
    private javax.swing.JButton btCalcular;
    private javax.swing.JButton btCalcularCuotas;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btnABorrar;
    private javax.swing.JButton btnABorrar1;
    private javax.swing.JButton btnABuscar;
    private javax.swing.JButton btnABuscar1;
    private javax.swing.JButton btnABuscar3;
    private javax.swing.JButton btnAClientes;
    private javax.swing.JButton btnAContinuar;
    private javax.swing.JButton btnAContinuar1;
    private javax.swing.JButton btnAContinuar2;
    private javax.swing.JButton btnAContinuar3;
    private javax.swing.JButton btnAContinuar4;
    private javax.swing.JButton btnAEmpresa;
    private javax.swing.JButton btnAListas;
    private javax.swing.JButton btnAModificar;
    private javax.swing.JButton btnAModificar1;
    private javax.swing.JButton btnAPagos;
    private javax.swing.JButton btnAPersona;
    private javax.swing.JButton btnAdministracion;
    private javax.swing.JButton btnAdministracion1;
    private javax.swing.JButton btnCaja;
    private javax.swing.JButton btnCaja1;
    private javax.swing.JButton btnEstadisticas;
    private javax.swing.JButton btnEstadisticas1;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnInformes;
    private javax.swing.JButton btnInformes1;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProductos1;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnProveedores1;
    private javax.swing.JButton btnVentas;
    private javax.swing.JButton btnVentas1;
    private javax.swing.JButton cancelarMP1;
    private javax.swing.JPanel cargaPanel;
    private javax.swing.JComboBox<CondIVA> cbCondIva;
    private javax.swing.JComboBox<CondIVA> cbCondIvaMp;
    private javax.swing.JCheckBox cbDescuento;
    private javax.swing.JComboBox<String> cbMetodosPagos;
    private javax.swing.JComboBox<MetodoPago> cbMpVentas;
    private javax.swing.JComboBox<String> cbNombre;
    private javax.swing.JComboBox<String> cbProductosV;
    private javax.swing.JComboBox<String> cbTipo;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JButton factA;
    private javax.swing.JButton factB;
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
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelFact;
    private javax.swing.JLabel lblFact;
    private javax.swing.JLabel lblimg;
    private javax.swing.JLabel lblimg1;
    private javax.swing.JPanel listaPanel;
    private javax.swing.JPanel metodoPago;
    private javax.swing.JPanel navbarADM;
    private javax.swing.JPanel navbarVNT;
    private javax.swing.JPanel panelCli;
    private javax.swing.JPanel panelFact;
    private javax.swing.JPanel regEmp;
    private javax.swing.JTable tablaLista;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCod;
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
    private javax.swing.JTextField txtDom;
    private javax.swing.JTextField txtDomCliente;
    private javax.swing.JTextField txtFiltroCuit;
    private javax.swing.JTextField txtFiltroRS;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtInteres;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JTextField txtNomPago;
    private javax.swing.JTextField txtNomRetiro;
    private javax.swing.JTextField txtNomRetiro1;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtPre1;
    private javax.swing.JTextField txtPre2;
    private javax.swing.JTextField txtRS;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTel;
    private javax.swing.JTextField txtUnidadCuota;
    private javax.swing.JPanel ventasPanel;
    // End of variables declaration//GEN-END:variables
}
