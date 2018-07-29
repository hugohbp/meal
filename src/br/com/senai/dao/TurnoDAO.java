
package br.com.senai.dao;

import br.com.senai.entities.Turno;

/**
 *
 * @author Maysa
 */
public class TurnoDAO extends GenericDAO<Turno>{
    
    public Turno getTurno(String nome){
        Turno turno = (Turno) session.createQuery("select t from Turno t where t.turno=:nome").setString("nome", nome).uniqueResult();
        fecharSessao();
        return turno;
    }
    

}
