package Negocio;

import Datos.DVenta;
import Modelos.Venta;
import java.util.List;
import java.util.ArrayList;

public class NVenta {

    public static boolean Registrar(Venta venta) {
        DVenta dVenta = new DVenta();
        return dVenta.Insertar(venta);
    }

    public List<Venta> listarVentasCliente(int idCliente) {
        DVenta dVenta = new DVenta();

        try {
            return dVenta.listarVentasCliente(idCliente);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
