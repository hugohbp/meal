/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entities.Ajuda;

/**
 *
 * @author Simulado
 */
public class AjudaDAO extends GenericDAO<Ajuda> {

    public Ajuda buscarPorTopico(String id) {
        Ajuda ajuda = (Ajuda) session.createQuery("select ajuda from Ajuda ajuda where ajuda.topico=:topico").setString("topico", id).uniqueResult();
        fecharSessao();
        return ajuda;
    }

}
