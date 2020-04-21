/**
 * Transporte.java
 * 
 * Creado el 20/04/2020 a las 08:43PM
 */
package objetos;

/**
 * Representa el transporte de material por la mina.
 * 
 * @author Equipo Mineria.
 */
public class Transporte {
    private Vehiculo vehiculo;
    private String nombreDeQuienEntrega;
    private Material material;
    private double cantidad;
    private String medida;

    /**
     * Constructor por omision.
     */
    public Transporte() {
    }

    /**
     * Constructor para inicializar los atributos del transporte.
     * 
     * @param vehiculo vehiculo.
     * @param nombreDeQuienEntrega Nombre de quien entrega.
     * @param material Material.
     * @param cantidad Cantidad.
     * @param medida Medida.
     */
    public Transporte(Vehiculo vehiculo, String nombreDeQuienEntrega, Material material, double cantidad, String medida) {
        this.vehiculo = vehiculo;
        this.nombreDeQuienEntrega = nombreDeQuienEntrega;
        this.material = material;
        this.cantidad = cantidad;
        this.medida = medida;
    }

    /**
     * Regresa el vehiculo.
     * 
     * @return Vehiculo.
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * Establece el vehiculo.
     * 
     * @param vehiculo 
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    /**
     * Regresa el nombre de quien entrega.
     * 
     * @return 
     */
    public String getNombreDeQuienEntrega() {
        return nombreDeQuienEntrega;
    }

    /**
     * Establece el nombre de quien entrega.
     * 
     * @param nombreDeQuienEntrega 
     */
    public void setNombreDeQuienEntrega(String nombreDeQuienEntrega) {
        this.nombreDeQuienEntrega = nombreDeQuienEntrega;
    }

    /**
     * Regresa cantidad.
     * 
     * @return 
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad.
     * 
     * @param cantidad 
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Regresa la medida.
     * 
     * @return 
     */
    public String getMedida() {
        return medida;
    }

    /**
     * Establece la medida.
     * 
     * @param medida 
     */
    public void setMedida(String medida) {
        this.medida = medida;
    }

    /**
     * Regresa el material.
     * 
     * @return 
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Establece el material.
     * 
     * @param material 
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Regresa una cadena de caractres con los atributos del transporte.
     * 
     * @return Cadena de caracteres.
     */
    @Override
    public String toString() {
        return "Transporte{" + "vehiculo=" + vehiculo + ", nombreDeQuienEntrega=" + nombreDeQuienEntrega + ", material=" + material + ", cantidad=" + cantidad + ", medida=" + medida + '}';
    }
}
