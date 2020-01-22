package br.com.gerencial.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Config_Email.class)
public abstract class Config_Email_ {

	public static volatile SingularAttribute<Config_Email, String> nome_visualizao;
	public static volatile SingularAttribute<Config_Email, String> senha;
	public static volatile SingularAttribute<Config_Email, String> port_servidor_smtp;
	public static volatile SingularAttribute<Config_Email, String> servidorPop;
	public static volatile SingularAttribute<Config_Email, String> servidor_smtp;
	public static volatile SingularAttribute<Config_Email, Long> id;
	public static volatile SingularAttribute<Config_Email, String> tipoAutent;
	public static volatile SingularAttribute<Config_Email, String> email;
	public static volatile SingularAttribute<Config_Email, String> portaPop;

}

