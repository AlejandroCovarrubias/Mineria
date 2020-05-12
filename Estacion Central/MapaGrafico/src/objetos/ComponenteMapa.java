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
public class ComponenteMapa {

    private Rectangle2D dibujo;
    private Color color;
    private String id;

    public ComponenteMapa(Rectangle2D dibujo, Color color, String id) {
        this.dibujo = dibujo;
        this.color = color;
        this.id = id;
    }

    public ComponenteMapa() {
    }

    public Rectangle2D getDibujo() {
        return dibujo;
    }

    public void setDibujo(Rectangle2D dibujo) {
        this.dibujo = dibujo;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
