package Negocio;

import Datos.DDetalleVenta;
import Modelos.DetalleVenta;
import java.util.ArrayList;
import java.util.List;

public class NDetalleVenta {

    private DDetalleVenta dDetalleVenta = new DDetalleVenta();

    public boolean insertar(DetalleVenta dv) throws Exception {
        
        if (dv.getCantidad() <= 0) {
            throw new Exception("La cantidad debe ser mayor a cero.");
        }
        if (dv.getPrecioUnitario() <= 0) {
            throw new Exception("El precio unitario no es válido.");
        }

        dv.setImporte(dv.getCantidad() * dv.getPrecioUnitario());
        return dDetalleVenta.insertar(dv);
    }

    public List<DetalleVenta> listarPorVenta(int idVenta) {

        DDetalleVenta dDetalle = new DDetalleVenta();

        try {
            return dDetalle.listarPorVenta(idVenta);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
