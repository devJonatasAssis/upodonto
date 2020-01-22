/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.enumerated;

/**
 *
 * @author Matheus
 */
public enum ContasPagarStatus {
    ABERTO("Aberto"),
    PAGO("Pago"),
    RECEBIDO("Recebido"),
    CANCELADO("Cancelado");
    
    private final String descricao;

    private ContasPagarStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
