/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.Municipio;
import br.com.sigd.repository.MunicipioRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Klimaco
 */
@Named("MunicipioController")
@SessionScoped
public class MunicipioController extends BasicBeanContent<Municipio, MunicipioRepository> implements Serializable {

    public MunicipioController() {
        this.entity = new Municipio();
    }

    @Override
    public void insert() {
        this.repository = new MunicipioRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        this.entity = new Municipio();
    }

    @Override
    public void remove() {
        this.repository = new MunicipioRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Municipio();
    }

    @Override
    public void update() {
        this.repository = new MunicipioRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new Municipio();
    }
}
