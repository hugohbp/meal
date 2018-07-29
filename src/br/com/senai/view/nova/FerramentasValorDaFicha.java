/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view.nova;

import br.com.senai.dao.EscolaDAO;
import br.com.senai.dao.FichaDAO;
import br.com.senai.entities.Escola;
import br.com.senai.entities.Ficha;
import br.com.senai.util.Config;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hugo
 */
public class FerramentasValorDaFicha extends javax.swing.JPanel {

    /**
     * Creates new form FerramentasValorDaFicha
     */
    Ficha f = new Ficha();

    public FerramentasValorDaFicha() {
        initComponents();
        Config.configurarPainel(this);

        Config.fillComboBox(cbEscolas, new EscolaDAO().escolasPermitidas(Config.usuario));
        atualizarValores();

    }

    private void atualizarValores() {

        DefaultTableModel tableModel = (DefaultTableModel) tValores.getModel();
        tableModel.setNumRows(0);

        if (!cbEscolas.getSelectedItem().toString().equalsIgnoreCase("Selecione...")) {

            try {

                for (Ficha ficha : new FichaDAO().pegarFichaEscola((Escola) cbEscolas.getSelectedItem())) {
                    tableModel.addRow(new Object[]{ficha, ficha.getValor(), new SimpleDateFormat("dd-MM-yyyy").format(ficha.getDataAlteracao()), ficha.getEscola().getNome()});
                }

            } catch (IllegalArgumentException ex) {

            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tValores = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        tfValorFicha = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        cbEscolas = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("Valor do lanche:");

        tValores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Valor", "Data alteração"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tValores);

        jButton1.setText("Cadastrar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Alterar");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cbEscolas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEscolasItemStateChanged(evt);
            }
        });

        jLabel3.setText("Selecione a  escola:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbEscolas, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfValorFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(164, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(234, 234, 234))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbEscolas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfValorFicha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!cbEscolas.getSelectedItem().toString().equalsIgnoreCase("Selecione...")) {

            if (!tfValorFicha.getText().isEmpty()) {
                double valor = 0;

                if (new FichaDAO().pegarFichaNaDataEEscola(new Date(), (Escola) cbEscolas.getSelectedItem()).isEmpty()) {

                    try {
                        valor = Double.parseDouble(tfValorFicha.getText());

                        f.setDataAlteracao(new Date());
                        f.setValor(valor);
                        f.setEscola((Escola) cbEscolas.getSelectedItem());

                        new FichaDAO().salvar(f);

                        atualizarValores();

                        new Config().mensagemSalvo();

                        tfValorFicha.setText(null);
                        tfValorFicha.requestFocus();
                        Config.fillComboBox(cbEscolas, new EscolaDAO().escolasPermitidas(Config.usuario));

                    } catch (NumberFormatException ex) {
                        try {
                            valor = Double.parseDouble(tfValorFicha.getText().replace(",", "."));

                            f.setDataAlteracao(new Date());
                            f.setValor(valor);
                            f.setEscola((Escola) cbEscolas.getSelectedItem());

                            new FichaDAO().salvar(f);

                            atualizarValores();

                            new Config().mensagemSalvo();

                            tfValorFicha.setText(null);
                            tfValorFicha.requestFocus();
                            Config.fillComboBox(cbEscolas, new EscolaDAO().escolasPermitidas(Config.usuario));
                        } catch (NumberFormatException ex1) {
                            JOptionPane.showMessageDialog(null, "Insira os valores corretamente.");
                            tfValorFicha.setText(null);
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Valor já inserido nessa data!");
                    tfValorFicha.setText(null);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma escola.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (tValores.getSelectedRowCount() > 0) {

            Ficha f = (Ficha) tValores.getValueAt(tValores.getSelectedRow(), 0);

            String valor = (String) JOptionPane.showInputDialog(null, "Valor na data: " + new SimpleDateFormat("dd-MM-yyyy").format(f.getDataAlteracao()), "Alterar dados ", JOptionPane.QUESTION_MESSAGE, null, null, f.getValor());
            double valorFicha = 0.0;
            try {
                if (valor != null) {

                    valorFicha = Double.parseDouble(valor);

                    f.setValor(valorFicha);

                    new FichaDAO().atualizar(f);

                    atualizarValores();

                    JOptionPane.showMessageDialog(null, "Salvo");

                }
            } catch (NumberFormatException ex) {

                try {
                    valorFicha = Double.parseDouble(valor.replace(",", "."));

                    f.setValor(valorFicha);

                    new FichaDAO().atualizar(f);

                    atualizarValores();

                    JOptionPane.showMessageDialog(null, "Salvo");

                } catch (NumberFormatException ex1) {
                    JOptionPane.showMessageDialog(null, "ERRO");
                }

            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbEscolasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEscolasItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!cbEscolas.getSelectedItem().toString().equalsIgnoreCase("Selecione...")) {

                try {
                    DefaultTableModel tableModel = (DefaultTableModel) tValores.getModel();

                    tableModel.setNumRows(0);

                    for (Ficha ficha : new FichaDAO().pegarFichaEscola((Escola) cbEscolas.getSelectedItem())) {

                        tableModel.addRow(new Object[]{ficha, ficha.getValor(), new SimpleDateFormat("dd-MM-yyyy").format(ficha.getDataAlteracao())});

                    }

                } catch (ClassCastException ex) {

                }
            }
        }
    }//GEN-LAST:event_cbEscolasItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbEscolas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tValores;
    private javax.swing.JTextField tfValorFicha;
    // End of variables declaration//GEN-END:variables
}
