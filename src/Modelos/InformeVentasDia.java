package Modelos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Resultado de lectura compartido por AFG-11 y AFG-94; no persiste un cierre. */
public class InformeVentasDia {
    public static class ResumenMetodo {
        private final Integer idMetodoPago;
        private final String descripcion;
        private final long operaciones;
        private final BigDecimal total;

        public ResumenMetodo(Integer idMetodoPago, String descripcion,
                long operaciones, BigDecimal total) {
            this.idMetodoPago = idMetodoPago;
            this.descripcion = descripcion == null || descripcion.trim().isEmpty()
                    ? "Sin especificar" : descripcion;
            this.operaciones = operaciones;
            this.total = total;
        }

        public Integer getIdMetodoPago() { return idMetodoPago; }
        public String getDescripcion() { return descripcion; }
        public long getOperaciones() { return operaciones; }
        public BigDecimal getTotal() { return total; }
    }

    private final LocalDate fecha;
    private final List<ResumenMetodo> metodos;
    private final BigDecimal total;

    public InformeVentasDia(LocalDate fecha, List<ResumenMetodo> metodos) {
        this.fecha = fecha;
        this.metodos = Collections.unmodifiableList(new ArrayList<>(metodos));
        this.total = metodos.stream().map(ResumenMetodo::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public LocalDate getFecha() { return fecha; }
    public List<ResumenMetodo> getMetodos() { return metodos; }
    public BigDecimal getTotal() { return total; }
}
