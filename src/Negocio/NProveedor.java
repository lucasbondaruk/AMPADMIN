package Negocio;

import Datos.DProveedor;
import Modelos.Proveedor;
import java.util.ArrayList;

public class NProveedor {

    private DProveedor dp = new DProveedor();

    public void create(Proveedor proveedor) throws Exception {

        if (proveedor == null) {
            throw new Exception("El proveedor no puede ser nulo");
        }

        if (proveedor.getNombre() == null || proveedor.getNombre().trim().isEmpty()) {
            throw new Exception("Debe ingresar la razón social o nombre");
        }

        if (proveedor.getCuit() == null || proveedor.getCuit().trim().isEmpty()) {
            throw new Exception("Debe ingresar el CUIT/CUIL");
        }

        String cuit = proveedor.getCuit().replaceAll("[^0-9]", "");

        if (cuit.length() != 11) {
            throw new Exception("El CUIT/CUIL debe contener 11 dígitos");
        }

        if (dp.existeCuit(cuit)) {
            throw new Exception("El CUIT/CUIL ya está registrado");
        }

        proveedor.setCuit(cuit);

        if (proveedor.getEstado() == null || proveedor.getEstado().trim().isEmpty()) {
            proveedor.setEstado("Activo");
        }

        dp.insert(proveedor);
    }
    
    public ArrayList<Proveedor> listar() throws Exception {
        return dp.listar();
    }
    
    public ArrayList<Proveedor> buscarPorNombre(String nombre) throws Exception {

        if (nombre == null) {
            nombre = "";
        }

        return dp.buscarPorNombre(nombre.trim());
    }
    
    public ArrayList<Proveedor> buscar(String nombre, String rubro, String estado) throws Exception {

        if (nombre == null) {
            nombre = "";
        }

        if (rubro == null) {
            rubro = "";
        }

        if (estado == null || estado.trim().isEmpty()) {
            estado = "Todos";
        }

        return dp.buscar(
                nombre.trim(),
                rubro.trim(),
                estado.trim()
        );
    }
    
    public void update(Proveedor proveedor) throws Exception {

        if (proveedor == null) {
            throw new Exception("El proveedor no puede ser nulo");
        }

        if (proveedor.getIdProveedor() <= 0) {
            throw new Exception("Proveedor inválido");
        }

        if (proveedor.getNombre() == null
                || proveedor.getNombre().trim().isEmpty()) {
            throw new Exception("Debe ingresar la razón social o nombre");
        }

        if (proveedor.getCuit() == null
                || proveedor.getCuit().trim().isEmpty()) {
            throw new Exception("Debe ingresar el CUIT/CUIL");
        }

        String cuit = proveedor.getCuit().replaceAll("[^0-9]", "");

        if (cuit.length() != 11) {
            throw new Exception("El CUIT/CUIL debe contener 11 dígitos");
        }

        if (dp.existeCuitEnOtroProveedor(cuit, proveedor.getIdProveedor())) {
            throw new Exception("El CUIT/CUIL ya pertenece a otro proveedor");
        }

        proveedor.setCuit(cuit);

        dp.update(proveedor);
    }
    
    public void desactivar(Proveedor proveedor) throws Exception {

        if (proveedor == null) {
            throw new Exception("Debe seleccionar un proveedor");
        }

        if (proveedor.getIdProveedor() <= 0) {
            throw new Exception("Proveedor inválido");
        }

        if ("Inactivo".equalsIgnoreCase(proveedor.getEstado())) {
            throw new Exception("El proveedor ya se encuentra inactivo");
        }

        dp.desactivar(proveedor.getIdProveedor());
    }
    
}