/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view;

import br.com.senai.util.Config;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Hugo
 */
public class PainelInicial extends javax.swing.JPanel {

    /**
     * Creates new form PainelInicial
     */
    public PainelInicial() {
        initComponents();
        repaint();
        revalidate();
        inicializar();
    }

    public void inicializar() {
        jLabel41.setText(Config.usuario.getNome());

        logo.setIcon(new ImageIcon(new ImageIcon("imagens/logoMEAL.PNG").getImage().getScaledInstance((int) logo.getPreferredSize().getWidth(), (int) logo.getPreferredSize().getHeight(), Image.SCALE_SMOOTH)));

        logoSenai.setIcon(new ImageIcon(new ImageIcon("imagens/senaiIMG.jpg").getImage().getScaledInstance((int) logoSenai.getPreferredSize().getWidth(), (int) logoSenai.getPreferredSize().getHeight(), Image.SCALE_SMOOTH)));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logo = new javax.swing.JLabel();
        logoSenai = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();

        setInheritsPopupMenu(true);

        logo.setPreferredSize(new java.awt.Dimension(400, 143));

        logoSenai.setPreferredSize(new java.awt.Dimension(186, 47));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(logoSenai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel41)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(logoSenai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logoSenai;
    // End of variables declaration//GEN-END:variables
}
