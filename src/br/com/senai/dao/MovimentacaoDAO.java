package br.com.senai.dao;

import br.com.senai.entities.Curso;
import br.com.senai.entities.Curso_e_Escola;
import br.com.senai.entities.Escola;
import br.com.senai.entities.Movimentacao;
import br.com.senai.entities.Tipo;
import br.com.senai.entities.Turma;
import br.com.senai.entities.Usuario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Maysa
 */
public class MovimentacaoDAO extends GenericDAO<Movimentacao> {

    public List<Movimentacao> listarPorData() {
        List<Movimentacao> movimentacoes = this.session.createQuery("select m from Movimentacao m order by m.horarioMovimentacao DESC").setMaxResults(100).list();
        this.fecharSessao();
        return movimentacoes;
    }

    public List<Movimentacao> listarMovimentacoesDaTurma(Turma t) {
        List<Movimentacao> movimentacoes = this.session.createQuery("select m from Movimentacao m where m.pk.aluno.pk.turma=:turma").setEntity("turma", t).setMaxResults(100).list();
        this.fecharSessao();
        return movimentacoes;
    }

    public List<Movimentacao> listarMovimentacoesDoCurso(Curso c) {
        List<Movimentacao> movimentacoes = this.session.createQuery("select m from Movimentacao m where m.pk.aluno.pk.turma.curso.pk.curso=:curso").setEntity("curso", c).setMaxResults(100).list();
        this.fecharSessao();
        return movimentacoes;
    }

    public List<Movimentacao> listarMovimentacoesDaModalidade(Tipo t) {
        List<Movimentacao> movimentacoes = this.session.createQuery("select m from Movimentacao m where m.pk.aluno.pk.turma.curso.tipo=:tipo").setEntity("tipo", t).setMaxResults(100).list();
        this.fecharSessao();
        return movimentacoes;
    }

    public List<Movimentacao> listarMovimentacoesDaEscola(Escola escola) {
        List<Movimentacao> movimentacoes = this.session.createQuery("select m from Movimentacao m where m.pk.aluno.pk.turma.curso.pk.escola=:escola").setEntity("escola", escola).setMaxResults(100).list();
        this.fecharSessao();
        return movimentacoes;
    }

    public List<Movimentacao> listarMovimentacoesDoCurso(Curso_e_Escola curso_e_Escola) {
        List<Movimentacao> movimentacoes = this.session.createQuery("select m from Movimentacao m where m.pk.aluno.pk.turma.curso=:curso").setEntity("curso", curso_e_Escola).setMaxResults(100).list();
        this.fecharSessao();
        return movimentacoes;
    }

    public List<Object[]> mediaEscola(Escola escola, int mes, int ano) {
        List<Object[]> lista = session.createQuery("select m.pk.aluno.pk.turma.curso.pk.escola.nome , EXTRACT (MONTH FROM m.pk.dataMovimentacao) as  mes_movimentacao, count(*), (select count (DISTINCT m.pk.dataMovimentacao) from Movimentacao m where EXTRACT (MONTH FROM m.pk.dataMovimentacao) = :mes  and EXTRACT (YEAR FROM m.pk.dataMovimentacao)= :ano and m.pk.aluno.pk.turma.curso.pk.escola=:escola ORDER BY (EXTRACT (MONTH FROM m.pk.dataMovimentacao)))  from Movimentacao m where m.pk.aluno.pk.turma.curso.pk.escola=:escola  and (EXTRACT (MONTH FROM m.pk.dataMovimentacao)) = :mes and (EXTRACT (YEAR FROM m.pk.dataMovimentacao)) = :ano GROUP BY EXTRACT (MONTH FROM m.pk.dataMovimentacao) ORDER BY (EXTRACT (MONTH FROM m.pk.dataMovimentacao)) ").setInteger("mes", mes).setInteger("ano", ano).setEntity("escola", escola).list();
        fecharSessao();
        return lista;

    }

