/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view.nova;

import br.com.senai.util.Config;
import br.com.senai.util.EncriptacaoAES;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Hugo
 */
public class OpcoesLogin extends javax.swing.JFrame {

    /**
     * Creates new form OpcoesLogin
     */
    private JTextField tfLogin;
    private JPasswordField pfSenha;

    public OpcoesLogin(JTextField jTextField, JPasswordField jPasswordField) {
        tfLogin = jTextField;
        pfSenha = jPasswordField;
        initComponents();
        carregarParametros();
        Config.carregarTema();
    }

    public void carregarParametros() {

        Config.carregarParametros();

        if (Config.manterLogin.equalsIgnoreCase("false")) {
            cbLogin.setSelected(false);
            cbSenha.setSelected(false);
        } else {
            cbLogin.setSelected(true);
            tfLogin.setText(Config.manterLogin);
            if (!Config.manterSenha.equalsIgnoreCase("false")) {
                cbSenha.setSelected(true);
                pfSenha.setText(Config.manterSenha);
            }
        }

    }

    public void salvarParametros() {
        EncriptacaoAES encriptacaoAES = new EncriptacaoAES();
        Config.manterLogin = encriptacaoAES.encriptar(cbLogin.isSelected() ? tfLogin.getText() : "false");
        Config.manterSenha = encriptacaoAES.encriptar(cbSenha.isSelected() ? new String(pfSenha.getPassword()) : "false");
        Config.salvarParametros();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbLogin = new javax.swing.JCheckBox();
        cbSenha = new javax.swing.JCheckBox();

        setUndecorated(true);
        setType(java.awt.Window.Type.UTILITY);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        cbLogin.setText("salvar login");
        cbLogin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbLoginStateChanged(evt);
            }
        });

        cbSenha.setText("salvar senha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbLogin)
                    .addComponent(cbSenha))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSenha)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void cbLoginStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbLoginStateChanged

        if (cbLogin.isSelected()) {
            cbSenha.setEnabled(true);
        } else {
            cbSenha.setEnabled(false);
            cbSenha.setSelected(false);
        }
    }//GEN-LAST:event_cbLoginStateChanged

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited


    }//GEN-LAST:event_formMouseExited

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        salvarParametros();
        System.out.println("foco perdido");
        dispose();

    }//GEN-LAST:event_formWindowLostFocus

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        salvarParametros();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbLogin;
    private javax.swing.JCheckBox cbSenha;
    // End of variables declaration//GEN-END:variables
}
