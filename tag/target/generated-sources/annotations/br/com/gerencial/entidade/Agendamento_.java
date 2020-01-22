package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.StatusAgendamento;
import br.com.gerencial.enumerated.TipoConsulta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Agendamento.class)
public abstract class Agendamento_ {

	public static volatile SingularAttribute<Agendamento, PessoaFisica> pessoaFisica;
	public static volatile SingularAttribute<Agendamento, Date> dtInicial;
	public static volatile SingularAttribute<Agendamento, Date> horaInicial;
	public static volatile SingularAttribute<Agendamento, TipoConsulta> tipoConsulta;
	public static volatile SingularAttribute<Agendamento, Cadeira> cadeira;
	public static volatile SingularAttribute<Agendamento, Sala> sala;
	public static volatile SingularAttribute<Agendamento, Date> horaFinal;
	public static volatile SingularAttribute<Agendamento, String> obs_agendamento;
	public static volatile SingularAttribute<Agendamento, Long> id;
	public static volatile SingularAttribute<Agendamento, Date> dtFinal;
	public static volatile SingularAttribute<Agendamento, StatusAgendamento> status;

}

