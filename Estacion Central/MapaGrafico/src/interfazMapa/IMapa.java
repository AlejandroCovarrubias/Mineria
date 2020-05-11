/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazMapa;

import java.awt.Graphics;
import java.util.List;
import objetos.Semaforo;
import objetos.Vehiculo;

/**
 *
 * @author Home
 */
public interface IMapa {
    
    public void actualizarComponentes();
    public void actualizarSemaforos(List<Semaforo> semaforos);
    public void actualziarVehiculos(List<Vehiculo> vehiculos);
    public void dibujarMapa(Graphics g);
    
}
