package Datos;

import Conexiones.SqlConection;
import Modelos.Rol;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DRol {

    private SqlConection Conexion = new SqlConection();

    public List<Rol> listar() throws SQLException {
        List<Rol> lista = new ArrayList<>();
        String sql = "SELECT idRol, nomRol, descripcion FROM Rol ORDER BY nomRol";
        try (Connection cn = Conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Rol r = new Rol();
                r.setIdRol(rs.getInt("idRol"));
                r.setNomRol(rs.getString("nomRol"));
                r.setDescripcion(rs.getString("descripcion"));
                lista.add(r);
            }
        }
        return lista;
    }

    public Rol buscarPorId(int idRol) throws SQLException {
        String sql = "SELECT idRol, nomRol, descripcion FROM Rol WHERE idRol = ?";
        try (Connection cn = Conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Rol r = new Rol();
                    r.setIdRol(rs.getInt("idRol"));
                    r.setNomRol(rs.getString("nomRol"));
                    r.setDescripcion(rs.getString("descripcion"));
                    return r;
                }
                return null; 
            }
        }
    }

    public boolean insertar(Rol r) throws SQLException {
        String sql = "INSERT INTO Rol (nomRol, descripcion) VALUES (?, ?)";
        try (Connection cn = Conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, r.getNomRol());
            ps.setString(2, r.getDescripcion());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Rol r) throws SQLException {
        String sql = "UPDATE Rol SET nomRol = ?, descripcion = ? WHERE idRol = ?";
        try (Connection cn = Conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, r.getNomRol());
            ps.setString(2, r.getDescripcion());
            ps.setInt(3, r.getIdRol());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int idRol) throws SQLException {
        String sql = "DELETE FROM Rol WHERE idRol = ?";
        try (Connection cn = Conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idRol);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean existeNombre(String nomRol, int idExcluir) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Rol WHERE nomRol = ? AND idRol <> ?";
        try (Connection cn = Conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nomRol);
            ps.setInt(2, idExcluir);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public boolean tieneUsuarios(int idRol) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Usuario WHERE idRol = ?";
        try (Connection cn = Conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
