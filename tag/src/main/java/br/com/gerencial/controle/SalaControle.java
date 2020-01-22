/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Sala;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.SalaFacade;
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
public class SalaControle extends AbstractControle<Sala> implements Serializable{
    
    private Sala sala = new Sala();
    
    @Inject
    private SalaFacade salaFacade;
    
    @Override
    public String salvar(){
        try {
            salaFacade.salvar(sala);
            Messages.addGlobalInfo("Sala Salva com Sucesso !");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void onRowEdit() {
        try {
            salaFacade.salvar(sala);
            Messages.addGlobalInfo("Sala Editada com Sucesso !");
        } catch (Exception e) {
            Messages.addGlobalError("Erro ao Editar");
        }
    }

    public SalaControle() {
        super(Sala.class);
    }

    @Override
    public AbstractFacade<Sala> getFacade() {
        return salaFacade;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
