/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.Telefone;
import br.com.sigd.repository.TelefoneRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Klimaco
 */
@Named("TelefoneController")
@SessionScoped
public class TelefoneController extends BasicBeanContent<Telefone, TelefoneRepository> implements Serializable {

    public TelefoneController() {
        this.entity = new Telefone();
    }

    @Override
    public void insert() {
        this.repository = new TelefoneRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        this.entity = new Telefone();
    }

    @Override
    public void remove() {
        this.repository = new TelefoneRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Telefone();
    }

    @Override
    public void update() {
        this.repository = new TelefoneRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new Telefone();
    }
}
