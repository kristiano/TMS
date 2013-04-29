package br.com.sigd.model;

import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;

/**
 * Classe que possui as informações referentes aos projetos.
 *
 * @author Fernando Chagas
 * @version 1.0
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Projeto.findAll", query = "SELECT a FROM Projeto a"),
    @NamedQuery(name = "Projeto.getById", query = "SELECT a FROM Projeto a WHERE a.id = :id"),
    @NamedQuery(name = "Projeto.getByProposta", query = "SELECT a FROM Projeto a WHERE a.proposta.id = :id"),})
public class Projeto implements Serializable, BaseEntity {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Proposta proposta;
    @ManyToOne
    private ProjetoStatus projetoStatus;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Capitulo> capitulos;
    private String urlArquivo;
    private String nomeArquivo;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<FeedBack> feedBack;
    private int cont;
    @OneToOne(mappedBy = "projeto")
    private Banca banca;

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo Projeto.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Projeto() {
        this.capitulos=new ArrayList<Capitulo>();
    }

    public List<FeedBack> getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(List<FeedBack> feedBack) {
        this.feedBack = feedBack;
    }

    /**
     * Método getId Retorna o id;
     *
     * @return id
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
        
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Método getProjetoStatus Retorna o status do projeto;
     *
     * @return projetoStatus
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public ProjetoStatus getProjetoStatus() {
        return projetoStatus;
    }

    /**
     * Método setProjetoStatus Altera o status do projeto.
     *
     * @param projetoStatus
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setProjetoStatus(ProjetoStatus projetoStatus) {
        this.projetoStatus = projetoStatus;
    }

    /**
     * Método getProposta Retorna a proposta do projeto;
     *
     * @return proposta
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Proposta getProposta() {
        return proposta;
    }

    /**
     * Método setProposta Altera a proposta do projeto.
     *
     * @param proposta
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
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

    public Banca getBanca() {
        return banca;
    }

    public void setBanca(Banca banca) {
        this.banca = banca;
    }        
}
