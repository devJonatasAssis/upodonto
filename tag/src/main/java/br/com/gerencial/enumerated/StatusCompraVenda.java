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
public enum StatusCompraVenda {
    
    ABERTO("Em Aberto"),
    FINALIZAR("Finalizado");
    
    private final String descricao;

    private StatusCompraVenda(String descricao){
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
}
