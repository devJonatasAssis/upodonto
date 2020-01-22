/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.ContasPagarStatus;
import br.com.gerencial.enumerated.TipoPagamento;
import java.util.Objects;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author unespar
 */
@Entity
public class ContasReceber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    private Double crValor = new Double(0);
    
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date crDataVencimento;
    
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date crDataLancamento;
    
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date crDataRecebimento;
    
    @Column(columnDefinition = "TEXT")
    private String obs = "";
    
    private Integer crNumParcela = 1;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContasPagarStatus contasPagarStatus = ContasPagarStatus.ABERTO;
    
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;
    
    @ManyToOne
    @JoinColumn(name = "comeco_id")
    private Procedimento comecoTratamento;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;
    
    @ManyToOne
    private Venda venda;
    
    @ManyToOne
    private Procedimento procedimento;

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCrValor() {
        return crValor;
    }

    public void setCrValor(Double crValor) {
        this.crValor = crValor;
    }

    public Date getCrDataVencimento() {
        return crDataVencimento;
    }

    public void setCrDataVencimento(Date crDataVencimento) {
        this.crDataVencimento = crDataVencimento;
    }

    public Date getCrDataLancamento() {
        return crDataLancamento;
    }

    public void setCrDataLancamento(Date crDataLancamento) {
        this.crDataLancamento = crDataLancamento;
    }

    public Date getCrDataRecebimento() {
        return crDataRecebimento;
    }

    public void setCrDataRecebimento(Date crDataRecebimento) {
        this.crDataRecebimento = crDataRecebimento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getCrNumParcela() {
        return crNumParcela;
    }

    public void setCrNumParcela(Integer crNumParcela) {
        this.crNumParcela = crNumParcela;
    }

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

    public Procedimento getComecoTratamento() {
        return comecoTratamento;
    }

    public void setComecoTratamento(Procedimento comecoTratamento) {
        this.comecoTratamento = comecoTratamento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        
        final ContasReceber other = (ContasReceber) obj;
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
