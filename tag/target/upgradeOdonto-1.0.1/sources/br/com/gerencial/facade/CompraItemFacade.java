package br.com.gerencial.facade;


import br.com.gerencial.entidade.CompraItem;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Matheus
 */
public class CompraItemFacade extends AbstractFacade<CompraItem> implements Serializable {

    @Inject
    private EntityManager em;

    public CompraItemFacade() {
        super(CompraItem.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
}
