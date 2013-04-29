package br.com.sigd.model;

import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe que possui os status de projeto e seus respectivos nomes. Possui id e
 * um nome.
 *
 * @author Fernando Chagas
 * @version 1.0
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="ProjetoStatus.findAll", query="SELECT a FROM ProjetoStatus a"),
    @NamedQuery (name="ProjetoStatus.getById", query="SELECT a FROM ProjetoStatus a WHERE a.id = :id")
})
public class ProjetoStatus implements Serializable, BaseEntity {

    @Id
    @GeneratedValue    
    private Long id;
    private String nome;

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo status do
     * projeto.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public ProjetoStatus() {
    }

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo status do
     * projeto.
     *
     * @author Fernando Chagas
     * @param id
     * @param nome
     * @since 1.0
     * @version 1.0
     */
    public ProjetoStatus(long id, String nome) {
        this.id = id;
        this.nome = nome;
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
     * Método getNome Retorna o nome do status do projeto.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método setNome Altera o nome do status do projeto.
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
        final ProjetoStatus other = (ProjetoStatus) obj;
        return true;
    }

}
