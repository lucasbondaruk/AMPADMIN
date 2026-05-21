package Presentacion;

import java.util.Date;
import java.awt.Color;
import Modelos.CuentaCorriente;
import Negocio.NCuentaCorriente;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;

public class PRegistroCC extends javax.swing.JFrame {

    private CuentaCorriente actual = null;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PRegistroCC.class.getName());

    public PRegistroCC() {
        initComponents();
        setLocationRelativeTo(null);

        ListaPanel.setVisible(false);
        autPers.setVisible(false);
        autEmp.setVisible(true);
        regEmp.setVisible(true);
        Btns.setVisible(true);

        opc = false;

        cargarTabla();

        revalidate();
        repaint();
    }
    private boolean opc;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDate = new com.toedter.calendar.JDateChooser();
        txtRS = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        navbar = new javax.swing.JPanel();
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
        autPers = new javax.swing.JPanel();
        txtDNIRetiro1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtNomRetiro1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Btns = new javax.swing.JPanel();
        btnABuscar = new javax.swing.JButton();
        btnAContinuar = new javax.swing.JButton();
        btnAModificar = new javax.swing.JButton();
        btnABorrar = new javax.swing.JButton();
        ListaPanel = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        regEmp.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, -1, -1));

        txtCuit.setBackground(new java.awt.Color(226, 225, 225));
        txtCuit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCuit.setBorder(null);
        regEmp.add(txtCuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 255, 33));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mail:");
        regEmp.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, -1, -1));

        txtMail.setBackground(new java.awt.Color(226, 225, 225));
        txtMail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMail.setBorder(null);
        regEmp.add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 330, 255, 33));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Teléfono:");
        regEmp.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, -1, -1));

        txtTel.setBackground(new java.awt.Color(226, 225, 225));
        txtTel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTel.setBorder(null);
        regEmp.add(txtTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 255, 33));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Domicilio:");
        regEmp.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, -1, -1));

        txtDom.setBackground(new java.awt.Color(226, 225, 225));
        txtDom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDom.setBorder(null);
        regEmp.add(txtDom, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 255, 33));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Serif", 0, 130)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setText("AMPADMIN");
        regEmp.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, 116));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 40)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 66, 115));
        jLabel9.setText("Registro de cuentas corrientes");
        regEmp.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, 60));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Fecha de ingreso:");
        regEmp.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, -1, -1));
        regEmp.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, 250, 30));

        txtRS.setBackground(new java.awt.Color(226, 225, 225));
        txtRS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtRS.setBorder(null);
        regEmp.add(txtRS, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 255, 33));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Razón Social/Nombre:");
        regEmp.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        getContentPane().add(regEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 980, 400));

        navbar.setBackground(new java.awt.Color(225, 250, 255));
        navbar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NAVBAR.png"))); // NOI18N
        navbar.add(lblimg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

        navbar.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 50));

        btnInformes.setBackground(new java.awt.Color(204, 255, 255));
        btnInformes.setForeground(new java.awt.Color(0, 51, 102));
        btnInformes.setText("Informes");
        btnInformes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInformes.setBorderPainted(false);
        btnInformes.setContentAreaFilled(false);
        navbar.add(btnInformes, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 90, 50));

        btnCaja.setBackground(new java.awt.Color(204, 255, 255));
        btnCaja.setForeground(new java.awt.Color(0, 51, 102));
        btnCaja.setText("Caja");
        btnCaja.setBorderPainted(false);
        btnCaja.setContentAreaFilled(false);
        navbar.add(btnCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 100, 50));

        btnProveedores.setBackground(new java.awt.Color(204, 255, 255));
        btnProveedores.setForeground(new java.awt.Color(0, 51, 102));
        btnProveedores.setText("Proveedores");
        btnProveedores.setBorderPainted(false);
        btnProveedores.setContentAreaFilled(false);
        navbar.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 120, 50));

        btnAdministracion.setBackground(new java.awt.Color(20, 52, 83));
        btnAdministracion.setForeground(new java.awt.Color(255, 255, 255));
        btnAdministracion.setText("Administración");
        btnAdministracion.setBorderPainted(false);
        navbar.add(btnAdministracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 120, 50));

        btnVentas.setBackground(new java.awt.Color(204, 255, 255));
        btnVentas.setForeground(new java.awt.Color(0, 51, 102));
        btnVentas.setText("Ventas");
        btnVentas.setBorderPainted(false);
        btnVentas.setContentAreaFilled(false);
        navbar.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 90, 50));

        btnEstadisticas.setBackground(new java.awt.Color(204, 255, 255));
        btnEstadisticas.setForeground(new java.awt.Color(0, 51, 102));
        btnEstadisticas.setText("Estadísticas");
        btnEstadisticas.setBorderPainted(false);
        btnEstadisticas.setContentAreaFilled(false);
        navbar.add(btnEstadisticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 120, 50));

        btnProductos.setBackground(new java.awt.Color(204, 255, 255));
        btnProductos.setForeground(new java.awt.Color(0, 51, 102));
        btnProductos.setText("Productos");
        btnProductos.setBorderPainted(false);
        btnProductos.setContentAreaFilled(false);
        btnProductos.addActionListener(this::btnProductosActionPerformed);
        navbar.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 100, 50));

        getContentPane().add(navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, -1));

        autEmp.setBackground(new java.awt.Color(255, 255, 255));
        autEmp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(2, 43, 73));
        jLabel15.setText("Autorizados al pago de la cuenta");
        autEmp.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, 40));

        txtDNIRetiro.setBackground(new java.awt.Color(226, 225, 225));
        txtDNIRetiro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDNIRetiro.setBorder(null);
        autEmp.add(txtDNIRetiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 255, 33));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("DNI:");
        autEmp.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, -1, -1));

        txtDNIPago.setBackground(new java.awt.Color(226, 225, 225));
        txtDNIPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDNIPago.setBorder(null);
        autEmp.add(txtDNIPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 255, 33));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Nombre:");
        autEmp.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, -1, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setText("+");
        autEmp.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, -1, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(2, 43, 73));
        jLabel17.setText("Autorizados a retiro de productos");
        autEmp.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Nombre:");
        autEmp.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        txtNomRetiro.setBackground(new java.awt.Color(226, 225, 225));
        txtNomRetiro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNomRetiro.setBorder(null);
        autEmp.add(txtNomRetiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 255, 33));

        txtNomPago.setBackground(new java.awt.Color(226, 225, 225));
        txtNomPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNomPago.setBorder(null);
        autEmp.add(txtNomPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 255, 33));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("DNI:");
        autEmp.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, -1, -1));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("+");
        autEmp.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setText("Para BUSCAR a una cuenta, ingrese el CUIT/CUIL.");
        autEmp.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        getContentPane().add(autEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 980, 200));

        autPers.setBackground(new java.awt.Color(255, 255, 255));
        autPers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDNIRetiro1.setBackground(new java.awt.Color(226, 225, 225));
        txtDNIRetiro1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDNIRetiro1.setBorder(null);
        autPers.add(txtDNIRetiro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 255, 33));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(2, 43, 73));
        jLabel21.setText("Autorizados a retiro de productos");
        autPers.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, 40));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Nombre:");
        autPers.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, -1));

        txtNomRetiro1.setBackground(new java.awt.Color(226, 225, 225));
        txtNomRetiro1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNomRetiro1.setBorder(null);
        autPers.add(txtNomRetiro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 255, 33));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("DNI:");
        autPers.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton6.setText("+");
        autPers.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 204));
        jLabel6.setText("Para BUSCAR a una cuenta, ingrese el CUIT/CUIL.");
        autPers.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        getContentPane().add(autPers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 980, 200));

        Btns.setBackground(new java.awt.Color(22, 22, 22));

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

        javax.swing.GroupLayout BtnsLayout = new javax.swing.GroupLayout(Btns);
        Btns.setLayout(BtnsLayout);
        BtnsLayout.setHorizontalGroup(
            BtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnsLayout.createSequentialGroup()
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
        BtnsLayout.setVerticalGroup(
            BtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnABuscar)
                .addComponent(btnAContinuar)
                .addComponent(btnAModificar)
                .addComponent(btnABorrar))
        );

        getContentPane().add(Btns, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 980, 40));

        ListaPanel.setBackground(new java.awt.Color(255, 255, 255));
        ListaPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setBackground(new java.awt.Color(161, 161, 161));
        jLabel24.setFont(new java.awt.Font("Serif", 0, 130)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(242, 242, 242));
        jLabel24.setText("AMPADMIN");
        ListaPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, 116));

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

        getContentPane().add(ListaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 980, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        modelo.addColumn("Tipo");

        NCuentaCorriente ncc = new NCuentaCorriente();

        for (CuentaCorriente c : ncc.listar()) {
            modelo.addRow(new Object[]{
                c.getRazonSocial(),
                c.getTelefono(),
                c.getCuit(),
                c.getDomicilio(),
                c.getMail(),
                c.getTipo()
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

    private void btnAContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAContinuarActionPerformed
        try {
            if (verificarCampos()) {
                String razonSocial = txtRS.getText();
                String telefono = txtTel.getText();
                String cuit = txtCuit.getText();
                Date fecha = txtDate.getDate();
                String domicilio = txtDom.getText();
                String mail = txtMail.getText();
                String tipo;
                if (!opc) {
                    tipo = "Empresa";
                } else {
                    tipo = "Persona";
                }
                CuentaCorriente cuenta = new CuentaCorriente(
                        razonSocial,
                        telefono,
                        cuit,
                        fecha,
                        domicilio,
                        mail,
                        tipo
                );

                NCuentaCorriente nuevaCuenta = new NCuentaCorriente();
                nuevaCuenta.create(cuenta);
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

            NCuentaCorriente negocio = new NCuentaCorriente();
            CuentaCorriente c = negocio.select(cuit);

            if (c != null) {
                actual = c;
                txtRS.setText(c.getRazonSocial());
                txtTel.setText(c.getTelefono());
                txtCuit.setText(c.getCuit());
                txtDom.setText(c.getDomicilio());
                txtMail.setText(c.getMail());
                String tipo = c.getTipo();
                if (tipo.equals("Persona")) {
                    btnAPersonaActionPerformed(null);
                } else {
                    btnAEmpresaActionPerformed(null);
                }
                txtDate.setDate(c.getfchaIngreso());
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
            actual.setTipo(opc ? "Persona" : "Empresa");

            NCuentaCorriente negocio = new NCuentaCorriente();
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
        ListaPanel.setVisible(true);
        btnAListas.setBackground(new Color(30, 90, 142));
        btnAClientes.setBackground(new Color(20, 52, 83));
        Btns.setVisible(false);
        regEmp.setVisible(false);
        autEmp.setVisible(false);
        autPers.setVisible(false);
    }//GEN-LAST:event_btnAListasActionPerformed

    private void btnAClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAClientesActionPerformed
        ListaPanel.setVisible(false);

        btnAListas.setBackground(new Color(20, 52, 83));
        btnAClientes.setBackground(new Color(30, 90, 142));

        Btns.setVisible(true);
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

        NCuentaCorriente ncc = new NCuentaCorriente();
        List<CuentaCorriente> lista = ncc.listar();

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Razón Social");
        modelo.addColumn("Teléfono");
        modelo.addColumn("CUIT/CUIL");
        modelo.addColumn("Domicilio");
        modelo.addColumn("Mail");
        modelo.addColumn("Tipo");

        boolean hayResultados = false;

        for (CuentaCorriente c : lista) {

            boolean coincide = true;

            if (!rs.isEmpty() && !c.getRazonSocial().toLowerCase().contains(rs)) {
                coincide = false;
            }

            if (!cuit.isEmpty() && !c.getCuit().contains(cuit)) {
                coincide = false;
            }

            if (tipo != null && !tipo.isEmpty() && !c.getTipo().equalsIgnoreCase(tipo)) {
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
                    c.getTipo()
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
                NCuentaCorriente negocio = new NCuentaCorriente();
                negocio.delete(actual.getId());
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
        JOptionPane.showMessageDialog(this, "Módulo previsto para futuras iteraciones.");
    }//GEN-LAST:event_btnAPagosActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new PLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Btns;
    private javax.swing.JPanel ListaPanel;
    private javax.swing.JPanel autEmp;
    private javax.swing.JPanel autPers;
    private javax.swing.JButton btnABorrar;
    private javax.swing.JButton btnABuscar;
    private javax.swing.JButton btnAClientes;
    private javax.swing.JButton btnAContinuar;
    private javax.swing.JButton btnAContinuar1;
    private javax.swing.JButton btnAContinuar3;
    private javax.swing.JButton btnAEmpresa;
    private javax.swing.JButton btnAListas;
    private javax.swing.JButton btnAModificar;
    private javax.swing.JButton btnAPagos;
    private javax.swing.JButton btnAPersona;
    private javax.swing.JButton btnAdministracion;
    private javax.swing.JButton btnCaja;
    private javax.swing.JButton btnEstadisticas;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnInformes;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnVentas;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblimg;
    private javax.swing.JPanel navbar;
    private javax.swing.JPanel regEmp;
    private javax.swing.JTable tablaLista;
    private javax.swing.JTextField txtCuit;
    private javax.swing.JTextField txtDNIPago;
    private javax.swing.JTextField txtDNIRetiro;
    private javax.swing.JTextField txtDNIRetiro1;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtDom;
    private javax.swing.JTextField txtFiltroCuit;
    private javax.swing.JTextField txtFiltroRS;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtNomPago;
    private javax.swing.JTextField txtNomRetiro;
    private javax.swing.JTextField txtNomRetiro1;
    private javax.swing.JTextField txtRS;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
