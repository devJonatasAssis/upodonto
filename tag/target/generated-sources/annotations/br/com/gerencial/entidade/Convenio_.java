package br.com.gerencial.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Convenio.class)
public abstract class Convenio_ {

	public static volatile SingularAttribute<Convenio, Date> data_cadastro;
	public static volatile SingularAttribute<Convenio, String> nome;
	public static volatile SingularAttribute<Convenio, Long> id;
	public static volatile SingularAttribute<Convenio, Boolean> status;

}

