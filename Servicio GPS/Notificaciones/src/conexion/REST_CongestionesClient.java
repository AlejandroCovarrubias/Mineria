/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngineResult;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import objetos.Congestion;

import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientProperties;

/**
 *
 * @author Alejandro Galindo
 */
public class REST_CongestionesClient {

    private REST_Congestiones_JerseyClient client;
    private String JWToken = "";

    public REST_CongestionesClient() {
        client = new REST_Congestiones_JerseyClient();
    }

    public boolean autenticar(String identificador, String pass) {
        try {
            //hash la pass
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(pass.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();

            String hashPass = String.format("%064x", new BigInteger(1, digest));

            Response postAuth = client.postAuth(identificador, hashPass, "auth");

            if (postAuth != null) {

                switch (postAuth.getStatus()) {
                    case 200:
                        JWToken = postAuth.readEntity(String.class);
                        System.out.println("Token Congestiones " + JWToken);
                        return true;
                    case 401:
                        return false;
                    default:
                        return false;
                }

            } else {
                return false;
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(REST_CongestionesClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean agregarCongestion(Congestion congestion) {

        Response post = client.postCongestion(congestion, JWToken);

        if (post != null) {

            System.out.println(post.getStatus());
            System.out.println(post.getHeaders());
            

            switch (post.getStatus()) {
                case 200:
                    System.out.println("Status: OK");
                    return true;
                case 401:
                    System.out.println("Status: Unhautorized");
                    return false;
                case 409:
                    System.out.println("Status: Conflict");
                    return false;
                default:
                    System.out.println("Status: Other");
                    return false;
            }
        }

        return false;
    }

    public List<Congestion> obtenerCongestiones() {
        return null;
    }

    static class REST_Congestiones_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "https://localhost:8443/CongestionesAPI";

        public REST_Congestiones_JerseyClient() {
            System.getProperties().put(SSLContextConfigurator.KEY_STORE_FILE, "C:/certs/mineria/keystore.jks");
            System.getProperties().put(SSLContextConfigurator.TRUST_STORE_FILE, "C:/certs/mineria/keystore.jks");
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

            webTarget = client.target(BASE_URI).path("congestionesAPI").path("congestiones");
        }

        public Response postAuth(String identificador, String password, String specificPath) throws ClientErrorException {
            WebTarget newTarget = webTarget.path(specificPath)
                    .queryParam("identificador", identificador)
                    .queryParam("password", password);

            return newTarget.request().post(null, Response.class);
        }

        public Response postCongestion(Object requestEntity, String JWToken) throws ClientErrorException {
            Congestion tempEntity = (Congestion) requestEntity;
            WebTarget newTarget = webTarget
                    .queryParam("posx", tempEntity.getX())
                    .queryParam("posy", tempEntity.getY())
                    .queryParam("descripcion", tempEntity.getDescripcion())
                    .queryParam("fecha", tempEntity.getFechaHora());

            return newTarget.request()
                    .header("auth", JWToken)
                    .post(Entity.entity(tempEntity, MediaType.APPLICATION_JSON));
        }

        public <T> T getJson(Class<T> responseType, String id, String JWToken) throws ClientErrorException {
            WebTarget newTarget = webTarget
                    .queryParam("idusuario", id);

            return newTarget.request()
                    .header("auth", JWToken)
                    .get(responseType);
        }

        public void close() {
            client.close();
        }
    }
}
