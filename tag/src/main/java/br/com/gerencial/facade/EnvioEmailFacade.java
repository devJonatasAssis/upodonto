/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.facade;

import br.com.gerencial.entidade.Config_Email;
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
public class EnvioEmailFacade extends AbstractFacade<Config_Email> implements Serializable{
    
    @Inject
    private EntityManager em;

    public EnvioEmailFacade() {
        super(Config_Email.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
}
