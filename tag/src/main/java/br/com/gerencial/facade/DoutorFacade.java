/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.facade;

import br.com.gerencial.entidade.Doutor;
import br.com.gerencial.util.Transacional;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author unespar
 */
@Transacional
public class DoutorFacade extends AbstractFacade<Doutor> implements Serializable{
    
    @Inject
    private EntityManager em;

    public DoutorFacade() {
        super(Doutor.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
}
