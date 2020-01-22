/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.facade;

import br.com.gerencial.entidade.Pessoa;
import br.com.gerencial.util.Transacional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jonatas
 */
@Transacional
public class PessoaFacade extends AbstractFacade<Pessoa> implements Serializable {

    @Inject
    private EntityManager em;

    public PessoaFacade() {
        super(Pessoa.class);
    }

    @Override
    public EntityManager getEm() {
        return em;
    }

    public List<Pessoa> listaFornecedor() {
        Query q = em.createQuery("FROM Pessoa WHERE fornecedor='true'");
        return q.getResultList();
    }

    public List<Pessoa> pessoaAutoComplete(String nome) {
        Query q = em.createQuery("FROM Pessoa AS p WHERE LOWER(p.nome) LIKE('%" + nome.toLowerCase() + "%')");
        return q.getResultList();
    }

    public List<Pessoa> porNomeSemelhante(String nome) {
        return em.createQuery("from Pessoa where nome like :nome", Pessoa.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}
