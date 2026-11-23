package Negocio;

import Modelos.InformeIA.PuntoSerie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Detección robusta de valores atípicos (outliers).
 *
 * Se usa la mediana y la MAD (desviación absoluta mediana) en lugar de media y desvío,
 * porque justamente los atípicos inflan la media y el desvío y se "esconden" solos.
 * El criterio es el puntaje z modificado de Iglewicz y Hoaglin: |z| > 3,5 es atípico.
 *
 * - Tickets: z robusto sobre el logaritmo del total de cada venta (los montos son asimétricos).
 * - Serie de períodos: filtro de Hampel sobre los residuos de una descomposición robusta
 *   (tendencia = mediana móvil, estacionalidad = mediana por mes del año). Un diciembre alto
 *   NO es atípico si todos los diciembres son altos. Además tiene que apartarse más de un 25 %
 *   de lo esperado, para no marcar variaciones chicas como atípicas.
 */
public final class NLimpiezaDatos {

    static final double UMBRAL_Z = 3.5;
    static final double DESVIO_MINIMO = 0.25;
    private static final double K_MAD = 0.6745; // hace la MAD comparable con el desvío estándar

    private NLimpiezaDatos() {
    }

    /** Resultado de limpiar la serie de períodos. */
    public record SerieLimpia(List<PuntoSerie> serie, List<PuntoSerie> atipicos, boolean corregida) {}

    /** Cantidad de ventas cuyo total es atípico (muy alto o muy bajo) respecto del resto. */
    public static int contarTicketsAtipicos(List<Double> totales) {
        if (totales.size() < 10) {
            return 0;
        }
        double[] logs = totales.stream().filter(t -> t > 0).mapToDouble(Math::log).toArray();
        double[] z = puntajesZRobustos(logs);
        int cuenta = 0;
        for (double v : z) {
            if (Math.abs(v) > UMBRAL_Z) {
                cuenta++;
            }
        }
        return cuenta;
    }

    /**
     * Marca los períodos atípicos. Si hay al menos dos ciclos (2 años) se puede separar
     * la estacionalidad con confianza, y entonces los atípicos se reemplazan por el valor
     * esperado en la serie que se usa para entrenar el pronóstico. Con menos historia solo
     * se informan, para no "borrar" un pico de temporada real.
     */
    public static SerieLimpia limpiarSerie(List<PuntoSerie> serie, int periodoEstacional) {
        int n = serie.size();
        if (n < 6) {
            return new SerieLimpia(serie, List.of(), false);
        }
        double[] y = serie.stream().mapToDouble(PuntoSerie::valor).toArray();
        int k = periodoEstacional >= 52 ? 3 : 2; // ventana de 5 meses o 7 semanas

        double[] tendencia = new double[n];
        for (int t = 0; t < n; t++) {
            tendencia[t] = mediana(Arrays.copyOfRange(y, Math.max(0, t - k), Math.min(n, t + k + 1)));
        }
        boolean conEstacionalidad = n >= 2 * periodoEstacional;
        double[] estacional = new double[n];
        if (conEstacionalidad) {
            for (int p = 0; p < periodoEstacional; p++) {
                List<Double> valores = new ArrayList<>();
                for (int t = p; t < n; t += periodoEstacional) {
                    valores.add(y[t] - tendencia[t]);
                }
                double med = mediana(valores.stream().mapToDouble(Double::doubleValue).toArray());
                for (int t = p; t < n; t += periodoEstacional) {
                    estacional[t] = med;
                }
            }
        }
        double[] residuo = new double[n];
        for (int t = 0; t < n; t++) {
            residuo[t] = y[t] - tendencia[t] - estacional[t];
        }
        double[] z = puntajesZRobustos(residuo);

        List<PuntoSerie> limpia = new ArrayList<>();
        List<PuntoSerie> atipicos = new ArrayList<>();
        for (int t = 0; t < n; t++) {
            PuntoSerie p = serie.get(t);
            // Atípico = estadísticamente raro (|z| > 3,5) Y relevante (se aparta más de 25 % de lo esperado).
            // La segunda condición evita marcar ruido chico cuando la serie es muy regular.
            double esperado = Math.max(1, Math.abs(tendencia[t] + estacional[t]));
            if (Math.abs(z[t]) > UMBRAL_Z && Math.abs(residuo[t]) > DESVIO_MINIMO * esperado) {
                atipicos.add(p);
                limpia.add(conEstacionalidad
                        ? new PuntoSerie(p.inicio(), Math.max(0, tendencia[t] + estacional[t]))
                        : p);
            } else {
                limpia.add(p);
            }
        }
        return new SerieLimpia(limpia, atipicos, conEstacionalidad && !atipicos.isEmpty());
    }

    /** z modificado = 0,6745 · (x − mediana) / MAD. Devuelve ceros si los datos no varían. */
    static double[] puntajesZRobustos(double[] x) {
        double[] z = new double[x.length];
        if (x.length == 0) {
            return z;
        }
        double med = mediana(x);
        double[] desvios = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            desvios[i] = Math.abs(x[i] - med);
        }
        double mad = mediana(desvios);
        if (mad == 0) {
            // Más de la mitad de los valores son iguales (p. ej. muchos meses en cero):
            // se usa la desviación absoluta media, z = (x − mediana) / (1,2533 · MeanAD).
            double meanAD = Arrays.stream(desvios).average().orElse(0);
            if (meanAD == 0) {
                return z;
            }
            mad = K_MAD * 1.253314 * meanAD;
        }
        for (int i = 0; i < x.length; i++) {
            z[i] = K_MAD * (x[i] - med) / mad;
        }
        return z;
    }

    static double mediana(double[] valores) {
        if (valores.length == 0) {
            return 0;
        }
        double[] v = valores.clone();
        Arrays.sort(v);
        int m = v.length / 2;
        return v.length % 2 == 1 ? v[m] : (v[m - 1] + v[m]) / 2.0;
    }
}
