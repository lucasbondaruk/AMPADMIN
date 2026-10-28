/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author Tomas Alvarez
 */
public class PromocionComboDetalle {

    private int idDetalle;
    private int idCombo;
    private Producto producto;
    private int cantidad;

    public PromocionComboDetalle() {
    }

    public PromocionComboDetalle(int idDetalle, int idCombo, Producto producto, int cantidad) {
        this.idDetalle = idDetalle;
        this.idCombo = idCombo;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(int idCombo) {
        this.idCombo = idCombo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
