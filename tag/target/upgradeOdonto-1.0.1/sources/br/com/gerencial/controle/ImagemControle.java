/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author unespar
 */
@Named
@RequestScoped
public class ImagemControle {
    
    @ManagedProperty("#{param.caminho}")
    private String caminho;
    
    private StreamedContent foto;

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public StreamedContent getFoto() throws IOException {
        if (caminho == null || caminho.isEmpty()) {
            Path path = Paths.get("/home/jonatas/Documentos/UpgradeSistemas/upodonto/UpOdonto/UpOdonto/tag/src/main/webapp/resources/manhattan-layout/images/branco.jpg");
            InputStream stream = Files.newInputStream(path);
            foto = new DefaultStreamedContent(stream);
        }
        else{
            Path path = Paths.get(caminho);
            InputStream stream = Files.newInputStream(path);
            foto = new DefaultStreamedContent(stream);
        }
        return foto;
    }

    public void setFoto(StreamedContent foto) {
        this.foto = foto;
    }
    
}
