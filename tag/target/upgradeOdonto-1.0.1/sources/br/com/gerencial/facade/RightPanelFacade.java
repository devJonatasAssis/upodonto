/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.facade;

import br.com.gerencial.entidade.Agendamento;
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
public class RightPanelFacade extends AbstractFacade<Agendamento> implements Serializable{
    
    @Inject
    private EntityManager em;

    public RightPanelFacade() {
        super(Agendamento.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
    
    public List<Agendamento> listaPacienteEmEspera(){
        Query q = em.createQuery("FROM Agendamento WHERE status='Aguardando'");
        return q.getResultList();
    }
}
