/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Usuario;
import br.com.gerencial.enumerated.NivelAcesso;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.UsuarioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author unespar
 */
@Named
@SessionScoped
public class UsuarioControle extends AbstractControle<Usuario> implements Serializable {
    
    private Usuario usuario = new Usuario();
    
    @Inject
    private UsuarioFacade usuarioFacade;
    
    @Override
    public String salvar() {
        try {
            Usuario usuarioRetorno = usuarioFacade.salvar(usuario);
            Path origem = Paths.get(usuario.getCaminho());
            Path destino = Paths.get("/home/jonatas/Documentos/UpgradeSistemas/upodonto/UpOdonto/UpOdonto/tag/src/main/webapp/resources/manhattan-layout/images/"
                    + usuarioRetorno.getId() + ".jpg");
            
            Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
            
            Messages.addGlobalInfo("Usuário salvo com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro");
        }
        
        return null;
    }
    
    public void excluir(Usuario usuario) {
        try {
            usuarioFacade.excluir(usuario);
            Path arquivo = Paths.get("/home/jonatas/Documentos/UpgradeSistemas/upodonto/UpOdonto/UpOdonto/tag/src/main/webapp/resources/manhattan-layout/images/"
                    + usuario.getId() + ".jpg");
            Files.deleteIfExists(arquivo);
            Messages.addGlobalInfo("Excluído com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Excluir");
        }
    }
    
    public void editar(ActionEvent evento) {
        try {
            usuario = (Usuario) evento.getComponent().getAttributes().get(evento);
            usuario.setCaminho("/home/unespar/Documentos/UpgradeSistemas/upodonto/UpOdonto/UpOdonto/tag/src/main/webapp/resources/manhattan-layout/images/"
                    + usuario.getId() + ".jpg");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalInfo("Erro ao Editar");
        }
    }
    
    public void upload(FileUploadEvent event) {
        try {
            UploadedFile fileUpload = event.getFile();
            Path arquivoTemp = Files.createTempFile(null, null);
            Files.copy(fileUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
            usuario.setCaminho(arquivoTemp.toString());
            Messages.addGlobalInfo("Imagem Salva com Sucesso");
            
        } catch (IOException e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro");
        }
        
    }
    
    public List<Usuario> listaUsuario() {
        return usuarioFacade.listaTodos();
    }
    
    public NivelAcesso[] getNiveisAcesso() {
        return NivelAcesso.values();
    }
    
    public UsuarioControle() {
        super(Usuario.class);
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public AbstractFacade<Usuario> getFacade() {
        return usuarioFacade;
    }
}
