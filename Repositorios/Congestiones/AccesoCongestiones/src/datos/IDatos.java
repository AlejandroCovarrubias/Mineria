/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.List;
import usuarios.Usuario;

/**
 *
 * @author Alejandro Galindo
 */
public interface IDatos {
    void crearUsuario(Usuario usuario) throws Exception;
    void editarUsuario(Usuario usuario) throws Exception;
    void eliminarUsuario(int idusuario) throws Exception;
    List<Usuario> obtenerUsuarios() throws Exception;
    Usuario obtenerUsuario(int idusuario) throws Exception;
    Usuario validar(String correo, String contrasenia) throws Exception;
}