package Presentacion;

/**
 * Pantalla del Informe con IA. Es un JFrame (no un Dialog) para que tenga los botones de
 * minimizar y maximizar. El parámetro "modal" se mantiene para no romper las llamadas
 * existentes, pero un JFrame no es modal: la ventana principal sigue disponible.
 */
public class PInformeIADiseno extends javax.swing.JFrame {

    public PInformeIADiseno(java.awt.Frame parent, boolean modal) {
        initComponents();
        if (parent != null) {
            setIconImage(parent.getIconImage()); // mismo ícono que la ventana principal
        }
        setLocationRelativeTo(parent);
        ControladorInformeIA controlador = new ControladorInformeIA(new ControladorInformeIA.Componentes(
                this,
                fechaDesde, fechaHasta,
                jComboBox1, // agrupar
                jSpinner1, // pronosticar
                jCheckBox1, // analizar con IA
                btnGenerarInforme, // generar informe
                btnDescargarInforme, // exportar PDF
                jLabel1, // estado
                lblIngresos, lblIngresosVar,
                lblVentas, lblVentasVar,
                lblTicket, lblTicketVar,
                jEditorPane1, // análisis y recomendaciones
                panelPronostico, panelProductos,
                panelDias, panelTortaRubros, panelTortaPagos,
                tablaStock, txtCalidad));
                controlador.agregarVendedores(cmbVendedor, new javax.swing.JLabel(),
                panelVendedorBarras, panelVendedorTorta);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelSuperior = new javax.swing.JPanel();
        panelFiltrosInformes = new javax.swing.JPanel();
        lblDesdeIA1 = new javax.swing.JLabel();
        lblDesdeIA2 = new javax.swing.JLabel();
        lblDesdeIA3 = new javax.swing.JLabel();
        lblDesdeIA4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnGenerarInforme = new javax.swing.JButton();
        btnDescargarInforme = new javax.swing.JButton();
        fechaDesde = new com.toedter.calendar.JDateChooser();
        fechaHasta = new com.toedter.calendar.JDateChooser();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        panelTarjetas = new javax.swing.JPanel();
        panelIngresosInforme = new javax.swing.JPanel();
        lblIngresos = new javax.swing.JLabel();
        lblIngresosVar = new javax.swing.JLabel();
        panelVentasInforme = new javax.swing.JPanel();
        lblVentas = new javax.swing.JLabel();
        lblVentasVar = new javax.swing.JLabel();
        panelTicketProm = new javax.swing.JPanel();
        lblTicket = new javax.swing.JLabel();
        lblTicketVar = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        panelPronostico = new javax.swing.JPanel();
        panelProductos = new javax.swing.JPanel();
        panelRubrosPagosDias = new javax.swing.JPanel();
        panelDias = new javax.swing.JPanel();
        panelTortas = new javax.swing.JPanel();
        panelTortaRubros = new javax.swing.JPanel();
        panelTortaPagos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaStock = new javax.swing.JTable();
        panelVendedores = new javax.swing.JPanel();
        panelFiltroVendedor = new javax.swing.JPanel();
        cmbVendedor = new javax.swing.JComboBox<>();
        panelGraficosVendedor = new javax.swing.JPanel();
        panelVendedorBarras = new javax.swing.JPanel();
        panelVendedorTorta = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCalidad = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informe de ventas con IA");
        setMinimumSize(new java.awt.Dimension(1101, 690));
        setPreferredSize(new java.awt.Dimension(1101, 690));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel1.setText("estado");
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_END);

        panelSuperior.setLayout(new java.awt.BorderLayout());

