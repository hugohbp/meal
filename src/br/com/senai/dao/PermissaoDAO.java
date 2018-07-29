/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entities.Escola;
import br.com.senai.entities.Permissao;
import br.com.senai.entities.Usuario;
import java.util.List;

/**
 *
 * @author Simulado
 */
public class PermissaoDAO extends GenericDAO<Permissao> {

    public List<Permissao> doUsuario(Usuario u) {
        List<Permissao> p = session.createQuery("select p from Permissao p where p.pk.usuario=:usu").setEntity("usu", u).list();
        fecharSessao();
        return p;
    }
    
    
    public Permissao doUsuarioEEscola(Usuario u, Escola e) {
        Permissao p = (Permissao) session.createQuery("select p from Permissao p where p.pk.usuario=:usu and p.pk.escola=:esc").setEntity("esc", e).setEntity("usu", u).uniqueResult();
        fecharSessao();
        return p;
    }
    

}
