/**
 * ServerSocket.java
 * 
 * Creado el 20/04/2020 a las 08:56PM
 */
package websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conexion.INotificaciones;
import conexion.Notificaciones;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import mensajeIoT.MensajeIoT;
import mensajemina.MensajeMina;
import objetos.Congestion;
import objetos.Semaforo;
import objetos.Vehiculo;
import transformador.Transformador;

/**
 *
 * @author Equipo Mineria.
 */
@ServerEndpoint(value = "/gps")
public class ServerSocket {
    
    private static Session central;
    private static Session controladorSemaforos;
    private static List<Session> vehiculos
            = Collections.synchronizedList(new ArrayList<Session>());
    

    private static List<Session> clients
            = Collections.synchronizedList(new ArrayList<Session>());

    private INotificaciones congestiones = new Notificaciones();
    
    @OnOpen
    public void onOpen(Session sesion) {
        System.out.println("GPS: Open Connection ...");
        clients.add(sesion);
    }

    @OnClose
    public void onClose(Session sesion) {
        System.out.println("GPS: Close Connection ...");
        if(sesion.equals(central))
            central = null;
        else if(sesion.equals(controladorSemaforos))
            controladorSemaforos = null;
        else if(vehiculos.contains(sesion)){
            vehiculos.remove(sesion);
        }
        clients.remove(sesion);
    }

    @OnMessage
    public void onMessage(String message, Session sesion) {

        // Transofrma lo recibido (JSON) a un mensaje (objeto)
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String msgFinal;
        MensajeMina mina;
        MensajeIoT msg = gson.fromJson(message, MensajeIoT.class);

        switch (msg.getTipo()) {
            case REGISTRAR_CENTRAL:
                central = sesion;
                System.out.println("GPS: registrado central");
                break;

            case REGISTRAR_SEMAFORO:
                controladorSemaforos = sesion;
                System.out.println("GPS: registrado controlador semaforos");
                break;

            case REGISTRAR_VEHICULO:
                synchronized(vehiculos){
                    vehiculos.add(sesion);
                }
                System.out.println("GPS: registrado vehiculo");
                break;

                // ControladorSemaforo > Central
            case ACTUALIZAR_SEMAFORO_CONTROLADOR:
                // Convierte el semaforo IoT a uno de mina
                Semaforo sem = Transformador.transformarSemaforo(msg.getContenido());
                // Agrega al manejador de congestiones
                congestiones.agregarSemaforo(sem);
                // Enpaqueta
                mina = new MensajeMina(sem);
                // Se lo envia a la central
                if(central!=null){
                    // Convierte a json
                    msgFinal = gson.toJson(mina);
                    // Manda
                    try {
                        central.getBasicRemote().sendText(msgFinal);
                        System.out.println("GPS: mandado semaforo actualizado");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } 
                }else{
                    System.out.println("GPS: No se econtro la central para mandar semaforo");
                }
                break;

                // Vehiculo > Central
            case MOVER_VEHICULO:
                // Convierte el vehiculo IoT a uno de mina
                Vehiculo veh = Transformador.transformarVehiculo(msg.getContenido());
                // Agrega al manejador de congestiones
                congestiones.agregarVehiculo(veh);
                // Enpaqueta
                mina = new MensajeMina(veh);
                // Se lo envia a la central
                if(central!=null){
                    // Convierte a json
                    msgFinal = gson.toJson(mina);
                    // Manda
                    try {
                        central.getBasicRemote().sendText(msgFinal);
                        System.out.println("GPS: mandado vehiculo actualizado");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } 
                }else{
                    System.out.println("GPS: No se econtro la central para mandar vehiculo");
                }
                break;
                
                // Central > ControladorSemaforo
            case ACTUALIZAR_SEMAFORO_CENTRAL:
                msgFinal = msg.getContenido();
                // Se lo envia a controlador semaforos
                if(controladorSemaforos!=null){
                    // Manda
                    try {
                        controladorSemaforos.getBasicRemote().sendText(msgFinal);
                        System.out.println("GPS: mandado cambio de estados de semaforo");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } 
                }else{
                    System.out.println("GPS: No se econtro el controlador de semaforos para mandar cambio de estado");
                }
                break;
        }
        
        //Revisa congestiones
        tratarCongestiones(gson);
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }
    
    private void tratarCongestiones(Gson gson){
        List<Congestion> cong = congestiones.obtenerCongestiones();
        if(!cong.isEmpty()){
            // Manda correo
            System.out.println("NOTIFICACIONES: Mandado correo.");
        }
    }
}