    public List<Movimentacao> funcionarios(Escola escola, Curso curso, Date data) {
        List<Movimentacao> lista = session.createQuery("select m from Movimentacao m where m.pk.aluno.pk.turma.curso.pk.escola=:escola and m.pk.aluno.pk.turma.curso.pk.curso=:curso and m.pk.dataMovimentacao=:data").setEntity("escola", escola).setEntity("curso", curso).setDate("data",data).list();
        fecharSessao();
        return lista;
    }

    public List<Object[]> totalDefasagem(Escola escola, int mes, int ano) {
        List<Object[]> l = session.createQuery("select ").list();
        fecharSessao();
        return l;
    }

//MAL RESOLVIDOOOOOO]
//    public List<Object[]> totalMovimentacoes(Usuario u) {
//        List<Object[]> l = session.createQuery("select A.nome_escola, A.total, B.total_mov from (( select e.nome as nome_escola, (count (a) * (select count (DISTINCT m.pk.dataMovimentacao)) as total from Movimentacao m where m.pk.aluno.pk.turma.curso.escola=e) from Escola e, Permissao p, Usuario u, Aluno a where p.pk.escola=e and p.pk.usuario=u and p.permissao=true and a.pk.turma.curso.escola=e and u=:usu and a.ativo=true group by e )) AS A, (( select e.nome as nome_escola, count(m) as total_mov from Escola e, Movimentacao m, Permissao p, Usuario u where m.pk.aluno.pk.turma.curso.escola=e and p.pk.escola=e and p.pk.usuario=u and p.permissao=true and u=:usu group by e )) AS B where A.nome_escola=B.nome_escola").setEntity("usu", u).list();
//        fecharSessao();
//        return l;
//
//    }
    public List<Object[]> totalMovimentacoes(Usuario u, Escola e, int mes, int ano) {
        List<Object[]> l = session.createQuery("select e.nome, (select count(*) from Aluno a where a.pk.turma.curso.pk.escola=e ), (select count (DISTINCT m1.pk.dataMovimentacao) from Movimentacao m1 where m1.pk.aluno.pk.turma.curso.pk.escola=e and MONTH(m1.pk.dataMovimentacao)=:mes and YEAR(m1.pk.dataMovimentacao)=:ano),"
                + " (select count(*) from Aluno a where a.pk.turma.curso.pk.escola=e ) * (select count (DISTINCT m1.pk.dataMovimentacao) from Movimentacao m1 where m1.pk.aluno.pk.turma.curso.pk.escola=e and MONTH(m1.pk.dataMovimentacao)=:mes and YEAR(m1.pk.dataMovimentacao) =:ano)"
                + " from Escola e, Permissao p, Usuario u, Aluno a, Movimentacao m where p.pk.escola=e and p.pk.usuario=u and p.permissao=true and a.pk.turma.curso.pk.escola=e and u=:usu and a.ativo=true and m.pk.aluno.pk.turma.curso.pk.escola=e and MONTH(m.pk.dataMovimentacao)=:mes and YEAR(m.pk.dataMovimentacao) =:ano and e=:escola group by  e.nome").setEntity("escola", e).setEntity("usu", u).setInteger("ano", ano).setInteger("mes", mes).list();
        fecharSessao();
        return l;

    }

    public List<Object[]> totalMovimentacoesRealizadas(Usuario u, Escola e, int mes, int ano) {
        List<Object[]> l = session.createQuery("select e.nome, count(m) from Escola e, Movimentacao m, Permissao p, Usuario u where m.pk.aluno.pk.turma.curso.pk.escola=e and p.pk.escola=e and p.pk.usuario=u and p.permissao=true and u=:usuario and MONTH(m.pk.dataMovimentacao)=:mes and YEAR(m.pk.dataMovimentacao)=:ano and e=:escola group by e").setEntity("escola", e).setInteger("mes", mes).setInteger("ano", ano).setEntity("usuario", u).list();
        fecharSessao();
        return l;

    }

}
