package Datos;

import Conexiones.SqlConection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DEstadistica {

    private final SqlConection conexion = new SqlConection();

    public int obtenerCantidadVentas() throws Exception {

        String sql = "SELECT COUNT(*) AS cantidad FROM Venta";

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("cantidad");
            }

            return 0;

        } catch (SQLException e) {
            throw new Exception(
                    "Error al obtener la cantidad de ventas: "
                    + e.getMessage(), e
            );
        }
    }

    public double obtenerIngresosTotales() throws Exception {

        String sql = """
                     SELECT COALESCE(SUM(total), 0) AS ingresos
                     FROM Venta
                     """;

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("ingresos");
            }

            return 0;

        } catch (SQLException e) {
            throw new Exception(
                    "Error al obtener los ingresos totales: "
                    + e.getMessage(), e
            );
        }
    }

    public double obtenerTicketPromedio() throws Exception {

        String sql = """
                     SELECT COALESCE(AVG(CAST(total AS FLOAT)), 0) AS promedio
                     FROM Venta
                     """;

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("promedio");
            }

            return 0;

        } catch (SQLException e) {
            throw new Exception(
                    "Error al obtener el ticket promedio: "
                    + e.getMessage(), e
            );
        }
    }
}
