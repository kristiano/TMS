/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import Enum.EstadoAvaliacao;
import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.AvaliacaoCapitulo;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Klimaco
 */
public class AvaliacaoCapituloRepository extends GenericRepository<AvaliacaoCapitulo> implements DefaultQueries<AvaliacaoCapitulo, Long> {

    public AvaliacaoCapituloRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public AvaliacaoCapitulo getById(Long id) {
        Query query = this.entityManager.createNamedQuery("AvaliacaoCapitulo.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<AvaliacaoCapitulo> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("AvaliacaoCapitulo.findAll");
        return query.getResultList();
    }

    public List<AvaliacaoCapitulo> getAvaliadosByTurma(Long id) {
        Query query = this.entityManager.createQuery("SELECT a FROM AvaliacaoCapitulo a WHERE a.turma.id = :id and a.estadoAvaliacao = :estado");
        query.setParameter("id", id);
        query.setParameter("estado", EstadoAvaliacao.NAO);
        return query.getResultList();
    }

    public AvaliacaoCapitulo getLiberadoPorCapitulo(Long id) {
        Query query = this.entityManager.createQuery("SELECT a FROM AvaliacaoCapitulo a WHERE a.capitulo.id = :id and a.estadoAvaliacao = :estado");
        query.setParameter("id", id);
        query.setParameter("estado", EstadoAvaliacao.LIBERADO);
        return getSingleResult(query);
    }

    public AvaliacaoCapitulo getAvaliadoPorCapitulo(Long id) {
        Query query = this.entityManager.createQuery("SELECT a FROM AvaliacaoCapitulo a WHERE a.capitulo.id = :id and a.estadoAvaliacao = :estado");
        query.setParameter("id", id);
        query.setParameter("estado", EstadoAvaliacao.NAO);
        return getSingleResult(query);
    }

    public AvaliacaoCapitulo getPorCapitulo(Long id) {
        Query query = this.entityManager.createQuery("SELECT a FROM AvaliacaoCapitulo a WHERE a.capitulo.id = :id");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    public List<AvaliacaoCapitulo> getAvaliadosPorNomeCapitulo(String nome) {
        Query query = this.entityManager.createQuery("SELECT a FROM AvaliacaoCapitulo a WHERE a.capitulo.nome = :nome and a.estadoAvaliacao = :estado");
        query.setParameter("nome", nome);
        query.setParameter("estado", EstadoAvaliacao.NAO);
        return query.getResultList();
    }

    public List<AvaliacaoCapitulo> getAvaliadosPorNomeCapituloETurma(String nome, Long idTurma) {
        Query query = this.entityManager.createQuery("SELECT a FROM AvaliacaoCapitulo a WHERE a.capitulo.nome = :nome and a.estadoAvaliacao = :estado and a.turma.id = :idTurma");
        query.setParameter("nome", nome);
        query.setParameter("idTurma", idTurma);
        query.setParameter("estado", EstadoAvaliacao.NAO);
        return query.getResultList();
    }

    public List<AvaliacaoCapitulo> getAvaliadosByAcademico(Long id) {
        Query query = this.entityManager.createQuery("SELECT a FROM AvaliacaoCapitulo a WHERE a.academico.id = :id and a.estadoAvaliacao = :estado");
        query.setParameter("id", id);
        query.setParameter("estado", EstadoAvaliacao.NAO);
        return query.getResultList();
    }

    public List<AvaliacaoCapitulo> getByDisponivelAcademico(Long id) {
        Query query = this.entityManager.createQuery("SELECT a FROM AvaliacaoCapitulo a WHERE a.academico.id = :id and a.estadoAvaliacao = :estado");
        query.setParameter("id", id);
        query.setParameter("estado", EstadoAvaliacao.LIBERADO);
        return query.getResultList();
    }

    public AvaliacaoCapitulo carregarAvaliacao(Long idCapitulo, Long idAcademico) {
        Query query = this.entityManager.createQuery("SELECT a FROM AvaliacaoCapitulo a WHERE a.academico.id = :idAcademico and a.capitulo.id = :idTurma and a.estadoAvaliacao = :estado");
        query.setParameter("idTurma", idAcademico);
        query.setParameter("idAcademico", idCapitulo);
        query.setParameter("estado", EstadoAvaliacao.LIBERADO);
        return getSingleResult(query);
    }
}
