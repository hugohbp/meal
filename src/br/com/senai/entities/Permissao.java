/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * @author Simulado
 */
@Entity
public class Permissao implements Serializable {

    private PermissaoPK pk = new PermissaoPK();

    private boolean permissao;

    @EmbeddedId
    public PermissaoPK getPk() {
        return pk;
    }

    public void setPk(PermissaoPK pk) {
        this.pk = pk;
    }

    public boolean isPermissao() {
        return permissao;
    }

    public void setPermissao(boolean permissao) {
        this.permissao = permissao;
    }

    public void setUsuario(Usuario u) {
        getPk().setUsuario(u);
    }

    @Transient
    public Usuario getUsuario() {
        return getPk().getUsuario();
    }

    public void setEscola(Escola e) {
        getPk().setEscola(e);
    }

    @Transient
    public Escola getEscola() {
        return getPk().getEscola();

    }

}
