package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.Especialista;
import br.com.gerencial.enumerated.SiglaCRO;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Doutor.class)
public abstract class Doutor_ extends br.com.gerencial.entidade.Pessoa_ {

	public static volatile SingularAttribute<Doutor, String> cro;
	public static volatile SingularAttribute<Doutor, Especialista> especialista;
	public static volatile SingularAttribute<Doutor, String> cpf_doutor;
	public static volatile SingularAttribute<Doutor, SiglaCRO> siglaCRO;

}

