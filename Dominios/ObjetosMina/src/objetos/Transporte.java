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
public class Transporte {
    private Vehiculo vehiculo;
    private String nombreDeQuienEntrega;
    private Material material;
    private double cantidad;
    private String medida;

    public Transporte() {
    }

    public Transporte(Vehiculo vehiculo, String nombreDeQuienEntrega, Material material, double cantidad, String medida) {
        this.vehiculo = vehiculo;
        this.nombreDeQuienEntrega = nombreDeQuienEntrega;
        this.material = material;
        this.cantidad = cantidad;
        this.medida = medida;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getNombreDeQuienEntrega() {
        return nombreDeQuienEntrega;
    }

    public void setNombreDeQuienEntrega(String nombreDeQuienEntrega) {
        this.nombreDeQuienEntrega = nombreDeQuienEntrega;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Transporte{" + "vehiculo=" + vehiculo + ", nombreDeQuienEntrega=" + nombreDeQuienEntrega + ", material=" + material + ", cantidad=" + cantidad + ", medida=" + medida + '}';
    }
    
    
    
    
}
