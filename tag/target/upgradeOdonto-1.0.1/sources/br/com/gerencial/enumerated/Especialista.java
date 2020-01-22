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
public enum Especialista {
    
    Auxiliar_Saude_Bucal("Auxiliar em Saúde Bucal"),
    Auxiliar_Protese_Dentaria("Auxiliar em Prótese Dentária"),
    Auxiliar_Odontologico("Auxiliar Odontológico"),
    Auxiliar_Dentista("Auxiliar Dentista"),
    Cirurgiao_Dentista("Cirurgião Dentista"),
    Dentista("Dentista"),
    Dentista_Clinico_Geral("Dentista Clínico Geral"),
    Dentista_Ortodontista("Dentista Ortodontista"),
    Dentista_Endodontista("Dentista Endodontista"),
    Dentista_Protesista("Dentista Protesista"),
    Dentista_Periodontista("Dentista Periodontista"),
    Radiologia_Odontologica("Dentista Especialista - Radiologia Odontológica"),
    Tecnico_Protese_Dentaria("Técnico em Prótese Dentária"),
    Tecnico_Saude_Bucal("Técnico em Saúde Bucal"),
    ODONTOLOGO("Odontólogo"),
    Protetico_Ceramista("Protético Ceramista");
 
    private String descricao;
    
    private Especialista(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
