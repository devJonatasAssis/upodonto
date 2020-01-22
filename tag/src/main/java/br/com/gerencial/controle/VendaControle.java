/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.converter.MoneyConverter;
import br.com.gerencial.entidade.ContasReceber;
import br.com.gerencial.entidade.Pessoa;
import br.com.gerencial.entidade.PessoaJuridica;
import br.com.gerencial.entidade.Produto;
import br.com.gerencial.entidade.Venda;
import br.com.gerencial.entidade.VendaItem;
import br.com.gerencial.enumerated.ContasPagarStatus;
import br.com.gerencial.enumerated.StatusCompraVenda;
import br.com.gerencial.enumerated.TipoPagamento;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.PessoaFacade;
import br.com.gerencial.facade.ProdutoFacade;
import br.com.gerencial.facade.VendaFacade;
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
public class VendaControle extends AbstractControle<Venda> implements Serializable {

    private Venda venda = new Venda();

    private ContasReceber contasReceber = new ContasReceber();

    @Inject
    private VendaFacade vendaFacade;

    @Inject
    private ProdutoFacade produtoFacade;
    
    @Inject
    private PessoaFacade pessoaFacade;

    private String formaPagamento = "V";
    private Long numParcela = 1L;
    private Date dtVencimento = new Date();

    private ConverterGenerico produtoConverter;
    private ConverterGenerico pessoaConverter;

    private MoneyConverter moneyConverter;

    public List<Produto> listaFiltrandoProduto(String parte) {
        return produtoFacade.listaFiltrando(parte, "nome");
    }
    
    public List<Pessoa> listaPessoa(){
        return pessoaFacade.listaTodos();
    }
    
    public List<Venda> listaVenda(){
        return vendaFacade.listaTodos();
    }

    @Override
    public String salvar() {
        try {
            vendaFacade.salvar(venda);
            for (VendaItem vendaItem : venda.getVendaItens()) {
                vendaItem.getProduto().baixarEstoque(vendaItem.getIvQuantidade());
                produtoFacade.salvar(vendaItem.getProduto());
            }
            Messages.addGlobalInfo("Venda Realizada com Sucesso !");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao realizar a compra !");
        }
        return null;
    }

    public Boolean estoqueDisponivel() {
        VendaItem item = venda.getVendaItem();
        BigDecimal estoque = item.getProduto().getEstoque();

        for (VendaItem vi : venda.getVendaItens()) {
            if (vi.getProduto().equals(item.getProduto())) {
                estoque = estoque.subtract(item.getIvQuantidade());
            }
        }

        if (estoque.compareTo(item.getIvQuantidade()) < 0) {
            Messages.addGlobalError("Estoque Insuficiente ! \n Restam apenas" + estoque + "Unidade(s)");
        }

        return true;
    }

    public void gerarParcelas() {
        venda.setContasRecebers(new ArrayList<ContasReceber>());
        Date dtparcela = dtVencimento;
        BigDecimal valorParcela = venda.getVendaTotalLiquido().divide(BigDecimal.valueOf(numParcela), RoundingMode.HALF_EVEN);
        for (int i = 1; i <= numParcela; i++) {
            ContasReceber cp = new ContasReceber();
            cp.setCrDataLancamento(new Date());
            cp.setCrNumParcela(i);
            cp.setVenda(venda);
            cp.setTipoPagamento(venda.getTipoPagamento());
            cp.setCrValor(valorParcela.doubleValue());
            cp.setCrDataVencimento(dtparcela);
            cp.setPessoa(venda.getPessoa());

            cp.setObs("Venda");
            Calendar cal = Calendar.getInstance();
            cal.setTime(dtparcela);
            cal.add(Calendar.MONTH, 1);
            dtparcela = cal.getTime();
            venda.getContasRecebers().add(cp);
        }

        if (formaPagamento.equals("P")) {
            contasReceber.setContasPagarStatus(ContasPagarStatus.ABERTO);
            contasReceber.setCrDataVencimento(dtparcela);
        } else {
            contasReceber.setContasPagarStatus(ContasPagarStatus.PAGO);
            contasReceber.setCrDataVencimento(new Date());
            contasReceber.setCrDataRecebimento(new Date());
        }
    }

    public void atualizaParcela() {
        dtVencimento = new Date();
        setNumParcela(1L);
        if (formaPagamento.equals("P")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dtVencimento);
            cal.add(Calendar.MONTH, 1);
            dtVencimento = cal.getTime();
        }
    }

    public void addItemVenda() {
        if (venda.getVendaItem().getIvQuantidade() != null && venda.getVendaItem().getIvQuantidade().compareTo(BigDecimal.ZERO) > 0) {
            if (estoqueDisponivel()) {
                venda.addItemVenda();
            } else {
                Messages.addGlobalInfo("A quantidade de Item deve ser superior a 0");
            }

            gerarParcelas();
        }
    }

    public void removeItem(VendaItem item) {
        venda.removeItem(item);
        gerarParcelas();
    }
    
    public void pessoaSelecionado(SelectEvent event) {
        Pessoa pessoa = (Pessoa) event.getObject();
        venda.setPessoa((Pessoa) pessoa);
    }

    public TipoPagamento[] getTiposPagamento() {
        return TipoPagamento.values();
    }
    
    public StatusCompraVenda[] getStatusCompraVenda(){
        return StatusCompraVenda.values();
    }

    public void relacionaPreco() {
        BigDecimal preco = venda.getVendaItem().getProduto().getPreco();
        venda.getVendaItem().setIvPrecoBruto(preco);
    }

    public VendaControle() {
        super(Venda.class);
    }

    @Override
    public AbstractFacade<Venda> getFacade() {
        return vendaFacade;
    }

    public ConverterGenerico getProdutoConverter() {
        if (produtoConverter == null) {
            produtoConverter = new ConverterGenerico(produtoFacade);
        }
        return produtoConverter;
    }

    public ConverterGenerico getPessoaConverter() {
        if (pessoaConverter == null) {
            pessoaConverter = new ConverterGenerico(pessoaFacade);
        }
        return pessoaConverter;
    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Long getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Long numParcela) {
        this.numParcela = numParcela;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }
    
    @NotBlank
    public String getNomePessoa() {
        return venda.getPessoa()== null
                ? null : venda.getPessoa().getNome();
    }

    public void setNomePessoa(String nome) {
    }
}
