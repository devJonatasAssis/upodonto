package br.com.gerencial.controle;

import br.com.gerencial.converter.ConverterGenerico;
import br.com.gerencial.entidade.Estado;
import br.com.gerencial.facade.AbstractFacade;
import br.com.gerencial.facade.CidadeFacade;
import br.com.gerencial.util.ControleUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 *
 * @author Jonatas
 * @param <T>
 */
public abstract class AbstractControle<T> implements Serializable {

    private Class<T> classe;
    private List<T> lista;
    private T entidade;
    private Boolean layoutList = true;
    private Boolean layoutForm = false;
    private ConverterGenerico converter;

    @Inject
    private CidadeFacade cidadeFacade;

    public AbstractControle(Class<T> classe) {
        this.classe = classe;
    }

    public abstract AbstractFacade<T> getFacade();

    public ConverterGenerico converter() {
        if (converter == null) {
            converter = new ConverterGenerico(getFacade());
        }
        return converter;
    }

    public void novo() {
        try {
            entidade = classe.newInstance();
            layoutList = false;
            layoutForm = true;
        } catch (InstantiationException | IllegalAccessException ex) {
            ControleUtil.systemMessage(FacesMessage.SEVERITY_FATAL, "Erro ao instanciar!", ex.getMessage());
        }
    }

    public void alterarForm() {
        layoutList = false;
        layoutForm = true;
    }

    public void cancelarForm() {
        layoutList = true;
        layoutForm = false;
    }

    public String salvar() {
        try {
            getFacade().create(entidade);
            ControleUtil.systemMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", "");
            return "List?faces-redirect=true";
        } catch (Exception ex) {
            ex.printStackTrace();
            ControleUtil.systemMessage(FacesMessage.SEVERITY_FATAL, "Erro ao salvar " + classe.getSimpleName(), ex.getMessage());
        }
        return null;
    }

    public String remove() {
        try {
            getFacade().excluir(entidade);
            ControleUtil.systemMessage(FacesMessage.SEVERITY_INFO, "Excluido com sucesso!", "");
            return "List?faces-redirect=true";
        } catch (Exception ex) {
            ControleUtil.systemMessage(FacesMessage.SEVERITY_FATAL, "Erro ao excluir " + classe.getSimpleName(), ex.getMessage());
        }
        return null;
    }

    public List<T> getListar() {
        if (lista == null) {
            lista = getFacade().listaTodos();
        }
        return lista;
    }

    public Boolean getLayoutList() {
        return layoutList;
    }

    public void setLayoutList(Boolean layoutList) {
        this.layoutList = layoutList;
    }

    public Boolean getLayoutForm() {
        return layoutForm;
    }

    public void setLayoutForm(Boolean layoutForm) {
        this.layoutForm = layoutForm;
    }

    public T getEntidade() {
        return entidade;
    }

    public void setEntidade(T entidade) {
        this.entidade = entidade;
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

}
