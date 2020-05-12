/**
 * MensajeMina.java
 *
 * Creado el 20/04/2020 a las 07:53PM
 */
package mensajemina;

import objetos.Congestion;
import objetos.Semaforo;
import objetos.Vehiculo;

/**
 * Mensaje que se manda a través de del websocket del GPS y contiene o un
 * vehículo, semáforo o congestión para utilizar en la estación central.
 *
 * @author Equipo Mineria.
 */
public class MensajeMina {

    private TipoMina tipo;
    private Vehiculo vehiculo;
    private Semaforo semaforo;
    private Congestion congestion;

    /**
     * Constructor para inicializar con el tipo mina.
     *
     * @param tipo Tipo de la mina.
     */
    public MensajeMina(TipoMina tipo) {
        this.tipo = tipo;
    }

    /**
     * Constructor para inicializar con el vehiculo.
     *
     * @param vehiculo Vehiculo.
     */
    public MensajeMina(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        semaforo = null;
        congestion = null;
        tipo = TipoMina.VEHICULO;
    }

    /**
     * Constructor para inicializar con el semaforo.
     *
     * @param semaforo Semaforo.
     */
    public MensajeMina(Semaforo semaforo) {
        this.semaforo = semaforo;
        vehiculo = null;
        congestion = null;
        tipo = TipoMina.SEMAFORO;
    }

    public MensajeMina(Congestion congestion) {
        this.congestion = congestion;
        semaforo = null;
        vehiculo = null;
        tipo = TipoMina.CONGESTION;
    }

    /**
     * Constructor por omision.
     */
    public MensajeMina() {
    }

    /**
     * Regresa el tipo de mina.
     *
     * @return Tipo de mina.
     */
    public TipoMina getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de mina.
     *
     * @param tipo Tipo mina.
     */
    public void setTipo(TipoMina tipo) {
        this.tipo = tipo;
    }

    /**
     * Regresa el vehiculo.
     *
     * @return Vehiculo.
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * Establece el vehiculo.
     *
     * @param vehiculo Vehiculo.
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * Regresa el semaforo.
     *
     * @return Semaforo.
     */
    public Semaforo getSemaforo() {
        return semaforo;
    }

    /**
     * Establece el semaforo.
     *
     * @param semaforo Semaforo.
     */
    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public Congestion getCongestion() {
        return congestion;
    }

    public void setCongestion(Congestion congestion) {
        this.congestion = congestion;
    }
}