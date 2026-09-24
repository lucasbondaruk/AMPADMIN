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
    private String tipoCondicion;
    private double descuento;
    private boolean estado;

    public Promocion() {
    }

    public Promocion(int idPromocion, String nombre, String tipoCondicion, double descuento, boolean estado) {
        this.idPromocion = idPromocion;
        this.nombre = nombre;
        this.tipoCondicion = tipoCondicion;
        this.descuento = descuento;
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

    public String getTipoCondicion() {
        return tipoCondicion;
    }

    public void setTipoCondicion(String tipoCondicion) {
        this.tipoCondicion = tipoCondicion;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
