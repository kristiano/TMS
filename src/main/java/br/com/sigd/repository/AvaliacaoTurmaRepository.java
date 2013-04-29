/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import Enum.EstadoAvaliacao;
import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.AvaliacaoTurma;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Klimaco
 */
public class AvaliacaoTurmaRepository extends GenericRepository<AvaliacaoTurma> implements DefaultQueries<AvaliacaoTurma, Long> {

    public AvaliacaoTurmaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public AvaliacaoTurma getById(Long id) {
        Query query = this.entityManager.createNamedQuery("AvaliacaoTurma.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<AvaliacaoTurma> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("AvaliacaoTurma.findAll");
        return query.getResultList();
    }

    public List<AvaliacaoTurma> getAvaliadosByTurma(Long id) {
        Query query = this.entityManager.createQuery("SELECT a FROM AvaliacaoTurma a WHERE a.turma.id = :id and a.estadoAvaliacao = :estado");
        query.setParameter("id", id);
        query.setParameter("estado", EstadoAvaliacao.NAO);
        return query.getResultList();
    }

    public List<AvaliacaoTurma> getByAcademico(Long id) {
        Query query = this.entityManager.createNamedQuery("AvaliacaoTurma.getByAcademico");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<AvaliacaoTurma> getByDisponivelAcademico(Long id) {
        Query query = this.entityManager.createQuery("SELECT a FROM AvaliacaoTurma a WHERE a.academico.id = :id and a.estadoAvaliacao = :estado");
        query.setParameter("id", id);
        query.setParameter("estado", EstadoAvaliacao.LIBERADO);
        return query.getResultList();
    }

    public AvaliacaoTurma carregarAvaliacao(Long idTurma, Long idAcademico) {
        Query query = this.entityManager.createQuery("SELECT a FROM AvaliacaoTurma a WHERE a.academico.id = :idAcademico and a.turma.id = :idTurma and a.estadoAvaliacao = :estado");
        query.setParameter("idTurma", idAcademico);
        query.setParameter("idAcademico", idTurma);
        query.setParameter("estado", EstadoAvaliacao.LIBERADO);
        return getSingleResult(query);
    }
}
