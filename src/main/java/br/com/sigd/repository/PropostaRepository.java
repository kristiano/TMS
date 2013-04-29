package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Proposta;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class PropostaRepository extends GenericRepository<Proposta> implements DefaultQueries<Proposta, Long> {

    public PropostaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Proposta getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Proposta.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Proposta> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Proposta.findAll");
        return query.getResultList();
    }

    /**
     * Método getAprovadaTurma Retorna uma lista com as propostas aprovadas de
     * uma determinada turma.
     *
     * @param id id da turma
     * @return Lista de propostas
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Proposta> getAprovadaTurma(long id) {
        Query query = this.entityManager.createNamedQuery("Proposta.getAprovadaTurma");
        query.setParameter("id", id);
        return query.getResultList();
    }

    /**
     * Método getPendenteTurma Retorna uma lista com as propostas pendentes de
     * uma determinada turma.
     *
     * @param id id do coordenador
     * @return Lista de propostas
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Proposta> getPendenteTurma(long id) {
        Query query = this.entityManager.createNamedQuery("Proposta.getPendenteTurma");
        query.setParameter("id", id);
        return query.getResultList();
    }    
    
      /**
     * Método getByProfessor Retorna uma lista com as propostas de um
     * determinado professor.
     *
     * @param id id do professor
     * @return Lista de turmas
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Proposta> getByProfessor(long id) {
        Query query = this.entityManager.createNamedQuery("Proposta.getByProfessor");
        query.setParameter("id", id);
        return query.getResultList();
    }
    
  public List<Proposta> getPropostaAcademico (Long id) {
        Query query = this.entityManager.createNamedQuery("Proposta.getByAcademico");
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    /**
     * Método getAprovadasProfessor Retorna uma lista com as propostas aprovadas de um
     * determinado professor.
     *
     * @param id id do professor
     * @return Lista de turmas
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Proposta> getAprovadasProfessor(long id) {
        Query query = this.entityManager.createNamedQuery("Proposta.getAprovadasProfessor");
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    /**
     * Método getAprovadasAcademico Retorna uma lista com as propostas aprovadas de um
     * determinado acadêmico.
     *
     * @param id id do acadêmico
     * @return Lista de turmas
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Proposta> getAprovadasAcademico(long id) {
        Query query = this.entityManager.createNamedQuery("Proposta.getAprovadasAcademico");
        query.setParameter("id", id);
        return query.getResultList();
    }
}