        panelFiltrosInformes.setBackground(new java.awt.Color(88, 107, 126));
        panelFiltrosInformes.setPreferredSize(new java.awt.Dimension(1100, 70));
        panelFiltrosInformes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDesdeIA1.setBackground(new java.awt.Color(255, 255, 255));
        lblDesdeIA1.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        lblDesdeIA1.setForeground(new java.awt.Color(255, 255, 255));
        lblDesdeIA1.setText("Desde:");
        panelFiltrosInformes.add(lblDesdeIA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 60, -1));

        lblDesdeIA2.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        lblDesdeIA2.setForeground(new java.awt.Color(255, 255, 255));
        lblDesdeIA2.setText("Hasta:");
        panelFiltrosInformes.add(lblDesdeIA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 50, -1));

        lblDesdeIA3.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        lblDesdeIA3.setForeground(new java.awt.Color(255, 255, 255));
        lblDesdeIA3.setText("Agrupar:");
        panelFiltrosInformes.add(lblDesdeIA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 70, -1));

        lblDesdeIA4.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        lblDesdeIA4.setForeground(new java.awt.Color(255, 255, 255));
        lblDesdeIA4.setText("Periodos a pronosticar");
        panelFiltrosInformes.add(lblDesdeIA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 160, -1));

        jCheckBox1.setBackground(new java.awt.Color(88, 107, 126));
        jCheckBox1.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Analizar con IA");
        jCheckBox1.setOpaque(true);
        panelFiltrosInformes.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, -1, 30));

        btnGenerarInforme.setBackground(new java.awt.Color(67, 107, 67));
        btnGenerarInforme.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        btnGenerarInforme.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarInforme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/documento.png"))); // NOI18N
        btnGenerarInforme.setText("Generar ");
        btnGenerarInforme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerarInforme.setFocusPainted(false);
        btnGenerarInforme.setFocusable(false);
        panelFiltrosInformes.add(btnGenerarInforme, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 15, 140, 40));

        btnDescargarInforme.setBackground(new java.awt.Color(234, 235, 166));
        btnDescargarInforme.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        btnDescargarInforme.setForeground(new java.awt.Color(0, 0, 0));
        btnDescargarInforme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/descargar.png"))); // NOI18N
        btnDescargarInforme.setText("Descargar");
        btnDescargarInforme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDescargarInforme.setFocusPainted(false);
        btnDescargarInforme.setFocusable(false);
        panelFiltrosInformes.add(btnDescargarInforme, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 15, 140, 40));
        panelFiltrosInformes.add(fechaDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, 30));
        panelFiltrosInformes.add(fechaHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 150, 30));

        jComboBox1.setFont(new java.awt.Font("Microsoft JhengHei", 0, 16)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.setFocusable(false);
        panelFiltrosInformes.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 150, -1));

        jSpinner1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSpinner1.setFocusable(false);
        panelFiltrosInformes.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 60, 30));

        panelSuperior.add(panelFiltrosInformes, java.awt.BorderLayout.NORTH);

        panelTarjetas.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 10, 6, 10));
        panelTarjetas.setOpaque(false);
        panelTarjetas.setLayout(new java.awt.GridLayout(1, 3, 20, 0));

        panelIngresosInforme.setBackground(new java.awt.Color(255, 255, 255));
        panelIngresosInforme.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ingresos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft JhengHei", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        panelIngresosInforme.setLayout(new java.awt.GridLayout(2, 1));

        lblIngresos.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblIngresos.setForeground(new java.awt.Color(113, 128, 150));
        lblIngresos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelIngresosInforme.add(lblIngresos);

        lblIngresosVar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelIngresosInforme.add(lblIngresosVar);

        panelTarjetas.add(panelIngresosInforme);

        panelVentasInforme.setBackground(new java.awt.Color(255, 255, 255));
        panelVentasInforme.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ventas Totales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft JhengHei", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        panelVentasInforme.setLayout(new java.awt.GridLayout(2, 1));

        lblVentas.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblVentas.setForeground(new java.awt.Color(113, 128, 150));
        lblVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelVentasInforme.add(lblVentas);

        lblVentasVar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelVentasInforme.add(lblVentasVar);

        panelTarjetas.add(panelVentasInforme);

        panelTicketProm.setBackground(new java.awt.Color(255, 255, 255));
        panelTicketProm.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ticket Promedio", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft JhengHei", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        panelTicketProm.setLayout(new java.awt.GridLayout(2, 1));

        lblTicket.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTicket.setForeground(new java.awt.Color(113, 128, 150));
        lblTicket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelTicketProm.add(lblTicket);

        lblTicketVar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelTicketProm.add(lblTicketVar);

        panelTarjetas.add(panelTicketProm);

        panelSuperior.add(panelTarjetas, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelSuperior, java.awt.BorderLayout.NORTH);

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jScrollPane1.setViewportView(jEditorPane1);

        jTabbedPane1.addTab("Análisis y Recomendaciones", jScrollPane1);
        jTabbedPane1.addTab("Ventas y Pronóstico", panelPronostico);
        jTabbedPane1.addTab("Productos", panelProductos);

        panelRubrosPagosDias.setLayout(new java.awt.BorderLayout());

        panelDias.setPreferredSize(new java.awt.Dimension(100, 220));
        panelRubrosPagosDias.add(panelDias, java.awt.BorderLayout.NORTH);

        panelTortas.setLayout(new java.awt.GridLayout(1, 2));
        panelTortas.add(panelTortaRubros);
        panelTortas.add(panelTortaPagos);

        panelRubrosPagosDias.add(panelTortas, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Rubros, pagos y días", panelRubrosPagosDias);

        tablaStock.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaStock);

        jTabbedPane1.addTab("Stock Crítico", jScrollPane2);

        panelVendedores.setLayout(new java.awt.BorderLayout());

        panelFiltroVendedor.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        cmbVendedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelFiltroVendedor.add(cmbVendedor);

        panelVendedores.add(panelFiltroVendedor, java.awt.BorderLayout.NORTH);

        panelGraficosVendedor.setLayout(new java.awt.GridLayout(1, 2));

        panelVendedorBarras.setPreferredSize(new java.awt.Dimension(100, 250));
        panelGraficosVendedor.add(panelVendedorBarras);
        panelGraficosVendedor.add(panelVendedorTorta);

        panelVendedores.add(panelGraficosVendedor, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Vendedores", panelVendedores);

        jScrollPane3.setViewportView(txtCalidad);

        jTabbedPane1.addTab("Calidad de datos y método", jScrollPane3);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PInformeIADiseno dialog = new PInformeIADiseno(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescargarInforme;
    private javax.swing.JButton btnGenerarInforme;
    private javax.swing.JComboBox<String> cmbVendedor;
    private com.toedter.calendar.JDateChooser fechaDesde;
    private com.toedter.calendar.JDateChooser fechaHasta;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDesdeIA1;
    private javax.swing.JLabel lblDesdeIA2;
    private javax.swing.JLabel lblDesdeIA3;
    private javax.swing.JLabel lblDesdeIA4;
    private javax.swing.JLabel lblIngresos;
    private javax.swing.JLabel lblIngresosVar;
    private javax.swing.JLabel lblTicket;
    private javax.swing.JLabel lblTicketVar;
    private javax.swing.JLabel lblVentas;
    private javax.swing.JLabel lblVentasVar;
    private javax.swing.JPanel panelDias;
    private javax.swing.JPanel panelFiltroVendedor;
    private javax.swing.JPanel panelFiltrosInformes;
    private javax.swing.JPanel panelGraficosVendedor;
    private javax.swing.JPanel panelIngresosInforme;
    private javax.swing.JPanel panelProductos;
    private javax.swing.JPanel panelPronostico;
    private javax.swing.JPanel panelRubrosPagosDias;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JPanel panelTarjetas;
    private javax.swing.JPanel panelTicketProm;
    private javax.swing.JPanel panelTortaPagos;
    private javax.swing.JPanel panelTortaRubros;
    private javax.swing.JPanel panelTortas;
    private javax.swing.JPanel panelVendedorBarras;
    private javax.swing.JPanel panelVendedorTorta;
    private javax.swing.JPanel panelVendedores;
    private javax.swing.JPanel panelVentasInforme;
    private javax.swing.JTable tablaStock;
    private javax.swing.JEditorPane txtCalidad;
    // End of variables declaration//GEN-END:variables
}
