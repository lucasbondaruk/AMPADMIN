package Negocio;

import Modelos.InformeIA;
import Modelos.InformeIA.ItemRanking;
import Modelos.InformeIA.ProductoCritico;
import Modelos.InformeIA.VentasVendedor;
import static Utilidades.FormatoAR.pct;
import static Utilidades.FormatoAR.pesos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * "Lectura" de cada gráfico del PDF: qué se ve y qué significa, en lenguaje simple.
 *
 * Se calcula en Java a partir de los mismos datos del gráfico (no usa IA), así que los
 * números del texto siempre coinciden con los del gráfico.
 */
public final class NLecturasGraficos {

    private NLecturasGraficos() {
    }

    public static String productos(InformeIA inf) {
        List<ItemRanking> top = inf.topProductos();
        if (top.isEmpty()) {
            return "No hubo ventas de productos en el período.";
        }
        double totalProductos = inf.ventasPorRubro().stream().mapToDouble(ItemRanking::importe).sum();
        double totalTop = top.stream().mapToDouble(ItemRanking::importe).sum();
        double promedio = totalTop / top.size();
        ItemRanking primero = top.get(0);
        ItemRanking ultimo = top.get(top.size() - 1);

        StringBuilder sb = new StringBuilder();
        sb.append("El producto que más facturó fue \"").append(primero.nombre()).append("\" con ")
                .append(pesos(primero.importe()));
        if (totalProductos > 0) {
            sb.append(" (").append(pct(100 * primero.importe() / totalProductos))
                    .append(" de lo facturado en productos)");
        }
        sb.append(". ");
        if (top.size() >= 3 && totalProductos > 0) {
            double tres = top.get(0).importe() + top.get(1).importe() + top.get(2).importe();
            sb.append("Los 3 primeros suman ").append(pesos(tres)).append(", el ")
                    .append(pct(100 * tres / totalProductos)).append(" del total. ");
        }
        long sobre = top.stream().filter(p -> p.importe() >= promedio).count();
        sb.append("De los ").append(top.size()).append(" productos del ranking, ").append(sobre)
                .append(sobre == 1 ? " está" : " están").append(" sobre el promedio del ranking (")
                .append(pesos(promedio)).append(").");
        if (top.size() >= 2 && ultimo.importe() > 0) {
            double veces = primero.importe() / ultimo.importe();
            sb.append(" El último del ranking, \"").append(ultimo.nombre()).append("\", facturó ")
                    .append(pesos(ultimo.importe()));
            if (veces >= 2) {
                sb.append(": el primero factura ").append(Utilidades.FormatoAR.num(veces)).append(" veces más");
            }
            sb.append(".");
        }
        if (totalProductos > 0 && 100 * primero.importe() / totalProductos >= 30) {
            sb.append(" Hay mucha dependencia de un solo producto: conviene no quedarse nunca sin stock de él.");
        }
        return sb.toString();
    }

    public static String dias(InformeIA inf) {
        List<ItemRanking> dias = inf.promedioPorDiaSemana();
        if (dias.isEmpty()) {
            return "No hay datos por día de la semana.";
        }
        double promedio = dias.stream().mapToDouble(ItemRanking::importe).average().orElse(0);
        if (promedio == 0) {
            return "No hubo ventas en el período.";
        }
        ItemRanking mejor = dias.stream().max(Comparator.comparingDouble(ItemRanking::importe)).orElseThrow();
        ItemRanking peor = dias.stream().filter(d -> d.importe() > 0)
                .min(Comparator.comparingDouble(ItemRanking::importe)).orElse(mejor);
        List<String> sobre = new ArrayList<>();
        List<String> sinVentas = new ArrayList<>();
        for (ItemRanking d : dias) {
            if (d.importe() >= promedio) {
                sobre.add(d.nombre().toLowerCase());
            }
            if (d.importe() == 0) {
                sinVentas.add(d.nombre().toLowerCase());
            }
        }
        StringBuilder sb = new StringBuilder("En promedio se venden ").append(pesos(promedio)).append(" por día. ");
        sb.append("El mejor día es el ").append(mejor.nombre().toLowerCase()).append(" (")
                .append(pesos(mejor.importe())).append(", ").append(pct(100 * (mejor.importe() / promedio - 1)))
                .append(" sobre el promedio)");
        if (peor != mejor) {
            sb.append(" y el más flojo el ").append(peor.nombre().toLowerCase()).append(" (")
                    .append(pesos(peor.importe())).append(", ").append(pct(100 * (1 - peor.importe() / promedio)))
                    .append(" debajo del promedio)");
        }
        sb.append(". Superan el promedio: ").append(String.join(", ", sobre)).append(".");
        if (!sinVentas.isEmpty()) {
            sb.append(" Sin ventas: ").append(String.join(", ", sinVentas)).append(" (probablemente días cerrados).");
        }
        if (peor != mejor && peor.importe() < promedio * 0.75) {
            sb.append(" El ").append(peor.nombre().toLowerCase())
                    .append(" es un buen candidato para promociones que atraigan clientes.");
        }
        return sb.toString();
    }

    public static String rubros(InformeIA inf) {
        return participacion(inf.ventasPorRubro(), "rubro", "rubros",
                "Las ventas dependen mucho de un solo rubro: una baja en él afecta a todo el negocio.");
    }

