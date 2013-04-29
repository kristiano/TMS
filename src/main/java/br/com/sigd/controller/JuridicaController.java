/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.Juridica;
import br.com.sigd.repository.JuridicaRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Klimaco
 */
@Named("JuridicaController")
@SessionScoped
public class JuridicaController extends BasicBeanContent<Juridica, JuridicaRepository> implements Serializable {

    public JuridicaController() {
        this.entity = new Juridica();
    }

    @Override
    public void insert() {
        this.repository = new JuridicaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        this.entity = new Juridica();
    }

    @Override
    public void remove() {
        this.repository = new JuridicaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Juridica();
    }

    @Override
    public void update() {
        this.repository = new JuridicaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new Juridica();
    }
}
