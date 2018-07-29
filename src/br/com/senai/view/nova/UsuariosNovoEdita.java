/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view.nova;

import br.com.senai.view.*;
import br.com.senai.dao.CidadeDAO;
import br.com.senai.util.Config;
import br.com.senai.dao.EscolaDAO;
import br.com.senai.dao.PermissaoDAO;
import br.com.senai.dao.TipoUsuarioDAO;
import br.com.senai.dao.UsuarioDAO;
import br.com.senai.entities.Cidade;
import br.com.senai.entities.Escola;
import br.com.senai.entities.Permissao;
import br.com.senai.entities.TipoUsuario;
import br.com.senai.entities.Usuario;
import br.com.senai.util.ProgressoEnviaEmailUsuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Hugo
 */
public class UsuariosNovoEdita extends javax.swing.JPanel {

    /**
     * Creates new form PainelUsuario
     */
    private List<Cidade> cidades;
    private List<Escola> escolas;
    private Usuario usuario;
    private HashMap<Integer, Permissao> listaPermissao = new HashMap<>();

    public UsuariosNovoEdita() {
        listaPermissao.clear();
        initComponents();
        Config.configurarPainel(jPanel1);
        init();
        usuario = (Usuario) Config.objetoTemporario;
        Config.objetoTemporario = null;

        if (usuario != null) {
            tfNomeAdm.setText(usuario.getNome());
            tfNomeAdm.setEditable(false);
            tfEmail.setText(usuario.getEmail());
            tfEmail.setEditable(false);
            tfLogin.setText(usuario.getLogin());
            tfLogin.setEditable(false);
            jCheckBox1.setVisible(false);
            jLabel10.setVisible(false);
            tfSenha.setVisible(false);
            jLabel11.setVisible(false);
            tfSenha1.setVisible(false);
            cbAdm.setSelected(usuario.getTipoUsuario().getDescricao().equalsIgnoreCase("Gerente"));
            for (Permissao p : new PermissaoDAO().doUsuario(usuario)) {
                listaPermissao.put(p.getEscola().getId(), p);
            }
            fillAdicionadas();
            btSalvar.setText("Salvar alterações");

        }
    }

    public void fillAdicionadas() {

        DefaultTableModel tableModel = (DefaultTableModel) tPermissoes1.getModel();

        tableModel.setNumRows(0);

        Iterator<?> escolas = new ArrayList(listaPermissao.values()).iterator();

        while (escolas.hasNext()) {
            Permissao p = (Permissao) escolas.next();
            tableModel.addRow(new Object[]{p.getEscola(), p.getEscola().getNome()});
        }

    }

    public void init() {
        Config.setPrompt("usuário@provedor.com / com.br", tfEmail);

        if (Config.usuario.getTipoUsuario().getDescricao().equalsIgnoreCase("Administrador")) {
            cidades = new CidadeDAO().cidadesQuePossuemEscola();
        } else {
            cidades = new CidadeDAO().cidadesPermitidas(Config.usuario);
        }

        if (cidades.size() == 1) {
            if (Config.usuario.getTipoUsuario().getDescricao().equalsIgnoreCase("Administrador")) {
                escolas = new EscolaDAO().escolaDaCidade(cidades.get(0));
            } else {
                escolas = new EscolaDAO().escolasPermitidasDaCidade(Config.usuario, cidades.get(0));
            }
            jLabel34.setVisible(false);
            cbCidadeUsuario.setVisible(false);

            if (escolas.size() == 1) {
                jLabel16.setVisible(false);
                cbEscolaCidade.setVisible(false);
                jScrollPane4.setVisible(false);
                tPermissoes1.setVisible(false);
                jButton3.setVisible(false);
            } else {
                Config.fillComboBox(cbEscolaCidade, escolas);
            }
        } else {
            Config.fillComboBox(cbCidadeUsuario, cidades);
        }
        listaPermissao.clear();
    }

    public void addEscola(Escola e) {

        try {

            Permissao p = (Permissao) listaPermissao.get(e.getId());

            if (p == null) {
                p = new Permissao();
                p.setEscola(e);
                p.setPermissao(true);
                listaPermissao.put(e.getId(), p);

                fillAdicionadas();

            }
        } catch (NullPointerException ex) {

        }
    }

