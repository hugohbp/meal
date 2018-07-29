/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entities.Escola;
import br.com.senai.entities.Ficha;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hugo
 */
public class FichaDAO extends GenericDAO<Ficha> {

 

    public Ficha pegarUltimoValor(Escola e) {
        
        Ficha f = (Ficha) session.createQuery("select f from Ficha f where f.dataAlteracao=(select max(ff.dataAlteracao) from Ficha ff where ff.escola=:escola) and f.escola=:escola").setEntity("escola", e).uniqueResult();
        this.fecharSessao();
        return f;

    }

    public List<Ficha> pegarFichaNaDataEEscola(Date data, Escola e) {
  
        List<Ficha> fichas = session.createQuery("select f from Ficha f where f.dataAlteracao=:data and f.escola=:escola").setEntity("escola", e).setDate("data", data).list();
        this.fecharSessao();
        return fichas;

    }
    public List<Ficha> pegarFichaEscola(Escola e) {
  
        List<Ficha> fichas = session.createQuery("select f from Ficha f where f.escola=:escola").setEntity("escola", e).list();
        this.fecharSessao();
        return fichas;

    }

}
