/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.entidade.Cargo;
import br.com.gerencial.entidade.Setor;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.CargoFacade;
import br.com.gerencial.facade.SetorFacade;
import java.util.ArrayList;
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
public class CargoControle extends AbstractControle<Cargo> {

    private Cargo cargo = new Cargo();

    @Inject
    private CargoFacade cargoFacade;

    @Inject
    private SetorFacade setorFacade;
    
    private ConverterGenerico setorConverter;

    public ConverterGenerico getSetorConverter() {
        if (setorConverter == null) {
            setorConverter = new ConverterGenerico(setorFacade);
        }
        return setorConverter;
    }

    public CargoControle() {
        super(Cargo.class);
    }

    @Override
    public AbstractFacade<Cargo> getFacade() {
        return cargoFacade;
    }

    @Override
    public String salvar() {
        try {
            cargoFacade.salvar(cargo);
            Messages.addGlobalInfo("Cargo Cadastrado com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Erro ao Salvar");
        }
        return null;
    }
    
    public List<Setor> listaSetor(){
        return setorFacade.listaTodos();
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

}


