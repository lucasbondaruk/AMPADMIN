/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author Tomas Alvarez
 */
public class Promocion {

    private int idPromocion;
    private String nombre;
    private String tipoRegla; // "MEDIO_PAGO", "MONTO_MINIMO", "COMBO"
    private MetodoPago metodoPago; // Opcional, si aplica
    private double montoMinimo;
    private double descuentoPorcentaje;
    private String condicion;
    private boolean estado;

    public Promocion() {
    }

    public Promocion(int idPromocion, String nombre, String condicion, double descuentoPorcentaje, boolean estado) {
        this.idPromocion = idPromocion;
        this.nombre = nombre;
        this.condicion = condicion;
        this.descuentoPorcentaje = descuentoPorcentaje;
        this.estado = estado;
    }

    public Promocion(int idPromocion, String nombre, String tipoRegla, MetodoPago metodoPago,
            double montoMinimo, double descuentoPorcentaje, String condicion, boolean estado) {
        this.idPromocion = idPromocion;
        this.nombre = nombre;
        this.tipoRegla = tipoRegla;
        this.metodoPago = metodoPago;
        this.montoMinimo = montoMinimo;
        this.descuentoPorcentaje = descuentoPorcentaje;
        this.condicion = condicion;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoRegla() {
        return tipoRegla;
    }

    public void setTipoRegla(String tipoRegla) {
        this.tipoRegla = tipoRegla;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(double montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public double getDescuentoPorcentaje() {
        return descuentoPorcentaje;
    }

    public void setDescuentoPorcentaje(double descuentoPorcentaje) {
        this.descuentoPorcentaje = descuentoPorcentaje;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
