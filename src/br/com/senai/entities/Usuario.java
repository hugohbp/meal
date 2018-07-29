/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Hugo
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String login;

    @Column(nullable = true)
    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @ManyToOne
    private TipoUsuario tipoUsuario;

    private boolean senhaAlterada;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Usuario criadoPor;

    @OneToMany(mappedBy = "criadoPor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Usuario> subordinados = new HashSet<>();

    @OneToMany(mappedBy = "pk.usuario", cascade = CascadeType.ALL)
    private List<Permissao> permissoes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Usuario> getSubordinados() {
        return subordinados;
    }

    public void setSubordinados(Set<Usuario> subordinados) {
        this.subordinados = subordinados;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public Usuario getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Usuario criadoPor) {
        this.criadoPor = criadoPor;
    }

    public boolean isSenhaAlterada() {
        return senhaAlterada;
    }

    public void setSenhaAlterada(boolean senhaAlterada) {
        this.senhaAlterada = senhaAlterada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return nome;
    }

}
