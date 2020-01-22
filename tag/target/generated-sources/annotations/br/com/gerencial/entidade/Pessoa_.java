package br.com.gerencial.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, Cidade> cidade;
	public static volatile SingularAttribute<Pessoa, Estado> estado;
	public static volatile SingularAttribute<Pessoa, String> telefone;
	public static volatile SingularAttribute<Pessoa, String> endereco;
	public static volatile SingularAttribute<Pessoa, String> numero;
	public static volatile SingularAttribute<Pessoa, String> bairro;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, String> cep;
	public static volatile SingularAttribute<Pessoa, Boolean> Funcionario;
	public static volatile SingularAttribute<Pessoa, String> complemento;
	public static volatile SingularAttribute<Pessoa, Boolean> paciente;
	public static volatile SingularAttribute<Pessoa, String> celular;
	public static volatile SingularAttribute<Pessoa, Long> id;
	public static volatile SingularAttribute<Pessoa, String> tipoLogradouro;
	public static volatile SingularAttribute<Pessoa, Date> data_Cadastro;
	public static volatile SingularAttribute<Pessoa, Boolean> fornecedor;
	public static volatile SingularAttribute<Pessoa, String> email;
	public static volatile SingularAttribute<Pessoa, Boolean> status;

}

