/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.Situacoes;
import br.com.sigd.repository.SituacoesRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author FernandoChagas
 */
@Named("SituacoesController")
@SessionScoped
public class SituacoesController extends BasicBeanContent<Situacoes, SituacoesRepository> implements Serializable{

   public SituacoesController() {
        this.entity = new Situacoes();
    }

    @Override
    public void insert() {
        this.repository = new SituacoesRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, this.entity.getNome(), " Inserido com sucesso" ));
        this.entity = new Situacoes();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {
        this.repository = new SituacoesRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Situacoes();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new SituacoesRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new Situacoes();
        this.listOfEntities.clear();
    }

    public List<Situacoes> getList() {
        this.repository = new SituacoesRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }
}
