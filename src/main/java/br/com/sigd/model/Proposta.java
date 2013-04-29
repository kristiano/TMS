package br.com.sigd.model;

import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;

/**
 * Classe que possui as informações referentes as propostas.
 *
 * @author Fernando Chagas
 * @version 1.0
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Proposta.findAll", query = "SELECT a FROM Proposta a"),
    @NamedQuery(name = "Proposta.getById", query = "SELECT a FROM Proposta a WHERE a.id = :id"),
    @NamedQuery(name = "Proposta.getByProfessor", query = "SELECT a FROM Proposta a JOIN a.professor p WHERE p.id = :id"),
    @NamedQuery(name = "Proposta.getByAcademico", query = "SELECT t FROM Proposta t JOIN t.academico c WHERE c.id = :id"),
    @NamedQuery(name = "Proposta.getAprovadasProfessor", query = "SELECT a FROM Proposta a JOIN a.professor p WHERE a.propostaStatus = 2 and p.id = :id"), //pega as aprovadas de um professor    
    @NamedQuery(name = "Proposta.getAprovadasAcademico", query = "SELECT a FROM Proposta a JOIN a.academico p WHERE a.propostaStatus = 2 and p.id = :id"), //pega as aprovadas de um academico
    @NamedQuery(name = "Proposta.getAprovadaTurma", query = "SELECT a FROM Proposta a WHERE a.propostaStatus = 2 and a.turma.id = :id"), //caso o status de aprovada seja 2
    @NamedQuery(name = "Proposta.getPendenteTurma", query = "SELECT a FROM Proposta a WHERE a.propostaStatus != 2 and a.turma.id = :id") //caso o status de aprovada seja 2    
})
public class Proposta implements Serializable, BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    private String titulo;
    private String tema;
    @ManyToMany    
    private List<AreaAtuacao> areaAtuacao;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<Professor> professor;
    @ManyToOne
    private Turma turma;
    @ManyToOne
    private PropostaStatus propostaStatus;
    private String urlArquivo;
    private String nomeArquivo = null;
    @ManyToOne(cascade= CascadeType.REFRESH)
    private Academico academico;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<FeedBack> feedBack;
    private int cont;

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo Proposta.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Proposta() {
        propostaStatus = new PropostaStatus();
        areaAtuacao = new ArrayList<AreaAtuacao>();
        professor = new ArrayList<Professor>();
        academico = new Academico();
        feedBack = new ArrayList<FeedBack>();
    }

    public List<FeedBack> getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(List<FeedBack> feedBack) {
        this.feedBack = feedBack;
    }

    /**
     * Método getAreaAtuacao Retorna as áreas de atuação da proposta.
     *
     * @return areaAtuacao
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<AreaAtuacao> getAreaAtuacao() {
        return areaAtuacao;
    }

    /**
     * Método setAreaAtuacao Altera as áreas de atuação da proposta.
     *
     * @param areaAtuacao
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setAreaAtuacao(List<AreaAtuacao> areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    /**
     * Método getId Retorna o id;
     *
     * @return id
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Long getId() {
        return id;
    }

    /**
     * Método getPropostaStatus Retorna o status da proposta.
     *
     * @return propostaStatus
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public PropostaStatus getPropostaStatus() {
        return propostaStatus;
    }

    /**
     * Método setPropostaStatus Altera o status da proposta.
     *
     * @param propostaStatus
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setPropostaStatus(PropostaStatus propostaStatus) {
        this.propostaStatus = propostaStatus;
    }

    /**
     * Método getTema Retorna o tema da proposta.
     *
     * @return tema
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public String getTema() {
        return tema;
    }

    /**
     * Método setTema Altera o tema da proposta.
     *
     * @param tema
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * Método getTitulo Retorna o título da proposta.
     *
     * @return titulo
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Método setTitulo Altera o titulo da proposta.
     *
     * @param titulo
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Método getTurma Retorna a turma da proposta.
     *
     * @return ano
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Turma getTurma() {
        return turma;
    }

    /**
     * Método setTurma Altera a turma da proposta. Observação: A proposta possui
     * uma turma porque o aluno está em uma ou mais turmas, logo não é possível
     * saber a turma de sua proposta.
     *
     * @param turma
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    /**
     * Método getProfessor Retorna a lista de professores da proposta.
     *
     * @return professor
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public List<Professor> getProfessor() {
        return professor;
    }

    /**
     * Método setProfessor Altera a lista de professores da proposta.
     *
     * @param professor
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setProfessor(List<Professor> professor) {
        this.professor = professor;
    }

    public Academico getAcademico() {
        return academico;
    }

    public void setAcademico(Academico academico) {
        this.academico = academico;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getUrlArquivo() {
        return urlArquivo;
    }

    public void setUrlArquivo(String urlArquivo) {
        this.urlArquivo = urlArquivo;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }        
}
