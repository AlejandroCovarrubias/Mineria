/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Home
 */
public class SemaforoGrafico {

    Semaforo semaforo;

    public SemaforoGrafico(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public ComponenteMapa dibujar() {
        Color color = null;
        switch (semaforo.getEstado()) {
            case "VERDE":
                color = Color.GREEN;
                break;
            case "AMARILLO":
                color = Color.YELLOW;
                break;
            case "ROJO":
                color = Color.RED;
                break;

        }

        if (color == null) {
            return null;
        }

        return new ComponenteMapa(new Rectangle2D.Double(semaforo.getX(), semaforo.getY(), 50, 50), color, semaforo.getIdentificador());
    }
}
