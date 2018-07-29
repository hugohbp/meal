/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view;

import br.com.senai.util.Config;
import br.com.senai.estatistica.GerarGrafico;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author Simulado
 */
public class DadosDefasagem extends javax.swing.JPanel {

    /**
     * Creates new form DadosDefasagem
     */
   

    public DadosDefasagem() {
       
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMonthChooser2 = new com.toedter.calendar.JMonthChooser();
        jLabel3 = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jButton1 = new javax.swing.JButton();
        fundoGrafico = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(770, 570));

        jLabel1.setText("Início:");

        jLabel2.setText("Término:");

        jLabel3.setText("Ano:");

        jButton1.setText("Procurar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fundoGraficoLayout = new javax.swing.GroupLayout(fundoGrafico);
        fundoGrafico.setLayout(fundoGraficoLayout);
        fundoGraficoLayout.setHorizontalGroup(
            fundoGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        fundoGraficoLayout.setVerticalGroup(
            fundoGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fundoGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMonthChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 94, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jMonthChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(fundoGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

//        for (Object[] o : new MovimentacaoDAO().totalMovimentacoes(Config.usuario, jMonthChooser1.getMonth() + 1, jYearChooser1.getYear())) {
//            System.out.println("");
//            System.out.println(" Escola: " + o[0] + "   Alunos : " + o[1]+ " Dias ativos para o lanche: "+o[2]+" Max possibilities: "+o[3]);
//            System.out.println("----------------------------------------");
//        }
//        for (Object[] o : new MovimentacaoDAO().totalMovimentacoesRealizadas(Config.usuario, jMonthChooser1.getMonth() + 1, jYearChooser1.getYear())) {
//            System.out.println("MOVIMENTAÇÕES:");
//            System.out.println(" Escola: " + o[0] + "   Total de movimentações : " + o[1]);
//        }
        ChartPanel painel = new ChartPanel(new GerarGrafico().graficoDefasagem(Estatisticas.escolaGrafico, jMonthChooser1.getMonth() + 1, jMonthChooser2.getMonth()+1, jYearChooser1.getYear()));

        fundoGrafico.removeAll();
        painel.setSize(fundoGrafico.getSize());
        painel.setVisible(true);

        fundoGrafico.removeAll();
        fundoGrafico.setBackground(Config.COR_FUNDO);
        fundoGrafico.add(painel);

        fundoGrafico.repaint();
        fundoGrafico.setVisible(true);


    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel fundoGrafico;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private com.toedter.calendar.JMonthChooser jMonthChooser2;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables
}
