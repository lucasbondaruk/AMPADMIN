/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Conexiones.SqlConection;
import Modelos.Oferta;
import Modelos.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tomas Alvarez
 */
public class NOferta {

    private final SqlConection conexion;

    public NOferta() {
        this.conexion = new SqlConection();
    }

    public boolean existeOfertaSolapada(int idProducto, java.util.Date inicio, java.util.Date fin) {
        String sql = "SELECT COUNT(*) FROM Oferta "
                + "WHERE IdProducto = ? AND Estado = 1 "
                + "AND (? <= FechaFin AND ? >= FechaInicio)";
        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ps.setTimestamp(2, new java.sql.Timestamp(inicio.getTime()));
            ps.setTimestamp(3, new java.sql.Timestamp(fin.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Producto> obtenerProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT idProducto, codigo, descripcion, precioUnitario FROM Producto";
        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setCodigo(rs.getInt("codigo"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioUnitario(rs.getFloat("precioUnitario"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public DefaultComboBoxModel<Producto> cargarComboProductos() {
        DefaultComboBoxModel<Producto> model = new DefaultComboBoxModel<>();
        for (Producto p : obtenerProductos()) {
            model.addElement(p);
        }
        return model;
    }

    public boolean guardarOferta(Oferta oferta) {
        String sql = "INSERT INTO Oferta (IdProducto, DescuentoPorcentaje, PrecioPromocional, FechaInicio, FechaFin, Estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, oferta.getIdProducto());
            ps.setDouble(2, oferta.getDescuentoPorcentaje());
            ps.setDouble(3, oferta.getPrecioPromocional());
            ps.setTimestamp(4, new java.sql.Timestamp(oferta.getFechaInicio().getTime()));
            ps.setTimestamp(5, new java.sql.Timestamp(oferta.getFechaFin().getTime()));
            ps.setBoolean(6, oferta.isEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public DefaultTableModel cargarTablaOfertas() {
        String[] columnas = {"ID", "Producto", "Desc. (%)", "Precio Promo", "Desde", "Hasta", "Estado"};
        DefaultTableModel model = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String sql = "SELECT o.IdOferta, p.descripcion, o.DescuentoPorcentaje, o.PrecioPromocional, "
                + "o.FechaInicio, o.FechaFin, o.Estado "
                + "FROM Oferta o INNER JOIN Producto p ON o.IdProducto = p.idProducto";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("IdOferta"),
                    rs.getString("descripcion"),
                    String.format("%.2f", rs.getDouble("DescuentoPorcentaje")),
                    String.format("$ %.2f", rs.getDouble("PrecioPromocional")),
                    rs.getDate("FechaInicio"),
                    rs.getDate("FechaFin"),
                    rs.getBoolean("Estado") ? "Vigente" : "Inactiva"
                };
                model.addRow(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
}
