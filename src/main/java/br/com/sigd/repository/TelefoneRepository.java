/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Telefone;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Klimaco
 */
public class TelefoneRepository extends GenericRepository<Telefone> implements DefaultQueries<Telefone, Long> {

    public TelefoneRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Telefone getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Telefone.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Telefone> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Telefone.findAll");
        return query.getResultList();
    }
}


