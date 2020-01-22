package br.com.gerencial.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Banco.class)
public abstract class Banco_ {

	public static volatile SingularAttribute<Banco, Cidade> cidade;
	public static volatile SingularAttribute<Banco, Estado> estado;
	public static volatile SingularAttribute<Banco, Date> data_cadastro;
	public static volatile SingularAttribute<Banco, String> nome;
	public static volatile SingularAttribute<Banco, String> num_conta;
	public static volatile SingularAttribute<Banco, Long> id;
	public static volatile SingularAttribute<Banco, String> agencia;

}

