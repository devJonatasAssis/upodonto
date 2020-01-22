package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.StatusCompraVenda;
import br.com.gerencial.enumerated.TipoPagamento;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Venda.class)
public abstract class Venda_ {

	public static volatile SingularAttribute<Venda, BigDecimal> vendaTotalBruto;
	public static volatile ListAttribute<Venda, ContasReceber> contasRecebers;
	public static volatile SingularAttribute<Venda, Date> dataVenda;
	public static volatile SingularAttribute<Venda, Pessoa> pessoa;
	public static volatile SingularAttribute<Venda, StatusCompraVenda> statusCompraVenda;
	public static volatile SingularAttribute<Venda, String> vendaObs;
	public static volatile SingularAttribute<Venda, BigDecimal> vendaAcrescimo;
	public static volatile ListAttribute<Venda, VendaItem> vendaItens;
	public static volatile SingularAttribute<Venda, Long> id;
	public static volatile SingularAttribute<Venda, TipoPagamento> tipoPagamento;
	public static volatile SingularAttribute<Venda, BigDecimal> vendaTotalLiquido;
	public static volatile SingularAttribute<Venda, BigDecimal> vendaDesconto;

}

