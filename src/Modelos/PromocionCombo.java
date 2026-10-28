/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomas Alvarez
 */
public class PromocionCombo {
    private int idCombo;
    private int idPromocion;
    private String nombreCombo;
    private double precioFinal;
    private List<PromocionComboDetalle> detalles;

    public PromocionCombo() {
        this.detalles = new ArrayList<>();
    }

    public PromocionCombo(int idCombo, int idPromocion, String nombreCombo, double precioFinal) {
        this.idCombo = idCombo;
        this.idPromocion = idPromocion;
        this.nombreCombo = nombreCombo;
        this.precioFinal = precioFinal;
        this.detalles = new ArrayList<>();
    }

    public int getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(int idCombo) {
        this.idCombo = idCombo;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombreCombo() {
        return nombreCombo;
    }

    public void setNombreCombo(String nombreCombo) {
        this.nombreCombo = nombreCombo;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public List<PromocionComboDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<PromocionComboDetalle> detalles) {
        this.detalles = detalles;
    }
}
