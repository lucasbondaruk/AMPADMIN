/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

/**
 *
 * @author Lucas GB
 */
public class PruebaInformeIA {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            PInformeIA d = new PInformeIA(null);
            d.setVisible(true);   // es modal: sigue cuando cerrás la ventana
            System.exit(0);
        });
    }

}
