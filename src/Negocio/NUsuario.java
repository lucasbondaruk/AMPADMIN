package Negocio;

import Datos.DUsuario;
import Modelos.Usuario;
import java.util.List;

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

    public List<Usuario> listar() throws Exception {
        return datos.listar();
    }

    public Usuario buscarPorId(int id) throws Exception {
        return datos.buscarPorId(id);
    }

    public void registrar(Usuario u) throws Exception {
        validar(u, 0);

        if (u.getContrasenia() == null || u.getContrasenia().length() < 4) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 4 caracteres.");
        }

        if (u.getEstado() == null || u.getEstado().trim().isEmpty()) {
            u.setEstado("Activo");
        }

        if (!datos.insertar(u)) {
            throw new Exception("No se pudo registrar el usuario.");
        }
    }

    public void modificar(Usuario u) throws Exception {
        if (u.getId() <= 0) {
            throw new IllegalArgumentException("Seleccioná un usuario para modificar.");
        }

        validar(u, u.getId());

      
        if (u.getContrasenia() != null && u.getContrasenia().length() < 4) {
            throw new IllegalArgumentException("La nueva contraseña debe tener al menos 4 caracteres.");
        }

        if (u.getEstado() == null || u.getEstado().trim().isEmpty()) {
            u.setEstado("Activo");
        }

        if (!datos.actualizar(u)) {
            throw new Exception("No se encontró el usuario a modificar.");
        }
    }


    public void eliminar(int id, int idUsuarioLogueado) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("Seleccioná un usuario para eliminar.");
        }
        if (id == idUsuarioLogueado) {
            throw new IllegalArgumentException("No podés eliminar tu propio usuario.");
        }

        if (!datos.eliminar(id)) {
            throw new Exception("No se encontró el usuario a eliminar.");
        }
    }

    private void validar(Usuario u, int idExcluir) throws Exception {
        if (u.getUsuario() == null || u.getUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("El usuario es obligatorio.");
        }
        if (u.getIdRol() == null) {
            throw new IllegalArgumentException("Seleccioná un rol.");
        }

        u.setUsuario(u.getUsuario().trim());
        if (u.getUsuario().length() > 50) {
            throw new IllegalArgumentException("El usuario no puede superar los 50 caracteres.");
        }

        u.setContrasenia(vacioANull(u.getContrasenia()));
        u.setTelefono(vacioANull(u.getTelefono()));
        u.setDomicilio(vacioANull(u.getDomicilio()));
        u.setMail(vacioANull(u.getMail()));

        if (u.getTelefono() != null && !u.getTelefono().matches("\\+?[0-9 ()-]+")) {
            throw new IllegalArgumentException("El teléfono solo puede contener números, espacios, guiones y paréntesis.");
        }
        if (u.getMail() != null && !u.getMail().matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$")) {
            throw new IllegalArgumentException("El mail no tiene un formato válido.");
        }

        if (datos.existeUsuario(u.getUsuario(), idExcluir)) {
            throw new IllegalArgumentException("Ya existe un usuario con ese nombre.");
        }
    }

    private String vacioANull(String s) {
        return (s == null || s.trim().isEmpty()) ? null : s.trim();
    }
}
