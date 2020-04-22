/**
 * ProducerSemaforo.java
 * 
 * Creado el 20/04/2020 a las 09:11PM
 */
package semaforo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Produce el estado de los semaforos.
 * 
 * @author Equipo Mineria.
 */
public class ProducerSemaforo {
    
    private String identificador;
    
    private String PUBLICAR = "notificacionesDeCambio";
    
    public ProducerSemaforo(String identificador){
        this.identificador = identificador;
    }
    
    /**
     * Encola el estado de los semaforos.
     * 
     * @param estado Estado.
     */
    public void encolarEstado(String estado){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
        try {
            Connection connection = (Connection) factory.newConnection();
            Channel channel = connection.createChannel();                       
            
            channel.queueDeclare(PUBLICAR, false, false, false, null);
            
            String mensajeFinal = identificador + "," + estado;
            
            System.out.println("[P] Encolando mensaje... " + estado);
            channel.basicPublish("", PUBLICAR, null, mensajeFinal.getBytes());
                       
        } catch (IOException ex) {
            Logger.getLogger(ProducerSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ProducerSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
