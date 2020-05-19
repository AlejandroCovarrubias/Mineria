/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import usuarios.Usuario;

/**
 *
 * @author Alejandro Galindo
 */
class UsuarioDAO extends DAOREST<Usuario>{

    @Override
    void crear(Usuario entidad) throws Exception {
        String sql = 
                "INSERT INTO usuarios_api.usuarios "
                + "(tipo, nombre, apellidos, edad, telefono, correoElectronico, contrasenia)"
                + "VALUES (\"" + entidad.getTipo() +
                "\", \"" + entidad.getNombre() +
                "\", \"" + entidad.getApellidos() +
                "\", " + entidad.getEdad() +
                ", \"" + entidad.getTelefono() +
                "\", \"" + entidad.getCorreoElectronico() +
                "\", \"" + entidad.getContrasenia()+"\");";
        
        Statement stmt;
        
        try{
            stmt = Conexion.getInstance().createStatement();
            stmt.executeUpdate(sql);
        }catch(SQLException ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    void editar(Usuario entidad) throws Exception {
        String sql = "UPDATE usuarios_api.usuarios SET "
                + "tipo='"+ entidad.getTipo() +"', "
                + "nombre='"+ entidad.getNombre() +"', "
                + "apellidos='"+ entidad.getApellidos() +"', "
                + "edad='"+ entidad.getEdad() +"', "
                + "telefono='"+ entidad.getTelefono() +"' "
                + "WHERE idusuario = " + entidad.getIDUsuario();
        
        Statement stmt;
        
        try {
            stmt = Conexion.getInstance().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    @Override
    void eliminar(int identificador)  throws Exception{
        String sql = "DELETE FROM usuarios WHERE idusuario = " + identificador;
        
        Statement stmt;
        
        try{
            stmt = Conexion.getInstance().createStatement();
            stmt.executeUpdate(sql);
        }catch(SQLException ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    Usuario validar(String correo, String contrasenia) throws Exception{
       String sql = "SELECT * FROM usuarios_api.usuarios WHERE correoElectronico = '" + correo
               + "' AND contrasenia = '" + contrasenia
               + "'";
    
        Statement stmt;
        ResultSet rs;
        
        try{
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                return transformar(rs);
            }
        }catch(SQLException ex){
            throw new Exception(ex.getMessage());
        }
        return null; 
    }

    @Override
    Usuario obtener(int identificador) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE idusuario = "+ identificador;
    
        Statement stmt;
        ResultSet rs;
        
        try{
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                return transformar(rs);
            }
        }catch(SQLException ex){
            throw new Exception(ex.getMessage());
        }
        return null;
    }

    @Override
    List<Usuario> obtenerTodos() throws Exception {
        String query = "SELECT * FROM usuarios";
        List<Usuario> listaUsuarios = new ArrayList<>();
        
        Statement stmt;
        ResultSet rs;
        
        try{
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                listaUsuarios.add(transformar(rs));
            }
        }catch(SQLException ex){
            throw new Exception(ex.getMessage());
        }
        return listaUsuarios;
    }

    @Override
    Usuario transformar(ResultSet rs) {
        Usuario usuario = null;
        
        try{
            usuario = new Usuario(
                    rs.getInt("idusuario"), 
                    rs.getNString("tipo"), 
                    rs.getNString("nombre"),
                    rs.getNString("apellidos"),
                    rs.getInt("edad"),  
                    rs.getNString("telefono"), 
                    rs.getNString("correoElectronico"),
                    rs.getNString("contrasenia"));
        }catch(SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }   
}