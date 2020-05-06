/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorSemaforos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import mensajeIoT.MensajeIoT;
import mensajeIoT.TipoIoT;

/**
 *
 * @author Home
 */
@ServerEndpoint(value = "/controladorSemaforos")
public class ServerSemaforosWebSocket {
    ProducerControl producer = null;
    
    @OnOpen
    public void onOpen(){
        if(producer==null)
            producer = new ProducerControl();
    }
    
    @OnClose
    public void onClose(){
        
    }
    
    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }
    
    @OnMessage
    public void onMessage(String message, Session sesion) {
        // Transofrma lo recibido (JSON) a un mensaje (objeto)
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        MensajeIoT msg = gson.fromJson(message, MensajeIoT.class);
        
        // Acepta solo los mensajes relevantes por si acaso
        if(msg.getTipo() == TipoIoT.ACTUALIZAR_SEMAFORO_CENTRAL){
            String[] datos = msg.getContenido().split(",");
            // ID, Estado
            if(producer!=null)
                producer.encolarOrden(datos[0], datos[1]);
            else
                System.out.println("SEMAFOROS: No hay productor?");
        }else{
            System.out.println("SEMAFOROS: Mensaje invalido recibido.");
        }
        
    }
    
}
