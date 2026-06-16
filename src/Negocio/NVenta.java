
package Negocio;

import Datos.DVenta;
import Modelos.Venta;

public class NVenta {
    
    public static boolean Registrar(Venta venta){
        DVenta dVenta = new DVenta();
        return dVenta.Insertar(venta);
    }
    
}
