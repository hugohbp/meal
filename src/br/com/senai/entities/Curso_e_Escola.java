/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Simulado
 */
@Entity
public class Curso_e_Escola implements Serializable {

    private List<Turma> turmas;

    private Curso_e_EscolaPK pk = new Curso_e_EscolaPK();

    private Tipo tipo;

    private boolean ativo;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @EmbeddedId
    public Curso_e_EscolaPK getPk() {
        return pk;
    }

    public void setPk(Curso_e_EscolaPK pk) {
        this.pk = pk;
    }

    @ManyToOne
    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE)
    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

}
