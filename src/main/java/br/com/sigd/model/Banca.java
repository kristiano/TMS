package br.com.sigd.model;

import Enum.BancaType;
import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Classe referente a banca de apresentação
 *
 * @author Klimaco
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="Banca.findAll", query="SELECT a FROM Banca a"),
    @NamedQuery (name="Banca.getById", query="SELECT a FROM Banca a WHERE a.id = :id")
})
public class Banca implements Serializable, BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDefesa;            
    @OneToOne
    private Projeto projeto;    
    @ManyToMany
    private List<Professor> avaliadores;
    @Enumerated(value= EnumType.STRING)   
    private BancaType defesa;
    private Double notaMedia;

    /**
     * Método construtor Utilize-o
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     */
    public Banca() {
        this.projeto = new Projeto();
        this.avaliadores = new ArrayList<Professor>();
    }

    /**
     * Método getAvaliadores Utilize-o para retornar uma lista de avaliadores
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return avaliadores
     */
    public List<Professor> getAvaliadores() {
        return avaliadores;
    }

    /**
     * Método setAvaliadores Utilize-o para definir uma lista de avaliadores
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param avaliadores
     */
    public void setAvaliadores(List<Professor> avaliadores) {
        this.avaliadores = avaliadores;
    }

    /**
     * Método getProjeto Utilize-o para retornar o projeto da banca
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return projeto
     */
    public Projeto getProjeto() {
        return projeto;
    }

    /**
     * Método setProjeto Utilize-o para definir o projeto da banca
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param projeto
     */
    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
    /**
     * Método getDataDefesa Utilize-o para retornar a data da defesa do projeto.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     * @return dataDefesa
     */

    public Date getDataDefesa() {
        return dataDefesa;
    }
    
        /**
     * Método setDataDefesa Utilize-o para definir a data da defesa do projeto.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     * @param dataDefesa 
     */

    public void setDataDefesa(Date dataDefesa) {
        this.dataDefesa = dataDefesa;
    }        

    public BancaType getDefesa() {
        return defesa;
    }

    public void setDefesa(BancaType defesa) {
        this.defesa = defesa;
    }

    public Double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }        

    @Override
    public Long getId() {
        return this.id;
    }
}

