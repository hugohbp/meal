/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view.nova;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Hugo
 */
public class JPanelPaginaInicial extends JPanel {

    private int espessuraLinha = 1;
    private String imagemDoClique;
    private String imagemDefault;
    private JLabel icone;
    private String titulo = "";

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public JPanelPaginaInicial(String imagem, String imagem2, String titulo1, String subtitulo1, Color cor) {
        imagemDoClique = imagem2;
        imagemDefault = imagem;
        titulo = titulo1;

        setOpaque(true);
        setBackground(cor);
        setLayout(new BorderLayout(10, 10));
        Border border = getBorder();
        Border margin = BorderFactory.createLineBorder(getBackground(), espessuraLinha);
        setBorder(new CompoundBorder(border, margin));

        icone = new JLabel("", JLabel.CENTER);
        icone.setIcon(new ImageIcon(new ImageIcon(imagem).getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH)));

        JLabel titulo = new JLabel(titulo1, JLabel.CENTER);
        titulo.setFont(new Font("Default", Font.BOLD, 14));
        
        JLabel subtitulo = new JLabel(subtitulo1, JLabel.CENTER);
        subtitulo.setForeground(Color.GRAY);

        JPanel conteudo = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        conteudo.add(icone, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        conteudo.add(titulo, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        conteudo.add(subtitulo, c);
        conteudo.setBackground(cor);

        add(conteudo, BorderLayout.CENTER);

        setPreferredSize(new Dimension(50, 50));
        setSize(new Dimension(50, 50));

        repaint();
        revalidate();

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                Border margin = BorderFactory.createLineBorder(new Color(40, 40, 40), espessuraLinha);
                setBorder(new CompoundBorder(margin, null));
                icone.setIcon(new ImageIcon(new ImageIcon(imagemDoClique).getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH)));
//                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                repaint();
                revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {

                Border margin = new EmptyBorder(espessuraLinha, espessuraLinha, espessuraLinha, espessuraLinha);
                setBorder(new CompoundBorder(margin, null));
                icone.setIcon(new ImageIcon(new ImageIcon(imagemDefault).getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH)));

                repaint();
                revalidate();
            }
        });
    }

    public JPanelPaginaInicial(String imagem, String imagem2, String titulo1, String subtitulo1) {

        imagemDoClique = imagem2;
        imagemDefault = imagem;
        titulo = titulo1;

        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BorderLayout(10, 10));
        Border border = getBorder();
        Border margin = BorderFactory.createLineBorder(getBackground(), espessuraLinha);
        setBorder(new CompoundBorder(border, margin));

        icone = new JLabel("", JLabel.CENTER);
        icone.setIcon(new ImageIcon(new ImageIcon(imagem).getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH)));

        JLabel titulo = new JLabel(titulo1, JLabel.CENTER);
        titulo.setFont(new Font("Default", Font.BOLD, 14));
        JLabel subtitulo = new JLabel(subtitulo1, JLabel.CENTER);
        subtitulo.setForeground(Color.GRAY);

        JPanel conteudo = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        conteudo.add(icone, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        conteudo.add(titulo, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        conteudo.add(subtitulo, c);

        add(conteudo, BorderLayout.CENTER);

        setPreferredSize(new Dimension(50, 50));
        setSize(new Dimension(50, 50));

        repaint();
        revalidate();

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                Border margin = BorderFactory.createLineBorder(new Color(40, 40, 40), espessuraLinha);
                setBorder(new CompoundBorder(margin, null));
                icone.setIcon(new ImageIcon(new ImageIcon(imagemDoClique).getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH)));
//                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                repaint();
                revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {

                Border margin = new EmptyBorder(espessuraLinha, espessuraLinha, espessuraLinha, espessuraLinha);
                setBorder(new CompoundBorder(margin, null));
                icone.setIcon(new ImageIcon(new ImageIcon(imagemDefault).getImage().getScaledInstance(37, 37, Image.SCALE_SMOOTH)));

                repaint();
                revalidate();
            }
        });
    }

}
