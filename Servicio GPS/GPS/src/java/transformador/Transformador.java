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

    private static int minNuevoX = 0;
    private static int maxNuevoX = 900;
    
    private static int minNuevoY = 0;
    private static int maxNuevoY = 550;
    

    public static Semaforo transformarSemaforo(String semaforo) {
        Semaforo nuevo = new Semaforo();

        // Obtiene datos
        String[] datos = semaforo.split(",");
        // identificador, estado, x, y
        
        // Pasa datos
        nuevo.setIdentificador(datos[0]);
        nuevo.setEstado(datos[1]);
        
        int rangoViejo = (maxViejo - minViejo);
        int rangoNuevoX = (maxNuevoX - minNuevoX);
        int rangoNuevoY = (maxNuevoY - minNuevoY);
        
        int x = (((Integer.parseInt(datos[2]) - minViejo) * rangoNuevoX) / rangoViejo) + minNuevoX;
        int y = (((Integer.parseInt(datos[3]) - minViejo) * rangoNuevoY) / rangoViejo) + minNuevoY;
        
        nuevo.setX(x);
        nuevo.setY(y);
        
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
        int rangoNuevoX = (maxNuevoX - minNuevoX);
        int rangoNuevoY = (maxNuevoY - minNuevoY);
        
        int x = (((Integer.parseInt(datos[2]) - minViejo) * rangoNuevoX) / rangoViejo) + minNuevoX;
        int y = (((Integer.parseInt(datos[3]) - minViejo) * rangoNuevoY) / rangoViejo) + minNuevoY;

        nuevo.setX(x);
        nuevo.setY(y);

        return nuevo;
    }

}
