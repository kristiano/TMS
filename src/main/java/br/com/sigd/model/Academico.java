package br.com.sigd.model;

import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Classe que possui as informações referentes a um acadêmico.
 *
 * @author Fernando Chagas
 * @version 1.0
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="Academico.findAll", query="SELECT a FROM Academico a"),
    @NamedQuery (name="Academico.getById", query="SELECT a FROM Academico a WHERE a.id = :id")
})
@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Academico extends Usuario implements Serializable,BaseEntity {
                  
    @OneToMany(mappedBy = "academico")
    private List<Proposta> propostas;
    private String matricula;         
    @ManyToOne
    private Curso curso;

    /**
     * Método contrutor Utilize-o para inicializar um objeto do Acadêmico. 
     *
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public Academico() {
        this.propostas = new ArrayList<Proposta>();
        this.permissao = "ACADEMICO";//senha padrão acad        
        this.setPassword("academico");
        this.setDataCadastro(new Date());
    }

    /**
     * Método getMatricula Retorna a matrícula do acadêmico.
     *
     * @return matricula Ex: 2009218312
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Método setMatricula Altera o número de matrícula do acadêmico.
     *
     * @param matricula
     * @author Fernando Chagas
     * @since 1.0
     * @version 1.0
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Academico clone = new Academico();
        clone.setCpf(cpf);
        clone.setDataCadastro(dataCadastro);
        clone.setEmail(email);
        clone.setEnabled(enabled);
        clone.setEndereco(endereco);
        clone.setId(id);
        clone.setMatricula(matricula);
        clone.setNascimento(nascimento);
        clone.setNome(nome);
        clone.setPassword(password);
        clone.setPermissao(permissao);
        clone.setTelefone(telefone);
        clone.setUsername(username);
        return clone;
    }

    public List<Proposta> getPropostas() {
        return propostas;
    }

    public void setPropostas(List<Proposta> propostas) {
        this.propostas = propostas;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }        
}
