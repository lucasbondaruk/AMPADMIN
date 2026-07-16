/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
                p.setRubroProducto(rp);

                IVA iva = new IVA();
                iva.setIdIVA(rs.getInt("idIVA"));
                iva.setDescripcion(rs.getString("iva"));
                iva.setPorcentaje(rs.getFloat("porcentaje"));
                p.setIva(iva);

                TipoProducto tp = new TipoProducto();
                tp.setIdTipoProducto(rs.getInt("idTipoProducto"));
                tp.setDescripcion(rs.getString("tipo"));
                tp.setUnidad(rs.getString("unidad"));
                p.setTipoProducto(tp);

                Proveedor prov = new Proveedor();
                prov.setIdProveedor(rs.getInt("idProveedor"));
                prov.setNombre(rs.getString("nombre"));
                p.setProveedor(prov);

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
                p.setRubroProducto(rp);

                IVA iva = new IVA();
                iva.setIdIVA(rs.getInt("idIVA"));
                iva.setDescripcion(rs.getString("iva"));
                iva.setPorcentaje(rs.getFloat("porcentaje"));
                p.setIva(iva);

                TipoProducto tp = new TipoProducto();
                tp.setIdTipoProducto(rs.getInt("idTipoProducto"));
                tp.setDescripcion(rs.getString("tipo"));
                tp.setUnidad(rs.getString("unidad"));
                p.setTipoProducto(tp);

                Proveedor prov = new Proveedor();
                prov.setIdProveedor(rs.getInt("idProveedor"));
                prov.setNombre(rs.getString("nombre"));
                p.setProveedor(prov);

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
                p.setRubroProducto(rp);

                IVA iva = new IVA();
                iva.setIdIVA(rs.getInt("idIVA"));
                iva.setDescripcion(rs.getString("iva"));
                iva.setPorcentaje(rs.getFloat("porcentaje"));
                p.setIva(iva);

                TipoProducto tp = new TipoProducto();
                tp.setIdTipoProducto(rs.getInt("idTipoProducto"));
                tp.setDescripcion(rs.getString("tipo"));
                tp.setUnidad(rs.getString("unidad"));
                p.setTipoProducto(tp);

                Proveedor prov = new Proveedor();
                prov.setIdProveedor(rs.getInt("idProveedor"));
                prov.setNombre(rs.getString("nombre"));
                p.setProveedor(prov);

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

}
