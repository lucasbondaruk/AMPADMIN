package Datos;

import Conexiones.SqlConection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

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
    
    public Map<String, Double> obtenerVentasPorDia() {

        Map<String, Double> datos = new LinkedHashMap<>();

        String sql =
            "SELECT Fecha, SUM(total) AS Total " +
            "FROM Venta " +
            "GROUP BY Fecha " +
            "ORDER BY Fecha";

        try {

            SqlConection conexion = new SqlConection();
            Connection con = conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                java.sql.Date fecha = rs.getDate("Fecha");

                String etiqueta =
                        fecha.toLocalDate()
                             .format(DateTimeFormatter.ofPattern("dd/MM"));

                datos.put(etiqueta, rs.getDouble("Total"));
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return datos;
    }
    
    public Map<String, Integer> obtenerVentasPorMetodoPago() {

        Map<String, Integer> datos = new LinkedHashMap<>();

        String sql = """
            SELECT
                mp.descripcion,
                COUNT(v.idVenta) AS Cantidad
            FROM Venta v
            INNER JOIN MetodoPago mp
                ON v.idMetodoPago = mp.idMetodoPago
            GROUP BY mp.descripcion
            ORDER BY Cantidad DESC
            """;

        SqlConection conexion = new SqlConection();

        try (
                Connection con = conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                datos.put(
                        rs.getString("descripcion"),
                        rs.getInt("Cantidad")
                );
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al obtener métodos de pago:\n" + ex.getMessage()
            );
        }

        return datos;
    }
    
    public int obtenerTotalClientes() {

        int total = 0;

        String sql = "SELECT COUNT(*) AS Total FROM Cliente WHERE tipoPersona IS NOT NULL AND tipoPersona <> ''";

        try {

            SqlConection conexion = new SqlConection();
            Connection con = conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getInt("Total");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return total;
    }
    
    public int obtenerCantidadEmpresas() {

        int total = 0;

        String sql =
                "SELECT COUNT(*) AS Total "
                + "FROM Cliente "
                + "WHERE tipoPersona = 'Empresa'";

        try {

            SqlConection conexion = new SqlConection();
            Connection con = conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getInt("Total");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return total;
    }
    
    public int obtenerCantidadPersonas() {

        int total = 0;

        String sql =
                "SELECT COUNT(*) AS Total "
                + "FROM Cliente "
                + "WHERE tipoPersona = 'Persona'";

        try {

            SqlConection conexion = new SqlConection();
            Connection con = conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getInt("Total");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return total;
    }
    
    public Map<String, Integer> obtenerClientesPorTipo() {

        Map<String, Integer> datos = new LinkedHashMap<>();

        String sql =
        "SELECT tipoPersona, COUNT(*) AS Cantidad "
        + "FROM Cliente "
        + "WHERE tipoPersona IN ('Persona', 'Empresa') "
        + "GROUP BY tipoPersona";

        try {

            SqlConection conexion = new SqlConection();
            Connection con = conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                datos.put(
                        rs.getString("tipoPersona"),
                        rs.getInt("Cantidad")
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return datos;
    }
    
    public Map<String, Integer> obtenerClientesPorCondicionIVA() {

        Map<String, Integer> datos = new LinkedHashMap<>();

        String sql =
                "SELECT ci.descripcion, COUNT(*) AS Cantidad "
                + "FROM Cliente c "
                + "INNER JOIN CondIva ci "
                + "ON c.idCondIva = ci.idCondIva "
                + "GROUP BY ci.descripcion "
                + "ORDER BY Cantidad DESC";

        try {

            SqlConection conexion = new SqlConection();
            Connection con = conexion.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                datos.put(
                        rs.getString("descripcion"),
                        rs.getInt("Cantidad")
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return datos;
    }
}
