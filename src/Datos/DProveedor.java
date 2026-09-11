package Datos;

import Conexiones.SqlConection;
import Modelos.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DProveedor {

    private SqlConection conexion = new SqlConection();

    public void insert(Proveedor p) throws Exception {

        String sql = "INSERT INTO dbo.Proveedor "
                + "(nombre, telefono, domicilio, mail, cuit, rubro, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getDomicilio());
            ps.setString(4, p.getMail());
            ps.setString(5, p.getCuit());
            ps.setString(6, p.getRubro());
            ps.setString(7, p.getEstado());

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new Exception("No se insertó ningún proveedor");
            }
        }
    }

    public boolean existeCuit(String cuit) throws Exception {

        String sql = "SELECT COUNT(*) "
                + "FROM dbo.Proveedor "
                + "WHERE cuit = ?";

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cuit);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }

                return false;
            }
        }
    }
    
    public ArrayList<Proveedor> listar() throws Exception {

        ArrayList<Proveedor> proveedores = new ArrayList<>();

        String sql = "SELECT idProveedor, nombre, telefono, domicilio, mail, "
                + "cuit, rubro, estado, fechaModificacion "
                + "FROM dbo.Proveedor";

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Proveedor proveedor = new Proveedor();

                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setDomicilio(rs.getString("domicilio"));
                proveedor.setMail(rs.getString("mail"));
                proveedor.setCuit(rs.getString("cuit"));
                proveedor.setRubro(rs.getString("rubro"));
                proveedor.setEstado(rs.getString("estado"));
                proveedor.setFechaModificacion(rs.getTimestamp("fechaModificacion"));

                proveedores.add(proveedor);
            }
        }

        return proveedores;
    }
    
    public ArrayList<Proveedor> buscarPorNombre(String nombre) throws Exception {

        ArrayList<Proveedor> proveedores = new ArrayList<>();

        String sql = "SELECT idProveedor, nombre, telefono, domicilio, mail, "
                + "cuit, rubro, estado, fechaModificacion "
                + "FROM dbo.Proveedor "
                + "WHERE nombre LIKE ?";

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Proveedor proveedor = new Proveedor();

                    proveedor.setIdProveedor(rs.getInt("idProveedor"));
                    proveedor.setNombre(rs.getString("nombre"));
                    proveedor.setTelefono(rs.getString("telefono"));
                    proveedor.setDomicilio(rs.getString("domicilio"));
                    proveedor.setMail(rs.getString("mail"));
                    proveedor.setCuit(rs.getString("cuit"));
                    proveedor.setRubro(rs.getString("rubro"));
                    proveedor.setEstado(rs.getString("estado"));
                    proveedor.setFechaModificacion(
                            rs.getTimestamp("fechaModificacion")
                    );

                    proveedores.add(proveedor);
                }
            }
        }

        return proveedores;
    }
    
    public ArrayList<Proveedor> buscar(String nombre, String rubro, String estado) throws Exception {

        ArrayList<Proveedor> proveedores = new ArrayList<>();

        String sql = "SELECT idProveedor, nombre, telefono, domicilio, mail, "
                + "cuit, rubro, estado, fechaModificacion "
                + "FROM dbo.Proveedor "
                + "WHERE (nombre LIKE ? OR cuit LIKE ?) "
                + "AND (rubro LIKE ? OR (? = '' AND rubro IS NULL)) ";

        if (!estado.equalsIgnoreCase("Todos")) {
            sql += "AND estado = ? ";
        }

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // El mismo texto busca tanto por Razón Social como por CUIT
            ps.setString(1, "%" + nombre + "%");
            ps.setString(2, "%" + nombre + "%");

            // Filtro por rubro
            ps.setString(3, "%" + rubro + "%");
            ps.setString(4, rubro);

            // Filtro por estado, salvo que esté seleccionado "Todos"
            if (!estado.equalsIgnoreCase("Todos")) {
                ps.setString(5, estado);
            }

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Proveedor proveedor = new Proveedor();

                    proveedor.setIdProveedor(rs.getInt("idProveedor"));
                    proveedor.setNombre(rs.getString("nombre"));
                    proveedor.setTelefono(rs.getString("telefono"));
                    proveedor.setDomicilio(rs.getString("domicilio"));
                    proveedor.setMail(rs.getString("mail"));
                    proveedor.setCuit(rs.getString("cuit"));
                    proveedor.setRubro(rs.getString("rubro"));
                    proveedor.setEstado(rs.getString("estado"));
                    proveedor.setFechaModificacion(
                            rs.getTimestamp("fechaModificacion")
                    );

                    proveedores.add(proveedor);
                }
            }
        }

        return proveedores;
    }
    
}