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
public enum TipoPagamento {
    
    BOLETO("Boleto"),
    CHEQUE("Cheque"),
    CARTAOCREDITO("Cartão de Crédito"),
    CARTAODEBITO("Cartão de Débito"),
    DEPOSITO("Depósito em Conta"),
    DINHEIRO("Em Dinheiro");
    
    private String descricao;

    public String getDescricao() {
        return descricao;
    }
    
    TipoPagamento(String descricao){
        this.descricao = descricao;
    }
    
}
