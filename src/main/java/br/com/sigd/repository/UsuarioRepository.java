/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Usuario;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Klimaco
 */
public class UsuarioRepository extends GenericRepository<Usuario> implements DefaultQueries<Usuario, Long> {

    public UsuarioRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Usuario getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Usuario.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Usuario> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Usuario.findAll");
        return query.getResultList();
    }

    public Usuario loadLoggedUser(String username) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> c = cb.createQuery(Usuario.class);
        Root<Usuario> l = c.from(Usuario.class);
        c.select(l);
        Predicate predi = cb.equal(l.get("username"), username);
        c.where(predi);
        TypedQuery<Usuario> query = this.entityManager.createQuery(c);
        return query.getSingleResult();
    }

    public Usuario loadUser(String user) {
        Query query = this.entityManager.createQuery("SELECT a FROM Usuario a WHERE username = :username");
        query.setParameter("username", user);
        Usuario result = getSingleResult(query);
        return result;
    }
}
