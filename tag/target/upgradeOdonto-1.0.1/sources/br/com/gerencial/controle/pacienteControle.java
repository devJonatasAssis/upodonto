/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.entidade.Procedimento;
import br.com.gerencial.entidade.PessoaFisica;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.ProcedimentoFacade;
import br.com.gerencial.facade.PessoaFisicaFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jonatas
 */
@Named
@ViewScoped
public class pacienteControle extends AbstractControle<PessoaFisica> implements Serializable{
    
    private Procedimento paciente = new Procedimento();
    
    @Inject
    private PessoaFisicaFacade pessoaFisicaFacade;
    
    @Inject
    private ProcedimentoFacade comecoTratamentoFacade;
    
    private String filtro = "";

    public pacienteControle() {
        super(PessoaFisica.class);
    }

    @Override
    public AbstractFacade<PessoaFisica> getFacade() {
        return pessoaFisicaFacade;
    }
    
    public List<Procedimento> listaPacientes(){
        return comecoTratamentoFacade.listaTodos();
    }
    
    public List<PessoaFisica> listaTodosPacientes(){
        return pessoaFisicaFacade.listaPacienteTrue();
    }
    
    public void pesquisarPaciente() {
        comecoTratamentoFacade.listaFiltrando(filtro, "pessoaFisica.nome");
    }

    public Procedimento getPaciente() {
        return paciente;
    }

    public void setPaciente(Procedimento paciente) {
        this.paciente = paciente;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
}
