package Datos;

import Conexiones.SqlConection;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelos.Cliente;

public class DCliente {

    private SqlConection conexion = new SqlConection();

    public void insert(Cliente c) throws Exception {

        String sql = "INSERT INTO dbo.Cliente "
                + "(razonSocial, telefono, cuit, fchaIngreso, domicilio, mail, tipoCliente,estado,tipoPersona,idCondIva) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getRazonSocial());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getCuit());
            ps.setDate(4, new java.sql.Date(c.getfchaIngreso().getTime()));
            ps.setString(5, c.getDomicilio());
            ps.setString(6, c.getMail());
            ps.setString(7, c.getTipoCliente());
            ps.setString(8,c.getEstado());
            ps.setString(9,c.getTipoPersona());
            ps.setInt(10,c.getIdCondIva());

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new Exception("No se insertó ningún registro");
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void update(Cliente c) throws Exception {
        String sql = "UPDATE dbo.Cliente SET "
                + "razonSocial=?, telefono=?, cuit=?, fchaIngreso=?, domicilio=?, mail=?, tipoCliente=?, estado=?, tipoPersona=?, idCondIva = ? "
                + "WHERE idCliente=?";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getRazonSocial());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getCuit());
            ps.setDate(4, new java.sql.Date(c.getfchaIngreso().getTime()));
            ps.setString(5, c.getDomicilio());
            ps.setString(6, c.getMail());
            ps.setString(7, c.getTipoCliente());
            ps.setString(9,c.getTipoPersona());
            ps.setInt(10,c.getIdCondIva());

            ps.setInt(8, c.getIdCliente());

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new Exception("No existe el id " + c.getIdCliente() + ". Ingrese un ID válido");
            }

        } catch (Exception e) {
            throw new Exception("Error al actualizar la cuenta corriente", e);
        }
    }

    public void delete(int id) throws Exception {

    String sql = "UPDATE dbo.Cliente SET estado = 'INACTIVA' WHERE id = ?";

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

    public Cliente select(String cuit) throws Exception {
        String sql = "SELECT * FROM dbo.Cliente WHERE cuit = ? AND estado = 'Activo'";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cuit);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt("idCliente"));
                    c.setRazonSocial(rs.getString("razonSocial"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setCuit(rs.getString("cuit"));
                    c.setfchaIngreso(rs.getDate("fchaIngreso"));
                    c.setDomicilio(rs.getString("domicilio"));
                    c.setMail(rs.getString("mail"));
                    c.setTipoCliente(rs.getString("tipoCliente"));
                    c.setEstado(rs.getString("estado"));
                    c.setTipoPersona(rs.getString("tipoPersona"));
                    c.setIdCondIva(rs.getInt("idCondIva"));

                    return c;
                }

                return null;
            } catch (SQLException e) {
                throw new Exception("Error al buscar la cuenta corriente");
            }
        }
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM Cliente WHERE estado = 'ACTIVO'";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("idCliente"));
                c.setRazonSocial(rs.getString("razonSocial"));
                c.setTelefono(rs.getString("telefono"));
                c.setCuit(rs.getString("cuit"));
                c.setfchaIngreso(rs.getDate("fchaIngreso"));
                c.setDomicilio(rs.getString("domicilio"));
                c.setMail(rs.getString("mail"));
                c.setTipoPersona(rs.getString("tipoPersona"));

                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean existeCuit(String cuit) throws Exception {

        String sql = "SELECT COUNT(*) FROM dbo.Cliente WHERE cuit = ?";

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
