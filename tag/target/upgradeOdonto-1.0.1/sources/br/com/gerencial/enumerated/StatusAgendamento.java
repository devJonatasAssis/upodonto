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
public enum StatusAgendamento {
    
    Confirmar("Confirmar"),
    Confirmado("Confirmado"),
    Aguardando("Aguardando"),
    EmAtendimento("Em Atendimento"),
    Atendido("Atendido"),
    Desmarcou("Desmarcou"),
    Faltou("Faltou");
    
    private final String descricao;
    
    private StatusAgendamento(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
