/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author jonatas
 */
public class ReportUtils {
    
    public void gerarRelatorioPDF(Map parametros, String caminho, Connection conn) throws IOException, JRException, ClassNotFoundException, SQLException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        InputStream reportStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(caminho);
        JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametros, conn);
        servletOutputStream.flush();
        servletOutputStream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
    
}
