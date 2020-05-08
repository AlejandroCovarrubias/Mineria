/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Alejandro Galindo
 */
abstract class DAOREST<T> {
    abstract void crear(T entidad) throws Exception;
    abstract void editar(T entidad) throws Exception;
    abstract void eliminar(int identificador) throws Exception;
    abstract T obtener(int identificador) throws Exception;
    abstract List<T> obtenerTodos() throws Exception;
    abstract T transformar(ResultSet rs) throws Exception; 
}
