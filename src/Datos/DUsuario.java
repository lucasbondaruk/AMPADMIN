package Datos;

import Conexiones.SqlConection;
import Modelos.Rol;
import Modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DUsuario {

    private SqlConection conexion = new SqlConection();

    public Usuario validarLogin(String usuario, String contrasenia) throws Exception {    //SI TIENEN PROBLEMAS PARA VALIDAR USUARIO, CAPAZ ES POR EL STRING DE CONEXIÓN

        String sql = "SELECT u.id, u.usuario, u.estado, r.idRol, r.nomRol "
                + "FROM dbo.Usuario u "
                + "INNER JOIN dbo.Rol r ON u.idRol = r.idRol "
                + "WHERE u.usuario = ? AND u.contrasenia = ? AND u.estado = 'Activo'";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, contrasenia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Rol rol = new Rol();
                    rol.setIdRol(rs.getInt("idRol"));
                    rol.setNomRol(rs.getString("nomRol"));

                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setEstado(rs.getString("estado"));
                    u.setIdRol(rol);
                    return u;
                }
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error, usuario inválido: " + e.getMessage());
        }
    }

    public boolean insertar(Usuario u) throws Exception {
        String sql = "INSERT INTO dbo.Usuario (usuario, contrasenia, estado, telefono, domicilio, mail, idRol) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getContrasenia());
            ps.setString(3, u.getEstado());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getDomicilio());
            ps.setString(6, u.getMail());
            ps.setInt(7, u.getIdRol().getIdRol());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Usuario> listar() throws Exception {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT u.id, u.usuario, u.estado, u.telefono, u.domicilio, u.mail, r.idRol, r.nomRol "
                + "FROM dbo.Usuario u "
                + "INNER JOIN dbo.Rol r ON u.idRol = r.idRol "
                + "WHERE u.estado = 'Activo' "
                + "ORDER BY u.usuario";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("idRol"));
                rol.setNomRol(rs.getString("nomRol"));

                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setUsuario(rs.getString("usuario"));
                u.setEstado(rs.getString("estado"));
                u.setTelefono(rs.getString("telefono"));
                u.setDomicilio(rs.getString("domicilio"));
                u.setMail(rs.getString("mail"));
                u.setIdRol(rol);
                lista.add(u);
            }
        }
        return lista;
    }

    public Usuario buscarPorId(int id) throws Exception {
        String sql = "SELECT u.id, u.usuario, u.estado, u.telefono, u.domicilio, u.mail, r.idRol, r.nomRol "
                + "FROM dbo.Usuario u "
                + "INNER JOIN dbo.Rol r ON u.idRol = r.idRol "
                + "WHERE u.id = ?";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Rol rol = new Rol();
                    rol.setIdRol(rs.getInt("idRol"));
                    rol.setNomRol(rs.getString("nomRol"));

                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setEstado(rs.getString("estado"));
                    u.setTelefono(rs.getString("telefono"));
                    u.setDomicilio(rs.getString("domicilio"));
                    u.setMail(rs.getString("mail"));
                    u.setIdRol(rol);
                    return u;
                }
                return null;
            }
        }
    }

    public boolean actualizar(Usuario u) throws Exception {
        boolean cambiaContrasenia = u.getContrasenia() != null;

        String sql = "UPDATE dbo.Usuario SET usuario = ?, estado = ?, telefono = ?, domicilio = ?, mail = ?, idRol = ?"
                + (cambiaContrasenia ? ", contrasenia = ?" : "")
                + " WHERE id = ?";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getEstado());
            ps.setString(3, u.getTelefono());
            ps.setString(4, u.getDomicilio());
            ps.setString(5, u.getMail());
            ps.setInt(6, u.getIdRol().getIdRol());

            if (cambiaContrasenia) {
                ps.setString(7, u.getContrasenia());
                ps.setInt(8, u.getId());
            } else {
                ps.setInt(7, u.getId());
            }
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws Exception {
        String sql = "UPDATE dbo.Usuario SET estado = 'Inactivo' WHERE id = ?";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean existeUsuario(String usuario, int idExcluir) throws Exception {
        String sql = "SELECT COUNT(*) FROM dbo.Usuario WHERE usuario = ? AND id <> ?";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setInt(2, idExcluir);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public boolean existeUsuario(String usuario) throws Exception {
        String sql = "SELECT COUNT(*) FROM dbo.Usuario WHERE usuario = ?";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
