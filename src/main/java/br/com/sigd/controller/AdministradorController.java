/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.Administrador;
import br.com.sigd.repository.AdministradorRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.primefaces.context.RequestContext;

/**
 *
 * @author marceloclaudios
 */
@Named("AdministradorController")
@SessionScoped
public class AdministradorController extends BasicBeanContent<Administrador, AdministradorRepository> implements Serializable {

   private Administrador loggedAdministrador;
    private String password;

    public AdministradorController() {
        this.entity = new Administrador();
        this.loggedAdministrador = null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void changePassword() {
        this.loggedAdministrador.setPassword(this.password);
        this.repository = new AdministradorRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.loggedAdministrador);
        this.password = "";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar senha", "Senha alterada com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", true);
    }

    public Administrador getLoggedAdministrador() {
        if (ApplicationUtilies.isLogged() && this.loggedAdministrador == null) {
            setLoggedAdministrador((Administrador) ApplicationUtilies.getAuthenticadedUser());
        }
        return this.loggedAdministrador;
    }

    public void setLoggedAdministrador(Administrador loggedAdministrador) {
        this.loggedAdministrador = loggedAdministrador;
    }

    public void editarPerfil() {
        this.repository = new AdministradorRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.loggedAdministrador);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, loggedAdministrador.getNome() + "Profile: ", "success"));
        this.entity = new Administrador();
        this.listOfEntities.clear();
    }

    @Override
    public void insert() {
        EntityManager a = ApplicationUtilies.catchEntityManager();
        this.repository = new AdministradorRepository(a);
        this.entity.setDataCadastro(new Date());
        this.getRepository().insert(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success", entity.getNome()));
        this.entity = new Administrador();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update() {
        this.repository = new AdministradorRepository(ApplicationUtilies.catchEntityManager());
        System.out.println(this.entity);
        this.loggedAdministrador = this.entity;
        this.getRepository().update(this.loggedAdministrador);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success", loggedAdministrador.getNome()));
        this.entity = new Administrador();
        this.listOfEntities.clear();
    }

    public void reset() {
        this.password = "";
    }
}
