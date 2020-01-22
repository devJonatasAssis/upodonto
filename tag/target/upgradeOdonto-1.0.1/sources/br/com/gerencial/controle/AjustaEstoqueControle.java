/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.entidade.AjustaEstoque;
import br.com.gerencial.entidade.Produto;
import br.com.gerencial.enumerated.TipoAjusteEstoque;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.AjustaFacade;
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
public class AjustaEstoqueControle extends AbstractControle<AjustaEstoque> implements Serializable{
    
    private AjustaEstoque ajustaEstoque = new AjustaEstoque();
    private Produto produto = new Produto();
    
    @Inject
    private AjustaFacade ajustaFacade;
    
    @Inject
    private ProdutoFacade produtoFacade;
    
    private String tipoAjuste = "A";
    
    private ConverterGenerico produtoConverter;
    
    public List<Produto> listaProduto(){
        return produtoFacade.listaTodos();
    }
    
    public TipoAjusteEstoque[] tipoAjusteEstoques(){
        return TipoAjusteEstoque.values();
    }
    
    @Override
    public String salvar(){
        try {
            if (ajustaEstoque.getAjusteMotivo().isEmpty()) {
                Messages.addGlobalError("Motivo do Ajuste  éObrigatório !");
            }
            else{
                ajustaFacade.salvar(ajustaEstoque);
                Messages.addGlobalInfo("Ajuste realizado com Sucesso !");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalInfo("Erro ao Salvar");
        }
        
        return null;
    }

    public AjustaEstoqueControle() {
        super(AjustaEstoque.class);
    }

    @Override
    public AbstractFacade<AjustaEstoque> getFacade() {
        return ajustaFacade;
    }

    public AjustaEstoque getAjustaEstoque() {
        return ajustaEstoque;
    }

    public void setAjustaEstoque(AjustaEstoque ajustaEstoque) {
        this.ajustaEstoque = ajustaEstoque;
    }

    public String getTipoAjuste() {
        return tipoAjuste;
    }

    public void setTipoAjuste(String tipoAjuste) {
        this.tipoAjuste = tipoAjuste;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ConverterGenerico getProdutoConverter() {
        if (produtoConverter == null) {
            produtoConverter = new ConverterGenerico(produtoFacade);
        }
        return produtoConverter;
    }

    public void setProdutoConverter(ConverterGenerico produtoConverter) {
        this.produtoConverter = produtoConverter;
    }
}
