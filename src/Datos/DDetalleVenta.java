
package Datos;
import Modelos.DetalleVenta;
import java.util.List;
import java.util.ArrayList;
import Conexiones.SqlConection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
        
public class DDetalleVenta {
    private SqlConection conexion = new SqlConection();

    public boolean insertar(DetalleVenta dv) throws Exception {
        String sql = "INSERT INTO DetalleVenta (idVenta, idProducto, cantidad, precioUnitario, importe, descuento) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dv.getIdVenta());
            ps.setInt(2, dv.getIdProducto());
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getPrecioUnitario());
            ps.setDouble(5, dv.getImporte());
            ps.setDouble(6, dv.getDescuento());

            return ps.executeUpdate() > 0;
        }
    }

}
