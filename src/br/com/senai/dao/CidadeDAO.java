/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entities.Cidade;
import br.com.senai.entities.Usuario;
import java.util.List;

/**
 *
 * @author Simulado
 */
public class CidadeDAO extends GenericDAO<Cidade> {

    public List<Cidade> cidadesPermitidas(Usuario u) {
        List<Cidade> c = session.createQuery("select c from Escola e, Permissao p, Usuario u, Cidade c where p.pk.escola=e and p.pk.usuario=u and p.permissao=true and e.cidade=c  and u=:usu group by c").setEntity("usu", u).list();
        fecharSessao();
        return c;
    }

    public List<Cidade> cidadesQuePossuemEscola() {
        List<Cidade> c = session.createQuery("select e.cidade from Escola e group by e.cidade").list();
        fecharSessao();
        return c;
    }

}
