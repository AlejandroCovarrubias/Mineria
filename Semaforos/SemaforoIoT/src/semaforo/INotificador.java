/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforo;

/**
 *
 * @author Alejandro Galindo
 */
public interface INotificador {
    void notificar(String estado);
    void actualizar(String estado);
}
