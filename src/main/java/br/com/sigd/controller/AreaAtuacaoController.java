package br.com.sigd.controller;

import br.com.sigd.model.AreaAtuacao;
import br.com.sigd.repository.AreaAtuacaoRepository;
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
@Named("AreaAtuacaoController")
@SessionScoped
public class AreaAtuacaoController extends BasicBeanContent<AreaAtuacao, AreaAtuacaoRepository> implements Serializable {

    public AreaAtuacaoController() {
        this.entity = new AreaAtuacao();
    }    
    
    @Override
    public void insert() {        
        this.repository = new AreaAtuacaoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        this.entity = new AreaAtuacao();
        this.listOfEntities.clear();
    }

    @Override
    public void remove() {
        this.repository = new AreaAtuacaoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new AreaAtuacao();
        this.listOfEntities.clear();
    }

    @Override
    public void update() {
        this.repository = new AreaAtuacaoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new AreaAtuacao();
        this.listOfEntities.clear();
    }
    
    public List<AreaAtuacao> getList () {
         if (listOfEntities.isEmpty()) {
            this.repository = new AreaAtuacaoRepository(ApplicationUtilies.catchEntityManager());
            listOfEntities = this.repository.getListOfElements();
        }
        return listOfEntities;      
    }
    
}
