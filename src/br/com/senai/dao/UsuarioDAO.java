package br.com.senai.dao;

import br.com.senai.entities.TipoUsuario;
import br.com.senai.entities.Usuario;
import br.com.senai.util.Config;
import java.util.List;

/**
 *
 * @author Maysa
 */
public class UsuarioDAO extends GenericDAO<Usuario> {
    
    public Usuario validarUsuario(String usuario) {
        Usuario u = (Usuario) session.createQuery("select u from Usuario u where u.login=:login").setString("login", usuario).uniqueResult();
        fecharSessao();
        return u;
    }
    
    public Usuario validarUsuarioEditar(String usuario) {
        Usuario u = (Usuario) session.createQuery("select u from Usuario u where u.login=:login and id!=:id ").setString("login", usuario).setInteger("id", Config.usuario.getId()).uniqueResult();
        fecharSessao();
        return u;
    }
    
    public Usuario validarLogin(String usuario, String senha) {
        Usuario u = (Usuario) session.createQuery("from Usuario where login=:login and senha=:senha").setString("login", usuario).setString("senha", senha).uniqueResult();
        fecharSessao();
        return u;
    }
    
    public Usuario validarEmail(String email) {
        Usuario u = (Usuario) session.createQuery("from Usuario where email=:email").setString("email", email).uniqueResult();
        fecharSessao();
        return u;
    }
    
    public List<Usuario> termo(String termo, Usuario u) {
        List<Usuario> l = session.createQuery("select u from Usuario u where ( u.nome like '%" + termo + "%' or u.login like '%" + termo + "%' or u.email like '%" + termo + "%' ) and u.criadoPor=:usu").setEntity("usu", u).list();
        fecharSessao();
        return l;
    }
    
    public List<Usuario> termoETipo(String termo, TipoUsuario t, Usuario u) {
        List<Usuario> l = session.createQuery("select u from Usuario u where ( u.nome like '%" + termo + "%' or u.login like '%" + termo + "%' or u.email like '%" + termo + "%' ) and u.tipoUsuario=:tipo and u.criadoPor=:usu").setEntity("tipo", t).setEntity("usu", u).list();
        fecharSessao();
        return l;
    }
    
    public List<Usuario> tipo(TipoUsuario t, Usuario u) {
        List<Usuario> l = session.createQuery("select u from Usuario u where u.tipoUsuario=:tipo and u.criadoPor=:usu").setEntity("tipo", t).setEntity("usu", u).list();
        fecharSessao();
        return l;
    }
    
    public List<Usuario> termoETipoF(String termo, Usuario u) {
        List<Usuario> l = session.createQuery("select u from Usuario u where ( u.nome like '%" + termo + "%' or u.login like '%" + termo + "%' or u.email like '%" + termo + "%' ) and u.tipoUsuario='Funcionario' and u.criadoPor=:usu").setEntity("usu", u).list();
        fecharSessao();
        return l;
    }
    
    public List<Usuario> criadosPorMim(Usuario u) {
        List<Usuario> l = session.createQuery("select u from Usuario u where u.criadoPor=:usu and u.criadoPor is not null").setEntity("usu", u).list();
        fecharSessao();
        return l;
    }
    
    public Usuario buscarPorId(String id) {
        Usuario u = (Usuario) session.createQuery("select u from Usuario u where u.login=:login").setString("login", id).uniqueResult();
        fecharSessao();
        return u;
    }
    
}
