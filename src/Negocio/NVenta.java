package Negocio;

import Conexiones.SqlConection;
import Datos.DDetalleVenta;
import Datos.DVenta;
import Modelos.DetalleVenta;
import Modelos.Producto;
import Modelos.Venta;
import java.util.List;
import java.util.ArrayList;

public class NVenta {

    private DVenta dVenta = new DVenta();
    private DDetalleVenta dDetalleVenta = new DDetalleVenta();
    private SqlConection conexion = new SqlConection();

    public static int insertarConId(Venta venta) throws Exception{
        DVenta dVenta = new DVenta();
        return dVenta.insertarConId(venta);
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
