package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Estudiante;

@Transactional
@ApplicationScoped
public class EstudianteRepoImpl implements IEstudianteRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Estudiante seleccionarPortId(Integer id) {
        return this.entityManager.find(Estudiante.class, id);

    }

    @Override
    public List<Estudiante> seleccionarTodos(String genero) {

        TypedQuery<Estudiante> myQuery = this.entityManager
                .createQuery("SELECT e FROM Estudiante e WHERE e.genero =:genero", Estudiante.class);
        myQuery.setParameter("genero", genero);
        return myQuery.getResultList();

    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void actualizarPorId(Estudiante estudiante) {
        this.entityManager.merge(estudiante);

    }

    public void actualizarParcialPorId(Estudiante estudiante) {
        this.entityManager.merge(estudiante);

    }

    @Override
    public void borrarPorId(Integer id) {
        this.entityManager.remove(this.seleccionarPortId(id));
    }

    @Override
    public void insertar(Estudiante estudiante) {
        this.entityManager.persist(estudiante);
    }

}
