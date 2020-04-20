/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author Home
 */
public class Semaforo {
    private String identificador;
    private String estado;
    private int x;
    private int y;

    public Semaforo() {
    }

    public Semaforo(String identificador, String estado, int x, int y) {
        this.identificador = identificador;
        this.estado = estado;
        this.x = x;
        this.y = y;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
