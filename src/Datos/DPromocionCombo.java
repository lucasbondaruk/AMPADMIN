/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexiones.SqlConection;
import Conexiones.SqlConection;
import Modelos.MetodoPago;
import Modelos.Producto;
import Modelos.Promocion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Modelos.PromocionCombo;
import Modelos.PromocionComboDetalle;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tomas Alvarez
 */
public class DPromocionCombo {

    private SqlConection conexion = new SqlConection();

    public int insertar(Connection cn, PromocionCombo c) throws SQLException {
        String sql = "INSERT INTO PromocionCombo (idPromocion, nombreCombo, precioFinal) VALUES (?, ?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, c.getIdPromocion());
            ps.setString(2, c.getNombreCombo());
            ps.setDouble(3, c.getPrecioFinal());
            if (ps.executeUpdate() == 0) {
                throw new SQLException("No se insertó el combo.");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("No se obtuvo el ID del combo.");
    }

    public void insertarDetalles(Connection cn, int idCombo, List<PromocionComboDetalle> detalles) throws SQLException {
        String sql = "INSERT INTO PromocionComboDetalle (idCombo, idProducto, cantidad) VALUES (?, ?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            for (PromocionComboDetalle d : detalles) {
                ps.setInt(1, idCombo);
                ps.setInt(2, d.getProducto().getIdProducto());
                ps.setInt(3, d.getCantidad());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    /**
     * Combo + detalles de una promoción. El Producto trae solo su ID (se
     * completa en Presentación).
     */
    public PromocionCombo buscarPorPromocion(int idPromocion) {
        String sqlCombo = "SELECT idCombo, idPromocion, nombreCombo, precioFinal FROM PromocionCombo WHERE idPromocion = ?";
        String sqlDet = "SELECT idProducto, cantidad FROM PromocionComboDetalle WHERE idCombo = ?";
        try (Connection cn = conexion.getConexion()) {
            PromocionCombo c = null;
            try (PreparedStatement ps = cn.prepareStatement(sqlCombo)) {
                ps.setInt(1, idPromocion);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        c = new PromocionCombo(rs.getInt("idCombo"), rs.getInt("idPromocion"),
                                rs.getString("nombreCombo"), rs.getDouble("precioFinal"));
                    }
                }
            }
            if (c == null) {
                return null;
            }
            try (PreparedStatement ps = cn.prepareStatement(sqlDet)) {
                ps.setInt(1, c.getIdCombo());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Producto p = new Producto();
                        p.setIdProducto(rs.getInt("idProducto"));
                        PromocionComboDetalle d = new PromocionComboDetalle();
                        d.setIdCombo(c.getIdCombo());
                        d.setProducto(p);
                        d.setCantidad(rs.getInt("cantidad"));
                        c.getDetalles().add(d);
                    }
                }
            }
            return c;
        } catch (Exception e) {
            System.err.println("Error en DPromocionCombo.buscarPorPromocion: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Actualiza nombre y precio del combo de esa promoción y devuelve su
     * idCombo.
     */
    public int actualizar(Connection cn, PromocionCombo c) throws SQLException {
        int idCombo = -1;
        try (PreparedStatement ps = cn.prepareStatement("SELECT idCombo FROM PromocionCombo WHERE idPromocion = ?")) {
            ps.setInt(1, c.getIdPromocion());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idCombo = rs.getInt(1);
                }
            }
        }
        if (idCombo == -1) {
            throw new SQLException("El combo de la promoción no existe.");
        }
        try (PreparedStatement ps = cn.prepareStatement("UPDATE PromocionCombo SET nombreCombo = ?, precioFinal = ? WHERE idCombo = ?")) {
            ps.setString(1, c.getNombreCombo());
            ps.setDouble(2, c.getPrecioFinal());
            ps.setInt(3, idCombo);
            ps.executeUpdate();
        }
        return idCombo;
    }

    public void borrarDetalles(Connection cn, int idCombo) throws SQLException {
        try (PreparedStatement ps = cn.prepareStatement("DELETE FROM PromocionComboDetalle WHERE idCombo = ?")) {
            ps.setInt(1, idCombo);
            ps.executeUpdate();
        }
    }
}
