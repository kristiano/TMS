package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Curso;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class CursoRepository extends GenericRepository<Curso> implements DefaultQueries<Curso,Long>{

    public CursoRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Curso getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Curso.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Curso> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Curso.findAll");
        return query.getResultList();
    }                    
}
