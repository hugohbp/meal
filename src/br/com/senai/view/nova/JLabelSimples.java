/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view.nova;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

/**
 *
 * @author Hugo
 */
public class JLabelSimples extends JLabel {

    public JLabelSimples(String texto, MouseListener l) {
        setFont(new Font("Tahoma", 0, 14));
        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 0));
        setText(texto);
        addMouseListener(l);
    }

    public JLabelSimples(String texto, Color cor, MouseListener l) {
        setFont(new Font("Tahoma", 0, 14));
        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 0));
        Border border = getBorder();
        Border margin = BorderFactory.createLineBorder(Color.black, 2);
        setBorder(new CompoundBorder(margin, border));
        setText(texto);
        setBackground(cor);
        addMouseListener(l);
    }

}
