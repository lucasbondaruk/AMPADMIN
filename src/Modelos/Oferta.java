/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.Date;

/**
 *
 * @author Tomas Alvarez
 */
public class Oferta {

    private int idOferta;
    private String alcance; // "PRODUCTO" o "RUBRO"
    private Producto producto; // Relación con Producto
    private RubroProducto rubroProducto; // Relación con RubroProducto
    private String tipoDescuento; // "PORCENTAJE" o "PRECIO_FIJO"
    private double descuentoPorcentaje;
    private double precioPromocional;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean estado; // true: Activa, false: Inactiva

    // Constructores
    public Oferta() {
    }

    public Oferta(int idOferta, String alcance, Producto producto, RubroProducto rubroProducto,
            String tipoDescuento, double descuentoPorcentaje, double precioPromocional,
            Date fechaInicio, Date fechaFin, boolean estado) {
        this.idOferta = idOferta;
        this.alcance = alcance;
        this.producto = producto;
        this.rubroProducto = rubroProducto;
        this.tipoDescuento = tipoDescuento;
        this.descuentoPorcentaje = descuentoPorcentaje;
        this.precioPromocional = precioPromocional;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public RubroProducto getRubroProducto() {
        return rubroProducto;
    }

    public void setRubroProducto(RubroProducto rubroProducto) {
        this.rubroProducto = rubroProducto;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public double getDescuentoPorcentaje() {
        return descuentoPorcentaje;
    }

    public void setDescuentoPorcentaje(double descuentoPorcentaje) {
        this.descuentoPorcentaje = descuentoPorcentaje;
    }

    public double getPrecioPromocional() {
        return precioPromocional;
    }

    public void setPrecioPromocional(double precioPromocional) {
        this.precioPromocional = precioPromocional;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Métodos utilitarios complementarios para compatibilidad de vistas
    public int getIdProducto() {
        return (producto != null) ? producto.getIdProducto() : 0;
    }

    public void setIdProducto(int idProducto) {
        if (this.producto == null) {
            this.producto = new Producto();
        }
        this.producto.setIdProducto(idProducto);
    }
}
