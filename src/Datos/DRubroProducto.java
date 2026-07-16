package Datos;

import Conexiones.SqlConection;
import Modelos.RubroProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DRubroProducto {

    private SqlConection conexion = new SqlConection();

    public List<RubroProducto> Listar() {
        List<RubroProducto> lista = new ArrayList<>();

        String sql = "SELECT * FROM RubroProducto";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RubroProducto rp = new RubroProducto();
                rp.setIdRubroProducto(rs.getInt("idRubroProducto"));
                rp.setDescripcion(rs.getString("descripcion"));
                lista.add(rp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
}
