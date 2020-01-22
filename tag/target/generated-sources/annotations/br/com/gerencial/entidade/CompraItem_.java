package br.com.gerencial.entidade;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CompraItem.class)
public abstract class CompraItem_ {

	public static volatile SingularAttribute<CompraItem, BigDecimal> icPrecoBruto;
	public static volatile SingularAttribute<CompraItem, Compra> compra;
	public static volatile SingularAttribute<CompraItem, Produto> produto;
	public static volatile SingularAttribute<CompraItem, BigDecimal> icDesconto;
	public static volatile SingularAttribute<CompraItem, BigDecimal> icQuantidade;
	public static volatile SingularAttribute<CompraItem, BigDecimal> icPrecoLiquido;
	public static volatile SingularAttribute<CompraItem, Integer> id;

}

