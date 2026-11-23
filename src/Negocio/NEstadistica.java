package Negocio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pruebas estadísticas no paramétricas (no suponen distribución normal, que en ventas
 * de un comercio casi nunca se cumple). Sirven para que el informe no "interprete" como
 * cambio real algo que es solo variación normal.
 */
public final class NEstadistica {

    private NEstadistica() {
    }

    /** Resultado de Mann-Kendall: z, p-valor bilateral y pendiente de Sen (unidades por período). */
    public record Tendencia(double z, double p, double pendienteSen) {
        public boolean significativa() {
            return p < 0.05;
        }
    }

    /** Resultado de Mann-Whitney: z y p-valor bilateral. */
    public record Comparacion(double z, double p) {
        public boolean significativa() {
            return p < 0.05;
        }
    }

    /**
     * Prueba de tendencia de Mann-Kendall con pendiente de Sen.
     * Detecta si la serie sube o baja de forma sostenida, sin dejarse engañar por un pico aislado.
     */
    public static Tendencia mannKendall(double[] y) {
        int n = y.length;
        long s = 0;
        List<Double> pendientes = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                s += Long.signum(Double.compare(y[j], y[i]));
                pendientes.add((y[j] - y[i]) / (j - i));
            }
        }
        double varS = n * (n - 1.0) * (2 * n + 5.0);
        for (int t : tamaniosDeEmpates(y)) {
            varS -= t * (t - 1.0) * (2 * t + 5.0);
        }
        varS /= 18.0;
        double z = 0;
        if (varS > 0) {
            if (s > 0) {
                z = (s - 1) / Math.sqrt(varS);
            } else if (s < 0) {
                z = (s + 1) / Math.sqrt(varS);
            }
        }
        double sen = NLimpiezaDatos.mediana(pendientes.stream().mapToDouble(Double::doubleValue).toArray());
        return new Tendencia(z, pBilateral(z), sen);
    }

    /**
     * Prueba U de Mann-Whitney (con corrección por empates y de continuidad).
     * Compara las ventas diarias de dos períodos: ¿la diferencia es real o puede ser azar?
     */
    public static Comparacion mannWhitney(double[] a, double[] b) {
        int n1 = a.length;
        int n2 = b.length;
        int n = n1 + n2;
        double[][] todos = new double[n][2]; // valor, grupo
        for (int i = 0; i < n1; i++) {
            todos[i] = new double[]{a[i], 0};
        }
        for (int i = 0; i < n2; i++) {
            todos[n1 + i] = new double[]{b[i], 1};
        }
        Arrays.sort(todos, (x, w) -> Double.compare(x[0], w[0]));
        double sumaRangosA = 0;
        double correccionEmpates = 0;
        int i = 0;
        while (i < n) {
            int j = i;
            while (j + 1 < n && todos[j + 1][0] == todos[i][0]) {
                j++;
            }
            double rangoMedio = (i + j) / 2.0 + 1;
            int t = j - i + 1;
            correccionEmpates += (double) t * t * t - t;
            for (int k = i; k <= j; k++) {
                if (todos[k][1] == 0) {
                    sumaRangosA += rangoMedio;
                }
            }
            i = j + 1;
        }
        double u = sumaRangosA - n1 * (n1 + 1) / 2.0;
        double media = n1 * (double) n2 / 2.0;
        double varianza = n1 * (double) n2 / 12.0 * ((n + 1) - correccionEmpates / (n * (n - 1.0)));
        if (varianza <= 0) {
            return new Comparacion(0, 1);
        }
        double dif = u - media;
        double z = (Math.abs(dif) - 0.5) / Math.sqrt(varianza) * Math.signum(dif);
        return new Comparacion(z, pBilateral(z));
    }

    /**
     * Prueba exacta de Poisson para comparar dos conteos (cantidad de ventas en dos períodos).
     *
     * Si las ventas llegan al azar a un ritmo constante, dado el total n = n1 + n2, la cantidad
     * del primer período sigue una binomial con p = t1 / (t1 + t2). Se calcula el p-valor exacto
     * bilateral (suma de los resultados tan o menos probables que el observado).
     *
     * @param n1 ventas del período 1
     * @param t1 duración del período 1 (días)
     * @param n2 ventas del período 2
     * @param t2 duración del período 2 (días)
     */
    public static Comparacion poissonDosPeriodos(long n1, double t1, long n2, double t2) {
        long n = n1 + n2;
        if (n == 0 || t1 <= 0 || t2 <= 0) {
            return new Comparacion(0, 1);
        }
        double prob = t1 / (t1 + t2);
        double[] logFact = new double[(int) n + 1];
        for (int i = 1; i <= n; i++) {
            logFact[i] = logFact[i - 1] + Math.log(i);
        }
        double logP = Math.log(prob);
        double logQ = Math.log1p(-prob);
        double[] logPmf = new double[(int) n + 1];
        for (int k = 0; k <= n; k++) {
            logPmf[k] = logFact[(int) n] - logFact[k] - logFact[(int) (n - k)] + k * logP + (n - k) * logQ;
        }
        double observado = logPmf[(int) n1];
        double p = 0;
        for (int k = 0; k <= n; k++) {
            if (logPmf[k] <= observado + 1e-7) {
                p += Math.exp(logPmf[k]);
            }
        }
        double esperado = n * prob;
        double z = (n1 - esperado) / Math.sqrt(n * prob * (1 - prob));
        return new Comparacion(z, Math.min(1, p));
    }

    /** p-valor bilateral de una normal estándar. */
    static double pBilateral(double z) {
        return Math.min(1, 2 * (1 - phi(Math.abs(z))));
    }

    /** Función de distribución normal estándar (aproximación de Abramowitz y Stegun 7.1.26). */
    static double phi(double x) {
        double t = 1 / (1 + 0.3275911 * Math.abs(x) / Math.sqrt(2));
        double erf = 1 - (((((1.061405429 * t - 1.453152027) * t) + 1.421413741) * t - 0.284496736) * t
                + 0.254829592) * t * Math.exp(-x * x / 2);
        return x >= 0 ? (1 + erf) / 2 : (1 - erf) / 2;
    }

    private static List<Integer> tamaniosDeEmpates(double[] y) {
        double[] v = y.clone();
        Arrays.sort(v);
        List<Integer> grupos = new ArrayList<>();
        int i = 0;
        while (i < v.length) {
            int j = i;
            while (j + 1 < v.length && v[j + 1] == v[i]) {
                j++;
            }
            if (j > i) {
                grupos.add(j - i + 1);
            }
            i = j + 1;
        }
        return grupos;
    }
}