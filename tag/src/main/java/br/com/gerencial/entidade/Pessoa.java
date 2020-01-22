/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import br.com.gerencial.interfaces.EntidadePai;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author sistemas
 */
@Entity
public abstract class Pessoa implements Serializable, EntidadePai {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "pes_nome")
    private String nome;

    @Column(name = "pes_endereco")
    private String endereco;
    
    private String tipoLogradouro;

    @ManyToOne
    @JoinColumn(name = "cid_id")
    private Cidade cidade;

    @ManyToOne
    private Estado estado;

    @Column(name = "pes_bairro")
    private String bairro;

    @Column(name = "pes_numero")
    private String numero;

    @Column(name = "pes_complemento")
    private String complemento;

    @Column(name = "pes_telefone")
    private String telefone;

    @Column(name = "pes_celular")
    private String celular;

    @Email
    @Column(name = "pes_email")
    private String email;

    @Temporal(TemporalType.DATE)
    private Date data_Cadastro = new Date();

    @Column(name = "pes_status")
    private Boolean status = true;

    @Column(name = "pes_cep")
    private String cep;

    @Column(name = "pes_Funcionario")
    private Boolean Funcionario = false;

    @Column(name = "pes_Paciente")
    private Boolean paciente = false;

    @Column(name = "pes_Fornecedor")
    private Boolean fornecedor = false;

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public Boolean getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Boolean fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Boolean getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(Boolean Funcionario) {
        this.Funcionario = Funcionario;
    }

    public Boolean getPaciente() {
        return paciente;
    }

    public void setPaciente(Boolean paciente) {
        this.paciente = paciente;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getData_Cadastro() {
        return data_Cadastro;
    }

    public void setData_Cadastro(Date data_Cadastro) {
        this.data_Cadastro = data_Cadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public abstract String getDocumento();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
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
