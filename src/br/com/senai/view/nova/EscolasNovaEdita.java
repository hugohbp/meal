/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view.nova;

import br.com.senai.util.Config;
import br.com.senai.dao.CidadeDAO;
import br.com.senai.dao.EscolaDAO;
import br.com.senai.entities.Cidade;
import br.com.senai.entities.Escola;
import javax.swing.JOptionPane;
import org.hibernate.exception.ConstraintViolationException;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Hugo
 */
public class EscolasNovaEdita extends javax.swing.JPanel {

    private Escola escola = new Escola();

    public EscolasNovaEdita() {
        iniciar();
    }

    public void iniciar() {
        initComponents();
        Config.configurarPainel(jPanel1);
        Config.fillComboBox(cbCidades, new CidadeDAO().listar());
        AutoCompleteDecorator.decorate(cbCidades);
        escola = (Escola) Config.objetoTemporario;
        if (escola != null) {
            tfNomeEscola.setText(escola.getNome());
            cbCidades.setSelectedItem(escola.getCidade());
            jButton5.setText("Salvar alterações");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfNomeEscola = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tEscolas1 = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        cbCidades = new javax.swing.JComboBox();

        jScrollPane1.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Nome da escola:");

        jButton5.setText("Salvar");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        tEscolas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Escola", "Cidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tEscolas1);
        if (tEscolas1.getColumnModel().getColumnCount() > 0) {
            tEscolas1.getColumnModel().getColumn(0).setMinWidth(0);
            tEscolas1.getColumnModel().getColumn(0).setPreferredWidth(0);
            tEscolas1.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Cidade:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfNomeEscola)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(416, 416, 416))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(466, 466, 466))
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbCidades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNomeEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (!tfNomeEscola.getText().isEmpty()) {
            if (!cbCidades.getSelectedItem().toString().equalsIgnoreCase("Selecione...")) {
                try {
                    try {
                        if (escola == null) {
                            Escola e = new Escola();

                            e.setNome(tfNomeEscola.getText());
                            e.setCidade((Cidade) cbCidades.getSelectedItem());

                            new EscolaDAO().salvar(e);
                            tfNomeEscola.setText(null);
                            tfNomeEscola.requestFocus();
                            Config.fillComboBox(cbCidades, new CidadeDAO().listar());
                            Config.mensagemSalvo();
                        } else {
                            escola.setNome(tfNomeEscola.getText());
                            escola.setCidade((Cidade) cbCidades.getSelectedItem());
                            new EscolaDAO().atualizar(escola);
                            Config.mensagemAtualizado();
                        }

                    } catch (ConstraintViolationException ex) {
                        Config.mensagemDeErro("Escola já cadastrada.");
                        tfNomeEscola.requestFocus();
                    }

                } catch (ClassCastException ex) {
                    JOptionPane.showMessageDialog(null, "Escola não permitida.");
                }
            } else {
                Config.mensagemInformativa("Selecione uma cidade.");
                cbCidades.requestFocus();
            }
        } else {
            Config.mensagemInformativa("Insira o nome da escola no seu respectivo campo.");
            tfNomeEscola.requestFocus();
        }


    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbCidades;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable tEscolas1;
    private javax.swing.JTextField tfNomeEscola;
    // End of variables declaration//GEN-END:variables
}
