/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Enum;

/**
 *
 * @author Fernando Chagas
 */
public enum GradeType {
    BANCA("Avaliação da banca"),
    TURMAALUNO("Avaliação de turma por aluno"),
    TURMACOORDENADOR("Avaliação de turma por coordenador"),
    LINHADOTEMPO("Linha do tempo");
    
    private final String type;
    
    GradeType(String type){
        this.type=type;
    }
    
    private String type(){
        return this.type;
    }
    
    public String asString(){
        return this.type;
    }
}
