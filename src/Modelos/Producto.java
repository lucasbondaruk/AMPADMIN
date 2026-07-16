package Modelos;


public class Producto {

    private int idProducto;
    private int codigo;
    private String descripcion;
    private float precioUnitario;
    private RubroProducto rubroProducto;
    private IVA iva;
    private StockProducto idStockProducto;
    private TipoProducto tipoProducto;
    private Proveedor proveedor;

    public Producto() {
    }

    public Producto(int idProducto, int codigo, String descripcion,
            float precioUnitario, RubroProducto rubroProducto,
            IVA iva, StockProducto idStockProducto,
            TipoProducto tipoProducto, Proveedor proveedor) {

        this.idProducto = idProducto;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.rubroProducto = rubroProducto;
        this.iva = iva;
        this.idStockProducto = idStockProducto;
        this.tipoProducto = tipoProducto;
        this.proveedor = proveedor;
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

    public RubroProducto getRubroProducto() {
        return rubroProducto;
    }

    public void setRubroProducto(RubroProducto rubroProducto) {
        this.rubroProducto = rubroProducto;
    }

    public IVA getIva() {
        return iva;
    }

    public void setIva(IVA iva) {
        this.iva = iva;
    }

    public StockProducto getIdStockProducto() {
        return idStockProducto;
    }

    public void setIdStockProducto(StockProducto idStockProducto) {
        this.idStockProducto = idStockProducto;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
