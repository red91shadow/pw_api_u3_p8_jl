package uce.edu.web.api.service;

import java.util.List;
import uce.edu.web.api.repository.modelo.Profesor; 
import uce.edu.web.api.service.to.ProfesorTo;

public interface IProfesorService {

    void guardar(Profesor profesor);
    void actualizarPorId(Profesor profesor);
    void actualizarParcialPorId(Profesor profesor);
    void borrarPorId(Integer id);
    Profesor buscarPorId(Integer id);
    List<Profesor> buscarTodos(String genero);
}