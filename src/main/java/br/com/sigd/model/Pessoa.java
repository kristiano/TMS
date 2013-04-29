package br.com.sigd.model;

import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Classe referente a informações de uma pessoa.
 *
 * @author Klimaco
 * @author Fernando Chagas
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="Pessoa.findAll", query="SELECT a FROM Pessoa a"),
    @NamedQuery (name="Pessoa.getById", query="SELECT a FROM Pessoa a WHERE a.id = :id")
})
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable, BaseEntity {

    @Id
    @GeneratedValue    
    protected Long id;
    protected String nome;
    @Email
    @NotEmpty(message="Digite um e-mail")
    protected String email;
    @OneToOne(cascade= CascadeType.ALL)
    protected Endereco endereco = new Endereco();
    @OneToOne(cascade= CascadeType.ALL)
    protected Telefone telefone = new Telefone();

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo Pessoa.
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     */
    public Pessoa() {
        endereco = new Endereco();
        telefone = new Telefone();
    }

    /**
     * Método getEmail Utilize-o para retornar o email.
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return email. Ex.: glaubos@sigd.com.br
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método setEmail Utilize-o definir um email.
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param email. Ex.: glaubos@sigd.com.br
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método getNome Utilize-o para retornar o nome de uma pessoa .
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return nome da pessoa. Ex.: Glaubos
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método getEndereco Utilize-o para retornar o endereço desta pessoa .
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * Método setEndereco Utilize-o para definir o endereço desta pessoa .
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param endereco
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * Método getTelefone Utilize-o para retornar o telefone desta Pessoa .
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return telefone
     */
    
    public Telefone getTelefone() {
        return telefone;
    }

    /**
     * Método setTelefone Utilize-o para definir o telefone desta pessoa .
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param telefone
     */
    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    /**
     * Método setNome Utilize-o para retornar o nome de uma pessoa .
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param nome . Ex.: Glaubos
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Long getId() {
        return id;
    }
}
