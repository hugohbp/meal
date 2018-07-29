/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entities.Tipo;

/**
 *
 * @author Hugo
 */
public class TipoDAO extends GenericDAO<Tipo> {

    public Tipo getTipo(String nome) {
        Tipo tipo = (Tipo) session.createQuery("select tipo from Tipo tipo where tipo.nome=:nome").setString("nome", nome).uniqueResult();
        fecharSessao();
        return tipo;
    }

}
