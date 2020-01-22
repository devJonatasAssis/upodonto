/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.TipoPagamento;
import br.com.gerencial.interfaces.EntidadePai;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author unespar
 */
@Entity
@Table(name = "procedimento")
public class Procedimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "dataLanc_procedimento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data = new Date();
    
    @Column(name = "dataFinalTratamtento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinal;

    @ManyToOne
    private PessoaFisica pessoaFisica = new PessoaFisica();

    @ManyToOne
    private Tratamento tratamento;

    @ManyToOne
    private Doutor doutor;

    private Boolean status;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<ContasReceber> contasReceber = new ArrayList<>();

    private Integer num_parcela;

    @Column(name = "comTrat_obs")
    private String obs;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    @Column(name = "comTratamento_desconto")
    private BigDecimal desconto = BigDecimal.ZERO;

    @Column(name = "comTratamento_acrescimo")
    private BigDecimal acrescimos = BigDecimal.ZERO;

    private BigDecimal procedimentoTotalBruto = BigDecimal.ZERO;

    private BigDecimal procedimentoTotalLiquido = BigDecimal.ZERO;
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "procedimento")
    private List<ItemProcedimento> itemProcedimentos = new ArrayList<>();
    
    @Transient
    private ItemProcedimento itemProcedimento = new ItemProcedimento();

    public void calculaTotal() {
        getProcedimentoTotalBruto();
        getProcedimentoTotalLiquido();
    }
    
    public void addItemProcedimento() {
        if (itemProcedimento.getQuantidade().compareTo(BigDecimal.ZERO) > 0) {
            itemProcedimento.setProcedimento(this);
            if (!itemProcedimentos.contains(itemProcedimento)) {
                itemProcedimentos.add(itemProcedimento);
            } else {
                ItemProcedimento ip = itemProcedimentos.get(itemProcedimentos.indexOf(itemProcedimento));
                ip.setQuantidade(ip.getQuantidade().add(itemProcedimento.getQuantidade()));
                ip.setQuantidade(ip.getQuantidade().add(itemProcedimento.getIpDesconto()));
            }

            itemProcedimento = new ItemProcedimento();
            calculaTotal();
        }
    }
    
    public void removeItem(ItemProcedimento item){
        itemProcedimentos.remove(item);
        calculaTotal();
    }

    public List<ItemProcedimento> getItemProcedimentos() {
        return itemProcedimentos;
    }

    public void setItemProcedimentos(List<ItemProcedimento> itemProcedimentos) {
        this.itemProcedimentos = itemProcedimentos;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getNum_parcela() {
        return num_parcela;
    }

    public void setNum_parcela(Integer num_parcela) {
        this.num_parcela = num_parcela;
    }

    public List<ContasReceber> getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(List<ContasReceber> contasReceber) {
        this.contasReceber = contasReceber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Doutor getDoutor() {
        return doutor;
    }

    public void setDoutor(Doutor doutor) {
        this.doutor = doutor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public Tratamento getTratamento() {
        return tratamento;
    }

    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }

    public BigDecimal getAcrescimos() {
        return acrescimos;
    }

    public void setAcrescimos(BigDecimal acrescimos) {
        this.acrescimos = acrescimos;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public ItemProcedimento getItemProcedimento() {
        return itemProcedimento;
    }

    public void setItemProcedimento(ItemProcedimento itemProcedimento) {
        this.itemProcedimento = itemProcedimento;
    }

    public BigDecimal getProcedimentoTotalBruto() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemProcedimento ip : itemProcedimentos) {
            total = total.add(ip.getIpPrecoBruto());
        }
        procedimentoTotalBruto = total;
        return procedimentoTotalBruto;
    }

    public void setProcedimentoTotalBruto(BigDecimal procedimentoTotalBruto) {
        this.procedimentoTotalBruto = procedimentoTotalBruto;
    }

    public BigDecimal getProcedimentoTotalLiquido() {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal totalDesc = BigDecimal.ZERO;
        for (ItemProcedimento ip : itemProcedimentos) {
            total = total.add(ip.getIpPrecoLiquido());
            totalDesc = totalDesc.add(ip.getIpDesconto());
        }
        desconto = totalDesc;
        procedimentoTotalLiquido = total;
        return procedimentoTotalLiquido;
    }

    public void setProcedimentoTotalLiquido(BigDecimal procedimentoTotalLiquido) {
        this.procedimentoTotalLiquido = procedimentoTotalLiquido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
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

        final Procedimento other = (Procedimento) obj;
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
