/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Datos.DOferta;
import Datos.DProducto;
import Modelos.Oferta;
import Modelos.Producto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tomas Alvarez
 */
public class NOferta {

    private DOferta dOferta = new DOferta();
    private DProducto dProducto = new DProducto();

    public boolean guardarOferta(Oferta oferta) {
        if (oferta == null) {
            return false;
        }

        // Activa mientras no haya vencido (incluye las programadas a futuro)
        oferta.setEstado(!oferta.getFechaFin().before(new Date()));

        if (oferta.getIdOferta() > 0) {
            return dOferta.modificar(oferta);
        } else {
            return dOferta.insertar(oferta);
        }
    }

    public boolean eliminarOferta(int idOferta) {
        return dOferta.eliminar(idOferta);
    }

    public DefaultTableModel cargarTablaOfertas() {
        String[] columnas = {"ID", "Producto / Rubro", "Desc. (%)", "Precio Promo", "Desde", "Hasta", "Estado"};
        DefaultTableModel model = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita edición directa en las celdas de la JTable
            }
        };

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dOferta.marcarVencidas();
        List<Oferta> lista = dOferta.listar();
        Date hoy = new Date();

        for (Oferta o : lista) {
            Object[] fila = new Object[7];
            fila[0] = o.getIdOferta();

            if (o.getProducto() != null) {
                fila[1] = o.getProducto().getDescripcion();
            } else if (o.getRubroProducto() != null) {
                fila[1] = "Rubro: " + o.getRubroProducto().getDescripcion();
            } else {
                fila[1] = "General";
            }

            fila[2] = String.format("%.2f", o.getDescuentoPorcentaje());
            fila[3] = String.format("$ %.2f", o.getPrecioPromocional());
            fila[4] = o.getFechaInicio() != null ? sdf.format(o.getFechaInicio()) : "";
            fila[5] = o.getFechaFin() != null ? sdf.format(o.getFechaFin()) : "";

            // Determinar etiqueta visual de estado
            if (o.getFechaFin().before(hoy)) {
                fila[6] = "No vigente";
            } else if (!o.isEstado()) {
                fila[6] = "Inactiva";
            } else if (o.getFechaInicio().after(hoy)) {
                fila[6] = "Programada";
            } else {
                fila[6] = "Vigente";
            }

            model.addRow(fila);
        }

        return model;
    }

    public DefaultComboBoxModel<Producto> cargarComboProductos() {
        DefaultComboBoxModel<Producto> model = new DefaultComboBoxModel<>();
        List<Producto> productos = dProducto.listar();
        for (Producto p : productos) {
            model.addElement(p);
        }
        return model;
    }

    public String verificarColision(int idProducto, int idRubro, java.util.Date inicio, java.util.Date fin, int idOfertaExcluir) {
        if (inicio == null || fin == null) {
            return "Las fechas de la oferta son obligatorias.";
        }
        return dOferta.buscarColision(
                idProducto,
                idRubro,
                new java.sql.Date(inicio.getTime()),
                new java.sql.Date(fin.getTime()),
                idOfertaExcluir);
    }

    public Oferta buscarPorId(int idOferta) {
        for (Oferta o : dOferta.listar()) {
            if (o.getIdOferta() == idOferta) {
                return o;
            }
        }
        return null;
    }
}
