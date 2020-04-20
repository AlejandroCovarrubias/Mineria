/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro Galindo
 */
public abstract class ISemaforo {
    public List<INotificador> notifs = new ArrayList<>();
    abstract void notificar(String estado);
    abstract void iniciarCiclo();
    abstract String obtenerEstado();
    void agregarNotificador(INotificador notificador){
        notifs.add(notificador);
    }
}
