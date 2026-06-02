package Modelos;

public class MetodoPago {

    private int idMetodoPago;
    private String descripcion;
    private boolean activo;

    public MetodoPago(){
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
    
    @Override
    public String toString(){
        return descripcion;
    }

}
