package br.com.sigd.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Classe referente a uma pessoa juridica.
 * OBS :  PARA IMPLEMENTAÇÕES FUTURAS !
 * @deprecated
 * @author Klimaco
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="Juridica.findAll", query="SELECT a FROM Juridica a"),
    @NamedQuery (name="Juridica.getById", query="SELECT a FROM Juridica a WHERE a.id = :id")
})
@PrimaryKeyJoinColumn(name = "id")
public class Juridica extends Pessoa {

    String cnpj;
    String inscricaoStadual;
    String razaoSocial;

    /**
     * Método construtor
     * Utilize-o para construir um objeto de pessoa juridica
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     */
    public Juridica() {
    }

    /**
     * Método getCnpj
     * Utilize-o para retornar o cnpj da juridica
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return cnpj ex.:2378344
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Método setCnpj
     * Utilize-o para definir o cnpj da juridica
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param cnpj ex.:7298734
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Método getInscricaoStatudal
     * Utilize-o para retornar a inscricao statudal
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return
     */
    public String getInscricaoStadual() {
        return inscricaoStadual;
    }

    /**
     * Método setInscricaoStatudal
     * Utilize-o para  definir a inscricao stadual
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param inscricaoStadual
     */
    public void setInscricaoStadual(String inscricaoStadual) {
        this.inscricaoStadual = inscricaoStadual;
    }

    /**
     * Método getRazaoSocial
     * Utilize-o para retornar a razao social
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return razaoSocial
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * Método setRazaoSocial
     * Utilize-o para definir a razao social
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param razaoSocial
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}
