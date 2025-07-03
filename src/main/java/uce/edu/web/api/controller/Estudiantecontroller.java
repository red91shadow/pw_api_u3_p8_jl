package uce.edu.web.api.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.hibernate.annotations.Type;

import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.IEstudianteService;

@Path("/estudiantes") //
public class Estudiantecontroller {// se lo suele llamar el servicion - web service

    @Inject
    private IEstudianteService estudianteService;

    @GET
    @Path("/{id}") // path param se usa para consultar un recurso mediante su id
    @Produces(MediaType.APPLICATION_XML)

    public Response consultarPorId(@PathParam("id") Integer id) { // se lo suele llamar capacidades

        return Response.status(227).entity(this.estudianteService.buscarPorId(id)).build();

    }
    // mediaType es el formato donde ser reciba el body

    // ?genero=F&provincia=pichincha
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
    @Consumes(MediaType.APPLICATION_XML)
    @Operation(summary = "Guardar Estudiante", description = "Nos permiete guardar en la .....")
    public void guardar(Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);

    }

    @PUT
    @Path("/{id}")
    public void actualizar(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        Estudiante e = this.estudianteService.buscarPorId(id);
        if (estudiante.getApellido() != null) {
            e.setApellido(estudiante.getApellido());
        }
        if (estudiante.getFechaNacimiento() != null) {
            e.setFechaNacimiento(estudiante.getFechaNacimiento());
        }

        if (estudiante.getNombre() != null) {
            e.setNombre(estudiante.getNombre());
        }
        this.estudianteService.actualizarParcialPorId(estudiante);
    }

    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id) {
        this.estudianteService.borrarPorId(id);

    }

}
// la api tiene que tener un nombre y esta viene dada por el nombre de nuestro
// proyecto
