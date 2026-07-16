/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexiones.SqlConection;
import Modelos.RubroProducto;
import Modelos.TipoProducto;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DTipoProducto {

    private SqlConection conexion = new SqlConection();

    public List<TipoProducto> Listar() {
        List<TipoProducto> lista = new ArrayList<>();

        String sql = "SELECT * FROM TipoProducto";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TipoProducto tp = new TipoProducto();
                tp.setIdTipoProducto(rs.getInt("idTipoProducto"));
                tp.setDescripcion(rs.getString("descripcion"));
                tp.setUnidad(rs.getString("unidad"));
                RubroProducto rp = new RubroProducto();
                rp.setIdRubroProducto(rs.getInt("idRubroProducto"));

                tp.setIdRubroProducto(rp);

                lista.add(tp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<TipoProducto> listarPorRubro(int idRubroProducto) {
        List<TipoProducto> lista = new ArrayList<>();

        String sql = """
                 SELECT tp.*, rp.descripcion AS rubro
                 FROM TipoProducto tp
                 INNER JOIN RubroProducto rp
                    ON tp.idRubroProducto = rp.idRubroProducto
                 WHERE tp.idRubroProducto = ?
                 """;

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idRubroProducto);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                TipoProducto tp = new TipoProducto();
                tp.setIdTipoProducto(rs.getInt("idTipoProducto"));
                tp.setDescripcion(rs.getString("descripcion"));
                tp.setUnidad(rs.getString("unidad"));

                RubroProducto rp = new RubroProducto();
                rp.setIdRubroProducto(rs.getInt("idRubroProducto"));
                rp.setDescripcion(rs.getString("rubro"));

                tp.setIdRubroProducto(rp);

                lista.add(tp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
