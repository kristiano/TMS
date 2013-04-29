/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Municipio;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Klimaco
 */
public class MunicipioRepository extends GenericRepository<Municipio> implements DefaultQueries<Municipio, Long> {

    public MunicipioRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Municipio getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Municipio.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Municipio> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Municipio.findAll");
        return query.getResultList();
    }
}


