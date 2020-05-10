/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorSemaforos;

import Prueba.Visual;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.websocket.Session;
import mensajeIoT.MensajeIoT;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;

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
    String rutaGPS = "wss://localhost:8443/GPS/gps";
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
        
        if(isConectado()){
            System.out.println("[R] Mandando a GPS");
            // Crea un objeto IoT para mandar
            // Las ubicaciones las deberia tener guardadas en alguna parte segun
            int x = 0;
            int y = 0;
            switch(split[0]){
                case "S1": x=5000;y=4000;break;
                case "S2": x=7070;y=2000;break;
                case "S3": x=1000;y=5070;break;
            }
            SemaforoIoT semaforo = new SemaforoIoT(split[0],split[1],x,y);
            //Empaqueta
            MensajeIoT msgFinal = new MensajeIoT(semaforo);
            //Enviar mensaje al GPS
            gps.mandarSemaforo(msgFinal,sesionGPS);
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                visual.actualizarVisualSemaforo(split[0], split[1]);
            }
        });
        
        
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
        int intentosMax = 1;
        
        gps = new ClienteGPSWebSocket();
        sesionGPS = null;
        
        while(intentos<intentosMax){
            try{
                ClientManager cm = ClientManager.createClient();
                
                System.getProperties().put(SSLContextConfigurator.KEY_STORE_FILE, "src/java/conexion/keystore.jks");
                System.getProperties().put(SSLContextConfigurator.TRUST_STORE_FILE, "src/java/conexion/keystore.jks");
                System.getProperties().put(SSLContextConfigurator.KEY_STORE_PASSWORD, "mineria");
                System.getProperties().put(SSLContextConfigurator.TRUST_STORE_PASSWORD, "mineria");
                final SSLContextConfigurator defaultConfig = new SSLContextConfigurator();

                defaultConfig.retrieve(System.getProperties());
                    // or setup SSLContextConfigurator using its API.

                SSLEngineConfigurator sslEngineConfigurator =
                    new SSLEngineConfigurator(defaultConfig, true, false, false);
                cm.getProperties().put(ClientProperties.SSL_ENGINE_CONFIGURATOR,
                    sslEngineConfigurator);
                
                sesionGPS = cm.connectToServer(gps, new URI(rutaGPS));
                break;
            }catch(Exception e){
                e.printStackTrace();
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