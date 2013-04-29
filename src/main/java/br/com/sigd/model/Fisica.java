/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Classe refernte as caracteristicas de uma pessoa física
 * @author Klimaco
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="Fisica.findAll", query="SELECT a FROM Fisica a"),
    @NamedQuery (name="Fisica.getById", query="SELECT a FROM Fisica a WHERE a.id = :id")
})
@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.JOINED)

public class Fisica extends Pessoa implements Serializable {

    protected String cpf;
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date nascimento;

    /**
     * Método Contrutor

     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     */
    public Fisica() {
    }

    /**
     * Método getCpf
     * Utilize-o para retornar o CPF desta pessoa fisica
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return cpf ex.:600.2323.23-08
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Método setCpf
     * Utilize-o para definir o cpf da pessoa fisica
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param cpf  ex.: 600.232.232-08
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Método getNascimento
     * Utilize-o para retornar a data de nascimento de pessoa física
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return nascimento
     */
    public Date getNascimento() {
        return nascimento;
    }

    /**
     * Método setNascimento
     * Utilize-o para definir a data de nascimento de uma pessoa física
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param nascimento
     */
    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }
}
