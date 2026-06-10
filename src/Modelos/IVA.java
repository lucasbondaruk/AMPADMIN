package Modelos;

public class IVA {

    private int idIVA;
    private String descripcion;
    private float porcentaje;

    public IVA() {

    }

    public IVA(int idIVA, String descripcion, float porcentaje) {
        this.idIVA = idIVA;
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
    }

    public int getIdIVA() {
        return idIVA;
    }

    public void setIdIVA(int idIVA) {
        this.idIVA = idIVA;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

}
