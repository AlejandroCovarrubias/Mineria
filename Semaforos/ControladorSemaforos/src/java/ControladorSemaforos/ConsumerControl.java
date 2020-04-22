package ControladorSemaforos;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alejandro Galindo
 */
public class ConsumerControl implements Runnable {

    private Routeador routeador;
    
    private String SUBSCRIBIR = "notificacionesDeCambio";


    /**
     * Constructor que inicializa a sus atributos.
     *
     * @param notificador Notificador.
     * @param notificable
     */
    public ConsumerControl(Routeador routeador) {
        this.routeador = routeador;
    }

    @Override
    public void run() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(SUBSCRIBIR, false, false, false, null);
            System.out.println(" [*] Esperando mensajes.");

            // Llegara mensaje con formato:
            // ID,ESTADOACOLOCAR
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("[C] Mensaje entrante... " + message);
                
                routeador.notificar(message);
            };
            
            channel.basicConsume(SUBSCRIBIR, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException ex) {
            Logger.getLogger(ConsumerControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ConsumerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}