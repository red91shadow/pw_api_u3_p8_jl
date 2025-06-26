package uce.edu.web.api.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.repository.modelo.Estudiante;
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
    public List<Profesor> consultarTodos(){
        return this.profesorService.buscarTodos();
        
    }

    
    public void guardar (@RequestBody Profesor profesor){
        
    }

    @PUT
    @Path("/{id}")
    public void actualizar (@RequestBody Profesor profesor, @PathParam("id") Integer id){

    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial (@RequestBody Estudiante estudiante, @PathParam("id") Integer id){

    }

    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id){
        
    }

}
