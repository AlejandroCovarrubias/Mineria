/**
 * IVehiculo.java
 * 
 * Creada el 20/04/2020 a las 09:28PM
 */
package vehiculoiot;

/**
 * Interfaz que establece lo necesario para un vehículo y su transporte.
 * 
 * @author Equipo Mineria.
 */
public interface IVehiculo {
    
    public String getMatricula();

    public String getNombre();

    public String getMaterial();

    public double getCantidad();

    public String getMedida();

    public int getX();

    public int getY();
    
}
