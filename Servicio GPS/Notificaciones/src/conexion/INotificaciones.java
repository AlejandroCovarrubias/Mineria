/**
 * INotificaciones.java
 * 
 * Creada el 20/04/2020 a las 07:25PM
 */
package conexion;

import java.util.List;
import objetos.Congestion;
import objetos.Semaforo;
import objetos.Vehiculo;


/**
 * Interfaz del servicio de notificaciones.
 * 
 * @author Equipo Mineria.
 */
public interface INotificaciones {
    
    public List<Congestion> obtenerCongestiones();
    public void agregarVehiculo(Vehiculo v);
    public void agregarSemaforo(Semaforo s);
    public void registrarCongestion(Congestion g);
    
}
