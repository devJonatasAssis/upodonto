/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.ContasPagar;
import br.com.gerencial.enumerated.ContasPagarStatus;
import br.com.gerencial.enumerated.TipoPagamento;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.ContasPagarFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;

/**
 *
 * @author unespar
 */
@Named
@ViewScoped
public class ContasPagarControle extends AbstractControle<ContasPagar> implements Serializable {

    private ContasPagar contasPagar = new ContasPagar();
    private ContasPagar contasPagarEdicao;
    private Boolean receberConta;

    @Inject
    private ContasPagarFacade contasPagarFacade;

    public ContasPagarControle() {
        super(ContasPagar.class);
    }

    @Override
    public AbstractFacade<ContasPagar> getFacade() {
        return contasPagarFacade;
    }

    private String tipoConta = "A";

    private Boolean pagar_Conta;

    @Override
    public String salvar() {
        try {
            contasPagarFacade.salvar(contasPagar);
            Messages.addGlobalInfo("Contas a Pagar Registrada com Sucesso !");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Salvar!");
        }

        return null;
    }

    @Override
    public void novo() {
        super.novo();
        setPagar_Conta(Boolean.FALSE);
        super.getEntidade().setData_lancamento(new Date());
        super.getEntidade().setData_vencimento(new Date());
    }

    @Override
    public void alterarForm() {
        contasPagar.setContasPagarStatus(ContasPagarStatus.RECEBIDO);
        contasPagar.setData_pagamento(new Date());
        setReceberConta(Boolean.TRUE);
        Messages.addGlobalInfo("Conta Paga com Sucesso !");
    }

    public void atualiza() {
        if (tipoConta.equals("P")) {
            super.getEntidade().setData_pagamento(new Date());
            super.getEntidade().setContasPagarStatus(ContasPagarStatus.PAGO);
        } else {
            super.getEntidade().setData_pagamento(null);
            super.getEntidade().setContasPagarStatus(ContasPagarStatus.ABERTO);
        }
    }
    
    public List<ContasPagar> listaTodasContas(){
        return contasPagarFacade.listaTodos();
    }

    public TipoPagamento[] getTiposPagamento() {
        return TipoPagamento.values();
    }

    public ContasPagarStatus[] getContasPagarStatus() {
        return ContasPagarStatus.values();
    }

    public ContasPagar getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(ContasPagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public Boolean getPagar_Conta() {
        return pagar_Conta;
    }

    public void setPagar_Conta(Boolean pagar_Conta) {
        this.pagar_Conta = pagar_Conta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public ContasPagar getContasPagarEdicao() {
        return contasPagarEdicao;
    }

    public void setContasPagarEdicao(ContasPagar contasPagarEdicao) {
        this.contasPagarEdicao = contasPagarEdicao;
    }

    public Boolean getReceberConta() {
        return receberConta;
    }

    public void setReceberConta(Boolean receberConta) {
        this.receberConta = receberConta;
    }
}
