/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensaje;

import objetos.Semaforo;
import objetos.Vehiculo;

/**
 *
 * @author Home
 */
public class MensajeMina {
    
    private Tipo tipo;
    private Vehiculo vehiculo;
    private Semaforo semaforo;

    public MensajeMina(Tipo tipo) {
        this.tipo = tipo;
    }

    public MensajeMina(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        semaforo = null;
        //tipo = Tipo.VEHICULO;
    }

    public MensajeMina(Semaforo semaforo) {
        this.semaforo = semaforo;
        vehiculo = null;
        tipo = Tipo.SEMAFORO;
    }

    public MensajeMina() {
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Semaforo getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }
    
    
}
