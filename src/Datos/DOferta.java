/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexiones.SqlConection;
import Modelos.Oferta;
import Modelos.Producto;
import Modelos.RubroProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Tomas Alvarez
 */
public class DOferta {

    private SqlConection conexion = new SqlConection();

    public boolean insertar(Oferta o) {
        String sql = "INSERT INTO Oferta "
                + "(alcance, idProducto, idRubroProducto, tipoDescuento, descuentoPorcentaje, precioPromocional, fechaInicio, fechaFin, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, o.getAlcance());

            if (o.getProducto() != null && o.getProducto().getIdProducto() > 0) {
                ps.setInt(2, o.getProducto().getIdProducto());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            if (o.getRubroProducto() != null && o.getRubroProducto().getIdRubroProducto() > 0) {
                ps.setInt(3, o.getRubroProducto().getIdRubroProducto());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.setString(4, o.getTipoDescuento());
            ps.setDouble(5, o.getDescuentoPorcentaje());
            ps.setDouble(6, o.getPrecioPromocional());
            ps.setTimestamp(7, new Timestamp(o.getFechaInicio().getTime()));
            ps.setTimestamp(8, new Timestamp(o.getFechaFin().getTime()));
            ps.setBoolean(9, o.isEstado());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificar(Oferta o) {
        String sql = "UPDATE Oferta SET "
                + "alcance = ?, idProducto = ?, idRubroProducto = ?, tipoDescuento = ?, "
                + "descuentoPorcentaje = ?, precioPromocional = ?, fechaInicio = ?, fechaFin = ?, estado = ? "
                + "WHERE idOferta = ?";

        try (Connection cn = conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, o.getAlcance());

            if (o.getProducto() != null && o.getProducto().getIdProducto() > 0) {
                ps.setInt(2, o.getProducto().getIdProducto());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            if (o.getRubroProducto() != null && o.getRubroProducto().getIdRubroProducto() > 0) {
                ps.setInt(3, o.getRubroProducto().getIdRubroProducto());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.setString(4, o.getTipoDescuento());
            ps.setDouble(5, o.getDescuentoPorcentaje());
            ps.setDouble(6, o.getPrecioPromocional());
            ps.setTimestamp(7, new Timestamp(o.getFechaInicio().getTime()));
            ps.setTimestamp(8, new Timestamp(o.getFechaFin().getTime()));
            ps.setBoolean(9, o.isEstado());
            ps.setInt(10, o.getIdOferta());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idOferta) {
        String sql = "DELETE FROM Oferta WHERE idOferta = ?";

        try (Connection cn = conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idOferta);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Oferta> listar() {
        List<Oferta> lista = new ArrayList<>();
        String sql = "SELECT o.idOferta, o.alcance, o.idProducto, o.idRubroProducto, "
                + "o.tipoDescuento, o.descuentoPorcentaje, o.precioPromocional, "
                + "o.fechaInicio, o.fechaFin, o.estado, "
                + "p.descripcion AS nomProd, p.precioUnitario AS precioOrig, "
                + "r.descripcion AS nomRubro "
                + "FROM Oferta o "
                + "LEFT JOIN Producto p ON o.idProducto = p.idProducto "
                + "LEFT JOIN RubroProducto r ON o.idRubroProducto = r.idRubroProducto "
                + "ORDER BY o.idOferta DESC";
        try (Connection cn = conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Oferta o = new Oferta();
                o.setIdOferta(rs.getInt("idOferta"));
                o.setAlcance(rs.getString("alcance"));

                int idProd = rs.getInt("idProducto");
                boolean prodNulo = rs.wasNull();      // pegado a su getInt
                if (!prodNulo) {
                    Producto p = new Producto();
                    p.setIdProducto(idProd);
                    p.setDescripcion(rs.getString("nomProd"));
                    p.setPrecioUnitario(rs.getFloat("precioOrig"));
                    o.setProducto(p);
                }

                int idRub = rs.getInt("idRubroProducto");
                boolean rubNulo = rs.wasNull();       // pegado a su getInt
                if (!rubNulo) {
                    RubroProducto rp = new RubroProducto();
                    rp.setIdRubroProducto(idRub);
                    rp.setDescripcion(rs.getString("nomRubro"));
                    o.setRubroProducto(rp);
                }

                o.setTipoDescuento(rs.getString("tipoDescuento"));
                o.setDescuentoPorcentaje(rs.getDouble("descuentoPorcentaje"));
                o.setPrecioPromocional(rs.getDouble("precioPromocional"));
                o.setFechaInicio(rs.getTimestamp("fechaInicio"));
                o.setFechaFin(rs.getTimestamp("fechaFin"));
                o.setEstado(rs.getBoolean("estado"));
                lista.add(o);
            }
        } catch (Exception e) {
            System.err.println("Error en DOferta.listar: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public String buscarColision(int idProducto, int idRubro, java.sql.Date inicio, java.sql.Date fin, int idOfertaExcluir) {
        String sqlOferta = "SELECT TOP 1 o.idOferta, o.alcance, o.descuentoPorcentaje, o.fechaInicio, o.fechaFin, "
                + "p.descripcion AS producto, r.descripcion AS rubro "
                + "FROM Oferta o "
                + "LEFT JOIN Producto p ON p.idProducto = o.idProducto "
                + "LEFT JOIN RubroProducto r ON r.idRubroProducto = o.idRubroProducto "
                + "WHERE o.estado = 1 AND o.idOferta <> ? "
                + "AND CAST(o.fechaInicio AS date) <= ? AND CAST(o.fechaFin AS date) >= ? "
                + "AND ((o.alcance = 'PRODUCTO' AND o.idProducto = ?) "
                + "  OR (o.alcance = 'RUBRO' AND o.idRubroProducto = ?))";

        String sqlCombo = "SELECT TOP 1 pr.nombre "
                + "FROM PromocionComboDetalle d "
                + "JOIN PromocionCombo c ON c.idCombo = d.idCombo "
                + "JOIN Promocion pr ON pr.idPromocion = c.idPromocion "
                + "WHERE pr.estado = 1 AND d.idProducto = ?";

        try (Connection cn = conexion.getConexion()) {

            try (PreparedStatement ps = cn.prepareStatement(sqlOferta)) {
                ps.setInt(1, idOfertaExcluir);
                ps.setDate(2, fin);      // la existente debe empezar antes de que termine la nueva
                ps.setDate(3, inicio);   // y terminar después de que empiece la nueva
                ps.setInt(4, idProducto);
                ps.setInt(5, idRubro);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                        String sobre = "PRODUCTO".equals(rs.getString("alcance"))
                                ? "el producto " + rs.getString("producto")
                                : "el rubro " + rs.getString("rubro");
                        return "El producto choca con la oferta #" + rs.getInt("idOferta")
                                + " (" + rs.getDouble("descuentoPorcentaje") + "% sobre " + sobre
                                + ", del " + sdf.format(rs.getTimestamp("fechaInicio"))
                                + " al " + sdf.format(rs.getTimestamp("fechaFin")) + ").";
                    }
                }
            }

            try (PreparedStatement ps = cn.prepareStatement(sqlCombo)) {
                ps.setInt(1, idProducto);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return "El producto ya forma parte del combo activo \"" + rs.getString("nombre") + "\".";
                    }
                }
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return "No se pudo verificar la colisión con otras promociones: " + e.getMessage();
        }
    }

    /**
     * Pasa a estado = 0 las ofertas activas cuya fecha fin ya pasó. Devuelve
     * cuántas cambió.
     */
    public int marcarVencidas() {
        String sql = "UPDATE Oferta SET estado = 0 WHERE estado = 1 AND fechaFin < ?";
        try (Connection cn = conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
