package br.com.gerencial.entidade;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tratamento.class)
public abstract class Tratamento_ {

	public static volatile SingularAttribute<Tratamento, Date> data_cadastro;
	public static volatile SingularAttribute<Tratamento, BigDecimal> valor;
	public static volatile SingularAttribute<Tratamento, String> nome;
	public static volatile SingularAttribute<Tratamento, Long> id;

}

