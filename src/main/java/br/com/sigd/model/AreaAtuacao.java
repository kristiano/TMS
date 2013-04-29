package br.com.sigd.model;

import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe que possui as informações referentes a areas de atuação.
 *
 * @author Fernando Chagas
 * @version 1.0
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="AreaAtuacao.findAll", query="SELECT a FROM AreaAtuacao a"),
    @NamedQuery (name="AreaAtuacao.getById", query="SELECT a FROM AreaAtuacao a WHERE a.id = :id")
})
public class AreaAtuacao implements Serializable, BaseEntity {

    @Id
    @GeneratedValue    
    private long id;
    private String nome;
    private String codigoArea;    
    @ManyToOne
    private AreaAtuacao pai;

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo Área de
     * Atuação.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public AreaAtuacao() {        
    }

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo Área de
     * Atuação.
     *
     * @param id
     * @param nome
     * @param codigoArea
     * @param pai
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public AreaAtuacao(long id, String nome, String codigoArea, AreaAtuacao pai) {
        this.id = id;
        this.nome = nome;
        this.codigoArea = codigoArea;
        this.pai = pai;
    }

    /**
     * Método getCodigoArea Retorna o código da Área de atuação.
     *
     * @return codigoArea
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public String getCodigoArea() {
        return codigoArea;
    }

    /**
     * Método setCodigoArea Altera o código da Área de atuação.
     *
     * @param codigoArea código do curso.
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
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
     * Método getNome Retorna o nome da Área de Atuação.
     *
     * @return nome
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método setNome Altera o nome da Área de Atuação.
     *
     * @param nome nome da área de atuação.
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método getPai Retorna o pai da Área de Atuação.
     *
     * @return pai
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public AreaAtuacao getPai() {
        return pai;
    }

    /**
     * Método setPai Altera o pai da Área de Atuação.
     *
     * @param pai
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setPai(AreaAtuacao pai) {
        this.pai = pai;
    }

}
