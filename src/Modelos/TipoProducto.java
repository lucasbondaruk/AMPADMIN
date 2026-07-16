
package Modelos;


public class TipoProducto {
    private int idTipoProducto;
    private String descripcion;
    private String unidad;
    private RubroProducto idRubroProducto;

    public TipoProducto(int idTipoProducto, String descripcion, String unidad, RubroProducto idRubroProducto) {
        this.idTipoProducto = idTipoProducto;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.idRubroProducto = idRubroProducto;
    }
    
    public TipoProducto(){
        
    }

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public RubroProducto getIdRubroProducto() {
        return idRubroProducto;
    }

    public void setIdRubroProducto(RubroProducto idRubroProducto) {
        this.idRubroProducto = idRubroProducto;
    }
    
    
    @Override
    public String toString(){
        return descripcion;
    }
    
}
