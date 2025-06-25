package uce.edu.web.api.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.repository.modelo.Profesor;

import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class Profesorcontroller {

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/consultarProf/{id}")
    public Profesor consultaPorId(@PathParam("id") Integer id) {
        return this.profesorService.buscarPorId(id);
    }

}
