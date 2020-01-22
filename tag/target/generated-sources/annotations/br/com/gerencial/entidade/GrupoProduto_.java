package br.com.gerencial.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GrupoProduto.class)
public abstract class GrupoProduto_ {

	public static volatile SingularAttribute<GrupoProduto, Boolean> ativo;
	public static volatile SingularAttribute<GrupoProduto, Date> data_cadastro;
	public static volatile SingularAttribute<GrupoProduto, String> nome;
	public static volatile SingularAttribute<GrupoProduto, Long> id;

}

