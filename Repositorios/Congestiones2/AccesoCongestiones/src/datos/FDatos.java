/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.List;
import objetos.Congestion;

/**
 *
 * @author Alejandro Galindo
 */
class FDatos implements IDatos {
    
    CongestionesDAO congestiones = new CongestionesDAO();

    @Override
    public void crearCongestion(Congestion congestion) throws Exception {
        congestiones.crear(congestion);
    }

    @Override
    public List<Congestion> obtenerCongestiones() throws Exception {
        return congestiones.obtenerTodos();
    }
}