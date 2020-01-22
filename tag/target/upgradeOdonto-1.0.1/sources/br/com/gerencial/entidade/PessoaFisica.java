/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.Civil;
import br.com.gerencial.enumerated.Parentesco;
import br.com.gerencial.enumerated.TipoConta;
import br.com.gerencial.enumerated.TipoPagamento;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author jonatas
 */
@Entity
public class PessoaFisica extends Pessoa implements Serializable {

    @CPF
    @Column(name = "fis_cpf")
    private String cpf;

    @Column(name = "fis_rg")
    private String rg;

    @Column(name = "pesFis_sexo")
    private String sexo = "Masculino";

    @Enumerated(EnumType.STRING)
    @Column(name = "fis_civil")
    private Civil civil;

    @Column(name = "pesFis_dataNasc")
    @Temporal(TemporalType.DATE)
    private Date data_nasc;

    @Temporal(TemporalType.DATE)
    @Column(name = "func_dataAdmissao")
    private Date data_admissao;

    @Temporal(TemporalType.DATE)
    @Column(name = "func_dataDemissao")
    private Date data_demissao;

    @ManyToOne
    @JoinColumn(name = "func_CargoId")
    private Cargo cargo;

    @Column(name = "func_Num_Conta")
    private String num_conta;

    @Column(name = "func_CargaHoraria")
    private String carga_horaria;

    @Column(name = "func_salario")
    private BigDecimal salario = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "func_TipoPagamento")
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "func_TipoConta")
    private TipoConta tipoConta;

    @ManyToOne
    @JoinColumn(name = "func_Banco")
    private Banco banco;

    @Column(name = "paciente_cns")
    private String cns;

    @Column(name = "paciente_carte")
    private String carteirinha;

    @Temporal(TemporalType.DATE)
    @Column(name = "paciente_dtValidade")
    private Date data_validade;
    
    @ManyToOne
    @JoinColumn(name = "convenio_id")
    private Convenio convenio;
    
    @Enumerated(EnumType.STRING)
    private Parentesco parentesco;
    
    @Column(name = "nomeResp_Paciente")
    private String nomeResp_Paciente;
    
    @Column(name = "telResp_Paciente")
    private String telResp_Paciente;
    
    @Column(name = "celResp_Paciente")
    private String celResp_Paciente;
    
    @Column(name = "cpfResp_Paciente")
    private String cpfResp_Paciente;
    
    private Boolean recebeEmail = true;

    public Boolean getRecebeEmail() {
        return recebeEmail;
    }

    public void setRecebeEmail(Boolean recebeEmail) {
        this.recebeEmail = recebeEmail;
    }

    public String getNomeResp_Paciente() {
        return nomeResp_Paciente;
    }

    public void setNomeResp_Paciente(String nomeResp_Paciente) {
        this.nomeResp_Paciente = nomeResp_Paciente;
    }

    public String getTelResp_Paciente() {
        return telResp_Paciente;
    }

    public void setTelResp_Paciente(String telResp_Paciente) {
        this.telResp_Paciente = telResp_Paciente;
    }

    public String getCelResp_Paciente() {
        return celResp_Paciente;
    }

    public void setCelResp_Paciente(String celResp_Paciente) {
        this.celResp_Paciente = celResp_Paciente;
    }

    public String getCpfResp_Paciente() {
        return cpfResp_Paciente;
    }

    public void setCpfResp_Paciente(String cpfResp_Paciente) {
        this.cpfResp_Paciente = cpfResp_Paciente;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    @Override
    public String getDocumento() {
        return cpf;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public String getNum_conta() {
        return num_conta;
    }

    public void setNum_conta(String num_conta) {
        this.num_conta = num_conta;
    }

    public String getCarteirinha() {
        return carteirinha;
    }

    public void setCarteirinha(String carteirinha) {
        this.carteirinha = carteirinha;
    }

    public Date getData_validade() {
        return data_validade;
    }

    public void setData_validade(Date data_validade) {
        this.data_validade = data_validade;
    }

    public Date getData_admissao() {
        return data_admissao;
    }

    public void setData_admissao(Date data_admissao) {
        this.data_admissao = data_admissao;
    }

    public Date getData_demissao() {
        return data_demissao;
    }

    public void setData_demissao(Date data_demissao) {
        this.data_demissao = data_demissao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(String carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public Civil getCivil() {
        return civil;
    }

    public void setCivil(Civil civil) {
        this.civil = civil;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
