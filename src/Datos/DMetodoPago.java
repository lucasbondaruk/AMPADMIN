/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;
import Conexiones.SqlConection;
import Modelos.MetodoPago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gimbo
 */
public class DMetodoPago {
    
    private SqlConection conexion = new SqlConection();
    
    public List<MetodoPago> Listar(){
        List<MetodoPago> lista = new ArrayList<>();

        String sql = "SELECT idMetodoPago ,descripcion, activo FROM MetodoPago ";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MetodoPago mp = new MetodoPago();
                mp.setIdMetodoPago(rs.getInt("idMetodoPago"));
                mp.setDescripcion(rs.getString("descripcion"));
                mp.setActivo(rs.getBoolean("activo"));
                lista.add(mp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public List<MetodoPago>ListarActivos(){
        List<MetodoPago> lista = new ArrayList<>();
        String sql = "SELECT idMetodoPago ,descripcion FROM MetodoPago WHERE activo = 1 ";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MetodoPago mp = new MetodoPago();
                mp.setIdMetodoPago(rs.getInt("idMetodoPago"));
                mp.setDescripcion(rs.getString("descripcion"));
                lista.add(mp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}
