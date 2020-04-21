/**
 * INotificador.java
 * 
 * Creado el 20/04/2020 a las 09:03PM
 */
package semaforo;

/**
 * Interfaz que define los métodos para notificar y actualizar el estado de los
 * semáforos al ser expuesto por la interfaz ISemaforo.
 * 
 * @author Equipo Mineria.
 */
public interface INotificador {
    void notificar(String estado);
    void actualizar(String estado);
}
