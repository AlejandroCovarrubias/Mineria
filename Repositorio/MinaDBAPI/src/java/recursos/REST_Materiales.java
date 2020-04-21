/**
 * REST_Materiales.java
 * 
 * Creado el 20/04/2020 a las 08:51PM
 */
package recursos;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import objetos.Material;

/**
 * REST Web Service
 *
 * @author Equipo Mineria.
 */
@Path("material")
public class REST_Materiales {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of REST_Materiales
     */
    public REST_Materiales() {
    }

    /**
     * Retrieves representation of an instance of recursos.REST_Materiales
     * @return an instance of objetos.Material
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Material getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of REST_Materiales
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Material content) {
    }
}
