/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Home
 */
public class Notificaciones implements INotificaciones{

    ClienteREST_Notificaciones rest;

    public Notificaciones() {
        rest = new ClienteREST_Notificaciones();
    }
    
    
    
    @Override
    public List<String> obtenerCongestiones(List<Vehiculo> vehiculos, List<Semaforo> semaforos) {
        List<String> congestiones = new ArrayList<>();
        
        // Hace logica para ver si hay congestiones
            // por ahora nomas dice que si

        registrarCongestion("500,500,Mucho movimiento da hueva,19/04/2020,13:00");
        
        // Regresa la lista
        return congestiones;
    }
    
    

    

    @Override
    public void registrarCongestion(String msg) {
        String[] datos = msg.split(",");
        
        // Crea la congestion
        Congestion nueva = new Congestion(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), datos[2], datos[3], datos[4]);
        
        // La manda con REST
          //rest.registrarCongestion(nueva);
        System.out.println("congestion mandada a rest!");
    }
    
}
