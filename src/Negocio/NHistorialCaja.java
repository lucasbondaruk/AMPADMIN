package Negocio;

import Datos.DHistorialCaja;
import Modelos.MetodoPago;
import Modelos.RegistroHistorialCaja;
import Modelos.RegistroHistorialCaja.Origen;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NHistorialCaja {
    private final DHistorialCaja datos = new DHistorialCaja();
    private static final DateTimeFormatter FORMATO = DateTimeFormatter
            .ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    public LocalDate interpretarFecha(String texto) {
        try {
            if (texto == null || !texto.trim().matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
                throw new IllegalArgumentException("Ingrese Desde y Hasta con formato dd/MM/yyyy.");
            }
            LocalDate fecha = LocalDate.parse(texto.trim(), FORMATO);
            if (fecha.getYear() < 1) {
                throw new IllegalArgumentException("El ano debe ser mayor a cero.");
            }
            return fecha;
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Fecha inexistente. Use dd/MM/yyyy.");
        }
    }

    public List<RegistroHistorialCaja> buscar(LocalDate desde, LocalDate hasta,
            String tipo, Integer idMetodo, String estado) throws SQLException {
        if (desde == null || hasta == null || desde.isAfter(hasta)) {
            throw new IllegalArgumentException("Desde no puede ser posterior a Hasta.");
        }
        return filtrar(datos.listarPorFechas(desde, hasta), tipo, idMetodo, estado);
    }

    public List<RegistroHistorialCaja> filtrar(List<RegistroHistorialCaja> registros,
            String tipo, Integer idMetodo, String estado) {
        return registros.stream()
                .filter(r -> tipo == null || "Todos".equals(tipo) || tipo.equals(r.getTipo()))
                .filter(r -> idMetodo == null || idMetodo.equals(r.getIdMetodo()))
                .filter(r -> estado == null || "Todos".equals(estado) || estado.equals(r.getEstado()))
                .sorted(Comparator.comparing(RegistroHistorialCaja::getFecha).reversed()
                        .thenComparing(r -> r.getOrigen().name())
                        .thenComparing(Comparator.comparingInt(RegistroHistorialCaja::getIdOrigen).reversed()))
                .collect(Collectors.toList());
    }

    public BigDecimal totalVentas(List<RegistroHistorialCaja> registros) {
        return registros.stream().filter(r -> r.getOrigen() == Origen.VENTA)
                .map(RegistroHistorialCaja::getMonto).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<MetodoPago> listarMetodos() throws SQLException { return datos.listarMetodos(); }
    public List<String> listarEstados() throws SQLException { return datos.listarEstadosVentasCobradas(); }
}
