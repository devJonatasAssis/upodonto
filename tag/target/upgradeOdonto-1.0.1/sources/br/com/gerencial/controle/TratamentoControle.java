/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Tratamento;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.TratamentoFacade;
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
public class TratamentoControle extends AbstractControle<Tratamento> implements Serializable{
    
    private Tratamento tratamento = new Tratamento();
    
    @Inject
    private TratamentoFacade tratamentoFacade;

    public TratamentoControle() {
        super(Tratamento.class);
    }

    @Override
    public AbstractFacade<Tratamento> getFacade() {
        return tratamentoFacade;
    }
    
    @Override
    public String salvar(){
        try {
            tratamentoFacade.salvar(tratamento);
            Messages.addGlobalInfo("Tratamento Salvo com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Salvar");
        }
        
        return null;
    }

    public Tratamento getTratamento() {
        return tratamento;
    }

    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }
}
