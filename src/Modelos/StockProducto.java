package Modelos;


public class StockProducto {
    private int idStockProducto;
    private int stockMin;
    private int stockMax;
    private int stockAct;

    public StockProducto(int idStockProducto, int stockMin, int stockMax, int stockAct) {
        this.idStockProducto = idStockProducto;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
        this.stockAct = stockAct;
    }

    public StockProducto() {
    }

    public int getIdStockProducto() {
        return idStockProducto;
    }

    public void setIdStockProducto(int idStockProducto) {
        this.idStockProducto = idStockProducto;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public int getStockMax() {
        return stockMax;
    }

    public void setStockMax(int stockMax) {
        this.stockMax = stockMax;
    }

    public int getStockAct() {
        return stockAct;
    }

    public void setStockAct(int stockAct) {
        this.stockAct = stockAct;
    }
    
}
