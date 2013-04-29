/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enum;

/**
 *
 * @author Fernando Chagas
 */
public enum EstadoAvaliacao {
    LIBERADO("Liberado para avaliação"),
    NAO("Não liberado");
    
    private final String type;
    
    EstadoAvaliacao(String type){
        this.type=type;
    }
    
    private String type(){
        return this.type;
    }
    
    public String asString(){
        return this.type;
    }
}
