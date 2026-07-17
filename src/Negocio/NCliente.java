package Negocio;

import Modelos.Cliente;
import Datos.DCliente;
import java.util.List;


public class NCliente {

    private DCliente dcc = new DCliente();

    public void create(Cliente cuenta) throws Exception {

        String cuit = cuenta.getCuit().replaceAll("[^0-9]", "");

        if (dcc.existeCuit(cuit)) {
            throw new Exception("El CUIT/CUIL ya está registrado");
        }

        dcc.insert(cuenta);

    }

    public void update(Cliente cuenta) throws Exception {

        if (cuenta.getIdCliente() <= 0) {
            throw new Exception("ID inválido");
        }

        dcc.update(cuenta);
    }

    public void delete(int id) throws Exception {

        if (id <= 0) {
            throw new Exception("ID inválido");
        }

        dcc.delete(id);
    }

    public Cliente select(String cuit) throws Exception {

        if (!cuit.matches("\\d{11}")) {
            throw new IllegalArgumentException("CUIT/CUIL inválido");
        }

        return dcc.select(cuit);
    }

    public List<Cliente> listar() {
        return dcc.listar();
    }

    public boolean isHabilitadoCuentaCorriente(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        return "Cuenta Corriente".equalsIgnoreCase(cliente.getTipoCliente())
                && "Activo".equalsIgnoreCase(cliente.getEstado());
    }
}
