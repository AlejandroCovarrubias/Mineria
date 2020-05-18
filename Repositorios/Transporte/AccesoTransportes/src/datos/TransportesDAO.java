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
import objetos.Material;
import objetos.Transporte;
import objetos.Vehiculo;

/**
 *
 * @author Alejandro Galindo
 */
class TransportesDAO extends DAOREST<Transporte>{

    @Override
    void crear(Transporte entidad) throws Exception {
        String sql =
                "INSERT INTO transportes_api.transportes "
                + "(matriculavehiculo,nombreentrega,material,cantidad,medida) "
                + "VALUES (\""
                + entidad.getVehiculo().getMatricula()
                +"\",\""
                + entidad.getNombreDeQuienEntrega()
                +"\",\""
                + entidad.getMaterial().getNombre()
                +"\","
                + entidad.getCantidad()
                +",\""
                + entidad.getMedida()
                + "\");";
        
        Statement stmt;
        System.out.println(sql);
        
        try{
            stmt = Conexion.getInstance().createStatement();
            stmt.executeUpdate(sql);
        }catch(SQLException ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    List<Transporte> obtenerTodos() throws Exception {
        String query = "SELECT * FROM transportes";
        List<Transporte> listaTransportes = new ArrayList<>();
        
        Statement stmt;
        ResultSet rs;
        
        try{
            stmt = Conexion.getInstance().createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                listaTransportes.add(transformar(rs));
            }
        }catch(SQLException ex){
            throw new Exception(ex.getMessage());
        }
        return listaTransportes;
    }

    @Override
    Transporte transformar(ResultSet rs) {
        Transporte transporte = null;
        
        try{
            transporte = new Transporte(
                    new Vehiculo(rs.getNString("matriculavehiculo"), rs.getNString("nombreentrega"), "Comun", "Comun", 0, 0), // Deberia hacer referencia cruzada con una tabla de vehiculos pero no tenemos
                    rs.getNString("nombreentrega"), 
                    new Material("material", "N/A"), // Deberia hacer una referencia cruzada con tabla de materiales pero no tenemos
                    rs.getDouble("cantidad"),
                    rs.getNString("medida"));
        }catch(SQLException ex){
            Logger.getLogger(TransportesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transporte;
    }   
}