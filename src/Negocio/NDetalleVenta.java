
package Negocio;

import Datos.DDetalleVenta;
import Modelos.DetalleVenta;

public class NDetalleVenta {
    
     private DDetalleVenta dDetalleVenta = new DDetalleVenta();

    public boolean insertar(DetalleVenta dv) throws Exception {
        // Validaciones de negocio
        if (dv.getCantidad() <= 0)
            throw new Exception("La cantidad debe ser mayor a cero.");
        if (dv.getPrecioUnitario() <= 0)
            throw new Exception("El precio unitario no es válido.");

        dv.setImporte(dv.getCantidad() * dv.getPrecioUnitario());
        return dDetalleVenta.insertar(dv);
    }
    
}
