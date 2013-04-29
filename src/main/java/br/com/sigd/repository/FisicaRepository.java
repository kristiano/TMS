/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Fisica;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Klimaco
 */
public class FisicaRepository extends GenericRepository<Fisica> implements DefaultQueries<Fisica, Long> {

    public FisicaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Fisica getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Fisica.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Fisica> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Fisica.findAll");
        return query.getResultList();
    }
}
