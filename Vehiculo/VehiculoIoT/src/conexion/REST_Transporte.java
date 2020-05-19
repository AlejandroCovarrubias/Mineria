/**
 * REST_Transporte.java
 * 
 * Creado el 20/04/2020 a las 09:37PM
 */
package conexion;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import objetos.Transporte;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientProperties;

/**
 * Clase para la comunicación al servidor REST de la base de datos, para 
 * poder registrar un transporte terminado.
 * 
 * @author Equipo Mineria.
 */
public class REST_Transporte {
    
        private REST_Transporte_JerseyClient client;
        private String JWToken = "";

        /**
         * Constructor que inicializa el cliente REST.
         */
        public REST_Transporte() {
            client = new REST_Transporte_JerseyClient();
        }

        public void setJWToken(String JWToken) {
            this.JWToken = JWToken;
        }
        
        /**
         * Registra el transporte del parametro.
         * 
         * @param transporte Transporte.
         */
        public void registrarTransporte(Transporte transporte){
            Response post = client.postTransporte(transporte, JWToken);

            if (post != null) {

                System.out.println(post);
                System.out.println(post.getHeaders());

                switch (post.getStatus()) {
                    case 200:
                        System.out.println("Registrado el transporte!");
                        break;
                    default:
                        System.out.println("Error al registrar transporte!");
                }
            }
        } 
    
    // Metodos default
    static class REST_Transporte_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "https://localhost:8443/TransportesAPI";

        public REST_Transporte_JerseyClient() {
            System.getProperties().put(SSLContextConfigurator.KEY_STORE_FILE, "src/conexion/keystore.jks");
            System.getProperties().put(SSLContextConfigurator.TRUST_STORE_FILE, "src/conexion/keystore.jks");
            System.getProperties().put(SSLContextConfigurator.KEY_STORE_PASSWORD, "mineria");
            System.getProperties().put(SSLContextConfigurator.TRUST_STORE_PASSWORD, "mineria");
            final SSLContextConfigurator defaultConfig = new SSLContextConfigurator();

            defaultConfig.retrieve(System.getProperties());
            // o establece SSLContextConfigurator usando su API.

            SSLEngineConfigurator sslEngineConfigurator
                    = new SSLEngineConfigurator(defaultConfig, true, false, false);

            client = javax.ws.rs.client.ClientBuilder
                    .newClient()
                    .property(ClientProperties.SSL_ENGINE_CONFIGURATOR, sslEngineConfigurator);

            webTarget = client.target(BASE_URI).path("transportesAPI").path("transportes");
        }
        
        public Response postTransporte(Object requestEntity, String JWToken) throws ClientErrorException {
            Transporte tempEntity = (Transporte) requestEntity;
            WebTarget newTarget = webTarget
                    .queryParam("matriculavehiculo", tempEntity.getVehiculo().getMatricula())
                    .queryParam("nombreentrega", tempEntity.getNombreDeQuienEntrega())
                    .queryParam("material", tempEntity.getMaterial().getNombre())
                    .queryParam("cantidad", tempEntity.getCantidad())
                    .queryParam("medida", tempEntity.getMedida());

            return newTarget.request()
                    .header("auth", JWToken)
                    .post(Entity.entity(tempEntity, MediaType.APPLICATION_JSON));
        }

        public void close() {
            client.close();
        }
    }   
}