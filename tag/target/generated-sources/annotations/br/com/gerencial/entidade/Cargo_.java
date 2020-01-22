package br.com.gerencial.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cargo.class)
public abstract class Cargo_ {

	public static volatile SingularAttribute<Cargo, Setor> setor;
	public static volatile SingularAttribute<Cargo, Date> data_cadastro;
	public static volatile SingularAttribute<Cargo, String> nome;
	public static volatile SingularAttribute<Cargo, Long> id;

}

