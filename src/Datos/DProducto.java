package Datos;

import Modelos.Producto;
import java.util.List;
import java.util.ArrayList;
import Conexiones.SqlConection;
import Modelos.IVA;
import Modelos.Proveedor;
import Modelos.RubroProducto;
import Modelos.StockProducto;
import Modelos.TipoProducto;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DProducto {

    private SqlConection conexion = new SqlConection();

    public boolean insertar(Producto p) {
        try {
            String sql = "INSERT INTO Producto"
                    + "(codigo, descripcion, precioUnitario, idRubroProducto, idIVA, idStockProducto, idTipoProducto, idProveedor)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            Connection cn = conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getCodigo());
            ps.setString(2, p.getDescripcion());
            ps.setFloat(3, p.getPrecioUnitario());
            ps.setInt(4, p.getIdRubroProducto().getIdRubroProducto());
            ps.setInt(5, p.getIdIVA().getIdIVA());
            ps.setInt(6, p.getIdStockProducto().getIdStockProducto());
            ps.setInt(7, p.getIdTipoProducto().getIdTipoProducto());
            ps.setInt(8, p.getIdProveedor().getIdProveedor());

            ps.executeUpdate();
            ps.close();
            cn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificar(Producto p) {
        try {
            String sql = "UPDATE Producto SET "
                    + "codigo = ?, "
                    + "descripcion = ?, "
                    + "precioUnitario = ?, "
                    + "idRubroProducto = ?, "
                    + "idIVA = ?, "
                    + "idStockProducto = ?, "
                    + "idTipoProducto = ?, "
                    + "idProveedor = ? "
                    + "WHERE idProducto = ?";
            Connection cn = conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getCodigo());
            ps.setString(2, p.getDescripcion());
            ps.setFloat(3, p.getPrecioUnitario());
            ps.setInt(4, p.getIdRubroProducto().getIdRubroProducto());
            ps.setInt(5, p.getIdIVA().getIdIVA());
            ps.setInt(6, p.getIdStockProducto().getIdStockProducto());
            ps.setInt(7, p.getIdTipoProducto().getIdTipoProducto());
            ps.setInt(8, p.getIdProveedor().getIdProveedor());
            ps.setInt(9, p.getIdProducto());
            int filasAfectadas = ps.executeUpdate();
            ps.close();
            cn.close();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Producto buscarPorCodigo(String codigo) throws Exception {

        String sql = """
           SELECT p.*,
           rp.descripcion AS rubro,
           i.descripcion AS iva,
           i.porcentaje,
           tp.descripcion AS tipo,
           tp.unidad,
           pr.nombre,
           sp.stockAct,
           sp.stockMin,
           sp.stockMax
           FROM Producto p
           INNER JOIN RubroProducto rp ON p.idRubroProducto = rp.idRubroProducto
           INNER JOIN IVA i ON p.idIVA = i.idIVA
           INNER JOIN TipoProducto tp ON p.idTipoProducto = tp.idTipoProducto
           INNER JOIN Proveedor pr ON p.idProveedor = pr.idProveedor
           INNER JOIN StockProducto sp ON p.idStockProducto = sp.idStockProducto
           WHERE p.codigo = ?
           """;

        try (Connection conn = conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Producto p = new Producto();

                p.setIdProducto(rs.getInt("idProducto"));
                p.setCodigo(rs.getInt("codigo"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioUnitario(rs.getFloat("precioUnitario"));

                StockProducto stock = new StockProducto();
                stock.setIdStockProducto(rs.getInt("idStockProducto"));
                stock.setStockAct(rs.getInt("stockAct"));
                stock.setStockMin(rs.getInt("stockMin"));
                stock.setStockMax(rs.getInt("stockMax"));

                p.setIdStockProducto(stock);

                RubroProducto rp = new RubroProducto();
                rp.setIdRubroProducto(rs.getInt("idRubroProducto"));
                rp.setDescripcion(rs.getString("rubro"));
                p.setIdRubroProducto(rp);

                IVA iva = new IVA();
                iva.setIdIVA(rs.getInt("idIVA"));
                iva.setDescripcion(rs.getString("iva"));
                iva.setPorcentaje(rs.getFloat("porcentaje"));
                p.setIdIVA(iva);

                TipoProducto tp = new TipoProducto();
                tp.setIdTipoProducto(rs.getInt("idTipoProducto"));
                tp.setDescripcion(rs.getString("tipo"));
                tp.setUnidad(rs.getString("unidad"));
                p.setIdTipoProducto(tp);

                Proveedor prov = new Proveedor();
                prov.setIdProveedor(rs.getInt("idProveedor"));
                prov.setNombre(rs.getString("nombre"));
                p.setIdProveedor(prov);

                return p;
            }

            return null;
        }
    }

    public Producto buscarPorId(int idProducto) throws Exception {

        String sql = """
    SELECT p.*,
           rp.descripcion AS rubro,
           i.descripcion AS iva,
           i.porcentaje,
           tp.descripcion AS tipo,
           tp.unidad,
           pr.nombre,
           sp.stockAct,
           sp.stockMin,
           sp.stockMax
    FROM Producto p
    INNER JOIN RubroProducto rp ON p.idRubroProducto = rp.idRubroProducto
    INNER JOIN IVA i ON p.idIVA = i.idIVA
    INNER JOIN TipoProducto tp ON p.idTipoProducto = tp.idTipoProducto
    INNER JOIN Proveedor pr ON p.idProveedor = pr.idProveedor
    INNER JOIN StockProducto sp ON p.idStockProducto = sp.idStockProducto
    WHERE p.idProducto = ?
    """;

        try (Connection conn = conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProducto);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Producto p = new Producto();

                p.setIdProducto(rs.getInt("idProducto"));
                p.setCodigo(rs.getInt("codigo"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioUnitario(rs.getFloat("precioUnitario"));

                StockProducto stock = new StockProducto();
                stock.setIdStockProducto(rs.getInt("idStockProducto"));
                stock.setStockAct(rs.getInt("stockAct"));
                stock.setStockMin(rs.getInt("stockMin"));
                stock.setStockMax(rs.getInt("stockMax"));

                p.setIdStockProducto(stock);

                RubroProducto rp = new RubroProducto();
                rp.setIdRubroProducto(rs.getInt("idRubroProducto"));
                rp.setDescripcion(rs.getString("rubro"));
                p.setIdRubroProducto(rp);

                IVA iva = new IVA();
                iva.setIdIVA(rs.getInt("idIVA"));
                iva.setDescripcion(rs.getString("iva"));
                iva.setPorcentaje(rs.getFloat("porcentaje"));
                p.setIdIVA(iva);

                TipoProducto tp = new TipoProducto();
                tp.setIdTipoProducto(rs.getInt("idTipoProducto"));
                tp.setDescripcion(rs.getString("tipo"));
                tp.setUnidad(rs.getString("unidad"));
                p.setIdTipoProducto(tp);

                Proveedor prov = new Proveedor();
                prov.setIdProveedor(rs.getInt("idProveedor"));
                prov.setNombre(rs.getString("nombre"));
                p.setIdProveedor(prov);

                return p;
            }

            return null;
        }
    }

    public List<Producto> listar() {

        List<Producto> lista = new ArrayList<>();

        String sql = """
    SELECT p.*,
           rp.descripcion AS rubro,
           i.descripcion AS iva,
           i.porcentaje,
           tp.descripcion AS tipo,
           tp.unidad,
           pr.nombre,
           sp.stockAct,
           sp.stockMin,
           sp.stockMax
    FROM Producto p
    INNER JOIN RubroProducto rp ON p.idRubroProducto = rp.idRubroProducto
    INNER JOIN IVA i ON p.idIVA = i.idIVA
    INNER JOIN TipoProducto tp ON p.idTipoProducto = tp.idTipoProducto
    INNER JOIN Proveedor pr ON p.idProveedor = pr.idProveedor
    INNER JOIN StockProducto sp ON p.idStockProducto = sp.idStockProducto
    """;

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Producto p = new Producto();

                p.setIdProducto(rs.getInt("idProducto"));
                p.setCodigo(rs.getInt("codigo"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioUnitario(rs.getFloat("precioUnitario"));

                StockProducto stock = new StockProducto();
                stock.setIdStockProducto(rs.getInt("idStockProducto"));
                stock.setStockAct(rs.getInt("stockAct"));
                stock.setStockMin(rs.getInt("stockMin"));
                stock.setStockMax(rs.getInt("stockMax"));

                p.setIdStockProducto(stock);

                RubroProducto rp = new RubroProducto();
                rp.setIdRubroProducto(rs.getInt("idRubroProducto"));
                rp.setDescripcion(rs.getString("rubro"));
                p.setIdRubroProducto(rp);

                IVA iva = new IVA();
                iva.setIdIVA(rs.getInt("idIVA"));
                iva.setDescripcion(rs.getString("iva"));
                iva.setPorcentaje(rs.getFloat("porcentaje"));
                p.setIdIVA(iva);

                TipoProducto tp = new TipoProducto();
                tp.setIdTipoProducto(rs.getInt("idTipoProducto"));
                tp.setDescripcion(rs.getString("tipo"));
                tp.setUnidad(rs.getString("unidad"));
                p.setIdTipoProducto(tp);

                Proveedor prov = new Proveedor();
                prov.setIdProveedor(rs.getInt("idProveedor"));
                prov.setNombre(rs.getString("nombre"));
                p.setIdProveedor(prov);

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean descontarStock(int idProducto, int cantidad) throws Exception {

        String sql = "UPDATE StockProducto "
                + "SET stockAct = stockAct - ? "
                + "WHERE idStockProducto = ("
                + "SELECT idStockProducto "
                + "FROM Producto "
                + "WHERE idProducto = ?)";

        try (Connection conn = conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cantidad);
            ps.setInt(2, idProducto);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int codigo) throws Exception {
        Connection conn = null;
        try {
            conn = conexion.getConexion();
            conn.setAutoCommit(false);
            
            String sqlBuscar = "SELECT idStockProducto "
                    + "FROM Producto "
                    + "WHERE codigo = ?";
            int idStockProducto = 0;
            try (PreparedStatement psBuscar = conn.prepareStatement(sqlBuscar)) {
                psBuscar.setInt(1, codigo);
                try (ResultSet rs = psBuscar.executeQuery()) {
                    if (rs.next()) {
                        idStockProducto = rs.getInt("idStockProducto");
                    } else {
                        return false;
                    }
                }
            }
            String sqlProducto = "DELETE FROM Producto WHERE codigo = ?";
            try (PreparedStatement psProducto = conn.prepareStatement(sqlProducto)) {
                psProducto.setInt(1, codigo);
                psProducto.executeUpdate();
            }
           
            String sqlStock = "DELETE FROM StockProducto "
                    + "WHERE idStockProducto = ?";
            try (PreparedStatement psStock = conn.prepareStatement(sqlStock)) {
                psStock.setInt(1, idStockProducto);
                psStock.executeUpdate();
            }
            conn.commit();
            return true;
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}
