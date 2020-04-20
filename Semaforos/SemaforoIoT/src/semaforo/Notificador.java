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
public class Notificador implements INotificador {

    private ISemaforo semaforo;
    
    public Notificador(ISemaforo semaforo) {
        this.semaforo = semaforo;
    }

    @Override
    public void notificar(String estado) {
        
    }

    @Override
    public void actualizar(String estado) {
        semaforo.notificar(estado);
        semaforo.iniciarCiclo();
    }
    
}
