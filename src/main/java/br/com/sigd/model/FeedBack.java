/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author FernandoChagas
 */
@Entity
@NamedQueries ({
    @NamedQuery (name="FeedBack.findAll", query="SELECT a FROM FeedBack a"),
    @NamedQuery (name="FeedBack.getById", query="SELECT a FROM FeedBack a WHERE a.id = :id")
})
public class FeedBack implements Serializable{
    
    @GeneratedValue
    @Id
    private long id;
    private String feedback;    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;

    public FeedBack() {
    }

    public FeedBack(String feedback, Date data) {
        this.feedback = feedback;
        this.data = data;
    }        

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public long getId() {
        return id;
    }                  
    
}
