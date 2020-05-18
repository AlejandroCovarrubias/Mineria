/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recurso;

import datos.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import objetos.Congestion;

/**
 * REST Web Service
 *
 * @author Alejandro Galindo
 */
@Path("/congestiones")
public class REST_Congestiones {

    @Context
    private UriInfo context;

    private IDatos fachada = Datos.getFacade();

    /**
     * Creates a new instance of REST_Usuarios
     */
    public REST_Congestiones() {
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("auth")
    public Response authorizationService(
            @QueryParam("identificador") String identificador,
            @QueryParam("password") String password) {

        System.out.println(identificador);
        System.out.println(password);

        if (identificador.isEmpty()) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .header("NO AUTORIZADO", "Identificador Incorrecto")
                    .build();
        }

        if (password.isEmpty()) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .header("NO AUTORIZADO", "Contrasenia Incorrecta")
                    .build();
        }

        String st = null;
        try {
            File file = new File("C:\\Users\\Home\\Documents\\Mineria\\Repositorios\\Congestiones\\CongestionesAPI\\src\\java\\recurso\\userslog.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            st = br.readLine();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(REST_Congestiones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(REST_Congestiones.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (st != null) {
            String[] autorizados = st.split(";");

            for (String auth : autorizados) {
                String[] idpass = auth.split(",");
                
                if (idpass[0].equals(identificador) && idpass[1].equals(password)) {

                    String token = JWTokenHelper.getInstance()
                            .crearToken(identificador, password);

                    return Response
                            .status(200)
                            .header("AUTORIZADO", "Haz sido autenticado")
                            .entity(token)
                            .build();
                }
            }
        }

        return Response
                .status(Response.Status.UNAUTHORIZED)
                .header("NO AUTORIZADO", "Usuario o contrasenia incorrecta")
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarCongestion(
            @QueryParam("posx") double x,
            @QueryParam("posy") double y,
            @QueryParam("descripcion") String descripcion,
            @QueryParam("fecha") String stringfecha) {

        try {
            Congestion congestion = new Congestion(x, y, descripcion, stringfecha);
            fachada.crearCongestion(congestion);
        }  catch (Exception ex) {
            //No se si seria la response adecuada
            System.out.println(ex.getMessage());
            return Response
                    .status(Response.Status.CONFLICT)
                    .header("Problemas en la BD", ex.getMessage())
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .header("OK", "Usuario creado")
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCongestiones() {
        List<Congestion> congestiones = new ArrayList<>();

        try {
            congestiones = fachada.obtenerCongestiones();
        } catch (Exception ex) {
            //No se si seria la response adecuada
            return Response
                    .status(Response.Status.CONFLICT)
                    .header("Problemas en la BD", ex.getMessage())
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .header("OK", "Usuarios obtenidos")
                .entity(congestiones)
                .build();
    }
}