/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Pessoa;
import br.com.gerencial.entidade.PessoaFisica;
import br.com.gerencial.entidade.PessoaJuridica;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.PessoaFacade;
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
public class PessoaControle extends AbstractControle<Pessoa> implements Serializable {

    private Pessoa pessoa;

    private PessoaFisica pessoaFisica = new PessoaFisica();

    @Inject
    private PessoaFacade pessoaFacade;

    @Inject
    private PessoaJuridicaControle pessoaJuridicaControle;

    @Inject
    private PessoaFisicaControle pessoaFisicaControle;

    public PessoaControle() {
        super(Pessoa.class);
    }
    
    public void excluir(Pessoa p) throws Exception{
        if (p instanceof PessoaFisica) {
            pessoaFisicaControle.excluir((PessoaFisica) p);
            Messages.addGlobalInfo("PF excluído com Sucesso !");
        }
        
        else{
            pessoaJuridicaControle.excluir((PessoaJuridica) p);
            Messages.addGlobalInfo("PJ excluído com Sucesso !");
        }
    }
    
    public String editar(Pessoa p) {
        if (p instanceof PessoaFisica) {
            pessoaFisicaControle.editar((PessoaFisica) p);
            return "EditaPessoaFisica";
        } else {
            pessoaJuridicaControle.editar((PessoaJuridica) p);
            return "EditaPessoaJuridica";
        }
    }
    
    public String verPaciente(Pessoa p){
        if (p instanceof PessoaFisica) {
            pessoaFisicaControle.editar((PessoaFisica) p);
            return "DadosPaciente";
        }
        
        else{
            Messages.addGlobalInfo("Está Pessoa não é um Paciente");
            return null;
        }
    }
    
    public List<Pessoa> listaPessoa(){
        return pessoaFacade.listaTodos();
    }
    
    @Override
    public AbstractFacade<Pessoa> getFacade() {
        return pessoaFacade;
    }

    public List<Pessoa> pessoaAutoComplete(String nome) {
        return pessoaFacade.pessoaAutoComplete(nome);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }
}
