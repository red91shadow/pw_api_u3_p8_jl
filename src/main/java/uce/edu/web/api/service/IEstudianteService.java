package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.modelo.Estudiante;
//capa de negocio, es recomendable que los nombres esten mas relacionados con la logica de negocio

public interface IEstudianteService {
    public Estudiante buscarPorId(Integer id);

    public List<Estudiante> buscarTodos();

    public void actualizarPorId(Estudiante estudiante);

    public void actualizarParcialPorId(Estudiante estudiante);

    public void borrarPorId(Integer id);

    public void guardar(Estudiante estudiante);

}
