/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.Banca;
import br.com.sigd.repository.BancaRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Klimaco
 */
@Named("BancaController")
@SessionScoped
public class BancaController extends BasicBeanContent<Banca, BancaRepository> implements Serializable {

    @Inject
    private ProfessorController professorController; 
    @Inject 
    private ProjetoController projetoController;
    @Inject 
    private GradeController gradeController;
    private Long idProjeto;    

    public BancaController() {
        this.entity = new Banca();
    }

    public Date getToday() {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    public Long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Long idProjeto) {
        this.idProjeto = idProjeto;
    }

    @Override
    public void insert() {
        this.repository = new BancaRepository(ApplicationUtilies.catchEntityManager());       
        this.entity.setProjeto(projetoController.getById(this.idProjeto));
        this.repository.insert(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success ", ""));
        this.entity = new Banca();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {
        this.repository = new BancaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Banca();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new BancaRepository(ApplicationUtilies.catchEntityManager());         
        this.entity.setProjeto(projetoController.getById(this.idProjeto));
        System.err.println (this.idProjeto);
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Banca atualizada com sucesso ", ""));
        this.entity = new Banca();
        this.listOfEntities.clear();
    }

    public List<Banca> getList() {
        this.repository = new BancaRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }

    public List<Banca> getDefendidasPorAvaliador() {
        this.repository = new BancaRepository(ApplicationUtilies.catchEntityManager());        
        listOfEntities = this.repository.getBancasDefendidas(professorController.getProfessorLogado().getId());          
        return listOfEntities;
    }
    
    public void loadAuxBanca () {
        this.repository = new BancaRepository(ApplicationUtilies.catchEntityManager());        
        this.gradeController.setAuxBanca(this.repository.getById(this.gradeController.getIdBanca()));
    }
}
