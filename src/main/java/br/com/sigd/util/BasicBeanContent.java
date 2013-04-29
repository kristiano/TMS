/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marceloclaudios
 */
public abstract class BasicBeanContent <Class,RepositoryClass> {
    
    protected List<Class> listOfEntities=new ArrayList<Class>();
    //Todo Bean que utilizar essa classe generica, deve ter um construtor
    //vazio, que deverá instaciar as referências abaixo
    protected  RepositoryClass repository; //mudei para protected
    protected Class entity;

    public Class getEntity() {
        return entity;
    }

    public void setEntity(Class entity) {
        this.entity = entity;
    }

    public List<Class> getListOfEntities() {
        return listOfEntities;
    }

    public void setListOfEntities(List<Class> listOfEntities) {
        this.listOfEntities = listOfEntities;
    }
    
    //função teste
    public void addToList (Class entity) {
        this.listOfEntities.add(entity);
    }
    
    abstract public void insert();
    abstract public void remove();
    abstract public void update();
    
    
    /*Apenas para demonstração do CRUD*/
    protected boolean isNew=true;
    protected boolean isEdit=false;
    
        
    public boolean getIsNew(){
        return isNew;
    }
    
    public boolean getIsEdit(){
        return isEdit;
    }
    
    public void prepareEdit(){
        this.isNew=false;
        this.isEdit=true;
    }
    
    public void prepareNew(){
        this.isNew=true;
        this.isEdit=false;
    }
    /**/

    /**
     * @return the repository
     */
    public RepositoryClass getRepository() {
        return repository;
    }
}
