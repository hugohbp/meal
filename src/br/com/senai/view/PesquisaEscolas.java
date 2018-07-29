/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view;

import br.com.senai.dao.CidadeDAO;
import br.com.senai.dao.EscolaDAO;
import br.com.senai.dao.MovimentacaoDAO;
import br.com.senai.entities.Cidade;
import br.com.senai.entities.Escola;
import br.com.senai.util.Config;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Hugo
 */
public class PesquisaEscolas extends javax.swing.JDialog {

    public PesquisaEscolas() {
        initComponents();
        Config.configurarJanela(this);
        init();

    }

    public void init() {

        if (!Config.usuario.getTipoUsuario().getDescricao().equalsIgnoreCase("Administrador")) {
            Config.fillComboBox(cbEscolaCidade, new CidadeDAO().cidadesPermitidas(Config.usuario));
        } else {
            Config.fillComboBox(cbEscolaCidade, new CidadeDAO().cidadesQuePossuemEscola());
        }

        Config.fillComboBox(cbCidadeEscolaAlterar, new CidadeDAO().listar());

        AutoCompleteDecorator.decorate(cbEscolaCidade);
        AutoCompleteDecorator.decorate(cbCidadeEscolaAlterar);

        iconRelatorio.setIcon(new ImageIcon(new ImageIcon("imagens/search.png").getImage().getScaledInstance(43, 43, Image.SCALE_SMOOTH)));

    }

    Escola escolaAlterar;

