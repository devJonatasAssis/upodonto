/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.GrupoProduto;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.GrupoProdutoFacade;
import java.io.Serializable;
import java.util.List;
import static javafx.scene.input.KeyCode.G;
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
public class GrupoProdutoControle extends AbstractControle<GrupoProduto> implements Serializable{
    
    private GrupoProduto grupoProduto = new GrupoProduto();
    
    @Inject
    private GrupoProdutoFacade grupoProdutoFacade;

    public GrupoProdutoControle() {
        super(GrupoProduto.class);
    }

    @Override
    public AbstractFacade<GrupoProduto> getFacade() {
        return grupoProdutoFacade;
    }
    
    @Override
    public String salvar(){
        try {
            grupoProdutoFacade.salvar(grupoProduto);
            Messages.addFlashGlobalInfo("Grupo cadastrado com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Salvar");
        }
        
        return null;
    }
    
    public void excluir(GrupoProduto g) throws Exception{
        grupoProdutoFacade.excluir(g);
    }
    
    public List<GrupoProduto> listaGrupoProduto(){
        return grupoProdutoFacade.listaTodos();
    }

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }
}
