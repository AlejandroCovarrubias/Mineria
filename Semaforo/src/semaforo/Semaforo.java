/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforo;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro Galindo
 */
public class Semaforo implements Runnable {

    private Estado estado = Estado.PENDIENTE;

    private int lapsoVerde = 15000;
    private int lapsoAmarillo = 4000;
    private int lapsoRojo = 10000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Semaforo semaforo = new Semaforo();
        semaforo.run();
    }

    public Estado getEstado() {
        return estado;
    }

    public synchronized void setEstado(Estado estado) {
        this.estado = estado;
    }

    public synchronized void setLapsoVerde(int lapsoVerde) {
        this.lapsoVerde = lapsoVerde;
    }

    public synchronized void setLapsoAmarillo(int lapsoAmarillo) {
        this.lapsoAmarillo = lapsoAmarillo;
    }

    public synchronized void setLapsoRojo(int lapsoRojo) {
        this.lapsoRojo = lapsoRojo;
    }

    @Override
    public void run() {
        while (true) {
            if (estado != Estado.PENDIENTE) {
                try {
                    switch (estado) {
                        case VERDE:
                            //Notificar el estado
                            System.out.println(estado);
                            sleep(lapsoVerde);
                            estado = Estado.AMARILLO;
                            break;
                        case AMARILLO:
                            System.out.println(estado);
                            sleep(lapsoAmarillo);
                            estado = Estado.ROJO;
                            break;
                        case ROJO:
                            System.out.println(estado);
                            sleep(lapsoRojo);
                            estado = Estado.VERDE;
                            break;
                        default:
                            break;
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Semaforo.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    synchronized (this) {
                        this.wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Semaforo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}