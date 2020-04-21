/**
 * REST_Congestion.java
 * 
 * Creado el 20/04/2020 a las 08:52PM
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
import objetos.Congestion;

/**
 * REST Web Service
 *
 * @author Equipo Mineria.
 */
@Path("generic")
public class REST_Congestion {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of REST_Congestion
     */
    public REST_Congestion() {
    }

    /**
     * Retrieves representation of an instance of recursos.REST_Congestion
     * @return an instance of objetos.Congestion
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Congestion getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of REST_Congestion
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Congestion content) {
        System.out.println("Me llego una congestion: "+content);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postTransporte(Congestion content){
        System.out.println("Me llego una congestion: "+content);
        
        return Response.ok().build();
    }
}
