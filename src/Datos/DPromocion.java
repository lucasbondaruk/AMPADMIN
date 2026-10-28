/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexiones.SqlConection;
import Modelos.MetodoPago;
import Modelos.Promocion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Modelos.PromocionCombo;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tomas Alvarez
 */
public class DPromocion {

    private SqlConection conexion = new SqlConection();

    public boolean insertar(Promocion p) {
        String sql = "INSERT INTO Promocion "
                + "(nombre, tipoRegla, idMetodoPago, montoMinimo, descuentoPorcentaje, condicion, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipoRegla());

            if (p.getMetodoPago() != null && p.getMetodoPago().getIdMetodoPago() > 0) {
                ps.setInt(3, p.getMetodoPago().getIdMetodoPago());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.setDouble(4, p.getMontoMinimo());
            ps.setDouble(5, p.getDescuentoPorcentaje());
            ps.setString(6, p.getCondicion());
            ps.setBoolean(7, p.isEstado());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificar(Promocion p) {
        String sql = "UPDATE Promocion SET "
                + "nombre = ?, tipoRegla = ?, idMetodoPago = ?, montoMinimo = ?, "
                + "descuentoPorcentaje = ?, condicion = ?, estado = ? "
                + "WHERE idPromocion = ?";

        try (Connection cn = conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipoRegla());

            if (p.getMetodoPago() != null && p.getMetodoPago().getIdMetodoPago() > 0) {
                ps.setInt(3, p.getMetodoPago().getIdMetodoPago());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.setDouble(4, p.getMontoMinimo());
            ps.setDouble(5, p.getDescuentoPorcentaje());
            ps.setString(6, p.getCondicion());
            ps.setBoolean(7, p.isEstado());
            ps.setInt(8, p.getIdPromocion());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cambiarEstado(int idPromocion, boolean nuevoEstado) {
        String sql = "UPDATE Promocion SET estado = ? WHERE idPromocion = ?";

        try (Connection cn = conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setBoolean(1, nuevoEstado);
            ps.setInt(2, idPromocion);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idPromocion) {
        Connection cn = null;
        try {
            cn = conexion.getConexion();
            cn.setAutoCommit(false);
            ejecutarPorId(cn, "DELETE FROM PromocionComboDetalle WHERE idCombo IN "
                    + "(SELECT idCombo FROM PromocionCombo WHERE idPromocion = ?)", idPromocion);
            ejecutarPorId(cn, "DELETE FROM PromocionCombo WHERE idPromocion = ?", idPromocion);
            int filas = ejecutarPorId(cn, "DELETE FROM Promocion WHERE idPromocion = ?", idPromocion);
            cn.commit();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            if (cn != null) {
                try {
                    cn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (cn != null) {
                try {
                    cn.setAutoCommit(true);
                    cn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public List<Promocion> listar() {
        List<Promocion> lista = new ArrayList<>();
        // Mapeo exacto de las columnas de tu BD: idMetodoPago -> descripcion
        String sql = "SELECT p.idPromocion, p.nombre, p.tipoRegla, p.idMetodoPago, "
                + "p.montoMinimo, p.descuentoPorcentaje, p.condicion, p.estado, "
                + "mp.descripcion AS nomMetodoPago "
                + "FROM Promocion p "
                + "LEFT JOIN MetodoPago mp ON p.idMetodoPago = mp.idMetodoPago "
                + "ORDER BY p.idPromocion DESC";

        try (Connection cn = conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Promocion p = new Promocion();
                p.setIdPromocion(rs.getInt("idPromocion"));
                p.setNombre(rs.getString("nombre"));
                p.setTipoRegla(rs.getString("tipoRegla"));

                int idMP = rs.getInt("idMetodoPago");
                if (!rs.wasNull()) {
                    MetodoPago mp = new MetodoPago();
                    mp.setIdMetodoPago(idMP);
                    mp.setDescripcion(rs.getString("nomMetodoPago"));
                    p.setMetodoPago(mp);
                }

                p.setMontoMinimo(rs.getDouble("montoMinimo"));
                p.setDescuentoPorcentaje(rs.getDouble("descuentoPorcentaje"));
                p.setCondicion(rs.getString("condicion"));
                p.setEstado(rs.getBoolean("estado"));

                lista.add(p);
            }

        } catch (Exception e) {
            System.err.println("Error en DPromocion.listar: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public List<MetodoPago> listarMetodosPago() {
        List<MetodoPago> lista = new ArrayList<>();
        String sql = "SELECT idMetodoPago, descripcion, activo FROM MetodoPago WHERE activo = 1";

        try (Connection cn = conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MetodoPago mp = new MetodoPago();
                mp.setIdMetodoPago(rs.getInt("idMetodoPago"));
                mp.setDescripcion(rs.getString("descripcion"));
                mp.setActivo(rs.getBoolean("activo"));
                lista.add(mp);
            }
        } catch (Exception e) {
            System.err.println("Error al listar MetodoPago: " + e.getMessage());
        }
        return lista;
    }
    private final DPromocionCombo dCombo = new DPromocionCombo();

    /**
     * Inserta la promoción con una conexión externa y devuelve el ID generado.
     */
    public int insertarConId(Connection cn, Promocion p) throws SQLException {
        String sql = "INSERT INTO Promocion "
                + "(nombre, tipoRegla, idMetodoPago, montoMinimo, descuentoPorcentaje, condicion, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipoRegla());
            if (p.getMetodoPago() != null && p.getMetodoPago().getIdMetodoPago() > 0) {
                ps.setInt(3, p.getMetodoPago().getIdMetodoPago());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }
            ps.setDouble(4, p.getMontoMinimo());
            ps.setDouble(5, p.getDescuentoPorcentaje());
            ps.setString(6, p.getCondicion());
            ps.setBoolean(7, p.isEstado());
            if (ps.executeUpdate() == 0) {
                throw new SQLException("No se insertó la promoción.");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("No se obtuvo el ID de la promoción.");
    }

    /**
     * Promocion + PromocionCombo + PromocionComboDetalle en una sola
     * transacción.
     */
    public boolean insertarComboCompleto(Promocion p, PromocionCombo combo) {
        Connection cn = null;
        try {
            cn = conexion.getConexion();
            cn.setAutoCommit(false);

            int idPromocion = insertarConId(cn, p);
            combo.setIdPromocion(idPromocion);

            int idCombo = dCombo.insertar(cn, combo);
            dCombo.insertarDetalles(cn, idCombo, combo.getDetalles());

            cn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (cn != null) {
                try {
                    cn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (cn != null) {
                try {
                    cn.setAutoCommit(true);
                    cn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private int ejecutarPorId(Connection cn, String sql, int id) throws SQLException {
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    /**
     * Promocion + PromocionCombo + detalles, en una sola transacción.
     */
    public boolean modificarComboCompleto(Promocion p, PromocionCombo combo) {
        String sql = "UPDATE Promocion SET nombre = ?, tipoRegla = ?, idMetodoPago = ?, montoMinimo = ?, "
                + "descuentoPorcentaje = ?, condicion = ?, estado = ? WHERE idPromocion = ?";
        Connection cn = null;
        try {
            cn = conexion.getConexion();
            cn.setAutoCommit(false);

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, p.getNombre());
                ps.setString(2, p.getTipoRegla());
                ps.setNull(3, java.sql.Types.INTEGER);
                ps.setDouble(4, p.getMontoMinimo());
                ps.setDouble(5, p.getDescuentoPorcentaje());
                ps.setString(6, p.getCondicion());
                ps.setBoolean(7, p.isEstado());
                ps.setInt(8, p.getIdPromocion());
                if (ps.executeUpdate() == 0) {
                    throw new SQLException("No se actualizó la promoción.");
                }
            }
            combo.setIdPromocion(p.getIdPromocion());
            int idCombo = dCombo.actualizar(cn, combo);
            dCombo.borrarDetalles(cn, idCombo);
            dCombo.insertarDetalles(cn, idCombo, combo.getDetalles());

            cn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (cn != null) {
                try {
                    cn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (cn != null) {
                try {
                    cn.setAutoCommit(true);
                    cn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * null = sin choque. Revisa ofertas vigentes/programadas y otros combos
     * activos.
     */
    public String buscarColisionProductos(List<Integer> ids, int idPromocionExcluir) {
        if (ids == null || ids.isEmpty()) {
            return null;
        }
        StringBuilder in = new StringBuilder();
        for (int i = 0; i < ids.size(); i++) {
            in.append(i == 0 ? "?" : ",?");
        }
        String sqlOferta = "SELECT TOP 1 p.descripcion AS producto, o.idOferta "
                + "FROM Producto p JOIN Oferta o ON o.estado = 1 "
                + "AND CAST(o.fechaFin AS date) >= CAST(GETDATE() AS date) "
                + "AND ((o.alcance = 'PRODUCTO' AND o.idProducto = p.idProducto) "
                + "  OR (o.alcance = 'RUBRO' AND o.idRubroProducto = p.idRubroProducto)) "
                + "WHERE p.idProducto IN (" + in + ")";
        String sqlCombo = "SELECT TOP 1 p.descripcion AS producto, pr.nombre "
                + "FROM PromocionComboDetalle d "
                + "JOIN PromocionCombo c ON c.idCombo = d.idCombo "
                + "JOIN Promocion pr ON pr.idPromocion = c.idPromocion "
                + "JOIN Producto p ON p.idProducto = d.idProducto "
                + "WHERE pr.estado = 1 AND pr.idPromocion <> ? AND d.idProducto IN (" + in + ")";
        try (Connection cn = conexion.getConexion()) {
            try (PreparedStatement ps = cn.prepareStatement(sqlOferta)) {
                int k = 1;
                for (Integer id : ids) {
                    ps.setInt(k++, id);
                }
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return "El producto \"" + rs.getString("producto") + "\" ya está en la oferta activa #"
                                + rs.getInt("idOferta") + ".";
                    }
                }
            }
            try (PreparedStatement ps = cn.prepareStatement(sqlCombo)) {
                ps.setInt(1, idPromocionExcluir);
                int k = 2;
                for (Integer id : ids) {
                    ps.setInt(k++, id);
                }
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return "El producto \"" + rs.getString("producto") + "\" ya está en el combo activo \""
                                + rs.getString("nombre") + "\".";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "No se pudo verificar la colisión: " + e.getMessage();
        }
        return null;
    }
}
