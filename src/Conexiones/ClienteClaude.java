package Conexiones;

import Utilidades.Json;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Map;

/** Cliente de la Messages API de Anthropic usando java.net.http (incluido en el JDK). */
public class ClienteClaude implements ServicioIA {

    private static final URI URL = URI.create("https://api.anthropic.com/v1/messages");

    private final String apiKey;
    private final String modelo;
    private final HttpClient http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(15))
            .build();

    public ClienteClaude(String apiKey, String modelo) {
        this.apiKey = apiKey;
        this.modelo = modelo;
    }

    @Override
    public String completar(String instrucciones, String mensaje) throws IOException, InterruptedException {
        String cuerpo = Json.escribir(Map.of(
                "model", modelo,
                "max_tokens", 4000,
                "system", instrucciones,
                "messages", List.of(Map.of("role", "user", "content", mensaje))));

        HttpRequest pedido = HttpRequest.newBuilder(URL)
                .timeout(Duration.ofSeconds(120))
                .header("x-api-key", apiKey)
                .header("anthropic-version", "2023-06-01")
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(cuerpo, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> respuesta = http.send(pedido,
                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        Object json = Json.leer(respuesta.body());
        if (respuesta.statusCode() != 200) {
            throw new IOException("La API de Claude respondió " + respuesta.statusCode() + ": "
                    + mensajeDeError(json));
        }
        // Respuesta: {"content":[{"type":"text","text":"..."}], "stop_reason": "...", ...}
        Map<?, ?> raiz = (Map<?, ?>) json;
        StringBuilder texto = new StringBuilder();
        for (Object bloque : (List<?>) raiz.get("content")) {
            Map<?, ?> b = (Map<?, ?>) bloque;
            if ("text".equals(b.get("type"))) {
                texto.append(b.get("text"));
            }
        }
        if ("max_tokens".equals(raiz.get("stop_reason"))) {
            throw new IOException("La respuesta de la IA quedó cortada (max_tokens). Subí max_tokens.");
        }
        return texto.toString();
    }

    private static String mensajeDeError(Object json) {
        if (json instanceof Map<?, ?> m && m.get("error") instanceof Map<?, ?> e) {
            return String.valueOf(e.get("message"));
        }
        return String.valueOf(json);
    }

    @Override
    public String descripcion() {
        return "Claude (" + modelo + ")";
    }
}
