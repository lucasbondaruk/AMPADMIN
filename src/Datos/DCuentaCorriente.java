package Datos;

import Conexiones.SqlConection;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelos.CuentaCorriente;

public class DCuentaCorriente {

    private SqlConection conexion = new SqlConection();

    public void insert(CuentaCorriente c) throws Exception {

        String sql = "INSERT INTO dbo.CuentaCorriente "
                + "(razonSocial, telefono, cuit, fchaIngreso, domicilio, mail, tipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getRazonSocial());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getCuit());
            ps.setDate(4, new java.sql.Date(c.getfchaIngreso().getTime()));
            ps.setString(5, c.getDomicilio());
            ps.setString(6, c.getMail());
            ps.setString(7, c.getTipo());

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new Exception("No se insertó ningún registro");
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void update(CuentaCorriente c) throws Exception {
        String sql = "UPDATE dbo.CuentaCorriente SET "
                + "razonSocial=?, telefono=?, cuit=?, fchaIngreso=?, domicilio=?, mail=?, tipo=? "
                + "WHERE id=?";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getRazonSocial());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getCuit());
            ps.setDate(4, new java.sql.Date(c.getfchaIngreso().getTime()));
            ps.setString(5, c.getDomicilio());
            ps.setString(6, c.getMail());
            ps.setString(7, c.getTipo());
            ps.setInt(8, c.getId());

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new Exception("No existe el id " + c.getId() + ". Ingrese un ID válido");
            }

        } catch (Exception e) {
            throw new Exception("Error al actualizar la cuenta corriente", e);
        }
    }

    public void delete(int id) throws Exception {

    String sql = "UPDATE dbo.CuentaCorriente SET estado = 'INACTIVA' WHERE id = ?";

    try (Connection con = conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);

        int filas = ps.executeUpdate();

        if (filas == 0) {
            throw new Exception("No existe una cuenta con ese ID");
        }

        } catch (Exception e) {
            throw new Exception("Error al desactivar la cuenta corriente", e);
        }
    }

    public CuentaCorriente select(String cuit) throws Exception {
        String sql = "SELECT * FROM dbo.CuentaCorriente WHERE cuit = ? AND estado = 'ACTIVA'";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cuit);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    CuentaCorriente c = new CuentaCorriente();
                    c.setId(rs.getInt("id"));
                    c.setRazonSocial(rs.getString("razonSocial"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setCuit(rs.getString("cuit"));
                    c.setfchaIngreso(rs.getDate("fchaIngreso"));
                    c.setDomicilio(rs.getString("domicilio"));
                    c.setMail(rs.getString("mail"));
                    c.setTipo(rs.getString("tipo"));
                    c.setEstado(rs.getString("estado"));

                    return c;
                }

                return null;
            } catch (SQLException e) {
                throw new Exception("Error al buscar la cuenta corriente");
            }
        }
    }

    public List<CuentaCorriente> listar() {
        List<CuentaCorriente> lista = new ArrayList<>();

        String sql = "SELECT * FROM CuentaCorriente WHERE estado = 'ACTIVA'";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CuentaCorriente c = new CuentaCorriente();
                c.setId(rs.getInt("id"));
                c.setRazonSocial(rs.getString("razonSocial"));
                c.setTelefono(rs.getString("telefono"));
                c.setCuit(rs.getString("cuit"));
                c.setfchaIngreso(rs.getDate("fchaIngreso"));
                c.setDomicilio(rs.getString("domicilio"));
                c.setMail(rs.getString("mail"));
                c.setTipo(rs.getString("tipo"));

                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean existeCuit(String cuit) throws Exception {

        String sql = "SELECT COUNT(*) FROM dbo.CuentaCorriente WHERE cuit = ?";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cuit);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

            return false;

        } catch (SQLException e) {
            throw new Exception("Error al verificar CUIT", e);
        }
    }
}
