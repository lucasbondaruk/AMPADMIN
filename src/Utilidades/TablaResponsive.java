package Utilidades;

import java.awt.Container;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;

/**
 * Hace que una JTable ocupe todo el ancho de su JScrollPane cuando la ventana crece:
 * las columnas se estiran manteniendo las proporciones de sus anchos preferidos
 * (los que se configuran con setPreferredWidth). Si la ventana es más angosta que la
 * suma de esos anchos, vuelve a la barra de desplazamiento horizontal.
 *
 * Funciona aunque después el código vuelva a llamar a setAutoResizeMode(AUTO_RESIZE_OFF).
 */
public class TablaResponsive {

    private TablaResponsive() {
    }

    public static void aplicar(final JTable tabla) {
        final Runnable ajustar = () -> ajustar(tabla);
        tabla.addPropertyChangeListener("autoResizeMode", e -> SwingUtilities.invokeLater(ajustar));
        tabla.addPropertyChangeListener("columnModel", e -> {
            escucharColumnas(tabla, ajustar);
            SwingUtilities.invokeLater(ajustar);
        });
        tabla.addPropertyChangeListener("model", e -> SwingUtilities.invokeLater(ajustar));
        tabla.addHierarchyListener(e -> SwingUtilities.invokeLater(ajustar));
        escucharColumnas(tabla, ajustar);
        Container p = tabla.getParent();
        if (p instanceof JViewport) {
            p.addComponentListener(new java.awt.event.ComponentAdapter() {
                @Override
                public void componentResized(java.awt.event.ComponentEvent e) {
                    ajustar(tabla);
                }
            });
        }
        SwingUtilities.invokeLater(ajustar);
    }

    private static void escucharColumnas(final JTable tabla, final Runnable ajustar) {
        tabla.getColumnModel().addColumnModelListener(new TableColumnModelListener() {
            @Override
            public void columnAdded(TableColumnModelEvent e) {
                TableColumn c = tabla.getColumnModel().getColumn(e.getToIndex());
                c.addPropertyChangeListener(ev -> {
                    if ("preferredWidth".equals(ev.getPropertyName())) {
                        SwingUtilities.invokeLater(ajustar);
                    }
                });
                SwingUtilities.invokeLater(ajustar);
            }

            @Override
            public void columnRemoved(TableColumnModelEvent e) {
                SwingUtilities.invokeLater(ajustar);
            }

            @Override
            public void columnMoved(TableColumnModelEvent e) {
            }

            @Override
            public void columnMarginChanged(ChangeEvent e) {
            }

            @Override
            public void columnSelectionChanged(ListSelectionEvent e) {
            }
        });
        for (int i = 0; i < tabla.getColumnModel().getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).addPropertyChangeListener(ev -> {
                if ("preferredWidth".equals(ev.getPropertyName())) {
                    SwingUtilities.invokeLater(ajustar);
                }
            });
        }
    }

    private static void ajustar(JTable tabla) {
        Container p = tabla.getParent();
        if (!(p instanceof JViewport) || p.getWidth() <= 0) {
            return;
        }
        int total = 0;
        for (int i = 0; i < tabla.getColumnModel().getColumnCount(); i++) {
            total += tabla.getColumnModel().getColumn(i).getPreferredWidth();
        }
        int modo = p.getWidth() >= total ? JTable.AUTO_RESIZE_ALL_COLUMNS : JTable.AUTO_RESIZE_OFF;
        if (tabla.getAutoResizeMode() != modo) {
            tabla.setAutoResizeMode(modo);
        }
        tabla.revalidate();
    }
}
