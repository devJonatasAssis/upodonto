/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.facade;

import br.com.gerencial.entidade.PessoaFisica;
import br.com.gerencial.interfaces.MovimentaEstoque;
import br.com.gerencial.interfaces.Validador;
import br.com.gerencial.util.Transacional;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jonatas
 * @param <T>
 */
public abstract class AbstractFacade<T> implements Serializable {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> classe) {
        this.entityClass = classe;
    }

    public abstract EntityManager getEm();

    @Transacional
    public T create(T entity) throws Exception {
        try {
            entity = getEm().merge(entity);
            getEm().flush();
            return entity;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public T salvar(T objeto) throws Exception {
        if (objeto instanceof MovimentaEstoque) {
            MovimentaEstoque m = (MovimentaEstoque) objeto;
            m.movimenta();
        }
        if (objeto instanceof Validador) {
            Validador v = (Validador) objeto;
            v.validar();
        }

        T retorno = (T) getEm().merge(objeto);
        return retorno;
    }

    @Transacional
    public void excluir(T entity) throws Exception {
        try {
            getEm().remove(getEm().merge(entity));
            getEm().flush();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public T pesquisarId(Long id) {
        return getEm().find(entityClass, id);
    }

    public List<T> listaTodos() {
        return getEm().createQuery("FROM " + entityClass.getSimpleName()).getResultList();
    }

    public List<T> listaFiltrando(String filtro, String... atributos) {
        String hql = "from " + entityClass.getSimpleName() + " obj where ";
        for (String atributo : atributos) {
            hql += "lower(obj." + atributo + ") like :filtro OR ";
        }
        hql = hql.substring(0, hql.length() - 3);
        Query q = getEm().createQuery(hql);
        q.setParameter("filtro", "%" + filtro.toLowerCase() + "%");
        return q.getResultList();
    }

    public List<T> listaFiltrandoPessoa(PessoaFisica pessoa) {
        String hql = "from " + entityClass.getSimpleName() + " obj"
                + " where obj.pessoaFisica= :paciente";
        Query q = getEm().createQuery(hql);
        q.setParameter("paciente", pessoa);
        return q.getResultList();
    }

}
