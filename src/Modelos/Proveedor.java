package Modelos;

import java.sql.Timestamp;

public class Proveedor {

    private int idProveedor;
    private String nombre;
    private String telefono;
    private String domicilio;
    private String mail;
    private String cuit;
    private String rubro;
    private String estado;
    private Timestamp fechaModificacion;

    // Constructor vacío
    public Proveedor() {
    }

    // Constructor completo
    // Lo vamos a utilizar principalmente al recuperar proveedores de la BD.
    public Proveedor(int idProveedor, String nombre, String telefono,
            String domicilio, String mail, String cuit, String rubro,
            String estado, Timestamp fechaModificacion) {

        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.mail = mail;
        this.cuit = cuit;
        this.rubro = rubro;
        this.estado = estado;
        this.fechaModificacion = fechaModificacion;
    }

    // Constructor para registrar un nuevo proveedor
    // No recibe ID porque lo genera la BD.
    // Tampoco recibe fechaModificacion porque todavía no fue modificado.
    public Proveedor(String nombre, String telefono, String domicilio,
            String mail, String cuit, String rubro, String estado) {

        this.nombre = nombre;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.mail = mail;
        this.cuit = cuit;
        this.rubro = rubro;
        this.estado = estado;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
