package Datos;

import Conexiones.SqlConection;
import Modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DUsuario {

    private SqlConection conexion = new SqlConection();

    public Usuario validarLogin(String usuario, String contrasenia) throws Exception {    //SI TIENEN PROBLEMAS PARA VALIDAR USUARIO, CAPAZ ES POR EL STRING DE CONEXIÓN

        String sql = "SELECT id, usuario, estado, idRol "
                + "FROM dbo.Usuario "
                + "WHERE usuario = ? "
                + "AND contrasenia = ? "
                + "AND estado = 'Activo' "
                + "AND idRol = 1";

        try (Connection con = conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, contrasenia);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setIdRol(rs.getInt("idRol"));
                    u.setEstado(rs.getString("estado"));

                    return u;
                }

                return null;
            }

        } catch (Exception e) {
            throw new Exception("Error, usuario inválido");
        }
    }
}
