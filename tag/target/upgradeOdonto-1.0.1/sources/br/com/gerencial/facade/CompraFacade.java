package br.com.gerencial.facade;

import br.com.gerencial.entidade.Compra;
import br.com.gerencial.util.Transacional;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Jonatas
 */
@Transacional
public class CompraFacade extends AbstractFacade<Compra> implements Serializable {

    @Inject
    private EntityManager em;

    public CompraFacade() {
        super(Compra.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
}
