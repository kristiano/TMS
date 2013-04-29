/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import Enum.EstadoAvaliacao;
import br.com.sigd.model.AvaliacaoTurma;
import br.com.sigd.model.Turma;
import br.com.sigd.repository.AvaliacaoTurmaRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.GenericController;
import java.io.Serializable;
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
@Named("AvaliacaoTurmaController")
@SessionScoped
public class AvaliacaoTurmaController extends GenericController<AvaliacaoTurma, AvaliacaoTurmaRepository> implements Serializable {

    private Turma auxTurma;
    private AvaliacaoTurma auxAvaliacaoTurma;
    private Double auxMedia;
    private Double auxVariancia;
    
    @Inject
    private TurmaController turmaController;

    public AvaliacaoTurmaController() {
        this.entity = new AvaliacaoTurma();
        this.auxTurma = new Turma();
        this.auxAvaliacaoTurma = new AvaliacaoTurma();
    }

    public Turma getAuxTurma() {
        return auxTurma;
    }

    public void setAuxTurma(Turma auxTurma) {
        this.auxTurma = auxTurma;
    }

    public AvaliacaoTurma getAuxAvaliacaoTurma() {
        return auxAvaliacaoTurma;
    }

    public void setAuxAvaliacaoTurma(AvaliacaoTurma auxAvaliacaoTurma) {
        this.auxAvaliacaoTurma = auxAvaliacaoTurma;
    }

    public Double getAuxMedia() {
        return auxMedia;
    }

    public void setAuxMedia(Double auxMedia) {
        this.auxMedia = auxMedia;
    }

    public Double getAuxVariancia() {
        return auxVariancia;
    }

    public void setAuxVariancia(Double auxVariancia) {
        this.auxVariancia = auxVariancia;
    }

    @Override
    public void insert() {
        this.repository = new AvaliacaoTurmaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Avaliação inserida com sucesso ", ""));
        this.entity = new AvaliacaoTurma();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {
        this.repository = new AvaliacaoTurmaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new AvaliacaoTurma();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new AvaliacaoTurmaRepository(ApplicationUtilies.catchEntityManager());
        this.entity.setEstadoAvaliacao(EstadoAvaliacao.NAO);
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Avaliado com sucesso ", ""));
        this.entity = new AvaliacaoTurma();
        this.listOfEntities.clear();
    }

    public List<AvaliacaoTurma> getList() {
        this.repository = new AvaliacaoTurmaRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }

    public List<AvaliacaoTurma> carregarAvaliadosByTurma(Long idTurma) {
        this.repository = new AvaliacaoTurmaRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getAvaliadosByTurma(idTurma);
        return listOfEntities;
    }
    
    public void calcularMediaEVariancia (Long idTurma) {
        List<AvaliacaoTurma> aux = carregarAvaliadosByTurma(idTurma);
        Double somatorio = 0.0;
        //calculo da média
        for (int i = 0; i < aux.size(); i++) {
            somatorio+=aux.get(i).getNota();
        }
        this.auxMedia = somatorio / aux.size();
        
        //cálculo da variância
        somatorio = 0.0;
        for (int i = 0; i < aux.size(); i++) {
            somatorio+= Math.pow(this.auxMedia-aux.get(i).getNota(), 2);
        }
        
        this.auxVariancia = somatorio / aux.size();
    }

    public List<AvaliacaoTurma> getByAcademico(Long idAcademico) {
        this.repository = new AvaliacaoTurmaRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getByAcademico(idAcademico);
        return listOfEntities;
    }

    public List<AvaliacaoTurma> carregarDisponivelAcademico(Long idAcademico) {
        this.repository = new AvaliacaoTurmaRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getByDisponivelAcademico(idAcademico);
        return listOfEntities;
    }

    public void carregarAvaliaçãoPorId() {
        this.repository = new AvaliacaoTurmaRepository(ApplicationUtilies.catchEntityManager());
        this.entity = this.repository.getById(this.auxAvaliacaoTurma.getId());        
    }
}
