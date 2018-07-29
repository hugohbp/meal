/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.util;

import br.com.senai.dao.FichaDAO;
import br.com.senai.entities.Atualizacao;
import br.com.senai.entities.Usuario;
import br.com.senai.estatistica.Conexao;
import br.com.senai.view.Login;
import br.com.senai.view.OpcoesBanco;
import br.com.senai.view.nova.PaginaInicial;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Hugo
 */
public final class Config {

    public static final boolean DEBUG = false;

    public static Object objetoTemporario;

    public static PaginaInicial PAGINA_INICIAL;

    public static final String NOME_SISTEMA = " MEAL (Entrega Automatizada de Lanches)";

    public static final String NOME_SISTEMA_SIGLA = "MEAL";

    public static final String VERSAO_DO_SISTEMA = "2.8.42";

    public static final String VERSAO_DO_SISTEMA_BETA = "2.9.4";

    public static Usuario usuario = new Usuario();

    public static final ImageIcon ICONE_SISTEMA = new ImageIcon("imagens/3.png");

    public static final Image LOGO_SISTEMA = ICONE_SISTEMA.getImage();

    public static final Color COR_FUNDO = Color.WHITE;

    public static final String NOME_BANCO_DE_DADOS = "meal";

    public static final Color COR_BOTAO = new Color(255, 200, 70);

    public static final Color COR_TEXTO = Color.BLACK;

    public static String usuarioBD = "";

    public static String senhaBD = "";

    public static String urlBD = "";

    public static int contador = 0;

    public final String emailProvedor = "email";

    public final String senhaProvedor = "senha";

    public final String senhaAdministrador = "senhaAdmin";

    //CONFIGURAÇÃO DO SISTEMA ATRAVÉS DE ARQUIVO
    public static String manterLogin = "";
    public static String manterSenha = "";
    public static String filtroEscolaNaoPermitida = "false";

    ////////////////////////////////////////////////////////////////////
    static {
        carregarConexao();
    }

    public static void carregarConexao() {
        try {
            List<String> linhas = new ArrayList<>();

            try {
                linhas = Files.readAllLines(Paths.get("config.txt"), StandardCharsets.UTF_8);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            EncriptacaoAES encriptacaoAES = new EncriptacaoAES();

            usuarioBD = encriptacaoAES.desencriptar(linhas.get(0));
            urlBD = encriptacaoAES.desencriptar(linhas.get(1));
            senhaBD = encriptacaoAES.desencriptar(linhas.get(2));

            System.out.println(usuarioBD + urlBD + senhaBD);
        } catch (NullPointerException ex) {
            new OpcoesBanco().setVisible(true);
        }

    }

    public static boolean verificarConexao(String url, String usuario, String senha) {

        boolean retorno = true;
        Connection connection = Conexao.getConnection(url, usuario, senha);

        try {
            if (connection != null) {
                if (!connection.isClosed()) {
                    connection.close();
                    retorno = false;
                }
            } else {
                retorno = true;
            }
        } catch (SQLException ex) {
            retorno = true;
        }
        return retorno;

    }

    public static boolean verificarConexao() {
        boolean retorno = true;
        Connection connection = Conexao.getConnection(Config.urlBD, Config.usuarioBD, Config.senhaBD);

        try {
            if (connection != null) {
                if (!connection.isClosed()) {
                    connection.close();
                    retorno = false;
                }
            } else {
                retorno = true;
            }
        } catch (SQLException ex) {
            retorno = true;
        }
        return retorno;
    }

    public static String criptografarSenha(char[] senha) {

        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(new String(senha).getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

            JOptionPane.showMessageDialog(null, "Erro ao configurar senha!", "Erro", JOptionPane.ERROR_MESSAGE);
            return "";
        }

    }

    public static String criptografarSenha(String senha) {

        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

            JOptionPane.showMessageDialog(null, "Erro ao configurar senha!", "Erro", JOptionPane.ERROR_MESSAGE);
            return "";
        }

    }

