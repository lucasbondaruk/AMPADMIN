
package Negocio;

import Datos.DTipoProducto;
import Modelos.TipoProducto;
import java.util.List;


public class NTipoProducto {
    DTipoProducto dtp = new DTipoProducto();
    
    public List<TipoProducto> listar(){
        return dtp.Listar();
    }
    public List<TipoProducto>listarPorRubro(int idRubroProducto){
        return dtp.listarPorRubro(idRubroProducto);
    }
}
