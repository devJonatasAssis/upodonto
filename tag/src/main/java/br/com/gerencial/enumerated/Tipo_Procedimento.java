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
public enum Tipo_Procedimento {

    CIRURGIA("Cirurgia"),
    CIRURGIA_BUCO_MAXILO_FACIAL("Cirurgia Buco Maxilo Facial"),
    DENTISTICA("Dentistica"),
    EMERGENCIA("Emergência"),
    ENDODONTIA("Endodontia"),
    EXAME_CLINICO("Exame Clínico"),
    ODONTOPEDIATRIA("Odontopediatria"),
    ORTODONTIA("Ortodontia"),
    PERIODONTIA("Periodontia"),
    PREVENCAO("Prevenção"),
    PROTESE("Prótese"),
    PROTESE_SOBRE_IMPLANTE("Prótese Sobre Implante"),
    RADIOLOGIA("Radiologia"),
    TESTES_EXAMES_LABORATORIO("Testes e Exames de Laboratório");

    private final String descricao;

    private Tipo_Procedimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
