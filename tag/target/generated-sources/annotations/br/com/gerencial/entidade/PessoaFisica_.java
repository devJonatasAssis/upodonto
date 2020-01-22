package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.Civil;
import br.com.gerencial.enumerated.Parentesco;
import br.com.gerencial.enumerated.TipoConta;
import br.com.gerencial.enumerated.TipoPagamento;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PessoaFisica.class)
public abstract class PessoaFisica_ extends br.com.gerencial.entidade.Pessoa_ {

	public static volatile SingularAttribute<PessoaFisica, String> carga_horaria;
	public static volatile SingularAttribute<PessoaFisica, Date> data_demissao;
	public static volatile SingularAttribute<PessoaFisica, BigDecimal> salario;
	public static volatile SingularAttribute<PessoaFisica, Convenio> convenio;
	public static volatile SingularAttribute<PessoaFisica, String> telResp_Paciente;
	public static volatile SingularAttribute<PessoaFisica, Banco> banco;
	public static volatile SingularAttribute<PessoaFisica, String> cns;
	public static volatile SingularAttribute<PessoaFisica, String> num_conta;
	public static volatile SingularAttribute<PessoaFisica, String> celResp_Paciente;
	public static volatile SingularAttribute<PessoaFisica, Date> data_nasc;
	public static volatile SingularAttribute<PessoaFisica, String> carteirinha;
	public static volatile SingularAttribute<PessoaFisica, String> rg;
	public static volatile SingularAttribute<PessoaFisica, Date> data_admissao;
	public static volatile SingularAttribute<PessoaFisica, Parentesco> parentesco;
	public static volatile SingularAttribute<PessoaFisica, Civil> civil;
	public static volatile SingularAttribute<PessoaFisica, String> cpf;
	public static volatile SingularAttribute<PessoaFisica, Date> data_validade;
	public static volatile SingularAttribute<PessoaFisica, String> nomeResp_Paciente;
	public static volatile SingularAttribute<PessoaFisica, String> sexo;
	public static volatile SingularAttribute<PessoaFisica, Cargo> cargo;
	public static volatile SingularAttribute<PessoaFisica, TipoPagamento> tipoPagamento;
	public static volatile SingularAttribute<PessoaFisica, TipoConta> tipoConta;
	public static volatile SingularAttribute<PessoaFisica, Boolean> recebeEmail;
	public static volatile SingularAttribute<PessoaFisica, String> cpfResp_Paciente;

}

