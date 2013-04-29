package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Projeto;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class ProjetoRepository extends GenericRepository<Projeto> implements DefaultQueries<Projeto, Long> {

    public ProjetoRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Projeto getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Projeto.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Projeto> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Projeto.findAll");
        return query.getResultList();
    }

    /**
     * Método getByProposta Retorna um projeto, caso exista uma proposta
     * correspondente.
     *
     * @param id id da proposta
     * @return projeto
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Projeto getByProposta(Long id) {
        Query query = this.entityManager.createNamedQuery("Projeto.getByProposta");
        query.setParameter("id", id);
        return getSingleResult(query);
    }
}
