package Negocio;

import Modelos.IVA;
import Datos.DIVA;

public class NIVA {

    public IVA buscarPorId(int idIVA) {
        DIVA diva = new DIVA();
        return diva.buscarPorId(idIVA);
    }
}
