/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enum;

/**
 *
 * @author Fernando Chagas
 */
public enum BancaType {
    SIM("Defendido"),
    NAO("Não defendido");
    
    private final String type;
    
    BancaType(String type){
        this.type=type;
    }
    
    private String type(){
        return this.type;
    }
    
    public String asString(){
        return this.type;
    }
}
