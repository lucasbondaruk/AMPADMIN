package Modelos;

import java.util.Date;

public class Cliente {

    private int idCliente;
    private String razonSocial;
    private String telefono;
    private String cuit;
    private Date fchaIngreso;
    private String domicilio;
    private String mail;
    private String tipoCliente;
    private String estado;
    private String tipoPersona;
    private int idCondIva;

    public Cliente(String razonSocial, String telefono, String cuit, Date fchaIngreso, String domicilio, String mail, String tipoCliente, String estado, String tipoPersona, int idCondIva) {
        this.razonSocial = razonSocial;
        this.telefono = telefono;
        this.cuit = cuit;
        this.fchaIngreso = fchaIngreso;
        this.domicilio = domicilio;
        this.mail = mail;
        this.tipoCliente = tipoCliente;
        this.estado = estado;
        this.tipoPersona = tipoPersona;
        this.idCondIva = idCondIva;
    }

    public Cliente() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public int getIdCondIva() {
        return idCondIva;
    }

    public void setIdCondIva(int idCondIva) {
        this.idCondIva = idCondIva;
    }

    
}
