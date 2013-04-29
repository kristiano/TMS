/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Endereco;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Klimaco
 */
public class EnderecoRepository extends GenericRepository<Endereco> implements DefaultQueries<Endereco, Long> {

    public EnderecoRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Endereco getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Endereco.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Endereco> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Endereco.findAll");
        return query.getResultList();
    }
}