    public static void mensagemSalvo() {
        JOptionPane.showMessageDialog(null, "A informação foi salva.", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mensagemApagado() {
        JOptionPane.showMessageDialog(null, "A informação foi apagada.", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mensagemAtualizado() {
        JOptionPane.showMessageDialog(null, "A informação foi atualizada.", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mensagemInformativa(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mensagemInformativa(String titulo, String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mensagemDeErro(String mensagem) {
        JOptionPane.showMessageDialog(null, "<html><b><font size=5>" + mensagem + "</font></b></html>", "Erro na aplicação", JOptionPane.ERROR_MESSAGE);
    }

    public static void mensagemDeAviso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    public static int mensagemDeConfirmacaoExclusao(String mensagem) {
        return JOptionPane.showConfirmDialog(null, mensagem, "Confirmar exclusão?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    }

    public static String mensagemDeEntrada(String cabecalho, String conteudo) {
        return (String) JOptionPane.showInputDialog(null, "<html><b>" + cabecalho + "</b> ", "Alterar informações ", JOptionPane.DEFAULT_OPTION, new ImageIcon("imagens/icones/181.png"), null, conteudo);
    }

    public static void fillComboBox(JComboBox combo, List lista) {

        combo.removeAllItems();

        combo.addItem(new Object() {

            @Override
            public String toString() {
                return "Selecione...";
            }

        });

        for (Object object : lista) {
            combo.addItem(object);
        }

    }

    public static void limparComboBox(JComboBox combo) {
        combo.removeAllItems();

        combo.addItem(new Object() {

            @Override
            public String toString() {
                return "Selecione...";
            }

        });
    }

    public static void fillComboBox(List<JComboBox> comboList, List lista) {

        for (JComboBox combo : comboList) {

            combo.removeAllItems();

            combo.addItem(new Object() {

                @Override
                public String toString() {
                    return "Selecione...";
                }

            });

            for (Object object : lista) {
                combo.addItem(object);
            }

        }

    }

    public static void configurarJanela(JFrame janela) {

        janela.setTitle(janela.getTitle() + Config.NOME_SISTEMA);
        janela.setIconImage(Config.LOGO_SISTEMA);

    }

    public static void configurarNovaJanela(JDialog janela) {

        janela.setTitle(janela.getTitle() + " - " + Config.NOME_SISTEMA_SIGLA);
        janela.setIconImage(Config.LOGO_SISTEMA);

    }

    public static void configurarNovaJanela(JFrame janela) {

        janela.setTitle(janela.getTitle() + " - " + Config.NOME_SISTEMA_SIGLA);
        janela.setIconImage(Config.LOGO_SISTEMA);

    }

    public static void configurarJanela(JDialog janela) {

        janela.setTitle(janela.getTitle() + Config.NOME_SISTEMA);
        janela.setIconImage(Config.LOGO_SISTEMA);

    }

    public static void configurarFonte(Component[] components) {

        for (Component c : components) {

            if (c instanceof JLabel) {

                ((JLabel) c).setFont(new Font("Arial", 0, 14));

            }

            if (c instanceof JButton) {
                ((JButton) c).setFont(new Font("Arial", 0, 14));
            }

            if (c instanceof JPanel) {
                configurarFonte(((JPanel) c).getComponents());
            }

        }

    }

    public boolean verificarFicha() {

        if (new FichaDAO().listar().isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public static void configurarTemaBotoes(Color fundo, Color texto) {
        UIManager.getLookAndFeelDefaults().put("Button.background", fundo);
        UIManager.getLookAndFeelDefaults().put("Button.textForeground", texto);
        UIManager.getLookAndFeelDefaults().put("background", Color.WHITE);
    }

    public static String retornaMes(String mes) {

        String mesRetorno = "";
        switch (mes) {
            case "1":
                mesRetorno = "Janeiro";

                break;
            case "2":
                mesRetorno = "Fevereiro";
                break;
            case "3":
                mesRetorno = "Março";
                break;

            case "4":
                mesRetorno = "Abril";
                break;

            case "5":
                mesRetorno = "Maio";
                break;

            case "6":
                mesRetorno = "Junho";
                break;

            case "7":
                mesRetorno = "Julho";
                break;

            case "8":
                mesRetorno = "Agosto";
                break;

            case "9":
                mesRetorno = "Setembro";
                break;

            case "10":
                mesRetorno = "Outubro";
                break;

            case "11":
                mesRetorno = "Novembro";
                break;

            case "12":
                mesRetorno = "Dezembro";
                break;
            default:

                mesRetorno = "";
        }

        return mesRetorno;

    }

    public static void adicionarPainel(JPanel pai, JPanel filho) {

        pai.removeAll();

        filho.setSize(pai.getSize());

        filho.repaint();
        filho.revalidate();

        pai.add(filho);

        pai.repaint();
        pai.revalidate();

    }

    public static void adicionarPainel(JFrame container, String titulo, JPanel pai, JPanel filho) {

        container.setTitle(titulo);
        configurarNovaJanela(container);

        pai.removeAll();

        filho.setSize(pai.getSize());

        filho.repaint();
        filho.revalidate();

        pai.add(filho);

        pai.repaint();
        pai.revalidate();

    }

    public static String formatarData(Date data) {
        return new SimpleDateFormat("dd-MM-yyyy...HH.mm.ss").format(data);
    }

    public static String formatarDataResumo(Date data) {
        return new SimpleDateFormat("dd-MM-yyyy").format(data);
    }

    public static byte[] gerarImagem(File file) {
        try {
            return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        } catch (IOException ex) {
            return null;
        }
    }

    public static Atualizacao atualizacao;

    public static void configurarPainel(JPanel jPanel) {
        jPanel.setBackground(Color.WHITE);
    }

    public static void carregarTema() {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());

                    Config.configurarTemaBotoes(Config.COR_BOTAO, Config.COR_TEXTO);

                    UIManager.getLookAndFeelDefaults().put("nimbusBase", Color.GRAY);
                    UIManager.getLookAndFeelDefaults().put("FileChooser.background", Color.WHITE);
                    UIManager.getLookAndFeelDefaults().put("FileChooser[Enabled].backgroundPainter", Color.WHITE);

                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Date setData(int hora, int minuto) {

        Calendar c = Calendar.getInstance();

        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), hora, minuto, 0);

        return c.getTime();

    }

    public String turnoAtual(Date data) {

        Calendar c = Calendar.getInstance();

        c.setTime(data);
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

        String turno = "";

        data = setData(hours, minutes);

        if (data.after(setData(8, 0)) && (data.before(setData(12, 0)))) {
            turno = "Manhã";
        } else if ((data.after(setData(12, 0)) || data.equals(setData(12, 0))) && data.before(setData(18, 0))) {
            turno = "Tarde";
        } else if ((data.after(setData(18, 0)) || data.equals(setData(18, 0))) && data.before(setData(22, 30))) {
            turno = "Noite";
        } else {
            turno = "Integral";
        }

        return turno;
    }

    public static void verificarArquivo() {
        File parametros = new File("parameters.txt");

        if (!parametros.exists()) {
            EncriptacaoAES encriptacaoAES = new EncriptacaoAES();
            List<String> linhas = new ArrayList<>();

            linhas.add(encriptacaoAES.encriptar("false"));
            linhas.add(encriptacaoAES.encriptar("false"));
            linhas.add(encriptacaoAES.encriptar("false"));

            try {
                Files.write(Paths.get("parameters.txt"), linhas, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void carregarParametros() {
        List<String> linhas = new ArrayList<>();

        try {
            linhas = Files.readAllLines(Paths.get("parameters.txt"), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        EncriptacaoAES encriptacaoAES = new EncriptacaoAES();

        Config.manterLogin = encriptacaoAES.desencriptar(linhas.get(0));
        Config.manterSenha = encriptacaoAES.desencriptar(linhas.get(1));
        Config.filtroEscolaNaoPermitida = encriptacaoAES.desencriptar(linhas.get(2));
    }

    public static void setPrompt(String texto, JTextComponent jTextComponent) {
        PromptSupport.setPrompt(texto, jTextComponent);
        PromptSupport.setFontStyle(Font.BOLD, jTextComponent);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, jTextComponent);
    }

    public static void salvarParametros() {
        EncriptacaoAES encriptacaoAES = new EncriptacaoAES();
        List<String> linhas = new ArrayList<>();

        linhas.add(Config.manterLogin);
        linhas.add(Config.manterSenha);
        linhas.add(encriptacaoAES.encriptar(Config.filtroEscolaNaoPermitida));

        try {
            Files.write(Paths.get("parameters.txt"), linhas, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
