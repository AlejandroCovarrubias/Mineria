/**
 * Semaforo.java
 * 
 * Creado el 20/04/2020 a las 09:03PM
 */
package semaforo;

import java.util.Objects;

/**
 * Representa un semaforo fisico.
 * 
 * @author Equipo Mineria.
 */
public class Semaforo extends ISemaforo{
    private String identificador;
    private String estado = "VERDE";

    /**
     * Constructor que inicializa su atributo Identificador.
     * 
     * @param identificador Identificador.
     */
    public Semaforo(String identificador) {
        this.identificador = identificador;
    }

    /**
     * Regresa el codigo Hash del semaforo en cuestion.
     * 
     * @return Codigo Hash.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.identificador);
        return hash;
    }

    /**
     * Compara 2 objetos para ver si son o no los mismos.
     * 
     * @param obj Objeto.
     * @return Si es o no es el objeto.
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
        final Semaforo other = (Semaforo) obj;
        if (!Objects.equals(this.identificador, other.identificador)) {
            return false;
        }
        return true;
    }

    /**
     * Regresa una cadena de caractres con todos los atributos del semaforo.
     * 
     * @return Cadena de caracteres.
     */
    @Override
    public String toString() {
        return "Semaforo{" + "identificador=" + identificador + ", estado=" + estado + '}';
    }  

    /**
     * Notifica.
     * 
     * @param estado Estado.
     */
    @Override
    void notificar(String estado) {
        for (INotificador notif : notifs) {
            notif.notificar(estado);
        }
    }

    /**
     * Inicia el ciclo.
     */
    @Override
    void iniciarCiclo() {
    }
    
    /**
     * Establece el estado del semaforo.
     * 
     * @param estado Estado del semaforo.
     */
    public void establecerEstado(String estado) {
        this.estado = estado;
        System.out.println("[S] Cambiando a... " + estado);
        System.out.println("-----------------------------------");
    }

    /**
     * Regresa el estado.
     * 
     * @return Estado.
     */
    @Override
    String obtenerEstado() {
        return this.estado;
    }
}