package br.com.senai.dao;

import br.com.senai.entities.Cidade;
import br.com.senai.entities.Escola;
import br.com.senai.entities.Usuario;
import java.util.List;

/**
 *
 * @author Maysa
 */
public class EscolaDAO extends GenericDAO<Escola> {

    public List<Escola> escolasPermitidas(Usuario u) {
        List<Escola> e = session.createQuery("select e from Escola e, Permissao p, Usuario u where p.pk.escola=e and p.pk.usuario=u and p.permissao=true and u=:usu").setEntity("usu", u).list();
        fecharSessao();
        return e;
    }

    public List<Escola> getEscolas(String termo) {
        List<Escola> escolas = session.createQuery("select e from Escola e where str(e.nome) like :termo").setString("termo", "%" + termo + "%").list();
        fecharSessao();
        return escolas;
    }

    public List<Escola> escolasPermitidasDaCidade(Usuario u, Cidade c) {
        List<Escola> e = session.createQuery("select e from Escola e, Permissao p, Usuario u where p.pk.escola=e and p.pk.usuario=u and p.permissao=true and u=:usu and e.cidade=:cidade").setEntity("cidade", c).setEntity("usu", u).list();
        fecharSessao();
        return e;
    }

    public List<Escola> escolaDaCidade(Cidade c) {
        List<Escola> le = session.createQuery("SELECT e FROM Escola e WHERE e.cidade=:c").setEntity("c", c).list();
        fecharSessao();
        return le;
    }

    public List<Escola> pesquisa(String query, Usuario u) {
        List<Escola> le = session.createQuery("select e from Escola e, Permissao p, Usuario u where p.pk.escola=e and p.pk.usuario=u and p.permissao=true and u=:usu and " + query).setEntity("usu", u).list();
        fecharSessao();
        return le;
    }

    public List<Escola> pesquisa(String query) {
//        List<Escola> le = session.createQuery("select e from Escola e, Permissao p, Usuario u where p.pk.escola=e and p.pk.usuario=u and p.permissao=true  and  " + query).list();
        List<Escola> le = session.createQuery("select e from Escola e where   " + query).list();
        fecharSessao();
        return le;
    }

    public Escola getEscola(String nome) {
        Escola escola = (Escola) session.createQuery("select e from Escola e where e.nome=:nome").setString("nome", nome).uniqueResult();
        fecharSessao();
        return escola;
    }

}
