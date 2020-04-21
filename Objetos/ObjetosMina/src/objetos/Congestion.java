/**
 * Congestion.java
 * 
 * Creado el 20/04/2020 a las 08:04PM
 */
package objetos;

/**
 * Representa una congestión (Una acumulación del tráfico determinada por el 
 * GPS).
 * 
 * @author Equipo Mina.
 */
public class Congestion {
    private int x;
    private int y;
    private String descripcion;
    private String fecha;
    private String hora;

    /**
     * Constructor por omision.
     */
    public Congestion() {
    }

    /**
     * Constructor para inicializar todos los atributos.
     * 
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     * @param descripcion Descripcion.
     * @param fecha Fecha.
     * @param hora Hora.
     */
    public Congestion(int x, int y, String descripcion, String fecha, String hora) {
        this.x = x;
        this.y = y;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
    }
    
    /**
     * Regresa X.
     * 
     * @return Coordenada en X.
     */
    public int getX() {
        return x;
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
     * Regresa Y.
     * 
     * @return Coordenada en Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Establece Y.
     * 
     * @param y Coordena en Y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Regresa la descripcion.
     * 
     * @return Descipcion
     */
    public String getDescripcion() {
        return descripcion;
    }
 
    /**
     * Establece la descripcion.
     * 
     * @param descripcion Descripción.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Regresa la fecha.
     * 
     * @return Fecha.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha.
     * 
     * @param fecha Fecha.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Regresa la hora.
     * 
     * @return Hora.
     */
    public String getHora() {
        return hora;
    }

    /**
     * Establece la hora.
     * 
     * @param hora Hora.
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Regresa todos los atributos en una cadena de caracteres.
     * 
     * @return Cadena de caracteres.
     */
    @Override
    public String toString() {
        return "Congestion{" + "x=" + x + ", y=" + y + ", descripcion=" + descripcion + ", fecha=" + fecha + ", hora=" + hora + '}';
    }
}
