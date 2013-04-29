package br.com.sigd.model;

import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Classe que possui as informações referentes as turmas.
 *
 * @author Fernando Chagas
 * @version 1.0
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Turma.findAll", query = "SELECT a FROM Turma a"),
    @NamedQuery(name = "Turma.getById", query = "SELECT a FROM Turma a WHERE a.id = :id"),
    @NamedQuery(name = "Turma.getTurmasCoordenador", query = "SELECT t FROM Turma t JOIN t.coordenador c WHERE c.id = :id")
})
public class Turma implements Serializable, BaseEntity {

    @Id
    @GeneratedValue
    private long id;
    private String ano;
    private String codigoTurma;
    private int semestre;
    @ManyToOne
    private Disciplina disciplina;
    private boolean status;
    @ManyToMany(cascade = CascadeType.MERGE)
        @JoinTable(name = "professor_turmas",
        joinColumns =
        @JoinColumn(name = "turmas_id"),
        inverseJoinColumns =
        @JoinColumn(name = "coordenador_id"))
    private List<Professor> coordenador;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataProposta;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataProjeto;
    @ManyToMany(cascade = CascadeType.MERGE)
        @JoinTable(name = "turma_academicos",
        joinColumns =
        @JoinColumn(name = "turma_id"),
        inverseJoinColumns =
        @JoinColumn(name = "academicos_id"))
    private List<Academico> academicos;

    
    public Turma() {
        this.coordenador = new ArrayList<Professor>();
        this.academicos = new ArrayList<Academico>();
    }

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo Turma.
     *
     * @param id
     * @param ano
     * @param codigoTurma
     * @param semestre
     * @param disciplina
     * @param status
     * @param dataProposta
     * @param dataProjeto
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Turma(long id, String ano, String codigoTurma, int semestre, Disciplina disciplina, boolean status, Date dataProposta, Date dataProjeto) {
        this.id = id;
        this.ano = ano;
        this.codigoTurma = codigoTurma;
        this.semestre = semestre;
        this.disciplina = disciplina;
        this.status = status;
        this.dataProposta = dataProposta;
        this.dataProjeto = dataProjeto;
    }

    /**
     * Método getAno Retorna o ano da turma.
     *
     * @return ano
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public String getAno() {
        return ano;
    }

    /**
     * Método setAno Altera o ano da turma.
     *
     * @param ano
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * Método getCodigoTurma Retorna o código da turma.
     *
     * @return codigoTurma
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public String getCodigoTurma() {
        return codigoTurma;
    }

    /**
     * Método setCodigoTurma Altera o código da turma.
     *
     * @param codigoTurma
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    /**
     * Método getDataProjeto Retorna a data de início de entrega dos projetos na
     * turma.
     *
     * @return dataProjeto
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Date getDataProjeto() {
        return dataProjeto;
    }

    /**
     * Método setDataProjeto Altera a data de início de entrega das propostas na
     * turma.
     *
     * @param dataProjeto
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setDataProjeto(Date dataProjeto) {
        this.dataProjeto = dataProjeto;
    }

    /**
     * Método getDataProposta Retorna a data de início de entrega das propostas
     * na turma.
     *
     * @return curso
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Date getDataProposta() {
        return dataProposta;
    }

    /**
     * Método setDataProposta Altera a data de início de entrega das propostas
     * na turma.
     *
     * @param dataProposta
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setDataProposta(Date dataProposta) {
        this.dataProposta = dataProposta;
    }

    /**
     * Método getDisciplina Retorna a disciplina da turma
     *
     * @return disciplina
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Disciplina getDisciplina() {
        return disciplina;
    }

    /**
     * Método setDisciplina Altera a lista de disciplinas da turma.
     *
     * @param disciplina
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * Método getId Retorna o id.
     *
     * @return id
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Método getSemestre Retorna o semestre da turma.
     *
     * @return semestre
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public int getSemestre() {
        return semestre;
    }

    /**
     * Método setSemestre Altera o semestre da turma.
     *
     * @param semestre
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    /**
     * Método getStatus Retorna o status da turma.
     *
     * @return status
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Método setStatus Altera o status da turma.
     *
     * @param status
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Método getCoordenador Retorna a lista de coordenadores da turma.
     *
     * @return coordenador
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Professor> getCoordenador() {
        return coordenador;
    }

    /**
     * Método setCoordenaddor Altera a lista de coordenadores da turma.
     *
     * @param coordenador
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setCoordenador(List<Professor> coordenador) {
        this.coordenador = coordenador;
    }

    public List<Academico> getAcademicos() {
        return academicos;
    }

    public void setAcademicos(List<Academico> academicos) {
        this.academicos = academicos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Turma other = (Turma) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
