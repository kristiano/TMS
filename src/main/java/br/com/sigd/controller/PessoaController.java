/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.Pessoa;
import br.com.sigd.repository.PessoaRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Klimaco
 */
@Named("PessoaController")
@SessionScoped
public class PessoaController extends BasicBeanContent<Pessoa, PessoaRepository> implements Serializable {

    public PessoaController() {
        this.entity = new Pessoa();
    }

    @Override
    public void insert() {
        this.repository = new PessoaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        this.entity = new Pessoa();
    }

    @Override
    public void remove() {
        this.repository = new PessoaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Pessoa();
    }

    @Override
    public void update() {
        this.repository = new PessoaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new Pessoa();
    }
}
