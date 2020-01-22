/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.entidade.GrupoProduto;
import br.com.gerencial.entidade.Produto;
import br.com.gerencial.enumerated.Unidade_Medida;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.GrupoProdutoFacade;
import br.com.gerencial.facade.ProdutoFacade;
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
public class ProdutoControle extends AbstractControle<Produto> implements Serializable {

    private Produto produto = new Produto();

    @Inject
    private ProdutoFacade produtoFacade;

    @Inject
    private GrupoProdutoFacade grupoProdutoFacade;

    public ProdutoControle() {
        super(Produto.class);
    }

    @Override
    public AbstractFacade<Produto> getFacade() {
        return produtoFacade;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String salvar() {
        try {
            produtoFacade.salvar(produto);
            Messages.addGlobalInfo("Produto Cadastrado com Sucesso !");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Salvar");
        }

        return null;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Unidade_Medida[] unidade_Medidas() {
        return Unidade_Medida.values();
    }

    private ConverterGenerico converterGrupo;

    public ConverterGenerico getConverterGrupo() {
        if (converterGrupo == null) {
            converterGrupo = new ConverterGenerico(grupoProdutoFacade);
        }
        return converterGrupo;
    }

    public List<GrupoProduto> grupoProdutos() {
        return grupoProdutoFacade.listaTodos();
    }
}
