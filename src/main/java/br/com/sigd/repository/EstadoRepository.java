/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Estado;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Klimaco
 */
public class EstadoRepository extends GenericRepository<Estado> implements DefaultQueries<Estado, Long> {

    public EstadoRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Estado getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Estado.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Estado> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Estado.findAll");
        return query.getResultList();
    }
}
