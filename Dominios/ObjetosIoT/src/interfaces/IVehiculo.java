/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import objetos.Material;


/**
 *
 * @author Home
 */
public interface IVehiculo {
    
    public String getMatricula();

    public String getNombre();

    public Material getMaterial();

    public double getCantidad();

    public String getMedida();

    public int getX();

    public int getY();
    
    
}
