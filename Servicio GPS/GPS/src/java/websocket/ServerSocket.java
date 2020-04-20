/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import objetos.Vehiculo;
import transformador.Transformador;

/**
 *
 * @author Home
 */
@ServerEndpoint(value = "/gps")
public class ServerSocket {
    
    private static Session central;
    private static Session controladorSemaforos;
    private static List<Session> vehiculos
            = Collections.synchronizedList(new ArrayList<Session>());
    

    private static List<Session> clients
            = Collections.synchronizedList(new ArrayList<Session>());

    @OnOpen
    public void onOpen(Session sesion) {
        System.out.println("Open Connection ...");
        clients.add(sesion);
    }

    @OnClose
    public void onClose(Session sesion) {
        System.out.println("Close Connection ...");
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
        MensajeIoT msg = gson.fromJson(message, MensajeIoT.class);

        switch (msg.getTipo()) {
            case REGISTRAR_CENTRAL:
                central = sesion;
                System.out.println("GPS: registrado central");
                break;

            case REGISTRAR_SEMAFORO:

                break;

            case REGISTRAR_VEHICULO:
                synchronized(vehiculos){
                    vehiculos.add(sesion);
                }
                System.out.println("GPS: registrado vehiculo");
                break;

            case ACTUALIZAR_SEMAFORO:

                break;

            case MOVER_VEHICULO:
                // Convierte el vehiculo IoT a uno de mina
                Vehiculo veh = Transformador.transformarVehiculo(msg.getContenido());
                // Enpaqueta
                MensajeMina mina = new MensajeMina(veh);
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
                }
                break;
        }
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }
}
