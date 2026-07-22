package Utilidades;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.jfree.chart.JFreeChart;

public class GeneradorPDF {

    private static final BaseColor COLOR_PRINCIPAL =
            new BaseColor(38, 90, 142);

    private GeneradorPDF() {
        // Evita crear objetos innecesarios de esta clase.
    }

    public static void generarInformeVentas(
        File archivo,
        String totalVentas,
        String ingresosTotales,
        String ticketPromedio,
        JFreeChart graficoVentas,
        JFreeChart graficoMetodosPago,
        String interpretacionVentas,
        String interpretacionMetodos
    ) throws DocumentException, IOException {

        Document documento = new Document(
                PageSize.A4.rotate(),
                35,
                35,
                30,
                30
        );

        PdfWriter.getInstance(
                documento,
                new FileOutputStream(archivo)
        );

        documento.open();

        agregarEncabezado(documento);
        agregarIndicadores(
                documento,
                totalVentas,
                ingresosTotales,
                ticketPromedio
        );

        agregarEspacio(documento, 12);

        agregarGraficos(
                documento,
                graficoVentas,
                graficoMetodosPago,
                interpretacionVentas,
                interpretacionMetodos
        );

        agregarEspacio(documento, 12);
        agregarPie(documento);

        documento.close();
    }

    private static void agregarEncabezado(Document documento)
            throws DocumentException {

        Font fuenteSistema = new Font(
                Font.FontFamily.HELVETICA,
                16,
                Font.BOLD,
                COLOR_PRINCIPAL
        );

        Font fuenteTitulo = new Font(
                Font.FontFamily.HELVETICA,
                22,
                Font.BOLD,
                COLOR_PRINCIPAL
        );

        Font fuenteFecha = new Font(
                Font.FontFamily.HELVETICA,
                10,
                Font.NORMAL,
                BaseColor.DARK_GRAY
        );

        Paragraph sistema = new Paragraph(
                "AMPADMIN",
                fuenteSistema
        );

        sistema.setAlignment(Element.ALIGN_CENTER);
        documento.add(sistema);

        Paragraph titulo = new Paragraph(
                "Informe de estadísticas de ventas",
                fuenteTitulo
        );

        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingBefore(4);
        documento.add(titulo);

        String fecha = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        );

        Paragraph fechaGeneracion = new Paragraph(
                "Generado el " + fecha,
                fuenteFecha
        );

        fechaGeneracion.setAlignment(Element.ALIGN_CENTER);
        fechaGeneracion.setSpacingBefore(5);
        fechaGeneracion.setSpacingAfter(16);

