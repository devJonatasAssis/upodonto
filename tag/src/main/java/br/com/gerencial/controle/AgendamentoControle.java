/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.entidade.Agendamento;
import br.com.gerencial.entidade.Cadeira;
import br.com.gerencial.entidade.PessoaFisica;
import br.com.gerencial.entidade.Sala;
import br.com.gerencial.enumerated.StatusAgendamento;
import br.com.gerencial.enumerated.TipoConsulta;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.AgendamentoFacade;
import br.com.gerencial.facade.CadeiraFacade;
import br.com.gerencial.facade.PessoaFisicaFacade;
import br.com.gerencial.facade.SalaFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author unespar
 */
@Named
@ViewScoped
public class AgendamentoControle extends AbstractControle<Agendamento> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Agendamento agendamento = new Agendamento();

    private PessoaFisica pessoa = new PessoaFisica();

    @Inject
    private EnvioEmailControle envioEmail;

    @Inject
    private AgendamentoFacade agendamentoFacade;

    @Inject
    private PessoaFisicaFacade pessoaFisicaFacade;
    
    @Inject
    private CadeiraFacade cadeiraFacade;
    
    @Inject
    private SalaFacade salaFacade;
    
    private List<Agendamento> listagem;

    private ScheduleModel agendaModel;

    private ScheduleEvent agendaEvent = new DefaultScheduleEvent();

    private String filtro = "";

    private String tipoDeBusca = "T";

    private Boolean enviarEmail = true;


    private ConverterGenerico pacienteConverter;
    
    private ConverterGenerico salaConverter;
    
    private ConverterGenerico cadeiraConverter;

    @PostConstruct
    public void init() {
        try {
            carregaLista();
            envioEmail.envioEmail();
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Inicializar");
        }
    }

    public void quandoSelecionado(SelectEvent selectEvent) {
        ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();

        for (Agendamento agd : listagem) {
            if (Objects.equals(agd.getId(), (Long) event.getData())) {
                agendamento = agd;
                break;
            }
        }
    }

    public void novoAgendamento(SelectEvent event) {
        agendamento = new Agendamento();
        agendamento.setDtInicial((Date) event.getObject());
        agendamento.setDtFinal((Date) event.getObject());
    }

    public void editar(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    @Override
    public String salvar() {
        try {
            if (agendamento.getDtInicial().getTime() <= agendamento.getDtFinal().getTime()) {
                agendamentoFacade.salvar(agendamento);
                Messages.addGlobalInfo("Agendamento concluído com Sucesso");
                if (pessoa.getRecebeEmail() == true) {
                    envioEmail.enviarEmail("Agendamento", getMsg(), agendamento.getPessoaFisica().getEmail());
                }

            } else {
                Messages.addGlobalError("Impossível salvar um Agendamento , cujo a Data Inicial seja maior que a Data Final");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Salvar");
        }

        return null;
    }

    private String getMsg() throws ParseException {
        StringBuilder Msg = new StringBuilder();
        String df = new SimpleDateFormat("dd/MM/yyyy").format(agendamento.getDtInicial());
        String df1 = new SimpleDateFormat("dd/MM/yyyy").format(agendamento.getDtFinal());
        String df2 = new SimpleDateFormat("HH:mm").format(agendamento.getHoraInicial());
        String df3 = new SimpleDateFormat("HH:mm").format(agendamento.getHoraFinal());
        Msg.append("Seu agendamento está marcado para o dia: ").append(df).append(" Às: ").append(df2);
        Msg.append("\n\n");
        Msg.append("Até o dia: ").append(df1).append(" Às: ").append(df3);
        Msg.append("\n\n");
        Msg.append("Tipo Consulta: ").append(agendamento.getTipoConsulta());
        Msg.append("\n\n");
        Msg.append("Status da Consulta: ").append(agendamento.getStatus());
        Msg.append("\n\n");
        Msg.append("Observação: ").append(agendamento.getObs_agendamento());
        return Msg.toString();
    }

    public void alterarStatus(Agendamento ag) {
            agendamento.setStatus(StatusAgendamento.Atendido);
            Messages.addGlobalInfo("Paciente Atendido com Sucesso !");
    }

    public void mover(ScheduleEntryMoveEvent move) {
        for (Agendamento ag : listagem) {
            if (ag.getId() == (Long) move.getScheduleEvent().getData()) {
                agendamento = ag;
                try {
                    agendamentoFacade.editar(agendamento);
                    Messages.addGlobalInfo("Movido com Sucesso !");
                } catch (Exception e) {
                    e.printStackTrace();
                    Messages.addGlobalError("Erro ao Mover !");
                }

                break;
            }
        }
    }

    public void redimensionar(ScheduleEntryResizeEvent resize) {
        for (Agendamento ag : listagem) {
            if (ag.getId() == (Long) resize.getScheduleEvent().getData()) {
                agendamento = ag;
                try {
                    agendamentoFacade.editar(agendamento);
                    Messages.addGlobalInfo("Redimensionado com Sucesso !");
                } catch (Exception e) {
                    e.printStackTrace();
                    Messages.addGlobalError("Erro ao Mover !");
                }

                break;
            }
        }
    }

    public void pesquisarPaciente() {
        agendamentoFacade.listaFiltrando(filtro, "pessoaFisica.nome");
    }

    public List<PessoaFisica> listaPacienteTrue() {
        return pessoaFisicaFacade.listaPacienteTrue();
    }
    
    public List<Cadeira> listaFiltrandoCadeira(String parte){
        return cadeiraFacade.listaFiltrando(parte, "descricao");
    }
    
    public List<Sala> listaFiltrandoSala(String parte){
        return salaFacade.listaFiltrando(parte, "descricao");
    }

    public void carregaLista() {
        agendaModel = new DefaultScheduleModel();
        if (tipoDeBusca.equals("T")) {
            listagem = agendamentoFacade.listaTodos();
        } else if (tipoDeBusca.equals("N")) {
            listagem = agendamentoFacade.listaFiltrando(filtro, "pessoaFisica.nome");
        } else {
            listagem = agendamentoFacade.listaFiltrandoPessoa(pessoa);
        }

        for (Agendamento ag : listagem) {
            DefaultScheduleEvent evt = new DefaultScheduleEvent();
            evt.setStartDate(ag.getDtInicial());
            evt.setEndDate(ag.getDtFinal());
            evt.setTitle(ag.getPessoaFisica().getNome());
            evt.setData(ag.getId());
            evt.setDescription(ag.getObs_agendamento());
            evt.setAllDay(true);
            evt.setEditable(true);

            switch (ag.getStatus()) {
                case Confirmado:
                    evt.setStyleClass("statusConfirmado");
                    break;
                case Confirmar:
                    evt.setStyleClass("statusConfirmar");
                    break;
                case Desmarcou:
                    evt.setStyleClass("statusDesmarcou");
                    break;
                case EmAtendimento:
                    evt.setStyleClass("statusEmAtendimento");
                    break;
                case Atendido:
                    evt.setStyleClass("statusAtendido");
                    break;
                case Faltou:
                    evt.setStyleClass("statusFaltou");
                    break;
                case Aguardando:
                    evt.setStyleClass("statusAguardando");
                    break;
                default:
                    evt.setStyleClass("status2");
                    break;
            }

            agendaModel.addEvent(evt);
        }
    }

    public AgendamentoControle() {
        super(Agendamento.class);
    }

    @Override
    public AbstractFacade<Agendamento> getFacade() {
        return agendamentoFacade;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public ScheduleModel getAgendaModel() {
        return agendaModel;
    }

    public void setAgendaModel(ScheduleModel agendaModel) {
        this.agendaModel = agendaModel;
    }

    public ScheduleEvent getAgendaEvent() {
        return agendaEvent;
    }

    public void setAgendaEvent(ScheduleEvent agendaEvent) {
        this.agendaEvent = agendaEvent;
    }

    public ConverterGenerico getPacienteConverter() {
        if (pacienteConverter == null) {
            pacienteConverter = new ConverterGenerico(pessoaFisicaFacade);
        }
        return pacienteConverter;
    }

    public void setPacienteConverter(ConverterGenerico pacienteConverter) {
        this.pacienteConverter = pacienteConverter;
    }

    public StatusAgendamento[] statusAgendamentos() {
        return StatusAgendamento.values();
    }

    public TipoConsulta[] tipoConsultas() {
        return TipoConsulta.values();
    }

    public List<Agendamento> getListagem() {
        return listagem;
    }

    public void setListagem(List<Agendamento> listagem) {
        this.listagem = listagem;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getTipoDeBusca() {
        return tipoDeBusca;
    }

    public void setTipoDeBusca(String tipoDeBusca) {
        this.tipoDeBusca = tipoDeBusca;
    }

    public PessoaFisica getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }

    public Boolean getEnviarEmail() {
        return enviarEmail;
    }

    public void setEnviarEmail(Boolean enviarEmail) {
        this.enviarEmail = enviarEmail;
    }

    public EnvioEmailControle getEnvioEmail() {
        return envioEmail;
    }

    public void setEnvioEmail(EnvioEmailControle envioEmail) {
        this.envioEmail = envioEmail;
    }

    public ConverterGenerico getCadeiraConverter() {
        if (cadeiraConverter == null) {
            cadeiraConverter = new ConverterGenerico(cadeiraFacade);
        }
        return cadeiraConverter;
    }

    public ConverterGenerico getSalaConverter() {
        if (salaConverter == null) {
            salaConverter = new ConverterGenerico(salaFacade);
        }
        return salaConverter;
    }
}
