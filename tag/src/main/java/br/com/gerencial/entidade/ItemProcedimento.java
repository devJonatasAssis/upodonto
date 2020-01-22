/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.Dente;
import br.com.gerencial.enumerated.Status_Odontograma;
import br.com.gerencial.enumerated.Tipo_Procedimento;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author jonatas
 */
@Entity
public class ItemProcedimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private BigDecimal ipPrecoBruto = BigDecimal.ZERO;
    private BigDecimal ipPrecoLiquido = BigDecimal.ZERO;
    private BigDecimal ipDesconto = BigDecimal.ZERO;
    private BigDecimal quantidade = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private Status_Odontograma status_Odontograma;

    @Enumerated(EnumType.STRING)
    private Dente de;

    @Enumerated(EnumType.STRING)
    private Dente ate;

    @Enumerated(EnumType.STRING)
    private Tipo_Procedimento tipoProcedimento;

    private String obs;

    @ManyToOne
    private Servico servico;

    @ManyToOne
    private Procedimento procedimento;

    public BigDecimal getSubTotalBruto() {
        if (ipPrecoBruto != null && quantidade != null) {
            return ipPrecoBruto.multiply(quantidade);
        }

        return BigDecimal.ZERO;
    }

    public BigDecimal getSubTotalLiquido() {
        if (ipPrecoBruto != null && quantidade != null) {
            if (ipDesconto != null && ipDesconto.compareTo(BigDecimal.ZERO) > 0 && ipDesconto.compareTo(getSubTotalBruto()) <= 0) {
                ipPrecoLiquido = ipPrecoLiquido.multiply(quantidade).subtract(ipDesconto);
                return ipPrecoLiquido;
            } else {
                ipPrecoLiquido = getSubTotalBruto().subtract(quantidade);
                return ipPrecoLiquido;
            }

        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getIpPrecoBruto() {
        return ipPrecoBruto;
    }

    public void setIpPrecoBruto(BigDecimal ipPrecoBruto) {
        this.ipPrecoBruto = ipPrecoBruto;
    }

    public BigDecimal getIpPrecoLiquido() {
        return ipPrecoLiquido;
    }

    public void setIpPrecoLiquido(BigDecimal ipPrecoLiquido) {
        this.ipPrecoLiquido = ipPrecoLiquido;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public Status_Odontograma getStatus_Odontograma() {
        return status_Odontograma;
    }

    public void setStatus_Odontograma(Status_Odontograma status_Odontograma) {
        this.status_Odontograma = status_Odontograma;
    }

    public Dente getDe() {
        return de;
    }

    public void setDe(Dente de) {
        this.de = de;
    }

    public Dente getAte() {
        return ate;
    }

    public void setAte(Dente ate) {
        this.ate = ate;
    }

    public Tipo_Procedimento getTipoProcedimento() {
        return tipoProcedimento;
    }

    public void setTipoProcedimento(Tipo_Procedimento tipoProcedimento) {
        this.tipoProcedimento = tipoProcedimento;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public BigDecimal getIpDesconto() {
        return ipDesconto;
    }

    public void setIpDesconto(BigDecimal ipDesconto) {
        this.ipDesconto = ipDesconto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.servico);
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
        final ItemProcedimento other = (ItemProcedimento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.servico, other.servico)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

}
