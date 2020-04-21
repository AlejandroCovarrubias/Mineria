/**
 * IVehiculo.java
 * 
 * Creado el 20/04/2020 a las 07:32PM
 */
package interfaces;

import objetos.Material;


/**
 * Interfaz de un vehículo en el entorno de IoT.
 * 
 * @author Equipo Mineria.
 */
public interface IVehiculo {
    
    public String getMatricula();

    public String getNombre();

    public Material getMaterial();

    public double getCantidad();

    public String getMedida();

    public int getX();

    public int getY();
}
