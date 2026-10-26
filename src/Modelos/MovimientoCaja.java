package Modelos;

import java.math.BigDecimal;
import java.util.Date;

public class MovimientoCaja {

    private int idMovimientoCaja;
    private String tipoMovimiento;
    private BigDecimal monto;
    private String motivo;
    private Date fecha;
    private int idUsuarioRegistro;
    private Integer idVenta;
    private Integer idMetodoPago;

    public MovimientoCaja() {
    }

    public MovimientoCaja(int idMovimientoCaja, String tipoMovimiento,
            BigDecimal monto, String motivo, Date fecha, int idUsuarioRegistro,
            Integer idVenta, Integer idMetodoPago) {
        this.idMovimientoCaja = idMovimientoCaja;
        this.tipoMovimiento = tipoMovimiento;
        this.monto = monto;
        this.motivo = motivo;
        this.fecha = fecha;
        this.idUsuarioRegistro = idUsuarioRegistro;
        this.idVenta = idVenta;
        this.idMetodoPago = idMetodoPago;
    }

    public int getIdMovimientoCaja() {
        return idMovimientoCaja;
    }

    public void setIdMovimientoCaja(int idMovimientoCaja) {
        this.idMovimientoCaja = idMovimientoCaja;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(int idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

}
