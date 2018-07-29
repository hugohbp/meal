package br.com.senai.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author Hugo
 */
@Entity
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    private AlunoPK pk = new AlunoPK();

    private boolean ativo;

    private List<Movimentacao> movimentacoes;

    
    private String nome;

    private String programa;

    @Column(nullable = true)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(nullable = true)
    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @OneToMany(mappedBy = "pk.aluno", cascade = CascadeType.ALL)
    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    @EmbeddedId
    private AlunoPK getPk() {
        return pk;
    }

    public void setPk(AlunoPK pk) {
        this.pk = pk;
    }

    public void setAluno(Long id) {
        getPk().setId(id);
    }

    @Transient
    public Long getAluno() {
        return this.getPk().getId();
    }

    @Transient
    public Turma getTurma() {
        return this.getPk().getTurma();
    }

    public void setTurma(Turma turma) {
        this.getPk().setTurma(turma);
    }

    @Override
    public String toString() {
        return pk.getId().toString();
    }

}
