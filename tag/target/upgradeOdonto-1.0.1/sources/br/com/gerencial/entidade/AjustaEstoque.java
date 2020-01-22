/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.TipoAjusteEstoque;
import br.com.gerencial.interfaces.MovimentaEstoque;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author jonatas
 */
@Entity
public class AjustaEstoque implements Serializable , MovimentaEstoque{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private BigDecimal ajusteQuantidade = BigDecimal.ZERO;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAjuste = new Date();
    
    @ManyToOne
    private Produto produto;
    
    @Enumerated
    private TipoAjusteEstoque tipoAjusteEstoque = TipoAjusteEstoque.Adicionar;
    
    private String ajusteMotivo;

    public String getAjusteMotivo() {
        return ajusteMotivo;
    }

    public void setAjusteMotivo(String ajusteMotivo) {
        this.ajusteMotivo = ajusteMotivo;
    }

    public TipoAjusteEstoque getTipoAjusteEstoque() {
        return tipoAjusteEstoque;
    }

    public void setTipoAjusteEstoque(TipoAjusteEstoque tipoAjusteEstoque) {
        this.tipoAjusteEstoque = tipoAjusteEstoque;
    }

    public BigDecimal getAjusteQuantidade() {
        return ajusteQuantidade;
    }

    public void setAjusteQuantidade(BigDecimal ajusteQuantidade) {
        this.ajusteQuantidade = ajusteQuantidade;
    }

    public Date getDataAjuste() {
        return dataAjuste;
    }

    public void setDataAjuste(Date dataAjuste) {
        this.dataAjuste = dataAjuste;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AjustaEstoque)) {
            return false;
        }
        AjustaEstoque other = (AjustaEstoque) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gerencial.entidade.AjustaEstoque[ id=" + id + " ]";
    }

    @Override
    public void movimenta() throws Exception {
        if (tipoAjusteEstoque.equals(TipoAjusteEstoque.Adicionar)) {
            produto.adicionarEstoque(ajusteQuantidade);
        }else{
            produto.baixarEstoque(ajusteQuantidade);
        }
    }
    
}
