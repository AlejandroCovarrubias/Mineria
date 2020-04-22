/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro Galindo
 */
public class Controlador implements Runnable {

    private ISemaforo semaforo;

    public Controlador(ISemaforo semaforo) {
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    //Revisa el estado actual del semaforo
                    if (semaforo.obtenerEstado().equals("VERDE")) {
                        semaforo.notificar(semaforo.obtenerEstado());
                        
                        //Espera por 8 segundos o hasta recibir notify()
                        long tBefore = System.currentTimeMillis();
                        long timeOut = 8000;
                        wait(timeOut);

                        //Si no fue notificado, cambia de estado
                        long t = (System.currentTimeMillis() - tBefore);
                        if(t >= timeOut){
                            semaforo.establecerEstado("AMARILLO");
                        }
                    } else if (semaforo.obtenerEstado().equals("AMARILLO")) {
                        semaforo.notificar(semaforo.obtenerEstado());

                        //Espera por 4 segundos o hasta recibir notify()
                        long tBefore = System.currentTimeMillis();
                        long timeOut = 4000;
                        wait(timeOut);

                        //Si no fue notificado, cambia de estado
                        long t = (System.currentTimeMillis() - tBefore);
                        if(t >= timeOut){
                            semaforo.establecerEstado("ROJO");
                        }
                    } else if (semaforo.obtenerEstado().equals("ROJO")) {
                        semaforo.notificar(semaforo.obtenerEstado());

                        //Espera por 6 segundos o hasta recibir notify()
                        long tBefore = System.currentTimeMillis();
                        long timeOut = 6000;
                        wait(timeOut);

                        //Si no fue notificado, cambia de estado
                        long t = (System.currentTimeMillis() - tBefore);
                        if(t >= timeOut){
                            semaforo.establecerEstado("VERDE");
                        }
                    }
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}