/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.Fisica;
import br.com.sigd.repository.FisicaRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Klimaco
 */
@Named("FisicaController")
@SessionScoped
public class FisicaController extends BasicBeanContent<Fisica, FisicaRepository>  implements Serializable {

   
    public FisicaController() {
        this.entity = new Fisica();
    }

    @Override
    public void insert() {
        this.repository = new FisicaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        this.entity = new Fisica();
    }

    @Override
    public void remove() {
        this.repository = new FisicaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Fisica();
    }

    @Override
    public void update() {
        this.repository = new FisicaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new Fisica();
    }


}
