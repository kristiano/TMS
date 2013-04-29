package br.com.sigd.controller;

import br.com.sigd.model.Curso;
import br.com.sigd.repository.CursoRepository;
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
@Named("CursoController")
@SessionScoped
public class CursoController extends BasicBeanContent<Curso, CursoRepository> implements Serializable {

   public CursoController() {
        this.entity = new Curso();
    }

    @Override
    public void insert() {
        this.repository = new CursoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success", entity.getNome()));
        this.entity = new Curso();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {    

        this.repository = new CursoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success", entity.getNome()));
        this.entity = new Curso();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new CursoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success", entity.getNome()));
        this.entity = new Curso();
        this.listOfEntities.clear();
    }

    public List<Curso> getList() {
        this.repository = new CursoRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }

    @Override
    public void prepareNew() {
        this.entity = new Curso();
        this.isNew = true;
        this.isEdit = false;
    }        
}