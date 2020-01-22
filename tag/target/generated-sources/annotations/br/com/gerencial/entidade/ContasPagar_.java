package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.ContasPagarStatus;
import br.com.gerencial.enumerated.TipoPagamento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContasPagar.class)
public abstract class ContasPagar_ {

	public static volatile SingularAttribute<ContasPagar, Compra> compra;
	public static volatile SingularAttribute<ContasPagar, String> contasPagar_obs;
	public static volatile SingularAttribute<ContasPagar, Pessoa> pessoaJuridica;
	public static volatile SingularAttribute<ContasPagar, Integer> num_parcelas;
	public static volatile SingularAttribute<ContasPagar, Date> data_pagamento;
	public static volatile SingularAttribute<ContasPagar, Double> valor;
	public static volatile SingularAttribute<ContasPagar, Date> data_lancamento;
	public static volatile SingularAttribute<ContasPagar, Long> id;
	public static volatile SingularAttribute<ContasPagar, ContasPagarStatus> contasPagarStatus;
	public static volatile SingularAttribute<ContasPagar, TipoPagamento> tipoPagamento;
	public static volatile SingularAttribute<ContasPagar, Date> data_vencimento;

}

