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
    public List<Profesor> seleccionarTodos() {

        TypedQuery<Profesor> myQuery = this.entityManager.createQuery("SELECT e FROM Profesor e", Profesor.class);
        return myQuery.getResultList();

    }

}
