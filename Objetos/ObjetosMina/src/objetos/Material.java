/**
 * Material.java
 * 
 * Creado el 20/04/2020 a las 08:15PM
 */
package objetos;

/**
 * Representa el material que se transporta.
 * 
 * @author Equipo Mineria.
 */
public class Material {
    private String nombre;
    private String descripcion;

    /**
     * Constructor por omision.
     */
    public Material() {
    }

    /**
     * Constructor para inicializar el nombre y la descripcion.
     * 
     * @param nombre Nombre.
     * @param descripcion Descripcion.
     */
    public Material(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Regresa el nombre
     * 
     * @return Nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establce el nombre.
     * 
     * @param nombre Nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa la descripcion.
     * 
     * @return Descripcion.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripcion.
     * 
     * @param descripcion Descripcion.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Regresa una cadena de caracteres con los valores de los atributos.
     * 
     * @return Cadena de caracteres.
     */
    @Override
    public String toString() {
        return "Material{" + "nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
