/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.view;

import br.com.senai.dao.AlunoDAO;
import br.com.senai.dao.CursoDAO;
import br.com.senai.util.Config;
import br.com.senai.dao.Curso_e_EscolaDAO;
import br.com.senai.dao.EscolaDAO;
import br.com.senai.dao.TipoDAO;
import br.com.senai.dao.TurmaDAO;
import br.com.senai.dao.TurnoDAO;
import br.com.senai.entities.Aluno;
import br.com.senai.entities.Curso;
import br.com.senai.entities.Curso_e_Escola;
import br.com.senai.entities.Escola;
import br.com.senai.entities.Tipo;
import br.com.senai.entities.Turma;
import static br.com.senai.view.PainelAluno.atualizarAlunos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.hibernate.HibernateException;

/**
 *
 * @author Simulado
 */
public class ImportarTurma extends javax.swing.JFrame {

    /**
     * Creates new form ImportaTurma
     */
    public ImportarTurma() {
        initComponents();
        Config.configurarJanela(this);
        Config.fillComboBox(cbEscola, new EscolaDAO().escolasPermitidas(Config.usuario));
//        Config.fillComboBox(cbTurno, new TurnoDAO().listar());

//        cbCurso.setEnabled(false);
//        jButton2.setEnabled(Config.DEBUG);
        nomeArquivo.setVisible(false);

        if (Config.DEBUG) {
//            tfNomeTurma.setText("AIPJ10T");
            arquivoSelecionado = new File("C:\\Users\\Hugo\\Desktop\\Planilha Poliana Lanche.CSV");
        }
    }

    int nNomeAluno = 0;
    int nCurso = 0;
    int nModalidade = 0;
    int nTurma = 0;
    int nTermino = 0;
    int nRA = 0;
    int nPrograma = 0;
    int nRE = 0;
    int nNomeFuncionario = 0;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        arquivo = new javax.swing.JFileChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nomeArquivo = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbEscola = new javax.swing.JComboBox();

        arquivo.setFileFilter(new FileNameExtensionFilter("Arquivos (*.txt, *.csv)", "txt", "csv"));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Importar funcionários");
        jLabel4.setToolTipText("");

        jLabel1.setText("1. Selecione um arquivo:");

