package Datos;

import Conexiones.SqlConection;
import Modelos.InformeIA.ItemRanking;
import Modelos.InformeIA.ProductoCritico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Consultas de agregación para el Informe con IA.
 *
 * Todas las fechas se manejan como rango semiabierto [desde, hasta + 1 día),
 * igual que DInformeVentasDia, para no perder ventas con hora.
 *
 * Se cuentan todas las ventas (Pagada y Pendiente/cuenta corriente) y se excluyen
 * automáticamente las inválidas que detecta DCalidadDatos: total nulo, cero o negativo
 * y fecha futura. Si más adelante agregan un estado "Anulada", sumarlo a FILTRO_VENTAS.
 */
public class DAnalisisVentas {

    /** Condición de venta válida, compartida con DCalidadDatos. */
    public static final String VENTA_VALIDA = " v.total > 0 AND v.fecha <= CAST(GETDATE() AS DATE) ";

    private static final String FILTRO_VENTAS = " v.fecha >= ? AND v.fecha < ? AND" + VENTA_VALIDA;

    private final SqlConection conexion = new SqlConection();

    /** Total vendido por día (solo los días con ventas). */
    public Map<LocalDate, Double> ventasDiarias(LocalDate desde, LocalDate hasta) throws SQLException {
        String sql = "SELECT CAST(v.fecha AS DATE) AS dia, "
                + "SUM(CAST(v.total AS DECIMAL(18,2))) AS total "
                + "FROM dbo.Venta v WHERE" + FILTRO_VENTAS
                + "GROUP BY CAST(v.fecha AS DATE) ORDER BY dia";
        Map<LocalDate, Double> datos = new TreeMap<>();
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setRango(ps, 1, desde, hasta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    datos.put(rs.getDate("dia").toLocalDate(), rs.getDouble("total"));
                }
            }
        }
        return datos;
    }

    /** [0] = ingresos, [1] = cantidad de ventas del rango. */
    public double[] totales(LocalDate desde, LocalDate hasta) throws SQLException {
        String sql = "SELECT COALESCE(SUM(CAST(v.total AS DECIMAL(18,2))), 0) AS ingresos, "
                + "COUNT_BIG(*) AS cantidad FROM dbo.Venta v WHERE" + FILTRO_VENTAS;
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setRango(ps, 1, desde, hasta);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return new double[]{rs.getDouble("ingresos"), rs.getLong("cantidad")};
            }
        }
    }

    public List<ItemRanking> topProductos(LocalDate desde, LocalDate hasta, int limite) throws SQLException {
        String sql = "SELECT TOP (?) p.descripcion AS nombre, "
                + "SUM(CAST(dv.importe AS DECIMAL(18,2))) AS importe, SUM(dv.cantidad) AS cantidad "
                + "FROM dbo.DetalleVenta dv "
                + "INNER JOIN dbo.Venta v ON v.idVenta = dv.idVenta "
                + "INNER JOIN dbo.Producto p ON p.idProducto = dv.idProducto "
                + "WHERE" + FILTRO_VENTAS
                + "GROUP BY p.idProducto, p.descripcion ORDER BY importe DESC";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, limite);
            setRango(ps, 2, desde, hasta);
            return leerRanking(ps);
        }
    }

    public List<ItemRanking> ventasPorRubro(LocalDate desde, LocalDate hasta) throws SQLException {
        String sql = "SELECT COALESCE(rp.descripcion, 'Sin rubro') AS nombre, "
                + "SUM(CAST(dv.importe AS DECIMAL(18,2))) AS importe, SUM(dv.cantidad) AS cantidad "
                + "FROM dbo.DetalleVenta dv "
                + "INNER JOIN dbo.Venta v ON v.idVenta = dv.idVenta "
                + "INNER JOIN dbo.Producto p ON p.idProducto = dv.idProducto "
                + "LEFT JOIN dbo.RubroProducto rp ON rp.idRubroProducto = p.idRubroProducto "
                + "WHERE" + FILTRO_VENTAS
                + "GROUP BY rp.descripcion ORDER BY importe DESC";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setRango(ps, 1, desde, hasta);
            return leerRanking(ps);
        }
    }

    public List<ItemRanking> ventasPorMetodoPago(LocalDate desde, LocalDate hasta) throws SQLException {
        String sql = "SELECT COALESCE(mp.descripcion, 'Sin método') AS nombre, "
                + "SUM(CAST(v.total AS DECIMAL(18,2))) AS importe, COUNT_BIG(*) AS cantidad "
                + "FROM dbo.Venta v "
                + "LEFT JOIN dbo.MetodoPago mp ON mp.idMetodoPago = v.idMetodoPago "
                + "WHERE" + FILTRO_VENTAS
                + "GROUP BY mp.descripcion ORDER BY importe DESC";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setRango(ps, 1, desde, hasta);
            return leerRanking(ps);
        }
    }

    /**
     * Productos con stock en o por debajo del mínimo, o que al ritmo de venta
     * de los últimos 30 días se quedan sin stock en menos de {@code diasAlerta} días.
     */
    public List<ProductoCritico> stockCritico(LocalDate hoy, int diasAlerta) throws SQLException {
        String sql = """
                SELECT p.codigo, p.descripcion, s.stockAct, s.stockMin,
                       COALESCE(vend.cantidad, 0) / 30.0 AS ventaDiaria
                FROM dbo.Producto p
                INNER JOIN dbo.StockProducto s ON s.idStockProducto = p.idStockProducto
                LEFT JOIN (
                    SELECT dv.idProducto, SUM(dv.cantidad) AS cantidad
                    FROM dbo.DetalleVenta dv
                    INNER JOIN dbo.Venta v ON v.idVenta = dv.idVenta
                    WHERE v.fecha >= ? AND v.fecha < ? AND v.total > 0
                    GROUP BY dv.idProducto
                ) vend ON vend.idProducto = p.idProducto
                WHERE s.stockAct <= s.stockMin
                   OR (vend.cantidad > 0 AND s.stockAct / (vend.cantidad / 30.0) < ?)
                ORDER BY CASE WHEN COALESCE(vend.cantidad, 0) = 0 THEN 1 ELSE 0 END,
                         s.stockAct / NULLIF(vend.cantidad / 30.0, 0)
                """;
        List<ProductoCritico> lista = new ArrayList<>();
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setRango(ps, 1, hoy.minusDays(29), hoy);
            ps.setInt(3, diasAlerta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new ProductoCritico(rs.getInt("codigo"), rs.getString("descripcion"),
                            rs.getInt("stockAct"), rs.getInt("stockMin"), rs.getDouble("ventaDiaria")));
                }
            }
        }
        return lista;
    }

    /** Usuario del sistema con su rol. */
    public record UsuarioResumen(int id, String usuario, String rol, String estado) {}

    /** Ventas de un día de un vendedor (idVendedor null = venta sin vendedor). */
    public record VentaDiariaVendedor(Integer idVendedor, LocalDate dia, double total, long cantidad) {}

    /** Todos los usuarios del sistema, cualquiera sea su rol o estado. */
    public List<UsuarioResumen> usuarios() throws SQLException {
        String sql = "SELECT u.id, u.usuario, u.estado, r.nomRol FROM dbo.Usuario u "
                + "LEFT JOIN dbo.Rol r ON r.idRol = u.idRol ORDER BY u.usuario";
        List<UsuarioResumen> lista = new ArrayList<>();
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new UsuarioResumen(rs.getInt("id"), rs.getString("usuario"),
                        rs.getString("nomRol"), rs.getString("estado")));
            }
        }
        return lista;
    }

    /** Ventas por vendedor y por día del rango. */
    public List<VentaDiariaVendedor> ventasDiariasPorVendedor(LocalDate desde, LocalDate hasta) throws SQLException {
        String sql = "SELECT v.idVendedor, CAST(v.fecha AS DATE) AS dia, "
                + "SUM(CAST(v.total AS DECIMAL(18,2))) AS total, COUNT_BIG(*) AS cantidad "
                + "FROM dbo.Venta v WHERE" + FILTRO_VENTAS
                + "GROUP BY v.idVendedor, CAST(v.fecha AS DATE)";
        List<VentaDiariaVendedor> lista = new ArrayList<>();
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setRango(ps, 1, desde, hasta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("idVendedor");
                    Integer idVendedor = rs.wasNull() ? null : id;
                    lista.add(new VentaDiariaVendedor(idVendedor, rs.getDate("dia").toLocalDate(),
                            rs.getDouble("total"), rs.getLong("cantidad")));
                }
            }
        }
        return lista;
    }

    private static void setRango(PreparedStatement ps, int indice, LocalDate desde, LocalDate hasta)
            throws SQLException {
        ps.setTimestamp(indice, Timestamp.valueOf(desde.atStartOfDay()));
        ps.setTimestamp(indice + 1, Timestamp.valueOf(hasta.plusDays(1).atStartOfDay()));
    }

    private static List<ItemRanking> leerRanking(PreparedStatement ps) throws SQLException {
        List<ItemRanking> lista = new ArrayList<>();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new ItemRanking(rs.getString("nombre"),
                        rs.getDouble("importe"), rs.getDouble("cantidad")));
            }
        }
        return lista;
    }
}
