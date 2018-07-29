package br.com.senai.dao;

import br.com.senai.entities.Aluno;
import br.com.senai.entities.Movimentacao;
import br.com.senai.entities.Turma;
import br.com.senai.entities.Turno;
import br.com.senai.entities.Usuario;
import java.util.List;

/**
 *
 * @author Maysa
 */
public class AlunoDAO extends GenericDAO<Aluno> {

    public Aluno validarTurma(Long id, Turno turno) {
        Aluno a = (Aluno) session.createQuery("select a from Aluno a, Turma t where a.pk.turma=t and a.ativo=true and t.turno=:turno and a.pk.id=:id and t.ativa=true").setLong("id", id).setEntity("turno", turno).uniqueResult();
        fecharSessao();
        return a;

    }

    public List<Aluno> getAlunos(Turma t) {
        List<Aluno> alunos = session.createQuery("select a from Aluno a where a.pk.turma=:turma").setEntity("turma", t).list();
        fecharSessao();
        return alunos;

    }

    public List<Movimentacao> getMovimentacoes(Aluno aluno) {
        List<Movimentacao> m = session.createQuery("select m from Movimentacao m where m.pk.aluno=:aluno").setEntity("aluno", aluno).list();
        fecharSessao();
        return m;
    }

    public Aluno validarTurma(Long id, String turno) {
        Aluno a = (Aluno) session.createQuery("select a from Aluno a, Turma t where a.pk.turma=t and a.ativo=true and t.turno.turno=:turno and a.pk.id=:id and t.ativa=true").setLong("id", id).setString("turno", turno).uniqueResult();
        fecharSessao();
        return a;

    }

    public List<Aluno> alunosComID(Long id) {
        List<Aluno> alunos = session.createQuery("select a from Aluno a where a.pk.id=:id").setLong("id", id).list();
        fecharSessao();
        return alunos;

    }

    public List<Aluno> pesquisar(String query, Usuario u) {
        List<Aluno> alunos = session.createQuery("select a from Turma t, Permissao p, Usuario u, Escola e, Curso c, Curso_e_Escola ce, Aluno a where p.pk.escola=e and p.pk.usuario=u and p.permissao=true and u=:usu and ce.pk.escola=e and ce.pk.curso=c and t.curso=ce and a.pk.turma=t and " + query).setEntity("usu", u).list();
        fecharSessao();
        return alunos;

    }

    public List<Aluno> alunosPermitidos(Usuario u) {
        List<Aluno> a = session.createQuery("select a from Turma t, Permissao p, Usuario u, Escola e, Curso c, Curso_e_Escola ce, Aluno a where p.pk.escola=e and p.pk.usuario=u and p.permissao=true and u=:usu and ce.pk.escola=e and ce.pk.curso=c and t.curso=ce and a.pk.turma=t").setEntity("usu", u).list();
        fecharSessao();
        return a;
    }

}
