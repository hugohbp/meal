package br.com.senai.dao;

import br.com.senai.entities.Curso;
import br.com.senai.entities.Curso_e_Escola;

import br.com.senai.entities.Escola;
import br.com.senai.entities.Tipo;
import br.com.senai.entities.Usuario;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Maysa
 */
public class CursoDAO extends GenericDAO<Curso> {

    public List<Object[]> quantidadeDeLanches(Escola escola) {
        List<Object[]> list = session.createQuery("select c.nome, count(*) from Curso c, Movimentacao m where m.pk.aluno.pk.turma.curso.pk.curso =c  and m.pk.aluno.pk.turma.curso.pk.escola=:escola group by c ").setEntity("escola", escola).setEntity("escola", escola).list();
        fecharSessao();
        return list;
    }

    @Override
    public List<Curso> listar() {
        List<Curso> lista = session.createCriteria(classe).addOrder(Order.desc("id")).list();
        fecharSessao();
        return lista;
    }

    public List<Curso_e_Escola> cursosPermitidos(Usuario u) {
        List<Curso_e_Escola> c = session.createQuery("select ce from Permissao p, Usuario u, Escola e, Curso c, Curso_e_Escola ce where p.pk.escola=e and p.pk.usuario=u and p.permissao=true and ce.pk.curso=c and ce.pk.escola=e and u=:usu and ce.ativo=true").setEntity("usu", u).list();
        fecharSessao();
        return c;
    }

    public List<Curso_e_Escola> pesquisa(String query, Usuario u) {
        List<Curso_e_Escola> c = session.createQuery("select ce from Permissao p, Usuario u, Escola e, Curso c, Curso_e_Escola ce where p.pk.escola=e and p.pk.usuario=u and p.permissao=true and ce.pk.curso=c and ce.pk.escola=e and u=:usu and " + query).setEntity("usu", u).list();
        fecharSessao();
        return c;
    }

    public Curso getCurso(String nomeCurso, Escola escola, Tipo tipo) {
        Curso c = (Curso) session.createQuery("SELECT c FROM Curso c where c.escola=:escola and c.tipo=:tipo and c.nome=:nome").setEntity("escola", escola).setEntity("tipo", tipo).setString("nome", nomeCurso).uniqueResult();
        fecharSessao();
        return c;
    }

    public Curso getCurso(String nome) {
        Curso curso = (Curso) session.createQuery("SELECT curso from Curso curso where curso.nome=:nome").setString("nome", nome).uniqueResult();
        fecharSessao();
        return curso;
    }

}
