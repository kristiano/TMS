package br.com.sigd.controller;

import br.com.sigd.model.Academico;
import br.com.sigd.model.AvaliacaoTurma;
import br.com.sigd.model.Turma;
import br.com.sigd.repository.AcademicoRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
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
 * @author FernandoChagas
 */
@Named("AcademicoController")
@SessionScoped
public class AcademicoController extends BasicBeanContent<Academico, AcademicoRepository> implements Serializable{

    private Academico loggedAcademico;
    private String password;
    @Inject
    private TurmaController turmaController;
    @Inject 
    private AvaliacaoTurmaController avaliacaoTurmaController;

    public AcademicoController() {
        this.entity = new Academico();
        this.loggedAcademico = null;
    }

    @Override
    public void insert() {
        this.repository = new AcademicoRepository(ApplicationUtilies.catchEntityManager());        
        this.repository.insert(this.entity);
        this.entity = new Academico();
        this.listOfEntities.clear();
    }
    
    public void inserirPorTurma (Turma auxTurma) { 
        System.out.println (auxTurma.getId());
        this.turmaController.setEntity(this.turmaController.getById(auxTurma.getId()));
        if (this.turmaController.getEntity().getAcademicos() == null) {
            System.out.println("null");
            List<Academico> aTest = this.turmaController.getEntity().getAcademicos();
            aTest = new ArrayList<Academico>();
        }
        //habilitando avaliacao do academico
        if (this.turmaController.getEntity() == null)
            System.out.println ("null turma");
        if (this.entity == null)
            System.out.println ("null academico");
        if (auxTurma == null)
            System.out.println ("null auxTurma");
        
        
        this.avaliacaoTurmaController.setEntity(new AvaliacaoTurma(this.turmaController.getEntity(), this.entity));
        this.avaliacaoTurmaController.insert();
        this.turmaController.getEntity().getAcademicos().add(this.entity);
        this.turmaController.update();
        this.entity = new Academico();
    }

    @Override
    public void remove() {
        this.repository = new AcademicoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.listOfEntities.clear();
        this.entity = new Academico();
    }

    @Override
    public void update() {
        this.repository = new AcademicoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new Academico();
        this.listOfEntities.clear();
    }

    public List<Academico> getList() {
        if (listOfEntities.isEmpty()) { 
            this.repository = new AcademicoRepository(ApplicationUtilies.catchEntityManager());
            listOfEntities = this.repository.getListOfElements();
        }
        return listOfEntities;
    }

    public Academico getLoggedAcademico() {
        if (ApplicationUtilies.isLogged() && this.loggedAcademico == null) {
            setLoggedCliente((Academico) ApplicationUtilies.getAuthenticadedUser());
        }
        return this.loggedAcademico;
    }
    
     public void editarPerfil() {                
        this.repository = new AcademicoRepository(ApplicationUtilies.catchEntityManager());        
        this.repository.update(this.loggedAcademico);
        this.entity = new Academico();        
        this.listOfEntities.clear();
    }

    public void setLoggedCliente(Academico loggedAcademico) {
        this.loggedAcademico = loggedAcademico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void changePassword(){
        this.loggedAcademico.setPassword(this.password);
        this.repository= new AcademicoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.loggedAcademico);
        this.password="";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Change password:", "success.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", true); 
    }   
    
    public Academico getUsuarioLogado () {
        return (Academico) ApplicationUtilies.getUsuarioLogado();
    }    
        
}
