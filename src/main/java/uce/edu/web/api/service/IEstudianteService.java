package uce.edu.web.api.service;

import java.util.List;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Estudiante;
//capa de negocio, es recomendable que los nombres esten mas relacionados con la logica de negocio
import uce.edu.web.api.service.to.EstudianteTo;

public interface IEstudianteService {
    public EstudianteTo buscarPorId(Integer id, UriInfo uriInfo);

    public List<Estudiante> buscarTodos(String genero);

    public void actualizarPorId(Estudiante estudiante);

    public void actualizarParcialPorId(Estudiante estudiante);

    public void borrarPorId(Integer id);

    public void guardar(Estudiante estudiante);

}
