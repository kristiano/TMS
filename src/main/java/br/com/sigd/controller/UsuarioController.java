/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;

import br.com.sigd.model.Usuario;
import br.com.sigd.repository.UsuarioRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Klimaco
 */
@Named("UsuarioController")
@SessionScoped
public class UsuarioController extends BasicBeanContent<Usuario, UsuarioRepository> implements Serializable {

    private Usuario loggedUsuario;
    private String password = "";
    private String confirmarPassword = "";

    public UsuarioController() {
        this.entity = new Usuario();
        this.loggedUsuario = null;
    }

    @Override
    public void insert() {
        this.repository = new UsuarioRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario criado com sucesso: ", entity.getNome()));
        this.entity = new Usuario();
    }

    @Override
    public void remove() {
        this.repository = new UsuarioRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Usuario();
    }

    @Override
    public void update() {
        this.repository = new UsuarioRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario atualizado com sucesso: ", entity.getNome()));
        this.entity = new Usuario();
    }

    public Usuario getLoggedUsuario() {
        if (ApplicationUtilies.isLogged() && this.loggedUsuario == null) {
            setLoggedUsuario((Usuario) ApplicationUtilies.getAuthenticadedUser());
        }
        return this.loggedUsuario;
    }

    public void setLoggedUsuario(Usuario loggedUsuario) {
        this.loggedUsuario = loggedUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    public String getConfirmarPassword() {
        return confirmarPassword;
    }

    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }

    public String changePassword() throws InterruptedException {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSeverity(facesMessage.SEVERITY_ERROR);
        if (this.password == null ? this.confirmarPassword != null : !this.password.equals(this.confirmarPassword)) {

            facesMessage.setDetail("As senhas não correspondem");
            context.addMessage("Erro", facesMessage);

            return null;
        } else {
            if (this.password.length() < 6) {
                facesMessage.setDetail("Digite no mínimo 6 caracteres");
                context.addMessage("Erro", facesMessage);
                return null;
            } else {

                this.loggedUsuario.setPassword(this.password);
                this.repository = new UsuarioRepository(ApplicationUtilies.catchEntityManager());
                this.repository.update(this.loggedUsuario);
                this.password = "";
                this.confirmarPassword = "";

                return "pretty:sucesso";
            }
        }


    }

    public String goToLogin() {
        return "pretty:login";
    }

    public void idleListener() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Sua sessão será fechada", "Você está ausente a 15 min"));
        //invalidate session
    }

    public void activeListener() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Seja bem-vindo de volta", "Demorou ein?!"));
    }
    
    public Usuario getUsuarioLogado () {
        return (Usuario) ApplicationUtilies.getUsuarioLogado();
    }
}
