package Datos;

import Conexiones.SqlConection;
import Modelos.MNegocio;
import java.sql.*;

public class DNegocio {

    private SqlConection Conexion = new SqlConection();
    
    public MNegocio obtener() throws SQLException {
        String sql = "SELECT idNegocio, nombreNegocio, domicilioNegocio, provinciaNegocio, telefonoNegocio "
                   + "FROM Negocio WHERE idNegocio = 1";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                MNegocio n = new MNegocio();
                n.setIdNegocio(rs.getInt("idNegocio"));
                n.setNombreNegocio(rs.getString("nombreNegocio"));
                n.setDomicilioNegocio(rs.getString("domicilioNegocio"));
                n.setProvinciaNegocio(rs.getString("provinciaNegocio"));
                n.setTelefonoNegocio(rs.getString("telefonoNegocio"));
                return n;
            }
            return null;
        }
    }

    
    public void guardar(MNegocio n) throws SQLException {
        String sql = "IF EXISTS (SELECT 1 FROM Negocio WHERE idNegocio = 1) "
                   + "  UPDATE Negocio SET nombreNegocio = ?, domicilioNegocio = ?, "
                   + "  provinciaNegocio = ?, telefonoNegocio = ? WHERE idNegocio = 1 "
                   + "ELSE "
                   + "  INSERT INTO Negocio (nombreNegocio, domicilioNegocio, provinciaNegocio, telefonoNegocio) "
                   + "  VALUES (?, ?, ?, ?)";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            String[] valores = {
                n.getNombreNegocio(), n.getDomicilioNegocio(),
                n.getProvinciaNegocio(), n.getTelefonoNegocio()
            };
            
            for (int i = 0; i < 4; i++) {
                ps.setString(i + 1, valores[i]);
                ps.setString(i + 5, valores[i]);
            }
            ps.executeUpdate();
        }
    }
}