    public static String pagos(InformeIA inf) {
        List<ItemRanking> pagos = inf.ventasPorMetodoPago();
        String texto = participacion(pagos, "medio de pago", "medios de pago", null);
        List<String> tickets = new ArrayList<>();
        for (ItemRanking p : pagos) {
            if (p.cantidad() > 0) {
                tickets.add(p.nombre() + " " + pesos(p.importe() / p.cantidad()));
            }
        }
        if (tickets.size() >= 2) {
            texto += " Ticket promedio por medio de pago: " + String.join("; ", tickets) + ".";
        }
        return texto;
    }

    public static String vendedores(InformeIA inf) {
        List<VentasVendedor> conVentas = inf.vendedores().stream().filter(v -> v.cantidad() > 0 && v.id() != null)
                .sorted(Comparator.comparingLong(VentasVendedor::cantidad).reversed()).toList();
        if (conVentas.isEmpty()) {
            return "Ningún usuario registró ventas en el período.";
        }
        long total = inf.vendedores().stream().mapToLong(VentasVendedor::cantidad).sum();
        long sinVentas = inf.vendedores().stream().filter(v -> v.cantidad() == 0 && v.id() != null).count();
        VentasVendedor primero = conVentas.get(0);
        StringBuilder sb = new StringBuilder();
        if (conVentas.size() == 1 && primero.cantidad() == total) {
            sb.append("Todas las ventas del período (").append(total).append(") las registró ")
                    .append(primero.usuario()).append(", por ").append(pesos(primero.ingresos())).append(".");
        } else {
            sb.append(conVentas.size() == 1 ? "1 usuario registró ventas. " : conVentas.size() + " usuarios registraron ventas. ")
                    .append(primero.usuario())
                    .append(" hizo la mayor cantidad: ").append(primero.cantidad()).append(" de ").append(total)
                    .append(" (").append(pct(100.0 * primero.cantidad() / total)).append("), por ")
                    .append(pesos(primero.ingresos())).append(".");
            VentasVendedor mayorTicket = conVentas.stream()
                    .max(Comparator.comparingDouble(VentasVendedor::ticketPromedio)).orElseThrow();
            sb.append(" El ticket promedio más alto es el de ").append(mayorTicket.usuario()).append(" (")
                    .append(pesos(mayorTicket.ticketPromedio())).append(").");
        }
        if (sinVentas > 0) {
            sb.append(" ").append(sinVentas).append(sinVentas == 1 ? " usuario no registró" : " usuarios no registraron")
                    .append(" ventas (puede ser normal si su rol no es de venta).");
        }
        inf.vendedores().stream().filter(v -> v.id() == null && v.cantidad() > 0).findFirst().ifPresent(v ->
                sb.append(v.cantidad() == 1 ? " Hay 1 venta" : " Hay " + v.cantidad() + " ventas")
                        .append(" sin vendedor asignado: conviene revisar la carga."));
        return sb.toString();
    }

    public static String stock(InformeIA inf) {
        List<ProductoCritico> stock = inf.stockCritico();
        if (stock.isEmpty()) {
            return "No hay productos en stock crítico: todos están sobre su mínimo y alcanzan para más de 15 días.";
        }
        long sinStock = stock.stream().filter(p -> p.stockActual() <= 0).count();
        long urgentes = stock.stream().filter(p -> p.stockActual() > 0 && p.diasCobertura() < 7).count();
        long bajoMinimo = stock.stream().filter(p -> p.stockActual() <= p.stockMinimo()).count();
        StringBuilder sb = new StringBuilder("Hay ").append(stock.size()).append(" productos en stock crítico: ")
                .append(bajoMinimo).append(" están en o por debajo de su mínimo");
        if (sinStock > 0) {
            sb.append(", ").append(sinStock).append(" ya sin stock");
        }
        if (urgentes > 0) {
            sb.append(" y ").append(urgentes).append(urgentes == 1 ? " se agota" : " se agotan")
                    .append(" en menos de una semana al ritmo actual");
        }
        sb.append(". La tabla está ordenada por urgencia: reponer primero los de arriba.");
        return sb.toString();
    }

    /** Lectura genérica de participación para rubros y medios de pago. */
    private static String participacion(List<ItemRanking> items, String singular, String plural, String alertaConcentracion) {
        List<ItemRanking> conVentas = items.stream().filter(i -> i.importe() > 0)
                .sorted(Comparator.comparingDouble(ItemRanking::importe).reversed()).toList();
        if (conVentas.isEmpty()) {
            return "No hubo ventas en el período.";
        }
        double total = conVentas.stream().mapToDouble(ItemRanking::importe).sum();
        ItemRanking primero = conVentas.get(0);
        double pctPrimero = 100 * primero.importe() / total;
        if (conVentas.size() == 1) {
            return "Todo lo facturado (" + pesos(total) + ") corresponde a un solo " + singular + ": "
                    + primero.nombre() + ".";
        }
        ItemRanking segundo = conVentas.get(1);
        StringBuilder sb = new StringBuilder("Se vendió en ").append(conVentas.size()).append(" ").append(plural)
                .append(". \"").append(primero.nombre()).append("\" lidera con ").append(pesos(primero.importe()))
                .append(" (").append(pct(pctPrimero)).append("), seguido por \"").append(segundo.nombre())
                .append("\" con ").append(pesos(segundo.importe())).append(" (")
                .append(pct(100 * segundo.importe() / total)).append(").");
        if (pctPrimero >= 60 && alertaConcentracion != null) {
            sb.append(" ").append(alertaConcentracion);
        } else if (conVentas.size() >= 3) {
            double resto = total - primero.importe() - segundo.importe();
            sb.append(" Los demás suman ").append(pct(100 * resto / total)).append(".");
        }
        return sb.toString();
    }
}