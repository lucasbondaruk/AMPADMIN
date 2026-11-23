package Utilidades;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Hace responsive una pantalla diseñada con AbsoluteLayout en NetBeans, sin tocar el .form.
 *
 * Cada posición y tamaño que se diseñó en el diseñador (x, y, ancho, alto) se escala en
 * proporción al tamaño real del panel. Si la ventana es un 40 % más ancha que la diseñada,
 * todo se ubica y se estira un 40 % más a lo ancho; lo mismo en alto.
 *
 * Uso, al final del constructor del JFrame (después de initComponents()):
 * <pre>
 * LayoutProporcional.aplicar(getContentPane());
 * </pre>
 * Recorre todos los paneles anidados y reemplaza cada AbsoluteLayout por esta versión
 * proporcional. Los paneles con otros layouts (BorderLayout, GroupLayout...) no se tocan.
 */
public final class LayoutProporcional implements LayoutManager2 {

    /** x, y, ancho, alto tal como se diseñaron (-1 = tamaño preferido). */
    private final Map<Component, int[]> diseno = new HashMap<>();
    private final Dimension tamanoDiseno;

    private LayoutProporcional(Dimension tamanoDiseno, Map<Component, int[]> restricciones) {
        this.tamanoDiseno = tamanoDiseno;
        this.diseno.putAll(restricciones);
    }

    // ------------------------------------------------------------------ aplicar

    /** Convierte en proporcionales todos los AbsoluteLayout de este contenedor y sus hijos. */
    public static void aplicar(Container raiz) {
        aplicar(raiz, -1, -1);
    }

    private static void aplicar(Container contenedor, int anchoDiseno, int altoDiseno) {
        LayoutManager layout = contenedor.getLayout();
        Map<Component, int[]> restricciones = null;
        if (layout != null && layout.getClass().getName().equals("org.netbeans.lib.awtextra.AbsoluteLayout")) {
            restricciones = leerRestricciones(layout);
        }
        if (restricciones != null) {
            // Tamaño de diseño: el que le dio su panel padre en el diseñador o, si no tenía,
            // el espacio que ocupan sus componentes (igual que calcula AbsoluteLayout).
            int ancho = anchoDiseno;
            int alto = altoDiseno;
            if (ancho <= 0 || alto <= 0) {
                int maxX = 0;
                int maxY = 0;
                for (Map.Entry<Component, int[]> e : restricciones.entrySet()) {
                    Dimension pref = e.getKey().getPreferredSize();
                    int[] r = e.getValue();
                    maxX = Math.max(maxX, r[0] + (r[2] < 0 ? pref.width : r[2]));
                    maxY = Math.max(maxY, r[1] + (r[3] < 0 ? pref.height : r[3]));
                }
                ancho = ancho > 0 ? ancho : maxX;
                alto = alto > 0 ? alto : maxY;
            }
            contenedor.setLayout(new LayoutProporcional(new Dimension(Math.max(1, ancho), Math.max(1, alto)),
                    restricciones));
        }
        for (Component hijo : contenedor.getComponents()) {
            if (hijo instanceof Container c) {
                int[] r = restricciones == null ? null : restricciones.get(hijo);
                aplicar(c, r == null ? -1 : r[2], r == null ? -1 : r[3]);
            }
        }
    }

    /** Lee las AbsoluteConstraints de un AbsoluteLayout de NetBeans (campo protegido "constraints"). */
    private static Map<Component, int[]> leerRestricciones(LayoutManager absoluteLayout) {
        try {
            Map<?, ?> tabla = null;
            for (Class<?> k = absoluteLayout.getClass(); k != null && tabla == null; k = k.getSuperclass()) {
                for (Field f : k.getDeclaredFields()) {
                    if (Map.class.isAssignableFrom(f.getType())) {
                        f.setAccessible(true);
                        tabla = (Map<?, ?>) f.get(absoluteLayout);
                        break;
                    }
                }
            }
            if (tabla == null) {
                return null;
            }
            Map<Component, int[]> resultado = new HashMap<>();
            for (Map.Entry<?, ?> e : tabla.entrySet()) {
                Object ac = e.getValue();
                resultado.put((Component) e.getKey(), new int[]{
                    entero(ac, "x"), entero(ac, "y"), entero(ac, "width"), entero(ac, "height")});
            }
            return resultado;
        } catch (ReflectiveOperationException | RuntimeException ex) {
            System.err.println("LayoutProporcional: no se pudo convertir un panel, queda fijo (" + ex + ")");
            return null;
        }
    }

    private static int entero(Object restriccion, String campo) throws ReflectiveOperationException {
        try {
            Field f = restriccion.getClass().getField(campo);
            return f.getInt(restriccion);
        } catch (NoSuchFieldException e) {
            String getter = "get" + Character.toUpperCase(campo.charAt(0)) + campo.substring(1);
            return (Integer) restriccion.getClass().getMethod(getter).invoke(restriccion);
        }
    }

    // ------------------------------------------------------------------ LayoutManager2

    @Override
    public void addLayoutComponent(Component comp, Object restriccion) {
        if (restriccion == null) {
            return;
        }
        if (restriccion instanceof int[] r && r.length == 4) {
            diseno.put(comp, r.clone());
            return;
        }
        // Componentes agregados por código con new AbsoluteConstraints(x, y, ancho, alto)
        try {
            diseno.put(comp, new int[]{entero(restriccion, "x"), entero(restriccion, "y"),
                entero(restriccion, "width"), entero(restriccion, "height")});
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Restricción no soportada: " + restriccion, e);
        }
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        // sin restricción: no se ubica (igual que AbsoluteLayout)
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        diseno.remove(comp);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(tamanoDiseno);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(tamanoDiseno);
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0.5f;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0.5f;
    }

    @Override
    public void invalidateLayout(Container target) {
    }

    @Override
    public void layoutContainer(Container parent) {
        // Nunca se achica por debajo de lo diseñado: así nada se encima ni se corta.
        double sx = Math.max(1.0, parent.getWidth() / (double) tamanoDiseno.width);
        double sy = Math.max(1.0, parent.getHeight() / (double) tamanoDiseno.height);
        for (Component c : parent.getComponents()) {
            int[] r = diseno.get(c);
            if (r == null) {
                continue;
            }
            Dimension pref = c.getPreferredSize();
            int x = (int) Math.round(r[0] * sx);
            int y = (int) Math.round(r[1] * sy);
            // Si en el diseño se usó el tamaño preferido (-1): los paneles se escalan,
            // y labels, íconos y botones mantienen su tamaño natural.
            boolean escalar = c instanceof javax.swing.JPanel || c instanceof javax.swing.JScrollPane;
            int ancho = r[2] >= 0 ? (int) Math.round(r[2] * sx) : (escalar ? (int) Math.round(pref.width * sx) : pref.width);
            int alto = r[3] >= 0 ? (int) Math.round(r[3] * sy) : (escalar ? (int) Math.round(pref.height * sy) : pref.height);
            c.setBounds(x, y, ancho, alto);
        }
    }
}
