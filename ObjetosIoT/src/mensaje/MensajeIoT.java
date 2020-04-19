/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensaje;

import interfaces.ISemaforo;
import interfaces.IVehiculo;

/**
 *
 * @author Home
 */
public class MensajeIoT {
    
    private Tipo tipo;
    private String contenido;

    public MensajeIoT() {
    }

    public MensajeIoT(Tipo tipo) {
        this.tipo = tipo;
    }

    public MensajeIoT(IVehiculo vehiculo) {
        tipo = Tipo.MOVER_VEHICULO;
        contenido = ""+vehiculo.getMatricula()+","+vehiculo.getNombre()+","+vehiculo.getX()+","+vehiculo.getY();
        
    }

    public MensajeIoT(ISemaforo semaforo) {
        tipo = Tipo.ACTUALIZAR_SEMAFORO;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    
    
    
    
    
    
    
    
}
