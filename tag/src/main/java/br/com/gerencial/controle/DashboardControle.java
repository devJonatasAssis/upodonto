/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.MoneyConverter;
import br.com.gerencial.entidade.Agendamento;
import br.com.gerencial.facade.AgendamentoFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.omnifaces.util.Messages;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.Axis;

/**
 *
 * @author unespar
 */
@Named
@ViewScoped
public class DashboardControle implements Serializable {

    private BarChartModel grafico1;

    @Inject
    private AgendamentoFacade agendamentoFacade;

    @Inject
    private EntityManager em;
    
    private MoneyConverter moneyConverter;

    public List<Agendamento> listaAgenda() {
        return agendamentoFacade.listaTodos();
    }

    public Object quantPaciente() {
            Query q = em.createQuery("SELECT COUNT(Pessoa) FROM Pessoa WHERE paciente='true'");
            return q.getSingleResult();
    }
    
    public Object mostraContasReceber(){
        Query q = em.createQuery("SELECT SUM(crValor) FROM ContasReceber WHERE contasPagarStatus='ABERTO'");
        return q.getSingleResult();
    }
    
    public Object mostraContasPagar(){
        Query q = em.createQuery("SELECT SUM(valor) FROM ContasPagar WHERE contasPagarStatus='ABERTO'");
        return q.getSingleResult();
    }

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    private void createAnimatedModels() {

        grafico1 = initBarModel();
        grafico1.setTitle("Comparação de Pacientes");
        grafico1.setAnimate(true);
        grafico1.setLegendPosition("ne");
        Axis yAxis;
        yAxis = grafico1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(400);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries ativo = new ChartSeries();
        ativo.setLabel("Pacientes Ativos");
        ativo.set("2004", 120);
        ativo.set("2005", 100);
        ativo.set("2006", 44);
        ativo.set("2007", 150);
        ativo.set("2008", 25);

        ChartSeries inativos = new ChartSeries();
        inativos.setLabel("Pacientes Inativos");
        inativos.set("2004", 52);
        inativos.set("2005", 60);
        inativos.set("2006", 110);
        inativos.set("2007", 135);
        inativos.set("2008", 120);

        model.addSeries(ativo);
        model.addSeries(inativos);

        return model;
    }

    public void setGrafico1(BarChartModel grafico1) {
        this.grafico1 = grafico1;
    }

    public BarChartModel getGrafico1() {
        return grafico1;
    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

}
