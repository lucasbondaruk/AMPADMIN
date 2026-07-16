/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Modelos.Producto;
import Datos.DProducto;
import java.util.List;

public class NProducto {

    private DProducto dProducto = new DProducto();

    public Producto buscarPorCodigo(String codigo) throws Exception {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new Exception("Ingrese un código de producto.");
        }

        Producto p = dProducto.buscarPorCodigo(codigo.trim());

        if (p == null) {
            throw new Exception("No se encontró un producto con el código: " + codigo);
        }

        return p;
    }

    public Producto select(int idProducto) {

        DProducto dProducto = new DProducto();

        try {
            return dProducto.buscarPorId(idProducto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Producto> listar() {
        return dProducto.listar();
    }

}
