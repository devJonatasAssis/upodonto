/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.converter.MoneyConverter;
import br.com.gerencial.entidade.Compra;
import br.com.gerencial.entidade.CompraItem;
import br.com.gerencial.entidade.ContasPagar;
import br.com.gerencial.entidade.Pessoa;
import br.com.gerencial.entidade.PessoaJuridica;
import br.com.gerencial.entidade.Produto;
import br.com.gerencial.enumerated.CompraTipo;
import br.com.gerencial.enumerated.ContasPagarStatus;
import br.com.gerencial.enumerated.StatusCompraVenda;
import br.com.gerencial.enumerated.TipoPagamento;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.CompraFacade;
import br.com.gerencial.facade.PessoaFacade;
import br.com.gerencial.facade.ProdutoFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class CompraControle extends AbstractControle<Compra> implements Serializable {

    private Compra compra = new Compra();

    private CompraItem compraItem = new CompraItem();

    private ContasPagar contasPagar = new ContasPagar();

    private String formaPagamento = "V";

    private Long numParcela = 1L;

    private Date dataVencimento = new Date();

    private ConverterGenerico pessoaConverter;
    private ConverterGenerico produtoConverter;
    private MoneyConverter moneyConverter;

    @Inject
    private PessoaFacade pessoaFacade;

    @Inject
    private ProdutoFacade produtoFacade;

    @Override
    public String salvar() {
        try {
            compraFacade.salvar(compra);
            System.out.println("Create");
            for (CompraItem item : compra.getCompraItems()) {
                System.out.println("QTD ANTES: " + item.getProduto().getEstoque());
                item.getProduto().adicionarEstoque(item.getIcQuantidade());
                System.out.println("QTD DEPOIS: " + item.getProduto().getEstoque());
                produtoFacade.salvar(item.getProduto());
                System.out.println("QTD DEPOIS AINDA: " + item.getProduto().getEstoque());
            }
            Messages.addGlobalInfo("Compra Realizada com Sucesso !");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao realizar a compra !");
        }
        return null;
    }

    public String excluir(Compra compra) {
        try {
            compraFacade.excluir(compra);
            Messages.addGlobalInfo("Registro excluído com Sucesso !");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Excluir");
        }

        return null;
    }

    public String editar(Compra compra) {
        try {
            this.compra = compra;
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro");
        }

        return "EditaCompra";
    }

    public void addItem() {
        if (compra.getCompraItem().getIcQuantidade() != null
                && compra.getCompraItem().getIcQuantidade().compareTo(BigDecimal.ZERO) > 0) {
            compra.addItem();
        } else {
            Messages.addGlobalError("A Quantidade do Item deve ser superior a zero!");
        }

        gerarParcelas();
    }

    public void removeItem(CompraItem item) {
        try {
            compra.removeItem(item);
            gerarParcelas();
        } catch (Exception e) {
        }
    }

    public void gerarParcelas() {
        if (compra.getStatusCompraVenda().equals(StatusCompraVenda.FINALIZAR)) {
            compra.setContasPagar(new ArrayList<ContasPagar>());
            Date dtparcela = dataVencimento;
            BigDecimal valorParcela = compra.getCompraTotalLiquido().divide(BigDecimal.valueOf(numParcela), RoundingMode.HALF_EVEN);
            for (int i = 1; i <= numParcela; i++) {
                ContasPagar cp = new ContasPagar();
                cp.setData_lancamento(new Date());
                cp.setNum_parcelas(i);
                cp.setCompra(compra);
                cp.setTipoPagamento(compra.getTipoPagamento());
                cp.setValor(valorParcela.doubleValue());
                cp.setData_vencimento(dtparcela);
                cp.setPessoaJuridica(compra.getPessoa());

                cp.setContasPagar_obs("Compra");
                Calendar cal = Calendar.getInstance();
                cal.setTime(dtparcela);
                cal.add(Calendar.MONTH, 1);
                dtparcela = cal.getTime();
                compra.getContasPagar().add(cp);
            }

            if (formaPagamento.equals("P")) {
                contasPagar.setContasPagarStatus(ContasPagarStatus.ABERTO);
                contasPagar.setData_vencimento(dtparcela);
            } else {
                contasPagar.setContasPagarStatus(ContasPagarStatus.PAGO);
                contasPagar.setData_vencimento(new Date());
                contasPagar.setData_pagamento(new Date());
            }
        }
    }

    public void atualizaParcela() {
        dataVencimento = new Date();
        setNumParcela(1L);
        if (formaPagamento.equals("P")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataVencimento);
            cal.add(Calendar.MONTH, 1);
            dataVencimento = cal.getTime();
        }
        gerarParcelas();
    }

    public void relacionaPreco() {
        BigDecimal preco = compra.getCompraItem().getProduto().getPreco();
        compra.getCompraItem().setIcPrecoBruto(preco);
    }

    public void pessoaSelecionado(SelectEvent event) {
        Pessoa pessoa = (Pessoa) event.getObject();
        compra.setPessoa((PessoaJuridica) pessoa);
    }

    public List<Pessoa> listaFornecedor() {
        return pessoaFacade.listaFornecedor();
    }

    public List<Produto> listaFiltrando(String parte) {
        return produtoFacade.listaFiltrando(parte, "nome");
    }

    public TipoPagamento[] getTiposPagamento() {
        return TipoPagamento.values();
    }

    public CompraTipo[] getCompraTipos() {
        return CompraTipo.values();
    }

    public StatusCompraVenda[] getStatusCompraVendas() {
        return StatusCompraVenda.values();
    }

    @Inject
    private CompraFacade compraFacade;

    public CompraControle() {
        super(Compra.class);
    }

    @Override
    public AbstractFacade<Compra> getFacade() {
        return compraFacade;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Long getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Long numParcela) {
        this.numParcela = numParcela;
    }

    public ContasPagar getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(ContasPagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public ConverterGenerico getPessoaConverter() {
        if (pessoaConverter == null) {
            pessoaConverter = new ConverterGenerico(pessoaFacade);
        }
        return pessoaConverter;
    }

    public ConverterGenerico getProdutoConverter() {
        if (produtoConverter == null) {
            produtoConverter = new ConverterGenerico(produtoFacade);
        }
        return produtoConverter;
    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public CompraItem getCompraItem() {
        return compraItem;
    }

    public void setCompraItem(CompraItem compraItem) {
        this.compraItem = compraItem;
    }

    @NotBlank
    public String getNomePessoa() {
        return compra.getPessoa() == null
                ? null : compra.getPessoa().getNome();
    }

    public void setNomePessoa(String nome) {
    }
}
