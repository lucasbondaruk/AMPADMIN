
package Datos;
import java.util.List;
import java.util.ArrayList;
import Modelos.CondIVA;
import Conexiones.SqlConection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DCondIVA {
    
    private SqlConection conexion = new SqlConection();
    
    public List<CondIVA> Listar(){
         List<CondIVA> lista = new ArrayList<>();

        String sql = "SELECT idCondIva ,descripcion FROM CondIva";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CondIVA c = new CondIVA();
                c.setIdCondIva(rs.getInt("idCondIva"));
                c.setDescripcion(rs.getString("descripcion"));
                
                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    } 
    
}
