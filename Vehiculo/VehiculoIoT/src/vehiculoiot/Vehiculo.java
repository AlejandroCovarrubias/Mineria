/**
 * Vehiculo.java
 * 
 * Creado el 20/04/2020 a las 09:29PM
 */
package vehiculoiot;

import conexion.ClientGPSVehiculo;
import conexion.REST_Transporte;
import interfaces.IVehiculo;
import java.util.Objects;
import objetos.Material;

/**
 * Implementación de la interfaz vehículo la cual añade la ubicación gps y la 
 * conexión.
 * 
 * @author Equipo Mineria.
 */
public class Vehiculo implements IVehiculo {

    private String matricula;
    private String nombre;
    private Material material; // Cambiar a material
    private double cantidad;
    private String medida;

    // Ubicacion con GPS, cambiar despues?
    private int x;
    private int y;

    // Conexiones
    private ClientGPSVehiculo socket;
    private REST_Transporte rest;

    /**
     * Constructor por omision.
     */
    public Vehiculo() {

    }

    /**
     * Constructor que inicializa todo los atributos.
     * 
     * @param matricula Matricula.
     * @param nombre Nombre.
     * @param material Material.
     * @param cantidad Cantidad.
     * @param medida Medida.
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     */
    public Vehiculo(String matricula, String nombre, Material material, double cantidad, String medida, int x, int y) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.material = material;
        this.cantidad = cantidad;
        this.medida = medida;
        this.x = x;
        this.y = y;
    }

    /**
     * Regresa el socket.
     * 
     * @return Socket
     */
    public ClientGPSVehiculo getSocket() {
        return socket;
    }

    /**
     * Establece el socket.
     * 
     * @param socket Socket.
     */
    public void setSocket(ClientGPSVehiculo socket) {
        this.socket = socket;
    }

    /**
     * Regresa el rest.
     * 
     * @return Rest.
     */
    public REST_Transporte getRest() {
        return rest;
    }

    /**
     * Establece el rest.
     * 
     * @param rest Rest.
     */
    public void setRest(REST_Transporte rest) {
        this.rest = rest;
    }

    /**
     * Regresa la matricula.
     * 
     * @return Matricula.
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Regresa el nombre.
     * 
     * @return Nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Regresa la matricula.
     * 
     * @return Matricula.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Regresa la cantidad.
     * 
     * @return Cantidad.
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * Regresa la medida.
     * 
     * @return Medida.
     */
    public String getMedida() {
        return medida;
    }

    /**
     * Regresa la posicion en X.
     * 
     * @return Coordenada en X.
     */
    public int getX() {
        return x;
    }

    /**
     * Regresa la posicion en Y.
     * 
     * @return Coordenada en Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Establece la matricula.
     * 
     * @param matricula Matricula.
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Establece el nombre.
     * 
     * @param nombre Nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el material.
     * 
     * @param material Material.
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Establece la cantidad.
     * 
     * @param cantidad Cantidad.
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Establece la medida.
     * 
     * @param medida Medida.
     */
    public void setMedida(String medida) {
        this.medida = medida;
    }

    /**
     * Establece X.
     * 
     * @param x Coordenada en X.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Establece Y.
     * 
     * @param y Coordenada en Y.
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.matricula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }
    
    
}
