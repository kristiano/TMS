package br.com.sigd.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Klimaco
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="Telefone.findAll", query="SELECT a FROM Telefone a"),
    @NamedQuery (name="Telefone.getById", query="SELECT a FROM Telefone a WHERE a.id = :id")
})
public class Telefone implements Serializable {

    @Id
    @GeneratedValue    
    protected long id;
    protected String numero;

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo Telefone.
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     */
    public Telefone() {
    }

    /**
     * Método getId Utilize-o para retornar a id. Ex.: 1
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
     * Método getNumero Utilize-o para retornar o numero. Ex.: 3524-1234
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Método setNumero Utilize-o para definir o numero. Ex.: 23434-3434
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
}
