package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.ContasPagarStatus;
import br.com.gerencial.enumerated.TipoPagamento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContasReceber.class)
public abstract class ContasReceber_ {

	public static volatile SingularAttribute<ContasReceber, String> obs;
	public static volatile SingularAttribute<ContasReceber, Date> crDataVencimento;
	public static volatile SingularAttribute<ContasReceber, Pessoa> pessoa;
	public static volatile SingularAttribute<ContasReceber, Procedimento> comecoTratamento;
	public static volatile SingularAttribute<ContasReceber, Double> crValor;
	public static volatile SingularAttribute<ContasReceber, Date> crDataRecebimento;
	public static volatile SingularAttribute<ContasReceber, Venda> venda;
	public static volatile SingularAttribute<ContasReceber, Integer> id;
	public static volatile SingularAttribute<ContasReceber, Date> crDataLancamento;
	public static volatile SingularAttribute<ContasReceber, ContasPagarStatus> contasPagarStatus;
	public static volatile SingularAttribute<ContasReceber, TipoPagamento> tipoPagamento;
	public static volatile SingularAttribute<ContasReceber, Procedimento> procedimento;
	public static volatile SingularAttribute<ContasReceber, Integer> crNumParcela;

}

