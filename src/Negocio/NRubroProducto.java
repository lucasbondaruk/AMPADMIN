package Negocio;

import Datos.DRubroProducto;
import Modelos.RubroProducto;
import java.util.List;

public class NRubroProducto {
    DRubroProducto drp = new DRubroProducto();
    
    public List<RubroProducto> listar(){
        return drp.Listar();
    }
}
