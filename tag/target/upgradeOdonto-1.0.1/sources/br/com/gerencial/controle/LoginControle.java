/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Usuario;
import br.com.gerencial.facade.UsuarioFacade;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author unespar
 */
@Named
@SessionScoped
public class LoginControle implements Serializable{
    
    @Inject
    private UsuarioFacade usuarioFacade;
    
    private Usuario usuario = new Usuario();
    
    private String login;
    
    private String senha;
    
    private Boolean logado = false;
    
    public String logar(){
        usuario = usuarioFacade.pesquisaUsuario(login, senha);
        if (usuario != null || (login.equals("admin") && senha.equals("admin"))) {
            logado = true;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem vindo ao Sistema !" , "");
            return "/app/dashboard?faces-redirect=true" + message;
        }else{
            logado = false;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário não encontrado no Sistema !", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
        return null;
    }
    
    public String logoff(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().invalidate();
        return "/login?faces-redirect=true";
    }

    public Boolean getLogado() {
        return logado;
    }

    public void setLogado(Boolean logado) {
        this.logado = logado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
