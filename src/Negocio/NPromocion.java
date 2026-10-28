/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Conexiones.SqlConection;
import Modelos.Promocion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tomas Alvarez
 */
public class NPromocion {

    private final SqlConection conexion;

    public NPromocion() {
        this.conexion = new SqlConection();
    }

    public boolean guardarPromocion(Promocion promo) {
        String sql = "INSERT INTO Promocion (Nombre, TipoCondicion, Descuento, Estado) VALUES (?, ?, ?, ?)";
        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, promo.getNombre());
            ps.setString(2, promo.getTipoCondicion());
            ps.setDouble(3, promo.getDescuento());
            ps.setBoolean(4, promo.isEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Promocion> listarPromociones() {
        List<Promocion> lista = new ArrayList<>();
        String sql = "SELECT IdPromocion, Nombre, TipoCondicion, Descuento, Estado FROM Promocion";
        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Promocion p = new Promocion(
                        rs.getInt("IdPromocion"),
                        rs.getString("Nombre"),
                        rs.getString("TipoCondicion"),
                        rs.getDouble("Descuento"),
                        rs.getBoolean("Estado")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public DefaultTableModel cargarTablaPromociones() {
        String[] columnas = {"ID", "Nombre", "Tipo Condición", "Descuento (%)", "Estado"};
        DefaultTableModel model = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Promocion p : listarPromociones()) {
            Object[] fila = {
                p.getIdPromocion(),
                p.getNombre(),
                p.getTipoCondicion(),
                String.format("%.2f", p.getDescuento()),
                p.isEstado() ? "Activa" : "Inactiva"
            };
            model.addRow(fila);
        }
        return model;
    }

    public boolean cambiarEstadoPromocion(int idPromocion, boolean nuevoEstado) {
        String sql = "UPDATE Promocion SET Estado = ? WHERE IdPromocion = ?";
        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, nuevoEstado);
            ps.setInt(2, idPromocion);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
