package uce.edu.web.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import uce.edu.web.api.service.mapper.ProfesorMapper; // Asumiendo que esta clase existe
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
        ProfesorTo prof = ProfesorMapper.toTo(this.profesorService.buscarPorId(id));
        prof.buildURI(uriInfo);
        return Response.status(Response.Status.OK).entity(prof).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "consultar profesores", description = "Nos permite ver todos los elementos de la tabla profesores")
    public Response consultarTodos(@QueryParam("genero") String genero, @Context UriInfo uriInfo) {
        List<Profesor> profesores = this.profesorService.buscarTodos(genero);
        List<ProfesorTo> profesoresTo = profesores.stream()
                .map(ProfesorMapper::toTo)
                .collect(Collectors.toList());
        
        profesoresTo.forEach(prof -> prof.buildURI(uriInfo));

        return Response.status(Response.Status.OK).entity(profesoresTo).build();
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar Profesor", description = "Nos permiete guardar en la .....")
    public Response guardar(@RequestBody ProfesorTo profesorTo, @Context UriInfo uriInfo) {
        Profesor profesor = ProfesorMapper.toEntity(profesorTo);
        this.profesorService.guardar(profesor);

        ProfesorTo respuestaTo = ProfesorMapper.toTo(profesor);
        respuestaTo.buildURI(uriInfo);

        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(profesor.getId())).build();

        return Response.created(location).entity(respuestaTo).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar profesor", description = "Nos permiete actualizar un profesor especifico  mediante su id")
    public Response actualizar(@RequestBody ProfesorTo profesorTo, @PathParam("id") Integer id, @Context UriInfo uriInfo) {
        Profesor profesor = ProfesorMapper.toEntity(profesorTo);
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);

        ProfesorTo respuestaTo = ProfesorMapper.toTo(profesor);
        respuestaTo.buildURI(uriInfo);

        return Response.status(Response.Status.OK).entity(respuestaTo).build();
    }

    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar parcial de un profesor", description = "Nos actualizar un profesor especifico mediante su id")
    public Response actualizarParcial(@RequestBody ProfesorTo profesorTo, @PathParam("id") Integer id, @Context UriInfo uriInfo) {
        Profesor e = this.profesorService.buscarPorId(id);

        if (profesorTo.getCargaHoraria() != null) {
            e.setCargaHoraria(profesorTo.getCargaHoraria());
        }
        if (profesorTo.getDepartamento() != null) {
            e.setDepartamento(profesorTo.getDepartamento());
        }
        if (profesorTo.getLastName() != null) {
            e.setLastName(profesorTo.getLastName());
        }
        if (profesorTo.getName() != null) {
            e.setName(profesorTo.getName());
        }

        this.profesorService.actualizarParcialPorId(e);
        ProfesorTo respuestaTo = ProfesorMapper.toTo(e);
        respuestaTo.buildURI(uriInfo);

        return Response.status(Response.Status.OK).entity(respuestaTo).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Borrar profesor", description = "Nos permiete eliminar un profesor especifico mediante un id")
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.profesorService.borrarPorId(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerHijosPorId(@PathParam("id") Integer id) {
        Hijo h1 = new Hijo();
        h1.setNombre("Nathan");
        Hijo h2 = new Hijo();
        h2.setNombre("Drake");
        List<Hijo> hijos = List.of(h1, h2);
        
        return Response.ok(hijos).build();
    }
}