/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.Estado;
import br.com.sigd.repository.EstadoRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Klimaco
 */
@Named("EstadoController")
@SessionScoped
public class EstadoController extends BasicBeanContent<Estado, EstadoRepository> implements Serializable {

   public EstadoController() {
        this.entity = new Estado();
    }

    @Override
    public void insert() {
        this.repository = new EstadoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro inserido com sucesso: ", entity.getNome()));
        this.entity = new Estado();
    }

    @Override
    public void remove() {
        this.repository = new EstadoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Estado();
    }

    @Override
    public void update() {
        this.repository = new EstadoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro atualizado com sucesso: ", entity.getNome()));
        this.entity = new Estado();
    }
}
