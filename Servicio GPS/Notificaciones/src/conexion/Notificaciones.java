/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import conexion.REST_CongestionesClient;
import conexion.INotificaciones;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import objetos.Congestion;
import objetos.Semaforo;
import objetos.Vehiculo;

/**
 *
 * @author Home
 */
public class Notificaciones implements INotificaciones{
    private REST_CongestionesClient rest;
    private List<Vehiculo> vehiculos;
    private List<Semaforo> semaforos;
    private boolean autenticado = false;
    
    private String IDENTIFICADOR = "SISTEMAGPS";
    private String PASSWORD = "mineria1234";
    
    // debug
    int count = 0;

    public Notificaciones() {
        vehiculos = new ArrayList<>();
        semaforos = new ArrayList<>();
        rest = new REST_CongestionesClient();
        //quiza aqui no es buen lugar para autenticar
        //autenticado = rest.autenticar(IDENTIFICADOR, PASSWORD);
    }

    @Override
    public void agregarVehiculo(Vehiculo v){
        // Revisa si ya existe
        if(vehiculos.contains(v)){
            // Reemplaza
            vehiculos.set(vehiculos.indexOf(v), v);
        }else{
            // Agrega nuevo
            vehiculos.add(v);
        }
    }
    
    @Override
    public void agregarSemaforo(Semaforo s){
        // Revisa si ya existe
        if(semaforos.contains(s)){
            // Reemplaza
            semaforos.set(semaforos.indexOf(s), s);
        }else{
            // Agrega nuevo
            semaforos.add(s);
        }
    }
    
    @Override
    public List<Congestion> obtenerCongestiones(){
        List<Congestion> lista = new ArrayList<>();
        // Logica para ver si hay congestion
        
        
        // Por ahora nadamas manda que si cada 20 veces que se revisa
        // Importante seguir el formato de Datetime de MySQL para la fecha
        // en String '2020-05-16 10:10:10'
        count++;
        if(count>19){
            count = 0;
            
            GregorianCalendar cl = new GregorianCalendar();
            String fechaS = cl.get(GregorianCalendar.YEAR) + "-" +
                    (cl.get(GregorianCalendar.MONTH) + 1) + "-" +
                    cl.get(GregorianCalendar.DAY_OF_MONTH) + " " +
                    cl.get(GregorianCalendar.HOUR) + ":" +
                    cl.get(GregorianCalendar.MINUTE) + ":" +
                    cl.get(GregorianCalendar.SECOND);
            
            Congestion g = new Congestion(50, 50, "Mucho movimiento", fechaS);
            lista.add( g );
            
            // Manda al REST
            registrarCongestion(g);
        }
        return lista;
    }
    
    /**
     * Registra una congestion.
     * 
     * @param g La congestion a registrar
     */
    @Override
    public void registrarCongestion(Congestion g) {
        // La manda con REST
        
        //Si no está autenticado
        if(!autenticado){
            //autentica 4 veces
            
            for (int i = 0; i < 4; i++) {
                autenticado = rest.autenticar(IDENTIFICADOR, PASSWORD);
                //Si autentica
                if(autenticado){
                    //Agrega congestion y concluye el metodo
                    rest.agregarCongestion(g);
                    return;
                }
            }
            
            //Si no autenticó en 4 intentos
            System.out.println("No se pudo autenticar con la API de Congestiones");

        //Si ya está autenticado (Tiene token)
        }else{
            rest.agregarCongestion(g);
        }
    }
}
