package uce.edu.web.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.ClaimValue;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.IHijoService;
import uce.edu.web.api.service.mapper.EstudianteMapper;
import uce.edu.web.api.service.to.EstudianteTo;

@Path("/estudiantes") //
public class Estudiantecontroller {// se lo suele llamar el servicion - web service

    @Inject
    JsonWebToken jwt;
    @Inject
    @Claim("sub")
    ClaimValue<String> subject;
    @Inject
    private IEstudianteService estudianteService;
    @Inject
    private IHijoService hijoService;

    @GET
    @Path("/{id}") // path param se usa para consultar un recurso mediante su id
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) { // se lo suele llamar
                                                                                            // capacidades
        EstudianteTo estu = EstudianteMapper.toTo(this.estudianteService.buscarPorId(id));
        estu.buildURI(uriInfo);
        return Response.status(Response.Status.OK).entity(estu).build();
    }
    // mediaType es el formato donde ser reciba el body

    // ?genero=F&provincia=pichincha soap-> XML,Rest ful ->json
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "conusltar estudiantes", description = "Este endpoint permiete registrar un nuevo estudiatne ")
    public Response consultarTodos(@QueryParam("genero") String genero, @Context UriInfo uriInfo) {

        List<Estudiante> estudiantes = this.estudianteService.buscarTodos(genero);

        List<EstudianteTo> estudiantesTo = estudiantes.stream()
                .map(EstudianteMapper::toTo)
                .collect(Collectors.toList());

        estudiantesTo.forEach(estu -> estu.buildURI(uriInfo));

        return Response.status(Response.Status.OK).entity(estudiantesTo).build();
    }
    // mensajes informativos desde codigos desde el 100
    // mensajes

    @POST // Indica que este método maneja solicitudes POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar Estudiante", description = "Nos permiete guardar en la .....")
    public Response guardar(EstudianteTo estudianteTo, @Context UriInfo uriInfo) {
        Estudiante estudiante = EstudianteMapper.toEntity(estudianteTo);
        this.estudianteService.guardar(estudiante);

        EstudianteTo respuestaTo = EstudianteMapper.toTo(estudiante);
        respuestaTo.buildURI(uriInfo);

        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(estudiante.getId())).build();

        return Response.created(location).entity(respuestaTo).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar estudiante", description = "Nos permiete actualizar un estuidante especifico  mediante su id")
    public Response actualizar(@RequestBody EstudianteTo estudianteTo, @PathParam("id") Integer id,
            @Context UriInfo uriInfo) {
        Estudiante estudiante = EstudianteMapper.toEntity(estudianteTo);
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);

        EstudianteTo respuestaTo = EstudianteMapper.toTo(estudiante);
        respuestaTo.buildURI(uriInfo);

        return Response.status(Response.Status.OK).entity(respuestaTo).build();
    }

    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar parcial de un estudiante", description = "Nos actualizar un estudiante especifico mediante su id")
    public Response actualizarParcial(@RequestBody EstudianteTo estudianteTo,
            @PathParam("id") Integer id, @Context UriInfo uriInfo) {
        Estudiante e = this.estudianteService.buscarPorId(id);

        if (estudianteTo.getNombre() != null) {
            e.setNombre(estudianteTo.getNombre());
        }
        if (estudianteTo.getApellido() != null) {
            e.setApellido(estudianteTo.getApellido());
        }
        if (estudianteTo.getFechaNacimiento() != null) {
            e.setFechaNacimiento(estudianteTo.getFechaNacimiento());
        }
        if (estudianteTo.getGenero() != null) {
            e.setGenero(estudianteTo.getGenero());
        }

        this.estudianteService.actualizarParcialPorId(e);
        EstudianteTo respuestaTo = EstudianteMapper.toTo(e);
        respuestaTo.buildURI(uriInfo);

        return Response.status(Response.Status.OK).entity(respuestaTo).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Borrar Estudiante", description = "Nos permiete eliminar un estudiante especifico mediante un id")
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.estudianteService.borrarPorId(id);
        return Response.noContent().build();
    }

    // htt://....../estudaitnes/1/hijos GET
    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerHijosPorId(@PathParam("id") Integer id) {
        // Para este recurso relacionado, también podrías usar DTOs si lo necesitas
        List<Hijo> hijos = this.hijoService.buscarPorEstudianteId(id);
        return Response.ok(hijos).build();
    }

}
// la api tiene que tener un nombre y esta viene dada por el nombre de nuestro
// proyecto