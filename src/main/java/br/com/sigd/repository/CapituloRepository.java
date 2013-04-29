package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Capitulo;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class CapituloRepository extends GenericRepository<Capitulo> implements DefaultQueries<Capitulo,Long>{

    public CapituloRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Capitulo getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Capitulo.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Capitulo> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Capitulo.findAll");
        return query.getResultList();
    }
    
    public List<Capitulo> getByProjeto(Long id) {
        Query query = this.entityManager.createNamedQuery("Capitulo.getByProjeto");
        query.setParameter("id", id);
        return query.getResultList();
    }
        
    
}
