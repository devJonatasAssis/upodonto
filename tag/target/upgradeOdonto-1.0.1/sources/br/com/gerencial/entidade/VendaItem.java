/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author unespar
 */
@Entity
public class VendaItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    private BigDecimal ivPrecoBruto = BigDecimal.ZERO;
    
    @NotNull
    private BigDecimal ivPrecoLiquido = BigDecimal.ZERO;
    
    @NotNull
    private BigDecimal ivQuantidade = BigDecimal.ZERO;
    
    private BigDecimal ivDesconto = BigDecimal.ZERO;
    
    @ManyToOne
    @JoinColumn(name = "venda_Id" , nullable = false)
    private Venda venda;
    
    @ManyToOne()
    @JoinColumn(name = "produto_Id" , nullable = false)
    private Produto produto;
    
    @Transient
    private BigDecimal descontoAdcional = BigDecimal.ZERO;
    
    public BigDecimal getSubTotalBruto(){
        if (ivPrecoBruto != null && ivQuantidade != null && ivQuantidade.compareTo(BigDecimal.ZERO) > 0) {
            return ivPrecoBruto.multiply(ivQuantidade);
        }
        return BigDecimal.ZERO;
    }
    
    public BigDecimal getSubTotalLiquido(){
        if (ivPrecoBruto != null && ivQuantidade != null && ivQuantidade.compareTo(BigDecimal.ZERO) > 0) {
            if (ivDesconto != null && ivDesconto.compareTo(BigDecimal.ZERO) > 0 && ivDesconto.compareTo(getSubTotalBruto()) <= 0) {
                ivPrecoLiquido = ivPrecoBruto.multiply(ivQuantidade).subtract(ivDesconto).subtract(descontoAdcional);
                return ivPrecoLiquido;
            }else{
                ivPrecoLiquido = getSubTotalBruto().subtract(descontoAdcional);
                return ivPrecoLiquido;
            }
        }
        
        return BigDecimal.ZERO;
    }

    public BigDecimal getIvPrecoBruto() {
        return ivPrecoBruto;
    }

    public void setIvPrecoBruto(BigDecimal ivPrecoBruto) {
        this.ivPrecoBruto = ivPrecoBruto;
    }

    public BigDecimal getIvPrecoLiquido() {
        return ivPrecoLiquido;
    }

    public void setIvPrecoLiquido(BigDecimal ivPrecoLiquido) {
        this.ivPrecoLiquido = ivPrecoLiquido;
    }

    public BigDecimal getIvQuantidade() {
        return ivQuantidade;
    }

    public void setIvQuantidade(BigDecimal ivQuantidade) {
        this.ivQuantidade = ivQuantidade;
    }

    public BigDecimal getIvDesconto() {
        return ivDesconto;
    }

    public void setIvDesconto(BigDecimal ivDesconto) {
        this.ivDesconto = ivDesconto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getDescontoAdcional() {
        return descontoAdcional;
    }

    public void setDescontoAdcional(BigDecimal descontoAdcional) {
        this.descontoAdcional = descontoAdcional;
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
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.produto);
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
        final VendaItem other = (VendaItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }
    
}
