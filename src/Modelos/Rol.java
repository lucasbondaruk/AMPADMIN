
package Modelos;

public class Rol {

    private int idRol;
    private String nomRol;
    private String descripcion;

    
    public Rol(){
    }
    
    public Rol(String nomRol, String descripcion) {
        this.nomRol = nomRol;
        this.descripcion = descripcion;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNomRol() {
        return nomRol;
    }

    public void setNomRol(String nomRol) {
        this.nomRol = nomRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        return nomRol;
    }

}

