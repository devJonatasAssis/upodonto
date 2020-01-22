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
public enum TipoAjusteEstoque {
    
    Adicionar("Adicionar no Estoque"),
    Baixar("Baixar no Estoque");
    
    private final String descricao;
    
    private TipoAjusteEstoque(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
