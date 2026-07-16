package Datos;

import Modelos.StockProducto;
import Conexiones.SqlConection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
