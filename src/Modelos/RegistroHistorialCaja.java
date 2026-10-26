package Modelos;

import java.math.BigDecimal;
import java.util.Date;

/** Fila de consulta: no representa una nueva venta ni un movimiento persistible. */
public class RegistroHistorialCaja {
    public enum Origen { VENTA, MOVIMIENTO }

    private final Origen origen;
    private final int idOrigen;
    private final Date fecha;
    private final Integer numeroBoleta;
    private final String cliente;
    private final String tipo;
    private final Integer idMetodo;
    private final String metodo;
    private final BigDecimal monto;
    private final String responsable;
    private final String estado;
    private final String motivo;

    public RegistroHistorialCaja(Origen origen, int idOrigen, Date fecha,
            Integer numeroBoleta, String cliente, String tipo, Integer idMetodo,
            String metodo, BigDecimal monto, String responsable, String estado, String motivo) {
        this.origen = origen;
        this.idOrigen = idOrigen;
        this.fecha = fecha == null ? null : new Date(fecha.getTime());
        this.numeroBoleta = numeroBoleta;
        this.cliente = cliente;
        this.tipo = tipo;
        this.idMetodo = idMetodo;
        this.metodo = metodo;
        this.monto = monto;
        this.responsable = responsable;
        this.estado = estado;
        this.motivo = motivo;
    }

    public Origen getOrigen() { return origen; }
    public int getIdOrigen() { return idOrigen; }
    public Date getFecha() { return fecha == null ? null : new Date(fecha.getTime()); }
    public Integer getNumeroBoleta() { return numeroBoleta; }
    public String getCliente() { return cliente; }
    public String getTipo() { return tipo; }
    public Integer getIdMetodo() { return idMetodo; }
    public String getMetodo() { return metodo; }
    public BigDecimal getMonto() { return monto; }
    public String getResponsable() { return responsable; }
    public String getEstado() { return estado; }
    public String getMotivo() { return motivo; }
}
