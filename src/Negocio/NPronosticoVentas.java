package Negocio;

import Modelos.InformeIA.EvaluacionModelo;
import Modelos.InformeIA.Granularidad;
import Modelos.InformeIA.Pronostico;
import Modelos.InformeIA.PuntoSerie;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Pronóstico de ventas con selección automática de modelo (sin librerías externas).
 *
 * <b>Técnica:</b> validación cruzada temporal con origen móvil ("rolling-origin" / backtesting).
 * Se simula varias veces el pasado: se entrena con los datos hasta un mes, se pronostican los
 * meses siguientes y se compara contra lo que realmente se vendió. Gana el modelo con menor
 * error absoluto medio (MAE). Nunca se usa información "del futuro" para entrenar.
 *
 * Candidatos, de más simple a más complejo:
 * <ol>
 *   <li>Ingenuo: igual que el último período (referencia mínima a superar).</li>
 *   <li>Media móvil de 3 períodos.</li>
 *   <li>Ingenuo estacional: igual que el mismo período del año anterior.</li>
 *   <li>Holt con tendencia amortiguada.</li>
 *   <li>Holt-Winters aditivo (tendencia + estacionalidad anual), con 2 años de historia o más.</li>
 * </ol>
 * La banda del 95 % sale de los errores reales de la validación para cada distancia (1, 2, 3...
 * períodos adelante), no de una fórmula teórica.
 */
public final class NPronosticoVentas {

    public static final int MINIMO_PERIODOS = 4;
    private static final int MAX_ORIGENES = 8;
    private static final double[] GRILLA = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
    private static final double[] GRILLA_PHI = {0.8, 0.85, 0.9, 0.95, 0.98};
    private static final double Z_95 = 1.96;

    private NPronosticoVentas() {
    }

    /** Un modelo candidato. */
    private interface Modelo {
        String nombre();

        boolean aplicable(int n, int m);

        double[] pronosticar(double[] y, int h, int m);
    }

    private static final List<Modelo> CANDIDATOS = List.of(
            new Modelo() {
                public String nombre() { return "Ingenuo (igual al último período)"; }
                public boolean aplicable(int n, int m) { return n >= 1; }
                public double[] pronosticar(double[] y, int h, int m) {
                    double[] f = new double[h];
                    Arrays.fill(f, y[y.length - 1]);
                    return f;
                }
            },
            new Modelo() {
                public String nombre() { return "Media móvil de 3 períodos"; }
                public boolean aplicable(int n, int m) { return n >= 3; }
                public double[] pronosticar(double[] y, int h, int m) {
                    int n = y.length;
                    double[] f = new double[h];
                    Arrays.fill(f, (y[n - 1] + y[n - 2] + y[n - 3]) / 3.0);
                    return f;
                }
            },
            new Modelo() {
                public String nombre() { return "Ingenuo estacional (igual al año anterior)"; }
                public boolean aplicable(int n, int m) { return n >= m; }
                public double[] pronosticar(double[] y, int h, int m) {
                    int n = y.length;
                    double[] f = new double[h];
                    for (int i = 0; i < h; i++) {
                        f[i] = y[n - m + (i % m)];
                    }
                    return f;
                }
            },
            new Modelo() {
                public String nombre() { return "Holt con tendencia amortiguada"; }
                public boolean aplicable(int n, int m) { return n >= MINIMO_PERIODOS; }
                public double[] pronosticar(double[] y, int h, int m) { return mejorHolt(y, h).pronostico; }
            },
            new Modelo() {
                public String nombre() { return "Holt-Winters (tendencia + estacionalidad anual)"; }
                public boolean aplicable(int n, int m) { return n >= 2 * m; }
                public double[] pronosticar(double[] y, int h, int m) { return mejorHoltWinters(y, h, m).pronostico; }
            });

