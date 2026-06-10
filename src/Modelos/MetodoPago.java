/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author Tomas Alvarez
 */
public class MetodoPago {
    private int idMetodoPago;
    private String descripcion;
    private boolean activo;

    public MetodoPago() {
    }

    public MetodoPago(int idMetodoPago, String descripcion, boolean activo) {
        this.idMetodoPago = idMetodoPago;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Sobrescribimos el toString para que el JComboBox de NetBeans muestre el texto de la descripción
    @Override
    public String toString() {
        return this.descripcion;
    }
}
