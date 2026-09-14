
package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexiones.SqlConection;
import Modelos.Proveedor; 

public class DProveedor {
    private SqlConection conexion = new SqlConection();
    
    public List<Proveedor> listar(){
        List<Proveedor> lista = new ArrayList<>();
        String sql = "Select * from Proveedor";
        try(Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("idProveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setTelefono(rs.getString("telefono"));
                p.setDomicilio(rs.getString("domicilio"));
                p.setMail(rs.getString("mail"));
                
                lista.add(p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}
