/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.facade;

import br.com.gerencial.entidade.Cidade;
import br.com.gerencial.util.Transacional;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author jonatas
 */
@Transacional
public class CidadeFacade extends AbstractFacade<Cidade>{
    
    @Inject
    private EntityManager em;

    public CidadeFacade() {
        super(Cidade.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
    
    public List<Cidade> buscarPorEstado(Long estadoId){
        
        Session sessao = (Session) em;
        
        try {
            Criteria consulta = sessao.createCriteria(Cidade.class);
            consulta.add(Restrictions.eq("estado.id", estadoId));
            consulta.addOrder(Order.asc("nome"));
            List<Cidade> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException e) {
            throw e;
        }finally{
            sessao.close();
        }
    }
}
