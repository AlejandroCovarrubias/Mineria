/**
 * Vehiculo.java.
 * 
 * Creado el 20/04/2020 a las 08:30PM
 */
package objetos;

import java.util.Objects;

/**
 * Representa un vehiculo de la mina con la información necesaria para 
 * dibujar gráficamente.
 * 
 * @author Equipo Mineria.
 */
public class Vehiculo {
    private String matricula;
    private String nombreConductor;
    private String modelo;
    private String tipo;
    private int x;
    private int y;

    /**
     * Constructor por omision.
     */
    public Vehiculo() {
    }

    /**
     * Constructor para inicializar todos los atributos de la clase.
     * 
     * @param matricula Matricula.
     * @param nombreConductor Nombre Conductor.
     * @param modelo Modelo.
     * @param tipo Tipo.
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     */
    public Vehiculo(String matricula, String nombreConductor, String modelo, String tipo, int x, int y) {
        this.matricula = matricula;
        this.nombreConductor = nombreConductor;
        this.modelo = modelo;
        this.tipo = tipo;
        this.x = x;
        this.y = y;
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
     * Establece la matricula.
     * 
     * @param matricula Matricula.
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Regresa el nombre del conductor.
     * 
     * @return Nombre del conductor.
     */
    public String getNombreConductor() {
        return nombreConductor;
    }

    /**
     * Establece el nombre del conductor.
     * 
     * @param nombreConductor Nombre del conductor.
     */
    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    /**
     * Regresa el modelo.
     * 
     * @return Modelo.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo.
     * 
     * @param modelo Modelo.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Regresa el tipo.
     * 
     * @return Tipo.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo.
     * 
     * @param tipo Tipo.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
     * Establece la coordenada en Y.
     * 
     * @param y Coordenada en Y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Regresa una cadena de caracteres con todos los datos del vehiculo.
     * 
     * @return Cadena de caracteres.
     */
    @Override
    public String toString() {
        return "Vehiculo{" + "matricula=" + matricula + ", nombreConductor=" + nombreConductor + ", modelo=" + modelo + ", tipo=" + tipo + ", x=" + x + ", y=" + y + '}';
    }

    /**
     * 
     * 
     * @return Codigo Hash del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.matricula);
        return hash;
    }

    /**
     * Compara que sean los mismos objetos mediante la matricula.
     * 
     * @param obj Objeto
     * @return Si es o no el objeto.
     */
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
