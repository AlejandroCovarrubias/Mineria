/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforo;

import java.util.Objects;

/**
 *
 * @author Alejandro Galindo
 */
public class Semaforo extends ISemaforo{
    private String identificador;
    private String estado = "VERDE";

    public Semaforo(String identificador) {
        this.identificador = identificador;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.identificador);
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
        final Semaforo other = (Semaforo) obj;
        if (!Objects.equals(this.identificador, other.identificador)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Semaforo{" + "identificador=" + identificador + ", estado=" + estado + '}';
    }  

    @Override
    void notificar(String estado) {
        for (INotificador notif : notifs) {
            notif.notificar(estado);
        }
    }

    @Override
    void iniciarCiclo() {
    }

    @Override
    String obtenerEstado() {
        return this.estado;
    }
}