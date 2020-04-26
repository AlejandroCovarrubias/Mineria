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
    private ClienteREST_Notificaciones rest;
    private List<Vehiculo> vehiculos;
    private List<Semaforo> semaforos;
    
    // debug
    int count = 0;

    public Notificaciones() {
        vehiculos = new ArrayList<>();
        semaforos = new ArrayList<>();
        rest = new ClienteREST_Notificaciones();
    }
    
    
    
    @Override
    public void agregarVehiculo(Vehiculo v){
        // Revisa si ya existe
        if(vehiculos.contains(v)){
            // Reemplaza
            vehiculos.set(vehiculos.indexOf(v), v);
        }else{
            // Agrega nuevo
            vehiculos.add(v);
        }
    }
    
    @Override
    public void agregarSemaforo(Semaforo s){
        // Revisa si ya existe
        if(semaforos.contains(s)){
            // Reemplaza
            semaforos.set(semaforos.indexOf(s), s);
        }else{
            // Agrega nuevo
            semaforos.add(s);
        }
    }
    
    @Override
    public List<Congestion> obtenerCongestiones(){
        List<Congestion> lista = new ArrayList<>();
        // Logica para ver si hay congestion
        
        
        // Por ahora nadamas manda que si cada 20 veces que se revisa
        count++;
        if(count>19){
            count = 0;
            Congestion g = new Congestion(50, 50, "Mucho movimiento", "Hoy", "Ahora");
            lista.add( g );
            // Manda al REST
            registrarCongestion(g);
        }
        return lista;
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
