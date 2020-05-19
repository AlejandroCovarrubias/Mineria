/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recurso;

import datos.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import objetos.Material;
import objetos.Transporte;
import objetos.Vehiculo;

/**
 * REST Web Service
 *
 * @author Alejandro Galindo
 */
@Path("/transportes")
public class REST_Transportes {

    @Context
    private UriInfo context;

    private IDatos fachada = Datos.getFacade();

    /**
     * Creates a new instance of REST_Usuarios
     */
    public REST_Transportes() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearTransporte(
            @QueryParam("matriculavehiculo") String matriculavehiculo,
            @QueryParam("nombreentrega") String nombreentrega,
            @QueryParam("material") String material,
            @QueryParam("cantidad") double cantidad,
            @QueryParam("medida") String medida) {

        Transporte transporte = new Transporte( new Vehiculo(matriculavehiculo, nombreentrega, "Comun", "Comun", 0, 0)
                , nombreentrega,
                new Material(material, "N/A"), 
                cantidad, 
                medida);

        try {
            fachada.crearTransporte(transporte);
        } catch (Exception ex) {
            //No se si seria la response adecuada
            System.out.println(ex.getMessage());
            return Response
                    .status(Response.Status.CONFLICT)
                    .header("Problemas en la BD", ex.getMessage())
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .header("OK", "Transporte registrado")
                .build();
    }

}
