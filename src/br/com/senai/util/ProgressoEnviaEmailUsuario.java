/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.util;

import br.com.senai.email.Email;
import br.com.senai.entities.Usuario;
import java.awt.Window;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.swing.SwingWorker;

/**
 *
 * @author Simulado
 */
public class ProgressoEnviaEmailUsuario extends SwingWorker<Void, Integer> {
    
    private Usuario u;
    private String senha;
    private Window jPanel;
    
    public ProgressoEnviaEmailUsuario(Window jPanel, Usuario usuario, String senha) {
        this.u = usuario;
        this.senha = senha;
        this.jPanel = jPanel;
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        
        try {
            
            new Email().enviarEmail(u.getEmail(), "Confirmação de cadastro", u.getNome() + ", <br>"
                    + "Você foi cadastrado no" + Config.NOME_SISTEMA + ", um sistema que visa otimizar o processo de entrega de lanches que ocorre nas escolas do SENAI.<br>"
                    + "Para entrar no sistema seguem abaixo o seu usuário e sua senha: <br>"
                    + "<u>usuário</u>: <b>" + u.getLogin() + "</b><br>"
                    + "<u>senha</u>: <b>" + senha + "</b><br>"
                    + " Para alterar suas informações  basta ir em <u>Minhas Informações -> Alterar</u> ou simplesmente pressione o atalho <u>ALT + A</u><br>"
                    + "*Este email é somente de envio, favor  não responder<br>"
                    + "<b>Caso não conheça o" + Config.NOME_SISTEMA + ", favor desconsiderar esse email.</b>");
            
            jPanel.dispose();
            
            Config.mensagemInformativa("Usuário cadastrado com sucesso.");
            
        } catch (MessagingException | UnsupportedEncodingException ex) {
            Config.mensagemDeErro("Usuário salvo, porém email não pode ser enviado.\n" + ex.getMessage());
        }
        
        return null;
    }
    
}
