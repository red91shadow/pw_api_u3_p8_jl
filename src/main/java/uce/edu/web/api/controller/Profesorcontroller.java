package uce.edu.web.api.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.repository.modelo.Profesor;

import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class Profesorcontroller {

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    public Profesor consultaPorId(@PathParam("id") Integer id) {
        return this.profesorService.buscarPorId(id);
    }

    @GET
    @Path("")
    public List<Profesor> consultarTodos() {
        return this.profesorService.buscarTodos();

    }

    @POST
    @Path("")
    public void guardar(@RequestBody Profesor profesor) {
        this.profesorService.guardar(profesor);

    }

    @PUT
    @Path("/{id}")
    public void actualizar(@RequestBody Profesor profesor, @PathParam("id") Integer id) {

        profesor.setId(id);
        this.profesorService.actualizarParcialPorId(profesor);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@RequestBody Profesor profesor, @PathParam("id") Integer id) {

        profesor.setId(id);
        Profesor e = this.profesorService.buscarPorId(id);
        if (profesor.getCargaHoraria() != null) {
            e.setCargaHoraria(profesor.getCargaHoraria());

        }

        if (profesor.getDepartamento() != null) {
            e.setDepartamento(profesor.getDepartamento());

        }

        if (profesor.getLastName() != null) {
            e.setLastName(profesor.getLastName());

        }

        if (profesor.getName() != null) {
            e.setName(profesor.getName());

        }

        this.profesorService.actualizarParcialPorId(profesor);
    }

    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id) {
        this.profesorService.borrarPorId(id);

    }

}
