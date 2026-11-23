package Datos;

import Conexiones.SqlConection;
import Modelos.InformeIA.Problema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Validaciones de calidad de datos sobre las ventas del período.
 *
 * Las ventas inválidas (total nulo/cero/negativo, fecha futura) se excluyen del análisis
 * (ver DAnalisisVentas.VENTA_VALIDA). El resto de los problemas se informan para revisar,
 * pero no se excluyen porque pueden ser ventas reales.
 */
public class DCalidadDatos {

    private final SqlConection conexion = new SqlConection();

    public List<Problema> validar(LocalDate desde, LocalDate hasta) throws SQLException {
        List<Problema> problemas = new ArrayList<>();
        try (Connection con = conexion.getConexion()) {
            agregar(problemas, con, desde, hasta, "Total inválido",
                    "Ventas con total nulo, cero o negativo (excluidas del análisis)", true,
                    "SELECT COUNT(*) FROM dbo.Venta v WHERE v.fecha >= ? AND v.fecha < ? "
                    + "AND (v.total IS NULL OR v.total <= 0)");

            // Fecha futura: se busca en todo el período elegido aunque supere hoy
            agregar(problemas, con, desde, hasta.isAfter(LocalDate.now()) ? hasta : LocalDate.now().plusYears(5),
                    "Fecha futura", "Ventas con fecha posterior a hoy (excluidas del análisis)", true,
                    "SELECT COUNT(*) FROM dbo.Venta v WHERE v.fecha >= ? AND v.fecha < ? "
                    + "AND v.fecha > CAST(GETDATE() AS DATE)");

            agregar(problemas, con, desde, hasta, "Venta sin detalle",
                    "Ventas sin productos en DetalleVenta: cuentan en ingresos pero no en productos ni rubros", false,
                    "SELECT COUNT(*) FROM dbo.Venta v WHERE v.fecha >= ? AND v.fecha < ? AND"
                    + DAnalisisVentas.VENTA_VALIDA
                    + "AND NOT EXISTS (SELECT 1 FROM dbo.DetalleVenta dv WHERE dv.idVenta = v.idVenta)");

            agregar(problemas, con, desde, hasta, "Posible duplicado",
                    "Ventas con mismo día, cliente, total y medio de pago que otra (revisar si se cargaron dos veces)", false,
                    "SELECT COUNT(*) FROM dbo.Venta v WHERE v.fecha >= ? AND v.fecha < ? AND"
                    + DAnalisisVentas.VENTA_VALIDA
                    + "AND EXISTS (SELECT 1 FROM dbo.Venta d WHERE d.idVenta < v.idVenta "
                    + "AND CAST(d.fecha AS DATE) = CAST(v.fecha AS DATE) AND d.idCliente = v.idCliente "
                    + "AND d.total = v.total AND d.idMetodoPago = v.idMetodoPago)");

            agregar(problemas, con, desde, hasta, "Detalle inválido",
                    "Renglones de venta con cantidad o importe cero o negativo", false,
                    "SELECT COUNT(*) FROM dbo.DetalleVenta dv INNER JOIN dbo.Venta v ON v.idVenta = dv.idVenta "
                    + "WHERE v.fecha >= ? AND v.fecha < ? AND (dv.cantidad <= 0 OR dv.importe <= 0)");
        }
        return problemas;
    }

    /** Totales de cada venta válida del período, para detectar tickets atípicos. */
    public List<Double> totalesDeVentas(LocalDate desde, LocalDate hasta) throws SQLException {
        String sql = "SELECT CAST(v.total AS FLOAT) AS total FROM dbo.Venta v "
                + "WHERE v.fecha >= ? AND v.fecha < ? AND" + DAnalisisVentas.VENTA_VALIDA;
        List<Double> totales = new ArrayList<>();
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            rango(ps, desde, hasta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    totales.add(rs.getDouble("total"));
                }
            }
        }
        return totales;
    }

    private static void agregar(List<Problema> lista, Connection con, LocalDate desde, LocalDate hasta,
                                String tipo, String detalle, boolean excluido, String sql) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            rango(ps, desde, hasta);
            try (ResultSet rs = ps.executeQuery()) {
                int cantidad = rs.next() ? rs.getInt(1) : 0;
                if (cantidad > 0) {
                    lista.add(new Problema(tipo, detalle, cantidad, excluido));
                }
            }
        }
    }

    private static void rango(PreparedStatement ps, LocalDate desde, LocalDate hasta) throws SQLException {
        ps.setTimestamp(1, Timestamp.valueOf(desde.atStartOfDay()));
        ps.setTimestamp(2, Timestamp.valueOf(hasta.plusDays(1).atStartOfDay()));
    }
}
