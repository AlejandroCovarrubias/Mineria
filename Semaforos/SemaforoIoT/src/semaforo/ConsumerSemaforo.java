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
public class ConsumerSemaforo implements Runnable {

    private INotificador notificador;
    
    public ConsumerSemaforo(INotificador notificador){
        this.notificador = notificador;
    }
    
    @Override
    public void run() {
        //Cuando llegue algo a un canal de cola
        //notificar al notificador
    }
    
}
