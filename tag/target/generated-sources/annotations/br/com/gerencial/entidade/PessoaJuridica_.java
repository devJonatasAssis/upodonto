package br.com.gerencial.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PessoaJuridica.class)
public abstract class PessoaJuridica_ extends br.com.gerencial.entidade.Pessoa_ {

	public static volatile SingularAttribute<PessoaJuridica, String> nome_representante;
	public static volatile SingularAttribute<PessoaJuridica, String> tel_representante;
	public static volatile SingularAttribute<PessoaJuridica, String> cnpj;
	public static volatile SingularAttribute<PessoaJuridica, String> ie;
	public static volatile SingularAttribute<PessoaJuridica, String> razaoSocial;
	public static volatile SingularAttribute<PessoaJuridica, String> cel_representante;

}

