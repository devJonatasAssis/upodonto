/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.facade;

import br.com.gerencial.entidade.Agendamento;
import br.com.gerencial.entidade.PessoaFisica;
import br.com.gerencial.util.Transacional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jonatas
 */
@Transacional
public class PessoaFisicaFacade extends AbstractFacade<PessoaFisica> implements Serializable{
    
    @Inject
    private EntityManager em;

    public PessoaFisicaFacade() {
        super(PessoaFisica.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
    
    public List<PessoaFisica> listaPessoaFuncionarioTrue(){
        Query q = em.createQuery("FROM PessoaFisica WHERE pes_funcionario='true'");
        return q.getResultList();
    }
    
    public List<PessoaFisica> listaPacienteTrue(){
        Query q = em.createQuery("FROM PessoaFisica WHERE pes_paciente='true'");
        return q.getResultList();
    }
}
