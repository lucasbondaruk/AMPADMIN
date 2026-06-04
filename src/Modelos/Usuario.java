package Modelos;

public class Usuario {

    private int id;
    private String usuario;
    private String contrasenia;
    private String estado;
    private String telefono;
    private String domicilio;
    private String mail;
    private int idRol;

    public Usuario() {
    }

    public Usuario(String usuario, String contrasenia, String estado, String telefono, String domicilio, String mail, int idRol) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.mail = mail;
        this.idRol = idRol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
}