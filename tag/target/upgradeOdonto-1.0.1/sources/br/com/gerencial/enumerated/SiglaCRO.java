/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.enumerated;

/**
 *
 * @author jonatas
 */
public enum SiglaCRO {
    
    AC("AC"),
    AL("AL"),
    AP("AP"),
    AM("AM"),
    BA("BA"),
    CE("CE"),
    DF("DF"),
    ES("ES"),
    GO("GO"),
    MA("MA"),
    MT("MT"),
    MS("MS"),
    MG("MG"),
    PA("PA"),
    PB("PB"),
    PE("PE"),
    PJ("PJ"),
    PR("PR"),
    RN("RJ"),
    RS("RJ"),
    RO("RO"),
    RR("RR"),
    SC("SC"),
    SP("SP"),
    RE("RE"),
    TO("TO");
    
    private final String descricao;
    
    private SiglaCRO(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
