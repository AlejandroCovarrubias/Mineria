/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajemina;

import objetos.Semaforo;
import objetos.Vehiculo;

/**
 *
 * @author Home
 */
public class MensajeMina {
    
    private TipoMina tipo;
    private Vehiculo vehiculo;
    private Semaforo semaforo;

    public MensajeMina(TipoMina tipo) {
        this.tipo = tipo;
    }

    public MensajeMina(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        semaforo = null;
        tipo = TipoMina.VEHICULO;
    }

    public MensajeMina(Semaforo semaforo) {
        this.semaforo = semaforo;
        vehiculo = null;
        tipo = TipoMina.SEMAFORO;
    }

    public MensajeMina() {
    }

    public TipoMina getTipo() {
        return tipo;
    }

    public void setTipo(TipoMina tipo) {
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
