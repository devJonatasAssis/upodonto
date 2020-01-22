/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Servico;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.ServicoFacade;
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
public class ServicoControle extends AbstractControle<Servico> implements Serializable{
    
    @Inject
    private ServicoFacade servicoFacade;

    public ServicoControle() {
        super(Servico.class);
    }

    @Override
    public AbstractFacade<Servico> getFacade() {
        return servicoFacade;
    }
    
    private Servico servico = new Servico();
    
    @Override
    public String salvar(){
        try {
            servicoFacade.salvar(servico);
            Messages.addGlobalInfo("Serviço Cadastrado com Sucesso !");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Salvar");
        }
 
        return null;
    }

    public ServicoFacade getServicoFacade() {
        return servicoFacade;
    }

    public void setServicoFacade(ServicoFacade servicoFacade) {
        this.servicoFacade = servicoFacade;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
