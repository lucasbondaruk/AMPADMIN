/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Modelos.Producto;
import java.util.List;
import java.util.ArrayList;
import Conexiones.SqlConection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DProducto {

    private SqlConection conexion = new SqlConection();

    public Producto buscarPorCodigo(String codigo) throws Exception {

        String sql = "SELECT * FROM Producto WHERE codigo = ?";
        try (Connection conn = conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setCodigo(rs.getInt("codigo"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioUnitario(rs.getFloat("precioUnitario"));
                p.setIdRubroProducto(rs.getInt("idRubroProducto"));
                p.setIdIVA(rs.getInt("idIVA"));
                p.setIdStockProducto(rs.getInt("idStockProducto"));
                p.setIdTipoProducto(rs.getInt("idTipoProducto"));
                p.setIdProveedor(rs.getInt("idProveedor"));
                return p;
            }
            return null; // no encontrado
        }
    }

    public Producto buscarPorId(int idProducto) throws Exception {

        String sql = "SELECT * FROM Producto WHERE idProducto = ?";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Producto p = new Producto();

                    p.setIdProducto(rs.getInt("idProducto"));
                    p.setCodigo(rs.getInt("codigo"));
                    p.setDescripcion(rs.getString("descripcion"));
                    p.setPrecioUnitario(rs.getFloat("precioUnitario"));
                    p.setIdRubroProducto(rs.getInt("idRubroProducto"));
                    p.setIdIVA(rs.getInt("idIVA"));
                    p.setIdStockProducto(rs.getInt("idStockProducto"));
                    p.setIdTipoProducto(rs.getInt("idTipoProducto"));
                    p.setIdProveedor(rs.getInt("idProveedor"));

                    return p;
                }

                return null;
            }

        } catch (SQLException e) {
            throw new Exception("Error al buscar el producto");
        }
    }

}
