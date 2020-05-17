/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import usuarios.Usuario;
import java.util.List;

/**
 *
 * @author Alejandro Galindo
 */
class FDatos implements IDatos {
    
    UsuarioDAO usuarios = new UsuarioDAO();

    @Override
    public void crearUsuario(Usuario usuario) throws Exception {
        try {
            usuarios.crear(usuario);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public void editarUsuario(Usuario usuario) throws Exception {
        try {
            usuarios.editar(usuario);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public void eliminarUsuario(int idusuario) throws Exception {
        try {
            usuarios.eliminar(idusuario);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public List<Usuario> obtenerUsuarios() throws Exception {
        try {
            return usuarios.obtenerTodos();
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public Usuario obtenerUsuario(int idusuario) throws Exception {
        try {
            return usuarios.obtener(idusuario);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public Usuario validar(String correo, String contrasenia) throws Exception {
        try {
            return usuarios.validar(correo, contrasenia);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }
}