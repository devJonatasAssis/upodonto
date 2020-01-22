package br.com.gerencial.facade;

import br.com.gerencial.entidade.Estado;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Matheus
 */
public class EstadoFacade extends AbstractFacade<Estado> implements Serializable {

    @Inject
    private EntityManager em;

    public EstadoFacade() {
        super(Estado.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
}
