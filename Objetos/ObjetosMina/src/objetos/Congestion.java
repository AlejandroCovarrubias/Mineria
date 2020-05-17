/**
 * Congestion.java
 * 
 * Creado el 20/04/2020 a las 08:04PM
 */
package objetos;

import java.util.Date;

/**
 * Representa una congestión (Una acumulación del tráfico determinada por el 
 * GPS).
 * 
 * @author Equipo Mina.
 */
public class Congestion {
    private int idCongestion;
    private double x;
    private double y;
    private String descripcion;
    private String fechaHora;
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
     */
    public Congestion(double x, double y, String descripcion, String fecha) {
        this.x = x;
        this.y = y;
        this.descripcion = descripcion;
        this.fechaHora = fecha;
    }

    public Congestion(int idCongestion, double x, double y, String descripcion, String fecha) {
        this.idCongestion = idCongestion;
        this.x = x;
        this.y = y;
        this.descripcion = descripcion;
        this.fechaHora = fecha;
    }

    public int getIdCongestion() {
        return idCongestion;
    }

    public void setIdCongestion(int idCongestion) {
        this.idCongestion = idCongestion;
    }
    
    /**
     * Regresa X.
     * 
     * @return Coordenada en X.
     */
    public double getX() {
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
    public double getY() {
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

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Congestion{" + "idCongestion=" + idCongestion + ", x=" + x + ", y=" + y + ", descripcion=" + descripcion + ", fecha=" + fechaHora + '}';
    }
}