/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view.nova;

import br.com.senai.util.Config;
import br.com.senai.view.PesquisaCursos;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Hugo
 */
public class Inicio extends javax.swing.JPanel {

    private JPanel jPanel;

    public Inicio(JPanel jPanel) {
        this.jPanel = jPanel;
        initComponents();
        carregarComponentes();
    }

    private void carregarComponentes() {

        jLabel1.setText(Config.usuario.getNome());
        jLabel2.setIcon(new ImageIcon(new ImageIcon("imagens/logoMEAL.PNG").getImage().getScaledInstance(178, 64, Image.SCALE_SMOOTH)));
        jLabel4.setIcon(new ImageIcon(new ImageIcon("imagens/logoSenai.png").getImage().getScaledInstance(131, 17, Image.SCALE_SMOOTH)));
        jPanel2.removeAll();

        jPanel2.setLayout(new GridLayout(0, 3, 0, 0));
        jPanel2.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        jLabel3.setText("BETA VERSION " + Config.VERSAO_DO_SISTEMA_BETA + "   ");

        if (Config.contador == 1) {
            carregarMenuInformacoesGerais();
        } else {
            carregarItensDoMenu();
        }
    }

    public HashMap<String, Object> menuUsuarios = new HashMap<>();
    public HashMap<String, Object> menuEscolas = new HashMap<>();
    public HashMap<String, Object> menuModalidades = new HashMap<>();

    public HashMap<String, Object> menuRelatorios = new HashMap<>();
    public HashMap<String, Object> menuEstatisticas = new HashMap<>();
    public HashMap<String, Object> menuImportar = new HashMap<>();
    public HashMap<String, Object> menuCadastroDeCurso = new HashMap<>();
    public HashMap<String, Object> menuCursos = new HashMap<>();
    public HashMap<String, Object> menuTurmas = new HashMap<>();
    public HashMap<String, Object> menuAlunosFuncionarios = new HashMap<>();

    public HashMap<String, Object> menuFerramentas = new HashMap<>();
    public HashMap<String, Object> menuSugestao = new HashMap<>();
    public HashMap<String, Object> menuAjuda = new HashMap<>();

    private void adicionarPainel(String titulo, HashMap<String, Object> menu) {
        JLabelMenu.itemSelecionado = "";
        Config.adicionarPainel(Config.PAGINA_INICIAL, titulo, jPanel, new Painel(jPanel, titulo, menu, true, false));
    }

