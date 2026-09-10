package Negocio;

import Modelos.Producto;
import Datos.DProducto;
import java.util.List;

public class NProducto {

    private DProducto dProducto = new DProducto();

    public boolean insertar(Producto p) {
        return dProducto.insertar(p);
    }

    public boolean modificar(Producto p) {
        return dProducto.modificar(p);
    }

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

    public boolean descontarStock(int idProducto, int cantidad) throws Exception {
        return dProducto.descontarStock(idProducto, cantidad);
    }

    public boolean eliminarPorCodigo(int codigo) {
        try {
            return dProducto.eliminar(codigo);
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
}
