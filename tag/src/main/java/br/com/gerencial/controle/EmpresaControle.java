/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Empresa;
import br.com.gerencial.enumerated.TipoEmpresa;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.EmpresaFacade;
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
public class EmpresaControle extends AbstractControle<Empresa> implements Serializable{
    
    private Empresa empresa = new Empresa();
    
    @Inject
    private EmpresaFacade empresaFacade;

    public EmpresaControle() {
        super(Empresa.class);
    }

    @Override
    public AbstractFacade<Empresa> getFacade() {
         return empresaFacade;
    }
    
    @Override
    public String salvar(){
        try {
            empresaFacade.salvar(empresa);
            empresa = new Empresa();
            Messages.addGlobalInfo("Registro Salvo com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Salvar");
        }
        
        return null;
    }
    
    public String excluir(Empresa empresa){
        try {
            empresaFacade.excluir(empresa);
            Messages.addGlobalInfo("Registro Excluído com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Excluir");
        }
        return null;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public TipoEmpresa[] tipoEmpresas(){
        return TipoEmpresa.values();
    }
}
