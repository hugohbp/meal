/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.util;

import br.com.senai.email.Email;
import java.awt.Window;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.MessagingException;
import javax.swing.SwingWorker;

/**
 *
 * @author Simulado
 */
public class ProgressoEnviaEmail extends SwingWorker<Void, Integer> {

    private Window janela;
    private String sugestao;

    public ProgressoEnviaEmail(Window janela, String sugestao) {
        this.janela = janela;
        this.sugestao = sugestao;
    }

    @Override
    protected Void doInBackground() throws Exception {

        try {
            new Email().enviarEmail("service.meal@gmail.com", "SUGESTÃO PARA O MEAL - " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                    "Descrição: <br><br><br><br>" + sugestao + "<br><br><br><br> Autor: " + Config.usuario.getNome()
            );
            Config.mensagemInformativa("Sugestão enviada com sucesso.");

        } catch (MessagingException | UnsupportedEncodingException ex) {
            Config.mensagemDeErro("Erro ao enviar sugestão.");
        }
        if (janela != null) {
            janela.dispose();
        }
        return null;
    }

}