    public void carregarItensDoMenu() {
        jPanel2.removeAll();
        JPanelPaginaInicial jpEscolas = new JPanelPaginaInicial("imagens/icones/2.png", "imagens/icones/21.png", "Escolas", "Criar, Pesquisar e Remover escolas");
        menuEscolas.put("Pesquisar", EscolasPesquisar.class);
        menuEscolas.put("Nova", EscolasNovaEdita.class);
        jpEscolas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                adicionarPainel("Escolas", menuEscolas);
            }
        });

        JPanelPaginaInicial jpModalidades = new JPanelPaginaInicial("imagens/icones/14.png", "imagens/icones/141.png", "Modalidades", "Criar, Editar e Remover modalidades");
        menuModalidades.put("Modalidades", Modalidades.class);
        menuModalidades.put("Nova", ModalidadesNovaEdita.class);
        jpModalidades.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                adicionarPainel("Modalidades", menuModalidades);
            }
        });

        JPanelPaginaInicial jpUsuarios = new JPanelPaginaInicial("imagens/icones/61.png", "imagens/icones/6.png", "Usuários",
                "Criar, Pesquisar e Remover usuários");
        menuUsuarios.put("Pesquisar", UsuariosPesquisar.class);
        menuUsuarios.put("Novo", UsuariosNovoEdita.class);
        jpUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                adicionarPainel("Usuários", menuUsuarios);
            }
        });

        JPanelPaginaInicial jpFerramentas = new JPanelPaginaInicial("imagens/icones/repair6.png", "imagens/icones/repair11.png", "Ferramentas", "Itens de configuração");

        jpFerramentas.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                adicionarPainel("Ferramentas", menuFerramentas);
            }
        });

        JPanelPaginaInicial jpSugestao = new JPanelPaginaInicial("imagens/icones/down.png", "imagens/icones/download168.png", "Sugestão", "Envie sua sugestão ;)");
        menuSugestao.put("Env. sugestão", Sugestao.class);
        jpSugestao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                adicionarPainel("Sugestão", menuSugestao);
            }
        });
        JPanelPaginaInicial jpAjuda = new JPanelPaginaInicial("imagens/icones/help.png", "imagens/icones/question.png", "Ajuda", "Saiba como utilizar o sistema");
        menuAjuda.put("Manual", AjudaManual.class);
        menuAjuda.put("Sobre", AjudaSobre.class);
        jpAjuda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                adicionarPainel("Ajuda", menuAjuda);
            }
        });

        JPanelPaginaInicial jpSair = new JPanelPaginaInicial("imagens/icones/closed42.png", "imagens/icones/open129.png", "Sair", "");

        jpSair.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                for (Window w : Window.getWindows()) {
                    w.dispose();
                }
                new Login().setVisible(true);
            }
        });

        JPanelPaginaInicial jpRelatorios = new JPanelPaginaInicial("imagens/icones/analytic-report.png", "imagens/icones/analytic-report.png", "Relatórios", "Criar, Analisar e Retirar relatórios");
        menuRelatorios.put("Configurar", RelatoriosConfigurar.class);
        jpRelatorios.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                adicionarPainel("Relatórios", menuRelatorios);
            }

        });

        JPanelPaginaInicial jpEstatisticas = new JPanelPaginaInicial("imagens/icones/closed40.png", "imagens/icones/female96.png", "Estatísticas", "");
        JPanelPaginaInicial jpImportar = new JPanelPaginaInicial("imagens/icones/upload.png", "imagens/icones/upload.png", "Importar alunos/funcionários", "");
        menuImportar.put("Importar alunos/funcionários", ImportarAlunosFuncionarios.class);
        jpImportar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                adicionarPainel("Importar alunos/funcionários", menuImportar);
            }

        });
        JPanelPaginaInicial jpInformacoesGerais = new JPanelPaginaInicial("imagens/icones/newspaper.png", "imagens/icones/newspaper.png", "Informações gerais", "Dados alunos/funcionários");
        jpInformacoesGerais.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Config.contador = 1;
                carregarMenuInformacoesGerais();
            }
        });

        if (Config.usuario.getTipoUsuario().getDescricao().equalsIgnoreCase("Administrador")) {
            jPanel2.add(jpEscolas);
            jPanel2.add(jpModalidades);
            menuFerramentas.put("Conf. conexão", FerramentasConexao.class);
            menuFerramentas.put("Backups", FerramentasBackups.class);
            menuFerramentas.put("Criar backup", new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (Config.mensagemDeConfirmacaoExclusao("Deseja realizar um backup?") == JOptionPane.YES_OPTION) {
                        File file = new File("backups");
                        try {
                            file.mkdirs();
                            Runtime.getRuntime().exec("cmd /c mysqldump -u" + Config.usuarioBD + " -p" + Config.senhaBD + " -h" + Config.urlBD + " --database " + Config.NOME_BANCO_DE_DADOS + " >  backups/" + Config.formatarData(new Date()) + ".sql");
                            Config.mensagemInformativa("BACKUP REALIZADO COM SUCESSO!!!");

                        } catch (IOException ex) {
                            Config.mensagemDeErro(ex.getMessage());
                        }
                    }
                }

            });

        } else {
            jPanel2.add(jpRelatorios);
//            jPanel2.add(jpEstatisticas);
            jPanel2.add(jpImportar);
            jPanel2.add(jpInformacoesGerais);
            menuFerramentas.put("Valor do lanche", FerramentasValorDaFicha.class);
            menuFerramentas.put("Opções", FerramentasOpcoes.class);
        }
        jPanel2.add(jpSugestao);
        jPanel2.add(jpFerramentas);
        jPanel2.add(jpUsuarios);
        jPanel2.add(jpAjuda);
        jPanel2.add(jpSair);

        jPanel2.repaint();
        jPanel2.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(755, 370));
        addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                formAncestorResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 239, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("NOME USUÁRIO");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel3.setText("2016");

        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.setMaximumSize(new java.awt.Dimension(272, 64));
        jLabel2.setMinimumSize(new java.awt.Dimension(272, 64));
        jLabel2.setPreferredSize(new java.awt.Dimension(272, 64));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formAncestorResized
        Config.adicionarPainel(jPanel, new Inicio(jPanel));
    }//GEN-LAST:event_formAncestorResized
    public HashMap<String, Object> menuPerfil = new HashMap<>();
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        JLabelMenu.itemSelecionado = "";
        menuPerfil.put("Informações", PerfilInfo.class);
        menuPerfil.put("Alterar senha", PerfilSenha.class);
        new PainelJDialog(Config.usuario.getNome(), menuPerfil).setVisible(true);

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        adicionarPainel("Ajuda", menuAjuda);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        try {
            Desktop.getDesktop().browse(new URI("http://www7.fiemg.com.br/senai/mais-senai/na-sua-cidade/senai-belo-horizonte-cetel-cesar-rodrigues"));
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(br.com.senai.view.Login.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void carregarMenuInformacoesGerais() {
        jPanel2.removeAll();
        jPanel2.setBackground(new Color(240, 230, 140));
        JPanelPaginaInicial jpCadastroDeCurso = new JPanelPaginaInicial("imagens/icones/17.png", "imagens/icones/171.png", "Cadastro de cursos", "", new Color(240, 230, 140));
        menuCadastroDeCurso.put("Cursos", Cursos.class);
        menuCadastroDeCurso.put("Novo", CadastroDeCursosNovoEdita.class);
        jpCadastroDeCurso.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                adicionarPainel("Cadastro de curso", menuCadastroDeCurso);
            }

        });
        JPanelPaginaInicial jpCursos = new JPanelPaginaInicial("imagens/icones/31.png", "imagens/icones/3.png", "Cursos", "", new Color(240, 230, 140));
        menuCursos.put("Pesquisar", PainelDefault.class);
        menuCursos.put("Novo", CursosNovoEdita.class);
        jpCursos.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                adicionarPainel("Cursos", menuCursos);
            }

        });
        JPanelPaginaInicial jpTurmas = new JPanelPaginaInicial("imagens/icones/41.png", "imagens/icones/4.png", "Turmas", "", new Color(240, 230, 140));
        menuTurmas.put("Pesquisar", PainelDefault.class);
        menuTurmas.put("Nova", TurmasNovaEdita.class);
        jpTurmas.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                adicionarPainel("Turmas", menuTurmas);
            }

        });
        JPanelPaginaInicial jpAlunosFuncionarios = new JPanelPaginaInicial("imagens/icones/51.png", "imagens/icones/5.png", "Alunos/Funcionários", "", new Color(240, 230, 140));
        menuAlunosFuncionarios.put("Pesquisar", PainelDefault.class);
        menuAlunosFuncionarios.put("Novo", AlunosFuncionariosNovoEdita.class);
        menuAlunosFuncionarios.put("Lixeira", PainelDefault.class);
        jpAlunosFuncionarios.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                adicionarPainel("Alunos/Funcionários", menuAlunosFuncionarios);
            }

        });
        JPanelPaginaInicial jpVoltar = new JPanelPaginaInicial("imagens/icones/left-pointing-arrow.png", "imagens/icones/left-pointing-arrow.png", "Voltar para menu principal", "", new Color(240, 230, 140));

        jpVoltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Config.contador = 0;
                jPanel2.setBackground(new Color(240, 240, 240));
                carregarItensDoMenu();
            }
        });

        jPanel2.add(jpCadastroDeCurso);
        jPanel2.add(jpCursos);
        jPanel2.add(jpTurmas);
        jPanel2.add(jpAlunosFuncionarios);
        jPanel2.add(jpVoltar);

        jPanel2.repaint();
        jPanel2.revalidate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
