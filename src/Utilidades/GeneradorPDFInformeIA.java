package Utilidades;

import Modelos.InformeIA;
import Modelos.InformeIA.AnalisisIA;
import Modelos.InformeIA.Kpis;
import Modelos.InformeIA.ProductoCritico;
import Modelos.InformeIA.Pronostico;
import Modelos.InformeIA.Recomendacion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import Negocio.NLecturasGraficos;
import org.jfree.chart.JFreeChart;

/** Exporta el Informe con IA a PDF con iText 5 (misma librería y estilo que GeneradorPDF). */
public final class GeneradorPDFInformeIA {

    private static final BaseColor COLOR_PRINCIPAL = new BaseColor(38, 90, 142);
    private static final BaseColor GRIS_CLARO = new BaseColor(242, 242, 240);
    private static final Font TITULO = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, COLOR_PRINCIPAL);
    private static final Font SUBTITULO = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD, COLOR_PRINCIPAL);
    private static final Font NORMAL = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
    private static final Font NEGRITA = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
    private static final Font CHICO = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.DARK_GRAY);
    private static final Font KPI_VALOR = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, COLOR_PRINCIPAL);
    private static final DateTimeFormatter FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private GeneradorPDFInformeIA() {
    }

    public static void generar(File archivo, InformeIA inf, String fuenteIA)
            throws DocumentException, IOException {
        Document doc = new Document(PageSize.A4, 36, 36, 32, 32);
        try (FileOutputStream salida = new FileOutputStream(archivo)) {
            PdfWriter.getInstance(doc, salida);
            doc.open();

            doc.add(new Paragraph("AMPADMIN · Informe de ventas con IA", TITULO));
            doc.add(new Paragraph("Período: " + inf.desde().format(FECHA) + " al " + inf.hasta().format(FECHA)
                    + "   ·   Generado: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                    CHICO));
            espacio(doc, 8);

            kpis(doc, inf.kpis());

            AnalisisIA a = inf.analisis();
            if (a != null) {
                doc.add(new Paragraph("Texto generado por: " + a.fuente(), CHICO));
                seccion(doc, "Resumen");
                doc.add(new Paragraph(a.resumen(), NORMAL));
            }

            seccion(doc, "Ventas y pronóstico");
            doc.add(imagen(GraficosInformeIA.ventasYPronostico(inf), 760, 380));
            pronostico(doc, inf.pronostico());
            if (a != null && !a.comentarioPronostico().isBlank()) {
                doc.add(new Paragraph(a.comentarioPronostico(), NORMAL));
            }

            if (a != null) {
                seccion(doc, "Interpretación");
                doc.add(vinetas(a.interpretacion()));

                seccion(doc, "Recomendaciones");
                for (Recomendacion r : a.recomendaciones()) {
                    Paragraph p = new Paragraph();
                    p.add(new Chunk("[" + r.prioridad() + "] " + r.titulo() + ": ", NEGRITA));
                    p.add(new Chunk(r.detalle(), NORMAL));
                    p.add(new Chunk("  (basado en " + String.join(", ", r.basadoEn()) + ")", CHICO));
                    p.setSpacingAfter(4);
                    doc.add(p);
                }
                if (!a.alertas().isEmpty()) {
                    seccion(doc, "Alertas");
                    doc.add(vinetas(a.alertas()));
                }
            } else if (inf.errorIA() != null) {
                seccion(doc, "Análisis con IA");
                doc.add(new Paragraph(inf.errorIA(), CHICO));
            }

            // ---- Una sección por pestaña: gráfico + lectura
            doc.newPage();
            seccion(doc, "Productos más facturados");
            if (!inf.topProductos().isEmpty()) {
                doc.add(imagen(GraficosInformeIA.productos(inf.topProductos()), 760, 420));
            }
            lectura(doc, NLecturasGraficos.productos(inf));

            doc.newPage();
            seccion(doc, "Rubros, pagos y días");
            doc.add(imagen(GraficosInformeIA.promedioPorDia(inf.promedioPorDiaSemana()), 760, 330));
            lectura(doc, NLecturasGraficos.dias(inf));
            doc.add(imagen(GraficosInformeIA.torta("Ventas por rubro", inf.ventasPorRubro()), 760, 300));
            lectura(doc, NLecturasGraficos.rubros(inf));
            doc.add(imagen(GraficosInformeIA.torta("Medios de pago", inf.ventasPorMetodoPago()), 760, 300));
            lectura(doc, NLecturasGraficos.pagos(inf));

            doc.newPage();
            seccion(doc, "Vendedores");
            java.util.List<Modelos.InformeIA.ItemRanking> cantidades = inf.vendedores().stream()
                    .filter(v -> v.cantidad() > 0)
                    .map(v -> new Modelos.InformeIA.ItemRanking(v.usuario(), v.cantidad(), v.cantidad()))
                    .toList();
            doc.add(imagen(GraficosInformeIA.tortaCantidades("Total de ventas por usuario", cantidades, "ventas"),
                    760, 330));
            doc.add(tablaVendedores(inf));
            lectura(doc, NLecturasGraficos.vendedores(inf));

            seccion(doc, "Stock crítico");
            if (!inf.stockCritico().isEmpty()) {
                doc.add(tablaStock(inf));
            }
            lectura(doc, NLecturasGraficos.stock(inf));

            calidadYMetodo(doc, inf);

            espacio(doc, 10);
            doc.add(new Paragraph("Estadísticas, pruebas de significancia y pronóstico calculados por AMPADMIN. "
                    + "Redacción: " + (a == null ? "-" : a.fuente()) + " (" + fuenteIA + "); cada número del texto "
                    + "se verificó contra los hallazgos. Revisar antes de tomar decisiones.", CHICO));
            doc.close();
        }
    }

    private static void kpis(Document doc, Kpis k) throws DocumentException {
        NumberFormat pesos = GraficosInformeIA.formatoPesos();
        PdfPTable t = new PdfPTable(3);
        t.setWidthPercentage(100);
        t.addCell(kpi("Ingresos", pesos.format(k.ingresos()), k.variacionIngresos()));
        t.addCell(kpi("Cantidad de ventas", String.valueOf(k.cantidadVentas()), k.variacionCantidad()));
        t.addCell(kpi("Ticket promedio", pesos.format(k.ticketPromedio()), k.variacionTicket()));
        doc.add(t);
    }

    private static PdfPCell kpi(String titulo, String valor, double variacion) {
        PdfPCell c = new PdfPCell();
        c.setBackgroundColor(GRIS_CLARO);
        c.setBorderColor(BaseColor.WHITE);
        c.setBorderWidth(3);
        c.setPadding(8);
        c.addElement(new Paragraph(titulo, CHICO));
        c.addElement(new Paragraph(valor, KPI_VALOR));
        c.addElement(new Paragraph(textoVariacion(variacion) + " vs. período anterior", CHICO));
        return c;
    }

    public static String textoVariacion(double v) {
        if (Double.isNaN(v)) {
            return "Sin datos";
        }
        return String.format(Locale.forLanguageTag("es-AR"), "%+.1f %%", v);
    }

    private static void pronostico(Document doc, Pronostico p) throws DocumentException {
        if (p == null) {
            doc.add(new Paragraph("No hay historia suficiente para pronosticar (mínimo 4 períodos completos).", CHICO));
            return;
        }
        NumberFormat pesos = GraficosInformeIA.formatoPesos();
        PdfPTable t = new PdfPTable(new float[]{2, 2, 2, 2});
        t.setWidthPercentage(100);
        t.setSpacingBefore(6);
        for (String h : new String[]{"Período", "Esperado", "Mínimo (95 %)", "Máximo (95 %)"}) {
            t.addCell(encabezado(h));
        }
        for (int i = 0; i < p.valores().size(); i++) {
            t.addCell(celda(p.valores().get(i).inicio().format(FECHA)));
            t.addCell(celda(pesos.format(p.valores().get(i).valor())));
            t.addCell(celda(pesos.format(p.limiteInferior().get(i).valor())));
            t.addCell(celda(pesos.format(p.limiteSuperior().get(i).valor())));
        }
        doc.add(t);
        String detalle = "Modelo elegido por validación cruzada: " + p.metodo()
                + (Double.isNaN(p.errorMedio()) ? "" : " · Error medio: " + FormatoAR.pesos(p.errorMedio())
                + " por período" + (Double.isNaN(p.mape()) ? "" : " (" + FormatoAR.pct(p.mape()) + ")"))
                + (p.advertencia() == null ? "" : " · " + p.advertencia());
        doc.add(new Paragraph(detalle, CHICO));
    }

    private static void calidadYMetodo(Document doc, InformeIA inf) throws DocumentException {
        doc.newPage();
        seccion(doc, "Calidad de datos");
        var cal = inf.calidad();
        if (cal.problemas().isEmpty() && cal.ticketsAtipicos() == 0 && cal.periodosAtipicos().isEmpty()) {
            doc.add(new Paragraph("No se detectaron problemas.", NORMAL));
        } else {
            PdfPTable t = new PdfPTable(new float[]{2, 1, 1.6f, 5});
            t.setWidthPercentage(100);
            for (String h : new String[]{"Problema", "Cantidad", "Tratamiento", "Detalle"}) {
                t.addCell(encabezado(h));
            }
            for (var p : cal.problemas()) {
                fila(t, p.tipo(), String.valueOf(p.cantidad()), p.excluido() ? "Excluido" : "Para revisar", p.detalle());
            }
            if (cal.ticketsAtipicos() > 0) {
                fila(t, "Ticket atípico", String.valueOf(cal.ticketsAtipicos()), "Para revisar",
                        "Importe muy distinto al resto (z robusto > 3,5)");
            }
            for (var p : cal.periodosAtipicos()) {
                fila(t, "Período atípico", "1", cal.serieCorregida() ? "Corregido" : "Solo informado",
                        p.inicio().format(FECHA) + ": " + FormatoAR.pesos(p.valor()));
            }
            doc.add(t);
        }

        Pronostico p = inf.pronostico();
        if (p != null && !p.evaluados().isEmpty()) {
            seccion(doc, "Selección del modelo de pronóstico (validación cruzada temporal)");
            PdfPTable t = new PdfPTable(new float[]{5, 2, 1.4f});
            t.setWidthPercentage(100);
            for (String h : new String[]{"Modelo", "Error medio por período", "Error %"}) {
                t.addCell(encabezado(h));
            }
            for (var e : p.evaluados()) {
                fila(t, e.nombre() + (e.nombre().equals(p.metodo()) ? "  (elegido)" : ""),
                        FormatoAR.pesos(e.errorMedio()), Double.isNaN(e.mape()) ? "-" : FormatoAR.pct(e.mape()));
            }
            doc.add(t);
        }

        seccion(doc, "Hallazgos calculados (base de la interpretación)");
        PdfPTable t = new PdfPTable(new float[]{0.7f, 9});
        t.setWidthPercentage(100);
        for (var h : inf.hallazgos()) {
            fila(t, h.id(), h.texto());
        }
        doc.add(t);
    }

    private static void fila(PdfPTable t, String... textos) {
        for (String x : textos) {
            t.addCell(celda(x));
        }
    }

    /** Recuadro gris con la lectura del gráfico. */
    private static void lectura(Document doc, String texto) throws DocumentException {
        PdfPTable t = new PdfPTable(1);
        t.setWidthPercentage(100);
        t.setSpacingBefore(4);
        t.setSpacingAfter(12);
        PdfPCell c = new PdfPCell();
        c.setBackgroundColor(GRIS_CLARO);
        c.setBorderColor(GRIS_CLARO);
        c.setPadding(8);
        Paragraph p = new Paragraph();
        p.add(new Chunk("Lectura: ", NEGRITA));
        p.add(new Chunk(texto, NORMAL));
        c.addElement(p);
        t.addCell(c);
        doc.add(t);
    }

    private static PdfPTable tablaVendedores(InformeIA inf) {
        PdfPTable t = new PdfPTable(new float[]{4, 2.5f, 1.4f, 2.2f, 2.2f});
        t.setWidthPercentage(100);
        t.setSpacingBefore(6);
        for (String h : new String[]{"Usuario", "Rol", "Ventas", "Ingresos", "Ticket promedio"}) {
            t.addCell(encabezado(h));
        }
        inf.vendedores().stream()
                .sorted(java.util.Comparator.comparingDouble(Modelos.InformeIA.VentasVendedor::ingresos).reversed())
                .forEach(v -> fila(t, v.usuario(), v.rol() == null ? "-" : v.rol(), String.valueOf(v.cantidad()),
                        FormatoAR.pesos(v.ingresos()), v.cantidad() == 0 ? "-" : FormatoAR.pesos(v.ticketPromedio())));
        return t;
    }

    private static PdfPTable tablaStock(InformeIA inf) {
        PdfPTable t = new PdfPTable(new float[]{1.2f, 4, 1.4f, 1.4f, 1.6f});
        t.setWidthPercentage(100);
        for (String h : new String[]{"Código", "Producto", "Stock", "Mínimo", "Días cobertura"}) {
            t.addCell(encabezado(h));
        }
        for (ProductoCritico p : inf.stockCritico()) {
            t.addCell(celda(String.valueOf(p.codigo())));
            t.addCell(celda(p.descripcion()));
            t.addCell(celda(String.valueOf(p.stockActual())));
            t.addCell(celda(String.valueOf(p.stockMinimo())));
            t.addCell(celda(Double.isInfinite(p.diasCobertura()) ? "Sin ventas" : String.format("%.0f", p.diasCobertura())));
        }
        return t;
    }

    private static List vinetas(java.util.List<String> textos) {
        List l = new List(List.UNORDERED, 12);
        l.setListSymbol("•  ");
        for (String s : textos) {
            l.add(new ListItem(s, NORMAL));
        }
        return l;
    }

    private static void seccion(Document doc, String titulo) throws DocumentException {
        Paragraph p = new Paragraph(titulo, SUBTITULO);
        p.setSpacingBefore(12);
        p.setSpacingAfter(4);
        doc.add(p);
    }

    private static PdfPCell encabezado(String texto) {
        PdfPCell c = new PdfPCell(new Phrase(texto, new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.WHITE)));
        c.setBackgroundColor(COLOR_PRINCIPAL);
        c.setPadding(5);
        return c;
    }

    private static PdfPCell celda(String texto) {
        PdfPCell c = new PdfPCell(new Phrase(texto, new Font(Font.FontFamily.HELVETICA, 9)));
        c.setPadding(4);
        c.setBorderColor(BaseColor.LIGHT_GRAY);
        return c;
    }

    private static Image imagen(JFreeChart chart, int ancho, int alto) throws IOException, DocumentException {
        ByteArrayOutputStream png = new ByteArrayOutputStream();
        // Se renderiza al doble de resolución para que se vea nítido al imprimir
        javax.imageio.ImageIO.write(chart.createBufferedImage(ancho * 2, alto * 2, ancho, alto, null), "png", png);
        Image img = Image.getInstance(png.toByteArray());
        img.scaleToFit(PageSize.A4.getWidth() - 72, alto * (PageSize.A4.getWidth() - 72) / ancho);
        img.setAlignment(Element.ALIGN_CENTER);
        return img;
    }

    private static void espacio(Document doc, float alto) throws DocumentException {
        Paragraph p = new Paragraph(" ");
        p.setSpacingAfter(alto);
        doc.add(p);
    }
}