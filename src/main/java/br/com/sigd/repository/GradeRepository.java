package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Grade;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class GradeRepository extends GenericRepository<Grade> implements DefaultQueries<Grade, Long> {

    public GradeRepository(EntityManager entityManager) {
        super(entityManager);
    }

        @Override
    public Grade getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Grade.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Grade> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Grade.findAll");
        return query.getResultList();
    }

    public List<Grade> getListaPorAvaliado(Long umId) {
        Query query = this.entityManager.createQuery("SELECT a FROM Grade a WHERE a.idAvaliado = :umId ORDER BY dataDeCriacao ASC");        
        query.setParameter("umId", umId);
        return query.getResultList();
    }
    
     public Grade getListaPorAvaliador(Long umId) {
        Query query = this.entityManager.createQuery("SELECT a FROM Grade a WHERE a.idAvaliador = :id");        
        query.setParameter("id", umId);
        return getSingleResult(query);
    }
}
