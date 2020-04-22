/**
 * ClienteGPSWebSocket.java
 * 
 * Creado el 21/04/2020 a las 12:10PM
 */
package ControladorSemaforos;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 * Clase para la conexión al WebSocket del servidor de GPS.
 * 
 * @author Equipo Mineria.
 */
@ClientEndpoint
public class ClienteGPSWebSocket {
    
    private int count;
    private String rutaGPS = "ws://localhost:8080/GPS/gps";

    /**
     * Constructor.
     */
    public ClienteGPSWebSocket() {
    }
    
    /**
     * Registra en central.
     * @param p Sesion.
     */
    @OnOpen
    public void onOpen(Session p) {
        
    }
    
    /**
     * Trata el mensaje.
     * @param message Mensaje.
     */
    @OnMessage
    public void onMessage(String message) {
        
    }
}
