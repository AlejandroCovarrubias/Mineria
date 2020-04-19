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
public class Vehiculo {
    private String matricula;
    private String nombreConductor;
    private String modelo;
    private String tipo;
    private int x;
    private int y;

    public Vehiculo() {
    }

    public Vehiculo(String matricula, String nombreConductor, String modelo, String tipo, int x, int y) {
        this.matricula = matricula;
        this.nombreConductor = nombreConductor;
        this.modelo = modelo;
        this.tipo = tipo;
        this.x = x;
        this.y = y;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
