package uce.edu.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.hibernate.annotations.Type;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;

import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.to.EstudianteTo;

@Path("/estudiantes") //
public class Estudiantecontroller {// se lo suele llamar el servicion - web service

    @Inject
    private IEstudianteService estudianteService;

    @GET
    @Path("/{id}") // path param se usa para consultar un recurso mediante su id
    @Produces(MediaType.APPLICATION_JSON)

    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) { // se lo suele llamar capacidades

        EstudianteTo estu = this.estudianteService.buscarPorId(id, uriInfo);

        return Response.status(227).entity(estu).build();

    }
    // mediaType es el formato donde ser reciba el body

    // ?genero=F&provincia=pichincha soap-> XML,Rest ful ->json
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "conusltar estudiantes", description = "Este endpoint permiete registrar un nuevo estudiatne ")
    public Response consultarTodos(@QueryParam("genero") String genero,
            @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return Response.status(Response.Status.OK).entity(this.estudianteService.buscarTodos(genero)).build();

    }
    // mensajes informativos desde codigos desde el 100
    // mensajes

    @POST // Indica que este método maneja solicitudes POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar Estudiante", description = "Nos permiete guardar en la .....")
    public Response guardar(Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);
        return Response.status(Response.Status.OK).build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar estudiante", description = "Nos permiete actualizar un estuidante especifico  mediante su id")
    public Response actualizar(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
        return Response.status(Response.Status.OK).build();
    }

    // @PATCH
    // @Path("/{id}")
    // @Consumes(MediaType.APPLICATION_JSON)
    // @Operation(summary = "Actualizar parcial de un estudiante", description = "Nos actualizar un estudiante especifico mediante su id")

    // public Response actualizarParcial(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
    //     estudiante.setId(id);
    //     Estudiante e = this.estudianteService.buscarPorId(id);
    //     if (estudiante.getApellido() != null) {
    //         e.setApellido(estudiante.getApellido());
    //     }
    //     if (estudiante.getFechaNacimiento() != null) {
    //         e.setFechaNacimiento(estudiante.getFechaNacimiento());
    //     }

    //     if (estudiante.getNombre() != null) {
    //         e.setNombre(estudiante.getNombre());
    //     }
    //     this.estudianteService.actualizarParcialPorId(e);
    //     return Response.status(Response.Status.OK).build();
    // }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Borrar Estudiante", description = "Nos permiete eliminar un estudiante especifico mediante un id")

    public Response borrarPorId(@PathParam("id") Integer id) {
        this.estudianteService.borrarPorId(id);
        return Response.status(Response.Status.OK).build();

    }

    // htt://....../estudaitnes/1/hijos GET
    @GET
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id) {

        Hijo h1 = new Hijo();
        h1.setNombre("Dirac");

        Hijo h2 = new Hijo();
        h2.setNombre("Paul");

        List<Hijo> hijos = new ArrayList<>();
        hijos.add(h1);
        hijos.add(h2);

        return hijos;
    }

}
// la api tiene que tener un nombre y esta viene dada por el nombre de nuestro
// proyecto
