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
public enum Status_Odontograma {
    
    ODONTOGRAMA_CHEGADA("Odontograma de Chegada"),
    AGUARDANDO("Aguardando Aprovação"),
    TRATAMENTO("Em Tratamento"),
    ENCERRADO("Encerrado"),
    PENDENTE("Pendente"),
    CANCELADO("Cancelado");
    
    private final String descricao;
    
    private Status_Odontograma(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
