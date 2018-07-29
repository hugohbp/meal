/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Hugo
 */
@Entity
public class Movimentacao implements Serializable {

    private MovimentacaoPK pk = new MovimentacaoPK();

    private Ficha ficha;

    private Date horarioMovimentacao;

    @Temporal(TemporalType.TIMESTAMP)
    public Date getHorarioMovimentacao() {
        return horarioMovimentacao;
    }

    public void setHorarioMovimentacao(Date horarioMovimentacao) {
        this.horarioMovimentacao = horarioMovimentacao;
    }

    @ManyToOne
    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    @EmbeddedId
    public MovimentacaoPK getPk() {
        return pk;
    }

    public void setPk(MovimentacaoPK pk) {
        this.pk = pk;
    }

    public void setDataMovimentacao(Date data) {
        getPk().setDataMovimentacao(data);
    }

    @Transient
    public Date getDataMovimentacao() {
        return getPk().getDataMovimentacao();
    }

    public void setAluno(Aluno aluno) {
        getPk().setAluno(aluno);
    }

    @Transient
    public Aluno getAluno() {

        return getPk().getAluno();
    }

}
