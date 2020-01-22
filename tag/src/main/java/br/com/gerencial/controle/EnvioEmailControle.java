/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Empresa;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author unespar-ti
 */
@Named
@ViewScoped
public class EnvioEmailControle implements Serializable{
    
    @Inject
    private EntityManager em;

    Email email;
    Empresa empresa;

    @PostConstruct
    public void envioEmail() {    
        configuracao();
    }

    
    private void configuracao() {
        try {
            email = new SimpleEmail();
            email.setHostName(field("servidor_smtp"));
            email.setSmtpPort(Integer.parseInt(field("port_servidor_smtp")));
            email.setDebug(true);
            email.setAuthentication(field("email"), field("senha"));
            email.setSSLOnConnect((field("tipoAutent").equals("SSL")));
            email.setFrom(field("email"), field("nome_visualizao"));
            email.addReplyTo(field("email"), field("nome_visualizao"));
        } catch (EmailException ex) {
            Logger.getLogger(EnvioEmailControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public String field(String campo){
        Query q = em.createQuery("SELECT " + campo + " FROM Config_Email");
        return q.getSingleResult().toString();
    }

    public void enviarEmail(String subject, String msg, String to) {
        final String Subject = subject;
        final String Msg = msg;
        final String To = to;

        new Thread() {
            @Override
            public void run() {
                try {
                    email.setSubject(Subject);
                    email.setMsg(Msg);
                    email.addTo(To);
                    email.send();
                } catch (EmailException ex) {
                    Logger.getLogger(EnvioEmailControle.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }

}
