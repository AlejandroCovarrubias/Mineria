/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforo;

/**
 *
 * @author Alejandro Galindo
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