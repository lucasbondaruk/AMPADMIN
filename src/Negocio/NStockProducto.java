package Negocio;

import Datos.DStockProducto;
import Modelos.StockProducto;

public class NStockProducto {

    private DStockProducto dsp = new DStockProducto();

    public StockProducto select(int id) {
        try {
            return dsp.select(id);
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean modificar(StockProducto sp){
        try{
            return dsp.modificar(sp);
        } catch (Exception e) {
            return false;
        }
    }

    public int insert(StockProducto sp) {
        try {
            return dsp.insert(sp);
        } catch (Exception e) {
            return -1;
        }
    }
}
