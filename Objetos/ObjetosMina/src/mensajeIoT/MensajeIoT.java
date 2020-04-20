/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeIoT;

import interfaces.ISemaforo;
import interfaces.IVehiculo;

/**
 *
 * @author Home
 */
public class MensajeIoT {
    
    private TipoIoT tipo;
    private String contenido;

    public MensajeIoT() {
    }

    public MensajeIoT(TipoIoT tipo) {
        this.tipo = tipo;
    }

    public MensajeIoT(IVehiculo vehiculo) {
        tipo = TipoIoT.MOVER_VEHICULO;
        contenido = ""+vehiculo.getMatricula()+","+vehiculo.getNombre()+","+vehiculo.getX()+","+vehiculo.getY();
        
    }

    public MensajeIoT(ISemaforo semaforo) {
        tipo = TipoIoT.ACTUALIZAR_SEMAFORO;
    }

    public TipoIoT getTipo() {
        return tipo;
    }

    public void setTipo(TipoIoT tipo) {
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    
    
    
    
    
    
    
    
}