        jButton1.setText("Escolha um arquivo...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("2.Selecione a escola:");

        nomeArquivo.setText("arquivo selecionado");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("aluno");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("funcionário");

        jLabel10.setText("Tipo de usuário:");

        jButton2.setText("Importar alunos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setText("Escola:");

        cbEscola.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEscolaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(199, 199, 199))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nomeArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel10))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButton1)
                                        .addGap(62, 62, 62)
                                        .addComponent(jRadioButton2)))
                                .addGap(125, 125, 125))
                            .addComponent(jLabel5)
                            .addComponent(cbEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(100, 100, 100)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1)
                                    .addComponent(nomeArquivo))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
 File arquivoSelecionado;
    boolean ativo = false;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (arquivo.showDialog(this, "Selecionar arquivo...") == JFileChooser.APPROVE_OPTION) {
            arquivoSelecionado = arquivo.getSelectedFile();

            if (arquivoSelecionado != null) {

                if (Config.DEBUG) {
                    System.out.println("Caminho do arquivo selecionado: " + arquivoSelecionado.getAbsolutePath());
                }

                try {

                    BufferedReader buffer = new BufferedReader(new FileReader(arquivoSelecionado));

                    String line;

                    int i = 1;
                    int x = 1;

                    while ((line = buffer.readLine()) != null) {

                        line = line.trim();

                        ativo = line.matches("[0-9]+,(\\w+)");

                        if (ativo) {
                            break;

                        }
                    }

                } catch (IOException ex) {

                }

                verificarArquivo();

                nomeArquivo.setText(arquivoSelecionado.getName());
                nomeArquivo.setVisible(true);
            }

        } else {
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbEscolaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEscolaItemStateChanged
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            if (!cbEscola.getSelectedItem().toString().equalsIgnoreCase("Selecione...")) {
//
//                Config.fillComboBox(cbCurso, new Curso_e_EscolaDAO().cursosDaEscola((Escola) cbEscola.getSelectedItem()));
//                cbCurso.setEnabled(true);
//
//            } else {
//                cbCurso.setEnabled(false);
//            }
//
//        }
    }//GEN-LAST:event_cbEscolaItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed
    Turma t = new Turma();
    String[][] matriz;
    int linhasMatriz;
    int colunasMatriz;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (jRadioButton2.isSelected()) {

            if ((arquivoSelecionado != null) && (cbEscola.getSelectedItem() instanceof Escola)) {

                if (JOptionPane.showConfirmDialog(null, "Deseja importar os funcionários?", "Confirmaçao", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                    List<String> linhas = new ArrayList<>();

                    try {
                        linhas = Files.readAllLines(Paths.get(arquivoSelecionado.getAbsolutePath()), StandardCharsets.UTF_8);
                    } catch (IOException ex) {
                        try {
                            linhas = Files.readAllLines(Paths.get(arquivoSelecionado.getAbsolutePath()), StandardCharsets.ISO_8859_1);
                        } catch (IOException ex1) {
                            Logger.getLogger(ImportarTurma.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }

                    int i = 0;
                    String[] colunas;

                    linhasMatriz = linhas.size();
                    colunasMatriz = linhas.get(0).split(";").length;

                    System.out.println(linhasMatriz);
                    System.out.println(colunasMatriz);
                    matriz = new String[linhasMatriz][colunasMatriz];

                    for (String linha : linhas) {
                        linha = linha.trim();
                        colunas = linha.split(";");
                        int j = 0;
                        for (String coluna : colunas) {
                            coluna = coluna.trim();
                            if (coluna == null || coluna.equalsIgnoreCase("")) {
                                coluna = "NULL";
                            }
                            matriz[i][j] = coluna;
                            j++;
                        }
                        i++;
                    }
                }
                if (Config.DEBUG) {
                    for (int k = 0; k < linhasMatriz; k++) {
                        for (int l = 0; l < colunasMatriz; l++) {

                            System.out.print(matriz[k][l] + "----------");
                        }
                        System.out.println("    ");
                    }
                }
                for (int k = 0; k < 1; k++) {
                    for (int l = 0; l < colunasMatriz; l++) {

                        if (matriz[k][l].equalsIgnoreCase("RE")) {
                            nRE = l;
                        }
                        if (matriz[k][l].equalsIgnoreCase("Nome")) {
                            nNomeFuncionario = l;
                        }
                    }
                }

                boolean erroArquivo = false;

                for (int i = 1; i < linhasMatriz; i++) {
                    try {
                        Long re = new Long(matriz[i][nRE]);
                        String nome = matriz[i][nNomeFuncionario];
                        if (nome == null || nome.isEmpty()) {
                            erroArquivo = true;
                            break;
                        }
                    } catch (NumberFormatException ex) {
                        erroArquivo = true;
                        break;
                    }

                }

                if (erroArquivo) {
                    JOptionPane.showMessageDialog(null, "ERRO AO IMPORTAR ARQUIVO.");
                } else {

                    Curso curso = new CursoDAO().getCurso("Funcionários");

                    if (curso == null) {
                        curso = new Curso();
                        curso.setNome("Funcionários");
                        new CursoDAO().salvar(curso);
                    }
                    Curso_e_Escola c = new Curso_e_EscolaDAO().getCurso_e_Escola(curso, (Escola) cbEscola.getSelectedItem());

                    if (c == null) {
                        c = new Curso_e_Escola();
                        c.getPk().setCurso(curso);
                        c.getPk().setEscola((Escola) cbEscola.getSelectedItem());

                        Tipo tipo = new TipoDAO().getTipo("Funcionário");
                        if (tipo == null) {
                            tipo = new Tipo();
                            tipo.setNome("Funcionário");
                            new TipoDAO().salvar(tipo);
                        }
                        c.setTipo(tipo);
                        c.setAtivo(true);
                        new Curso_e_EscolaDAO().salvar(c);
                    }

                    try {
                        t = new TurmaDAO().pesquisarPorCurso(c).get(0);
                    } catch (IndexOutOfBoundsException ex) {
                        t = null;
                    }
                    if (t == null) {
                        t = new Turma();
                        try {
                            t.setNome("FUNC" + ((Escola) cbEscola.getSelectedItem()).getNome().substring(0, 5).toUpperCase());
                        } catch (StringIndexOutOfBoundsException ex) {
                            t.setNome("FUNC");
                        }
                        t.setTurno(new TurnoDAO().getTurno("Integral"));
                        t.setAtiva(true);
                        t.setCurso(c);
                        new TurmaDAO().salvar(t);
                    }

                    for (int i = 1; i < linhasMatriz; i++) {

                        if (Config.DEBUG) {
                            System.out.println(matriz[i][nNomeFuncionario]);
                            System.out.println("  ");
                        }

                        if (new AlunoDAO().alunosComID(new Long(matriz[i][nRE])).isEmpty()) {
                            Aluno aluno = new Aluno();
                            aluno.setAluno(new Long(matriz[i][nRE]));
                            aluno.setAtivo(true);
                            aluno.setNome(matriz[i][nNomeFuncionario]);
                            aluno.setTurma(t);
                            new AlunoDAO().salvar(aluno);
                        }
                    }
                    if (new AlunoDAO().getAlunos(t).isEmpty()) {
                        new TurmaDAO().deletar(t);
                        new Curso_e_EscolaDAO().deletar(c);
                    } else {
                        Config.mensagemSalvo();
                    }
                }
            } else {
                Config.mensagemDeAviso("Erro ao importar os usuários");
            }

        } else {
            JOptionPane.showMessageDialog(null, "FUNÇÃO NÃO IMPLEMENTADA.", "AVISO", JOptionPane.INFORMATION_MESSAGE);

//            if (JOptionPane.showConfirmDialog(null, "Deseja importar os alunos?", "Confirmaçao", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//
//                String nomeTurma = tfNomeTurma.getText();
//                t = new TurmaDAO().getNome(nomeTurma);
//
//                if (t == null) {
//                    t = new Turma();
//                    t.setNome(nomeTurma);
//                    t.setTurno((Turno) cbTurno.getSelectedItem());
//                    t.setAtiva(true);
//                    t.setCurso(new Curso_e_EscolaDAO().getCurso_e_Escola((Curso) cbCurso.getSelectedItem(), (Escola) cbEscola.getSelectedItem()));
////                new TurmaDAO().salvar(t);
//                    System.out.println("salvou");
//                }
//
//                List<String> linhas = new ArrayList<>();
//
//                try {
//                    linhas = Files.readAllLines(Paths.get(arquivoSelecionado.getAbsolutePath()), StandardCharsets.UTF_8);
//                } catch (IOException ex) {
//                    try {
//                        linhas = Files.readAllLines(Paths.get(arquivoSelecionado.getAbsolutePath()), StandardCharsets.ISO_8859_1);
//                    } catch (IOException ex1) {
//                        Logger.getLogger(ImportarTurma.class.getName()).log(Level.SEVERE, null, ex1);
//                    }
//                }
//
//                int i = 0;
//                String[] colunas;
//
//                linhasMatriz = linhas.size();
//                colunasMatriz = linhas.get(0).split(";").length;
//
//                System.out.println(linhasMatriz);
//                System.out.println(colunasMatriz);
////
//                matriz = new String[linhasMatriz][colunasMatriz];
//                for (String linha : linhas) {
//
////                if (i != 0) {
////                    linha = linha.trim();
////                    String[] colunas = linha.split(";");
////                    Long id = Long.parseLong(colunas[2]);
////
////                    if (new AlunoDAO().alunosComID(id).isEmpty()) {
////                        salvarAlunos(t, id);
////                    }
//                    linha = linha.trim();
//                    colunas = linha.split(";");
//
//                    int j = 0;
//                    for (String coluna : colunas) {
//                        coluna = coluna.trim();
//
//                        if (coluna == null || coluna.equalsIgnoreCase("")) {
//                            coluna = "NULL";
//                        }
//
//                        matriz[i][j] = coluna;
//                        j++;
//                    }
//                    i++;
//                }
//            }
////        for (int k = 0; k < linhasMatriz; k++) {
////            for (int l = 0; l < colunasMatriz; l++) {
////
////                System.out.print(matriz[k][l] + "----------");
////            }
////            System.out.println("    ");
////        }
//
//            for (int k = 0; k < 1; k++) {
//                for (int l = 0; l < colunasMatriz; l++) {
//
//                    if (matriz[k][l].equalsIgnoreCase("CURSO")) {
//                        System.out.println("coluna do curso: " + l);
//
//                        nCurso = l;
//                        String curso = "NULL";
//
//                        for (int i = 1; i < linhasMatriz; i++) {
//                            if (!curso.equalsIgnoreCase(matriz[i][l])) {
//                                curso = matriz[i][l];
//                                if (new CursoDAO().getCurso(curso) == null) {
//                                    new CursoDAO().salvar(new Curso(curso));
//                                }
//                                System.out.println("Nome do curso: " + curso);
//                            }
//                        }
//
//                    }
//
//                    if (matriz[k][l].equalsIgnoreCase("TURMA")) {
//                        nTurma = l;
//                    }
//                    if (matriz[k][l].equalsIgnoreCase("PROGRAMA")) {
//                        nPrograma = l;
//                    }
//                    if (matriz[k][l].equalsIgnoreCase("TERMINO")) {
//                        nTermino = l;
//                    }
//                    if (matriz[k][l].equalsIgnoreCase("ALUNO")) {
//                        nNomeAluno = l;
//                    }
//                    if (matriz[k][l].equalsIgnoreCase("RA")) {
//                        nRA = l;
//                    }
//                    if (matriz[k][l].equalsIgnoreCase("MODALIDADE")) {
//                        nModalidade = l;
//                        System.out.println("coluna da modalidade: " + l);
//                        String modalidade = "NULL";
//                        for (int i = 1; i < linhasMatriz; i++) {
//                            if (!modalidade.equalsIgnoreCase(matriz[i][l])) {
//                                modalidade = matriz[i][l];
//                                if (new TipoDAO().getTipo(modalidade) == null) {
//                                    new TipoDAO().salvar(new Tipo(modalidade));
//                                }
//                                System.out.println("nome da modalidade: " + modalidade);
//                            }
//                        }
//                    }
//                }
//
//                for (int i = 1; i < linhasMatriz; i++) {
//
//                    if (Config.DEBUG) {
////                    System.out.println(matriz[i][nCurso]);
////                    System.out.println("  ");
//                    }
//
//                    if (new Curso_e_EscolaDAO().getCurso_e_Escola(new CursoDAO().getCurso(matriz[i][nCurso]), (Escola) cbEscola.getSelectedItem()) == null) {
//
//                        Curso_e_Escola curso_e_Escola = new Curso_e_Escola();
//                        curso_e_Escola.setAtivo(true);
//                        curso_e_Escola.setTipo(new TipoDAO().getTipo(matriz[i][nModalidade]));
//                        curso_e_Escola.getPk().setCurso(new CursoDAO().getCurso(matriz[i][nCurso]));
//                        curso_e_Escola.getPk().setEscola((Escola) cbEscola.getSelectedItem());
//                        new Curso_e_EscolaDAO().salvar(curso_e_Escola);
//                    }
//                }
//
//                for (int i = 1; i < linhasMatriz; i++) {
//
//                    String nomeTurma = matriz[i][nTurma].substring(0, matriz[i][nTurma].length() - 2);
//                    if (new TurmaDAO().getNome(nomeTurma) == null) {
//                        Turma turma = new Turma();
//                        turma.setNome(nomeTurma);
//                        turma.setAtiva(true);
//                        turma.setInicio(new Date());
//                        try {
//                            turma.setTermino(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(matriz[i][nTermino]));
//                        } catch (ParseException ex) {
//                            Logger.getLogger(ImportarTurma.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//
//                        String turnoDaTurma = String.valueOf(matriz[i][nTurma].charAt(matriz[i][nTurma].length() - 3));
//                        String retorno = "";
//
//                        switch (turnoDaTurma) {
//                            case "M":
//                                retorno = "Manhã";
//                                break;
//                            case "T":
//                                retorno = "Tarde";
//                                break;
//                            case "N":
//                                retorno = "Noite";
//                                break;
//                            default:
//                                throw new AssertionError();
//                        }
//
//                        turma.setTurno(new TurnoDAO().getTurno(retorno));
//                        turma.setCurso(new Curso_e_EscolaDAO().getCurso_e_Escola(new CursoDAO().getCurso(matriz[i][nCurso]), (Escola) cbEscola.getSelectedItem()));
//
//                        new TurmaDAO().salvar(turma);
//
//                    }
//                }
//
//                for (int i = 1; i < linhasMatriz; i++) {
//                    if (new AlunoDAO().alunosComID(new Long(matriz[i][nRA])).isEmpty()) {
//                        Aluno aluno = new Aluno();
//                        aluno.setAluno(new Long(matriz[i][nRA]));
//                        aluno.setAtivo(true);
//                        aluno.setNome(matriz[i][nNomeAluno]);
//                        aluno.setPrograma(matriz[i][nPrograma].equals("NULL") ? null : matriz[i][nPrograma]);
//                        aluno.setTurma(new TurmaDAO().getNome(matriz[i][nTurma].substring(0, matriz[i][nTurma].length() - 2)));
//                        new AlunoDAO().salvar(aluno);
//                    }
//                }
//
//            }
//
//
//            Config.mensagemSalvo();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public void salvarAlunos(Turma t, Long id) {
        try {

//                System.out.println(tfRaALuno.getText());
//                Turma t = (Turma) cbTurma.getSelectedItem();
//                Long id = Long.parseLong(tfRaALuno.getText());
            if (new TurmaDAO().isAtiva(t) != null) {

                if (new AlunoDAO().validarTurma(id, t.getTurno()) == null) {

                    Aluno a = new Aluno();

                    a.setAluno(id);
                    a.setTurma(t);
                    a.setAtivo(true);

                    try {
                        new AlunoDAO().salvar(a);

                        atualizarAlunos();

                        new MensagemSalvo().setVisible(true);

                    } catch (HibernateException e) {
                        atualizarAlunos();
                        new MensagemNaoSalvo().setVisible(true);

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "O aluno já está realizando um curso nesse turno.");

                }
            } else {
                JOptionPane.showMessageDialog(null, "A turma não está ativa no momento.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Dado inválido \n *somente números", "Erro", JOptionPane.ERROR_MESSAGE);

        }
    }
//
//    public void atualizarPainelEscolas() {
//        GridLayout layout = new GridLayout(0, 1, 2, 3);
//        painelEscolas.removeAll();
//
//        painelEscolas.setLayout(layout);
//
//        for (final Escola escola : new EscolaDAO().escolasPermitidas(Config.usuario)) {
//            painelEscolas.add(new JCheckBox(new AbstractAction(escola.getNome(), null) {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    System.out.println(escola.getNome());
//                }
//            }) {
//
//                @Override
//                public void setLabel(String label) {
//                    super.setLabel(escola.getNome()); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public void setName(String name) {
//                    super.setName(escola.getNome()); //To change body of generated methods, choose Tools | Templates.
//                }
//            });
//        }
//
//        painelEscolas.revalidate();
//        painelEscolas.repaint();
//
//    }
//
//    private void carregarInformacoes() {
//
//        GridLayout layout = new GridLayout(0, 1, 1, 1);
//        painelEscolas.removeAll();
//
//        painelEscolas.setLayout(layout);
//
//        for (int k = 0; k < 1; k++) {
//            for (int l = 0; l < 3; l++) {
//                painelEscolas.add(new JCheckBox((l + 1) + "-" + matriz[k][l], false));
//            }
//        }
//
////        List<String> linhas = null;
////
////        try {
////            linhas = Files.readAllLines(Paths.get("escolas.txt"), StandardCharsets.UTF_8);
////        } catch (IOException ex) {
////            try {
////                Files.write(Paths.get("escolas.txt"), "".getBytes("utf-8"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
////                linhas = Files.readAllLines(Paths.get("escolas.txt"), StandardCharsets.UTF_8);
////            } catch (UnsupportedEncodingException ex1) {
////                Logger.getLogger(Escolas.class.getName()).log(Level.SEVERE, null, ex1);
////            } catch (IOException ex1) {
////                Logger.getLogger(Escolas.class.getName()).log(Level.SEVERE, null, ex1);
////            }
////        }
////        for (String linha : linhas) {
////            String[] split = linha.split(";");
////
////            for (String palavra : split) {
////
////                for (Component component : painelEscolas.getComponents()) {
////
////                    if (component instanceof JCheckBox) {
////
////                        if (palavra.equalsIgnoreCase(((JCheckBox) component).getText())) {
////                            ((JCheckBox) component).setSelected(true);
////                        }
////
////                    }
////                }
////
////            }
////        }
//        painelEscolas.revalidate();
//        painelEscolas.repaint();
//
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser arquivo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbEscola;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel nomeArquivo;
    // End of variables declaration//GEN-END:variables

    private void verificarArquivo() {

        List<String> linhas = new ArrayList<>();

        try {
            linhas = Files.readAllLines(Paths.get(arquivoSelecionado.getAbsolutePath()), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            try {
                linhas = Files.readAllLines(Paths.get(arquivoSelecionado.getAbsolutePath()), StandardCharsets.ISO_8859_1);
            } catch (IOException ex1) {
                Logger.getLogger(ImportarTurma.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        int i = 0;
        String[] colunas = linhas.get(0).split(";");

        int j = 0;
        for (String coluna : colunas) {
            coluna = coluna.trim();
            if (coluna.equalsIgnoreCase("re") || coluna.equalsIgnoreCase("nome")) {
            } else {
                JOptionPane.showMessageDialog(null, "Arquivo inválido.");
                break;
            }
            j++;
        }
    }
}
