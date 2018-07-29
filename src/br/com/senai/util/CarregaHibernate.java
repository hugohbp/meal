/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.util;

import br.com.senai.dao.AtualizacaoDAO;
import br.com.senai.dao.TipoUsuarioDAO;
import br.com.senai.dao.TurnoDAO;
import br.com.senai.dao.UsuarioDAO;
import br.com.senai.entities.Atualizacao;
import br.com.senai.entities.TipoUsuario;
import br.com.senai.entities.Turno;
import br.com.senai.entities.Usuario;
import br.com.senai.view.Login;
import br.com.senai.view.OpcoesBanco;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingWorker;
import org.hibernate.exception.JDBCConnectionException;

public class CarregaHibernate extends SwingWorker<Void, Integer> {

    private JFrame telaPai;

    public JFrame getTelaPai() {
        return telaPai;
    }

    public void setTelaPai(JFrame telaPai) {
        this.telaPai = telaPai;
    }

    public CarregaHibernate() {
    }

    public CarregaHibernate(JFrame telaPai) {
        this.telaPai = telaPai;
    }

    @Override
    protected Void doInBackground() throws Exception {

        File configuracao = new File("config.txt");

        if (!configuracao.exists()) {
            EncriptacaoAES encriptacaoAES = new EncriptacaoAES();
            List<String> linhas = new ArrayList<>();

            linhas.add(encriptacaoAES.encriptar("nomeDoBancoDeDados"));
            linhas.add(encriptacaoAES.encriptar("IP"));
            linhas.add(encriptacaoAES.encriptar("Senha"));

            Files.write(Paths.get("config.txt"), linhas, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
        
        Config.verificarArquivo();

        try {

            synchronized (Config.class) {
                Config.carregarTema();
                Config.carregarConexao();
                try {
                    HibernateUtil.getSessionFactory().openSession().close();
                } catch (ExceptionInInitializerError error) {
                    Config.mensagemDeErro("Falha ao conectar a rede. Contate o administrador.");

                    JPasswordField jpassword = new JPasswordField();
                    if (JOptionPane.showConfirmDialog(null, jpassword, "Entre com a senha", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION) {
                        String password = new String(jpassword.getPassword());

                        if (password.equalsIgnoreCase(new Config().senhaAdministrador)) {
                            new OpcoesBanco().setVisible(true);
                        } else {
                            System.exit(0);
                        }

                    } else {
                        System.exit(0);
                    }
                }
            }

            List<Atualizacao> atualizacoes = new AtualizacaoDAO().pegarVersoes();

            if (!atualizacoes.isEmpty()) {

                for (Atualizacao atualizacao : atualizacoes) {
                    System.out.println(atualizacao.getNome());

                    if (atualizacao.getVersaoRequerida().equalsIgnoreCase(Config.VERSAO_DO_SISTEMA) && !atualizacao.getVersao().equalsIgnoreCase(Config.VERSAO_DO_SISTEMA)) {
                        Config.atualizacao = atualizacao;

                        if (atualizacao.getArquivo() != null) {

                            try {

                                Files.write(Paths.get(System.getProperty("user.dir") + "/SistemaDePaes.jar"), atualizacao.getArquivo());

                                Runtime runtime = Runtime.getRuntime();
                                runtime.exec("cmd /c java -jar SistemaDePaes.jar");

                                System.exit(0);

                            } catch (IOException ex) {
                                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                        break;

                    }
                }
            }

            if (new TipoUsuarioDAO().listar().isEmpty()) {

                TipoUsuario tp = new TipoUsuario();

                tp.setDescricao("Gerente");

                new TipoUsuarioDAO().salvar(tp);

                TipoUsuario tp2 = new TipoUsuario();

                tp2.setDescricao("Administrador");

                new TipoUsuarioDAO().salvar(tp2);

                TipoUsuario tp3 = new TipoUsuario();

                tp3.setDescricao("Funcionário");

                new TipoUsuarioDAO().salvar(tp3);
            }

            if (new UsuarioDAO().listar().isEmpty()) {

                Usuario u = new Usuario();
                u.setTipoUsuario(new TipoUsuarioDAO().getByName("Administrador"));
                u.setNome("Administrador do sistema");
                u.setSenha(Config.criptografarSenha(((String) "admin@cetel").toCharArray()));
                u.setLogin("admin");
                u.setEmail("service.meal@gmail.com");

                new UsuarioDAO().salvar(u);

            }

            if (new TurnoDAO().listar().isEmpty()) {

                new TurnoDAO().salvar(new Turno("Manhã"));
                new TurnoDAO().salvar(new Turno("Tarde"));
                new TurnoDAO().salvar(new Turno("Noite"));
                new TurnoDAO().salvar(new Turno("Integral"));

            }

        } catch (JDBCConnectionException ex) {
            System.out.println(ex.getMessage());

        }

        this.telaPai.dispose();

        new Login().setVisible(true);
        return null;
    } // Fim do método doBackground  

} // Fim da classe CarregaHibernate  
