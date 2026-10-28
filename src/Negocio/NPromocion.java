/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Datos.DMetodoPago;
import Datos.DPromocion;
import Datos.DPromocionCombo;
import Modelos.MetodoPago;
import Modelos.Promocion;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import Modelos.PromocionCombo;
import Modelos.PromocionComboDetalle;
import java.util.ArrayList;

/**
 *
 * @author Tomas Alvarez
 */
public class NPromocion {

    private DPromocion dPromocion = new DPromocion();
    private final DMetodoPago dMetodoPago = new DMetodoPago();

    /**
     * Recupera todos los medios de pago activos para el ComboBox dinámico
     */
    public List<MetodoPago> listarMediosPagoActivos() {
        return dMetodoPago.Listar();
    }

    /**
     * Busca un objeto MetodoPago por su descripción o nombre
     */
    public MetodoPago obtenerMedioPagoPorNombre(String nombre) {
        List<MetodoPago> lista = dMetodoPago.Listar();
        for (MetodoPago mp : lista) {
            if (mp.getDescripcion().equalsIgnoreCase(nombre)) {
                return mp;
            }
        }
        return null;
    }

    public DefaultComboBoxModel<MetodoPago> cargarComboMediosPago() {
        DefaultComboBoxModel<MetodoPago> model = new DefaultComboBoxModel<>();
        List<MetodoPago> lista = dPromocion.listarMetodosPago(); // Método DAO
        for (MetodoPago mp : lista) {
            model.addElement(mp);
        }
        return model;
    }

    public boolean guardarPromocionMedioPago(String nombre, MetodoPago medioPago, double porcentaje, boolean activo) {
        if (nombre == null || nombre.trim().isEmpty() || medioPago == null) {
            return false;
        }

        Promocion promo = new Promocion();
        promo.setNombre(nombre.trim());
        promo.setTipoRegla("Por Medio de Pago");
        promo.setMetodoPago(medioPago);
        promo.setDescuentoPorcentaje(porcentaje);
        promo.setCondicion(medioPago.getDescripcion() + " · " + String.format("%.0f%% de descuento", porcentaje));
        promo.setEstado(activo);

        return dPromocion.insertar(promo);
    }

    public boolean guardarPromocion(Promocion promo) {
        if (promo == null) {
            return false;
        }

        if (promo.getIdPromocion() > 0) {
            return dPromocion.modificar(promo);
        } else {
            return dPromocion.insertar(promo);
        }
    }

    public boolean cambiarEstadoPromocion(int idPromocion, boolean nuevoEstado) {
        return dPromocion.cambiarEstado(idPromocion, nuevoEstado);
    }

    public boolean eliminarPromocion(int idPromocion) {
        return dPromocion.eliminar(idPromocion);
    }

    public DefaultTableModel cargarTablaPromociones() {
        String[] columnas = {"ID", "Nombre Promoción", "Tipo Regla", "Detalle / Condición", "Estado"};
        DefaultTableModel model = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Promocion> lista = dPromocion.listar();

        for (Promocion p : lista) {
            Object[] fila = new Object[5];
            fila[0] = p.getIdPromocion();
            fila[1] = p.getNombre();
            fila[2] = p.getTipoRegla() != null ? p.getTipoRegla() : "General";
            fila[3] = p.getCondicion() != null ? p.getCondicion() : "-";
            fila[4] = p.isEstado() ? "Activa" : "Inactiva";

            model.addRow(fila);
        }

        return model;
    }

    public boolean guardarPromocionCombo(Promocion promo, List<PromocionComboDetalle> detalles, double precioFinal) {
        if (promo == null || promo.getNombre() == null || promo.getNombre().trim().isEmpty()) {
            return false;
        }
        if (detalles == null || detalles.isEmpty()) {
            return false;
        }
        if (promo.getDescuentoPorcentaje() <= 0 || promo.getDescuentoPorcentaje() > 100) {
            return false;
        }
        if (precioFinal <= 0) {
            return false;
        }

        String nombre = promo.getNombre().trim();
        PromocionCombo combo = new PromocionCombo();
        combo.setNombreCombo(nombre.length() > 100 ? nombre.substring(0, 100) : nombre); // columna varchar(100)
        combo.setPrecioFinal(precioFinal);
        combo.setDetalles(detalles);

        return dPromocion.insertarComboCompleto(promo, combo);
    }

    public Promocion buscarPorId(int idPromocion) {
        for (Promocion p : dPromocion.listar()) {
            if (p.getIdPromocion() == idPromocion) {
                return p;
            }
        }
        return null;
    }

    public PromocionCombo obtenerCombo(int idPromocion) {
        return new DPromocionCombo().buscarPorPromocion(idPromocion);
    }

    public String verificarColisionCombo(List<Integer> idsProductos, int idPromocionExcluir) {
        return dPromocion.buscarColisionProductos(idsProductos, idPromocionExcluir);
    }

    /**
     * null = activada. Si no, devuelve el motivo para mostrarlo en un
     * JOptionPane.
     */
    public String activarPromocion(int idPromocion) {
        Promocion p = buscarPorId(idPromocion);
        if (p == null) {
            return "No se encontró la promoción seleccionada.";
        }
        if (p.isEstado()) {
            return "La promoción ya está activa.";
        }
        if ("Combo / Pack de Productos".equals(p.getTipoRegla())) {
            PromocionCombo c = obtenerCombo(idPromocion);
            if (c == null || c.getDetalles().isEmpty()) {
                return "El combo no tiene productos cargados.";
            }
            List<Integer> ids = new ArrayList<>();
            for (PromocionComboDetalle d : c.getDetalles()) {
                ids.add(d.getProducto().getIdProducto());
            }
            String choque = dPromocion.buscarColisionProductos(ids, idPromocion);
            if (choque != null) {
                return choque;
            }
        }
        return dPromocion.cambiarEstado(idPromocion, true) ? null : "No se pudo actualizar el estado en la base de datos.";
    }

    public boolean modificarPromocionCombo(Promocion promo, List<PromocionComboDetalle> detalles, double precioFinal) {
        if (promo == null || promo.getIdPromocion() <= 0 || promo.getNombre() == null
                || promo.getNombre().trim().isEmpty() || detalles == null || detalles.isEmpty()
                || promo.getDescuentoPorcentaje() <= 0 || promo.getDescuentoPorcentaje() > 100 || precioFinal <= 0) {
            return false;
        }
        String nombre = promo.getNombre().trim();
        PromocionCombo combo = new PromocionCombo();
        combo.setIdPromocion(promo.getIdPromocion());
        combo.setNombreCombo(nombre.length() > 100 ? nombre.substring(0, 100) : nombre);
        combo.setPrecioFinal(precioFinal);
        combo.setDetalles(detalles);
        return dPromocion.modificarComboCompleto(promo, combo);
    }
}
