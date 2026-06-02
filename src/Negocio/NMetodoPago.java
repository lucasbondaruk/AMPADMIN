
package Negocio;

import Modelos.MetodoPago;
import Datos.DMetodoPago;
import java.util.List;

public class NMetodoPago {
    
    private DMetodoPago mp = new DMetodoPago(); 
    
    public List<MetodoPago>GetList(){
        return mp.Listar();
    }
    
    public List<MetodoPago>GetListActivo(){
        return mp.ListarActivos();
    }
    
}
