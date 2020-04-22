/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorSemaforos;

import interfaces.ISemaforo;

/**
 *
 * @author Home
 */
public class SemaforoIoT implements ISemaforo{

    private String identificador;
    private String estado;
    private int x;
    private int y;
    
    public SemaforoIoT() {
    }

    public SemaforoIoT(String identificador, String estado, int x, int y) {
        this.identificador = identificador;
        this.estado = estado;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String getIdentificador() {
        return identificador;
    }

    @Override
    public String getEstado() {
        return estado;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    
    
}
