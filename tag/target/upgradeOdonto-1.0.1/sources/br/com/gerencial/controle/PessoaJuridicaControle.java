/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.entidade.Cidade;
import br.com.gerencial.entidade.Estado;
import br.com.gerencial.entidade.PessoaJuridica;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.CidadeFacade;
import br.com.gerencial.facade.EstadoFacade;
import br.com.gerencial.facade.PessoaJuridicaFacade;
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
public class PessoaJuridicaControle extends AbstractControle<PessoaJuridica> implements Serializable{
    
    @Inject
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    
    private PessoaJuridica pessoaJuridica = new PessoaJuridica();
    
    @Inject
    private CidadeFacade cidadeFacade;
    
    @Inject
    private EstadoFacade estadoFacade;
    
    private ConverterGenerico cidadeConverter;
    private ConverterGenerico estadoConverter;

    public PessoaJuridicaControle() {
        super(PessoaJuridica.class);
    }

    @Override
    public AbstractFacade<PessoaJuridica> getFacade() {
       return pessoaJuridicaFacade;
    }
    
    @Override
    public String salvar(){
        try {
            pessoaJuridicaFacade.salvar(pessoaJuridica);
            Messages.addGlobalInfo("Registro salvo com Sucesso !");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Salvar !");
        }
        return null;
    }
    
    public void excluir(PessoaJuridica pj) throws Exception{
        pessoaJuridicaFacade.excluir(pj);
    }
    
    public void editar(PessoaJuridica pj){
        this.pessoaJuridica = pj;
    }
    
    public List<Cidade> listaFiltrando(String parte){
        return cidadeFacade.listaFiltrando(parte, "nome");
    }
    
    public List<Estado> listaEstados(){
        return estadoFacade.listaTodos();
    }

    public ConverterGenerico getCidadeConverter() {
        if (cidadeConverter == null) {
            cidadeConverter = new ConverterGenerico(cidadeFacade);
        }
        return cidadeConverter;
    }

    public ConverterGenerico getEstadoConverter() {
        if (estadoConverter == null) {
            estadoConverter = new ConverterGenerico(estadoFacade);
        }
        return estadoConverter;
    }

    public PessoaJuridicaFacade getPessoaJuridicaFacade() {
        return pessoaJuridicaFacade;
    }

    public void setPessoaJuridicaFacade(PessoaJuridicaFacade pessoaJuridicaFacade) {
        this.pessoaJuridicaFacade = pessoaJuridicaFacade;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }
}
