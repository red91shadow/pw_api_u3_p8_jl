package uce.edu.web.api.controller;

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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.IEstudianteService;

@Path("/estudiantes") //
public class Estudiantecontroller {// se lo suele llamar el servicion - web service

    @Inject
    private IEstudianteService estudianteService;

    @GET
    @Path("/{id}") // path param se usa para consultar un recurso mediante su id
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar por ID", description = "Nos permiete permite conultar un elemnto de la tabla estudiante mediatne un id")
    public Response consultarPorId(@PathParam("id") Integer id) { // se lo suele llamar capacidades

        return Response.status(Response.Status.OK).entity(this.estudianteService.buscarPorId(id)).build();

    }

    // ?genero=F&provincia=pichincha
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "conusltar estudiantes", description = "Nos permite ver todos los elementos de la tabla estudiante")
    public Response consultarTodos(@QueryParam("genero") String genero,
            @QueryParam("provincia") String provincia) {
        System.out.println(provincia);

        return Response.status(Response.Status.OK).entity(this.estudianteService.buscarTodos(genero)).build();
    }

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

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar parcial de un estudiante", description = "Nos actualizar un estudiante especifico mediante su id")

    public Response actualizarParcial(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
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
        this.estudianteService.actualizarParcialPorId(e);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Borrar Estudiante", description = "Nos permiete eliminar un estudiante especifico mediante un id")

    public Response borrarPorId(@PathParam("id") Integer id) {
        this.estudianteService.borrarPorId(id);
        return Response.status(Response.Status.OK).build();

    }

}
// la api tiene que tener un nombre y esta viene dada por el nombre de nuestro
// proyecto
