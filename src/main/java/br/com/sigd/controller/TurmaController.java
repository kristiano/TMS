package br.com.sigd.controller;

import br.com.sigd.model.Academico;
import br.com.sigd.model.Turma;
import br.com.sigd.repository.TurmaRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author FernandoChagas
 */
@Named("TurmaController")
@SessionScoped
public class TurmaController extends BasicBeanContent<Turma, TurmaRepository> implements Serializable {

    @Inject
    ProfessorController professor;
    @Inject
    UsuarioController usuario;
    private Turma auxTurma;

    public TurmaController() {
        this.entity = new Turma();
        this.auxTurma = new Turma();
    }

    @Override
    public void insert() {
        this.repository = new TurmaRepository(ApplicationUtilies.catchEntityManager());
        addCoordenador();
        this.repository.insert(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Transplantatio Unit inserted ", ""));
        this.entity = new Turma();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {
        this.repository = new TurmaRepository(ApplicationUtilies.catchEntityManager());
        //pega a entidade e modifica os coordenadores para professores
        for (int i = 0; i < this.entity.getCoordenador().size(); i++) {
            //verifica se o coordenador não possui mais nenhuma turma pra voltar sua permissao para professor
            if (getByCoordenador(this.entity.getCoordenador().get(i).getId()).isEmpty()) {
                this.entity.getCoordenador().get(i).setPermissao("PROFESSOR");
                professor.update(this.entity.getCoordenador().get(i));
            }
        }
        this.repository.remove(this.entity);
        this.entity = new Turma();
        this.listOfEntities.clear();
    }

    public void addCoordenador() {
        //pega a entidade e modifica os professores para coordenadores
        for (int i = 0; i < this.entity.getCoordenador().size(); i++) {
            //PRECISA DAR O UPDATE EM CADA UM PROFESSOR
            if (this.entity.getCoordenador().get(i).getPermissao().equals("PROFESSOR")) {
                System.out.println("Aqui");
                //atualiza pra coordenador
                this.entity.getCoordenador().get(i).setPermissao("COORDENADOR");

                professor.update(this.entity.getCoordenador().get(i));
            }
        }
    }

    @Override
    public void update() {
        this.repository = new TurmaRepository(ApplicationUtilies.catchEntityManager());
        addCoordenador();
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Transplantatio Unit updated ", ""));
        this.entity = new Turma();
        this.listOfEntities.clear();
    }

    public List<Turma> getList() {
        this.repository = new TurmaRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }

    public List<Turma> getByCoordenador() {
        this.repository = new TurmaRepository(ApplicationUtilies.catchEntityManager());
        List<Turma> turmasCoordenador = this.repository.getTurmasCoordenador(usuario.getLoggedUsuario().getId());
        return turmasCoordenador;
    }

    public List<Turma> getByCoordenador(long idCoordenador) {
        this.repository = new TurmaRepository(ApplicationUtilies.catchEntityManager());
        List<Turma> turmasCoordenador = this.repository.getTurmasCoordenador(idCoordenador);
        return turmasCoordenador;
    }

    public Turma getById(Long id) {
        this.repository = new TurmaRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getById(id);
    }

    public List<Academico> carregarAcadPorTurma() {
        this.repository = new TurmaRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getAcademicos(this.entity.getId());
    }

    public List<Academico> carregarAcadPorTurma(Long idTurma) {
        this.repository = new TurmaRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getAcademicos(idTurma);
    }

    public Turma getAuxTurma() {
        return auxTurma;
    }

    public void setAuxTurma(Turma auxTurma) {
        this.auxTurma = auxTurma;
    }

    public List<Turma> carregaTurmasPorDisciplina(Long idDisciplina) {
        this.repository = new TurmaRepository(ApplicationUtilies.catchEntityManager());
        this.listOfEntities = this.repository.carregaTurmasPorDisciplina(idDisciplina);
        return listOfEntities;
    }
    
}
