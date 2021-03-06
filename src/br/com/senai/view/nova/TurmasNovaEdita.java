/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view.nova;

import br.com.senai.view.*;
import br.com.senai.util.Config;
import br.com.senai.dao.Curso_e_EscolaDAO;
import br.com.senai.dao.EscolaDAO;
import br.com.senai.dao.TurmaDAO;
import br.com.senai.dao.TurnoDAO;
import br.com.senai.entities.Curso;
import br.com.senai.entities.Escola;
import br.com.senai.entities.Turma;
import br.com.senai.entities.Turno;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Hugo
 */
public class TurmasNovaEdita extends javax.swing.JPanel {

    /**
     * Creates new form PainelTurma
     */
    public TurmasNovaEdita() {
        initComponents();

        Config.fillComboBox(cbTurno, new TurnoDAO().listar());
        Config.fillComboBox(cbEscola, new EscolaDAO().escolasPermitidas(Config.usuario));

        cbCurso.setEnabled(false);

        AutoCompleteDecorator.decorate(cbCurso);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfNomeTurma = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbCurso = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        cbTurno = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        cbEscola = new javax.swing.JComboBox();

        jScrollPane2.setBorder(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nome da turma:");

        jButton6.setText("Salvar");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Curso:");

        cbCurso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));
        cbCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCursoActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Turno:");

        cbTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Escola:");

        cbEscola.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEscolaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel27)
                    .addComponent(jLabel19)
                    .addComponent(jLabel3)
                    .addComponent(cbEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNomeTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNomeTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (!tfNomeTurma.getText().isEmpty() && !cbCurso.getSelectedItem().toString().equalsIgnoreCase("Selecione...") && !cbTurno.getSelectedItem().toString().equalsIgnoreCase("Selecione...")) {

            Turma t = new Turma();

            t.setNome(tfNomeTurma.getText());

            t.setAtiva(true);

            t.setCurso(new Curso_e_EscolaDAO().getCurso_e_Escola((Curso) cbCurso.getSelectedItem(), (Escola) cbEscola.getSelectedItem()));

            t.setTurno((Turno) cbTurno.getSelectedItem());

            new TurmaDAO().salvar(t);

            Config.mensagemSalvo();

            tfNomeTurma.setText(null);

        } else {
            JOptionPane.showMessageDialog(null, "Preencha os campos corretamente.", "Resposta", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void cbCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCursoActionPerformed

    private void cbEscolaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEscolaItemStateChanged

        if (cbEscola.getSelectedItem() instanceof Escola) {
            Config.fillComboBox(cbCurso, new Curso_e_EscolaDAO().cursosDaEscola((Escola) cbEscola.getSelectedItem()));
            cbCurso.setEnabled(true);
        } else {
            Config.limparComboBox(cbCurso);
            cbCurso.setEnabled(false);
        }


    }//GEN-LAST:event_cbEscolaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbCurso;
    private javax.swing.JComboBox cbEscola;
    private javax.swing.JComboBox cbTurno;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tfNomeTurma;
    // End of variables declaration//GEN-END:variables

}
