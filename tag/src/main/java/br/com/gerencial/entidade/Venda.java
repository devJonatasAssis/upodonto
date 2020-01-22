/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.StatusCompraVenda;
import br.com.gerencial.enumerated.TipoPagamento;
import br.com.gerencial.interfaces.MovimentaEstoque;
import br.com.gerencial.interfaces.Validador;
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
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author unespar
 */
@Entity
@Table(name = "venda")
public class Venda implements Serializable, MovimentaEstoque, Validador {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private BigDecimal vendaTotalBruto = BigDecimal.ZERO;

    @NotNull
    private BigDecimal vendaTotalLiquido = BigDecimal.ZERO;

    @NotNull
    private BigDecimal vendaDesconto = BigDecimal.ZERO;

    private BigDecimal vendaAcrescimo = BigDecimal.ZERO;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVenda = new Date();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "venda",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<VendaItem> vendaItens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "venda",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<ContasReceber> contasRecebers = new ArrayList<>();

    @Enumerated
    private TipoPagamento tipoPagamento;

    @Enumerated
    private StatusCompraVenda statusCompraVenda;

    private String vendaObs;

    @Transient
    private VendaItem vendaItem = new VendaItem();

    public void calculaTotal() {
        getVendaTotalBruto();
        getVendaTotalLiquido();
    }

    public void addItemVenda() {
        if (vendaItem.getIvQuantidade().compareTo(BigDecimal.ZERO) > 0) {
            vendaItem.setVenda(this);
            if (!vendaItens.contains(vendaItem)) {
                vendaItens.add(vendaItem);
            } else {
                VendaItem iv = vendaItens.get(vendaItens.indexOf(vendaItem));
                iv.setIvQuantidade(iv.getIvQuantidade().add(vendaItem.getIvDesconto()));
            }

            vendaItem = new VendaItem();
            calculaTotal();
        }
    }

    public void removeItem(VendaItem item) {
        vendaItens.remove(item);
        calculaTotal();
    }

    public BigDecimal getVendaTotalBruto() {
        BigDecimal total = BigDecimal.ZERO;
        for (VendaItem vi : vendaItens) {
            total = total.add(vi.getSubTotalBruto());
        }

        vendaTotalBruto = total;
        return vendaTotalBruto;
    }

    public void setVendaTotalBruto(BigDecimal vendaTotalBruto) {
        this.vendaTotalBruto = vendaTotalBruto;
    }

    public BigDecimal getVendaTotalLiquido() {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal totalDesc = BigDecimal.ZERO;
        for (VendaItem iv : vendaItens) {
            total = total.add(iv.getSubTotalLiquido());
            totalDesc = totalDesc.add(iv.getIvDesconto());
        }
        vendaDesconto = totalDesc;
        vendaTotalLiquido = total;
        return vendaTotalLiquido;
    }

    public void setVendaTotalLiquido(BigDecimal vendaTotalLiquido) {
        this.vendaTotalLiquido = vendaTotalLiquido;
    }

    public BigDecimal getVendaDesconto() {
        return vendaDesconto;
    }

    public void setVendaDesconto(BigDecimal vendaDesconto) {
        this.vendaDesconto = vendaDesconto;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<VendaItem> getVendaItens() {
        return vendaItens;
    }

    public void setVendaItens(List<VendaItem> vendaItens) {
        this.vendaItens = vendaItens;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<ContasReceber> getContasRecebers() {
        return contasRecebers;
    }

    public void setContasRecebers(List<ContasReceber> contasRecebers) {
        this.contasRecebers = contasRecebers;
    }

    public VendaItem getVendaItem() {
        return vendaItem;
    }

    public void setVendaItem(VendaItem vendaItem) {
        this.vendaItem = vendaItem;
    }

    public BigDecimal getVendaAcrescimo() {
        return vendaAcrescimo;
    }

    public void setVendaAcrescimo(BigDecimal vendaAcrescimo) {
        this.vendaAcrescimo = vendaAcrescimo;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public StatusCompraVenda getStatusCompraVenda() {
        return statusCompraVenda;
    }

    public void setStatusCompraVenda(StatusCompraVenda statusCompraVenda) {
        this.statusCompraVenda = statusCompraVenda;
    }

    public String getVendaObs() {
        return vendaObs;
    }

    public void setVendaObs(String vendaObs) {
        this.vendaObs = vendaObs;
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
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public void validar() throws Exception {
        if (statusCompraVenda.equals(StatusCompraVenda.FINALIZAR)) {
            for (VendaItem item : vendaItens) {
                item.getProduto().baixarEstoque(item.getIvQuantidade());
            }
        }
    }

    @Override
    public void movimenta() throws Exception {
        if (vendaItens.isEmpty()) {
            throw new Exception("Não é possivel salvar uma venda sem itens");
        }
    }

}
