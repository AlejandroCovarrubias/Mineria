/**
 * ClienteGPSWebSocket.java
 * 
 * Creado el 21/04/2020 a las 12:10PM
 */
package ControladorSemaforos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import mensajeIoT.MensajeIoT;
import mensajeIoT.TipoIoT;

/**
 * Clase para la conexión al WebSocket del servidor de GPS.
 * 
 * @author Equipo Mineria.
 */
@ClientEndpoint
public class ClienteGPSWebSocket {
    
    private String rutaGPS = "wss://localhost:8443/GPS/gps";

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
        // Manda para registrarse
        try{
            MensajeIoT msg = new MensajeIoT(TipoIoT.REGISTRAR_SEMAFORO);
            
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            
            String send = gson.toJson(msg);
            
            p.getBasicRemote().sendText(send);
            
            System.out.println("[R] Registrado en central.");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    public void mandarSemaforo(MensajeIoT msgIoT, Session p){
        // Convierte a JSON
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String msg = gson.toJson(msgIoT);
        
        try {
            p.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
}