/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view.nova;

import br.com.senai.util.Config;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Hugo
 */
public class JLabelMenu extends JLabel {

    private boolean ativarEventoSaida;
    private JPanel pai;
    private Class classe;
    private String tituloPrincipal;
    private String mensagem = "";
    public static String itemSelecionado = "";
    private Object object;
    public static List<JPanel> paineis = new ArrayList<>();
    private JPanel painelAtivo = new JPanel();

    public static List<JPanel> getPaineis() {
        return paineis;
    }

    public static void setPaineis(List<JPanel> paineis) {
        JLabelMenu.paineis = paineis;
    }

    public boolean isAtivarEventoSaida() {
        return ativarEventoSaida;
    }

    public void setAtivarEventoSaida(boolean ativarEventoSaida) {
        this.ativarEventoSaida = ativarEventoSaida;
    }

//    private void limparMenu(Component[] componentes) {
//
//        for (Component component : componentes) {
//            if (component instanceof JLabelMenu) {
//                JLabelMenu jLabel1 = (JLabelMenu) component;
//                System.out.println(jLabel1.getText());
//                if (jLabel1.getText().equals(this.getText())) {
//                    jLabel1.ativarEventoSaida = false;
//                    jLabel1.setBackground(new Color(208, 208, 208));
//                    jLabel1.setOpaque(true);
//                    jLabel1.repaint();
//                    jLabel1.revalidate();
//                } else {
//                    jLabel1.ativarEventoSaida = true;
//                    jLabel1.setBackground(new Color(224, 224, 224));
//                    jLabel1.setOpaque(false);
//                    jLabel1.repaint();
//                    jLabel1.revalidate();
//                }
//            }
//
//        }
//    }
    public void noClique() {
        this.ativarEventoSaida = false;
        this.setBackground(new Color(240, 230, 140));
        this.repaint();
        this.revalidate();
        pai.setLayout(new GridLayout(0, 1));
        adicionarPainel();
    }

    private void adicionarPainel() {
        try {
            painelAtivo = (JPanel) classe.newInstance();

            if (paineis.isEmpty()) {
                paineis.add(painelAtivo);
                Config.adicionarPainel(pai, painelAtivo);
            } else {
                Config.adicionarPainel(pai, paineis.get(0));
            }
        } catch (InstantiationException | IllegalAccessException | NullPointerException ex) {
        }
    }

    public JLabelMenu(String titulo, final MouseListener l) {
        ativarEventoSaida = true;
        setOpaque(true);

        String[] titulos = titulo.split("--");
        int i = 0;
        for (String titulo1 : titulos) {
            if (i == 0) {
                this.tituloPrincipal = titulo1;
            }
            if (i == 1) {
                this.mensagem = titulo1;
            }
            i++;
        }
        
        setFont(new Font("Tahoma", 0, 16));
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));
        setText(tituloPrincipal);
        setPreferredSize(new Dimension(150, 0));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getMinimumSize().height));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                if (ativarEventoSaida) {
                    setBackground(new Color(208, 208, 208));
                } else {
                    setBackground(new Color(255, 215, 0));
                }
                setOpaque(true);
                repaint();
                revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (ativarEventoSaida) {
                    setBackground(new Color(240, 240, 240));
                } else {
                    setBackground(new Color(240, 230, 140));
                }
                repaint();
                revalidate();
            }
        });
        addMouseListener(l);
    }

    public JLabelMenu(final JPanel jPanel, String titulo, final JPanel pai, final Class classe, final MouseListener l) {
        ativarEventoSaida = true;
        this.pai = pai;
        this.classe = classe;
        setOpaque(true);

        String[] titulos = titulo.split("--");
        int i = 0;
        for (String titulo1 : titulos) {
            if (i == 0) {
                this.tituloPrincipal = titulo1;
            }
            if (i == 1) {
                this.mensagem = titulo1;
            }
            i++;
        }

        class Evento {

            public void eventoDeClique() {
                for (Component component : jPanel.getComponents()) {
                    if (component instanceof JLabelMenu) {
                        JLabelMenu jLabel1 = (JLabelMenu) component;
                        if (jLabel1.getText().equals(tituloPrincipal)) {
                            jLabel1.ativarEventoSaida = false;
                            itemSelecionado = jLabel1.getText();
                            jLabel1.setBackground(new Color(240, 230, 140));
                        } else {
                            jLabel1.ativarEventoSaida = true;
                            paineis.clear();
                            System.gc();
                            jLabel1.setBackground(new Color(240, 240, 240));
                        }

                        jLabel1.repaint();
                        jLabel1.revalidate();
                    }
                }

                pai.setLayout(new GridLayout(0, 1));
                adicionarPainel();
            }
        }
        setFont(new Font("Tahoma", 0, 16));
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));
        setText(tituloPrincipal);
        setPreferredSize(new Dimension(150, 0));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getMinimumSize().height));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                if (ativarEventoSaida) {
                    setBackground(new Color(208, 208, 208));
                } else {
                    setBackground(new Color(255, 215, 0));
                }
                setOpaque(true);
                repaint();
                revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (ativarEventoSaida) {
                    setBackground(new Color(240, 240, 240));
                } else {
                    setBackground(new Color(240, 230, 140));
                }
                repaint();
                revalidate();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                //inicialmente somente para remover objetos
                if (!mensagem.isEmpty()) {
                    if (Config.mensagemDeConfirmacaoExclusao(mensagem) == JOptionPane.YES_OPTION) {
                        ((JDialog) getParent().getParent().getParent().getParent().getParent().getParent().getParent()).dispose();
                    }
                } else {
                    new Evento().eventoDeClique();
                }
            }
        });
    }

    public JLabelMenu(final String titulo, final HashMap<String, Object> menu, Object objeto) {
        this.object = objeto;
        setText(titulo);
        setFont(new Font("Tahoma", 0, 16));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabelMenu.itemSelecionado = "";
                paineis.clear();
                Config.objetoTemporario = object;
                new PainelJDialog(titulo, menu).setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(208, 208, 208));
                setOpaque(true);
                repaint();
                revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(240, 240, 240));
                setOpaque(false);
                repaint();
                revalidate();
            }

        });

    }
}
