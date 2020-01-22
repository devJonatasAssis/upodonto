/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.Especialista;
import br.com.gerencial.enumerated.SiglaCRO;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author unespar
 */
@Entity
public class Doutor extends Pessoa implements Serializable {

    @Column(name = "func_cro")
    private String cro;

    @Enumerated(EnumType.STRING)
    @Column(name = "func_SiglaCro")
    private SiglaCRO siglaCRO;

    @Enumerated(EnumType.STRING)
    private Especialista especialista;

    @CPF
    private String cpf_doutor;

    @Override
    public String getDocumento() {
        return cpf_doutor;
    }

    public String getCpf_doutor() {
        return cpf_doutor;
    }

    public void setCpf_doutor(String cpf_doutor) {
        this.cpf_doutor = cpf_doutor;
    }

    public Especialista getEspecialista() {
        return especialista;
    }

    public void setEspecialista(Especialista especialista) {
        this.especialista = especialista;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public SiglaCRO getSiglaCRO() {
        return siglaCRO;
    }

    public void setSiglaCRO(SiglaCRO siglaCRO) {
        this.siglaCRO = siglaCRO;
    }

}
