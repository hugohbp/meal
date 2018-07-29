/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view;

import br.com.senai.dao.CursoDAO;
import br.com.senai.dao.Curso_e_EscolaDAO;
import br.com.senai.dao.EscolaDAO;
import br.com.senai.dao.MovimentacaoDAO;
import br.com.senai.dao.TurmaDAO;
import br.com.senai.dao.TurnoDAO;
import br.com.senai.entities.Curso;
import br.com.senai.entities.Escola;
import br.com.senai.entities.Turma;
import br.com.senai.entities.Turno;
import br.com.senai.util.Config;
import static br.com.senai.view.PesquisaTurmas.fillTable;
import java.awt.Image;
import java.awt.event.ItemEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Hugo
 */
public class PesquisaTurmas extends javax.swing.JDialog {

    /**
     * Creates new form PesquisaTurmas1
     */
    public PesquisaTurmas() {
        initComponents();

        Config.configurarJanela(this);

        Config.fillComboBox(cbCurso, new CursoDAO().cursosPermitidos(Config.usuario));
        Config.fillComboBox(cbTurno, new TurnoDAO().listar());
        iconRelatorio.setIcon(new ImageIcon(new ImageIcon("imagens/search.png").getImage().getScaledInstance(43, 43, Image.SCALE_SMOOTH)));
        cbCurso.setEnabled(false);
        Config.fillComboBox(jComboBox1, new EscolaDAO().escolasPermitidas(Config.usuario));

        AutoCompleteDecorator.decorate(cbCurso);

    }

    public static Turma turma = new Turma();

