package Negocio;

import Datos.DInformeVentasDia;
import Modelos.InformeVentasDia;
import java.sql.SQLException;
import java.time.LocalDate;

public class NInformeVentasDia {
    private final DInformeVentasDia datos = new DInformeVentasDia();

    public InformeVentasDia consultar(LocalDate fecha) throws SQLException {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha del informe es obligatoria.");
        }
        return new InformeVentasDia(fecha, datos.consultar(fecha));
    }
}
