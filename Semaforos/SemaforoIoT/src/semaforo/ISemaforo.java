/**
 * ISemaforos.java
 * 
 * Creado el 20/04/2020 a las 09:16PM
 */
package semaforo;

import java.util.ArrayList;
import java.util.List;

/**
 * Interfaz que en su implementación define los métodos para notificar y 
 * actualizar el estado del semáforo.
 * 
 * @author Equipo Mineria.
 */
public abstract class ISemaforo {
    public List<INotificador> notifs = new ArrayList<>();
    abstract void notificar(String estado);
    abstract void iniciarCiclo();
    abstract void establecerEstado(String estado);
    abstract String obtenerEstado();
    void agregarNotificador(INotificador notificador){
        notifs.add(notificador);
    }
}
