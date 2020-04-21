/**
 * REST_Transportes.java
 * 
 * Creado el 20/04/2020 a las 08:51PM
 */
package recursos;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import objetos.Transporte;

/**
 * REST Web Service
 *
 * @author Equipo Mineria.
 */
@Path("transporte")
public class REST_Transporte {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of REST_Transporte
     */
    public REST_Transporte() {
    }

    /**
     * Retrieves representation of an instance of recursos.REST_Transporte
     * @return an instance of objetos.Transporte
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Transporte getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of REST_Transporte
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putTransporte(Transporte content) {
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postTransporte(Transporte content){
        System.out.println("Me llego un transporte: "+content);
        
        return Response.ok().build();
    }
}