    public void fillTable(List<Escola> escolas) {

        DefaultTableModel tableModel = (DefaultTableModel) tEscolas.getModel();

        tableModel.setNumRows(0);

        for (Escola escola : escolas) {
            tableModel.addRow(new Object[]{escola, escola.getNome(), escola.getCidade().getNome()});
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AlterarEscola = new javax.swing.JDialog();
        jLabel31 = new javax.swing.JLabel();
        tfNomeEscolaAlterar = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        cbCidadeEscolaAlterar = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        icon = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        cbEscolaCidade = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tEscolas = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        tfNomeTurma = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        iconRelatorio = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        AlterarEscola.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        jLabel31.setText("Nome da escola:");

        jLabel32.setText("Cidade:");

        jButton3.setText("Salvar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel35.setText("Editar escola");
        jLabel35.setToolTipText("Alterar senha do usuário");

        javax.swing.GroupLayout AlterarEscolaLayout = new javax.swing.GroupLayout(AlterarEscola.getContentPane());
        AlterarEscola.getContentPane().setLayout(AlterarEscolaLayout);
        AlterarEscolaLayout.setHorizontalGroup(
            AlterarEscolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AlterarEscolaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(AlterarEscolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(tfNomeEscolaAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AlterarEscolaLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AlterarEscolaLayout.createSequentialGroup()
                        .addGroup(AlterarEscolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AlterarEscolaLayout.createSequentialGroup()
                                .addGroup(AlterarEscolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbCidadeEscolaAlterar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)))
                .addGap(20, 20, 20))
        );
        AlterarEscolaLayout.setVerticalGroup(
            AlterarEscolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AlterarEscolaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(AlterarEscolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35)
                    .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNomeEscolaAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCidadeEscolaAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AlterarEscolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4))
                .addGap(20, 20, 20))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar escolas -");
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setPreferredSize(new java.awt.Dimension(641, 483));

        jScrollPane2.setBorder(null);

        cbEscolaCidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEscolaCidadeItemStateChanged(evt);
            }
        });

        jLabel4.setText("Cidade:");

        tEscolas.setAutoCreateRowSorter(true);
        tEscolas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome da escola", "Cidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tEscolas);
        if (tEscolas.getColumnModel().getColumnCount() > 0) {
            tEscolas.getColumnModel().getColumn(0).setMinWidth(0);
            tEscolas.getColumnModel().getColumn(0).setPreferredWidth(0);
            tEscolas.getColumnModel().getColumn(0).setMaxWidth(0);
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

        jLabel2.setText("Nome da escola:");

        jButton1.setText("Pesquisar com todos os filtros");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Pesquisar escolas");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(iconRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfNomeTurma)
                    .addComponent(cbEscolaCidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEscolaCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton2)
                    .addComponent(jButton12)
                    .addComponent(jButton11))
                .addGap(20, 20, 20))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton11, jButton12, jButton2});

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

    private void cbEscolaCidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEscolaCidadeItemStateChanged

        if (cbEscolaCidade.getSelectedItem() instanceof Cidade) {

            if (Config.usuario.getTipoUsuario().getDescricao().equalsIgnoreCase("Administrador")) {
                fillTable(new EscolaDAO().escolaDaCidade((Cidade) cbEscolaCidade.getSelectedItem()));

            } else {
                fillTable(new EscolaDAO().escolasPermitidasDaCidade(Config.usuario, (Cidade) cbEscolaCidade.getSelectedItem()));
            }

        } else {
            DefaultTableModel dtmTabela = (DefaultTableModel) tEscolas.getModel();
            dtmTabela.setNumRows(0);
        }

    }//GEN-LAST:event_cbEscolaCidadeItemStateChanged

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (tEscolas.getSelectedRowCount() > 0) {
            escolaAlterar = (Escola) tEscolas.getValueAt(tEscolas.getSelectedRow(), 0);

            tfNomeEscolaAlterar.setText(escolaAlterar.getNome());
            cbCidadeEscolaAlterar.setSelectedItem(escolaAlterar.getCidade());

            AlterarEscola.setTitle("Alterar escola - ");
            Config.configurarJanela(AlterarEscola);
            AlterarEscola.setSize(413, 200);
            AlterarEscola.setLocationRelativeTo(null);
            icon.setIcon(new ImageIcon(new ImageIcon("imagens/icones/15.png").getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH)));
            AlterarEscola.pack();
            AlterarEscola.setVisible(true);
        } else {
            Config.mensagemInformativa("Selecione uma escola.");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        if (tEscolas.getSelectedRowCount() > 0) {
            Escola e = (Escola) tEscolas.getValueAt(tEscolas.getSelectedRow(), 0);

            if (new MovimentacaoDAO().listarMovimentacoesDaEscola(e).isEmpty()) {

                if (Config.mensagemDeConfirmacaoExclusao("Deseja excluir essa escola?\n<html><b>Nome: " + e.getNome() + "</b>\n*TODAS AS INFORMAÇÕES RELACIONADAS A ESSA ESCOLA SERÃO *APAGADAS*") == JOptionPane.YES_OPTION) {
                    new EscolaDAO().deletar(e);
                    Config.mensagemInformativa("Escola removida.");

                    getContentPane().removeAll();

                    initComponents();

                    init();

                    repaint();
                    revalidate();

                    PainelEscola.fillListaEscola();
                }
            } else {
                Config.mensagemInformativa("Há movimentações para essa escola.");
            }

        } else {
            Config.mensagemInformativa("Selecione uma escola.");
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void tfNomeTurmaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomeTurmaKeyReleased

        if (Config.usuario.getTipoUsuario().getDescricao().equalsIgnoreCase("Administrador")) {

            fillTable(new EscolaDAO().pesquisa("e.nome like '%" + tfNomeTurma.getText() + "%'"));

        } else {
            fillTable(new EscolaDAO().pesquisa("e.nome like '%" + tfNomeTurma.getText() + "%'", Config.usuario));
        }

    }//GEN-LAST:event_tfNomeTurmaKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (!cbEscolaCidade.getSelectedItem().toString().equalsIgnoreCase("Selecione...") && !tfNomeTurma.getText().isEmpty()) {
            if (Config.usuario.getTipoUsuario().getDescricao().equalsIgnoreCase("Administrador")) {
                fillTable(new EscolaDAO().pesquisa("e.nome like '%" + tfNomeTurma.getText() + "%' and e.cidade.id=" + ((Cidade) cbEscolaCidade.getSelectedItem()).getId() + ""));
            } else {
                fillTable(new EscolaDAO().pesquisa("e.nome like '%" + tfNomeTurma.getText() + "%' and e.cidade.id=" + ((Cidade) cbEscolaCidade.getSelectedItem()).getId() + "", Config.usuario));
            }

        } else {
            Config.mensagemInformativa("Insira as informações corretamente.");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (!tfNomeEscolaAlterar.getText().isEmpty()) {

            escolaAlterar.setNome(tfNomeEscolaAlterar.getText());
            escolaAlterar.setCidade((Cidade) cbCidadeEscolaAlterar.getSelectedItem());

            new EscolaDAO().atualizar(escolaAlterar);
            Config.mensagemInformativa("Escola atualizada.");

            AlterarEscola.dispose();

            getContentPane().removeAll();

            initComponents();

            init();

            repaint();
            revalidate();

            PainelEscola.fillListaEscola();

        } else {
            Config.mensagemInformativa("Insira um nome.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AlterarEscola.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AlterarEscola;
    private static javax.swing.JComboBox cbCidadeEscolaAlterar;
    private javax.swing.JComboBox cbEscolaCidade;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel iconRelatorio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tEscolas;
    private javax.swing.JTextField tfNomeEscolaAlterar;
    private javax.swing.JTextField tfNomeTurma;
    // End of variables declaration//GEN-END:variables
}
