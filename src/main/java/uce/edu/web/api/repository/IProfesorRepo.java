package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.Profesor;

public interface IProfesorRepo {

    public Profesor seleccionarPorId(Integer Id);

    public List<Profesor> seleccionarTodos(String genero);

    public void actualizarPorId(Profesor profesor);

    public void actualizarParcialPorId(Profesor profesor);

    public void borrarPorId(Integer id);

    public void insertar(Profesor profesor);

}
