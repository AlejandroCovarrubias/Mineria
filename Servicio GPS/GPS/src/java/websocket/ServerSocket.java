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

    private static List<Session> clients
            = Collections.synchronizedList(new ArrayList<Session>());

    // Buscar como mejor identificar a los clientes
    private static List<Integer> ids
            = Collections.synchronizedList(new ArrayList<Integer>());

    @OnOpen
    public void onOpen(Session sesion) {
        System.out.println("Open Connection ...");
        clients.add(sesion);
    }

    @OnClose
    public void onClose(Session sesion) {
        System.out.println("Close Connection ...");
//        ids.remove(clients.indexOf(sesion));
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
                ids.add(-1); // Por ahora nada mas se ocupa identificar la central
                System.out.println("registrado central");
                break;

            case REGISTRAR_SEMAFORO:

                break;

            case REGISTRAR_VEHICULO:
                ids.add(ids.size());
                System.out.println("registrado vehiculo");
                break;

            case ACTUALIZAR_SEMAFORO:

                break;

            case MOVER_VEHICULO:
                // Convierte el vehiculo IoT a uno de mina
                Vehiculo veh = Transformador.transformarVehiculo(msg.getContenido());
                // Enpaqueta
                MensajeMina mina = new MensajeMina(veh);
                // Se lo envia a la central
                synchronized (clients) {
                    synchronized (ids) {
                        // Encuentra a la central
                        Session central = clients.get(ids.indexOf(-1));
                        // Convierte a json
                        msgFinal = gson.toJson(mina);
                        // Manda
                        try {
                            central.getBasicRemote().sendText(msgFinal);
                            System.out.println("mandado vehiculo actualizado");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
