package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Profesor;

@Transactional
@ApplicationScoped
public class ProfesorRepoImpl implements IProfesorRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Profesor seleccionarPorId(Integer Id) {

        return this.entityManager.find(Profesor.class, Id);

    }

    @Override
    public List<Profesor> seleccionarTodos(String genero) {

        TypedQuery<Profesor> myQuery = this.entityManager
                .createQuery("SELECT e FROM Profesor e WHERE e.genero =:genero", Profesor.class);
                myQuery.setParameter("genero", genero);
        return myQuery.getResultList();

    }

    @Override
    public void actualizarPorId(Profesor profesor) {
        this.entityManager.merge(profesor);
    }

    @Override
    public void actualizarParcialPorId(Profesor profesor) {
        this.entityManager.merge(profesor);
    }

    @Override
    public void borrarPorId(Integer id) {
        this.entityManager.remove(this.seleccionarPorId(id));

    }

    @Override
    public void insertar(Profesor profesor) {
        this.entityManager.persist(profesor);
    }

}
