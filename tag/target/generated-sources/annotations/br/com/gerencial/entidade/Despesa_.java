package br.com.gerencial.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Despesa.class)
public abstract class Despesa_ {

	public static volatile SingularAttribute<Despesa, Date> data_cadastro;
	public static volatile SingularAttribute<Despesa, String> nome;
	public static volatile SingularAttribute<Despesa, Long> id;

}

