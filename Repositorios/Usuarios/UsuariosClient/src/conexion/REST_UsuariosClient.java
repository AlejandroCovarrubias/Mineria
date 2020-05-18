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
import usuarios.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientProperties;

/**
 *
 * @author Alejandro Galindo
 */
public class REST_UsuariosClient {

    private REST_Usuarios_JerseyClient client;
    private String JWToken = "";

    public REST_UsuariosClient() {
        client = new REST_Usuarios_JerseyClient();
    }

    public boolean autenticarUsuario(String correo, String pass) {
        try {
            //hash la pass
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(pass.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();

            String hashPass = String.format("%064x", new BigInteger(1, digest));
            System.out.println(hashPass);

            Response postAuth = client.postAuth(correo, hashPass, "login");

            if (postAuth != null) {
                
                switch (postAuth.getStatus()) {
                    case 200:
                        // Revisa que sea tipo general
                        String tipo = postAuth.getHeaderString("tipo");
                        if(!tipo.equals("GERENCIAL"))
                            return false;
                        JWToken = postAuth.readEntity(String.class);
                        System.out.println(JWToken);
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
            Logger.getLogger(REST_UsuariosClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean crearUsuario(Usuario usuario) {
        //cusuario

        try {
            //hash la pass
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(usuario.getContrasenia().getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();

            usuario.setContrasenia(String.format("%064x", new BigInteger(1, digest)));

            System.out.println(usuario.getContrasenia());

            Response post = client.postUsuario(usuario, JWToken);

            if (post != null) {

                System.out.println(post);
                System.out.println(post.getHeaders());

                switch (post.getStatus()) {
                    case 200:
                        return true;
                    case 401:
                        return false;
                    default:
                        return false;
                }
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(REST_UsuariosClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean editarUsuario(Usuario usuario) {
        Response put = client.putUsuario(usuario, JWToken);

        if (put != null) {

            System.out.println(put);
            System.out.println(put.getHeaders());

            switch (put.getStatus()) {
                case 200:
                    return true;
                case 401:
                    return false;
                default:
                    return false;
            }
        }
        
        return false;
    }

    public boolean eliminarUsuario(Usuario usuario) {
        Response del = client.deleteUsuario(usuario, JWToken);

        if (del != null) {

            System.out.println(del);
            System.out.println(del.getHeaders());

            switch (del.getStatus()) {
                case 200:
                    return true;
                case 401:
                    return false;
                default:
                    return false;
            }
        }
        
        return false;
    }

    public Usuario obtenerUsuario(String idUsuario) {
        Usuario json = client.getJson(Usuario.class, idUsuario, JWToken);
        System.out.println(json);
        return json;
    }

    public List<Usuario> obtenerUsuarios() {
        return null;
    }

    static class REST_Usuarios_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "https://localhost:8443/UsuariosAPI";

        public REST_Usuarios_JerseyClient() {
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

            webTarget = client.target(BASE_URI).path("usuariosAPI").path("usuarios");
        }

        public Response postAuth(String mail, String pass, String specificPath) throws ClientErrorException {
            WebTarget newTarget = webTarget.path(specificPath)
                    .queryParam("correo", mail)
                    .queryParam("contrasenia", pass);

            return newTarget.request().post(null, Response.class);
        }
        
        public Response postUsuario(Object requestEntity, String JWToken) throws ClientErrorException {
            Usuario tempEntity = (Usuario) requestEntity;
            WebTarget newTarget = webTarget
                    .queryParam("tipo", tempEntity.getTipo())
                    .queryParam("nombre", tempEntity.getNombre())
                    .queryParam("apellidos", tempEntity.getApellidos())
                    .queryParam("edad", tempEntity.getEdad())
                    .queryParam("telefono", tempEntity.getTelefono())
                    .queryParam("correo", tempEntity.getCorreoElectronico())
                    .queryParam("contrasenia", tempEntity.getContrasenia());

            return newTarget.request()
                    .header("login", JWToken)
                    .post(Entity.entity(tempEntity, MediaType.APPLICATION_JSON));
        }

        public Response putUsuario(Object requestEntity, String JWToken) throws ClientErrorException {
            Usuario tempEntity = (Usuario) requestEntity;
            WebTarget newTarget;
            
                newTarget = webTarget
                    .queryParam("idusuario", tempEntity.getIDUsuario())
                    .queryParam("tipo", tempEntity.getTipo())
                    .queryParam("nombre", tempEntity.getNombre())
                    .queryParam("apellidos", tempEntity.getApellidos())
                    .queryParam("edad", tempEntity.getEdad())
                    .queryParam("telefono", tempEntity.getTelefono());
                
                return newTarget.request()
                    .header("login", JWToken)
                    .put(Entity.entity(tempEntity, MediaType.APPLICATION_JSON));
        }
        
        public Response deleteUsuario(Object requestEntity, String JWToken) throws ClientErrorException {
            Usuario tempEntity = (Usuario) requestEntity;
            WebTarget newTarget;
            
            newTarget = webTarget
                    .queryParam("idusuario", tempEntity.getIDUsuario());
                
                return newTarget.request()
                    .header("login", JWToken)
                    .delete();
        }

        public <T> T getJson(Class<T> responseType, String id, String JWToken) throws ClientErrorException {
            WebTarget newTarget = webTarget
                    .queryParam("idusuario", id);

            return newTarget.request()
                    .header("login", JWToken)
                    .get(responseType);
        }

        public void close() {
            client.close();
        }
    }

}
