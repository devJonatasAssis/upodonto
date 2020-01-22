/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.util;

import com.sun.faces.component.visit.FullVisitContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;

/**
 *
 * @author jonatas
 */
public class ControleUtil {
    
    public static void systemMessage(FacesMessage.Severity tipo, String msg, String detalhe) {
        FacesMessage message = new FacesMessage(tipo, msg, detalhe);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static boolean requiredComponent(String... id) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        boolean validacao = true;
        for (String s : id) {
            UIComponent u = findComponent(s);
            Method m1 = u.getClass().getMethod("getValue", null);
            Method m2 = u.getClass().getMethod("setValid", boolean.class);
            Method m3 = u.getClass().getMethod("getRequiredMessage", null);
            if (m1.invoke(u, null) == null || m1.invoke(u, null).toString().trim().length() == 0) {
                m2.invoke(u, false);
                validacao = false;
                systemMessage(FacesMessage.SEVERITY_ERROR, m3.invoke(u, null).toString(), "");
            } else {
                m2.invoke(u, true);
            }
        }
        return validacao;
    }

    public static UIComponent findComponent(final String id) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot root = context.getViewRoot();
        final UIComponent[] found = new UIComponent[1];
        try {
            root.visitTree(new FullVisitContext(context), new VisitCallback() {
                @Override
                public VisitResult visit(VisitContext context, UIComponent component) {
                    if (component != null && component.getId() != null && component.getId().equals(id)) {
                        found[0] = component;
                        return VisitResult.COMPLETE;
                    }
                    return VisitResult.ACCEPT;
                }
            });
            return found[0];
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Componente nao encontrado " + id + ". ERRO: " + ex.getMessage());
            return null;
        }
    }
    
}
