/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author marceloclaudios
 */
@Entity
@NamedQueries({
    @NamedQuery(name="PasswordRecovery.findAll", query = "SELECT a FROM PasswordRecovery a"),
    @NamedQuery(name="PasswordRecovery.getById", query = "SELECT a FROM PasswordRecovery a WHERE a.id = :id")
})
public class PasswordRecovery implements Serializable {
    @Id
    private String token;
    @ManyToOne
    private Usuario usuario;

    public PasswordRecovery(){}
    
    public PasswordRecovery(Usuario user){
        setUsuario(usuario);
    }
    
    public String getToken() {
        return token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void generateToken(){
        Date data = new Date();
        this.token=String.valueOf(this.usuario.hashCode() + data.getTime());
    }
}
