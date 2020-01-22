/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.entidade;

import br.com.gerencial.enumerated.TipoEmpresa;
import br.com.gerencial.interfaces.EntidadePai;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.hibernate.envers.Audited;

/**
 *
 * @author jonatas
 */
@Entity
public class Empresa extends PessoaJuridica implements Serializable, EntidadePai {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    private TipoEmpresa tipoEmpresa;

    public TipoEmpresa getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

}
