/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.UserFeedback;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author FernandoChagas
 */
public class UserFeedbackRepository extends GenericRepository<UserFeedback> implements DefaultQueries<UserFeedback, Long> {

    public UserFeedbackRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public UserFeedback getById(Long id) {
        Query query = this.entityManager.createNamedQuery("UserFeedback.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<UserFeedback> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("UserFeedback.findAll");
        return query.getResultList();
    }

    public List<UserFeedback> getByUser(Long id) {
        Query query = this.entityManager.createNamedQuery("UserFeedback.getByUser");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<UserFeedback> getPendentes() {
        Query query = this.entityManager.createQuery("SELECT a FROM UserFeedback a WHERE a.status = 'Pendente'");
        return query.getResultList();
    }

    public List<UserFeedback> getEncaminhados() {
        Query query = this.entityManager.createQuery("SELECT a FROM UserFeedback a WHERE a.status = 'Encaminhado'");
        return query.getResultList();
    }

    public List<UserFeedback> getRespondidos() {
        Query query = this.entityManager.createQuery("SELECT a FROM UserFeedback a WHERE a.status = 'Respondido'");
        return query.getResultList();
    }
    
    public List<UserFeedback> getEncaminhadosResponsavel(Long id) {
        Query query = this.entityManager.createQuery("SELECT a FROM UserFeedback a WHERE a.responsavel.id = :id and a.status = 'Encaminhado'");
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    public List<UserFeedback> getRespondidosResponsavel(Long id) {
        Query query = this.entityManager.createQuery("SELECT a FROM UserFeedback a WHERE a.responsavel.id = :id and a.status = 'Respondido'");
        query.setParameter("id", id);
        return query.getResultList();
    }
}
