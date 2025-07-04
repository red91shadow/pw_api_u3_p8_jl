package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.modelo.Profesor;

public interface IProfesorService {

    public Profesor buscarPorId(Integer id);

    public List<Profesor> buscarTodos(String genero);

    public void actualizarPorId(Profesor profesor);

    public void actualizarParcialPorId(Profesor profesor);

    public void borrarPorId(Integer id);

    public void guardar(Profesor profesor);

}
