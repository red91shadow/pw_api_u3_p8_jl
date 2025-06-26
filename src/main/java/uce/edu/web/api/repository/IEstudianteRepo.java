package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.Estudiante;

public interface IEstudianteRepo {

    public Estudiante seleccionarPortId(Integer id);

    public List<Estudiante> seleccionarTodos();

    
    

}
