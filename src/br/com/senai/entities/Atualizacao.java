/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.entities;

import br.com.senai.util.Config;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;

/**
 *
 * @author Hugo
 */
@Entity
public class Atualizacao implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(nullable = true)
    private String url;

    @Column(nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataLancamento;

    @Column(nullable = false)
    private String versao;

    @Column(nullable = false)
    private String versaoRequerida;

    @Lob
    @Column(nullable = true)
    private byte[] arquivo;

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getVersaoRequerida() {
        return versaoRequerida;
    }

    public void setVersaoRequerida(String versaoRequerida) {
        this.versaoRequerida = versaoRequerida;
    }

    @Override
    public String toString() {
        return getNome() + "; Versão: " + getVersao() + "; " + Config.formatarDataResumo(getDataLancamento());
    }

}