    public void apagarEscola(Escola e) {
        try {
            listaPermissao.remove(e.getId());
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Escolha uma escola primeiro.");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tfLogin = new javax.swing.JTextField();
        tfNomeAdm = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        cbAdm = new javax.swing.JCheckBox();
        btSalvar = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        tfSenha = new javax.swing.JPasswordField();
        tfSenha1 = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        cbEscolaCidade = new javax.swing.JComboBox();
        cbCidadeUsuario = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        tPermissoes1 = new javax.swing.JTable();

        jScrollPane1.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Login:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Nome:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Email:");

        cbAdm.setBackground(new java.awt.Color(255, 255, 255));
        cbAdm.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbAdm.setText("Gerente");
        cbAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAdmActionPerformed(evt);
            }
        });

        btSalvar.setText("Salvar");
        btSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBox1.setText("Utilizar senha automática (ex.: 85421536)");
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Senha:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Confirmar senha:");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Cidade:");

        jButton3.setText("Remover escola");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Escola:");

        cbEscolaCidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEscolaCidadeItemStateChanged(evt);
            }
        });

        cbCidadeUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCidadeUsuarioItemStateChanged(evt);
            }
        });

        tPermissoes1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "iD", "Escolas adicionadas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tPermissoes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tPermissoes1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tPermissoes1);
        if (tPermissoes1.getColumnModel().getColumnCount() > 0) {
            tPermissoes1.getColumnModel().getColumn(0).setMinWidth(0);
            tPermissoes1.getColumnModel().getColumn(0).setPreferredWidth(0);
            tPermissoes1.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(cbCidadeUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(cbEscolaCidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCidadeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEscolaCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                        .addGap(482, 482, 482))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfNomeAdm)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfEmail)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(484, 484, 484))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(484, 484, 484))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(254, 254, 254))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfLogin)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(479, 479, 479))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfSenha)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(421, 421, 421))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfSenha1)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbAdm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(446, 446, 446))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNomeAdm, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbAdm, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btSalvar)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAdmActionPerformed
    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        ProgressoEnviaEmailUsuario emailUsuario = null;
        if (usuario == null) {
            if (!tfNomeAdm.getText().isEmpty() && !tfEmail.getText().isEmpty() && !tfLogin.getText().isEmpty() && tfSenha.getPassword().length != 0 && tfSenha1.getPassword().length != 0) {
                if (tfEmail.getText().matches("[a-z0-9]{4,}@[a-z]+(.com|.com.[a-z]+)")) {
                    if (Arrays.equals(tfSenha.getPassword(), tfSenha1.getPassword())) {

                        synchronized (UsuariosNovoEdita.class) {
                            try {
                                jPanel1.removeAll();

                                jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.LINE_AXIS));

                                JLabel jLabel = new JLabel("<html>Aguarde enviando email de confirmação<br>para o usuário cadastrado...");

                                jLabel.setFont(new java.awt.Font("Tahoma", 1, 18));

                                jPanel1.add(jLabel);

                                jPanel1.repaint();
                                jPanel1.revalidate();

                                String senha = "";

                                usuario.setLogin(tfLogin.getText());
                                usuario.setNome(tfNomeAdm.getText());
                                usuario.setEmail(tfEmail.getText());
                                usuario.setCriadoPor(Config.usuario);

                                if (jCheckBox1.isSelected()) {
                                    senha = RandomStringUtils.randomNumeric(8);
                                    usuario.setSenhaAlterada(true);
                                } else {
                                    senha = new String(tfSenha.getPassword());
                                }

                                usuario.setSenha(Config.criptografarSenha(senha));

                                if (cbAdm.isSelected()) {
                                    usuario.setTipoUsuario(new TipoUsuarioDAO().getByName("Gerente"));
                                } else {
                                    usuario.setTipoUsuario(new TipoUsuarioDAO().getByName("Funcionário"));
                                }

                                new UsuarioDAO().salvar(usuario);

                                salvarPermissoes();
                                
                                MensagemAguarde ma = new MensagemAguarde();
                                ma.setVisible(true);
                                emailUsuario = new ProgressoEnviaEmailUsuario(ma, usuario, senha);
                                emailUsuario.execute();

                            } catch (ConstraintViolationException ex) {
                                JOptionPane.showMessageDialog(null, "Login já existente.");
                            }

                            tfNomeAdm.setText(null);
                            tfEmail.setText(null);
                            tfLogin.setText(null);
                            tfSenha.setText(null);
                            tfSenha1.setText(null);

                            cbAdm.setSelected(false);
                            jCheckBox1.setSelected(false);
                            tfSenha.setEnabled(true);
                            tfSenha1.setEnabled(true);

                            removeAll();

                            initComponents();
                            init();
                            fillAdicionadas();
                            repaint();
                            revalidate();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Senhas não coincidem.", "Resposta", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Email incorreto.", "Resposta", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha os campos corretamente.", "Resposta", JOptionPane.INFORMATION_MESSAGE);

            }
        } else {
            if (!listaPermissao.isEmpty()) {
                String tipo;
                if (cbAdm.isSelected()) {
                    tipo = "Gerente";
                } else {
                    tipo = "Funcionário";
                }

                TipoUsuario tu = new TipoUsuarioDAO().getByName(tipo);

                usuario.setTipoUsuario(tu);
                new UsuarioDAO().atualizar(usuario);

                Iterator<?> permissoes = new ArrayList(listaPermissao.values()).iterator();

                while (permissoes.hasNext()) {

                    Permissao p = (Permissao) permissoes.next();

                    if (new PermissaoDAO().doUsuarioEEscola(usuario, p.getEscola()) == null) {
                        p.setUsuario(usuario);
                        new PermissaoDAO().salvar(p);
                    }

                }
                JOptionPane.showMessageDialog(null, "Alterações salvas com sucesso!!");

            } else {
                Config.mensagemDeAviso("Nennhuma escola permitida");
            }
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void cbCidadeUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCidadeUsuarioItemStateChanged

        if (cbCidadeUsuario.getSelectedItem() instanceof Cidade) {

            if (Config.usuario.getTipoUsuario().getDescricao().equalsIgnoreCase("Administrador")) {
                Config.fillComboBox(cbEscolaCidade, new EscolaDAO().escolaDaCidade((Cidade) cbCidadeUsuario.getSelectedItem()));
            } else {
                Config.fillComboBox(cbEscolaCidade, new EscolaDAO().escolasPermitidasDaCidade(Config.usuario, (Cidade) cbCidadeUsuario.getSelectedItem()));
            }

            cbEscolaCidade.setEnabled(true);

        } else {
            cbEscolaCidade.setEnabled(false);
        }

    }//GEN-LAST:event_cbCidadeUsuarioItemStateChanged

    private void tPermissoes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tPermissoes1MouseClicked

        if (tPermissoes1.getSelectedRowCount() > 0) {
            jButton3.setEnabled(true);
        } else {
            jButton3.setEnabled(false);
        }
    }//GEN-LAST:event_tPermissoes1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (tPermissoes1.getSelectedRowCount() > 0) {
            if (usuario == null) {

                apagarEscola((Escola) tPermissoes1.getValueAt(tPermissoes1.getSelectedRow(), 0));
                if (cidades.size() == 1) {
                    Config.fillComboBox(cbEscolaCidade, new EscolaDAO().escolaDaCidade(cidades.get(0)));
                } else {
                    Config.fillComboBox(cbEscolaCidade, new EscolaDAO().escolaDaCidade((Cidade) cbCidadeUsuario.getSelectedItem()));

                }

            } else {
                Escola escola = (Escola) tPermissoes1.getValueAt(tPermissoes1.getSelectedRow(), 0);
                Permissao p = new PermissaoDAO().doUsuarioEEscola(usuario, escola);
                if (p != null) {
                    new PermissaoDAO().deletar(p);
                }
                listaPermissao.remove(escola.getId());
            }
            fillAdicionadas();
            jButton3.setEnabled(false);

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma escola");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cbEscolaCidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEscolaCidadeItemStateChanged

        if (cbEscolaCidade.getSelectedItem() instanceof Escola) {
            addEscola((Escola) cbEscolaCidade.getSelectedItem());
        }

    }//GEN-LAST:event_cbEscolaCidadeItemStateChanged

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged


    }//GEN-LAST:event_jCheckBox1StateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            tfSenha.setEnabled(false);
            tfSenha1.setEnabled(false);
            tfSenha.setText("---");
            tfSenha1.setText("---");
        } else {

            tfSenha.setEnabled(true);
            tfSenha1.setEnabled(true);
            tfSenha.setText(null);
            tfSenha1.setText(null);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSalvar;
    private javax.swing.JCheckBox cbAdm;
    private javax.swing.JComboBox cbCidadeUsuario;
    private javax.swing.JComboBox cbEscolaCidade;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tPermissoes1;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfLogin;
    private javax.swing.JTextField tfNomeAdm;
    private javax.swing.JPasswordField tfSenha;
    private javax.swing.JPasswordField tfSenha1;
    // End of variables declaration//GEN-END:variables

    private void salvarPermissoes() {
        Iterator<?> permissoes = new ArrayList(listaPermissao.values()).iterator();
        while (permissoes.hasNext()) {
            Permissao p = (Permissao) permissoes.next();
            p.setUsuario(usuario);
            new PermissaoDAO().salvar(p);
        }
    }
}
