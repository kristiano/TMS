/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.Endereco;
import br.com.sigd.repository.EnderecoRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Klimaco
 */
@Named("EnderecoController")
@SessionScoped
public class EnderecoController extends BasicBeanContent<Endereco, EnderecoRepository> implements Serializable {

    public EnderecoController() {
        this.entity = new Endereco();
    }

    @Override
    public void insert() {
        this.repository = new EnderecoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        this.entity = new Endereco();
    }

    @Override
    public void remove() {
        this.repository = new EnderecoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Endereco();
    }

    @Override
    public void update() {
        this.repository = new EnderecoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new Endereco();
    }
}
