/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.util;

import br.com.senai.entities.Escola;
import java.util.Date;

/**
 *
 * @author Hugo
 */
public class DadosRelatorio {

    public DadosRelatorio() {
    }
    
    public DadosRelatorio(Date inicio, Date termino) {
        this.inicio = inicio;
        this.termino = termino;
    }

    public DadosRelatorio(Date inicio, Date termino, Escola escola) {
        this.inicio = inicio;
        this.termino = termino;
        this.escola = escola;
    }
    
    
    private Date inicio;
    private Date termino;
    private Escola escola;

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }
    
    
    
    

}
