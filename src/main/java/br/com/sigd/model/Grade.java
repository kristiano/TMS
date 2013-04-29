package br.com.sigd.model;

import Enum.GradeType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Fernando Chagas
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Grade.findAll", query = "SELECT a FROM Grade a"),
    @NamedQuery(name = "Grade.getById", query = "SELECT a FROM Grade a WHERE a.id = :id")
})
public class Grade implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(value= EnumType.STRING)    
    private GradeType tipo;
    private Long valor; 
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataDeCriacao;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataDeAtualizacao;
    @Length(min=2,max=50000)
    private String conteudo;
    private Long idAvaliador;
    private Long idAvaliado;
    private Long idTipoAvaliado;

    public Grade() {
    }

    public Long getId() {
        return id;
    }

    public GradeType getTipo() {
        return tipo;
    }

    public void setTipo(GradeType tipo) {
        this.tipo = tipo;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }    

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Long getIdAvaliador() {
        return idAvaliador;
    }

    public void setIdAvaliador(Long idAvaliador) {
        this.idAvaliador = idAvaliador;
    }

    public Long getIdAvaliado() {
        return idAvaliado;
    }

    public void setIdAvaliado(Long idAvaliado) {
        this.idAvaliado = idAvaliado;
    }        

    public Date getDataDeAtualizacao() {
        return dataDeAtualizacao;
    }

    public void setDataDeAtualizacao(Date dataDeAtualizacao) {
        this.dataDeAtualizacao = dataDeAtualizacao;
    }        

    public Long getIdTipoAvaliado() {
        return idTipoAvaliado;
    }

    public void setIdTipoAvaliado(Long idTipoAvaliado) {
        this.idTipoAvaliado = idTipoAvaliado;
    }        
}
