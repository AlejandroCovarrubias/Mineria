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
import javax.websocket.Session;
import mensajeIoT.MensajeIoT;
import mensajeIoT.TipoIoT;

/**
 *
 * @author Alejandro Galindo
 */
@ClientEndpoint
public class ClienteSemaforos {
    
    public void cambiarEstado(String semaforo, Session p){
        // Crea un mensaje IoT para mandar
        MensajeIoT msg = new MensajeIoT(TipoIoT.ACTUALIZAR_SEMAFORO_CENTRAL);
        msg.setContenido(semaforo);
        
        // Convierte a JSon
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String send = gson.toJson(msg);
        try{
            p.getBasicRemote().sendText(send);
            System.out.println("Mandado cambio de semaforos a GPS.");
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
}
