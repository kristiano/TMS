package br.com.sigd.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe referente as informações de um Municipio que está ligado a um Estado
 *
 * @author Klimaco
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="Municipio.findAll", query="SELECT a FROM Municipio a"),
    @NamedQuery (name="Municipio.getById", query="SELECT a FROM Municipio a WHERE a.id = :id")
})
public class Municipio implements Serializable {

    @Id
    @GeneratedValue    
    private long id;
    private String nome;
    @ManyToOne(cascade= CascadeType.PERSIST)
    private Estado estado;

    /**
     * Método construtor Utilize-o para contruit um objeto do tipo Muunicipio.
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     */
    public Municipio() {
        this.estado = new Estado();
    }

    /**
     * Método getEstado Utilize-o para retornar um estado deste Municipio.
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return estado . Ex.: objeto do tipo estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Método setEstado Utilize-o para definir um objeto do tipo estado .
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param estado ex.: Estado maranhao;
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Método getId Utilize-o para retornar a id deste Municipio.
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Método getNome Utilize-o para retornar o nome do municipio .
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return nome. ex.: Imperatriz
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método setNome Utilize-o para definir o nome de um municipio.
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param nome. ex.: Imperatriz
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
