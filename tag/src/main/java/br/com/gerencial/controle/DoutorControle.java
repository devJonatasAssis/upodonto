/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Doutor;
import br.com.gerencial.enumerated.Especialista;
import br.com.gerencial.enumerated.SiglaCRO;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.DoutorFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;

/**
 *
 * @author unespar
 */
@Named
@ViewScoped
public class DoutorControle extends AbstractControle<Doutor> implements Serializable{
    
    private Doutor doutor = new Doutor();
    
    @Override
    public String salvar(){
        try {
            doutorFacade.salvar(doutor);
            Messages.addGlobalInfo("Doutor(a) Cadastrado com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Salvar");
        }
        
        return null;
    }
    
    public List<Doutor> listaDoutors(){
        return doutorFacade.listaTodos();
    }
    
    public Especialista[] especialistas(){
        return Especialista.values();
    }
    
    public SiglaCRO[] siglaCROs(){
        return SiglaCRO.values();
    }
    
    @Inject
    private DoutorFacade doutorFacade;

    public DoutorControle() {
        super(Doutor.class);
    }

    @Override
    public AbstractFacade<Doutor> getFacade() {
        return doutorFacade;
    }

    public Doutor getDoutor() {
        return doutor;
    }

    public void setDoutor(Doutor doutor) {
        this.doutor = doutor;
    }
}
