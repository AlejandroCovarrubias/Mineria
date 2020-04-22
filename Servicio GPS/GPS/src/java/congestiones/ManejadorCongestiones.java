/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congestiones;

import java.util.ArrayList;
import java.util.List;
import objetos.Congestion;
import objetos.Semaforo;
import objetos.Vehiculo;

/**
 *
 * @author Home
 */
public class ManejadorCongestiones {
    private List<Vehiculo> vehiculos;
    private List<Semaforo> semaforos;
    
    // debug
    int count = 0;

    public ManejadorCongestiones() {
        vehiculos = new ArrayList<>();
        semaforos = new ArrayList<>();
    }
    
    public void agregaVehiculo(Vehiculo v){
        // Revisa si ya existe
        if(vehiculos.contains(v)){
            // Reemplaza
            vehiculos.set(vehiculos.indexOf(v), v);
        }else{
            // Agrega nuevo
            vehiculos.add(v);
        }
    }
    
    public void agregaSemaforo(Semaforo s){
        // Revisa si ya existe
        if(semaforos.contains(s)){
            // Reemplaza
            semaforos.set(semaforos.indexOf(s), s);
        }else{
            // Agrega nuevo
            semaforos.add(s);
        }
    }
    
    public Congestion revisaCongestiones(){
        
        // Logica para ver si hay congestion
        
        
        // Por ahora nadamas manda que si cada 20 veces que se revisa
        count++;
        if(count>19){
            count = 0;
            return new Congestion(50, 50, "Mucho movimiento", "Hoy", "Ahora");
        }
        return null;
    }
}
