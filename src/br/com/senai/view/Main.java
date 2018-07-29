/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view;

import br.com.senai.dao.UsuarioDAO;
import br.com.senai.util.CarregaHibernate;
import br.com.senai.util.Config;
import br.com.senai.view.nova.PaginaInicial;
import java.awt.Color;

/**
 *
 * @author Simulado
 */
public class Main {

    public static void main(String[] args) {

        if (Config.DEBUG) {
            Config.usuario = new UsuarioDAO().validarLogin("admin", Config.criptografarSenha("admin@cetel"));

//            new br.com.senai.view.nova.Login().setVisible(true);
           Config.PAGINA_INICIAL = new PaginaInicial();

            Config.PAGINA_INICIAL.setVisible(true);
//           
           
          
//            Config.carregarTema();
//
//            if (Config.usuario.getTipoUsuario().getDescricao().equalsIgnoreCase("Funcionário")) {
//                new Menu().setVisible(true);
//            } else {
//                new MenuAdm().setVisible(true);
//            }
//
//            new GerarRelatorios().setVisible(true);
            
        } else {

            Loading loading2 = new Loading();
            loading2.getContentPane().setBackground(Color.WHITE);

            loading2.setVisible(true);

            new CarregaHibernate(loading2).execute();
        }
    }

}
