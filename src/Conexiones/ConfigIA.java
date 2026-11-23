package Conexiones;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Lee la configuración de IA de un archivo {@code ia.properties} en la carpeta del proyecto
 * (la misma donde está build.xml; NetBeans ejecuta desde ahí).
 *
 * IMPORTANTE: el repo de AMPADMIN es público. ia.properties está en .gitignore y la API key
 * NUNCA se escribe en el código. Cada integrante tiene su propio archivo local.
 *
 * Ejemplo de ia.properties:
 * <pre>
 * proveedor=ollama
 * ollama.url=http://localhost:11434
 * ollama.modelo=qwen3:8b
 * ollama.temperatura=0.2
 * ollama.contexto=8192
 * ollama.pensar=false
 *
 * # o bien Claude (pago):
 * # proveedor=claude
 * # claude.apiKey=sk-ant-...
 * # claude.modelo=claude-sonnet-5-5
 * </pre>
 *
 * También acepta la variable de entorno ANTHROPIC_API_KEY si no hay archivo.
 */
public final class ConfigIA {

    public static final Path ARCHIVO = Path.of("ia.properties");

    private ConfigIA() {
    }

    /** Crea el servicio configurado, o devuelve null si no hay IA configurada. */
    public static ServicioIA crearServicio() {
        Properties p = new Properties();
        if (Files.exists(ARCHIVO)) {
            try (InputStream in = Files.newInputStream(ARCHIVO)) {
                p.load(in);
            } catch (IOException e) {
                System.err.println("No se pudo leer " + ARCHIVO.toAbsolutePath() + ": " + e.getMessage());
            }
        }
        String proveedor = p.getProperty("proveedor", "ollama").trim().toLowerCase();

        if (proveedor.equals("ollama")) {
            String pensar = p.getProperty("ollama.pensar", "").trim();
            return new ClienteOllama(
                    p.getProperty("ollama.url", "http://localhost:11434").trim(),
                    p.getProperty("ollama.modelo", "qwen3:8b").trim(),
                    Double.parseDouble(p.getProperty("ollama.temperatura", "0.2").trim()),
                    Integer.parseInt(p.getProperty("ollama.contexto", "8192").trim()),
                    pensar.isEmpty() ? null : Boolean.valueOf(pensar));
        }

        String apiKey = p.getProperty("claude.apiKey", System.getenv("ANTHROPIC_API_KEY"));
        if (apiKey == null || apiKey.isBlank()) {
            return null;
        }
        return new ClienteClaude(apiKey.trim(), p.getProperty("claude.modelo", "claude-sonnet-5-5").trim());
    }
}
