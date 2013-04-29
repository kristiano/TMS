/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.repository;

import br.com.sigd.interfaces.DefaultQueries;
import br.com.sigd.model.Professor;
import br.com.sigd.model.Turma;
import br.com.sigd.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Klimaco
 */
public class ProfessorRepository extends GenericRepository<Professor> implements DefaultQueries<Professor, Long> {

    public ProfessorRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Professor getById(Long id) {
        Query query = this.entityManager.createNamedQuery("Professor.getById");
        query.setParameter("id", id);
        return getSingleResult(query);
    }

    @Override
    public List<Professor> getListOfElements() {
        Query query = this.entityManager.createNamedQuery("Professor.findAll");
        return query.getResultList();
    }

    public List<Turma> getTurmasPorProfessor(Long id) {
        Query query = this.entityManager.createQuery("SELECT turmas FROM Professor a WHERE a.id = :id");
        return query.getResultList();
    }

    public Professor getProfessorComCursos(Long umIdProfessor) {
        Query query = this.entityManager.createQuery("Select professor from Professor professor join professor.cursos where professor.id = :umIdProfessor");
        query.setParameter("umIdProfessor", umIdProfessor);
        return getSingleResult(query);
    }
        
}
