/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author marceloclaudios
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Administrador extends Usuario {

    public Administrador() {
        super();
        this.permissao = "ADMINISTRADOR";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Administrador clone = new Administrador();
        clone.setCpf(cpf);
        clone.setDataCadastro(dataCadastro);
        clone.setEmail(email);
        clone.setEnabled(enabled);
        clone.setEndereco(endereco);
        clone.setId(id);
        clone.setNascimento(nascimento);
        clone.setNome(nome);
        clone.setPassword(password);
        clone.setPermissao(permissao);
        clone.setTelefone(telefone);
        clone.setUsername(username);
        return clone;
    }
}
