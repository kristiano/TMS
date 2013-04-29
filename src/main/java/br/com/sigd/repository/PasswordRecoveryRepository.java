/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.PasswordRecovery;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author marceloclaudios
 */
public class PasswordRecoveryRepository extends 
        GenericRepository<PasswordRecovery> implements
        DefaultQueries<PasswordRecovery,String> {

    public PasswordRecoveryRepository(EntityManager entityManager) {
        super(entityManager);
    }
    
    @Override
    public PasswordRecovery getById(String id) {
        Query query = this.entityManager.createNamedQuery("PasswordRecovery.getById");
        query.setParameter("id", id);
        PasswordRecovery result = getSingleResult(query);
        return result;
    }

    @Override
    public List<PasswordRecovery> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("PasswordRecovery.findAll");
        return query.getResultList();
    }
    
    public void removeByID(Long id){
        String hql = "delete from PasswordRecovery where usuario_id = :id";
        Query query = this.entityManager.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
