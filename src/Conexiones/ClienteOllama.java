package Conexiones;

import Utilidades.Json;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Cliente para Ollama (https://ollama.com): corre un modelo en la propia PC, gratis y sin
 * internet. Instalar Ollama y ejecutar una vez, por ejemplo: {@code ollama pull qwen3:8b}
 *
 * Configuración pensada para que un modelo chico no se equivoque:
 * <ul>
 *   <li>{@code format} = esquema JSON: Ollama restringe la generación para que la respuesta
 *       siempre tenga la estructura pedida (decodificación restringida).</li>
 *   <li>{@code temperature} baja: respuestas estables, sin "creatividad" con los números.</li>
 *   <li>{@code num_ctx} amplio: si el contexto es chico, Ollama corta el prompt sin avisar y
 *       el modelo no ve parte de los datos.</li>
 * </ul>
 */
public class ClienteOllama implements ServicioIA {

    private final URI url;
    private final String modelo;
    private final double temperatura;
    private final int contexto;
    private final Boolean pensar;
    private final HttpClient http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    /**
     * @param pensar para modelos con razonamiento (qwen3, etc.): false lo desactiva y responde más
     *               rápido; null no envía el parámetro (para modelos que no lo soportan)
     */
    public ClienteOllama(String urlBase, String modelo, double temperatura, int contexto, Boolean pensar) {
        this.url = URI.create(urlBase.replaceAll("/+$", "") + "/api/chat");
        this.modelo = modelo;
        this.temperatura = temperatura;
        this.contexto = contexto;
        this.pensar = pensar;
    }

    @Override
    public String completar(String instrucciones, String mensaje) throws IOException, InterruptedException {
        return completar(instrucciones, mensaje, null);
    }

    @Override
    public String completar(String instrucciones, String mensaje, Map<String, Object> esquemaJson)
            throws IOException, InterruptedException {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("model", modelo);
        cuerpo.put("stream", false);
        cuerpo.put("format", esquemaJson != null ? esquemaJson : "json");
        if (pensar != null) {
            cuerpo.put("think", pensar);
        }
        cuerpo.put("options", Map.of(
                "temperature", temperatura,
                "num_ctx", contexto,
                "num_predict", 2500,
                "seed", 42)); // misma entrada → misma respuesta, útil para probar
        cuerpo.put("messages", List.of(
                Map.of("role", "system", "content", instrucciones),
                Map.of("role", "user", "content", mensaje)));

        HttpRequest pedido = HttpRequest.newBuilder(url)
                .timeout(Duration.ofMinutes(10)) // en CPU sin placa de video puede tardar
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(Json.escribir(cuerpo), StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> respuesta;
        try {
            respuesta = http.send(pedido, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (java.net.ConnectException e) {
            throw new IOException("No se pudo conectar con Ollama en " + url
                    + ". ¿Está instalado y abierto?", e);
        }
        if (respuesta.statusCode() == 404) {
            throw new IOException("Ollama no tiene el modelo \"" + modelo + "\". Ejecutá: ollama pull " + modelo);
        }
        if (respuesta.statusCode() != 200) {
            throw new IOException("Ollama respondió " + respuesta.statusCode() + ": " + respuesta.body());
        }
        // Respuesta: {"message":{"role":"assistant","content":"...","thinking":"..."}, "done_reason":"stop", ...}
        Map<?, ?> raiz = (Map<?, ?>) Json.leer(respuesta.body());
        if ("length".equals(raiz.get("done_reason"))) {
            throw new IOException("La respuesta de Ollama quedó cortada; subí num_predict o usá un modelo más rápido.");
        }
        return String.valueOf(((Map<?, ?>) raiz.get("message")).get("content"));
    }

    @Override
    public String descripcion() {
        return "Ollama local (" + modelo + ")";
    }
}
