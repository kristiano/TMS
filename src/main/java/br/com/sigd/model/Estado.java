package br.com.sigd.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe referente as informações de um Estado que pode possuir vários
 * Municípios
 *
 * @author Klimaco
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="Estado.findAll", query="SELECT a FROM Estado a"),
    @NamedQuery (name="Estado.getById", query="SELECT a FROM Estado a WHERE a.id = :id")
})
public class Estado implements Serializable {

    @Id
    @GeneratedValue    
    private long id;
    private String sigla;
    private String nome;

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo Estado.
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     */
    public Estado() {
    }

    /**
     * Método getId Utilize-o para retornar uma id.
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return id. ex.: 1
     */
    public long getId() {
        return id;
    }

    /**
     * Método getNome Utilize-o para retornar um nome .
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return nome. ex.: Glaubos
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método setNome Utilize-o para definir um nome .
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param nome. ex.: Glaubos
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método getSigla Utilize-o para retornar uma sigla.
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return sigla. ex.: MA
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * Método setSigla Utilize-o para definir uma silga.
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param sigla. ex.: MA
     */
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
