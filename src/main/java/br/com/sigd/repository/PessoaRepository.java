/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Pessoa;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Klimaco
 */
public class PessoaRepository extends GenericRepository<Pessoa> implements DefaultQueries<Pessoa, Long> {

    public PessoaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Pessoa getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Pessoa.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Pessoa> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Pessoa.findAll");
        return query.getResultList();
    }
    
}
