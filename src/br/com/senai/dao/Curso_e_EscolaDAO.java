/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entities.Curso;
import br.com.senai.entities.Curso_e_Escola;
import br.com.senai.entities.Escola;
import java.util.List;

/**
 *
 * @author Simulado
 */
public class Curso_e_EscolaDAO extends GenericDAO<Curso_e_Escola> {

    public List<Curso> cursosDaEscola(Escola e) {
        List<Curso> l = session.createQuery("select ce.pk.curso from Curso_e_Escola ce where ce.pk.escola=:escola").setEntity("escola", e).list();
        fecharSessao();
        return l;
    }

    public Curso_e_Escola getCurso_e_Escola(Curso c, Escola e) {
        Curso_e_Escola ce = (Curso_e_Escola) session.createQuery("select ce from Curso_e_Escola ce where ce.pk.curso=:curso and ce.pk.escola=:escola").setEntity("curso", c).setEntity("escola", e).uniqueResult();
        fecharSessao();
        return ce;
    }

}
