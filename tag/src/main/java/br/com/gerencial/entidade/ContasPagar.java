/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.ContasPagarStatus;
import br.com.gerencial.enumerated.TipoPagamento;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author unespar
 */
@Entity
public class ContasPagar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data_vencimento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data_pagamento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data_lancamento;

    @Column(name = "valor_pagar")
    private Double valor = new Double(0);

    @ManyToOne
    private Pessoa pessoaJuridica;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @Column(name = "parcelas", length = 3)
    private Integer num_parcelas = 1;

    @Enumerated(EnumType.STRING)
    private ContasPagarStatus contasPagarStatus = ContasPagarStatus.ABERTO;

    @Column(name = "contPagar_tipo_pagamento")
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    @Column(columnDefinition = "TEXT")
    private String contasPagar_obs = "";

    public ContasPagarStatus getContasPagarStatus() {
        return contasPagarStatus;
    }

    public void setContasPagarStatus(ContasPagarStatus contasPagarStatus) {
        this.contasPagarStatus = contasPagarStatus;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getContasPagar_obs() {
        return contasPagar_obs;
    }

    public void setContasPagar_obs(String contasPagar_obs) {
        this.contasPagar_obs = contasPagar_obs;
    }

    public Integer getNum_parcelas() {
        return num_parcelas;
    }

    public void setNum_parcelas(Integer num_parcelas) {
        this.num_parcelas = num_parcelas;
    }

    public Date getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public Date getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(Date data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public Date getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(Date data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Pessoa getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(Pessoa pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContasPagar other = (ContasPagar) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

}
