package Modelos;

public class Producto {

    private int idProducto;
    private int codigo;
    private String descripcion;
    private float precioUnitario;
    private int idRubroProducto;
    private int idIVA;
    private int idStockProducto;
    private int idTipoProducto;
    private int idProveedor;

    public Producto() {
    }

    public Producto(int idProducto, int codigo, String descripcion, float precioUnitario, int idRubroProducto, int idIVA, int idStockProducto, int idTipoProducto, int idProveedor) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.idRubroProducto = idRubroProducto;
        this.idIVA = idIVA;
        this.idStockProducto = idStockProducto;
        this.idTipoProducto = idTipoProducto;
        this.idProveedor = idProveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getIdRubroProducto() {
        return idRubroProducto;
    }

    public void setIdRubroProducto(int idRubroProducto) {
        this.idRubroProducto = idRubroProducto;
    }

    public int getIdIVA() {
        return idIVA;
    }

    public void setIdIVA(int idIVA) {
        this.idIVA = idIVA;
    }

    public int getIdStockProducto() {
        return idStockProducto;
    }

    public void setIdStockProducto(int idStockProducto) {
        this.idStockProducto = idStockProducto;
    }

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

}
