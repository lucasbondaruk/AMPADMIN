package Datos;

import Conexiones.SqlConection;
import Modelos.InformeVentasDia.ResumenMetodo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DInformeVentasDia {
    private final SqlConection conexion = new SqlConection();

    public List<ResumenMetodo> consultar(LocalDate fecha) throws SQLException {
        String sql = "SELECT v.idMetodoPago, mp.descripcion, COUNT_BIG(*) AS operaciones, "
                + "SUM(CAST(v.total AS DECIMAL(38,2))) AS total "
                + "FROM dbo.Venta v "
                + "LEFT JOIN dbo.MetodoPago mp ON mp.idMetodoPago = v.idMetodoPago "
                + "WHERE v.estado = 'Pagada' AND v.fecha >= ? AND v.fecha < ? "
                + "GROUP BY v.idMetodoPago, mp.descripcion "
                + "ORDER BY mp.descripcion, v.idMetodoPago";
        List<ResumenMetodo> resultado = new ArrayList<>();
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(fecha.atStartOfDay()));
            ps.setTimestamp(2, Timestamp.valueOf(fecha.plusDays(1).atStartOfDay()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("idMetodoPago");
                    Integer idMetodo = rs.wasNull() ? null : id;
                    resultado.add(new ResumenMetodo(idMetodo, rs.getString("descripcion"),
                            rs.getLong("operaciones"), rs.getBigDecimal("total")));
                }
            }
        }
        return resultado;
    }
}
