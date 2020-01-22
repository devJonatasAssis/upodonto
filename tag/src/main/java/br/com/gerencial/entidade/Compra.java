/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.CompraTipo;
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
import javax.persistence.EnumType;
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

/**
 *
 * @author unespar
 */
@Entity
@Table(name = "compra")
public class Compra implements Serializable, Validador, MovimentaEstoque {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal compraTotalBruto;

    private BigDecimal compraTotalLiquido;

    private BigDecimal compraDesconto = BigDecimal.ZERO;

    private BigDecimal compraAcrescimo = BigDecimal.ZERO;

    @Temporal(TemporalType.TIMESTAMP)
    private Date compraData = new Date();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "compra",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<CompraItem> compraItems = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "compra",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<ContasPagar> contasPagar = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    private CompraTipo compraTipo;

    @Column(name = "compra_NumNfe")
    private String num_nfe;

    private Boolean compraStatus = true;

    private String compraObs;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "compra_status")
    private StatusCompraVenda statusCompraVenda;

    @Transient
    private CompraItem compraItem = new CompraItem();

    public void addItem() {
        if (compraItem.getIcQuantidade().compareTo(BigDecimal.ZERO) > 0) {
            compraItem.setCompra(this);
            if (!compraItems.contains(compraItem)) {
                compraItems.add(compraItem);
            } else {
                CompraItem iv = compraItems.get(compraItems.indexOf(compraItem));
                iv.setIcQuantidade(iv.getIcQuantidade().add(compraItem.getIcQuantidade()));
                iv.setIcDesconto(iv.getIcDesconto().add(compraItem.getIcDesconto()));
            }  
            calculaTotal();
            compraItem = new CompraItem();
        }
    }

    public void removeItem(CompraItem item) throws Exception {
        compraItems.remove(item);
        calculaTotal();
        item.getProduto().adicionarEstoque(item.getIcQuantidade());
    }
   
    public void calculaTotal() {
        getCompraTotalBruto();
        getCompraTotalLiquido();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCompraTotalBruto() {
        BigDecimal total = BigDecimal.ZERO;
        for (CompraItem i : compraItems) {
            total = total.add(i.getSubTotalBruto());
        }
        compraTotalBruto = total;
        return compraTotalBruto;
    }

    public void setCompraTotalBruto(BigDecimal compraTotalBruto) {
        this.compraTotalBruto = compraTotalBruto;
    }

    public BigDecimal getCompraTotalLiquido() {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal totalDesc = BigDecimal.ZERO;
        for (CompraItem i : compraItems) {
            total = total.add(i.getSubTotalLiquido());
            totalDesc = totalDesc.add(i.getIcDesconto());
        }
        compraDesconto = totalDesc;
        compraTotalLiquido = total;
        return compraTotalLiquido;
    }

    public BigDecimal getCompraAcrescimo() {
        return compraAcrescimo;
    }

    public void setCompraAcrescimo(BigDecimal compraAcrescimo) {
        this.compraAcrescimo = compraAcrescimo;
    }

    public CompraTipo getCompraTipo() {
        return compraTipo;
    }

    public void setCompraTipo(CompraTipo compraTipo) {
        this.compraTipo = compraTipo;
    }

    public String getNum_nfe() {
        return num_nfe;
    }

    public void setNum_nfe(String num_nfe) {
        this.num_nfe = num_nfe;
    }

    public void setCompraTotalLiquido(BigDecimal compraTotalLiquido) {
        this.compraTotalLiquido = compraTotalLiquido;
    }

    public BigDecimal getCompraDesconto() {
        return compraDesconto;
    }

    public void setCompraDesconto(BigDecimal compraDesconto) {
        this.compraDesconto = compraDesconto;
    }

    public Date getCompraData() {
        return compraData;
    }

    public void setCompraData(Date compraData) {
        this.compraData = compraData;
    }

    public List<CompraItem> getCompraItems() {
        return compraItems;
    }

    public void setCompraItems(List<CompraItem> compraItems) {
        this.compraItems = compraItems;
    }

    public List<ContasPagar> getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(List<ContasPagar> contasPagar) {
        this.contasPagar = contasPagar;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public CompraItem getCompraItem() {
        return compraItem;
    }

    public void setCompraItem(CompraItem compraItem) {
        this.compraItem = compraItem;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Boolean getCompraStatus() {
        return compraStatus;
    }

    public void setCompraStatus(Boolean compraStatus) {
        this.compraStatus = compraStatus;
    }

    public String getCompraObs() {
        return compraObs;
    }

    public void setCompraObs(String compraObs) {
        this.compraObs = compraObs;
    }

    public StatusCompraVenda getStatusCompraVenda() {
        return statusCompraVenda;
    }

    public void setStatusCompraVenda(StatusCompraVenda statusCompraVenda) {
        this.statusCompraVenda = statusCompraVenda;
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

        final Compra other = (Compra) obj;
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
    public void movimenta() throws Exception {
        if (statusCompraVenda.equals(StatusCompraVenda.FINALIZAR)) {
            for (CompraItem item : compraItems) {
                item.getProduto().adicionarEstoque(item.getIcQuantidade());
            }
        }
    }

    @Override
    public void validar() throws Exception {
        if (compraItems.isEmpty()) {
            throw new Exception("Não é possivel salvar uma Compra sem Itens , "
                    + "Por Favor Adicione Algum Produto");
        }
    }

}
