package br.com.gerencial.entidade;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Servico.class)
public abstract class Servico_ {

	public static volatile SingularAttribute<Servico, Date> data_cadastro;
	public static volatile SingularAttribute<Servico, BigDecimal> valor;
	public static volatile SingularAttribute<Servico, String> nome;
	public static volatile SingularAttribute<Servico, Long> id;
	public static volatile SingularAttribute<Servico, Boolean> status;

}

