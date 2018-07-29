/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view;

import br.com.senai.util.Config;
import br.com.senai.dao.AlunoDAO;
import br.com.senai.dao.EscolaDAO;
import br.com.senai.dao.FichaDAO;
import br.com.senai.dao.MovimentacaoDAO;
import br.com.senai.entities.Aluno;
import br.com.senai.entities.Escola;
import br.com.senai.entities.Ficha;
import br.com.senai.entities.Movimentacao;
import br.com.senai.util.PlayAudio;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Hugo
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    boolean audio = false;

    public String alterarTexto(String texto) {
        return "<font size=3><b>" + texto + "</b></font></html>";
    }

    public Menu() {

        initComponents();

        jLabel4.setText("<html>" + alterarTexto("movimentação"));
        jLabel4.setToolTipText("Clique para abrir o histórico de retirada dos lanches");

        Config.configurarJanela(this);

        this.setExtendedState(Menu.MAXIMIZED_BOTH);

        jLabel2.setText(Config.usuario.getNome());

        //Master
        try {

            if (Config.usuario.getTipoUsuario().getDescricao().equalsIgnoreCase("Master")) {

                tfRA.setEnabled(false);

            } else if (Config.usuario.getTipoUsuario().getDescricao().equalsIgnoreCase("Admin")) {
                tfRA.setEnabled(false);

            }
        } catch (NullPointerException ex) {

        }

        final AbstractAction escapeAction = new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (audio) {
                    audio = false;
                } else {
                    audio = true;
                }

            }
        };

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.CTRL_DOWN_MASK), "PROGRAM_KEY");

        getRootPane().getActionMap().put("PROGRAM_KEY", escapeAction);

        jLabel3.setIcon(new ImageIcon(new ImageIcon("imagens/senaiIMG.jpg").getImage().getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(), Image.SCALE_SMOOTH)));

        jMenu5.setEnabled(!Config.usuario.getTipoUsuario().getDescricao().equalsIgnoreCase("Master"));

    }

    public static String raAluno = "";

    public Date setData(int hora, int minuto) {

        Calendar c = Calendar.getInstance();

        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), hora, minuto, 0);

        return c.getTime();

    }

    public String turnoAtual() {

        Calendar c = Calendar.getInstance();

        String turno = "";

        if (new Date().after(setData(8, 0)) && (new Date().before(setData(12, 0)))) {
            turno = "Manhã";
        } else if (new Date().after(setData(13, 0)) && new Date().before(setData(17, 0))) {
            turno = "Tarde";
        } else if (new Date().after(setData(18, 30)) && new Date().before(setData(22, 30))) {
            turno = "Noite";
        } else {
            turno = "Integral";
        }

        return turno;

    }

    public void fazerMovimentacao() {

//        System.out.println(turnoAtual());
        if (!tfRA.getText().isEmpty()) {
            if (new Config().verificarFicha()) {
                Aluno a = null;
                boolean chave = false;

                try {

                    Long id = Long.parseLong(tfRA.getText());

//                    AlunoPK pk = new AlunoPK();
//                    
//                    pk.setId(Long.parseLong(tfRA.getText()));
//                    pk.setTurma( new TurmaDAO().pegarTurmaDoTurnoEAluno(Long.parseLong(tfRA.getText()), turnoAtual()));
                    //   System.out.println(pk.getTurma().getNome());
//                    a = new AlunoDAO().buscarPorId(pk);
                    a = new AlunoDAO().validarTurma(id, "Integral");

                    if (a == null) {
                        a = new AlunoDAO().validarTurma(id, turnoAtual());
                    }

                    if (a != null) {

                        if (a.getTurma().isAtiva()) {
                            if (Config.filtroEscolaNaoPermitida.equalsIgnoreCase("true")) {
                                List<String> linhas = Files.readAllLines(Paths.get("escolas.txt"), StandardCharsets.UTF_8);
                                for (String linha : linhas) {
                                    String[] split = linha.split(";");
                                    for (String palavra : split) {
                                        if (palavra.equalsIgnoreCase(a.getTurma().getCurso().getPk().getEscola().getNome())) {
                                            for (Escola e : new EscolaDAO().escolasPermitidas(Config.usuario)) {
                                                if (e.equals(a.getTurma().getCurso().getPk().getEscola())) {
                                                    chave = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }else{
                                chave = true;
                            }
                            
                            if (chave) {
                                try {
                                    Movimentacao m = new Movimentacao();

                                    m.setDataMovimentacao(new Date());
                                    m.setHorarioMovimentacao(new Date());
                                    m.setAluno(a);

                                    Ficha f = new FichaDAO().pegarUltimoValor(a.getTurma().getCurso().getPk().getEscola());

//                                    System.out.println("Valor da ficha da escola: " + f.getValor());
                                    m.setFicha(f);

                                    try {
                                        jLabel4.setText("<html>Última movimentação: " + alterarTexto(a.getNome()));
                                        tfRA.setText(null);
                                        tfRA.requestFocus();

                                        new MovimentacaoDAO().salvar(m);
                                        new MensagemOK().setVisible(true);

                                    } catch (ConstraintViolationException e) {

                                        tfRA.setText(null);
                                        tfRA.requestFocus();

                                        new MensagemERRO().setVisible(true);

                                        if (audio) {

                                            PlayAudio pa = new PlayAudio("completo.mp3");

                                            pa.play();
                                        }

                                    }

                                } catch (NullPointerException ex) {
                                    JOptionPane.showMessageDialog(null, "Cadastre o valor do lanche.");
                                    tfRA.setText(null);
                                    tfRA.requestFocus();
                                }

                            } else {
                                tfRA.setText(null);
                                tfRA.requestFocus();
                                new MensagemEscola().setVisible(true);

                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Turma não existe mais.\nObs.: o curso acabou ou a turma foi fechada.");

                            tfRA.setText(null);
                            tfRA.requestFocus();

                        }

                    } else {

                        if (!new AlunoDAO().alunosComID(id).isEmpty()) {

                            JOptionPane.showMessageDialog(null, "Aluno cadastrado, porém não está estudando no momento.");

                            tfRA.setText(null);
                            tfRA.requestFocus();

                        } else {
                            raAluno = tfRA.getText();
                            new MensagemUsuarioInexistente().setVisible(true);

                            tfRA.setText(null);
                        }

                    }

                } catch (NumberFormatException | IOException ex) {
                    JOptionPane.showMessageDialog(null, "Dados incorretos");
                    tfRA.setText(null);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cadastre um valor da ficha primeiro.");
                tfRA.setText(null);
                tfRA.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
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

        tfRA = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        barraDeMenu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tfRA.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        tfRA.setToolTipText("Insira o registro do aluno");
        tfRA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfRAMouseClicked(evt);
            }
        });
        tfRA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfRAKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfRAKeyReleased(evt);
            }
        });

        jLabel1.setText("Insira o R.A. do aluno:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("jLabel2");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("registros...");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jMenu1.setText("Arquivo");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Sair");
        jMenuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Fechar programa");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        barraDeMenu.add(jMenu1);

        jMenu2.setText("Minhas informações");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setText("Alterar");
        jMenuItem3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        barraDeMenu.add(jMenu2);

        jMenu5.setText("Ferramentas");
        jMenu5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Escolas");
        jMenuItem6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        barraDeMenu.add(jMenu5);

        jMenu3.setText("Ajuda");
        jMenu3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem13.setText("Sugestões");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem13);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setText("Exibir ajuda");
        jMenuItem12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setText("Sobre");
        jMenuItem11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        barraDeMenu.add(jMenu3);

        setJMenuBar(barraDeMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(441, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(tfRA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfRA, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfRAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfRAMouseClicked
        if (!tfRA.getText().isEmpty()) {
            tfRA.setText("");
        }

    }//GEN-LAST:event_tfRAMouseClicked

    private void tfRAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfRAKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            fazerMovimentacao();
        }
    }//GEN-LAST:event_tfRAKeyPressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        dispose();

        for (Window w : Window.getWindows()) {
            w.dispose();
        }
        new Login().setVisible(true);


    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

        if (Config.filtroEscolaNaoPermitida.equals("true")) {
            new Escolas().setVisible(true);
        } else {
            Config.mensagemDeAviso("FUNÇÃO DESATIVADA");
        }


    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new AlterarInformacoes().setVisible(true);

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        new Sobre().setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        new JanelaAjuda().setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void tfRAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfRAKeyReleased
        if (!tfRA.getText().isEmpty()) {
            try {
                Long num = Long.parseLong(tfRA.getText());
            } catch (NumberFormatException ex) {
                tfRA.setText(null);

            }
        }

    }//GEN-LAST:event_tfRAKeyReleased

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed

        new Sugestoes().setVisible(true);


    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        new VisualizarMovimentacoes().setVisible(true);

    }//GEN-LAST:event_jLabel4MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraDeMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JTextField tfRA;
    // End of variables declaration//GEN-END:variables
}
