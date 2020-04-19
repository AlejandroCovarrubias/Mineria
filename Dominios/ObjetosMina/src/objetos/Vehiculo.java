/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Vehiculo{" + "matricula=" + matricula + ", nombreConductor=" + nombreConductor + ", modelo=" + modelo + ", tipo=" + tipo + ", x=" + x + ", y=" + y + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.matricula);
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
        final Vehiculo other = (Vehiculo) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
