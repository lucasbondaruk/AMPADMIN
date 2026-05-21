package Negocio;

import Datos.DUsuario;
import Modelos.Usuario;

public class NUsuario {

    private DUsuario datos = new DUsuario();

    public Usuario login(String usuario, String contrasenia) throws Exception {

        if (usuario == null || usuario.trim().isEmpty()) {
            throw new Exception("Debe ingresar el usuario");
        }

        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            throw new Exception("Debe ingresar la contraseña");
        }

        Usuario u = datos.validarLogin(usuario.trim(), contrasenia.trim());

        if (u == null) {
            throw new Exception("Usuario o contraseña incorrectos");
        }

        return u;
    }
}