/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.model;

import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author FernandoChagas
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="ProblemasAssociados.findAll", query="SELECT a FROM ProblemasAssociados a"),
    @NamedQuery (name="ProblemasAssociados.getById", query="SELECT a FROM ProblemasAssociados a WHERE a.id = :id")
})
public class ProblemasAssociados  implements Serializable, BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;

    public ProblemasAssociados() {
    }        

    @Override
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }        

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProblemasAssociados other = (ProblemasAssociados) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }        
}
