
package Negocio;

import Datos.DStockProducto;
import Modelos.StockProducto;


public class NStockProducto {
    private DStockProducto dsp = new DStockProducto();
    
    public StockProducto select(int id){
        try{
            return dsp.select(id);
        } catch(Exception e){
            return null;
        }
    }
    
}
