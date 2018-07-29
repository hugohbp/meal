/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Hugo
 */
@Entity
public class Tipo implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipo")
    private List<Curso_e_Escola> ce;

    public Tipo() {
    }

    public Tipo(String nome) {
        this.nome = nome;
    }
    
    

    public List<Curso_e_Escola> getCe() {
        return ce;
    }

    public void setCe(List<Curso_e_Escola> ce) {
        this.ce = ce;
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

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tipo other = (Tipo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
