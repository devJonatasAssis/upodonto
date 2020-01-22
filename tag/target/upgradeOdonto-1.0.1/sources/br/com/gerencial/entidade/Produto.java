/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.Unidade_Medida;
import br.com.gerencial.interfaces.EntidadePai;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jonatas
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable, EntidadePai {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prod_id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "prod_DataCadastro")
    private Date data_cadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "prod_DataValidade")
    private Date data_validade;

    @Column(name = "prod_nome", nullable = false)
    private String nome;

    @Column(name = "prod_codigo")
    private String codigo;

    @Column(name = "prod_codIterno")
    private String codigo_Interno;

    @Column(name = "prod_estoque")
    private BigDecimal estoque = BigDecimal.ZERO;

    @Column(name = "prod_preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "prod_ativo")
    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private GrupoProduto grupo;

    @Enumerated(EnumType.STRING)
    private Unidade_Medida unidade;

    @Column(name = "prod_Marca")
    private String marca;

    @Column(name = "prod_NCM")
    private String ncm;

    public void baixarEstoque(BigDecimal quantidade) throws Exception {
        if (estoque.compareTo(quantidade) >= 0) {
            estoque = estoque.subtract(quantidade);
        } else {
            throw new Exception("Estoque Insuficiente para Venda,"
                    + "pois o Produto" + nome + "possui apenas"
                    + estoque + " Itens no Estoque.");
        }
    }

    public void adicionarEstoque(BigDecimal quantidade) throws Exception {
        estoque = estoque.add(quantidade);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public Date getData_validade() {
        return data_validade;
    }

    public void setData_validade(Date data_validade) {
        this.data_validade = data_validade;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getEstoque() {
        return estoque;
    }

    public void setEstoque(BigDecimal estoque) {
        this.estoque = estoque;
    }

    public Unidade_Medida getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade_Medida unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public GrupoProduto getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoProduto grupo) {
        this.grupo = grupo;
    }

    public String getCodigo_Interno() {
        return codigo_Interno;
    }

    public void setCodigo_Interno(String codigo_Interno) {
        this.codigo_Interno = codigo_Interno;
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
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
