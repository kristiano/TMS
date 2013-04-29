/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.interfaces;

import java.util.List;

/**
 *
 * @author marceloclaudios
 */
public interface DefaultQueries<Class, T> {
    
    public Class getById(T id);
    public List<Class> getListOfElements();    
    
}
