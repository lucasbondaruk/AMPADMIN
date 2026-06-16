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

    public int obtenerCantidadVentasPendientes(int idCliente) {
        DVenta dVenta = new DVenta();
        try {
            return dVenta.obtenerCantidadVentasPendientes(idCliente);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double obtenerMontoVentasPendientes(int idCliente) {
        DVenta dVenta = new DVenta();
        try {
            return dVenta.obtenerMontoVentasPendientes(idCliente);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
