/**
 * MensajeIoT.java
 * 
 * Creado el 20/04/2020 a las 07:33PM
 */
package mensajeIoT;

import interfaces.ISemaforo;
import interfaces.IVehiculo;

/**
 * Mensaje que se manda a través de del websocket del GPS y contiene alguna 
 * información que puede ser utilizada por este mismo para hacer distintas 
 * funciones indicadas en TipoIoT.
 * 
 * @author Equipo Mineria.
 */
public class MensajeIoT {
    
    private TipoIoT tipo;
    private String contenido;

    /**
     * Constructor por omision.
     */
    public MensajeIoT() {
    }

    /**
     * Constructor para inicializar el TipoIoT.
     * @param tipo Tipo de IoT.
     */
    public MensajeIoT(TipoIoT tipo) {
        this.tipo = tipo;
    }

    /**
     * Constructor para inicializar el vehiculo.
     * 
     * @param vehiculo Vehiculo.
     */
    public MensajeIoT(IVehiculo vehiculo) {
        tipo = TipoIoT.MOVER_VEHICULO;
        contenido = ""+vehiculo.getMatricula()+","+vehiculo.getNombre()+","+vehiculo.getX()+","+vehiculo.getY();
    }

    /**
     * Constructor para inicializar el semaforo.
     * 
     * @param semaforo Semaforo.
     */
    public MensajeIoT(ISemaforo semaforo) {
        tipo = TipoIoT.ACTUALIZAR_SEMAFORO_CONTROLADOR;
        contenido = ""+semaforo.getIdentificador()+","+semaforo.getEstado()+","+semaforo.getX()+","+semaforo.getY();
    }

    /**
     * Regresa el tipo de IoT.
     * 
     * @return Regresa el tipo de IoT.
     */
    public TipoIoT getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de IoT.
     * 
     * @param tipo Establece el tipo de IoT.
     */
    public void setTipo(TipoIoT tipo) {
        this.tipo = tipo;
    }

    /**
     * Regresa el contenido del mensaje.
     * 
     * @return Contenido del mensaje.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Establece el contenido del mensaje.
     * 
     * @param contenido Contenido del mensaje.
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
