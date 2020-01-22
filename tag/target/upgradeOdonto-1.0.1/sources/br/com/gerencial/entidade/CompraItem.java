/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author unespar
 */
@Entity
public class CompraItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal icPrecoBruto = BigDecimal.ZERO;
    private BigDecimal icPrecoLiquido = BigDecimal.ZERO;
    private BigDecimal icQuantidade = BigDecimal.ZERO;
    private BigDecimal icDesconto = BigDecimal.ZERO;
    
    @ManyToOne
    @JoinColumn(name = "compra_id" , nullable = false)
    private Compra compra;
    
    @ManyToOne
    @JoinColumn(name = "produto_id" , nullable = false)
    private Produto produto;
    
    @Transient
    private BigDecimal descAdicional = BigDecimal.ZERO;
    
    public BigDecimal getSubTotalBruto(){
        if (icPrecoBruto != null && icQuantidade != null && icQuantidade.compareTo(BigDecimal.ZERO) > 0) {
            return icPrecoBruto.multiply(icQuantidade);
        }
        return BigDecimal.ZERO;
    }
    
    public BigDecimal getSubTotalLiquido(){
        if (icPrecoBruto != null && icQuantidade != null && icQuantidade.compareTo(BigDecimal.ZERO) > 0) {
            if (icDesconto != null && icDesconto.compareTo(BigDecimal.ZERO) > 0 && icDesconto.compareTo(getSubTotalBruto()) <= 0) {
                icPrecoLiquido = icPrecoBruto.multiply(icQuantidade).subtract(icDesconto).subtract(descAdicional);
                return icPrecoLiquido;
            } else {
                icPrecoLiquido = getSubTotalBruto().subtract(descAdicional);
                return icPrecoLiquido;
            }
        }
        return BigDecimal.ZERO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getIcPrecoBruto() {
        return icPrecoBruto;
    }

    public void setIcPrecoBruto(BigDecimal icPrecoBruto) {
        this.icPrecoBruto = icPrecoBruto;
    }

    public BigDecimal getIcPrecoLiquido() {
        return icPrecoLiquido;
    }

    public void setIcPrecoLiquido(BigDecimal icPrecoLiquido) {
        this.icPrecoLiquido = icPrecoLiquido;
    }

    public BigDecimal getIcQuantidade() {
        return icQuantidade;
    }

    public void setIcQuantidade(BigDecimal icQuantidade) {
        this.icQuantidade = icQuantidade;
    }

    public BigDecimal getIcDesconto() {
        return icDesconto;
    }

    public void setIcDesconto(BigDecimal icDesconto) {
        this.icDesconto = icDesconto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getDescAdicional() {
        return descAdicional;
    }

    public void setDescAdicional(BigDecimal descAdicional) {
        this.descAdicional = descAdicional;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.produto);
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
        final CompraItem other = (CompraItem) obj;
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
