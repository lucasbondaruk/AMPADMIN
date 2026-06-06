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

    private final DCobros datosCobros;

    public NCobros() {
        // Inicializamos la capa de datos interna de forma limpia
        this.datosCobros = new DCobros();
    }

    /**
     * Obtiene el total acumulado de dinero de las ventas pendientes de un
     * cliente. Este método lo vas a llamar apenas selecciones al cliente en tu
     * combo o buscador.
     */
    public double obtenerDeudaCliente(int idCliente) {
        return datosCobros.obtenerTotalVentasPendientes(idCliente);
    }

    /**
     * REGLA DE NEGOCIO: Obtiene la cantidad real de comprobantes/ventas
     * pendientes. Sirve para validar si el cliente realmente tiene deudas
     * físicas que saldar.
     */
    public int obtenerCantidadVentasPendientes(int idCliente) {
        // Corregido: Llama al método de conteo real de la base de datos
        return datosCobros.contarVentasPendientes(idCliente);
    }

    /**
     * Ejecuta el proceso de negocio para registrar el cobro de la cuenta
     * corriente. Recibe los parámetros limpios de la pantalla, valida reglas de
     * negocio, arma el objeto Modelo y lo manda a la BD.
     */
    public boolean procesarCobro(int idCliente, int idMetodoPago, double totalCompras, int cuotas, double interes, boolean aplicarDescuento, double totalFinal) {

        // 1. Validaciones básicas de reglas de negocio
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
        if (totalFinal <= 0) {
            System.out.println("Error de negocio: El monto total a cobrar debe ser mayor a 0.");
            return false;
        }

        // 2. Construimos el objeto Modelo con los métodos de tu clase
        CobroCuentaCorriente nuevoCobro = new CobroCuentaCorriente();
        nuevoCobro.setCodCli(idCliente);
        nuevoCobro.setCodMp(idMetodoPago);

        // Convertimos a int si tu modelo maneja totalCompras como entero, 
        // pero lo ideal es mantenerlo en double/float por los centavos de la deuda.
        nuevoCobro.setTotalCompras((int) totalCompras);

        nuevoCobro.setCuotas(cuotas);
        nuevoCobro.setInteres(interes);
        nuevoCobro.setDescuento(aplicarDescuento); // Pasa el boolean que corregimos en Datos
        nuevoCobro.setTotal(totalFinal);

        // 3. Lo enviamos a la capa de datos para la transacción en la BD
        return datosCobros.registrarCobro(nuevoCobro);
    }
}
