/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorSemaforos;

import Prueba.Visual;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.websocket.Session;
import org.glassfish.tyrus.client.ClientManager;

/**
 *
 * @author Alejandro Galindo
 */
public class Routeador {
    
    private List<String> idSemaforos = new ArrayList<>();
    
    private ProducerControl producer;
    
    private Visual visual;
    
    private ClienteGPSWebSocket gps;
    private Session sesionGPS = null;
    private String rutaGPS = "ws://localhost:8080/GPS/gps";
    private boolean conectado = false;
    
    //Visual temporal
    public Routeador(ProducerControl producer, Visual visual){
        this.producer = producer;
        this.visual = visual;
        
        //Vamos a suponer que hay declarados 3 semaforos
        idSemaforos.add("S1");
        idSemaforos.add("S2");
        idSemaforos.add("S3");
    }
    
    public void notificar(String msg){
        System.out.println("-----------------------------------");
        
        String[] split = msg.split(",");
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                visual.actualizarVisualSemaforo(split[0], split[1]);
            }
        });
        
        if(isConectado()){
            //Enviar mensaje al GPS
        }
    }
    
    public void encolar(String id, String msg){
        if(idSemaforos.contains(id)){
            producer.encolarOrden(id, msg);
        }
    }

    public List<String> getIdSemaforos() {
        return idSemaforos;
    }
    
    /**
     * Conecta al servicio GPS.
     */
    private boolean isConectado(){
        if(conectado != false){
            return true;
        }
        
        System.out.println("[R] Conectando al Servicio GPS...");
        int intentos = 0;
        int intentosMax = 5;
        
        gps = new ClienteGPSWebSocket();
        sesionGPS = null;
        
        while(intentos<intentosMax){
            try{
                ClientManager cm = ClientManager.createClient();
                sesionGPS = cm.connectToServer(gps, new URI(rutaGPS));
                break;
            }catch(Exception e){
                intentos++;
                System.out.println("[R] Error conectando al GPS, intentando "+(intentosMax-intentos)+" veces mas");
            }
        }
        if(intentos==intentosMax || sesionGPS == null){
            System.out.println("No se pudo conectar...");
            return false;
        }
        
        System.out.println("Conectado a gps!");
        conectado = true;
        return true;
    }
}