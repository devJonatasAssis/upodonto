/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.facade;

import br.com.gerencial.entidade.Cargo;
import br.com.gerencial.util.Transacional;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author jonatas
 */
@Transacional
public class CargoFacade extends AbstractFacade<Cargo>{
    
    @Inject
    private EntityManager em;

    public CargoFacade() {
        super(Cargo.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
    
}
