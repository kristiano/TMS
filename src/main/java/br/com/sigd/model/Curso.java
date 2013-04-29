package br.com.sigd.model;

import br.com.sigd.repository.AcademicoRepository;
import br.com.sigd.repository.CursoRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Classe que possui as informações referentes a um curso.
 *
 * @author Fernando Chagas
 * @version 1.0
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT a FROM Curso a"),
    @NamedQuery(name = "Curso.getById", query = "SELECT a FROM Curso a WHERE a.id = :id")
})
public class Curso implements Serializable, BaseEntity {

    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String codigo;
    private int totalHoras;
    @OneToMany(mappedBy = "curso")
    private List<Disciplina> disciplinas;
    @OneToMany(mappedBy = "curso",fetch= FetchType.EAGER)
    private List<Academico> academicos;    
   
    @ManyToMany(mappedBy="cursos")
    private List<Professor> professores;

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo curso.
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Curso() {
    }

    /**
     * Método contrutor Utilize-o para inicializar um objeto do tipo curso.
     *
     * @param id
     * @param nome
     * @param codigo código do curso.
     * @param totalHoras total de horas para conclusão do curso.
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Curso(long id, String nome, String codigo, int totalHoras) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.totalHoras = totalHoras;
    }

    /**
     * Método getCodigo Retorna o código do curso.
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
     * Método setCodigo Altera o código do curso.
     *
     * @param codigo código do curso.
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Método getNome Retorna o nome do curso.
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
     * Método setNome Altera o nome do curso.
     *
     * @param nome nome do curso.
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método getTotalHoras Retorna o total de horas do curso.
     *
     * @return totalHoras
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public int getTotalHoras() {
        return totalHoras;
    }

    /**
     * Método setTotalHoras Altera a quantidade total de horas no curso.
     *
     * @param totalHoras total de horas do curso.
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setTotalHoras(int totalHoras) {
        this.totalHoras = totalHoras;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Academico> getAcademicos() {
         try {
            return academicos;
        } catch (Exception e) {
           AcademicoRepository listaAcademico;
            listaAcademico = new AcademicoRepository(ApplicationUtilies.catchEntityManager());
            academicos = listaAcademico.getListOfElements();
            return academicos;
        }
        
    }

    public void setAcademicos(List<Academico> academicos) {
        this.academicos = academicos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }        

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Curso other = (Curso) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }        
}
