package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.PropostaStatus;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class PropostaStatusRepository extends GenericRepository<PropostaStatus> implements DefaultQueries<PropostaStatus,Long>{

    public PropostaStatusRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public PropostaStatus getById(Long id) {
        Query query = this.entityManager.createNamedQuery("PropostaStatus.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<PropostaStatus> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("PropostaStatus.findAll");
        return query.getResultList();
    }
        
    
}
