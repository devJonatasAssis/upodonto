/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import br.com.gerencial.interfaces.EntidadePai;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 *
 * @author jonatas
 */
@Entity
public class PessoaJuridica extends Pessoa implements Serializable, EntidadePai {

    @CNPJ
    @Column(name = "pes_cnpj")
    private String cnpj;

    @Column(name = "jur_ie")
    private String ie;

    @Column(name = "pes_razao")
    private String razaoSocial;

    @Column(name = "forn_NomeRepres")
    private String nome_representante;

    @Column(name = "forn_TelRepres")
    private String tel_representante;

    @Column(name = "forn_CelRepres")
    private String cel_representante;

    @Override
    public String getDocumento() {
        return cnpj;
    }

    public String getNome_representante() {
        return nome_representante;
    }

    public void setNome_representante(String nome_representante) {
        this.nome_representante = nome_representante;
    }

    public String getTel_representante() {
        return tel_representante;
    }

    public void setTel_representante(String tel_representante) {
        this.tel_representante = tel_representante;
    }

    public String getCel_representante() {
        return cel_representante;
    }

    public void setCel_representante(String cel_representante) {
        this.cel_representante = cel_representante;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

}
