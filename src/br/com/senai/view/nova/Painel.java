/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view.nova;

import br.com.senai.util.Config;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Hugo
 */
public class Painel extends javax.swing.JPanel {

    /**
     * Creates new form Painel
     */
    private JPanel jPanel;
    private boolean comRetorno;
    private boolean atualizar;
    private String titulo;
    private HashMap<String, Object> itensMenu;
    private Component[] componentesMenu;

    public Painel(JPanel jPanel, String titulo, HashMap<String, Object> itensMenu, boolean comRetorno, boolean atualizar) {
        this.comRetorno = comRetorno;
        this.titulo = titulo;
        this.itensMenu = itensMenu;
        this.jPanel = jPanel;
        this.atualizar = atualizar;
        iniciarComponentes();
        createComponentMap();
    }

//    public Painel(JPanel jPanel, String titulo, Component[] componentesMenu, boolean comRetorno) {
//        this.comRetorno = comRetorno;
//        this.titulo = titulo;
//        this.itensMenu = new HashMap<>();
//        this.componentesMenu = componentesMenu;
//        this.jPanel = jPanel;
//        iniciarComponentes();
//    }
    private void iniciarComponentes() {
        initComponents();
        carregarMenu(itensMenu);
        jLabel1.setIcon(new ImageIcon(new ImageIcon("imagens/icones/right164.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        jLabel2.setText(titulo);

        jLabel1.setVisible(comRetorno);

        if (!jLabel1.isVisible()) {
            jLabel2.setBorder(BorderFactory.createEmptyBorder(0, 160, 0, 0));
            jLabel2.repaint();
            jLabel2.revalidate();
        }
    }

//    private void carregarComponentesMenu(Component[] componentesMenu) {
//        jPanel2.removeAll();
//        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.PAGE_AXIS));
//
//        for (Component item : componentesMenu) {
//
//            JLabelMenu jLabelMenu = (JLabelMenu) item;
//
//            if (!jLabelMenu.isAtivarEventoSaida()) {
//                jLabelMenu.noClique();
//            }
//            jPanel2.add(jLabelMenu);
//        }
//
//        jPanel2.repaint();
//        jPanel2.revalidate();
//    }
    private void carregarMenu(HashMap<String, Object> itensMenu) {
        jpMenu.removeAll();
        jpMenu.setLayout(new BoxLayout(jpMenu, BoxLayout.PAGE_AXIS));

        for (Map.Entry<String, Object> entry : itensMenu.entrySet()) {
            if (entry.getValue() instanceof Class) {
                jpMenu.add(new JLabelMenu(jpMenu, entry.getKey(), jpConteudo, (Class) entry.getValue(), null));
            } else if (entry.getValue() instanceof MouseListener) {
                jpMenu.add(new JLabelMenu(entry.getKey(), (MouseListener) entry.getValue()));
            }else{
                System.out.println("fudeu muito");
            }
        }
        jpMenu.repaint();
        jpMenu.revalidate();

        if (JLabelMenu.itemSelecionado.isEmpty()) {
            ((JLabelMenu) jpMenu.getComponents()[0]).noClique();
        } else {
            for (Component component : jpMenu.getComponents()) {
                if (((JLabelMenu) component).getText().equals(JLabelMenu.itemSelecionado)) {
                    ((JLabelMenu) component).noClique();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpCabecalho = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jpMenu = new javax.swing.JPanel();
        jpConteudo = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(756, 369));
        addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                formAncestorResized(evt);
            }
        });

        jpCabecalho.setBackground(new java.awt.Color(224, 224, 224));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 0, 51));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel2.setText("TÍTULO ");

        javax.swing.GroupLayout jpCabecalhoLayout = new javax.swing.GroupLayout(jpCabecalho);
        jpCabecalho.setLayout(jpCabecalhoLayout);
        jpCabecalhoLayout.setHorizontalGroup(
            jpCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCabecalhoLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(535, Short.MAX_VALUE))
        );
        jpCabecalhoLayout.setVerticalGroup(
            jpCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jpMenuLayout = new javax.swing.GroupLayout(jpMenu);
        jpMenu.setLayout(jpMenuLayout);
        jpMenuLayout.setHorizontalGroup(
            jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        jpMenuLayout.setVerticalGroup(
            jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );

        jpConteudo.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpConteudoLayout = new javax.swing.GroupLayout(jpConteudo);
        jpConteudo.setLayout(jpConteudoLayout);
        jpConteudoLayout.setHorizontalGroup(
            jpConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpConteudoLayout.setVerticalGroup(
            jpConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jpCabecalho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpCabecalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Config.adicionarPainel(Config.PAGINA_INICIAL, "Início (" + Config.usuario.getTipoUsuario().getDescricao() + ")", jPanel, new Inicio(jPanel));
        JLabelMenu.getPaineis().clear();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        jLabel1.setBackground(new Color(208, 208, 208));
        jLabel1.setOpaque(true);
        jLabel1.repaint();
        jLabel1.revalidate();
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        jLabel1.setBackground(new Color(224, 224, 224));
        jLabel1.setOpaque(false);
        jLabel1.repaint();
        jLabel1.revalidate();
    }//GEN-LAST:event_jLabel1MouseExited
    private Component componente;
    Container container;
    private HashMap<String, Component> componentMap;

    private void createComponentMap() {
        componentMap = new HashMap<>();
        Component[] components = Config.PAGINA_INICIAL.getContentPane().getComponents();
        for (int i = 0; i < components.length; i++) {
            componentMap.put(components[i].getName(), components[i]);
        }
    }

    public Component getComponentByName(String name) {
        if (componentMap.containsKey(name)) {
            return (Component) componentMap.get(name);
        } else {
            return null;
        }
    }


    private void formAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formAncestorResized
        Config.adicionarPainel(jPanel, new Painel(jPanel, titulo, itensMenu, comRetorno, true));
//        JFrame paginaInicial = (JFrame) this.getParent().getParent().getParent().getParent().getParent();
//        Config.adicionarPainel(jPanel, (JPanel) componente);
    }//GEN-LAST:event_formAncestorResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jpCabecalho;
    private javax.swing.JPanel jpConteudo;
    private javax.swing.JPanel jpMenu;
    // End of variables declaration//GEN-END:variables
}
