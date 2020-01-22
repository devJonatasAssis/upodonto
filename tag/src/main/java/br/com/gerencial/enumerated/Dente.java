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
public enum Dente {

    d11("Dente 11"),
    d12("Dente 12"),
    d13("Dente 13"),
    d14("Dente 14"),
    d15("Dente 15"),
    d16("Dente 16"),
    d17("Dente 17"),
    d18("Dente 18"),
    d21("Dente 21"),
    d22("Dente 22"),
    d23("Dente 23"),
    d24("Dente 24"),
    d25("Dente 25"),
    d26("Dente 26"),
    d27("Dente 27"),
    d28("Dente 28"),
    d31("Dente 31"),
    d32("Dente 32"),
    d33("Dente 33"),
    d34("Dente 34"),
    d35("Dente 35"),
    d36("Dente 36"),
    d37("Dente 37"),
    d38("Dente 38"),
    d41("Dente 41"),
    d42("Dente 42"),
    d43("Dente 43"),
    d44("Dente 44"),
    d45("Dente 45"),
    d46("Dente 46"),
    d47("Dente 47"),
    d48("Dente 48");

    private String descricao;
    
    private Dente(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
