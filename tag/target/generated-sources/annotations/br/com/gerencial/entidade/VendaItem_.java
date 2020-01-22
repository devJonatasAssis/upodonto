package br.com.gerencial.entidade;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VendaItem.class)
public abstract class VendaItem_ {

	public static volatile SingularAttribute<VendaItem, BigDecimal> ivPrecoLiquido;
	public static volatile SingularAttribute<VendaItem, Venda> venda;
	public static volatile SingularAttribute<VendaItem, BigDecimal> ivQuantidade;
	public static volatile SingularAttribute<VendaItem, Produto> produto;
	public static volatile SingularAttribute<VendaItem, Long> id;
	public static volatile SingularAttribute<VendaItem, BigDecimal> ivPrecoBruto;
	public static volatile SingularAttribute<VendaItem, BigDecimal> ivDesconto;

}

