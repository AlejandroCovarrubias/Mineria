/**
 * Controlador.java
 * 
 * Creado el 20/04/2020 a las 09:01PM
 */
package semaforo;

/**
 * Controlador del semaforo.
 * 
 * @author Equipo Mineria.
 */
public class Controlador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ISemaforo semaforo = new Semaforo("ABCD");
        INotificador notificador = new Notificador(semaforo);
        
        ConsumerSemaforo consumer = new ConsumerSemaforo(notificador);
        ProducerSemaforo producer = new ProducerSemaforo();
        
        while(true){
            consumer.run();
            if(semaforo.obtenerEstado().equals("VERDE")){
            
            }
        }
    }  
}