    /**
     * @param historico serie de períodos completos, ordenada, sin huecos y ya limpia de atípicos
     * @param horizonte cuántos períodos hacia adelante pronosticar
     * @return el pronóstico, o null si hay menos de {@link #MINIMO_PERIODOS} períodos
     */
    public static Pronostico pronosticar(List<PuntoSerie> historico, int horizonte, Granularidad g) {
        int n = historico.size();
        if (n < MINIMO_PERIODOS || horizonte < 1) {
            return null;
        }
        double[] y = historico.stream().mapToDouble(PuntoSerie::valor).toArray();
        int m = g.periodoEstacional;

        // ---- Validación cruzada temporal
        // Si alcanza, se valida desde 2 ciclos completos para que Holt-Winters también compita
        int origenes = n - 2 * m >= 3
                ? Math.min(MAX_ORIGENES, n - 2 * m)
                : Math.min(MAX_ORIGENES, n - MINIMO_PERIODOS);
        int primerEntrenamiento = n - origenes;
        List<Resultado> resultados = new ArrayList<>();
        if (origenes >= 1) {
            for (Modelo modelo : CANDIDATOS) {
                if (modelo.aplicable(primerEntrenamiento, m)) {
                    resultados.add(validar(modelo, y, primerEntrenamiento, horizonte, m));
                }
            }
        }

        Modelo elegido;
        Resultado res;
        if (resultados.isEmpty()) { // sin datos para validar: Holt por defecto
            elegido = CANDIDATOS.get(3);
            res = null;
        } else {
            res = resultados.stream().min(Comparator.comparingDouble(r -> r.mae)).orElseThrow();
            elegido = res.modelo;
        }

        double[] f = elegido.pronosticar(y, horizonte, m);
        double[] sigma = sigmasPorHorizonte(res, horizonte, y);

        List<PuntoSerie> valores = new ArrayList<>();
        List<PuntoSerie> inferior = new ArrayList<>();
        List<PuntoSerie> superior = new ArrayList<>();
        LocalDate fecha = historico.get(n - 1).inicio();
        for (int h = 0; h < horizonte; h++) {
            fecha = g.siguiente(fecha);
            double valor = Math.max(0, f[h]);
            double margen = Z_95 * sigma[h];
            valores.add(new PuntoSerie(fecha, valor));
            inferior.add(new PuntoSerie(fecha, Math.max(0, valor - margen)));
            superior.add(new PuntoSerie(fecha, valor + margen));
        }

        List<EvaluacionModelo> evaluados = resultados.stream()
                .sorted(Comparator.comparingDouble(r -> r.mae))
                .map(r -> new EvaluacionModelo(r.modelo.nombre(), r.mae, r.mape))
                .toList();

        List<String> avisos = new ArrayList<>();
        if (res == null) {
            avisos.add("Con " + n + " períodos no alcanza para validar modelos; se usó Holt sin medir su error.");
        } else if (elegido == CANDIDATOS.get(0) || elegido == CANDIDATOS.get(1)) {
            avisos.add("Ningún modelo con tendencia o estacionalidad superó a la referencia simple: "
                    + "las ventas no muestran un patrón predecible todavía.");
        }
        if (n < 2 * m) {
            avisos.add("Hay " + n + " períodos de historia; con menos de " + (2 * m)
                    + " no se puede medir la estacionalidad anual.");
        }
        if (res != null && !Double.isNaN(res.mape) && res.mape > 30) {
            avisos.add("El error de validación es alto (" + Math.round(res.mape) + " %): tomar el pronóstico como orientativo.");
        }
        return new Pronostico(elegido.nombre(), valores, inferior, superior,
                res == null ? Double.NaN : res.mape, res == null ? Double.NaN : res.mae,
                evaluados, avisos.isEmpty() ? null : String.join(" ", avisos));
    }

    // ------------------------------------------------------------------ validación

    private static final class Resultado {
        Modelo modelo;
        double mae;
        double mape;
        double[] sumaCuadrados;
        int[] cuenta;
    }

    /** Origen móvil: entrena con y[0..L), pronostica y[L..L+h) para L = primero..n-1. */
    private static Resultado validar(Modelo modelo, double[] y, int primero, int horizonte, int m) {
        Resultado r = new Resultado();
        r.modelo = modelo;
        r.sumaCuadrados = new double[horizonte];
        r.cuenta = new int[horizonte];
        double sumaAbs = 0;
        int nAbs = 0;
        double sumaPct = 0;
        int nPct = 0;
        for (int largo = primero; largo < y.length; largo++) {
            int h = Math.min(horizonte, y.length - largo);
            double[] f = modelo.pronosticar(Arrays.copyOf(y, largo), h, m);
            for (int i = 0; i < h; i++) {
                double real = y[largo + i];
                double e = real - Math.max(0, f[i]);
                sumaAbs += Math.abs(e);
                nAbs++;
                r.sumaCuadrados[i] += e * e;
                r.cuenta[i]++;
                if (real != 0) {
                    sumaPct += Math.abs(e / real);
                    nPct++;
                }
            }
        }
        r.mae = sumaAbs / nAbs;
        r.mape = nPct == 0 ? Double.NaN : sumaPct / nPct * 100.0;
        return r;
    }

