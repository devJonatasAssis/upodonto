/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.entidade.Banco;
import br.com.gerencial.entidade.Cidade;
import br.com.gerencial.entidade.Estado;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.BancoFacade;
import br.com.gerencial.facade.CidadeFacade;
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
public class BancoControle extends AbstractControle<Banco> implements Serializable{
    
    private Banco banco = new Banco();
    
    @Inject
    private EstadoFacade estadoFacade;
    
    @Inject 
    private CidadeFacade cidadeFacade;
    
    private ConverterGenerico converterEstado;
    private ConverterGenerico converterCidade;
    
    @Inject
    private BancoFacade bancoFacade;

    public BancoControle() {
        super(Banco.class);
    }

    @Override
    public AbstractFacade<Banco> getFacade() {
        return bancoFacade;
    }
    
    @Override
    public String salvar(){
        try {
            bancoFacade.salvar(banco);
            banco = new Banco();
            Messages.addGlobalInfo("Registro salvo com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalInfo("Erro ao Salvar");
        }
        
        return null;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public BancoFacade getBancoFacade() {
        return bancoFacade;
    }

    public void setBancoFacade(BancoFacade bancoFacade) {
        this.bancoFacade = bancoFacade;
    }

    public ConverterGenerico getConverterEstado() {
        if (converterEstado == null) {
            converterEstado = new ConverterGenerico(estadoFacade);
        }
        return converterEstado;
    }
    
    public List<Estado> listaEstados(){
        return estadoFacade.listaTodos();
    }

    public ConverterGenerico getConverterCidade() {
        if (converterCidade == null) {
            converterCidade = new ConverterGenerico(cidadeFacade);
        }
        return converterCidade;
    }
    
    public List<Cidade> listaFiltrando(String parte){
        return cidadeFacade.listaFiltrando(parte, "nome");
    }
}
