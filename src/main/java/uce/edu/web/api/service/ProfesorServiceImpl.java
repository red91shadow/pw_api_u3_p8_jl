package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.IProfesorRepo;
import uce.edu.web.api.repository.modelo.Profesor;

@ApplicationScoped
public class ProfesorServiceImpl implements IProfesorService {

    @Inject
    private IProfesorRepo profesorRepo;

    @Override
    public Profesor buscarPorId(Integer id) {
        return this.profesorRepo.seleccionarPorId(id);
    }

    @Override
    public List<Profesor> buscarTodos(String genero) {
        return this.profesorRepo.seleccionarTodos(genero);
    }

    @Override
    @Transactional
    public void actualizarPorId(Profesor profesor) {
        this.profesorRepo.actualizarPorId(profesor);
    }

    @Override
    @Transactional
    public void actualizarParcialPorId(Profesor profesor) {
        this.profesorRepo.actualizarParcialPorId(profesor);
    }

    @Override
    @Transactional
    public void borrarPorId(Integer id) {
        this.profesorRepo.borrarPorId(id);
    }

    @Override
    @Transactional
    public void guardar(Profesor profesor) {
        this.profesorRepo.insertar(profesor);
    }
}