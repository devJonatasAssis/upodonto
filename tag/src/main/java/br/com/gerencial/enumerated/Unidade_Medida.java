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
public enum Unidade_Medida {
    
    Unidade("Unidade"),
    Gramas("Gramas"),
    KG("Kg"),
    ML("Ml"),
    Litros("Litros"),
    Centimetro("Centímetro"),
    Metro("Metro");
    
    private final String descricao;

    public String getDescricao() {
        return descricao;
    }
    
    private Unidade_Medida(String descricao){
        this.descricao = descricao;
    }  
}
