# Informe de ventas con IA (AMPADMIN)

## Cómo funciona

```
SQL Server
   │
   ├─► DCalidadDatos + NLimpiezaDatos   1. LIMPIEZA: validaciones + detección robusta de atípicos
   ├─► DAnalisisVentas                  2. ESTADÍSTICA: KPIs, rankings, stock
   ├─► NPronosticoVentas                3. PREDICCIÓN: 5 modelos, gana el mejor en validación cruzada
   ├─► NHallazgos + NEstadistica        4. HALLAZGOS: frases con números y pruebas de significancia
   └─► ServicioIA (Ollama) + NVerificadorIA
                                        5. REDACCIÓN: la IA redacta SOLO con los hallazgos y se verifica
```

**La IA no calcula ni interpreta números.** Todo lo cuantitativo lo hace Java. La IA (un
modelo local chico) solo redacta, prioriza y recomienda, y su respuesta se verifica antes de
mostrarla. Si falla, el informe usa un análisis automático por reglas. Nunca queda vacío.

## 1. Limpieza de datos

| Validación | Tratamiento |
|---|---|
| Total nulo, cero o negativo | Excluida del análisis |
| Fecha futura | Excluida del análisis |
| Venta sin renglones en DetalleVenta | Se informa (cuenta en ingresos pero no en productos) |
| Posible duplicado (mismo día, cliente, total y medio de pago) | Se informa para revisar |
| Renglón con cantidad o importe ≤ 0 | Se informa |
| **Ticket atípico** | Se informa |
| **Período atípico** (mes/semana) | Se corrige para el pronóstico si hay 2 años de historia |

**Técnica de atípicos: estadística robusta (mediana + MAD).** La media y el desvío estándar
se "contaminan" con los mismos valores atípicos que se quieren detectar. La mediana y la MAD
(desviación absoluta mediana) no. Se usa el **puntaje z modificado de Iglewicz y Hoaglin**:
|z| > 3,5 es atípico.

- **Tickets:** z robusto sobre el logaritmo del total (los montos de venta son asimétricos).
- **Períodos:** **filtro de Hampel** sobre los residuos de una descomposición robusta:
  tendencia = mediana móvil, estacionalidad = mediana por mes del año. Así un diciembre alto
  no es atípico si todos los diciembres son altos. Además tiene que apartarse más de 25 % de lo
  esperado, para no marcar ruido chico.
- Con 2 años o más de historia, el período atípico se reemplaza por su valor esperado **solo
  para entrenar el pronóstico**. El gráfico y los totales muestran siempre el valor real
  (cuadrado rojo en el gráfico).

## 2. Predicción: selección de modelo por validación cruzada temporal

Se prueban 5 modelos, de más simple a más complejo:

1. Ingenuo (igual al último período): la referencia mínima a superar
2. Media móvil de 3 períodos
3. Ingenuo estacional (igual al mismo mes del año anterior)
4. Holt con tendencia amortiguada
5. Holt-Winters aditivo (tendencia + estacionalidad), con 24 meses o más

**Validación cruzada con origen móvil (rolling-origin / backtesting):** se "vuelve al pasado"
hasta 8 veces, se entrena con los datos hasta ese momento, se pronostican los meses siguientes
y se compara contra lo que realmente se vendió. Gana el modelo con **menor error absoluto medio**.
Nunca se usan datos del futuro para entrenar.

- La **banda del 95 %** se calcula con los errores reales de la validación para cada distancia
  (1, 2, 3… meses adelante), no con una fórmula teórica.
- Si ningún modelo le gana al ingenuo, el informe lo dice: las ventas todavía no tienen un
  patrón predecible. Esa honestidad también es una interpretación correcta.
- Para que Holt-Winters compita y aprenda los picos de temporada hacen falta **36 meses**
  (24 para entrenar y 12 para validar que los picos se repiten).

En la pestaña *Calidad de datos y método* está la tabla con el error de cada modelo.

