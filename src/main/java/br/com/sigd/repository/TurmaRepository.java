package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Academico;
import br.com.sigd.model.Turma;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class TurmaRepository extends GenericRepository<Turma> implements DefaultQueries<Turma, Long> {

    public TurmaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Turma getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Turma.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Turma> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Turma.findAll");
        return query.getResultList();
    }

    /**
     * Método getTurmasCoordenador Retorna uma lista com as turmas de um
     * determinado coordenador;
     *
     * @param id id do coordenador
     * @return Lista de turmas
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Turma> getTurmasCoordenador(Long id) {
        Query query = this.entityManager.createNamedQuery("Turma.getTurmasCoordenador");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Academico> getAcademicos(Long idTurma) {
        Query query = this.entityManager.createQuery("Select academicos from Turma turma join turma.academicos academicos  where turma.id = :id");
        query.setParameter("id", idTurma);
        return query.getResultList();
    }

    public List<Academico> getCoordenadores(Long idTurma) {
        Query query = this.entityManager.createQuery("Select coordenador from Turma turma join turma.coordenador coordenador where turma.id = :id");
        query.setParameter("id", idTurma);
        return query.getResultList();
    }

    public List<Turma> carregaTurmasPorDisciplina(Long idDisciplina) {
        Query query = this.entityManager.createQuery("Select turma from Turma turma where turma.disciplina.id = :id");
        query.setParameter("id", idDisciplina);
        return query.getResultList();
    }
}
