package ControladorSemaforos;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro Galindo
 */
public class ProducerControl {
    /**
     * Encola el estado de los semaforos.
     * 
     * @param queue
     * @param mensaje
     */
    public void encolarOrden(String queue, String mensaje){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
        try {
            Connection connection = (Connection) factory.newConnection();
            Channel channel = connection.createChannel();                       
            
            channel.queueDeclare(queue, false, false, false, null);
            
            System.out.println("[P] Encolando estado... "  + mensaje + " en... " + queue);
            channel.basicPublish("", queue, null, mensaje.getBytes());
                       
        } catch (IOException ex) {
            Logger.getLogger(ProducerControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ProducerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
