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
public class CobroCuentaCorriente {

    private int idCobroCuentaCorriente;
    private double total;
    private int totalCompras;
    private Date fechaPago;
    private int cuotas;
    private double interes;      // Usamos double para representar el float/monto de SQL
    private boolean descuento;
    private int codMp;       // Relación FK
    private int codCli;    // Relación FK

    public CobroCuentaCorriente() {
    }

    public CobroCuentaCorriente(int idCobroCuentaCorriente, double total, int totalCompras, Date fechaPago, int cuotas, double interes, boolean descuento, int codMp, int codCli) {
        this.idCobroCuentaCorriente = idCobroCuentaCorriente;
        this.total = total;
        this.totalCompras = totalCompras;
        this.fechaPago = fechaPago;
        this.cuotas = cuotas;
        this.interes = interes;
        this.descuento = descuento;
        this.codMp = codMp;
        this.codCli = codCli;
    }

    public int getIdCobroCuentaCorriente() {
        return idCobroCuentaCorriente;
    }

    public void setIdCobroCuentaCorriente(int idCobroCuentaCorriente) {
        this.idCobroCuentaCorriente = idCobroCuentaCorriente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(int totalCompras) {
        this.totalCompras = totalCompras;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public boolean isDescuento() {
        return descuento;
    }

    public void setDescuento(boolean descuento) {
        this.descuento = descuento;
    }

    public int getCodMp() {
        return codMp;
    }

    public void setCodMp(int codMp) {
        this.codMp = codMp;
    }

    public int getCodCli() {
        return codCli;
    }

    public void setCodCli(int codCli) {
        this.codCli = codCli;
    }

}
