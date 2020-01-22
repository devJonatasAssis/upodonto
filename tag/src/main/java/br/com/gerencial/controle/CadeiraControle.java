/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Cadeira;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.CadeiraFacade;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author jonatas
 */
@Named
@ViewScoped
public class CadeiraControle extends AbstractControle<Cadeira> implements Serializable {

    private Cadeira cadeira = new Cadeira();

    @Inject
    private CadeiraFacade cadeiraFacade;

    @Override
    public String salvar() {
        try {
            cadeiraFacade.salvar(cadeira);
            Messages.addGlobalInfo("Cadeira Salva com Sucesso !");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void onRowEdit() {
        try {
            cadeiraFacade.salvar(cadeira);
            Messages.addGlobalInfo("Cadeira Editada com Sucesso !");
        } catch (Exception e) {
            Messages.addGlobalError("Erro ao Editar");
        }
    }

    public CadeiraControle() {
        super(Cadeira.class);
    }

    @Override
    public AbstractFacade<Cadeira> getFacade() {
        return cadeiraFacade;
    }

    public Cadeira getCadeira() {
        return cadeira;
    }

    public void setCadeira(Cadeira cadeira) {
        this.cadeira = cadeira;
    }
}