    public static void fillTable(String query) {

        DefaultTableModel tableModel = (DefaultTableModel) tTurmas.getModel();

        tableModel.setNumRows(0);

        for (Turma turma : new TurmaDAO().pesquisa(query, Config.usuario)) {
            tableModel.addRow(new Object[]{turma, turma.getNome(), turma.getCurso().getPk().getCurso().getNome(), turma.getTurno().getTurno(), turma.isAtiva()});
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        cbTurno = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        cbCurso = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTurmas = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        tfNomeTurma = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cbAtiva = new javax.swing.JCheckBox();
        iconRelatorio = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setPreferredSize(new java.awt.Dimension(641, 483));

        jScrollPane2.setBorder(null);

        cbTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTurno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTurnoItemStateChanged(evt);
            }
        });

        jLabel27.setText("Turno:");

        cbCurso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));
        cbCurso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCursoItemStateChanged(evt);
            }
        });

        jLabel4.setText("Curso:");

        tTurmas.setAutoCreateRowSorter(true);
        tTurmas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Turma", "Curso", "Turno", "Ativa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tTurmas);
        if (tTurmas.getColumnModel().getColumnCount() > 0) {
            tTurmas.getColumnModel().getColumn(0).setMinWidth(0);
            tTurmas.getColumnModel().getColumn(0).setPreferredWidth(0);
            tTurmas.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jButton11.setText("Alterar");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Remover");
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.setRolloverEnabled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        tfNomeTurma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNomeTurmaKeyReleased(evt);
            }
        });

        jLabel2.setText("Nome da turma:");

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cbAtiva.setText("Ativa");
        cbAtiva.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAtivaItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Pesquisar turmas");
        jLabel5.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Resultados encontrados:");
        jLabel6.setToolTipText("");

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Escola:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(cbTurno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfNomeTurma)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbAtiva, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(iconRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cbCurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1))
                        .addGap(448, 448, 448)))
                .addGap(20, 20, 20))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton11, jButton12, jButton2});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iconRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNomeTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbAtiva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jButton11)
                    .addComponent(jButton2))
                .addGap(20, 20, 20))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbTurnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTurnoItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {

            try {

                fillTable("t.turno.id=" + ((Turno) cbTurno.getSelectedItem()).getId());
            } catch (ClassCastException ex) {

            }

        }
    }//GEN-LAST:event_cbTurnoItemStateChanged

    private void cbCursoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCursoItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {

            try {
                fillTable("ce.pk.curso.id=" + ((Curso) cbCurso.getSelectedItem()).getId() + " and ce.pk.escola.id=" + ((Escola) jComboBox1.getSelectedItem()).getId());

            } catch (ClassCastException ex) {

            }

        }

    }//GEN-LAST:event_cbCursoItemStateChanged

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        if (tTurmas.getSelectedRowCount() > 0) {

            turma = (Turma) tTurmas.getValueAt(tTurmas.getSelectedRow(), 0);
            new EditarTurma().setVisible(true);

        } else {
            Config.mensagemInformativa("Selecione uma turma.");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if (tTurmas.getSelectedRowCount() > 0) {

            Turma t = (Turma) tTurmas.getValueAt(tTurmas.getSelectedRow(), 0);

            if (new MovimentacaoDAO().listarMovimentacoesDaTurma(t).isEmpty()) {
                if (Config.mensagemDeConfirmacaoExclusao("Deseja excluir essa turma?\nNome:" + t.getNome() + "\nEscola:" + t.getCurso().getPk().getEscola().getNome() + "\n*Não será possível reverter o processo") == JOptionPane.YES_OPTION) {

                    new TurmaDAO().deletar(t);

                }
            } else {

                if (Config.mensagemDeConfirmacaoExclusao("Turma com registro de movimentação.\n<html><b>Deseja torná-la inativa?</b></html>\nNome: " + t.getNome() + "\nEscola: " + t.getCurso().getPk().getEscola().getNome()) == JOptionPane.YES_OPTION) {

                    t.setAtiva(false);
                    new TurmaDAO().atualizar(t);

                }
            }

            DefaultTableModel tableModel = (DefaultTableModel) tTurmas.getModel();

            tableModel.setNumRows(0);

        } else {
            Config.mensagemInformativa("Selecione uma turma.");
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void tfNomeTurmaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomeTurmaKeyReleased
        fillTable("t.nome like '%" + tfNomeTurma.getText() + "%'");

    }//GEN-LAST:event_tfNomeTurmaKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (!cbCurso.getSelectedItem().toString().equalsIgnoreCase("Selecione...") && !cbTurno.getSelectedItem().toString().equalsIgnoreCase("Selecione...")) {

            String nome = tfNomeTurma.getText();
            Curso curso = new Curso();
            Turno turno = new Turno();
            Escola escola = new Escola();

            try {
                curso = (Curso) cbCurso.getSelectedItem();
                turno = (Turno) cbTurno.getSelectedItem();
                escola = (Escola) jComboBox1.getSelectedItem();

            } catch (ClassCastException ex) {
                curso = null;
                turno = null;
                escola = null;

            }

            boolean ativa = cbAtiva.isSelected();

            fillTable("t.nome like '%" + nome + "%' and ce.pk.curso.id =" + curso.getId() + " and ce.pk.escola.id=" + escola.getId() + " and t.turno.id=" + turno.getId() + "and t.ativa=" + ativa);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um curso ou turno.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbAtivaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbAtivaItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {

            try {

                fillTable("t.ativa=" + true);
            } catch (ClassCastException ex) {
                System.out.println(ex.getMessage());
            }

        }

        if (evt.getStateChange() == ItemEvent.DESELECTED) {

            try {

                fillTable("t.ativa=" + false);
            } catch (ClassCastException ex) {
                System.out.println(ex.getMessage());
            }

        }


    }//GEN-LAST:event_cbAtivaItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!jComboBox1.getSelectedItem().toString().equalsIgnoreCase("Selecione...")) {

                Config.fillComboBox(cbCurso, new Curso_e_EscolaDAO().cursosDaEscola((Escola) jComboBox1.getSelectedItem()));
                cbCurso.setEnabled(true);

            } else {
                Config.limparComboBox(cbCurso);
                cbCurso.setEnabled(false);

            }
        } else {
            cbCurso.setEnabled(false);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbAtiva;
    private javax.swing.JComboBox cbCurso;
    private javax.swing.JComboBox cbTurno;
    private javax.swing.JLabel iconRelatorio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private static javax.swing.JTable tTurmas;
    private javax.swing.JTextField tfNomeTurma;
    // End of variables declaration//GEN-END:variables
}