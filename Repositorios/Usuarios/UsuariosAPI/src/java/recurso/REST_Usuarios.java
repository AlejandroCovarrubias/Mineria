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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import usuarios.Usuario;

/**
 * REST Web Service
 *
 * @author Alejandro Galindo
 */
@Path("/usuarios")
public class REST_Usuarios {

    @Context
    private UriInfo context;

    private IDatos fachada = Datos.getFacade();

    /**
     * Creates a new instance of REST_Usuarios
     */
    public REST_Usuarios() {
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response authorizationService(
            @QueryParam("correo") String correoElectronico,
            @QueryParam("contrasenia") String contrasenia) {

        System.out.println(correoElectronico);
        System.out.println(contrasenia);

        if (correoElectronico.isEmpty()) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .header("NO AUTORIZADO", "Correo Electronico vacio")
                    .build();
        }

        if (contrasenia.isEmpty()) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .header("NO AUTORIZADO", "Contrasenia vacia")
                    .build();
        }

        try {
            Usuario validar = fachada.validar(correoElectronico, contrasenia);
            System.out.println(validar);

            if (validar != null) {
                String token = JWTokenHelper.getInstance()
                        .crearToken(correoElectronico, contrasenia);

                return Response
                        .status(200)
                        .header("AUTORIZADO", "Haz sido autenticado de manera correcta")
                        .entity(token)
                        .header("tipo", validar.getTipo())
                        .build();
            }else{
                return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .header("NO AUTORIZADO", "Usuario no registrado")
                    .build();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .header("NO AUTORIZADO", "Usuario no registrado")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearUsuario(
            @QueryParam("tipo") String tipo,
            @QueryParam("nombre") String nombre,
            @QueryParam("apellidos") String apellidos,
            @QueryParam("edad") int edad,
            @QueryParam("telefono") String telefono,
            @QueryParam("correo") String correoElectronico,
            @QueryParam("contrasenia") String contrasenia) {

        Usuario usuario = new Usuario(
                tipo, nombre, apellidos, edad,
                telefono, correoElectronico, contrasenia);

        try {
            fachada.crearUsuario(usuario);
        } catch (Exception ex) {
            //No se si seria la response adecuada
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

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarUsuario(
            @QueryParam("idusuario") int idusuario,
            @QueryParam("tipo") String tipo,
            @QueryParam("nombre") String nombre,
            @QueryParam("apellidos") String apellidos,
            @QueryParam("edad") int edad,
            @QueryParam("telefono") String telefono) {

        Usuario usuario = new Usuario(
                idusuario, tipo, nombre, apellidos, edad,
                telefono, "", "");

        try {
            fachada.editarUsuario(usuario);
        } catch (Exception ex) {
            //No se si seria la response adecuada
            return Response
                    .status(Response.Status.CONFLICT)
                    .header("Problemas en la BD", ex.getMessage())
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .header("OK", "Usuario editado")
                .build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarUsuario(@QueryParam("idusuario") int idusuario) {
        try {
            fachada.eliminarUsuario(idusuario);
        } catch (Exception ex) {
            //No se si seria la response adecuada
            return Response
                    .status(Response.Status.CONFLICT)
                    .header("Problemas en la BD", ex.getMessage())
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .header("OK", "Usuario eliminado")
                .build();
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerUsuario(@QueryParam("idusuario") int idusuario) {
        Usuario usuario = null;

        try {
            usuario = fachada.obtenerUsuario(idusuario);
        } catch (Exception ex) {
            //No se si seria la response adecuada
            return Response
                    .status(Response.Status.CONFLICT)
                    .header("Problemas en la BD", ex.getMessage())
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .header("OK", "Usuario obtenido")
                .entity(usuario)
                .build();
    }
}
