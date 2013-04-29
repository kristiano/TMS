package br.com.sigd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;

/**
 * Classe que possui informações de um capitulo. Possui id e um nome.
 *
 * @author Fernando Chagas
 * @version 1.0
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Capitulo.findAll", query = "SELECT a FROM Capitulo a"),
    @NamedQuery(name = "Capitulo.getById", query = "SELECT a FROM Capitulo a WHERE a.id = :id"),
    @NamedQuery(name = "Capitulo.getByProjeto", query = "SELECT a FROM Capitulo a WHERE a.projeto.id = :id")
})
public class Capitulo implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String urlArquivo;
    private String nomeArquivo;
    private String status;
    @ManyToOne
    private Projeto projeto;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<FeedBack> feedBack;
    private int cont;
    

    /**
     * Método construtor Utilize-o para inicializar um objeto do tipo Capitulo.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Capitulo() {
        projeto = new Projeto();
        feedBack = new ArrayList<FeedBack>();
    }

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo Capitulo.
     *
     * @author Fernando Chagas
     * @param nome
     * @since 1.0
     * @version 1.0
     */
    public Capitulo(String nome) {
        this.nome = nome;
    }

    public List<FeedBack> getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(List<FeedBack> feedBack) {
        this.feedBack = feedBack;
    }

    /**
     * Método getId Retorna o id.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public long getId() {
        return id;
    }

    /**
     * Método getNome Retorna o nome do capítulo.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método setNome Altera o nome do capítulo
     *
     * @param nome nome do capítulo.
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
