/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.enumerated;

/**
 *
 * @author ricardo
 */
public enum NivelAcesso {
    MASTER("Master"),
    ADMIN("Administrador"),
    DOUTOR("Doutor(a)"),
    FINANCEIRO("Financeiro"),
    RECEPCAO("Recepção");

    private final String descricao;

    private NivelAcesso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
