/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.entidade.PessoaJuridica;
import br.com.gerencial.facade.PessoaJuridicaFacade;
import br.com.gerencial.facade.RelatorioFacade;
import static br.com.gerencial.util.Conexao.getConnection;
import br.com.gerencial.util.ReportUtils;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author jonatas
 */
@Named
@RequestScoped
public class RelatorioControle implements Serializable {

    @Inject
    private RelatorioFacade relatorioFacade;

    @Inject
    private LoginControle loginControle;
    
    @Inject
    private PessoaJuridicaFacade pessoaJuridicaFacade;

    private PessoaJuridica pessoaJuridica;
    private ConverterGenerico converterFornecedor;
    
    private String ftTratamento = "TODOS";
    private Boolean ftFornecedor = true;
    private String tipoRelatorio = "BANCO";
    
    private Date dataFim = new Date();
    private Date dataInicio = new Date();
    
    public List<PessoaJuridica> listaFornecedores(String parte){
        return pessoaJuridicaFacade.listaFiltrando(parte, "nome");
    }

    public void relatorioCadastralBanco() {
        try {
            ReportUtils util = new ReportUtils();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/Relatorio_Banco.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void relatorioCadastralConvenio() {
        try {
            ReportUtils util = new ReportUtils();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/Relatorio_Convenio.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void relatorioCadastralDespesas() {
        try {
            ReportUtils util = new ReportUtils();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/Relatorio_Despesas.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioProduto() throws SQLException {
        try {
            DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("frm:tabela");
            Map<String, Object> filtros = tabela.getFilters();
            String proDescricao = (String) filtros.get("nome");

            String caminho = Faces.getRealPath("/WEB-INF/reports/Relatorio_Produto.jasper");

            Map<String, Object> parametros = new HashMap<>();
            if (proDescricao == null) {
                parametros.put("produto_descricao", "%%");
            } else {
                parametros.put("produto_descricao", "%" + proDescricao + "%");
            }

            Connection connection = relatorioFacade.getConnection();
            JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, connection);
            JasperPrintManager.printReport(relatorio, true);
        } catch (JRException e) {
            Messages.addGlobalError("Erro");
        }

    }

    public void relatorioCadastralServico() {
        try {
            ReportUtils util = new ReportUtils();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/Relatorio_Servico.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void relatorioCadastralTratamento() {
        try {
            ReportUtils util = new ReportUtils();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/Relatorio_Tratamento.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void relatorioCadastralUsuario() {
        try {
            ReportUtils util = new ReportUtils();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/Relatorio_Usuario.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void relatorioCadastralGrupoProduto() {
        try {
            ReportUtils util = new ReportUtils();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/Relatorio_GrupoProduto.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelContaPagarPeriodo() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
            String caminhoReal = sc.getRealPath("/");

            Map<String, Object> parametros = new HashMap<>();

            parametros.put("SUBREPORT_DIR", caminhoReal + "WEB-INF/reports/");
            parametros.put("p_dtinicio", dataInicio);
            parametros.put("p_dtfim", dataFim);
            parametros.put("USUARIO", loginControle.getUsuario().getLogin());

            String condicao = "";

            //if (!ftFornecedor && ftFornecedor != null) {
              //  condicao += " AND p.pes_id=" + pessoaJuridica.getId();  
                //System.out.println("Id:" + condicao);
            //}
            
            parametros.put(condicao, "p_condicao");
            
            String relatorio = "WEB-INF/reports/rel_contapagar_periodo.jasper";
            Connection conn = relatorioFacade.getConnection();
            ReportUtils reportUtils = new ReportUtils();
            reportUtils.gerarRelatorioPDF(parametros, relatorio, conn);

        } catch (SQLException | IOException | JRException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public void gerarRelContaReceberPeriodo() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
            String caminhoReal = sc.getRealPath("/");

            Map<String, Object> parametros = new HashMap<>();

            parametros.put("SUBREPORT_DIR", caminhoReal + "WEB-INF/reports/");
            parametros.put("p_dtinicio", dataInicio);
            parametros.put("p_dtfim", dataFim);
            parametros.put("USUARIO", loginControle.getUsuario().getLogin());

            String condicao = "";

            //if (!ftFornecedor && ftFornecedor != null) {
              //  condicao += " AND p.pes_id=" + pessoaJuridica.getId();  
                //System.out.println("Id:" + condicao);
            //}
            
            parametros.put(condicao, "p_condicao");
            
            String relatorio = "WEB-INF/reports/rel_contareceber_periodo.jasper";
            Connection conn = relatorioFacade.getConnection();
            ReportUtils reportUtils = new ReportUtils();
            reportUtils.gerarRelatorioPDF(parametros, relatorio, conn);

        } catch (SQLException | IOException | JRException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getFtTratamento() {
        return ftTratamento;
    }

    public void setFtTratamento(String ftTratamento) {
        this.ftTratamento = ftTratamento;
    }

    public Boolean getFtFornecedor() {
        return ftFornecedor;
    }

    public void setFtFornecedor(Boolean ftFornecedor) {
        this.ftFornecedor = ftFornecedor;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public ConverterGenerico getConverterFornecedor() {
        if (converterFornecedor == null) {
            converterFornecedor = new ConverterGenerico(pessoaJuridicaFacade);
        }
        return converterFornecedor;
    }

    public void setConverterFornecedor(ConverterGenerico converterFornecedor) {
        this.converterFornecedor = converterFornecedor;
    }
}
