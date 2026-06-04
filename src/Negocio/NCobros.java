/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Datos.DCobros;
import Modelos.CobroCuentaCorriente;

/**
 *
 * @author Tomas Alvarez
 */
public class NCobros {

    private Datos.DCobros datosCobros;

    public NCobros() {
        // Inicializamos la capa de datos interna de forma limpia
        this.datosCobros = new DCobros();
    }

    /**
     * Obtiene el total acumulado de dinero de las ventas pendientes de un
     * cliente. Este método lo vas a llamar apenas busques al cliente en tu
     * pantalla.
     */
    public double obtenerDeudaCliente(int idCliente) {
        return datosCobros.obtenerTotalVentasPendientes(idCliente);
    }

    /**
     * Obtiene la cantidad de comprobantes/ventas pendientes. (Alineado con el
     * totalCompras que maneja tu modelo).
     */
    public int obtenerCantidadVentasPendientes(int idCliente) {
        // Podés usar el total de compras pendientes directamente para el flujo de la pantalla
        return (int) datosCobros.obtenerTotalVentasPendientes(idCliente);
    }

    /**
     * Ejecuta el proceso de negocio para registrar el cobro de la cuenta
     * corriente. Recibe los parámetros limpios de la pantalla, arma el objeto
     * Modelo y lo manda a la BD.
     */
    public boolean procesarCobro(int idCliente, int idMetodoPago, int totalCompras, int cuotas, double interes, boolean aplicarDescuento, double totalFinal) {

        // 1. Validaciones básicas de negocio
        if (idCliente <= 0) {
            System.out.println("Error de negocio: El cliente no es válido.");
            return false;
        }
        if (totalCompras <= 0) {
            System.out.println("Error de negocio: El cliente no registra deudas pendientes.");
            return false;
        }
        if (cuotas <= 0) {
            System.out.println("Error de negocio: La cantidad de cuotas debe ser mayor a 0.");
            return false;
        }

        // 2. Construimos el objeto Modelo con los setters correctos de tu clase
        CobroCuentaCorriente nuevoCobro = new CobroCuentaCorriente();
        nuevoCobro.setCodCli(idCliente);
        nuevoCobro.setCodMp(idMetodoPago);
        nuevoCobro.setTotalCompras(totalCompras);
        nuevoCobro.setCuotas(cuotas);
        nuevoCobro.setInteres(interes);
        nuevoCobro.setDescuento(aplicarDescuento);
        nuevoCobro.setTotal(totalFinal);

        // 3. Lo enviamos a la capa de datos para que impacte la BD y retorne el resultado
        return datosCobros.registrarCobro(nuevoCobro);
    }
}
