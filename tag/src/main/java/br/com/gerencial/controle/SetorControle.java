/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.entidade.Setor;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.SetorFacade;
import br.com.gerencial.util.Conexao;
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
public class SetorControle extends AbstractControle<Setor> implements Serializable{
    
    private Setor setor = new Setor();
    
    @Inject
    private SetorFacade setorFacade;

    public SetorControle() {
        super(Setor.class);
    }

    @Override
    public AbstractFacade<Setor> getFacade() {
        return setorFacade;
    }
    
    @Override
    public String salvar(){
        try {
            setorFacade.salvar(setor);
            Messages.addGlobalInfo("Setor Cadastrado com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
    
}
