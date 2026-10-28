package Negocio;

import Datos.DRol;
import Modelos.Rol;
import java.sql.SQLException;
import java.util.List;

public class NRol {

    private final DRol datos = new DRol();

    public List<Rol> listar() throws SQLException {
        return datos.listar();
    }

    public Rol buscarPorId(int idRol) throws SQLException {
        return datos.buscarPorId(idRol);
    }

    public void insertar(Rol r) throws SQLException {
        validar(r, 0);
        if (!datos.insertar(r)) {
            throw new SQLException("No se pudo registrar el rol.");
        }
    }

    public void actualizar(Rol r) throws SQLException {
        if (r.getIdRol() <= 0) {
            throw new IllegalArgumentException("Seleccioná un rol para modificar.");
        }
        validar(r, r.getIdRol());
        if (!datos.actualizar(r)) {
            throw new SQLException("No se encontró el rol a modificar.");
        }
    }

    public void eliminar(int idRol) throws SQLException {
        if (idRol <= 0) {
            throw new IllegalArgumentException("Seleccioná un rol para eliminar.");
        }
        if (datos.tieneUsuarios(idRol)) {
            throw new IllegalArgumentException("No se puede eliminar: hay usuarios con este rol.");
        }
        if (!datos.eliminar(idRol)) {
            throw new SQLException("No se encontró el rol a eliminar.");
        }
    }

    private void validar(Rol r, int idExcluir) throws SQLException {
        if (r.getNomRol() == null || r.getNomRol().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del rol es obligatorio.");
        }
        r.setNomRol(r.getNomRol().trim());
        if (r.getNomRol().length() > 50) {
            throw new IllegalArgumentException("El nombre del rol no puede superar los 50 caracteres.");
        }
        if (r.getDescripcion() != null) {
            r.setDescripcion(r.getDescripcion().trim());
        }
        if (datos.existeNombre(r.getNomRol(), idExcluir)) {
            throw new IllegalArgumentException("Ya existe un rol con ese nombre.");
        }
    }
}
