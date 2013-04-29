/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.model;

import br.com.sigd.util.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Classe referente ao professor
 *
 * @author Klimaco
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Professor.findAll", query = "SELECT a FROM Professor a"),
    @NamedQuery(name = "Professor.getById", query = "SELECT a FROM Professor a WHERE a.id = :id")
})
@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Professor extends Usuario implements Serializable, BaseEntity {

    private String matricula;
    private int quantidadeOrientando;    
    
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "professor_cursos", 
            joinColumns =@JoinColumn(name = "professores_id"),
            inverseJoinColumns =@JoinColumn(name = "cursos_id"))
    private List<Curso> cursos = new ArrayList<Curso>();

    /** que é?
   
     * Método construtor
     curso funciona, turmas não, olha aqui, tá escrito igual os relacionamentos
     * @author Glaubos Climaco 
     * @since 1.0
     * @version 1.0
     */
    public Professor() {
        this.permissao = "PROFESSOR";
    }

    /**
     * Método getMatricula Utilize-o para retornar a matricula do professor
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return matricula . ex.: 123456
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Método setMatricula Utilize-o para retornar a matricula do professor
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param matricula. ex.: 12345678
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Método getMatricula Utilize-o para retornar a quantidade de orientandos
     * desse professor
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @return quantidadeOrientando. ex.: 2
     */
    public int getQuantidadeOrientando() {
        return quantidadeOrientando;
    }

    /**
     * Método setQuantidadeOrientando Utilize-o para definir a quantidade de
     * orientando deste professor
     *
     * @author Glaubos Climaco
     * @since 1.0
     * @version 1.0
     * @param quantidadeOrientando ex.: 2
     */
    public void setQuantidadeOrientando(int quantidadeOrientando) {
        this.quantidadeOrientando = quantidadeOrientando;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Professor clone = new Professor();
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
        clone.setMatricula(matricula);
        clone.setQuantidadeOrientando(quantidadeOrientando);
        return clone;
    }
    
    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
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
        final Professor other = (Professor) obj;
        return true;
    }
}
