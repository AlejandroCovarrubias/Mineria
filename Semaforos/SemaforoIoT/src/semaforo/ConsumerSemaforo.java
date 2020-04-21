/**
 * ConsumerSemaforo.java
 * 
 * Creado el 20/04/2020 a las 08:59PM
 */
package semaforo;

/**
 * Clase que consume los estados de los semaforos.
 * 
 * @author Equipo Mineria.
 */
public class ConsumerSemaforo implements Runnable {

    private INotificador notificador;
    
    /**
     * Constructor que inicializa a sus atributos.
     * 
     * @param notificador Notificador.
     */
    public ConsumerSemaforo(INotificador notificador){
        this.notificador = notificador;
    }
    
    @Override
    public void run() {
        //Cuando llegue algo a un canal de cola
        //notificar al notificador
    }
    
}
