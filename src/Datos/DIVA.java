package Datos;

import Modelos.IVA;
import Conexiones.SqlConection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DIVA {

    private SqlConection conexion = new SqlConection();

    public IVA buscarPorId(int idIVA) {
        IVA iva = null;

        try {
            Connection cn = conexion.getConexion();

            String sql = "SELECT * FROM IVA WHERE idIVA = ?";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idIVA);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                iva = new IVA();
                iva.setIdIVA(rs.getInt("idIVA"));
                iva.setDescripcion(rs.getString("descripcion"));
                iva.setPorcentaje(rs.getFloat("porcentaje"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return iva;
    }
}
