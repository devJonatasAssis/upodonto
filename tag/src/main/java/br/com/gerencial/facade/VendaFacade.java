/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.facade;

import br.com.gerencial.entidade.Venda;
import br.com.gerencial.util.Transacional;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author unespar
 */
@Transacional
public class VendaFacade extends AbstractFacade<Venda> implements Serializable{
    
    @Inject
    private EntityManager em;

    public VendaFacade() {
        super(Venda.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
    
}
