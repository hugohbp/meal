package br.com.senai.dao;

import br.com.senai.entities.Aluno;
import br.com.senai.entities.Curso_e_Escola;
import br.com.senai.entities.Turma;
import br.com.senai.entities.Turno;
import br.com.senai.entities.Usuario;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Maysa
 */
public class TurmaDAO extends GenericDAO<Turma> {

    public List<Turma> pesquisarPorCursoETurno(Curso_e_Escola curso, Turno turno) {
        List<Turma> turmas = session.createQuery("select t from Turma t where t.curso=:curso and t.turno=:turno").setEntity("curso", curso).setEntity("turno", turno).list();
        fecharSessao();
        return turmas;
    }

    public List<Turma> pesquisarPorCurso(Curso_e_Escola curso) {
        List<Turma> turmas = session.createQuery("select t from Turma t where t.curso=:curso").setEntity("curso", curso).list();
        fecharSessao();
        return turmas;
    }
    
//    public List<Turma> pesquisarPorEscola(Escola escola) {
//        List<Turma> turmas = session.createQuery("select t from Turma t where t.curso").setEntity("curso", curso).list();
//        fecharSessao();
//        return turmas;
//    }

    public Turma pegarTurmaDoTurnoEAluno(Long id, String turno) {
        Turma turma = (Turma) session.createQuery("select t from Turma t, Aluno a where a.pk.turma.nome=t.nome and t.turno.turno=:turno and a.pk.id=:id ").setLong("id", id).setString("turno", turno).uniqueResult();
        fecharSessao();
        return turma;

    }

    public Aluno pegarTurmaDoTurnoEAluno2(Long id, String turno) {
        Aluno aluno = (Aluno) session.createQuery("select a from Aluno a, Turma t, ").setLong("id", id).setString("turno", turno).uniqueResult();
        fecharSessao();
        return aluno;

    }

    @Override
    public List<Turma> listar() {
        List<Turma> lista = session.createCriteria(classe).addOrder(Order.desc("nome")).list();
        fecharSessao();
        return lista;

    }

    public List<Turma> pesquisa(String query, Usuario u) {
        List<Turma> lista = session.createQuery("select t from Permissao p, Usuario u, Escola e, Curso c, Curso_e_Escola ce, Turma t where p.pk.escola=e and p.pk.usuario=u and p.permissao=true and ce.pk.curso=c and ce.pk.escola=e and u=:usu and t.curso=ce and " + query).setEntity("usu", u).list();
        fecharSessao();
        return lista;

    }
    
     

    public Turma isAtiva(Turma turma) {
        Turma t = (Turma) session.createQuery("select t from Turma t where t=:turma and t.ativa=true").setEntity("turma", turma).uniqueResult();
        fecharSessao();
        return t;

    }

    public Turma getNome(String nome) {
        Turma t = (Turma) session.createQuery("select t from Turma t where t.nome=:nome").setString("nome", nome).uniqueResult();
        fecharSessao();
        return t;

    }

    public List<Turma> turmasPermitidas(Usuario u) {
        List<Turma> c = session.createQuery("select t from Turma t, Permissao p, Usuario u, Escola e, Curso c, Curso_e_Escola ce where p.pk.escola=e and p.pk.usuario=u and p.permissao=true and u=:usu and ce.pk.escola=e and ce.pk.curso=c and t.curso=ce and t.ativa=true").setEntity("usu", u).list();
        fecharSessao();
        return c;

    }

}
