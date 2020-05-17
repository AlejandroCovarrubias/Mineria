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
import objetos.Congestion;

/**
 *
 * @author Alejandro Galindo
 */
class CongestionesDAO extends DAOREST<Congestion>{

    @Override
    void crear(Congestion entidad) throws Exception {
        String sql =
                "INSERT INTO congestiones_api.congestiones "
                + "(x, y, descripcion, fecha) "
                + "VALUES (\"" + entidad.getX()+
                "\", \"" + entidad.getY() +
                "\", \"" + entidad.getDescripcion() +
                "\", \"" + entidad.getFechaHora()+"\");";
        
        Statement stmt;
        
        try{
            stmt = Conexion.getInstance().createStatement();
            stmt.executeUpdate(sql);
        }catch(SQLException ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    List<Congestion> obtenerTodos() throws Exception {
        String query = "SELECT * FROM congestiones";
        List<Congestion> listaCongestiones = new ArrayList<>();
        
        Statement stmt;
        ResultSet rs;
        
        try{
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                listaCongestiones.add(transformar(rs));
            }
        }catch(SQLException ex){
            throw new Exception(ex.getMessage());
        }
        return listaCongestiones;
    }

    @Override
    Congestion transformar(ResultSet rs) {
        Congestion congestion = null;
        
        try{
            congestion = new Congestion(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getNString(4), rs.getDate(5).toString());
        }catch(SQLException ex){
            Logger.getLogger(CongestionesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return congestion;
    }   
}