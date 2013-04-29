package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.AreaAtuacao;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class AreaAtuacaoRepository extends GenericRepository<AreaAtuacao> implements DefaultQueries<AreaAtuacao,Long>{

    public AreaAtuacaoRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public AreaAtuacao getById(Long id) {
        Query query = this.entityManager.createNamedQuery("AreaAtuacao.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<AreaAtuacao> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("AreaAtuacao.findAll");
        return query.getResultList();
    }
        
    
}
