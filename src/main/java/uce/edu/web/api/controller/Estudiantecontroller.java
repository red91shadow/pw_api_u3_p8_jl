package uce.edu.web.api.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.IEstudianteService;

@Path("/estudiantes") //
public class Estudiantecontroller {// se lo suele llamar el servicion - web service

    @Inject
    private IEstudianteService estudianteService;

    @GET
    @Path("/{id}") // path param se usa para consultar un recurso mediante su id
    public Estudiante consultarPorId(@PathParam("id") Integer id) { // se lo suele llamar capacidades
        return this.estudianteService.buscarPorId(id);

    }

    // ?genero=F&provincia=pichincha
    @GET
    @Path("")
    @Operation(summary = "conusltar estudiantes")
    public List<Estudiante> consultarTodos(@QueryParam("genero") String genero,
            @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return this.estudianteService.buscarTodos(genero);
    }

    @POST // Indica que este método maneja solicitudes POST
    @Path("")
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
