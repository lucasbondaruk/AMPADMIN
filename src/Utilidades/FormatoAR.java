package Utilidades;

import java.text.NumberFormat;
import java.util.Locale;

/** Formatos numéricos argentinos usados en hallazgos, pantalla y PDF. */
public final class FormatoAR {

    public static final Locale ES_AR = Locale.forLanguageTag("es-AR");

    private FormatoAR() {
    }

    /** $ 1.234.567 (sin decimales). */
    public static String pesos(double v) {
        NumberFormat f = NumberFormat.getNumberInstance(ES_AR);
        f.setMaximumFractionDigits(0);
        return "$ " + f.format(Math.round(v));
    }

    /** 12,5 % */
    public static String pct(double v) {
        return String.format(ES_AR, "%.1f %%", v);
    }

    /** 12,5 (un decimal) */
    public static String num(double v) {
        return String.format(ES_AR, "%.1f", v);
    }

    /** p-valor legible: "p = 0,012" o "p < 0,001". */
    public static String p(double v) {
        return v < 0.001 ? "p < 0,001" : String.format(ES_AR, "p = %.3f", v);
    }
}
