package Modelos;

public class RubroProducto {
    private int idRubroProducto;
    private String descripcion;

    public RubroProducto(int idRubroProducto, String descripcion) {
        this.idRubroProducto = idRubroProducto;
        this.descripcion = descripcion;
    }

    public RubroProducto() {
    }

    public int getIdRubroProducto() {
        return idRubroProducto;
    }

    public void setIdRubroProducto(int idRubroProducto) {
        this.idRubroProducto = idRubroProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        return descripcion; 
    }
}
