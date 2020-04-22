/**
 * Controlador.java
 *
 * Creado el 20/04/2020 a las 09:01PM
 */
package semaforo;

import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador del semaforo.
 *
 * @author Equipo Mineria.
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("IDENTIFICADOR DE SEMAFORO: ");
        String identificador = scn.nextLine();
        
        Semaforo semaforo = new Semaforo(identificador);
        ProducerSemaforo producer = new ProducerSemaforo(identificador);
        INotificador notificador = new Notificador(semaforo, producer);
        
        semaforo.agregarNotificador(notificador);
        
        Controlador controlador = new Controlador(semaforo);
        
        Thread hiloControlador = new Thread(controlador);
        ConsumerSemaforo consumer = new ConsumerSemaforo(notificador, controlador, identificador);
        
        Thread hiloConsumidor = new Thread(consumer);
        
        hiloControlador.start();
        hiloConsumidor.start();
    }
}