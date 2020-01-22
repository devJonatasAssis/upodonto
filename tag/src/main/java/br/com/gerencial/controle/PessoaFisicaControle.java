/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.entidade.Banco;
import br.com.gerencial.entidade.Cargo;
import br.com.gerencial.entidade.Cidade;
import br.com.gerencial.entidade.Convenio;
import br.com.gerencial.entidade.Estado;
import br.com.gerencial.entidade.Pessoa;
import br.com.gerencial.entidade.PessoaFisica;
import br.com.gerencial.enumerated.Civil;
import br.com.gerencial.enumerated.Especialista;
import br.com.gerencial.enumerated.Parentesco;
import br.com.gerencial.enumerated.SiglaCRO;
import br.com.gerencial.enumerated.TipoConta;
import br.com.gerencial.enumerated.TipoPagamento;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.BancoFacade;
import br.com.gerencial.facade.CargoFacade;
import br.com.gerencial.facade.CidadeFacade;
import br.com.gerencial.facade.ConvenioFacade;
import br.com.gerencial.facade.EstadoFacade;
import br.com.gerencial.facade.PessoaFisicaFacade;
import java.io.Serializable;
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
public class PessoaFisicaControle extends AbstractControle<PessoaFisica> implements Serializable {

    private PessoaFisica pessoaFisica = new PessoaFisica();
    
    private Pessoa pessoa;
    
    private String arquivoSelecionado;

    @Inject
    private PessoaFisicaFacade pessoaFisicaFacade;

    @Inject
    private CidadeFacade cidadeFacade;

    @Inject
    private EstadoFacade estadoFacade;

    @Inject
    private CargoFacade cargoFacade;

    @Inject
    private BancoFacade bancoFacade;
    
    @Inject
    private ConvenioFacade convenioFacade;
    
    private Boolean pac_Responsavel = true;

    private ConverterGenerico estadoConverter;
    private ConverterGenerico cidadeConverter;
    private ConverterGenerico cargoConverter;
    private ConverterGenerico bancoConverter;
    private ConverterGenerico convenioConverter;

    public PessoaFisicaControle() {
        super(PessoaFisica.class);
    }

    @Override
    public AbstractFacade<PessoaFisica> getFacade() {
        return pessoaFisicaFacade;
    }

    @Override
    public String salvar() {
        try {
            pessoaFisicaFacade.salvar(pessoaFisica);
            Messages.addGlobalInfo("Salvo com Sucesso !");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalInfo("Erro ao Salvar");
        }

        arquivoSelecionado = null;
        return null;
    }
    
    public void excluir(PessoaFisica p) throws Exception{
        pessoaFisicaFacade.excluir(p);
    }
    
    public void editar(PessoaFisica e){
        this.pessoaFisica = e;
    }

    @Override
    public void novo() {
        pessoaFisica = new PessoaFisica();
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public PessoaFisicaFacade getPessoaFisicaFacade() {
        return pessoaFisicaFacade;
    }

    public void setPessoaFisicaFacade(PessoaFisicaFacade pessoaFisicaFacade) {
        this.pessoaFisicaFacade = pessoaFisicaFacade;
    }

    public ConverterGenerico getCidadeConverter() {
        if (cidadeConverter == null) {
            cidadeConverter = new ConverterGenerico(cidadeFacade);
        }
        return cidadeConverter;
    }

    public ConverterGenerico getEstadoConverter() {
        if (estadoConverter == null) {
            estadoConverter = new ConverterGenerico(estadoFacade);
        }
        return estadoConverter;
    }

    public ConverterGenerico getCargoConverter() {
        if (cargoConverter == null) {
            cargoConverter = new ConverterGenerico(cargoFacade);
        }
        return cargoConverter;
    }

    public ConverterGenerico getBancoConverter() {
        if (bancoConverter == null) {
            bancoConverter = new ConverterGenerico(bancoFacade);
        }
        return bancoConverter;
    }

    public List<Estado> listaEstados() {
        return estadoFacade.listaTodos();
    }

    public List<Cidade> listaCidades() {
        return cidadeFacade.listaTodos();
    }
    
    public List<Cidade> listaFiltrando(String parte){
        return cidadeFacade.listaFiltrando(parte, "nome");
    }

    public List<Cargo> listaCargos() {
        return cargoFacade.listaTodos();
    }
    
    public List<Convenio> listaConvenio(){
        return convenioFacade.listaTodos();
    }

    public List<Banco> listaBanco() {
        return bancoFacade.listaTodos();
    }

    public Civil[] getCivils() {
        return Civil.values();
    }

    public TipoConta[] getContas() {
        return TipoConta.values();
    }

    public TipoPagamento[] getTipoPagamentos() {
        return TipoPagamento.values();
    }

    public SiglaCRO[] getSiglaCROs() {
        return SiglaCRO.values();
    }

    public Especialista[] getEspecialistas() {
        return Especialista.values();
    }
    
    public Parentesco[] getParentescos(){
        return Parentesco.values();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getArquivoSelecionado() {
        return arquivoSelecionado;
    }

    public void setArquivoSelecionado(String arquivoSelecionado) {
        this.arquivoSelecionado = arquivoSelecionado;
    }

    public ConverterGenerico getConvenioConverter() {
        if (convenioConverter == null) {
            convenioConverter = new ConverterGenerico(convenioFacade);
        }
        return convenioConverter;
    }

    public void setConvenioConverter(ConverterGenerico convenioConverter) {
        this.convenioConverter = convenioConverter;
    }

    public Boolean getPac_Responsavel() {
        return pac_Responsavel;
    }

    public void setPac_Responsavel(Boolean pac_Responsavel) {
        this.pac_Responsavel = pac_Responsavel;
    }
}
