/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.ProblemasAssociados;
import br.com.sigd.repository.ProblemasAssociadosRepository;
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
@Named("ProblemasAssociadosController")
@SessionScoped
public class ProblemasAssociadosController extends BasicBeanContent<ProblemasAssociados, ProblemasAssociadosRepository> implements Serializable{

     public ProblemasAssociadosController() {
        this.entity = new ProblemasAssociados();
    }

    @Override
    public void insert() {
        this.repository = new ProblemasAssociadosRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, entity.getNome(), " Inserido com sucesso: "));
        this.entity = new ProblemasAssociados();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {
        this.repository = new ProblemasAssociadosRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new ProblemasAssociados();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new ProblemasAssociadosRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro atualizado com sucesso: ", entity.getNome()));
        this.entity = new ProblemasAssociados();
        this.listOfEntities.clear();
    }

    public List<ProblemasAssociados> getList() {
        this.repository = new ProblemasAssociadosRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }
}
