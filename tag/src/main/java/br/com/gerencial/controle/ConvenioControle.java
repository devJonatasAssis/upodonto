/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.entidade.Cidade;
import br.com.gerencial.entidade.Estado;
import br.com.gerencial.entidade.Convenio;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.CidadeFacade;
import br.com.gerencial.facade.EstadoFacade;
import br.com.gerencial.facade.ConvenioFacade;
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
public class ConvenioControle extends AbstractControle<Convenio> implements Serializable{
    
    private Convenio convenio = new Convenio();
    
    @Inject
    private ConvenioFacade planoFacade;
    
    @Inject
    private EstadoFacade estadoFacade;
    
    @Inject
    private CidadeFacade cidadeFacade;
    
    @Inject
    private ConvenioFacade convenioFacade;
    
    private ConverterGenerico estadoConverter;
    
    private ConverterGenerico cidadeConverter;

    public ConvenioControle() {
        super(Convenio.class);
    }

    @Override
    public AbstractFacade<Convenio> getFacade() {
        return planoFacade;
    }
    
    @Override
    public String salvar(){
        try {
            planoFacade.salvar(convenio);
            Messages.addGlobalInfo("Plano de Saúde cadastrado com Sucesso !");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Salvar !");
        }
        
        return null;
    }
    
    public List<Convenio> listaConvenio(){
        return convenioFacade.listaTodos();
    }

    public ConverterGenerico getEstadoConverter() {
        if (estadoConverter == null) {
            estadoConverter = new ConverterGenerico(estadoFacade);
        }
        return estadoConverter;
    }

    public ConverterGenerico getCidadeConverter() {
        if (cidadeConverter == null) {
            cidadeConverter = new ConverterGenerico(cidadeFacade);
        }
        return cidadeConverter;
    }
    
    public List<Cidade> listaFiltrando(String parte){
        return cidadeFacade.listaFiltrando(parte, "nome");
    }
    
    public List<Estado> listaEstados(){
        return estadoFacade.listaTodos();
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }
}
