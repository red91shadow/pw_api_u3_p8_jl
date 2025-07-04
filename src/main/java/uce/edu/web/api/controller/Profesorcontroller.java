package uce.edu.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.repository.modelo.Profesor;

import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
public class Profesorcontroller {

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar por ID", description = "Nos permiete permite conultar un elemento de la tabla estudiante mediatne un id")
    public Response consultaPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {

        ProfesorTo prof = this.profesorService.buscarPorId(id,uriInfo) ;
        return Response.status(Response.Status.OK).entity(prof).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "consultar profesores", description = "Nos permite ver todos los elementos de la tabla profesores")
    public Response consultarTodos(@QueryParam("genero") String genero,
            @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return Response.status(Response.Status.OK).entity(this.profesorService.buscarTodos(genero)).build();

    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar Profesor", description = "Nos permiete guardar en la .....")
    public Response guardar(@RequestBody Profesor profesor) {
        this.profesorService.guardar(profesor);
        return Response.status(Response.Status.OK).build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar profesor", description = "Nos permiete actualizar un profesor especifico  mediante su id")
    public Response actualizar(@RequestBody Profesor profesor, @PathParam("id") Integer id) {

        profesor.setId(id);
        this.profesorService.actualizarParcialPorId(profesor);
        return Response.status(Response.Status.OK).build();

    }

    // @PATCH
    // @Path("/{id}")
    // @Consumes(MediaType.APPLICATION_JSON)
    // @Operation(summary = "Actualizar parcial de un profesor", description = "Nos actualizar un profesor especifico mediante su id")
    // public Response actualizarParcial(@RequestBody Profesor profesor, @PathParam("id") Integer id) {

    //     profesor.setId(id);
    //     Profesor e = this.profesorService.buscarPorId(id);
    //     if (profesor.getCargaHoraria() != null) {
    //         e.setCargaHoraria(profesor.getCargaHoraria());

    //     }

    //     if (profesor.getDepartamento() != null) {
    //         e.setDepartamento(profesor.getDepartamento());

    //     }

    //     if (profesor.getLastName() != null) {
    //         e.setLastName(profesor.getLastName());

    //     }

    //     if (profesor.getName() != null) {
    //         e.setName(profesor.getName());

    //     }

    //     this.profesorService.actualizarParcialPorId(profesor);
    //     return Response.status(Response.Status.OK).build();

    // }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Borrar profesor", description = "Nos permiete eliminar un profesor especifico mediante un id")
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.profesorService.borrarPorId(id);
        return Response.status(Response.Status.OK).build();

    }

      @GET
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id) {

        Hijo h1 = new Hijo();
        h1.setNombre("Nathan");

        Hijo h2 = new Hijo();
        h2.setNombre("Drake");

        List<Hijo> hijos = new ArrayList<>();
        hijos.add(h1);
        hijos.add(h2);

        return hijos;
    }

}
