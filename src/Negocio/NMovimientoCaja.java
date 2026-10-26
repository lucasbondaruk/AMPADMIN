package Negocio;

import Datos.DMovimientoCaja;
import Modelos.MovimientoCaja;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class NMovimientoCaja {

    private final DMovimientoCaja datos = new DMovimientoCaja();

    public boolean registrar(MovimientoCaja movimiento) throws SQLException {
        if (movimiento == null) {
            throw new IllegalArgumentException("El movimiento es obligatorio.");
        }
        String tipo = movimiento.getTipoMovimiento();
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar el tipo de movimiento.");
        }
        tipo = tipo.trim();
        if ("Ingreso".equalsIgnoreCase(tipo)) {
            tipo = "Ingreso";
        } else if ("Egreso".equalsIgnoreCase(tipo)) {
            tipo = "Egreso";
        } else {
            throw new IllegalArgumentException("El tipo debe ser Ingreso o Egreso.");
        }
        if (movimiento.getMonto() == null
                || movimiento.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto es obligatorio y debe ser mayor a cero.");
        }
        if (movimiento.getMotivo() == null || movimiento.getMotivo().trim().isEmpty()) {
            throw new IllegalArgumentException("El motivo es obligatorio.");
        }
        if (movimiento.getIdUsuarioRegistro() <= 0) {
            throw new IllegalArgumentException("El usuario de registro no es valido.");
        }
        movimiento.setTipoMovimiento(tipo);
        movimiento.setMotivo(movimiento.getMotivo().trim());
        // AFG-5 actual registra exclusivamente movimientos manuales.
        movimiento.setIdVenta(null);
        movimiento.setIdMetodoPago(null);
        return datos.insertar(movimiento);
    }

    public List<MovimientoCaja> listarPorFechas(LocalDate desde, LocalDate hasta)
            throws SQLException {
        return datos.listarPorFechas(desde, hasta);
    }

    public MovimientoCaja buscarPorId(int idMovimientoCaja) throws SQLException {
        return datos.buscarPorId(idMovimientoCaja);
    }
}
