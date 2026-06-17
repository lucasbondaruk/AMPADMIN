package Datos;

import Conexiones.SqlConection;
import Modelos.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public List<Venta> listarVentasCliente(int idCliente) throws Exception {

        List<Venta> lista = new ArrayList<>();

        String sql = "SELECT * FROM Venta WHERE idCliente = ?";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Venta v = new Venta();

                    v.setIdVenta(rs.getInt("idVenta"));
                    v.setNumVenta(rs.getInt("numVenta"));
                    v.setFecha(rs.getDate("fecha"));
                    v.setTotal(rs.getFloat("total"));
                    v.setCuotas(rs.getInt("cuotas"));
                    v.setInteres(rs.getFloat("interes"));
                    v.setTipoComprobante(rs.getString("tipoComprobante"));
                    v.setIdMetodoPago(rs.getInt("idMetodoPago"));
                    v.setIdIVA(rs.getInt("idIVA"));
                    v.setIdCliente(rs.getInt("idCliente"));
                    v.setIdVendedor(rs.getInt("idVendedor"));

                    lista.add(v);
                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al listar ventas del cliente");
        }

        return lista;
    }

    public int obtenerCantidadVentasPendientes(int idCliente) throws Exception {
        int cantidadPendientes = 0;
        // Cuenta la cantidad de registros de venta que no han sido pagados
        String sql = "SELECT COUNT(*) AS Cantidad FROM Venta WHERE idCliente = ? AND estado <> 'Pagada'";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cantidadPendientes = rs.getInt("Cantidad");
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al contar las ventas pendientes del cliente: " + e.getMessage());
        }

        return cantidadPendientes;
    }

    public double obtenerMontoVentasPendientes(int idCliente) throws Exception {
        double saldoPendiente = 0;
        // Suma el total de las ventas que no han sido pagadas aún
        String sql = "SELECT SUM(total) AS TotalPendiente FROM Venta WHERE idCliente = ? AND estado <> 'Pagada'";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    saldoPendiente = rs.getDouble("TotalPendiente");
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener la suma de ventas pendientes del cliente: " + e.getMessage());
        }

        return saldoPendiente;
    }
}
