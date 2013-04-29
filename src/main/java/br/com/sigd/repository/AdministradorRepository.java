/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.model.Administrador;
import br.com.sigd.util.GenericRepository;
import javax.persistence.EntityManager;

/**
 *
 * @author marceloclaudios
 */
public class AdministradorRepository extends GenericRepository<Administrador> {
    
    public AdministradorRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
