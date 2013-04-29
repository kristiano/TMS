package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Disciplina;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class DisciplinaRepository extends GenericRepository<Disciplina> implements DefaultQueries<Disciplina,Long>{

    public DisciplinaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Disciplina getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Disciplina.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Disciplina> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Disciplina.findAll");
        return query.getResultList();
    }
        
    
}
