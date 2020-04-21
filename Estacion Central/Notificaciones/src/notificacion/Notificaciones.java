/**
 * Notificaciones.java
 * 
 * Creado el 20/04/2020 a las 07:26PM
 */
package notificacion;

import conexion.ClienteREST_Notificaciones;
import interfaz.INotificaciones;
import java.util.ArrayList;
import java.util.List;
import objetos.Congestion;
import objetos.Semaforo;
import objetos.Vehiculo;

/**
 * Implementación de la interfaz, establece metodo para saber si hay una 
 * congestión basado en los vehículos y semáforos actuales. Utiliza una clase 
 * cliente REST para registrar en caso encontrado.
 * 
 * @author Equipo Mineria.
 */
public class Notificaciones implements INotificaciones{

    ClienteREST_Notificaciones rest;

    public Notificaciones() {
        rest = new ClienteREST_Notificaciones();
    }
    
    /**
     * Obtiene las congestiones.
     * 
     * @param vehiculos Lista de vehiculos.
     * @param semaforos Lista de semaforos.
     * @return Lista de congestiones.
     */
    @Override
    public List<String> obtenerCongestiones(List<Vehiculo> vehiculos, List<Semaforo> semaforos) {
        List<String> congestiones = new ArrayList<>();
        
        // Hace logica para ver si hay congestiones
            // por ahora nomas dice que si

        registrarCongestion("500,500,Mucho movimiento da hueva,19/04/2020,13:00");
        
        // Regresa la lista
        return congestiones;
    }
    
    /**
     * Registra una congestion.
     * 
     * @param msg Mensaje
     */
    @Override
    public void registrarCongestion(String msg) {
        String[] datos = msg.split(",");
        
        // Crea la congestion
        Congestion nueva = new Congestion(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), datos[2], datos[3], datos[4]);
        
        // La manda con REST
        rest.registrarCongestion(nueva);
    }
}
