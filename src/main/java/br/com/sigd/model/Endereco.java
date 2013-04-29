package br.com.sigd.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe referente ao Endereço de uma Pessoa
 *
 * @author Klimaco
 * @author Fernando Chagas
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT a FROM Endereco a"),
    @NamedQuery(name = "Endereco.getById", query = "SELECT a FROM Endereco a WHERE a.id = :id")
})
public class Endereco implements Serializable {

    @Id
    @GeneratedValue
    protected long id;
    protected String logradouro;
    protected String bairro;
    protected String numero;
    protected String complemento;
    protected long cep;
    @ManyToOne(cascade = CascadeType.PERSIST)
    protected Municipio municipio;

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo Endereço.
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     */
    public Endereco() {
        this.municipio = new Municipio();
    }

    /**
     * Método getBairro Utilize-o para retornar o bairro deste endereço
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return bairro. ex.: Planalto
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Método setBairro Utilize-o para definir um bairro para este endereço
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param bairro . ex.: bacuri *
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Método getCep Utilize-o para retornar o cep deste endereço
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return cep. ex.: 00000
     */
    public long getCep() {
        return cep;
    }

    /**
     * Método setCep Utilize-o para definir um cep p/ este endereço
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param cep. ex.: 7777777
     */
    public void setCep(long cep) {
        this.cep = cep;
    }

    /**
     * Método getComplemento Utilize-o para retornar o complemento desde
     * endereço
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Método setComplemento Utilize-o para definir um complemento deste
     * endereço
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param complemento . ex.: ao lado da MV gás
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * Método getId Utilize-o para retornar a id
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
     * Método getLogradouro Utilize-o para retornar o logradouro deste endereço
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return logradouro. ex.: 104 Norte
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * Método setLogradouro Utilize-o para definir o logradouro deste endereço
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param logradouro. ex.: 107 norte
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * Método getMunicipio Utilize-o para retornar o objeto municipio deste
     * endereço
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return municipio. ex.: Municipio imperatriz;
     */
    public Municipio getMunicipio() {
        return municipio;
    }

    /**
     * Método setMUnicipio Utilize-o para definir um municipio para este
     * endereço
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param municipio. ex.: Municipio imperatriz.
     */
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    /**
     * Método getNumero Utilize-o para retornar o numero deste endereço
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return numero. ex.: 112B
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Método setNumero Utilize-o para definir o numero do endereço
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param numero. ex.: 223A
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
}
