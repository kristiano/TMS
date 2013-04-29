package br.com.sigd.model;

import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;

/**
 * Classe que possui as informações referentes as disciplinas.
 *
 * @author Fernando Chagas
 * @version 1.0
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="Disciplina.findAll", query="SELECT a FROM Disciplina a"),
    @NamedQuery (name="Disciplina.getById", query="SELECT a FROM Disciplina a WHERE a.id = :id")
})
public class Disciplina implements Serializable, BaseEntity {

    @Id
    @GeneratedValue    
    private Long id;
    private String codigo;
    @Length(min=5, max=20000)
    private String nome;
    private String ementa;
    private int cargaHoraria;    
    @ManyToOne(cascade= CascadeType.REFRESH)
    private Curso curso;
    //falta professor

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo Disciplina.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Disciplina() {
    }

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo Disciplina.
     *
     * @param id
     * @param codigo
     * @param nome
     * @param ementa
     * @param cargaHoraria
     * @param curso
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Disciplina(long id, String codigo, String nome, String ementa, int cargaHoraria, Curso curso) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.ementa = ementa;
        this.cargaHoraria = cargaHoraria;
        this.curso = curso;
    }

    /**
     * Método getCargaHorario Retorna a carga horária.
     *
     * @return cargaHoraria
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * Método setCargaHoraria Altera a carga horária.
     *
     * @param cargaHoraria total da carga horária
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * Método getCodigo Retorna o código da disciplina.
     *
     * @return codigo
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Método setCodigo Altera o código da disciplina.
     *
     * @param codigo
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Método getCurso Retorna o curso da disciplina.
     *
     * @return curso
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * Método setCurso Altera o curso da disciplina.
     *
     * @param curso
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * Método getEmenta Retorna a ementa da disciplina.
     *
     * @return ementa
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public String getEmenta() {
        return ementa;
    }

    /**
     * Método setEmenta Altera a ementa da disciplina.
     *
     * @param ementa
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    /**
     * Método getId Retorna o id.
     *
     * @return id
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */    
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Método getNome Retorna o nome da disciplina.
     *
     * @return nome
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método setNome Altera o nome da disciplina.
     *
     * @param nome
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Disciplina other = (Disciplina) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }  
    
    
         
}
