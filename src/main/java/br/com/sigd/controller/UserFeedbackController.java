/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.UserFeedback;
import br.com.sigd.repository.UserFeedbackRepository;
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
 * @author FernandoChagas
 */
@Named("UserFeedbackController")
@SessionScoped
public class UserFeedbackController extends BasicBeanContent<UserFeedback, UserFeedbackRepository> implements Serializable {

    @Inject
    private UsuarioController usuario;
    @Inject
    private TurmaController turmaController;
    UserFeedback mostrar = new UserFeedback();
    private Long idTurma;
    

    public UserFeedbackController() {
        this.entity = new UserFeedback();
    }

    @Override
    public void insert() {
        this.repository = new UserFeedbackRepository(ApplicationUtilies.catchEntityManager());
        this.entity.setUsuario(usuario.getLoggedUsuario());
        this.entity.setDataSolicitacao(getToday());
        this.entity.setStatus("Pendente");        
        this.entity.setTurma(turmaController.getById(this.idTurma));

        this.repository.insert(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Feedback inserido com sucesso: ", ""));
        this.idTurma = new Long(0);
        this.entity = new UserFeedback();
        this.listOfEntities.clear();


    }

    @Override
    public void remove() {
        this.repository = new UserFeedbackRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new UserFeedback();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new UserFeedbackRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Feedback atualizado com sucesso: ", ""));
        this.entity = new UserFeedback();
        this.listOfEntities.clear();
    }

    public void updates(String page) {
        this.repository = new UserFeedbackRepository(ApplicationUtilies.catchEntityManager());
        //caso tenha setado um responsável a resposta é ignorada
        if (this.entity.getResponsavel() != null) {
            this.entity.setResposta("");
            this.entity.setStatus("Encaminhado");
            this.entity.setDataEncaminhamento(getToday());
        }
        //caso nao tenha sido encaminhado deve verificar se já foi respondido, caso sim seta o status antes de atualizar
        if (!this.entity.getResposta().equals("")) {
            this.entity.setStatus("Respondido");
            this.entity.setDataResposta(getToday());
        }
        this.repository.update(this.entity);
        this.entity = new UserFeedback();
        this.listOfEntities.clear();
    }

    public void responder(String page) {
        this.repository = new UserFeedbackRepository(ApplicationUtilies.catchEntityManager());
        //caso nao tenha sido encaminhado deve verificar se já foi respondido, caso sim seta o status antes de atualizar
        if (!this.entity.getResposta().equals("")) {
            this.entity.setStatus("Respondido");
            this.entity.setDataResposta(getToday());
        }
        this.repository.update(this.entity);
        this.entity = new UserFeedback();
        this.listOfEntities.clear();
    }

    public List<UserFeedback> getList() {
        this.repository = new UserFeedbackRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }

    public List<UserFeedback> getByUsuarioLogado() {
        this.repository = new UserFeedbackRepository(ApplicationUtilies.catchEntityManager());
        List<UserFeedback> listaUsuario = this.repository.getByUser(usuario.getLoggedUsuario().getId());
        return listaUsuario;
    }

    public List<UserFeedback> getByResponsavel() {
        this.repository = new UserFeedbackRepository(ApplicationUtilies.catchEntityManager());
        List<UserFeedback> listaUsuario = this.repository.getEncaminhadosResponsavel(usuario.getLoggedUsuario().getId());
        return listaUsuario;
    }

    public List<UserFeedback> getRespondidosResponsavel() {
        this.repository = new UserFeedbackRepository(ApplicationUtilies.catchEntityManager());
        List<UserFeedback> listaUsuario = this.repository.getRespondidosResponsavel(usuario.getLoggedUsuario().getId());
        return listaUsuario;
    }

    public List<UserFeedback> getPendentes() {
        this.repository = new UserFeedbackRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getPendentes();
    }

    public List<UserFeedback> getEncaminhados() {
        this.repository = new UserFeedbackRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getEncaminhados();
    }

    public List<UserFeedback> getRespondidos() {
        this.repository = new UserFeedbackRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getRespondidos();
    }    

    public Date getToday() {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    public UserFeedback getMostrar() {
        return mostrar;
    }

    public void setMostrar(UserFeedback mostrar) {
        this.mostrar = mostrar;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }
}
