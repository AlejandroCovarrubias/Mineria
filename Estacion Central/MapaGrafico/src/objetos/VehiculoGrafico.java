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
public class VehiculoGrafico {

    Vehiculo vehiculo;

    public VehiculoGrafico(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public ComponenteMapa dibujar() {
        return new ComponenteMapa(new Rectangle2D.Double(vehiculo.getX(), vehiculo.getY(), 50, 50), Color.black, vehiculo.getMatricula());
    }

}
