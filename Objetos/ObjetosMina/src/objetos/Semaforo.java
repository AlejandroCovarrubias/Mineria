/**
 * Semaforo.java
 * 
 * Creado el 20/04/2020 a las 08:21PM
 */
package objetos;

/**
 * Representa un semaforo.
 * 
 * @author Equipo Mineria.
 */
public class Semaforo {
    private String identificador;
    private String estado;
    private int x;
    private int y;

    /**
     * Constructor por omision.
     */
    public Semaforo() {
    }

    /**
     * Constructor para inicializar el identificador, el estado, Y y X.
     * 
     * @param identificador Identificador.
     * @param estado estado.
     * @param x X.
     * @param y Y.
     */
    public Semaforo(String identificador, String estado, int x, int y) {
        this.identificador = identificador;
        this.estado = estado;
        this.x = x;
        this.y = y;
    }

    /**
     * Regresa el identificador.
     * 
     * @return Identificador.
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Establece el identificador.
     * 
     * @param identificador Identificador.
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * Regresa el estado.
     * 
     * @return Estado.
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Establece el estado.
     * 
     * @param estado.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Regresa X.
     * 
     * @return X.
     */
    public int getX() {
        return x;
    }

    /**
     * Establece X.
     * 
     * @param x X.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Regresa Y.
     * 
     * @return Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Establece Y.
     * 
     * @param y Y.
     */
    public void setY(int y) {
        this.y = y;
    }
}
