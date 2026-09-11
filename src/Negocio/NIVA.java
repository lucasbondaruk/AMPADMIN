package Negocio;

import Modelos.IVA;
import Datos.DIVA;
import java.util.List;

public class NIVA {
    DIVA diva = new DIVA(); 
    
    public IVA buscarPorId(int idIVA) {
        return diva.buscarPorId(idIVA);
    }
    public List<IVA> listar(){
        return diva.listar();
    }
}
