/**
 * Notificador.java
 * 
 * Creado el 20/04/2020 a las 09:12PM
 */
package semaforo;

/**
 * Clase que implementa la interfaz INotificador y le permite notificar el 
 * estado a través de la clase ProducerSemaforo y actualizar el estado mediante 
 * la clase ConsumerSemaforo.
 * 
 * @author Equipo Mineria.
 */
public class Notificador implements INotificador {

    private ISemaforo semaforo;
    
    private ProducerSemaforo producer;
    
    /**
     * Constructor que inicializa a su atributo Semaforo.
     * 
     * @param semaforo Semaforo.
     */
    public Notificador(ISemaforo semaforo, ProducerSemaforo producer) {
        this.semaforo = semaforo;
        this.producer = producer;
    }

    /**
     * Notifica el esto del semaforo.
     * 
     * @param estado Estado.
     */
    @Override
    public void notificar(String estado) {
        producer.encolarEstado(estado);
    }

    /**
     * Actualiza el estado de los semaforos.
     * 
     * @param estado Estado.
     */
    @Override
    public void actualizar(String estado) {
        semaforo.establecerEstado(estado);
    }
}
