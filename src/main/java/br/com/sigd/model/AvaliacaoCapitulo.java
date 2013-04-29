/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.model;

import Enum.EstadoAvaliacao;
import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author Adm
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "AvaliacaoCapitulo.findAll", query = "SELECT a FROM AvaliacaoCapitulo a"),
    @NamedQuery(name = "AvaliacaoCapitulo.getById", query = "SELECT a FROM AvaliacaoCapitulo a WHERE a.id = :id")
})
public class AvaliacaoCapitulo implements Serializable, BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Capitulo capitulo;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Turma turma;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Academico academico;
    private Double nota;
    @Enumerated(value = EnumType.STRING)
    private EstadoAvaliacao estadoAvaliacao;

    public AvaliacaoCapitulo() {
        this.data = new Date();
        this.estadoAvaliacao = this.estadoAvaliacao.LIBERADO;
    }

    public AvaliacaoCapitulo(Capitulo capitulo, Turma turma, Academico academico) {
        this.capitulo = capitulo;
        this.turma = turma;
        this.academico = academico;
        this.data = new Date();
        this.estadoAvaliacao = this.estadoAvaliacao.LIBERADO;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Academico getAcademico() {
        return academico;
    }

    public void setAcademico(Academico academico) {
        this.academico = academico;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public EstadoAvaliacao getEstadoAvaliacao() {
        return estadoAvaliacao;
    }

    public void setEstadoAvaliacao(EstadoAvaliacao estadoAvaliacao) {
        this.estadoAvaliacao = estadoAvaliacao;
    }

    public Capitulo getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(Capitulo capitulo) {
        this.capitulo = capitulo;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final AvaliacaoCapitulo other = (AvaliacaoCapitulo) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
