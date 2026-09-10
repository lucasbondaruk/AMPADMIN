
package Modelos;


public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String telefono;
    private String domicilio;
    private String mail;

    
    public Proveedor(){
        
    }
    public Proveedor(int idProveedor, String nombre, String telefono, String domicilio, String mail) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.mail = mail;
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
    
    @Override
    public String toString() {
        return this.nombre;
    }
    
    
}
