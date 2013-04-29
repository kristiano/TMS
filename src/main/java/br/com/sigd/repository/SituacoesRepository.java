/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Situacoes;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class SituacoesRepository extends GenericRepository<Situacoes> implements DefaultQueries<Situacoes,Long>{

    public SituacoesRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Situacoes getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Situacoes.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Situacoes> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Situacoes.findAll");
        return query.getResultList();
    }
        
    
}
