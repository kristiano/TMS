package br.com.sigd.controller;

import br.com.sigd.model.Disciplina;
import br.com.sigd.model.Turma;
import br.com.sigd.repository.DisciplinaRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author FernandoChagas
 */
@Named("DisciplinaController")
@SessionScoped
public class DisciplinaController extends BasicBeanContent<Disciplina, DisciplinaRepository> implements Serializable {

    private Disciplina auxDisciplina;
    @Inject
    private TurmaController turmaController;
    @Inject
    private AvaliacaoTurmaController avaliacaoTurmaController;
    private Double auxMedia;
    private Double auxVariancia;
    private List<Double> auxMediaTurmas;

    public DisciplinaController() {
        this.entity = new Disciplina();
        this.auxMediaTurmas = new ArrayList<Double>();
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

    public List<Double> getAuxMediaTurmas() {
        return auxMediaTurmas;
    }

    public void setAuxMediaTurmas(List<Double> auxMediaTurmas) {
        this.auxMediaTurmas = auxMediaTurmas;
    }

    @Override
    public void insert() {
        this.repository = new DisciplinaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success", ""));
        this.entity = new Disciplina();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {
        this.repository = new DisciplinaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success", ""));
        this.entity = new Disciplina();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new DisciplinaRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success", ""));
        this.entity = new Disciplina();
        this.listOfEntities.clear();
    }

    public List<Disciplina> getList() {
        this.repository = new DisciplinaRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }

    public Disciplina getAuxDisciplina() {
        return auxDisciplina;
    }

    public void setAuxDisciplina(Disciplina auxDisciplina) {
        this.auxDisciplina = auxDisciplina;
    }

    public void calcularMediaEVariancia() {
        List<Turma> auxListaTurmas = this.turmaController.carregaTurmasPorDisciplina(this.auxDisciplina.getId());
        this.auxMediaTurmas = new ArrayList<Double>();
        //Salva as medias das turmas de uma disciplina em uma lista auxiliar
        for (int i = 0; i < auxListaTurmas.size(); i++) {
            //calcula média e variância da turma
            this.avaliacaoTurmaController.calcularMediaEVariancia(auxListaTurmas.get(i).getId());
            //adiciona a nova média calculada
            //caso a turma ainda não tenha sido avaliada não entra pra conta
            if (!Double.isNaN(this.avaliacaoTurmaController.getAuxMedia()))
                this.auxMediaTurmas.add(this.avaliacaoTurmaController.getAuxMedia());
        }

        //calculo media
        Double somatorio = 0.0;
        for (int i = 0; i < this.auxMediaTurmas.size(); i++) {
            somatorio += this.auxMediaTurmas.get(i);
            System.out.println(this.auxMediaTurmas.get(i));
        }
        this.auxMedia = somatorio / this.auxMediaTurmas.size();


        //calculo variância
        somatorio = 0.0;
        for (int i = 0; i < this.auxMediaTurmas.size(); i++) {
            somatorio += Math.pow(this.auxMedia - this.auxMediaTurmas.get(i), 2);
        }

        this.auxVariancia = somatorio / this.auxMediaTurmas.size();
    }
}
