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
public enum TipoEmpresa {
    
    SEDE("Sede"),
    FILIAL("Filial");
    
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    private TipoEmpresa(String descricao) {
        this.descricao = descricao;
    }
}
