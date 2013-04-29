package br.com.sigd.controller;

import br.com.sigd.model.ProjetoStatus;
import br.com.sigd.repository.ProjetoStatusRepository;
import br.com.sigd.util.ApplicationUtilies;
import br.com.sigd.util.BasicBeanContent;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author FernandoChagas
 */
@Named("ProjetoStatusController")
@SessionScoped
public class ProjetoStatusController extends BasicBeanContent<ProjetoStatus, ProjetoStatusRepository> implements Serializable {

    public ProjetoStatusController() {
        this.entity = new ProjetoStatus();
    }

    @Override
    public void insert() {
        this.repository = new ProjetoStatusRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        this.entity = new ProjetoStatus();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {
        this.repository = new ProjetoStatusRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new ProjetoStatus();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new ProjetoStatusRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new ProjetoStatus();
        this.listOfEntities.clear();
    }

    public List<ProjetoStatus> getList() {
        if (listOfEntities.isEmpty()) {
            this.repository = new ProjetoStatusRepository(ApplicationUtilies.catchEntityManager());
            listOfEntities = this.repository.getListOfElements();
        }
        return listOfEntities;
    }

    public ProjetoStatus getById(Long id) {
        this.repository = new ProjetoStatusRepository(ApplicationUtilies.catchEntityManager());
        ProjetoStatus ps = this.repository.getById(id);
        return ps;
    }
}
