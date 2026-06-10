package Datos;

import Conexiones.SqlConection;
import Modelos.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DVenta {
    private SqlConection conexion = new SqlConection();

    public boolean Insertar(Venta venta) {
        try {
            Connection cn = conexion.getConexion();

            String sql = "INSERT INTO Venta "
                    + "(numVenta, fecha, total, cuotas, interes, tipoComprobante, "
                    + "idMetodoPago, idIVA, idCliente, idVendedor) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, venta.getNumVenta());
            ps.setDate(2, new java.sql.Date(venta.getFecha().getTime()));
            ps.setFloat(3, venta.getTotal());
            ps.setInt(4, venta.getCuotas());
            ps.setFloat(5, venta.getInteres());
            ps.setString(6, venta.getTipoComprobante());
            ps.setInt(7, venta.getIdMetodoPago());
            ps.setInt(8, venta.getIdIVA());
            ps.setInt(9, venta.getIdCliente());
            ps.setInt(10, venta.getIdVendedor());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al registrar venta: " + e.getMessage());
            return false;
        }
    }
}
