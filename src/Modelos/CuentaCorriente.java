package Modelos;

import java.util.Date;

public class CuentaCorriente {

    private int id;
    private String razonSocial;
    private String telefono;
    private String cuit;
    private Date fchaIngreso;
    private String domicilio;
    private String mail;
    private String tipo;
    private String estado;

    public CuentaCorriente(String razonSocial, String telefono, String cuit, Date fchaIngreso, String domicilio, String mail, String tipo) {
        this.razonSocial = razonSocial;
        this.telefono = telefono;
        this.cuit = cuit;
        this.fchaIngreso = fchaIngreso;
        this.domicilio = domicilio;
        this.mail = mail;
        this.tipo = tipo;
    }

    public CuentaCorriente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Date getfchaIngreso() {
        return fchaIngreso;
    }

    public void setfchaIngreso(Date fchaIngreso) {
        this.fchaIngreso = fchaIngreso;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getEstado() {
    return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
