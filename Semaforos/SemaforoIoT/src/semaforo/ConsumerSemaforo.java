/**
 * ConsumerSemaforo.java
 *
 * Creado el 20/04/2020 a las 08:59PM
 */
package semaforo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que consume los estados de los semaforos.
 *
 * @author Equipo Mineria.
 */
public class ConsumerSemaforo implements Runnable {

    private INotificador notificador;
    
    private Controlador notificable;
    
    private String SUBSCRIBIR;
    
    private String identificador;

    /**
     * Constructor que inicializa a sus atributos.
     *
     * @param notificador Notificador.
     * @param notificable
     */
    public ConsumerSemaforo(INotificador notificador, Controlador notificable, String identificador) {
        this.notificador = notificador;
        this.notificable = notificable;
        this.identificador = identificador;
        this.SUBSCRIBIR = identificador;
    }

    @Override
    public void run() {     
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            
            channel.queueDeclare(identificador, false, false, false, null);
            System.out.println("[*] Semaforo " + identificador + " esperando peticiones de cambio de estado.");     
            
            // Llegara mensaje con formato >
            // ESTADOACOLOCAR
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("[C] Petición de cambio de estado entrante... " + message);
                    notificador.actualizar(message);
                    
                    synchronized(notificable){
                        notificable.notify();
                    }
            };
            channel.basicConsume(identificador, true, deliverCallback, consumerTag -> { });            
        } catch (IOException ex) {
            Logger.getLogger(ConsumerSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ConsumerSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}