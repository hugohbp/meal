/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view.nova;

import br.com.senai.dao.CursoDAO;
import br.com.senai.dao.MovimentacaoDAO;
import br.com.senai.entities.Aluno;
import br.com.senai.entities.Curso;
import br.com.senai.entities.Movimentacao;
import br.com.senai.estatistica.GerarRelatorio;
import br.com.senai.util.Config;
import br.com.senai.util.DadosRelatorio;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author Hugo
 */
public class RelatoriosFuncionarios extends javax.swing.JPanel {

    List<Aluno> alunosManha = new ArrayList<>();
    List<Aluno> alunosTarde = new ArrayList<>();
    List<Aluno> alunosNoite = new ArrayList<>();
    int quantidadeManha;
    int quantidadeTarde;
    int quantidadeNoite;

    public RelatoriosFuncionarios() {
        initComponents();
        Config.configurarPainel(jPanel1);
        DadosRelatorio dadosRelatorio = (DadosRelatorio) Config.objetoTemporario;

        quantidadeManha = 0;
        quantidadeTarde = 0;
        quantidadeNoite = 0;
        Curso curso = new CursoDAO().getCurso("Funcionários");

        Days d = Days.daysBetween(new DateTime(dadosRelatorio.getInicio()), new DateTime(dadosRelatorio.getTermino()));

        int days = d.getDays();

        jPanel1.removeAll();
        jPanel1.setLayout(new GridLayout(0, 1));
        String corpo = "";

        HashMap mapa = new HashMap();

        mapa.put("DATAEMISSAO", Config.formatarDataResumo(new Date()));
        mapa.put("PERIODO", Config.formatarDataResumo(dadosRelatorio.getInicio()) + " a " + Config.formatarDataResumo(dadosRelatorio.getTermino()));
        mapa.put("ESCOLA", (dadosRelatorio.getEscola().getNome()));

        for (int i = 0; i <= days; i++) {
            alunosManha.clear();
            alunosTarde.clear();
            alunosNoite.clear();
            corpo += "<br>__" + Config.formatarDataResumo(new DateTime(dadosRelatorio.getInicio()).plusDays(i).toDate()) + "________________________________________<br><br>";

            List<Movimentacao> resultados = new MovimentacaoDAO().funcionarios(dadosRelatorio.getEscola(), curso, new DateTime(dadosRelatorio.getInicio()).plusDays(i).toDate());

            for (Movimentacao movimentacao : resultados) {
                switch (new Config().turnoAtual(movimentacao.getHorarioMovimentacao())) {
                    case "Manhã":
                        alunosManha.add((movimentacao.getAluno()));
                        break;
                    case "Tarde":
                        alunosTarde.add((movimentacao.getAluno()));
                        break;
                    case "Noite":
                        alunosNoite.add((movimentacao.getAluno()));
                        break;
                }
            }

            quantidadeManha += alunosManha.size();
            quantidadeTarde += alunosTarde.size();
            quantidadeNoite += alunosNoite.size();

            if (!alunosManha.isEmpty()) {
                corpo += "<font size= \"4\">Turno: <b>Manhã</b> | Quantidade: <b>" + alunosManha.size() + "</b> retirada (s)</font><br><br><i>Funcionários:</i> <br><br>";
                for (Aluno aluno : alunosManha) {
                    corpo += aluno.getNome() + "<br>";
                }
                corpo += "............................................................<br><br>";
            }

            if (!alunosTarde.isEmpty()) {
                corpo += "<font size= \"4\">Turno: <b>Tarde</b> | Quantidade: <b>" + alunosTarde.size() + "</b> retirada (s)</font><br><br><i>Funcionários:</i> <br><br>";
                for (Aluno aluno : alunosTarde) {
                    corpo += aluno.getNome() + "<br>";
                }

                corpo += "_________________________________________________________________________________________________________________________________<br><br>";

            }
            if (!alunosNoite.isEmpty()) {
                corpo += "<font size= \"4\">Turno: <b>Noite</b> | Quantidade: <b>" + alunosNoite.size() + "</b> retirada (s)</font><br><br><i>Funcionários:</i> <br><br>";
                for (Aluno aluno : alunosNoite) {
                    corpo += aluno.getNome() + "<br>";
                }
                corpo += "_________________________________________________________________________________________________________________________________<br><br>";
            }
            corpo += "<i>RESUMO DIÁRIO</i> (" + Config.formatarDataResumo(new DateTime(dadosRelatorio.getInicio()).plusDays(i).toDate()) + ")<br><br>";
            corpo += "MANHÃ: " + alunosManha.size() + " retirada (s)<br>";
            corpo += "TARDE: " + alunosTarde.size() + " retirada (s)<br>";
            corpo += "NOITE: " + alunosNoite.size() + " retirada (s)<br><br>";
            corpo += "<b>TOTAL: " + (alunosManha.size() + alunosTarde.size() + alunosNoite.size()) + " retirada (s)</b><br>";
        }

        corpo += "-----------------------------------------------------------------------";
        corpo += "<br><br><i>RESUMO GERAL</i><br><br>";
        corpo += "MANHÃ: " + quantidadeManha + " retirada (s)<br>";
        corpo += "TARDE: " + quantidadeTarde + " retirada (s)<br>";
        corpo += "NOITE: " + quantidadeNoite + " retirada (s)<br><br>";
        corpo += "<b>TOTAL: " + (quantidadeManha + quantidadeTarde + quantidadeNoite) + " retirada (s)</b><br>";

        mapa.put("CORPO", corpo);

        String arquivo = "RelatorioFuncionarios.jasper";

        JRViewer relatorio = null;

        try {

            JasperPrint jp = JasperFillManager.fillReport(arquivo, mapa);

            relatorio = new JRViewer(jp);
            relatorio.setZoomRatio(0.5f);
            relatorio.setBackground(Color.WHITE);

            relatorio.setSize(jPanel1.getSize());
            relatorio.setVisible(true);
            relatorio.setBackground(Color.WHITE);

            jPanel1.add(relatorio);

            jPanel1.revalidate();
            jPanel1.repaint();

        } catch (JRException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 679, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents
    HashMap<String, Object> menu = new HashMap<>();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
