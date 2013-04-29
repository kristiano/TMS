package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.ProjetoStatus;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class ProjetoStatusRepository extends GenericRepository<ProjetoStatus> implements DefaultQueries<ProjetoStatus,Long>{

    public ProjetoStatusRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public ProjetoStatus getById(Long id) {
        Query query = this.entityManager.createNamedQuery("ProjetoStatus.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<ProjetoStatus> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("ProjetoStatus.findAll");
        return query.getResultList();
    }
        
    
}
