package br.com.sigd.controller;

import br.com.sigd.model.PropostaStatus;
import br.com.sigd.repository.PropostaStatusRepository;
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
@Named("PropostaStatusController")
@SessionScoped
public class PropostaStatusController extends BasicBeanContent<PropostaStatus, PropostaStatusRepository> implements Serializable {

    public PropostaStatusController() {
        this.entity = new PropostaStatus();
    }

    @Override
    public void insert() {
        this.repository = new PropostaStatusRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        this.entity = new PropostaStatus();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {
        this.repository = new PropostaStatusRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new PropostaStatus();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new PropostaStatusRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new PropostaStatus();
        this.listOfEntities.clear();
    }

    public List<PropostaStatus> getList() {
        this.repository = new PropostaStatusRepository(ApplicationUtilies.catchEntityManager());
        listOfEntities = this.repository.getListOfElements();
        return listOfEntities;
    }

    public PropostaStatus getById(Long id) {
        this.repository = new PropostaStatusRepository(ApplicationUtilies.catchEntityManager());
        PropostaStatus ps = this.repository.getById(id);
        return ps;
    }
}
