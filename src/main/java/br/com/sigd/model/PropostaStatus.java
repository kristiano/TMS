package br.com.sigd.model;

import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe que possui os status de Proposta e seus respectivos nomes. Possui id e
 * um nome.
 *
 * @author Fernando Chagas
 * @version 1.0
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="PropostaStatus.findAll", query="SELECT a FROM PropostaStatus a"),
    @NamedQuery (name="PropostaStatus.getById", query="SELECT a FROM PropostaStatus a WHERE a.id = :id")
})
public class PropostaStatus implements Serializable, BaseEntity {

    @Id
    @GeneratedValue    
    private Long id;
    private String nome;

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo status do
     * Proposta.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public PropostaStatus() {
    }

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo status do
     * Proposta.
     *
     * @author Fernando Chagas
     * @param id
     * @param nome
     * @since 1.0
     * @version 1.0
     */
    public PropostaStatus(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    /**
     * Método getId Retorna o id.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Método getNome Retorna o nome do status do Proposta.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método setNome Altera o nome do status do Proposta.
     *
     * @param nome nome do capítulo.
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PropostaStatus other = (PropostaStatus) obj;
        return true;
    }

}
