/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import Enum.BancaType;
import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Banca;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Klimaco
 */
public class BancaRepository extends GenericRepository<Banca> implements DefaultQueries<Banca, Long> {

    public BancaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Banca getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Banca.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Banca> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Banca.findAll");
        return query.getResultList();
    }

    public List<Banca> getBancasDefendidas(Long umId) {
        Query query = this.entityManager.createQuery("SELECT a FROM Banca a JOIN a.avaliadores av WHERE a.defesa = :defesa and av.id = :umId ORDER BY dataDefesa DESC");
        query.setParameter("defesa", BancaType.SIM);
        query.setParameter("umId", umId);
        return query.getResultList();
    }
}
