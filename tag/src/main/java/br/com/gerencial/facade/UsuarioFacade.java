/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.facade;

import br.com.gerencial.entidade.PessoaFisica;
import br.com.gerencial.entidade.Usuario;
import br.com.gerencial.util.Criptografia;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements Serializable{
    
    @Inject
    private EntityManager em;

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
   public Usuario pesquisaUsuario(String login, String senha) {
        Query query = em.createQuery("FROM Usuario AS u WHERE u.login='" + login + "' AND u.senha='" + Criptografia.md5(senha) + "'");
   
        if (query.getResultList().size() == 1) {
            return (Usuario) query.getResultList().get(0);
        }
        return null;
    }
   
    @Override
    public EntityManager getEm() {
        return em;
    }
    
    public List<PessoaFisica> listaFuncionarioTrue(){
        Query q = em.createQuery("FROM PessoaFisica WHERE funcionario='true'");
        return q.getResultList();
    }

    
}
