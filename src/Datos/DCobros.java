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

    private final SqlConection sqlCon = new SqlConection();

    // 1. Obtener la suma automática del monto de las ventas pendientes del cliente
    public double obtenerTotalVentasPendientes(int idCliente) {
        String sql = "SELECT ISNULL(SUM(total), 0) AS sumaTotal FROM Venta WHERE idCliente = ? AND estado = 'Pendiente'";
        

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

    // 2. REGLA DE NEGOCIO: Contar cuántas ventas pendientes tiene asociadas la cuenta del cliente
    public int contarVentasPendientes(int idCliente) {
        String sql = "SELECT COUNT(*) AS cantidad FROM Venta WHERE idCliente = ? AND estado = 'Pendiente'";

        try (Connection con = sqlCon.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cantidad");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al contar las ventas pendientes: " + e.getMessage());
        }
        return 0;
    }

    // 3. Registrar el cobro y actualizar el estado de las deudas (Con Transacción Segura en Java)
    public boolean registrarCobro(CobroCuentaCorriente cobro) {
        // Nombres de columnas ajustados PERFECTAMENTE a tu captura de SQL Server
        String sqlInsertCobro = "INSERT INTO CobroCuentaCorriente (total, totalCompras, fechaPago, cuotas, interes, descuento, codMP, codCli) "
                + "VALUES (?, ?, GETDATE(), ?, ?, ?, ?, ?)";

        String sqlUpdateVentas = "UPDATE Venta SET estado = 'Pagada' WHERE idCliente = ? AND estado = 'Pendiente'";

        Connection con = null;
        PreparedStatement psInsert = null;
        PreparedStatement psUpdate = null;

        try {
            con = sqlCon.getConexion();
            // Desactivamos el auto-commit para manejar la transacción nosotros de forma segura
            con.setAutoCommit(false);

            // --- PASO 1: Insertar en CobroCuentaCorriente ---
            psInsert = con.prepareStatement(sqlInsertCobro);
            psInsert.setDouble(1, cobro.getTotal());
            psInsert.setDouble(2, cobro.getTotalCompras());
            psInsert.setInt(3, cobro.getCuotas());
            psInsert.setDouble(4, cobro.getInteres());

            // Si tu modelo maneja descuento como double (monto) o int (porcentaje) pasás el valor, 
            // si es boolean ponemos 1 o 0. Asumo double por coherencia con la BD.
            psInsert.setBoolean(5, cobro.isDescuento());

            psInsert.setInt(6, cobro.getCodMp()); // idMetodoPago
            psInsert.setInt(7, cobro.getCodCli()); // idCliente

            int filasCobro = psInsert.executeUpdate();

            // --- PASO 2: Regla de Negocio - Actualizar las ventas del cliente a 'Pagada' ---
            psUpdate = con.prepareStatement(sqlUpdateVentas);
            psUpdate.setInt(1, cobro.getCodCli());
            int filasVentasActualizadas = psUpdate.executeUpdate();

            // --- PASO 3: Validación de la transacción ---
            // Si se insertó el cobro correctamente, confirmamos los cambios en la BD
            if (filasCobro > 0) {
                con.commit();
                return true;
            } else {
                // Si algo falló, deshacemos todo para no dejar datos inconsistentes
                con.rollback();
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error en la transacción de cobro: " + e.getMessage());
            if (con != null) {
                try {
                    con.rollback(); // Cancelar cambios si saltó una excepción
                } catch (SQLException ex) {
                    System.out.println("Error al hacer rollback: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            // Cerramos todos los recursos abiertos de forma segura
            try {
                if (psInsert != null) {
                    psInsert.close();
                }
                if (psUpdate != null) {
                    psUpdate.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

}
