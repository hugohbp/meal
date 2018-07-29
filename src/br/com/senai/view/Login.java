/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view;

import br.com.senai.util.Config;
import br.com.senai.dao.UsuarioDAO;
import br.com.senai.entities.Usuario;
import br.com.senai.util.EncriptacaoAES;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;
import org.hibernate.HibernateException;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Hugo
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
//    Timer timer = new Timer(10000, new AbstractAction("banco") {
//        Socket socket = null;
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//            try {
//                socket = new Socket("127.0.0.1", 3306);
//
//                if (socket != null) {
////                    System.out.println("--------IP--------");
////                    System.out.println(socket.getInetAddress().getHostAddress());
//                    socket.close();
//                }
//
//            } catch (java.net.NoRouteToHostException ex) {
//
//                Config.mensagemDeErro("Falha de rede. Contate o administrador.");
//                if (JOptionPane.showConfirmDialog(null, "Deseja encerrar a aplicação?", "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
//                    System.exit(0);
//                } else {
//                    
//                    Window window = new Window(null);
//                    
//                    for (Window window1 : Window.getWindows()) {
//                        if(window1.isVisible()){
//                            System.out.println(window1.getClass());
//                            if(window1 instanceof JFrame || window1 instanceof JDialog){
//                                window=window1;
//                                break;
//                            }
//                        }    
////                        if (window1.isShowing() && window1.isFocused()) {
////                            window = window1;
////                        }
//
//                    }
//                    if(timer.isRunning()){
//                        timer.stop();
//                    }
//                    new MensagemAguarde2(window, true,timer).setVisible(true);
//                }
//
//            } catch (IOException ex) {
//                Config.mensagemDeErro(ex.getMessage());
//            }
//
//        }
//    });
    public Login() {
        initComponents();
//        timer.start();
        Config.configurarJanela(this);

        labelFoto.setIcon(new ImageIcon(new ImageIcon("imagens/painel1.jpg").getImage().getScaledInstance(labelFoto.getWidth(), labelFoto.getHeight(), Image.SCALE_SMOOTH)));
        logo.setIcon(new ImageIcon(new ImageIcon("imagens/logoMEAL.PNG").getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH)));

        PromptSupport.setPrompt("Login", tfLogin);
        PromptSupport.setFontStyle(Font.BOLD, tfLogin);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, tfLogin);

        PromptSupport.setPrompt("Senha", tfSenha);
        PromptSupport.setFontStyle(Font.BOLD, tfSenha);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, tfSenha);

        jLabel1.setIcon(new ImageIcon(new ImageIcon("imagens/senaiIMG.jpg").getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH)));

        toFront();
        toFront();
        toFront();

        final AbstractAction escapeAction = new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent ae) {
                JPasswordField jpassword = new JPasswordField();
                if (JOptionPane.showConfirmDialog(null, jpassword, "Entre com a senha", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION) {
                    if (new String(jpassword.getPassword()).equalsIgnoreCase("java123")) {
                        new CadastroDeAtualizacao().setVisible(true);
                    }
                }
            }
        };

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), "ESCAPE_KEY");

        getRootPane().getActionMap().put("ESCAPE_KEY", escapeAction);
        lAtualizacao.setVisible(false);

        if (Config.contador == 0) {

            if (Config.atualizacao != null && !Config.atualizacao.getUrl().isEmpty()) {

                Config.mensagemInformativa(Config.NOME_SISTEMA_SIGLA + " - Informação", "Uma nova versão do " + Config.NOME_SISTEMA_SIGLA + " foi lançada!\n A versão " + Config.atualizacao.getVersao() + " está disponível.");
                lAtualizacao.setText("<html><font size=3><u> Nova atualização: " + Config.atualizacao.getNome() + "</u></font></html>");
                lAtualizacao.setToolTipText("Clique para baixar a nova atualização.");
                lAtualizacao.setVisible(true);

            }
            Config.contador++;
        }

        cbSenha.setEnabled(false);
        carregarParametros();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        tfLogin = new javax.swing.JTextField();
        tfSenha = new javax.swing.JPasswordField();
        labelFoto = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lAtualizacao = new javax.swing.JLabel();
        cbLogin = new javax.swing.JCheckBox();
        cbSenha = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Área de Login -");
        setResizable(false);

        jButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton1.setMnemonic('e');
        jButton1.setText("Entrar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tfLogin.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfLogin.setToolTipText("Digite seu login");
        tfLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfLoginKeyPressed(evt);
            }
        });

        tfSenha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfSenha.setToolTipText("Digite sua senha");
        tfSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfSenhaKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Esqueceu sua senha?");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        lAtualizacao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lAtualizacao.setText("Atualização:");
        lAtualizacao.setToolTipText("");
        lAtualizacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lAtualizacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lAtualizacaoMouseClicked(evt);
            }
        });

        cbLogin.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        cbLogin.setText("Manter meu login");
        cbLogin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbLoginStateChanged(evt);
            }
        });

        cbSenha.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        cbSenha.setText("Salvar minha senha");
        cbSenha.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbSenhaStateChanged(evt);
            }
        });
        cbSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addComponent(lAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfLogin, tfSenha});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lAtualizacao)))
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tfLogin, tfSenha});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
 public void logar() {
        Usuario u = null;

        try {

            if (new UsuarioDAO().validarUsuario(tfLogin.getText()) != null) {

//                if (!manterSenha.equals("false") && !tfSenha.getText().isEmpty()) {
//                    u = new UsuarioDAO().validarLogin(tfLogin.getText(), new String(tfSenha.getPassword()));
//                } else {
                u = new UsuarioDAO().validarLogin(tfLogin.getText(), Config.criptografarSenha(tfSenha.getPassword()));
//                }
                if (u != null) {
                    Config.usuario = u;

                    salvarParametros();

                    dispose();

                    if (Config.usuario.isSenhaAlterada()) {
                        JOptionPane.showMessageDialog(null, "É recomendável alterar sua senha.");

                        new AlterarSenha().setVisible(true);

                    } else {
                        if (u.getTipoUsuario().getDescricao().equalsIgnoreCase("Funcionário")) {
                            new Menu().setVisible(true);
                        } else {
                            new MenuAdm().setVisible(true);
                        }

                    }
                } else {
                    tfSenha.setText(null);
                    tfSenha.requestFocus();
                    Config.mensagemInformativa("Senha inválida.");

                }
            } else {
                tfLogin.setText(null);
                tfSenha.setText(null);
                tfLogin.requestFocus();
                Config.mensagemInformativa("Login inexistente.");
            }

        } catch (HeadlessException | HibernateException | NoClassDefFoundError e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "FALHA AO INICIAR O BANCO", "erro", JOptionPane.ERROR_MESSAGE);
            new OpcoesBanco().setVisible(true);
        }

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
                tfSenha.setText(Config.manterSenha);
            }
        }

    }

    public void salvarParametros() {
        EncriptacaoAES encriptacaoAES = new EncriptacaoAES();
        Config.manterLogin = encriptacaoAES.encriptar(cbLogin.isSelected() ? tfLogin.getText() : "false");
        Config.manterSenha = encriptacaoAES.encriptar(cbSenha.isSelected() ? new String(tfSenha.getPassword()) : "false");
        Config.salvarParametros();
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        logar();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void tfSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSenhaKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            logar();

        }
    }//GEN-LAST:event_tfSenhaKeyPressed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        new EsqueciASenha().setVisible(true);


    }//GEN-LAST:event_jLabel3MouseClicked

    private void tfLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            logar();

        }
    }//GEN-LAST:event_tfLoginKeyPressed

    private void lAtualizacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lAtualizacaoMouseClicked

        try {
            Desktop.getDesktop().browse(new URI(Config.atualizacao.getUrl()));

        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(Login.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_lAtualizacaoMouseClicked

    private void cbSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSenhaActionPerformed

    private void cbLoginStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbLoginStateChanged

//        if (!tfLogin.getText().isEmpty()) {
        if (cbLogin.isSelected()) {
            cbSenha.setEnabled(true);
        } else {
            cbSenha.setEnabled(false);
            cbSenha.setSelected(false);
        }

//        } else {
//            cbLogin.setSelected(false);
//        }
    }//GEN-LAST:event_cbLoginStateChanged

    private void cbSenhaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbSenhaStateChanged
    }//GEN-LAST:event_cbSenhaStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbLogin;
    private javax.swing.JCheckBox cbSenha;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lAtualizacao;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField tfLogin;
    private javax.swing.JPasswordField tfSenha;
    // End of variables declaration//GEN-END:variables
}
