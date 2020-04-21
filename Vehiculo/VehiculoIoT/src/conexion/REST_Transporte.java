/**
 * REST_Transporte.java
 * 
 * Creado el 20/04/2020 a las 09:37PM
 */
package conexion;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import objetos.Transporte;

/**
 * Clase para la comunicación al servidor REST de la base de datos, para 
 * poder registrar un transporte terminado.
 * 
 * @author Equipo Mineria.
 */
public class REST_Transporte {
    
        private REST_Transporte_JerseyClient client;

        /**
         * Constructor que inicializa el cliente REST.
         */
        public REST_Transporte() {
            client = new REST_Transporte_JerseyClient();
        }
        
        /**
         * Registra el transporte del parametro.
         * 
         * @param transporte Transporte.
         */
        public void registrarTransporte(Transporte transporte){
            client.postTransporte(transporte);
            System.out.println("Registrado Transporte en REST!");
        } 
    
    // Metodos default
    static class REST_Transporte_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/MinaDBAPI/webresources";

        public REST_Transporte_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("transporte");
        }

        public Response postTransporte(Object requestEntity) throws ClientErrorException {
            return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
        }

        public void putTransporte(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

        public <T> T getJson(Class<T> responseType) throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void close() {
            client.close();
        }
    }   
}
    

    
    
    

