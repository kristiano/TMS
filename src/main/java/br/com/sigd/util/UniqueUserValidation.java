/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.util;

import br.com.sigd.repository.UsuarioRepository;
import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author marceloclaudios
 */
public class UniqueUserValidation implements ConstraintValidator<UniqueUser, String>{

    private UniqueUser user;
    
    @Override
    public void initialize(UniqueUser user) {
        this.user=user;
       
    }

    @Override
    public boolean isValid(String user, ConstraintValidatorContext cvc) {
        
        if(user == null)
            return true;
        EntityManager em = JPAFilter.getFactory().createEntityManager();
        UsuarioRepository rep = new UsuarioRepository(em);
        if (rep.loadUser(user) == null)
            return true;
        return false;
}
}