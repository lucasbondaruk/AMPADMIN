package Datos;

import Modelos.StockProducto;
import Conexiones.SqlConection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class DStockProducto {

    private SqlConection conexion = new SqlConection();

    public StockProducto select(int id) {
        StockProducto sp = null;

        try {
            Connection cn = conexion.getConexion();

            String sql = "SELECT * FROM StockProducto WHERE idStockProducto = ?";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                sp = new StockProducto();
                sp.setIdStockProducto(rs.getInt("idStockProducto"));
                sp.setStockMin(rs.getInt("stockMin"));
                sp.setStockMax(rs.getInt("stockMax"));
                sp.setStockAct(rs.getInt("stockAct"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }
    //dataset para grafico de torta Stock por rubro

    public DefaultPieDataset obtenerStockPorRubro() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Notar los espacios al final de cada línea antes de la comilla
        String sql = "SELECT r.descripcion AS Rubro, SUM(s.stockAct) AS TotalStock "
                + "FROM dbo.Producto p "
                + "INNER JOIN dbo.RubroProducto r ON p.idRubroProducto = r.idRubroProducto "
                + "INNER JOIN dbo.StockProducto s ON p.idStockProducto = s.idStockProducto "
                + "GROUP BY r.descripcion";

        try {
            Connection cn = conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String rubro = rs.getString("Rubro");
                double total = rs.getDouble("TotalStock");
                dataset.setValue(rubro, total);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataset;
    }

    //DataSet para Grafico de barras (STOCK CRITCO >:V
    public DefaultCategoryDataset obtenerStockCritico() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Notar los espacios al final de cada línea
        String sql = "SELECT p.descripcion AS Producto, s.stockAct AS StockActual, s.stockMin AS StockMinimo "
                + "FROM dbo.Producto p "
                + "INNER JOIN dbo.StockProducto s ON p.idStockProducto = s.idStockProducto "
                + "WHERE s.stockAct <= (s.stockMin * 1.5) "
                + "ORDER BY s.stockAct ASC";

        try {
            Connection cn = conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String producto = rs.getString("Producto");
                double stockActual = rs.getDouble("StockActual");
                double stockMinimo = rs.getDouble("StockMinimo");

                dataset.addValue(stockActual, "Stock Actual", producto);
                dataset.addValue(stockMinimo, "Stock Mínimo", producto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataset;
    }
}
