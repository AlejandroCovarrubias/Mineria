/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.util.List;
import objetos.Semaforo;
import objetos.Vehiculo;

/**
 *
 * @author Home
 */
public interface INotificaciones {
    
    public List<String> obtenerCongestiones(List<Vehiculo> vehiculos,List<Semaforo> semaforos);
    
    public void registrarCongestion(String msg);
    
}
