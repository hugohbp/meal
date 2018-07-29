/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entities.TipoUsuario;

/**
 *
 * @author Simulado
 */
public class TipoUsuarioDAO extends GenericDAO<TipoUsuario> {

    public TipoUsuario getByName(String tipo) {

        TipoUsuario tu = (TipoUsuario) session.createQuery("select tu from TipoUsuario tu where tu.descricao=:desc").setString("desc", tipo).uniqueResult();
        fecharSessao();

        return tu;

    }

}
