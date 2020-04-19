/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import mensajemina.MensajeMina;
import mensajeIoT.Tipo;
import objetos.Semaforo;
import objetos.Vehiculo;
import ui.FrameMain;

/**
 *
 * @author Home
 */
@ClientEndpoint
public class ClienteGPS {
    
    private int count;
    private FrameMain actualizable;
    private List<Vehiculo> vehiculos;
    private List<Semaforo> semaforos;

    public ClienteGPS(FrameMain actualizable) {
        this.actualizable = actualizable;
        count = 0;
        vehiculos = new ArrayList<>();
        semaforos = new ArrayList<>();
    }
    
    
    
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
        
        switch(msg.getTipo()){
            case VEHICULO:
                // Revisa si ya existe
                if(vehiculos.contains(msg.getVehiculo())){
                    // Reemplaza
                    vehiculos.set(vehiculos.indexOf(msg.getVehiculo()), msg.getVehiculo());
                }else{
                    // Agrega nuevo
                    vehiculos.add(msg.getVehiculo());
                }
                break;
                
            case SEMAFORO:
                // Revisa si ya existe
                if(semaforos.contains(msg.getSemaforo())){
                    // Reemplaza
                    semaforos.set(semaforos.indexOf(msg.getSemaforo()), msg.getSemaforo());
                }else{
                    // Agrega nuevo
                    semaforos.add(msg.getSemaforo());
                }
                break;
        }

        // Por ahora nada mas imprime para probar que sirve
        
        System.out.println("Me llego: "+message);
        
        
        // Checa si hay congestiones
        count++;
        actualizable.procesarUbicaciones();
        
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public List<Semaforo> getSemaforos() {
        return semaforos;
    }
    
    
    
}
