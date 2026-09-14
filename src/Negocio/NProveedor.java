
package Negocio;
import Datos.DProveedor;
import Modelos.Proveedor;
import java.util.List;

public class NProveedor {
    
    private DProveedor dp = new DProveedor();
   
    public List<Proveedor> listar(){
        return dp.listar();
    }
}
