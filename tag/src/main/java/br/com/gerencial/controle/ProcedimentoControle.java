/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.converter.MoneyConverter;
import br.com.gerencial.entidade.Procedimento;
import br.com.gerencial.entidade.ContasReceber;
import br.com.gerencial.entidade.Doutor;
import br.com.gerencial.entidade.Pessoa;
import br.com.gerencial.entidade.PessoaFisica;
import br.com.gerencial.entidade.Produto;
import br.com.gerencial.entidade.Servico;
import br.com.gerencial.entidade.Tratamento;
import br.com.gerencial.enumerated.ContasPagarStatus;
import br.com.gerencial.enumerated.Dente;
import br.com.gerencial.enumerated.Status_Odontograma;
import br.com.gerencial.enumerated.TipoPagamento;
import br.com.gerencial.enumerated.Tipo_Procedimento;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.ProcedimentoFacade;
import br.com.gerencial.facade.DoutorFacade;
import br.com.gerencial.facade.PessoaFisicaFacade;
import br.com.gerencial.facade.ServicoFacade;
import br.com.gerencial.facade.TratamentoFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.validator.constraints.NotBlank;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author unespar
 */
@Named
@ViewScoped
public class ProcedimentoControle extends AbstractControle<Procedimento> implements Serializable {

    private Procedimento procedimento = new Procedimento();
    private ContasReceber contasReceber = new ContasReceber();

    private Tratamento tratamento;

    private String formaPagamento = "V";

    private Date dataVencimento = new Date();
    
    private Boolean odontograma = true;

    @Inject
    private DoutorFacade doutorFacade;

    @Inject
    private PessoaFisicaFacade pessoaFisicaFacade;

    @Inject
    private TratamentoFacade tratamentoFacade;
    
    @Inject
    private ServicoFacade servicoFacade;
    
    @Inject
    private ProcedimentoFacade procedimentoFacade;

    private ConverterGenerico tratamentoConverter;

    private ConverterGenerico pessoaFisicaConverter;

    private ConverterGenerico doutorConverter;
    
    private ConverterGenerico servicoConverter;

    private MoneyConverter moneyConverter;

    public List<Doutor> listaDoutors() {
        return doutorFacade.listaTodos();
    }

    public List<PessoaFisica> listaFisicas() {
        return pessoaFisicaFacade.listaTodos();
    }

    public List<Tratamento> tratamentos(String parte) {
        return tratamentoFacade.listaFiltrando(parte, "nome");
    }
    
    public List<Servico> listaFiltrando(String parte) {
        return servicoFacade.listaFiltrando(parte, "nome");
    }

    @Override
    public String salvar() {
        try {
            procedimentoFacade.salvar(procedimento);
            Messages.addGlobalInfo("Parabéns! Salvo com Sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Salvar");
        }

        return null;
    }

    public void pessoaSelecionado(SelectEvent event) {
        Pessoa pessoa = (Pessoa) event.getObject();
        procedimento.setPessoaFisica((PessoaFisica) pessoa);
    }
    
    public void addItemProcedimento() {
        if (procedimento.getItemProcedimento().getQuantidade() != null
                && procedimento.getItemProcedimento().getQuantidade().compareTo(BigDecimal.ZERO) > 0) {
            procedimento.addItemProcedimento();
        } else {
            Messages.addGlobalError("A Quantidade do Item deve ser superior a zero!");
        }
    }

    public ProcedimentoControle() {
        super(Procedimento.class);
    }

    public void gerarParcelas() {
        procedimento.setContasReceber(new ArrayList<ContasReceber>());
        Date data_parc = dataVencimento;
        for (int i = 1; i <= procedimento.getNum_parcela(); i++) {
            ContasReceber cp = new ContasReceber();
            cp.setCrDataLancamento(new Date());
            cp.setCrNumParcela(i);
            cp.setComecoTratamento(procedimento);
            cp.setTipoPagamento(procedimento.getTipoPagamento());
            cp.setPessoa(procedimento.getPessoaFisica());
            cp.setCrValor(procedimento.getProcedimentoTotalLiquido().doubleValue() / procedimento.getNum_parcela());
            cp.setCrDataVencimento(data_parc);

            cp.setObs("Começo de Tratamento");
            Calendar cal = Calendar.getInstance();
            cal.setTime(data_parc);
            cal.add(Calendar.MONTH, 1);
            data_parc = cal.getTime();

            procedimento.getContasReceber().add(cp);
        }

        if (formaPagamento.equals("P")) {
            contasReceber.setContasPagarStatus(ContasPagarStatus.ABERTO);
            contasReceber.setCrDataVencimento(new Date());
            contasReceber.setCrDataRecebimento(new Date());
        } else {
            contasReceber.setContasPagarStatus(ContasPagarStatus.RECEBIDO);
            contasReceber.setCrDataVencimento(new Date());
            contasReceber.setCrDataRecebimento(new Date());
        }
    }

    public void atualizaParcelas() {
        dataVencimento = new Date();
        procedimento.setNum_parcela(Integer.SIZE);
        if (formaPagamento.equals("P")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataVencimento);
            cal.add(Calendar.MONTH, 1);
            dataVencimento = cal.getTime();
        }
        gerarParcelas();
    }
    
    public void relacionaPreco() {
        BigDecimal preco = procedimento.getItemProcedimento().getServico().getValor();
        procedimento.getItemProcedimento().setIpPrecoBruto(preco);
    }

    public TipoPagamento[] tipoPagamentos() {
        return TipoPagamento.values();
    }
    
    public Status_Odontograma[] status_Odontograma(){
        return Status_Odontograma.values();
    }
    
    public Tipo_Procedimento[] tipo_Procedimentos(){
        return Tipo_Procedimento.values();
    }
    
    public Dente[] dentes(){
        return Dente.values();
    }

    @Override
    public AbstractFacade<Procedimento> getFacade() {
        return procedimentoFacade;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public ConverterGenerico getDoutorConverter() {
        if (doutorConverter == null) {
            doutorConverter = new ConverterGenerico(doutorFacade);
        }
        return doutorConverter;
    }

    public ConverterGenerico getPessoaFisicaConverter() {

        if (pessoaFisicaConverter == null) {
            pessoaFisicaConverter = new ConverterGenerico(pessoaFisicaFacade);
        }
        return pessoaFisicaConverter;
    }

    public ConverterGenerico getTratamentoConverter() {
        if (tratamentoConverter == null) {
            tratamentoConverter = new ConverterGenerico(tratamentoFacade);
        }
        return tratamentoConverter;
    }

    public ConverterGenerico getServicoConverter() {
        if (servicoConverter == null) {
            servicoConverter = new ConverterGenerico(servicoFacade);
        }
        return servicoConverter;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Tratamento getTratamento() {
        return tratamento;
    }

    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    @NotBlank
    public String getNomePaciente() {
        return procedimento.getPessoaFisica() == null
                ? null : procedimento.getPessoaFisica().getNome();
    }

    public void setNomePaciente(String nome) {
    }

    public Boolean getOdontograma() {
        return odontograma;
    }

    public void setOdontograma(Boolean odontograma) {
        this.odontograma = odontograma;
    }
}
