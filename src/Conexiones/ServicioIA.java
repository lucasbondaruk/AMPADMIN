package Conexiones;

import java.io.IOException;

/**
 * Proveedor de IA generativa. Hay dos implementaciones:
 * {@link ClienteClaude} (API de Anthropic, requiere API key e internet) y
 * {@link ClienteOllama} (modelo local gratuito, sin internet).
 */
public interface ServicioIA {

    /**
     * Envía un mensaje y devuelve el texto de la respuesta.
     *
     * @param instrucciones prompt de sistema (rol y reglas)
     * @param mensaje       contenido del usuario (los datos del informe)
     */
    String completar(String instrucciones, String mensaje) throws IOException, InterruptedException;

    /**
     * Igual que {@link #completar(String, String)} pero exigiendo un esquema JSON de respuesta.
     * Los proveedores que no soportan esquemas lo ignoran (el esquema también va en el prompt).
     */
    default String completar(String instrucciones, String mensaje, java.util.Map<String, Object> esquemaJson)
            throws IOException, InterruptedException {
        return completar(instrucciones, mensaje);
    }

    /** Nombre legible para mostrar en pantalla y en el PDF, p. ej. "Claude (claude-sonnet-5-5)". */
    String descripcion();
}
