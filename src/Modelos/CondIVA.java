package Modelos;

public class CondIVA {

    private int idCondIva;
    private String descripcion;
    private boolean ivaDiscriminado;

    public CondIVA(String descripcion, boolean ivaDiscriminado) {
        this.descripcion = descripcion;
        this.ivaDiscriminado = ivaDiscriminado;
    }
    
    public CondIVA(){
        
    }

    public int getIdCondIva() {
        return idCondIva;
    }

    public void setIdCondIva(int idCondIva) {
        this.idCondIva = idCondIva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isIvaDiscriminado() {
        return ivaDiscriminado;
    }

    public void setIvaDiscriminado(boolean ivaDiscriminado) {
        this.ivaDiscriminado = ivaDiscriminado;
    }
    
    @Override
    public String toString(){
        return descripcion;
    }

}
