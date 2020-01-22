/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.facade;

import br.com.gerencial.entidade.Convenio;
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
public class ConvenioFacade extends AbstractFacade<Convenio> implements Serializable{
    
    @Inject
    private EntityManager em;

    public ConvenioFacade() {
        super(Convenio.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
    
    @Override
    public List<Convenio> listaTodos(){
        Query q = em.createQuery("FROM Convenio WHERE status='true'");
        return q.getResultList();
    }
}
