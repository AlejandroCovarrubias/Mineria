/**
 * INotificaciones.java
 * 
 * Creada el 20/04/2020 a las 07:25PM
 */
package interfaz;

import java.util.List;
import objetos.Semaforo;
import objetos.Vehiculo;

/**
 * Interfaz del servicio de notificaciones.
 * 
 * @author Equipo Mineria.
 */
public interface INotificaciones {
    
    public List<String> obtenerCongestiones(List<Vehiculo> vehiculos,List<Semaforo> semaforos);
    
    public void registrarCongestion(String msg);
    
}
