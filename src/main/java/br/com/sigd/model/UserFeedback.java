/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.model;

import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author FernandoChagas
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "UserFeedback.findAll", query = "SELECT a FROM UserFeedback a"),
    @NamedQuery(name = "UserFeedback.getById", query = "SELECT a FROM UserFeedback a WHERE a.id = :id"),
    @NamedQuery(name = "UserFeedback.getByUser", query = "SELECT a FROM UserFeedback a WHERE a.usuario.id = :id")        
})
public class UserFeedback implements Serializable, BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Situacoes situacoes;
    @ManyToOne
    private ProblemasAssociados problemasAssociados;
    @ManyToOne
    private Turma turma;
    @ManyToOne
    private Usuario usuario;
    private String descricao;
    private String status;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataSolicitacao;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataEncaminhamento;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataResposta;
    @ManyToOne
    private Usuario responsavel;
    private String resposta;

    public UserFeedback() {
        situacoes = new Situacoes();
        problemasAssociados = new ProblemasAssociados();
        turma = new Turma();
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ProblemasAssociados getProblemasAssociados() {
        return problemasAssociados;
    }

    public void setProblemasAssociados(ProblemasAssociados problemasAssociados) {
        this.problemasAssociados = problemasAssociados;
    }

    public Situacoes getSituacoes() {
        return situacoes;
    }

    public void setSituacoes(Situacoes situacoes) {
        this.situacoes = situacoes;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataEncaminhamento() {
        return dataEncaminhamento;
    }

    public void setDataEncaminhamento(Date dataEncaminhamento) {
        this.dataEncaminhamento = dataEncaminhamento;
    }

    public Date getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(Date dataResposta) {
        this.dataResposta = dataResposta;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }        
    
    
        
}
