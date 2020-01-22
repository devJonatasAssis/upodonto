package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.Unidade_Medida;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, String> codigo;
	public static volatile SingularAttribute<Produto, Boolean> ativo;
	public static volatile SingularAttribute<Produto, GrupoProduto> grupo;
	public static volatile SingularAttribute<Produto, String> nome;
	public static volatile SingularAttribute<Produto, String> codigo_Interno;
	public static volatile SingularAttribute<Produto, Unidade_Medida> unidade;
	public static volatile SingularAttribute<Produto, BigDecimal> preco;
	public static volatile SingularAttribute<Produto, BigDecimal> estoque;
	public static volatile SingularAttribute<Produto, String> marca;
	public static volatile SingularAttribute<Produto, Date> data_cadastro;
	public static volatile SingularAttribute<Produto, String> ncm;
	public static volatile SingularAttribute<Produto, Date> data_validade;
	public static volatile SingularAttribute<Produto, Long> id;

}

