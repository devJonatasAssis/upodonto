package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.TipoAjusteEstoque;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AjustaEstoque.class)
public abstract class AjustaEstoque_ {

	public static volatile SingularAttribute<AjustaEstoque, BigDecimal> ajusteQuantidade;
	public static volatile SingularAttribute<AjustaEstoque, Produto> produto;
	public static volatile SingularAttribute<AjustaEstoque, Date> dataAjuste;
	public static volatile SingularAttribute<AjustaEstoque, Long> id;
	public static volatile SingularAttribute<AjustaEstoque, TipoAjusteEstoque> tipoAjusteEstoque;
	public static volatile SingularAttribute<AjustaEstoque, String> ajusteMotivo;

}

