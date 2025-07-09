package uce.edu.web.api.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import uce.edu.web.api.repository.modelo.Hijo;

public class HijoRepoImpl implements HijoRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hijo> buscarPorEstudianteId(Integer id) {

        TypedQuery<Hijo> myQuery = this.entityManager
                .createQuery("SELECT h FROM HIJO h WHERE h.id =:id", Hijo.class);
        myQuery.setParameter("id", id);
        return myQuery.getResultList();
    }

}
