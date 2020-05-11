/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazMapa;

import java.util.ArrayList;
import java.util.List;
import objetos.ComponenteMapa;
import objetos.Semaforo;
import objetos.SemaforoGrafico;
import objetos.Vehiculo;
import objetos.VehiculoGrafico;

/**
 *
 * @author Home
 */
public class Mapa implements IMapa{
    private List<SemaforoGrafico> semaforos;
    private List<VehiculoGrafico> vehiculos;

    public Mapa() {
        semaforos = new ArrayList();
        vehiculos = new ArrayList();
    }

    @Override
    public List<ComponenteMapa> obtenerComponente() {
        ArrayList<ComponenteMapa> nueva = new ArrayList<>();
        for(VehiculoGrafico veh : vehiculos)
            nueva.add(veh.dibujar());
        for(SemaforoGrafico sem : semaforos)
            nueva.add(sem.dibujar());
        return nueva;
    }

    @Override
    public void actualizarSemaforos(List<Semaforo> semaforos) {
        ArrayList<SemaforoGrafico> nueva = new ArrayList<>();
        for(Semaforo sem : semaforos){
            nueva.add(new SemaforoGrafico(sem));
        }
        this.semaforos = nueva;
    }

    @Override
    public void actualziarVehiculos(List<Vehiculo> vehiculos) {
        ArrayList<VehiculoGrafico> nueva = new ArrayList<>();
        for(Vehiculo veh : vehiculos){
            nueva.add(new VehiculoGrafico(veh));
        }
        this.vehiculos = nueva;
    }
    
    
    
    
}
