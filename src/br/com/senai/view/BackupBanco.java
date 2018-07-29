/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view;

import br.com.senai.util.Config;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Hugo
 */
public class BackupBanco extends javax.swing.JFrame {

    /**
     * Creates new form BackupBanco
     */
    public BackupBanco() {
        initComponents();
        Config.configurarJanela(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jFileChooser1.setFileFilter(new FileNameExtensionFilter("Arquivos SQL (*.sql)", "sql"));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Restaurar e criar backups -");

        jButton3.setText("Criar backup");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Restaurar backup");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        File file = new File("backups");
        try {

            file.mkdirs();

            Runtime.getRuntime().exec("cmd /c mysqldump -u" + Config.usuarioBD + " -p" + Config.senhaBD + " -h" + Config.urlBD + " --database " + Config.NOME_BANCO_DE_DADOS + " >  backups/" + Config.formatarData(new Date()) + ".sql");
            Config.mensagemInformativa("BACKUP REALIZADO COM SUCESSO!!!");

        } catch (IOException ex) {
            Config.mensagemDeErro(ex.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();

            String caminho[] = file.getParent().split("\\\\");

            String pasta = caminho[caminho.length - 1];

            if (pasta.equalsIgnoreCase("backups")) {

                try {

                    Runtime.getRuntime().exec("cmd /c mysqldump -u" + Config.usuarioBD + " -p" + Config.senhaBD + " -h" + Config.urlBD + " --database " + Config.NOME_BANCO_DE_DADOS + " >  backups/" + Config.formatarData(new Date()) + ".." + Config.usuario.getLogin() + ".sql");

                    Runtime.getRuntime().exec("cmd /c  mysql -u" + Config.usuarioBD + " -p" + Config.senhaBD + " -h" + Config.urlBD + "  <  " + pasta + "/" + file.getName());
                    Config.mensagemInformativa("RESTAURAÇÃO REALIZADA COM SUCESSO!!");

                } catch (IOException ex) {
                    Config.mensagemDeErro(ex.getMessage());
                }
            } else {
                Config.mensagemDeErro("Diretório inválido.");
            }

        }

    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration//GEN-END:variables
}