## 3. Interpretaciones correctas: hallazgos + pruebas estadísticas

`NHallazgos` convierte los números en frases (H1, H2…) ya interpretadas:

| Hallazgo | Técnica |
|---|---|
| ¿Las ventas cambiaron frente al período anterior? | **Prueba U de Mann-Whitney** sobre las ventas diarias: dice si la diferencia es real o puede ser variación normal (p < 0,05) |
| ¿Hay tendencia? | **Prueba de Mann-Kendall** + **pendiente de Sen** ($ por mes, robusta a picos) |
| Concentración de productos | **Análisis de Pareto** (cuántos productos generan el 80 %) |
| Día de la semana | Promedio diario por día vs. promedio general |
| Medios de pago, rubros, ticket | Participación % y ticket por medio |
| Stock crítico | Días de cobertura = stock / venta diaria de los últimos 30 días |
| Pronóstico | Total esperado, modelo, error de validación y cambio vs. últimos períodos |

Son pruebas **no paramétricas**: no suponen distribución normal, que en ventas casi nunca se
cumple. Las implementaciones se verificaron contra `scipy` y dan los mismos z y p-valores.

## 4. La IA solo redacta, y se la verifica

1. **Esquema JSON forzado:** Ollama recibe un JSON Schema en `format` y restringe la generación
   (decodificación restringida). La respuesta siempre tiene la estructura correcta.
2. **Temperatura 0,2 y semilla fija:** respuestas estables y reproducibles.
3. **Citas obligatorias:** cada interpretación, alerta y recomendación indica en `basadoEn` los
   hallazgos que la justifican.
4. **Verificación de números (grounding):** `NVerificadorIA` extrae todos los números del texto de
   la IA ($ 1.234.567, 12,5 %, "1,2 millones") y los compara con los de los hallazgos, con tolerancia
   por redondeo. Lo que no coincide o cita hallazgos inexistentes se descarta.
5. **Autocorrección:** si hubo errores, se le devuelven a la IA con su respuesta anterior para que
   los corrija (1 reintento).
6. **Red de seguridad:** lo que quede vacío se completa con el análisis automático, y las
   recomendaciones de prioridad Alta (stock crítico, caídas significativas) se agregan si la IA no
   las cubrió.

El informe indica quién lo redactó: **IA verificada**, **IA con correcciones** o **Automático (sin IA)**.

## Instalación

1. Extraer el zip en la carpeta raíz del proyecto (donde está `build.xml`), combinando carpetas.
   Son clases nuevas y no se modifica ninguna existente. No hay que agregar librerías.
2. Instalar Ollama: https://ollama.com
3. Bajar un modelo (una sola vez):
   - PC con 16 GB de RAM o más: `ollama pull qwen3:8b`
   - PC con 8 GB: `ollama pull qwen3:4b`
4. Copiar `ia.properties.ejemplo` como `ia.properties` y ajustar `ollama.modelo`.
5. Agregar el botón en `panelContenidoEstadisticas` de PPanelAMP:

```java
private void btnInformeIAActionPerformed(java.awt.event.ActionEvent evt) {
    new PInformeIA(this).setVisible(true);
}
```

## Datos de prueba

Para probar la estacionalidad, cargar **36 meses** de historia (en una copia de la base).
El script de prueba que copia las ventas actuales hacia atrás sirve cambiando `WHILE @mes <= 26`
por `WHILE @mes <= 36`.

## Ajustes

- `NLimpiezaDatos.UMBRAL_Z` (3,5) y `DESVIO_MINIMO` (25 %): sensibilidad de los atípicos.
- `DAnalisisVentas.VENTA_VALIDA`: qué ventas se excluyen. Si agregan un estado "Anulada", sumarlo acá.
- `NInformeIA.INSTRUCCIONES`: el prompt.
- `NInformeIA.INTENTOS_IA`: cantidad de intentos (cada uno tarda lo que tarde el modelo local).
