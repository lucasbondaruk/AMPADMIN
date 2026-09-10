package Modelos;


public class Producto {

    private int idProducto;
    private int codigo;
    private String descripcion;
    private float precioUnitario;
    private RubroProducto idRubroProducto;
    private IVA idIVA;
    private StockProducto idStockProducto;
    private TipoProducto idTipoProducto;
    private Proveedor idProveedor;

    public Producto() {
    }

    public Producto(int idProducto, int codigo, String descripcion,
            float precioUnitario, RubroProducto idRubroProducto,
            IVA idIVA, StockProducto idStockProducto,
            TipoProducto idTipoProducto, Proveedor idProveedor) {

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

    public RubroProducto getIdRubroProducto() {
        return idRubroProducto;
    }

    public void setIdRubroProducto(RubroProducto idRubroProducto) {
        this.idRubroProducto = idRubroProducto;
    }

    public IVA getIdIVA() {
        return idIVA;
    }

    public void setIdIVA(IVA idIVA) {
        this.idIVA = idIVA;
    }

    public StockProducto getIdStockProducto() {
        return idStockProducto;
    }

    public void setIdStockProducto(StockProducto idStockProducto) {
        this.idStockProducto = idStockProducto;
    }

    public TipoProducto getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(TipoProducto idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }
}
