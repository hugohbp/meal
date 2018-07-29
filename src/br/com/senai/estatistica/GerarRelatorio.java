/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estatistica;

import br.com.senai.util.Config;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author Hugo
 */
public class GerarRelatorio {

    Connection con;

    public GerarRelatorio() {
        con = Conexao.getConnection(Config.urlBD, Config.usuarioBD, Config.senhaBD);
    }

   

    public JPanel gerarRelatorioGeral(Date dataInicio, Date dataTermino) {

        HashMap mapa = new HashMap();

        mapa.put("DATA_INICIO", dataInicio);
        mapa.put("DATA_TERMINO", dataTermino);
        mapa.put("NOME", Config.usuario.getNome());
        mapa.put("ID_USUARIO", Config.usuario.getId());

        String arquivo = "relatorioPadraoPaes.jasper";
        JRViewer relatorio = null;

        try {
            JasperPrint jp = JasperFillManager.fillReport(arquivo, mapa, con);

            relatorio = new JRViewer(jp);
            relatorio.setZoomRatio(0.5273f);
            relatorio.setBackground(Color.WHITE);

            con.close();

            if (con.isClosed()) {
//                System.out.println("FECHADOOOOOOOO");
            }

        } catch (JRException | SQLException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return relatorio;

    }

    public JPanel gerarRelatorioPorEscolas(Date dataInicio, Date dataTermino) {

        HashMap mapa = new HashMap();

        mapa.put("DATA_INICIO", dataInicio);
        mapa.put("DATA_TERMINO", dataTermino);
        mapa.put("NOME", Config.usuario.getNome());
        mapa.put("ID_USUARIO", Config.usuario.getId());

        String arquivo = "relatorioPorEscolas.jasper";
        JRViewer relatorio = null;

        try {

            JasperPrint jp = JasperFillManager.fillReport(arquivo, mapa, con);

            relatorio = new JRViewer(jp);
            relatorio.setZoomRatio(0.5273f);

            con.close();

            if (con.isClosed()) {
//                System.out.println("FECHADOOOOOOOO");
            }

        } catch (JRException | SQLException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return relatorio;

    }

}
