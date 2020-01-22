/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Pessoa;
import br.com.gerencial.facade.PessoaFacade;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author unespar-ti
 */
@Named
@ViewScoped
public class SelecaoPessoaControle implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private PessoaFacade pessoaFacade;

    private String nome;

    private List<Pessoa> pessoaFiltrados;

    public void selecionar(Pessoa pessoa) {
        RequestContext.getCurrentInstance().closeDialog(pessoa);
    }

    public void abrirDialogo() {
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", true);
        opcoes.put("draggable", true);
        opcoes.put("contentHeight", 470);

        RequestContext.getCurrentInstance().openDialog("/app/selecaoPessoa", opcoes, null);
    }

    public void pesquisar() {
        pessoaFiltrados = pessoaFacade.porNomeSemelhante(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pessoa> getPessoaFiltrados() {
        return pessoaFiltrados;
    }
}
