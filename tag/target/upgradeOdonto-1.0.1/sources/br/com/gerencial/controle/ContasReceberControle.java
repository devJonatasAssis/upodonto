/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.ContasReceber;
import br.com.gerencial.enumerated.ContasPagarStatus;
import br.com.gerencial.enumerated.TipoPagamento;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.ContaReceberFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
public class ContasReceberControle extends AbstractControle<ContasReceber> implements Serializable {

    private ContasReceber contasReceber = new ContasReceber();

    private ContasReceber contasReceberEdicao;

    @Inject
    private ContaReceberFacade contasReceberFacade;

    private final String tipoConta = "A";

    private Boolean receberConta;

    public ContasReceberControle() {
        super(ContasReceber.class);
    }

    @Override
    public AbstractFacade<ContasReceber> getFacade() {
        return contasReceberFacade;
    }

    public List<ContasReceber> listaReceber() {
        return contasReceberFacade.listaTodos();
    }

    @Override
    public void novo() {
        setReceberConta(Boolean.FALSE);
        contasReceber.setCrDataLancamento(new Date());
        contasReceber.setCrDataVencimento(new Date());

    }

    @Override
    public void alterarForm() {
            contasReceber.setContasPagarStatus(ContasPagarStatus.RECEBIDO);
            contasReceber.setCrDataRecebimento(new Date());
            setReceberConta(Boolean.TRUE);
            Messages.addGlobalInfo("Conta Recebida com Sucesso !");

    }

    public void editar(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    public void atualiza() {
        if (tipoConta.equals("R")) {
            contasReceber.setCrDataRecebimento(new Date());
            contasReceber.setContasPagarStatus(ContasPagarStatus.RECEBIDO);
        } else {
            contasReceber.setCrDataRecebimento(null);
            contasReceber.setContasPagarStatus(ContasPagarStatus.ABERTO);
        }
    }

    public TipoPagamento[] getTipoPagamentos() {
        return TipoPagamento.values();
    }

    public ContasPagarStatus[] getReceberPagarStatus() {
        return ContasPagarStatus.values();
    }

    public Boolean getReceberConta() {
        return receberConta;
    }

    public void setReceberConta(Boolean receberConta) {
        this.receberConta = receberConta;
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public ContasReceber getContasReceberEdicao() {
        return contasReceberEdicao;
    }

    public void setContasReceberEdicao(ContasReceber contasReceberEdicao) {
        this.contasReceberEdicao = contasReceberEdicao;
    }
}
