/**
 * ClientGPSVehiculo.java
 * 
 * Creado el 20/04/2020 a las 09:21PM
 */
package conexion;

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
public class ClientGPSVehiculo {

    /**
     * Abre la sesion.
     * 
     * @param p Sesion.
     */
    @OnOpen
    public void onOpen(Session p) {
        // Manda para registrarse
        try {
            MensajeIoT msg = new MensajeIoT(TipoIoT.REGISTRAR_VEHICULO);

            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            String send = gson.toJson(msg);

            p.getBasicRemote().sendText(send);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * OnMessage.
     * 
     * @param message Mensaje.
     */
    @OnMessage
    public void onMessage(String message) {

    }

    /**
     * Manda la posicion.
     * 
     * @param message Mensaje.
     * @param p Sesion.
     */
    public void mandaPosicion(String message, Session p) {
        try {
            p.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}