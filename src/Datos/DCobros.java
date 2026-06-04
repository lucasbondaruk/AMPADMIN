/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexiones.SqlConection;
import Modelos.CobroCuentaCorriente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Tomas Alvarez
 */
public class DCobros {

    // 1. Obtener la suma automática del monto de las ventas pendientes del cliente
    public double obtenerTotalVentasPendientes(int idCliente) {
        // Cambiado COUNT(*) por SUM(total) para que realmente devuelva la suma monetaria (double)
        String sql = "SELECT ISNULL(SUM(total), 0) AS sumaTotal FROM Venta WHERE idCliente = ? AND estado = 'Pendiente'";
        Conexiones.SqlConection sqlCon = new Conexiones.SqlConection();

        try (Connection con = sqlCon.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("sumaTotal");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al calcular la suma de ventas pendientes: " + e.getMessage());
        }
        return 0;
    }

    // 2. Insertar el cobro de la cuenta corriente de forma directa con transacción
    public boolean registrarCobro(CobroCuentaCorriente cobro) {
        String sql = "BEGIN TRANSACTION; "
                + "INSERT INTO CobroCuentaCorriente (idCliente, idMetodoPago, fechaPago, totalCompras, cuotas, interes, descuento, total) "
                + "VALUES (?, ?, GETDATE(), ?, ?, ?, ?, ?); "
                + "DECLARE @IdCobro INT = SCOPE_IDENTITY(); "
                + "INSERT INTO DetalleCobroCuentaCorriente (idCobro, numVenta) "
                + "SELECT @IdCobro, numVenta FROM Venta WHERE idCliente = ? AND estado = 'Pendiente'; "
                + "UPDATE Venta SET estado = 'Pagada' WHERE idCliente = ? AND estado = 'Pendiente'; "
                + "COMMIT TRANSACTION;";

        SqlConection sqlCon = new SqlConection();

        try (Connection con = sqlCon.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Corregido según los nombres exactos de los getters de tu clase de modelo
            ps.setInt(1, cobro.getCodCli());
            ps.setInt(2, cobro.getCodMp());
            ps.setInt(3, cobro.getTotalCompras());
            ps.setInt(4, cobro.getCuotas());
            ps.setDouble(5, cobro.getInteres());
            ps.setBoolean(6, cobro.isDescuento());
            ps.setDouble(7, cobro.getTotal());

            // Parámetros para el Detalle y el Update dentro del Script SQL
            ps.setInt(8, cobro.getCodCli());
            ps.setInt(9, cobro.getCodCli());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar el cobro en la BD: " + e.getMessage());
            return false;
        }
    }
}
