package Modelos;

import java.util.Date;

public class Venta {

    private int idVenta;
    private int numVenta;
    private Date fecha;
    private float total;
    private int cuotas;
    private float interes;
    private String tipoComprobante;
    private int idMetodoPago;
    private int idIVA;
    private int idCliente;
    private int idVendedor;

  
    public Venta() {
    }

    
    public Venta(int idVenta, int numVenta, Date fecha, float total, int cuotas,
            float interes, String tipoComprobante, int idMetodoPago,
            int idIVA, int idCliente, int idVendedor) {
        this.idVenta = idVenta;
        this.numVenta = numVenta;
        this.fecha = fecha;
        this.total = total;
        this.cuotas = cuotas;
        this.interes = interes;
        this.tipoComprobante = tipoComprobante;
        this.idMetodoPago = idMetodoPago;
        this.idIVA = idIVA;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getNumVenta() {
        return numVenta;
    }

    public void setNumVenta(int numVenta) {
        this.numVenta = numVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public float getInteres() {
        return interes;
    }

    public void setInteres(float interes) {
        this.interes = interes;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public int getIdIVA() {
        return idIVA;
    }

    public void setIdIVA(int idIVA) {
        this.idIVA = idIVA;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }
}
