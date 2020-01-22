/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.enumerated;

/**
 *
 * @author unespar
 */
public enum Parentesco {
    
    PAI("Pai"),
    MAE("Mãe"),
    IRMAO("Irmão(a)"),
    TIO("Tio(a)"),
    AVO("Avô(ó)"),
    PRIMO("Primo(a)"),
    OUTRO("Outro");
    
    private final String descricao;
    
    private Parentesco(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
