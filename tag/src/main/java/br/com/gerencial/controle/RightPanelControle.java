/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Agendamento;
import br.com.gerencial.entidade.Empresa;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author unespar-ti
 */
@Named
@ViewScoped
public class RightPanelControle implements Serializable {

    @Inject
    private EntityManager em;
    
    private Agendamento agendamento = new Agendamento();
    
    @Inject
    private EnvioEmailControle envioEmail;

//    public String field(String campo) {
//        Query q = em.createQuery("SELECT " + campo + " FROM Empresa");
//        return q.getSingleResult().toString();
//    }

    private List<Agendamento> agendamentos;

    @PostConstruct
    public void init() {
        try {
            agendamentos = new ArrayList<>();
            agendamentos = pacientesEmEspera();
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("erro");
        }

    }
    
    public void enviarEmailLembranca(Agendamento agendamento){
        try {
            envioEmail.enviarEmail("Lembrança de Consulta", msg(agendamento), agendamento.getPessoaFisica().getEmail());
            Messages.addGlobalInfo("Mensagem de Lembrança enviada com Sucesso !");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Enviar Mensagem");
        }
    }
    
    private String msg(Agendamento agendamento) throws ParseException {
        StringBuilder Msg = new StringBuilder();
        String df = new SimpleDateFormat("dd/MM/yyyy").format(agendamento.getDtInicial());
        String df1 = new SimpleDateFormat("dd/MM/yyyy").format(agendamento.getDtFinal());
        String df2 = new SimpleDateFormat("HH:mm").format(agendamento.getHoraInicial());
        String df3 = new SimpleDateFormat("HH:mm").format(agendamento.getHoraFinal());
        Msg.append("Olá viemos aqui pra te lembrar da sua consulta que é no dia : ").append(df).append(" Às: ").append(df2);;
        Msg.append("\n\n");
        Msg.append("Até o dia: ").append(df1).append(" Às: ").append(df3);;
        Msg.append("\n\n");
        Msg.append("Tipo Consulta: ").append(agendamento.getTipoConsulta());
        Msg.append("\n\n");
        Msg.append("Status da Consulta: ").append(agendamento.getStatus());
        Msg.append("\n\n");
        Msg.append("Observação: ").append(agendamento.getObs_agendamento());
        return Msg.toString();
    }

    public List<Agendamento> pacientesEmEspera() {
        Query q = em.createQuery("FROM Agendamento WHERE dtInicial = current_date ORDER BY horaInicial ASC");
        return q.getResultList();
    }
    
    public List<Agendamento> pacientesDeAmanha() {
        Query q = em.createQuery("FROM Agendamento WHERE dtInicial = current_date +1 ORDER BY horaInicial ASC");
        return q.getResultList();
    }

    public List<Agendamento> pacientesEmAtendimento() {
        Query q = em.createQuery("FROM Agendamento WHERE status='EmAtendimento'");
        return q.getResultList();
    }

    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Paciente Selecionado", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", event.getObject().toString()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Lista Reordenada", null));
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
}
