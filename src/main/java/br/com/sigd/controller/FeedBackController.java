/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.FeedBack;
import br.com.sigd.repository.FeedBackRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author FernandoChagas
 */
@Named("FeedBackController")
@SessionScoped
public class FeedBackController extends BasicBeanContent<FeedBack, FeedBackRepository> implements Serializable{

     @Override
    public void insert() {
        this.repository = new FeedBackRepository(ApplicationUtilies.catchEntityManager());
        this.entity.setData(getToday());
        this.repository.insert(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Feedback inserido com sucesso: ", ""));
        this.entity = new FeedBack();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {
        this.repository = new FeedBackRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new FeedBack();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new FeedBackRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Feedback atualizado com sucesso: ", ""));
        this.entity = new FeedBack();
        this.listOfEntities.clear();
    }

    public List<FeedBack> getList() {
        this.repository = new FeedBackRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }

    public Date getToday() {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }
}
