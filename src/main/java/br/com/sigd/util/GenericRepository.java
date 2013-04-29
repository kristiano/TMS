/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.util;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author marceloclaudios
 */
public abstract class GenericRepository<Class> {

    protected EntityManager entityManager;

    public GenericRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(Class beInserted) {
        this.entityManager.persist(beInserted); 
        this.entityManager.flush();        
    }

    public void remove(Class beRemoved) {
        this.entityManager.remove(this.entityManager.merge(beRemoved));
        this.entityManager.flush();
    }

    public void update(Class beUpdated) {
        this.entityManager.merge(beUpdated);
        this.entityManager.flush();
    }
    
    /**
     * Método getSingleResult
     * Método responsável por retornar um único resultado em uma busca.
     * @param <Class> como o método é genérico ele pode receber classes diferentes
     * @param query 
     * @return list 
     */

    public <Class> Class getSingleResult(Query query) {
        query.setMaxResults(1); //limita a busca a um elemento
        List<Class> listResult = query.getResultList(); //recebe a lista com os resultados
        if (listResult == null || listResult.isEmpty()) //verifica se é nulo ou vazia
            return null;
        else
            return (Class) listResult.get(0); //caso não seja envia o primeiro resultado        
    }
    
    
    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * @param entityManager the entityManager to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
