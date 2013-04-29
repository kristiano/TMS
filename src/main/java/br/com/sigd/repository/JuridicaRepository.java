/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Juridica;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Klimaco
 */
public class JuridicaRepository extends GenericRepository<Juridica> implements DefaultQueries<Juridica, Long> {

    public JuridicaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Juridica getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Juridica.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Juridica> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Juridica.findAll");
        return query.getResultList();
    }
}

