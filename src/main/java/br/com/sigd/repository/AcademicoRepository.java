package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Academico;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class AcademicoRepository extends GenericRepository<Academico> implements DefaultQueries<Academico, Long> {

    public AcademicoRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Academico getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Academico.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Academico> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Academico.findAll");
        return query.getResultList();
    }

    public List<Academico> carregarPorTurma(Long idTurma) {
        Query query = this.entityManager.createQuery("Select academico from Academico academico join academico.turma where academico.id = :id");
        query.setParameter("id", idTurma);
        return query.getResultList();
    }

    public Academico getByUserName(String username) {
        Query query = this.entityManager.createQuery("Select academico from Academico academico where academico.username = :username");
        query.setParameter("username", username);
        return getSingleResult(query);
    }
}
