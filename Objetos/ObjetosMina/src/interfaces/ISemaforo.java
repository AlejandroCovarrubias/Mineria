/**
 * ISemaforo.java
 * 
 * Creado el 20/04/2020 a las 07:30PM
 */
package interfaces;

/**
 * Interfaz de un semáforo en el entorno de IoT.
 * 
 * @author Equipo Mineria.
 */
public interface ISemaforo {
    
    public String getIdentificador();
    
    public String getEstado();

    public int getX();

    public int getY();
}
