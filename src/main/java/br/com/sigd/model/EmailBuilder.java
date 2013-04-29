/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.model;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
/**
 *
 * @author marceloclaudios
 */
public class EmailBuilder {
    
    private Email email;
    
    public EmailBuilder(){
        email = new SimpleEmail();
    }
    
    public void configure(String destinatario) throws EmailException{
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("fndchagas@gmail.com", "senha"));
        email.setTLS(true);
        email.setFrom("fndchagas@gmail.com");
        email.addTo(destinatario);
    }
    
    
    public void setAsRegisterMessage(String link){
    }
    
    public void setAsRecoveryPassMessage(String token) throws EmailException{
        email.setSubject("SIGD - Recuperação de Senha");
        email.setMsg("localhost:8080/sigd/reset-password/"+token);
    }
    
    public void setAsContact(String mensagem,String assunto,String email,String nome) throws EmailException{
        this.email.setSubject(assunto);
        this.email.setMsg(mensagem+"/n/b"+nome);
        this.email.setFrom(email);
        
    }
    
    public void send() throws EmailException{
        email.send();
    }
    
}