    /** Desvío del error para cada distancia de pronóstico; nunca decrece con la distancia. */
    private static double[] sigmasPorHorizonte(Resultado r, int horizonte, double[] y) {
        double[] s = new double[horizonte];
        double base;
        if (r != null && r.cuenta[0] > 0) {
            base = Math.sqrt(r.sumaCuadrados[0] / r.cuenta[0]);
        } else {
            base = mejorHolt(y, 1).sigma(); // error dentro de la muestra
        }
        for (int h = 0; h < horizonte; h++) {
            double sh = (r != null && r.cuenta[h] >= 2)
                    ? Math.sqrt(r.sumaCuadrados[h] / r.cuenta[h])
                    : base * Math.sqrt(h + 1.0);
            s[h] = h == 0 ? sh : Math.max(sh, s[h - 1]);
        }
        return s;
    }

    // ------------------------------------------------------------------ suavizado exponencial

    private record Ajuste(double[] pronostico, double sse, int errores) {
        double sigma() {
            return errores == 0 ? 0 : Math.sqrt(sse / errores);
        }
    }

    private static Ajuste mejorHolt(double[] y, int h) {
        Ajuste mejor = null;
        for (double a : GRILLA) {
            for (double b : GRILLA) {
                for (double phi : GRILLA_PHI) {
                    Ajuste aj = holtAmortiguado(y, a, b * 0.5, phi, h); // beta chico = tendencia estable
                    if (mejor == null || aj.sse() < mejor.sse()) {
                        mejor = aj;
                    }
                }
            }
        }
        return mejor;
    }

    private static Ajuste mejorHoltWinters(double[] y, int h, int m) {
        Ajuste mejor = null;
        for (double a : GRILLA) {
            for (double b : GRILLA) {
                for (double c : GRILLA) {
                    Ajuste aj = holtWinters(y, a, b * 0.5, c, m, h);
                    if (mejor == null || aj.sse() < mejor.sse()) {
                        mejor = aj;
                    }
                }
            }
        }
        return mejor;
    }

    /** Holt con tendencia amortiguada (damped trend). */
    private static Ajuste holtAmortiguado(double[] y, double alfa, double beta, double phi, int horizonte) {
        double nivel = y[0];
        double tendencia = y[1] - y[0];
        double sse = 0;
        int errores = 0;
        for (int t = 1; t < y.length; t++) {
            double prediccion = nivel + phi * tendencia;
            if (t >= 2) { // en t=1 la predicción es exacta por la inicialización
                double e = y[t] - prediccion;
                sse += e * e;
                errores++;
            }
            double nivelNuevo = alfa * y[t] + (1 - alfa) * prediccion;
            tendencia = beta * (nivelNuevo - nivel) + (1 - beta) * phi * tendencia;
            nivel = nivelNuevo;
        }
        double[] f = new double[horizonte];
        double sumaPhi = 0;
        for (int h = 1; h <= horizonte; h++) {
            sumaPhi += Math.pow(phi, h);
            f[h - 1] = nivel + sumaPhi * tendencia;
        }
        return new Ajuste(f, sse, errores);
    }

    /** Holt-Winters aditivo con estacionalidad de período m. */
    private static Ajuste holtWinters(double[] y, double alfa, double beta, double gamma, int m, int horizonte) {
        int n = y.length;
        double media1 = promedio(y, 0, m);
        double media2 = promedio(y, m, 2 * m);
        double nivel = media1;
        double tendencia = (media2 - media1) / m;
        double[] estacional = new double[n];
        for (int i = 0; i < m; i++) {
            estacional[i] = y[i] - media1;
        }
        double sse = 0;
        int errores = 0;
        for (int t = m; t < n; t++) {
            double prediccion = nivel + tendencia + estacional[t - m];
            double e = y[t] - prediccion;
            sse += e * e;
            errores++;
            double nivelNuevo = alfa * (y[t] - estacional[t - m]) + (1 - alfa) * (nivel + tendencia);
            tendencia = beta * (nivelNuevo - nivel) + (1 - beta) * tendencia;
            estacional[t] = gamma * (y[t] - nivelNuevo) + (1 - gamma) * estacional[t - m];
            nivel = nivelNuevo;
        }
        double[] f = new double[horizonte];
        for (int h = 1; h <= horizonte; h++) {
            f[h - 1] = nivel + h * tendencia + estacional[n - m + (h - 1) % m];
        }
        return new Ajuste(f, sse, errores);
    }

    private static double promedio(double[] y, int desde, int hasta) {
        double s = 0;
        for (int i = desde; i < hasta; i++) {
            s += y[i];
        }
        return s / (hasta - desde);
    }
}
