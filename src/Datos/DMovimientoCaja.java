package Datos;

import Conexiones.SqlConection;
import Modelos.MovimientoCaja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DMovimientoCaja {

    private final SqlConection conexion = new SqlConection();
    private static final String COLUMNAS = "idMovimientoCaja, tipoMovimiento, "
            + "monto, motivo, fecha, idUsuarioRegistro, idVenta, idMetodoPago";

    public boolean insertar(MovimientoCaja movimiento) throws SQLException {
        String sql = "INSERT INTO dbo.MovimientoCaja "
                + "(tipoMovimiento, monto, motivo, idUsuarioRegistro, idVenta, idMetodoPago) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, movimiento.getTipoMovimiento());
            ps.setBigDecimal(2, movimiento.getMonto());
            ps.setString(3, movimiento.getMotivo());
            ps.setInt(4, movimiento.getIdUsuarioRegistro());
            if (movimiento.getIdVenta() == null) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, movimiento.getIdVenta());
            }
            if (movimiento.getIdMetodoPago() == null) {
                ps.setNull(6, Types.INTEGER);
            } else {
                ps.setInt(6, movimiento.getIdMetodoPago());
            }
            return ps.executeUpdate() > 0;
        }
    }

    /** Consulta ambos dias completos, incluyendo desde y hasta. */
    public List<MovimientoCaja> listarPorFechas(LocalDate desde, LocalDate hasta)
            throws SQLException {
        if (desde == null || hasta == null || desde.isAfter(hasta)) {
            throw new IllegalArgumentException("Debe indicar un rango de fechas valido.");
        }
        String sql = "SELECT " + COLUMNAS + " FROM dbo.MovimientoCaja "
                + "WHERE fecha >= ? AND fecha < ? ORDER BY fecha DESC, idMovimientoCaja DESC";
        List<MovimientoCaja> movimientos = new ArrayList<>();

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(desde.atStartOfDay()));
            ps.setTimestamp(2, Timestamp.valueOf(hasta.plusDays(1).atStartOfDay()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    movimientos.add(mapear(rs));
                }
            }
        }
        return movimientos;
    }

    public MovimientoCaja buscarPorId(int idMovimientoCaja) throws SQLException {
        String sql = "SELECT " + COLUMNAS + " FROM dbo.MovimientoCaja WHERE idMovimientoCaja = ?";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMovimientoCaja);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    private MovimientoCaja mapear(ResultSet rs) throws SQLException {
        MovimientoCaja movimiento = new MovimientoCaja();
        movimiento.setIdMovimientoCaja(rs.getInt("idMovimientoCaja"));
        movimiento.setTipoMovimiento(rs.getString("tipoMovimiento"));
        movimiento.setMonto(rs.getBigDecimal("monto"));
        movimiento.setMotivo(rs.getString("motivo"));
        movimiento.setFecha(rs.getTimestamp("fecha"));
        movimiento.setIdUsuarioRegistro(rs.getInt("idUsuarioRegistro"));
        int idVenta = rs.getInt("idVenta");
        movimiento.setIdVenta(rs.wasNull() ? null : idVenta);
        int idMetodoPago = rs.getInt("idMetodoPago");
        movimiento.setIdMetodoPago(rs.wasNull() ? null : idMetodoPago);
        return movimiento;
    }
}
