/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.facade;

import br.com.gerencial.util.Transacional;
import java.sql.Connection;
import java.sql.SQLException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

/**
 *
 * @author jonatas
 */
@Transacional
public class RelatorioFacade {
    
    @Inject
    private EntityManager em;
    
    public Connection getConnection() throws SQLException{
        Session session = em.unwrap(Session.class);
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();
        Connection connection = cp.getConnection();
        return connection;
    }
}
