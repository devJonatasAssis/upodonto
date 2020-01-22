/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.facade;

import br.com.gerencial.entidade.Agendamento;
import br.com.gerencial.util.Transacional;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jonatas
 */
@Transacional
public class AgendamentoFacade extends AbstractFacade<Agendamento> implements Serializable{
    
    @Inject
    private EntityManager em;

    public AgendamentoFacade() {
        super(Agendamento.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
    
    public void editar(Agendamento agendamento){
        Query q = em.createQuery("UPDATE Agendamento SET paciente_id=?, dtInicial=?, dtFinal=?, agenda_obs=?, status=?, tipoConsulta=? WHERE id=?");
        q.setParameter(1, agendamento.getPessoaFisica());
        q.setParameter(2, new java.util.Date(agendamento.getDtInicial().getTime()));
        q.setParameter(3, new java.util.Date(agendamento.getDtFinal().getTime()));
        q.setParameter(4, agendamento.getObs_agendamento());
        q.setParameter(5, agendamento.getStatus());
        q.setParameter(6, agendamento.getTipoConsulta());
        q.setParameter(7, agendamento.getId());
        q.executeUpdate();
    }
    
//    public void pesquisarPaciente(Agendamento agendamento){
//        Query q = em.createQuery("SELECT a.id, a.paciente_id, p.pes_nome FROM agendamento a INNER JOIN pessoa p ON a.paciente_id = p.id WHERE p.pes_nome like '%?%'");
//        q.getResultList();
//    }
}
