/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.ProblemasAssociados;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class ProblemasAssociadosRepository extends GenericRepository<ProblemasAssociados> implements DefaultQueries<ProblemasAssociados, Long> {

    public ProblemasAssociadosRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public ProblemasAssociados getById(Long id) {
        Query query = this.entityManager.createNamedQuery("ProblemasAssociados.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<ProblemasAssociados> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("ProblemasAssociados.findAll");
        return query.getResultList();
    }
}
