package Datos;

import Conexiones.SqlConection;
import Modelos.MetodoPago;
import Modelos.RegistroHistorialCaja;
import Modelos.RegistroHistorialCaja.Origen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Consultas exclusivamente de lectura para AFG-7. */
public class DHistorialCaja {
    private final SqlConection conexion = new SqlConection();

    public List<RegistroHistorialCaja> listarPorFechas(LocalDate desde, LocalDate hasta)
            throws SQLException {
        if (desde == null || hasta == null || desde.isAfter(hasta)
                || hasta.equals(LocalDate.MAX)) {
            throw new IllegalArgumentException("Debe indicar un rango de fechas valido.");
        }
        String ventas = "SELECT v.idVenta, v.numVenta, v.fecha, v.total, v.estado, "
                + "v.idMetodoPago, c.razonSocial AS cliente, mp.descripcion AS metodo, "
                + "u.usuario AS responsable FROM dbo.Venta v "
                + "LEFT JOIN dbo.Cliente c ON c.idCliente = v.idCliente "
                + "LEFT JOIN dbo.MetodoPago mp ON mp.idMetodoPago = v.idMetodoPago "
                + "LEFT JOIN dbo.Usuario u ON u.id = v.idVendedor "
                + "WHERE v.estado = 'Pagada' AND v.fecha >= ? AND v.fecha < ?";
        String manuales = "SELECT m.idMovimientoCaja, m.fecha, m.tipoMovimiento, "
                + "m.monto, m.motivo, u.usuario AS responsable FROM dbo.MovimientoCaja m "
                + "LEFT JOIN dbo.Usuario u ON u.id = m.idUsuarioRegistro "
                + "WHERE m.idVenta IS NULL AND m.idMetodoPago IS NULL "
                + "AND m.fecha >= ? AND m.fecha < ?";
        List<RegistroHistorialCaja> resultado = new ArrayList<>();
        try (Connection con = conexion.getConexion()) {
            try (PreparedStatement ps = preparar(con, ventas, desde, hasta);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int numero = rs.getInt("numVenta");
                    Integer boleta = rs.wasNull() ? null : numero;
                    int metodo = rs.getInt("idMetodoPago");
                    Integer idMetodo = rs.wasNull() ? null : metodo;
                    resultado.add(new RegistroHistorialCaja(Origen.VENTA,
                            rs.getInt("idVenta"), rs.getDate("fecha"), boleta,
                            rs.getString("cliente"), "Venta", idMetodo,
                            rs.getString("metodo"), rs.getBigDecimal("total"),
                            rs.getString("responsable"), rs.getString("estado"), null));
                }
            }
            try (PreparedStatement ps = preparar(con, manuales, desde, hasta);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.add(new RegistroHistorialCaja(Origen.MOVIMIENTO,
                            rs.getInt("idMovimientoCaja"), rs.getTimestamp("fecha"),
                            null, null, rs.getString("tipoMovimiento"), null, null,
                            rs.getBigDecimal("monto"), rs.getString("responsable"),
                            null, rs.getString("motivo")));
                }
            }
        }
        return resultado;
    }

    private PreparedStatement preparar(Connection con, String sql, LocalDate desde,
            LocalDate hasta) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        try {
            ps.setTimestamp(1, Timestamp.valueOf(desde.atStartOfDay()));
            ps.setTimestamp(2, Timestamp.valueOf(hasta.plusDays(1).atStartOfDay()));
            return ps;
        } catch (SQLException | RuntimeException ex) {
            ps.close();
            throw ex;
        }
    }

    public List<MetodoPago> listarMetodos() throws SQLException {
        List<MetodoPago> resultado = new ArrayList<>();
        String sql = "SELECT idMetodoPago, descripcion, activo FROM dbo.MetodoPago ORDER BY descripcion";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(new MetodoPago(rs.getInt("idMetodoPago"),
                        rs.getString("descripcion"), rs.getBoolean("activo")));
            }
        }
        return resultado;
    }

    public List<String> listarEstadosVentasCobradas() throws SQLException {
        List<String> resultado = new ArrayList<>();
        String sql = "SELECT DISTINCT estado FROM dbo.Venta WHERE estado = 'Pagada' ORDER BY estado";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(rs.getString("estado"));
            }
        }
        return resultado;
    }
}
