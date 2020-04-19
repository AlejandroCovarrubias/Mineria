/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import mensaje.MensajeIoT;
import mensaje.MensajeMina;
import mensaje.Tipo;

/**
 *
 * @author Home
 */
@ClientEndpoint
public class ClienteGPS {

    //Websocket
    
    @OnOpen
    public void onOpen(Session p) {
        // Manda para registrarse
        try{
            MensajeIoT msg = new MensajeIoT(Tipo.REGISTRAR_CENTRAL);
            
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            
            String send = gson.toJson(msg);
            
            p.getBasicRemote().sendText(send);
            
            System.out.println("registrado central");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @OnMessage
    public void onMessage(String message) {
        // Desencripta
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        
        MensajeMina msg = gson.fromJson(message, MensajeMina.class);
        
        // Hace cosas con el (actualiza mapa)
        // Por ahora nada mas imprime para probar que sirve
        
        System.out.println("Me llego: "+message);
        
    }
    
}
