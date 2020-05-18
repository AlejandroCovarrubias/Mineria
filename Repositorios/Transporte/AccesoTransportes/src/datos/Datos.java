/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author Alejandro Galindo
 */
public class Datos {
    public static IDatos getFacade(){
        return new FDatos();
    }
}
