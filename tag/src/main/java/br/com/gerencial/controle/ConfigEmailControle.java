/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Config_Email;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.ConfigEmailFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;

/**
 *
 * @author unespar-ti
 */
@Named
@ViewScoped
public class ConfigEmailControle extends AbstractControle<Config_Email> implements Serializable{
    
    private Config_Email config_Email = new Config_Email();
    
    @Inject
    private ConfigEmailFacade configEmailFacade;

    public ConfigEmailControle() {
        super(Config_Email.class);
    }

    @Override
    public AbstractFacade<Config_Email> getFacade() {
        return configEmailFacade;
    }
    
    @Override
    public String salvar(){
        try {
            configEmailFacade.salvar(config_Email);
            Messages.addGlobalInfo("Email configurado com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro");
        }
        return null;
    }
    
    public String excluir(Config_Email config_Email){
        try {
            configEmailFacade.excluir(config_Email);
            Messages.addGlobalInfo("Email Excluído com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro");
        }
        
        return null;
    }
    
    public List<Config_Email> listaTodos(){
        return configEmailFacade.listaTodos();
    }

    public Config_Email getConfig_Email() {
        return config_Email;
    }

    public void setConfig_Email(Config_Email config_Email) {
        this.config_Email = config_Email;
    }
}
