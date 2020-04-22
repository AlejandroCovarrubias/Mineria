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
     * Registra una congestion.
     * 
     * @param g La congestion a registrar
     */
    @Override
    public void registrarCongestion(Congestion g) {
        // La manda con REST
        rest.registrarCongestion(g);
    }
}