        documento.add(fechaGeneracion);
    }

    private static void agregarIndicadores(
            Document documento,
            String totalVentas,
            String ingresosTotales,
            String ticketPromedio
    ) throws DocumentException {

        PdfPTable tabla = new PdfPTable(3);

        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{1, 1, 1});
        tabla.setSpacingAfter(8);

        tabla.addCell(
                crearCeldaIndicador(
                        "TOTAL DE VENTAS",
                        totalVentas
                )
        );

        tabla.addCell(
                crearCeldaIndicador(
                        "INGRESOS TOTALES",
                        ingresosTotales
                )
        );

        tabla.addCell(
                crearCeldaIndicador(
                        "TICKET PROMEDIO",
                        ticketPromedio
                )
        );

        documento.add(tabla);
    }

    private static PdfPCell crearCeldaIndicador(
            String titulo,
            String valor
    ) {

        Font fuenteTitulo = new Font(
                Font.FontFamily.HELVETICA,
                10,
                Font.BOLD,
                BaseColor.DARK_GRAY
        );

        Font fuenteValor = new Font(
                Font.FontFamily.HELVETICA,
                15,
                Font.BOLD,
                COLOR_PRINCIPAL
        );

        Paragraph contenido = new Paragraph();

        contenido.add(new Phrase(titulo + "\n", fuenteTitulo));
        contenido.add(new Phrase(valor, fuenteValor));

        PdfPCell celda = new PdfPCell(contenido);

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(12);
        celda.setBackgroundColor(new BaseColor(242, 242, 242));
        celda.setBorderColor(BaseColor.WHITE);
        celda.setBorderWidth(5);

        return celda;
    }

    private static void agregarGraficos(
        Document documento,
        JFreeChart graficoVentas,
        JFreeChart graficoMetodosPago,
        String interpretacionVentas,
        String interpretacionMetodos
    ) throws DocumentException, IOException {

        PdfPTable tablaGraficos = new PdfPTable(2);

        tablaGraficos.setWidthPercentage(100);
        tablaGraficos.setWidths(new float[]{1, 1});

        Image imagenVentas =
                convertirGraficoEnImagen(graficoVentas, 900, 600);

        Image imagenMetodos =
                convertirGraficoEnImagen(graficoMetodosPago, 900, 600);

        imagenVentas.scaleToFit(355, 275);
        imagenMetodos.scaleToFit(355, 275);

        PdfPCell celdaVentas =
        crearCeldaGrafico(imagenVentas, interpretacionVentas);

        PdfPCell celdaMetodos =
        crearCeldaGrafico(imagenMetodos, interpretacionMetodos);

        configurarCeldaGrafico(celdaVentas);
        configurarCeldaGrafico(celdaMetodos);

        tablaGraficos.addCell(celdaVentas);
        tablaGraficos.addCell(celdaMetodos);

        documento.add(tablaGraficos);
    }

    private static void configurarCeldaGrafico(PdfPCell celda) {

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(8);
        celda.setBorderColor(new BaseColor(220, 220, 220));
    }

    private static Image convertirGraficoEnImagen(
        JFreeChart grafico,
        int ancho,
        int alto
    ) throws IOException, DocumentException {

        ByteArrayOutputStream salida =
                new ByteArrayOutputStream();

        javax.imageio.ImageIO.write(
                grafico.createBufferedImage(ancho, alto),
                "png",
                salida
        );

        return Image.getInstance(
                salida.toByteArray()
        );
    }

    private static void agregarPie(Document documento)
            throws DocumentException {

        Font fuente = new Font(
                Font.FontFamily.HELVETICA,
                9,
                Font.ITALIC,
                BaseColor.GRAY
        );

        Paragraph pie = new Paragraph(
                "Informe generado automáticamente por AMPADMIN.",
                fuente
        );

        pie.setAlignment(Element.ALIGN_CENTER);
        documento.add(pie);
    }

    private static void agregarEspacio(
                Document documento,
                float espacio
        ) throws DocumentException {

            Paragraph separacion = new Paragraph(" ");
            separacion.setSpacingAfter(espacio);

            documento.add(separacion);
        }

    private static PdfPCell crearCeldaGrafico(
        Image imagen,
        String descripcion
    ) {

        PdfPTable contenido = new PdfPTable(1);
        contenido.setWidthPercentage(100);

        PdfPCell celdaImagen = new PdfPCell(imagen, true);
        celdaImagen.setBorder(PdfPCell.NO_BORDER);
        celdaImagen.setHorizontalAlignment(Element.ALIGN_CENTER);

        Font fuenteDescripcion = new Font(
                Font.FontFamily.HELVETICA,
                9,
                Font.NORMAL,
                BaseColor.DARK_GRAY
        );

        Paragraph texto = new Paragraph(descripcion, fuenteDescripcion);
        texto.setAlignment(Element.ALIGN_JUSTIFIED);

        PdfPCell celdaDescripcion = new PdfPCell(texto);
        celdaDescripcion.setBorder(PdfPCell.NO_BORDER);
        celdaDescripcion.setPaddingTop(6);

        contenido.addCell(celdaImagen);
        contenido.addCell(celdaDescripcion);

        PdfPCell celdaPrincipal = new PdfPCell(contenido);
        configurarCeldaGrafico(celdaPrincipal);

        return celdaPrincipal;
    }
    
    public static void generarInformeClientes(
        File archivo,
        String totalClientes,
        String totalEmpresas,
        String totalPersonas,
        JFreeChart graficoClientesTipo,
        JFreeChart graficoClientesIVA,
        String interpretacionTipo,
        String interpretacionIVA
    ) throws DocumentException, IOException {

        Document documento = new Document(
                PageSize.A4.rotate(),
                35,
                35,
                30,
                30
        );

        PdfWriter.getInstance(
                documento,
                new FileOutputStream(archivo)
        );

        documento.open();

        agregarEncabezadoClientes(documento);

        agregarIndicadoresClientes(
                documento,
                totalClientes,
                totalEmpresas,
                totalPersonas
        );

        agregarEspacio(documento, 12);

        agregarGraficosClientes(
                documento,
                graficoClientesTipo,
                graficoClientesIVA,
                interpretacionTipo,
                interpretacionIVA
        );

        agregarEspacio(documento, 12);
        agregarPie(documento);

        documento.close();
    }
    
    private static void agregarEncabezadoClientes(
        Document documento
    ) throws DocumentException {

        Font fuenteSistema = new Font(
                Font.FontFamily.HELVETICA,
                16,
                Font.BOLD,
                COLOR_PRINCIPAL
        );

        Font fuenteTitulo = new Font(
                Font.FontFamily.HELVETICA,
                22,
                Font.BOLD,
                COLOR_PRINCIPAL
        );

        Font fuenteFecha = new Font(
                Font.FontFamily.HELVETICA,
                10,
                Font.NORMAL,
                BaseColor.DARK_GRAY
        );

        Paragraph sistema = new Paragraph(
                "AMPADMIN",
                fuenteSistema
        );

        sistema.setAlignment(Element.ALIGN_CENTER);
        documento.add(sistema);

        Paragraph titulo = new Paragraph(
                "Informe de estadísticas de clientes",
                fuenteTitulo
        );

        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingBefore(4);
        documento.add(titulo);

        String fecha = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy HH:mm:ss"
                )
        );

        Paragraph fechaGeneracion = new Paragraph(
                "Generado el " + fecha,
                fuenteFecha
        );

        fechaGeneracion.setAlignment(Element.ALIGN_CENTER);
        fechaGeneracion.setSpacingBefore(5);
        fechaGeneracion.setSpacingAfter(16);

        documento.add(fechaGeneracion);
    }
    
    private static void agregarIndicadoresClientes(
        Document documento,
        String totalClientes,
        String totalEmpresas,
        String totalPersonas
    ) throws DocumentException {

        PdfPTable tabla = new PdfPTable(3);

        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{1, 1, 1});
        tabla.setSpacingAfter(8);

        tabla.addCell(
                crearCeldaIndicador(
                        "TOTAL DE CLIENTES",
                        totalClientes
                )
        );

        tabla.addCell(
                crearCeldaIndicador(
                        "EMPRESAS",
                        totalEmpresas
                )
        );

        tabla.addCell(
                crearCeldaIndicador(
                        "PERSONAS",
                        totalPersonas
                )
        );

        documento.add(tabla);
    }
    
    private static void agregarGraficosClientes(
        Document documento,
        JFreeChart graficoClientesTipo,
        JFreeChart graficoClientesIVA,
        String interpretacionTipo,
        String interpretacionIVA
    ) throws DocumentException, IOException {

        PdfPTable tablaGraficos = new PdfPTable(2);

        tablaGraficos.setWidthPercentage(100);
        tablaGraficos.setWidths(new float[]{1, 1});

        Image imagenTipo =
                convertirGraficoEnImagen(
                        graficoClientesTipo,
                        900,
                        600
                );

        Image imagenIVA =
                convertirGraficoEnImagen(
                        graficoClientesIVA,
                        900,
                        600
                );

        imagenTipo.scaleToFit(355, 275);
        imagenIVA.scaleToFit(355, 275);

        PdfPCell celdaTipo =
                crearCeldaGrafico(
                        imagenTipo,
                        interpretacionTipo
                );

        PdfPCell celdaIVA =
                crearCeldaGrafico(
                        imagenIVA,
                        interpretacionIVA
                );

        configurarCeldaGrafico(celdaTipo);
        configurarCeldaGrafico(celdaIVA);

        tablaGraficos.addCell(celdaTipo);
        tablaGraficos.addCell(celdaIVA);

        documento.add(tablaGraficos);
    }
    
}