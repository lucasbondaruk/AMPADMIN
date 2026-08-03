package Presentacion;

import Modelos.Usuario;
import Negocio.NUsuario;
import javax.swing.JOptionPane;
import Modelos.Rol;
import java.util.Random;

public class PLogin extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PLogin.class.getName());

    public PLogin() {
        initComponents();
        setLocationRelativeTo(null);
        String[] frases = {
            "Cada pequeño paso que das hoy te acerca a grandes resultados mañana.",
            "Las oportunidades no aparecen por casualidad, se crean con esfuerzo.",
            "El éxito comienza cuando decides intentarlo una vez más.",
            "Aprender algo nuevo cada día es invertir en tu mejor versión.",
            "La constancia vence al talento cuando el talento no es constante.",
            "No tengas miedo de empezar desde cero; es una nueva oportunidad para construir algo mejor.",
            "Los grandes cambios nacen de pequeñas decisiones tomadas con valentía.",
            "Cada desafío es una oportunidad para descubrir de lo que eres capaz.",
            "La disciplina transforma los sueños en metas alcanzables.",
            "El conocimiento crece cuando se comparte y se pone en práctica.",
            "No esperes el momento perfecto; convierte este momento en el perfecto para comenzar.",
            "El progreso es más importante que la perfección.",
            "Todo logro empieza con la decisión de no rendirse.",
            "Las mejores historias comienzan con alguien que se animó a dar el primer paso.",
            "Hoy es un buen día para avanzar, aprender y superarte a ti mismo."
        };

        Random random = new Random();
        String frase = frases[random.nextInt(frases.length)];

        txtFrase.setText(
                "<html><div style='width:250px; text-align:center;'>"
                + frase
                + "</div></html>"
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoginPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        FormPanel = new javax.swing.JPanel();
        btnIngresar = new javax.swing.JButton();
        txtContrasenia = new javax.swing.JPasswordField();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFrase = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(560, 372));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LoginPanel.setBackground(new java.awt.Color(255, 255, 255));
        LoginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo3d.png"))); // NOI18N
        jLabel6.setFocusable(false);
        jLabel6.setOpaque(true);
        LoginPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 550, 490));

        FormPanel.setBackground(new java.awt.Color(2, 53, 73));
        FormPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnIngresar.setBackground(new java.awt.Color(217, 235, 251));
        btnIngresar.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(13, 49, 82));
        btnIngresar.setText("Ingresar");
        btnIngresar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnIngresar.addActionListener(this::btnIngresarActionPerformed);
        FormPanel.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, 140, 40));

        txtContrasenia.setBackground(new java.awt.Color(255, 255, 255));
        txtContrasenia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtContrasenia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        FormPanel.add(txtContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 223, 40));

        txtUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(0, 0, 0));
        txtUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        FormPanel.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 223, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(11, 132, 172));
        jLabel3.setText("INICIAR SESIÓN");
        FormPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 110, 26));

        jLabel5.setFont(new java.awt.Font("Sylfaen", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("¡ Bienvenido !");
        FormPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Contraseña");
        FormPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuario");
        FormPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, -1, -1));

        LoginPanel.add(FormPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 430, 740));

        txtFrase.setFont(new java.awt.Font("Garamond", 0, 27)); // NOI18N
        txtFrase.setForeground(new java.awt.Color(0, 0, 0));
        txtFrase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFrase.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LoginPanel.add(txtFrase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 520, 220));

        getContentPane().add(LoginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        try {
            String usuario = txtUsuario.getText().trim();
            String contrasenia = new String(txtContrasenia.getPassword()).trim();

            NUsuario negocio = new NUsuario();
            Usuario u = negocio.login(usuario, contrasenia);

            JOptionPane.showMessageDialog(
                    this,
                    "Bienvenido " + u.getUsuario()
            );

            PPanelAMP ventana = new PPanelAMP(u);
            ventana.setVisible(true);
            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new PLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FormPanel;
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JLabel txtFrase;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
