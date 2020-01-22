package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.CompraTipo;
import br.com.gerencial.enumerated.StatusCompraVenda;
import br.com.gerencial.enumerated.TipoPagamento;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Compra.class)
public abstract class Compra_ {

	public static volatile SingularAttribute<Compra, BigDecimal> compraAcrescimo;
	public static volatile SingularAttribute<Compra, Pessoa> pessoa;
	public static volatile SingularAttribute<Compra, BigDecimal> compraTotalBruto;
	public static volatile SingularAttribute<Compra, Date> compraData;
	public static volatile ListAttribute<Compra, ContasPagar> contasPagar;
	public static volatile SingularAttribute<Compra, CompraTipo> compraTipo;
	public static volatile SingularAttribute<Compra, StatusCompraVenda> statusCompraVenda;
	public static volatile SingularAttribute<Compra, String> compraObs;
	public static volatile ListAttribute<Compra, CompraItem> compraItems;
	public static volatile SingularAttribute<Compra, String> num_nfe;
	public static volatile SingularAttribute<Compra, Boolean> compraStatus;
	public static volatile SingularAttribute<Compra, Integer> id;
	public static volatile SingularAttribute<Compra, BigDecimal> compraDesconto;
	public static volatile SingularAttribute<Compra, TipoPagamento> tipoPagamento;
	public static volatile SingularAttribute<Compra, BigDecimal> compraTotalLiquido;

}

