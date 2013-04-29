/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.FeedBack;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class FeedBackRepository extends GenericRepository<FeedBack> implements DefaultQueries<FeedBack, Long> {

    public FeedBackRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public FeedBack getById(Long id) {
        Query query = this.entityManager.createNamedQuery("FeedBack.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<FeedBack> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("FeedBack.findAll");
        return query.getResultList();
    }
}
