/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.controller;


import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author marceloclaudios
 */

@Named("UtilBean")
@RequestScoped
public class UtilController implements Serializable {
    
    public String loggedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    
}
