/**
 * Transformador.java
 * 
 * Creado el 20/04/2020 a las 08:57PM
 */
package transformador;

import interfaces.ISemaforo;
import interfaces.IVehiculo;
import objetos.Material;
import objetos.Semaforo;
import objetos.Vehiculo;

/**
 *
 * @author Equipo Mineria.
 */
public class Transformador {

    // Constantes
    private static int minViejo = 0;
    private static int maxViejo = 18500;

    private static int minNuevo = 0;
    private static int maxNuevo = 100;

    public static Semaforo transformarSemaforo(String semaforo) {
        Semaforo nuevo = new Semaforo();

        // Pasa datos y esas weas
        return nuevo;
    }

    public static Vehiculo transformarVehiculo(String vehiculo) {
        Vehiculo nuevo = new Vehiculo();

        // Obtiene datos
        String[] datos = vehiculo.split(",");
        // matricula, nombre, x, y

        // Datos que se pueden pasar facil
        nuevo.setMatricula(datos[0]);
        nuevo.setNombreConductor(datos[1]);
        nuevo.setModelo("Carro de mina");
        nuevo.setTipo("Regular");

        int rangoViejo = (maxViejo - minViejo);
        int rangoNuevo = (maxNuevo - minNuevo);

        int x = (((Integer.parseInt(datos[2]) - minViejo) * rangoNuevo) / rangoViejo) + minNuevo;
        int y = (((Integer.parseInt(datos[3]) - minViejo) * rangoNuevo) / rangoViejo) + minNuevo;

        nuevo.setX(x);
        nuevo.setY(y);

        return nuevo;
    }

}
