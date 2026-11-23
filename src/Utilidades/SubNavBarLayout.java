package Utilidades;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 * Acomoda los botones de una SubNavBar centrados justo debajo del botón de la navbar
 * principal que la abre. Cada botón toma el mismo ancho que ese botón principal, así que
 * todo se escala junto con la ventana. Si no entran centrados (por ejemplo Caja, que está
 * en el borde), se corren lo justo para no salirse de la ventana.
 *
 *              Productos
 *    Listas    Gestionar   Actualizar
 */
public class SubNavBarLayout implements LayoutManager {

    private static final int SEPARACION = 4;
    private final Component botonPrincipal;

    public SubNavBarLayout(Component botonPrincipal) {
        this.botonPrincipal = botonPrincipal;
    }

    /** Aplica el layout a la SubNavBar y la mantiene alineada cuando el botón principal se mueve. */
    public static void aplicar(final Container subNavBar, Component botonPrincipal) {
        ordenarComoEnElDiseno(subNavBar);
        subNavBar.setLayout(new SubNavBarLayout(botonPrincipal));
        botonPrincipal.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                subNavBar.revalidate();
                subNavBar.repaint();
            }

            @Override
            public void componentMoved(java.awt.event.ComponentEvent e) {
                subNavBar.revalidate();
                subNavBar.repaint();
            }
        });
        subNavBar.revalidate();
    }

    /** Deja los botones en el mismo orden de izquierda a derecha que tienen en el diseñador. */
    private static void ordenarComoEnElDiseno(Container subNavBar) {
        LayoutManager original = subNavBar.getLayout();
        if (original != null) {
            java.awt.Dimension d = subNavBar.getSize();
            if (d.width <= 0 || d.height <= 0) {
                d = subNavBar.getPreferredSize();
            }
            subNavBar.setSize(Math.max(d.width, 980), Math.max(d.height, 1));
            original.layoutContainer(subNavBar);
        }
        Component[] botones = subNavBar.getComponents();
        java.util.Arrays.sort(botones, java.util.Comparator.comparingInt(Component::getX));
        for (int i = 0; i < botones.length; i++) {
            subNavBar.setComponentZOrder(botones[i], i);
        }
    }

    @Override
    public void layoutContainer(Container parent) {
        List<Component> botones = new ArrayList<>();
        for (Component c : parent.getComponents()) {
            if (c.isVisible()) {
                botones.add(c);
            }
        }
        int n = botones.size();
        if (n == 0 || botonPrincipal.getParent() == null) {
            return;
        }
        int anchoPadre = parent.getWidth();
        int alto = parent.getHeight();
        int ancho = Math.max(1, botonPrincipal.getWidth());
        int total = n * ancho + (n - 1) * SEPARACION;
        if (total > anchoPadre) { // no entran: se achican para ocupar todo el ancho
            ancho = Math.max(1, (anchoPadre - (n - 1) * SEPARACION) / n);
            total = n * ancho + (n - 1) * SEPARACION;
        }
        int centro = SwingUtilities.convertPoint(botonPrincipal.getParent(),
                botonPrincipal.getX() + botonPrincipal.getWidth() / 2, 0, parent).x;
        int x = centro - total / 2;
        x = Math.max(0, Math.min(x, anchoPadre - total));
        for (Component b : botones) {
            b.setBounds(x, 0, ancho, alto);
            x += ancho + SEPARACION;
        }
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return parent.getSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(0, 0);
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
    }
}
