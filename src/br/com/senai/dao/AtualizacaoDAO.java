/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entities.Atualizacao;
import java.util.List;

/**
 *
 * @author Hugo
 */
public class AtualizacaoDAO extends GenericDAO<Atualizacao> {

    public List<Atualizacao> pegarVersoes() {
        List<Atualizacao> atualizacoes = session.createQuery("select a from Atualizacao a order by a.dataLancamento DESC").list();
        fecharSessao();
        return atualizacoes;
    }

}
