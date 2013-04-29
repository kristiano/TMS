/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.Academico;
import br.com.sigd.model.Curso;
import br.com.sigd.model.Professor;
import br.com.sigd.model.Turma;
import br.com.sigd.repository.ProfessorRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Klimaco
 * @author Fernando Chagas
 */
@Named("ProfessorController")
@SessionScoped
public class ProfessorController extends BasicBeanContent<Professor, ProfessorRepository> implements Serializable {

    private Professor professorLogado;
    private String password;
    @Inject
    private CursoController cursoController;

    public ProfessorController() {
        this.entity = new Professor();
        this.professorLogado = null;
    } 

    @Override
    public void insert() {
        this.repository = new ProfessorRepository(ApplicationUtilies.catchEntityManager());
        this.entity.setPassword("professor"); //senha padrão        
        this.entity.setDataCadastro(getToday());
        this.repository.insert(this.entity);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Professor(a): " + this.entity.getNome() + "success", this.entity.getNome()));
        this.entity = new Professor();
        this.listOfEntities.clear();

    }

    public Date getToday() {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    @Override
    public void remove() {
        this.repository = new ProfessorRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Professor();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new ProfessorRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success", entity.getNome()));
        this.entity = new Professor();
        this.listOfEntities.clear();
    }

    public void update(Professor entity) {
        this.repository = new ProfessorRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success", entity.getNome()));
        this.listOfEntities.clear();
    }

    public List<Professor> getList() {
        this.repository = new ProfessorRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }

    public void editarPerfil() {
        this.repository = new ProfessorRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.professorLogado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, professorLogado.getNome(), " : Perfil altualizado com sucesso"));
        this.entity = new Professor();
        this.listOfEntities.clear();
    }

    public void setLoggedCliente(Professor professorLogado) {
        this.professorLogado = professorLogado;
    }

    public void changePassword() {
        this.professorLogado.setPassword(this.password);
        this.repository = new ProfessorRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.professorLogado);
        this.password = "";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar senha", "Senha alterada com sucesso."));
        context.addCallbackParam("loggedIn", true);
    }

    public Professor getProfessorLogado() {
        if (ApplicationUtilies.isLogged() && this.professorLogado == null) {
            setLoggedCliente((Professor) ApplicationUtilies.getAuthenticadedUser());
        }
        return this.professorLogado;
    }

    public List<Academico> getAcademicosTurmasProfessorLogado() {
        this.repository = new ProfessorRepository(ApplicationUtilies.catchEntityManager());
        //pega todos os professores com seus respectivos cursos
        Professor professor = this.repository.getProfessorComCursos(getProfessorLogado().getId());
        List<Academico> auxAcademicos = new ArrayList<Academico>();
        if (professor == null) {
            System.out.println("null");
        }
        for (int i = 0; i < professor.getCursos().size(); i++) {
            //para cada curso adiciona os seus respectivos acadêmicos
            auxAcademicos.addAll(professor.getCursos().get(i).getAcademicos());
        }
        return auxAcademicos;
    }

    public List<Curso> buscarListaCursoProfessorLogado() {
        this.repository = new ProfessorRepository(ApplicationUtilies.catchEntityManager());
        Professor professor = this.repository.getProfessorComCursos(getProfessorLogado().getId());
        return professor.getCursos();
    }
    
}