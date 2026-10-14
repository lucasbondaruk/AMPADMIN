package Negocio;

import Datos.DNegocio;
import Modelos.MNegocio;
import java.sql.SQLException;

public class NNegocio {

    private static MNegocio negocioActual;
    private final DNegocio datos = new DNegocio();

    public void cargarNegocio() throws SQLException {
        negocioActual = datos.obtener();
    }

    public void guardar(MNegocio n) throws SQLException {
        validar(n);
        n.setIdNegocio(1);
        datos.guardar(n);
        negocioActual = n;
    }

    public static MNegocio getNegocioActual() {
        if (negocioActual == null) {
            try {
                negocioActual = new DNegocio().obtener();
            } catch (SQLException e) {
                System.out.println("Error al cargar el negocio: " + e.getMessage());
            }
        }
        return negocioActual;
    }

    public static boolean hayNegocioCargado() {
        return negocioActual != null;
    }

    private void validar(MNegocio n) {
        n.setNombreNegocio(validarTexto(n.getNombreNegocio(), "nombre del negocio", 100));
        n.setDomicilioNegocio(validarTexto(n.getDomicilioNegocio(), "domicilio", 150));
        n.setProvinciaNegocio(validarTexto(n.getProvinciaNegocio(), "provincia", 50));
        n.setTelefonoNegocio(validarTexto(n.getTelefonoNegocio(), "teléfono", 20));

        if (!n.getTelefonoNegocio().matches("\\+?[0-9 ()-]+")) {
            throw new IllegalArgumentException("El teléfono solo puede contener números, espacios, guiones, paréntesis y + al inicio.");
        }

        if (n.getTelefonoNegocio().replaceAll("[^0-9]", "").length() < 6) {
            throw new IllegalArgumentException("El teléfono debe tener al menos 6 dígitos.");
        }
    }

    private String validarTexto(String valor, String campo, int maximo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El " + campo + " es obligatorio.");
        }
        valor = valor.trim();
        if (valor.length() > maximo) {
            throw new IllegalArgumentException("El " + campo + " no puede superar los " + maximo + " caracteres.");
        }
        return valor;
    }
}
