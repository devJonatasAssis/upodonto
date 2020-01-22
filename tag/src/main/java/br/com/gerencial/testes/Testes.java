/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerencial.testes;

import br.com.gerencial.controle.EnvioEmailControle;
import org.omnifaces.util.Messages;

/**
 *
 * @author unespar-ti
 */
public class Testes {
    
    public static void main(String[] args) {
        try {
            EnvioEmailControle envioEmail = new EnvioEmailControle();
            envioEmail.enviarEmail("Agendamento","Seu Agendamento está marcado para o Dia 12/07/2018 08:40","jonatassilvatrab@gmail.com");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("erro");
        }
    }
    
}
