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
public enum Sexo {
    
    MASCULINO("Masculino"),
    FEMININO("Feminio");
    
    private final String descricao;
    
    private Sexo(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
    
}
