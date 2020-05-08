/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recurso;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import java.io.IOException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Alejandro Galindo
 */
@Provider
public class FilterAuthentication implements ContainerRequestFilter {

    public static final String AUTHENTICATION_SERVICE_PATH = "login";

    @Override
    public void filter(ContainerRequestContext requestContext)
            throws IOException, WebApplicationException {
        String method = requestContext.getMethod();
        String path = requestContext.getUriInfo().getPath();

        if (method.equals("POST") && path.contains("login")) {
            //verificar credenciales y generar token
            //No hace nada, lo acepta y el metodo authorizationService lo valida
            //y genera el token

        } else {
            String token = requestContext.getHeaderString(AUTHENTICATION_SERVICE_PATH);

            if (token != null) {
                try {
                    JWTokenHelper.getInstance().verificarToken(token);
                } catch (JWTVerificationException exception) {
                    if (exception instanceof TokenExpiredException) {
                        throw new WebApplicationException(Response
                                .status(Response.Status.UNAUTHORIZED)
                                .header("NO AUTORIZADO", "Token expirado")
                                .build());
                    } else {
                        throw new WebApplicationException(Response
                                .status(Response.Status.UNAUTHORIZED)
                                .header("NO AUTORIZADO", "Token invalido")
                                .build());
                    }
                }
            } else {
                throw new WebApplicationException(Response
                        .status(Response.Status.UNAUTHORIZED)
                        .header("NO AUTORIZADO", "Token inexistente")
                        .build());
            }
        }
    }
}