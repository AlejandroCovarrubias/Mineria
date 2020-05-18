/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alejandro Galindo
 */
class Conexion {
    
    /**
     * Conector a la base de datos.
     */
    private static Connection instance;

    private static String servidor = "localhost"; //Nombre del servidor 
    private static String puerto = "3306"; //IP
    private static String user = "minerio"; //usuario loggin
    private static String password = "sesamo"; //Contraseña
    private static String baseDatos = "congestiones_api"; //Nombre de la base de datos
    private static String dbURL = "jdbc:mysql://localhost:3306/congestiones_api";
    
    /**
     * Establece una conexion con la BD
     *
     * @return Conexion con la BD
     * @throws SQLException

     */
    public static Connection getInstance() throws SQLException{
        if (instance == null) {
            instance = DriverManager.getConnection(dbURL, user, password);
        }
        return instance;
    }   
}