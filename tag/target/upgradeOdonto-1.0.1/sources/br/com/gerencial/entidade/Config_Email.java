/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author unespar-ti
 */
@Entity
@Table(name = "configEmail")
public class Config_Email implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Email
    private String email;
    private String senha;
    private String servidor_smtp;
    private String port_servidor_smtp;
    private String tipoAutent;
    private String servidorPop;
    private String portaPop;
    private String nome_visualizao;

    public String getNome_visualizao() {
        return nome_visualizao;
    }

    public void setNome_visualizao(String nome_visualizao) {
        this.nome_visualizao = nome_visualizao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getServidor_smtp() {
        return servidor_smtp;
    }

    public void setServidor_smtp(String servidor_smtp) {
        this.servidor_smtp = servidor_smtp;
    }

    public String getPort_servidor_smtp() {
        return port_servidor_smtp;
    }

    public void setPort_servidor_smtp(String port_servidor_smtp) {
        this.port_servidor_smtp = port_servidor_smtp;
    }

    public String getTipoAutent() {
        return tipoAutent;
    }

    public void setTipoAutent(String tipoAutent) {
        this.tipoAutent = tipoAutent;
    }

    public String getServidorPop() {
        return servidorPop;
    }

    public void setServidorPop(String servidorPop) {
        this.servidorPop = servidorPop;
    }

    public String getPortaPop() {
        return portaPop;
    }

    public void setPortaPop(String portaPop) {
        this.portaPop = portaPop;
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
        if (!(object instanceof Config_Email)) {
            return false;
        }
        Config_Email other = (Config_Email) object;
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
