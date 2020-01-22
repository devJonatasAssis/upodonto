/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Estado;
import br.com.gerencial.facade.EstadoFacade;
import java.io.Serializable;
import java.util.List;
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
public class EstadoControle implements Serializable{
    
    private Estado estado = new Estado();
    
    @Inject
    private EstadoFacade estadoFacade;
    
    
    public void salvar(){
        try {
            estadoFacade.salvar(estado);
            Messages.addGlobalInfo("Registro Salvo com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Estado> listaEstados(){
        return estadoFacade.listaTodos();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public EstadoFacade getEstadoFacade() {
        return estadoFacade;
    }

    public void setEstadoFacade(EstadoFacade estadoFacade) {
        this.estadoFacade = estadoFacade;
    }
    
    
}
