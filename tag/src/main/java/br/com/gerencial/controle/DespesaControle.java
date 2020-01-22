/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Despesa;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.DespesaFacade;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;

/**
 *
 * @author jonatas
 */
@Named
@ViewScoped
public class DespesaControle extends AbstractControle<Despesa> implements Serializable{
    
    private Despesa despesa = new Despesa();
    
    @Inject
    private DespesaFacade despesaFacade;

    public DespesaControle() {
        super(Despesa.class);
    }

    @Override
    public AbstractFacade<Despesa> getFacade() {
        return despesaFacade;
    }
    
    @Override
    public String salvar(){
        try {
            despesaFacade.salvar(despesa);
            Messages.addGlobalInfo("Despesa Cadastrada com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Salvar");
        }
        
        return null;
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }
}
