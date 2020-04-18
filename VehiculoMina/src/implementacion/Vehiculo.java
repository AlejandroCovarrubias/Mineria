/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacion;

import conexion.ClientGPSVehiculo;
import conexion.REST_Transporte;
import interfaz.IVehiculo;

/**
 *
 * @author Home
 */
public class Vehiculo implements IVehiculo {
    private String matricula;
    private String nombre;
    private String material; // Cambiar a material
    private double cantidad;
    private String medida;
    
    // Ubicacion con GPS, cambiar despues?
    private int x;
    private int y;
    
    // Conexiones
    private ClientGPSVehiculo socket;
    private REST_Transporte rest;

    public Vehiculo() {
        
    }
    
    public Vehiculo(String matricula, String nombre, String material, double cantidad, String medida, int x, int y) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.material = material;
        this.cantidad = cantidad;
        this.medida = medida;
        this.x = x;
        this.y = y;
    }

    public ClientGPSVehiculo getSocket() {
        return socket;
    }

    public void setSocket(ClientGPSVehiculo socket) {
        this.socket = socket;
    }

    public REST_Transporte getRest() {
        return rest;
    }

    public void setRest(REST_Transporte rest) {
        this.rest = rest;
    }
    
    
    

    public String getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMaterial() {
        return material;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getMedida() {
        return medida;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    

    
    
}
