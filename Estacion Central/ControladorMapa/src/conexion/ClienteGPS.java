/**
 * ClienteGPS.java
 *
 * Creado el 20/04/2020 a las 7:10PM
 */
package conexion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import mensajeIoT.MensajeIoT;
import mensajeIoT.TipoIoT;
import mensajemina.MensajeMina;
import objetos.Semaforo;
import objetos.Vehiculo;
import ui.FrameMain;

/**
 * Clase para la conexión al WebSocket del servidor de GPS.
 *
 * @author Equipo Mineria.
 */
@ClientEndpoint
public class ClienteGPS {

    private int count;
    private FrameMain actualizable;
    private List<Vehiculo> vehiculos;
    private List<Semaforo> semaforos;

    /**
     * Constructor para inicializar variables.
     *
     * @param actualizable Frame principal.
     */
    public ClienteGPS(FrameMain actualizable) {
        this.actualizable = actualizable;
        count = 0;
        vehiculos = new ArrayList<>();
        semaforos = new ArrayList<>();
    }

    /**
     * Registra en central.
     *
     * @param p Sesion.
     */
    @OnOpen
    public void onOpen(Session p) {
        // Manda para registrarse
        try {
            MensajeIoT msg = new MensajeIoT(TipoIoT.REGISTRAR_CENTRAL);

            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            String send = gson.toJson(msg);

            p.getBasicRemote().sendText(send);

            System.out.println("registrado central");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Trata el mensaje.
     *
     * @param message Mensaje.
     */
    @OnMessage
    public void onMessage(String message) {
        // Desencripta
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        MensajeMina msg = gson.fromJson(message, MensajeMina.class);

        // Hace cosas con el (actualiza mapa)
        switch (msg.getTipo()) {
            case VEHICULO:
                // Revisa si ya existe
                if (vehiculos.contains(msg.getVehiculo())) {
                    // Reemplaza
                    vehiculos.set(vehiculos.indexOf(msg.getVehiculo()), msg.getVehiculo());
                } else {
                    // Agrega nuevo
                    vehiculos.add(msg.getVehiculo());
                }
                break;

            case SEMAFORO:
                // Revisa si ya existe
                if (semaforos.contains(msg.getSemaforo())) {
                    // Reemplaza
                    semaforos.set(semaforos.indexOf(msg.getSemaforo()), msg.getSemaforo());
                } else {
                    // Agrega nuevo
                    semaforos.add(msg.getSemaforo());
                    actualizable.actualizarComboSemaforos();
                }
                break;

        }

        // Por ahora nada mas imprime para probar que sirve
        System.out.println("Me llego: " + message);

        // Checa si hay congestiones
        actualizable.procesarUbicaciones();
    }

    /**
     * Regresa el contador.
     *
     * @return Contador.
     */
    public int getCount() {
        return count;
    }

    /**
     * Establece el contador.
     *
     * @param count Contador.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Regresa el vehiculo.
     *
     * @return Vehiculo.
     */
    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    /**
     * Regresa la lista de semaforos.
     *
     * @return Lista de semaforos.
     */
    public List<Semaforo> getSemaforos() {
        return semaforos;
    }

    public FrameMain getActualizable() {
        return actualizable;
    }

    public void setActualizable(FrameMain actualizable) {
        this.actualizable = actualizable;
    }

}
