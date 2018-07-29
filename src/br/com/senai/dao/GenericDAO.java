package br.com.senai.dao;

import br.com.senai.util.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Maysa
 */
abstract class GenericDAO<T> {

    protected Session session;
    protected Class classe;

    public GenericDAO() {
        classe = gerarClasse();
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }

    private Class<T> gerarClasse() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void fecharSessao() {
        session.getTransaction().commit();
        session.close();
    }

    public void salvar(T o) {

        try {
            session.save(o);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            throw ex;
        } finally {
            session.close();
        }

    }

    public Session getSession() {
        return session;
    }

    public T buscarPorId(Serializable id) {
        T entity = (T) session.get(classe, id);
        fecharSessao();
        return entity;

    }

    public void atualizar(T t) {
        session.update(t);
        fecharSessao();
    }

    public void deletar(T t) {
        session.delete(t);
        fecharSessao();
    }

    public List<T> listar() {
        List<T> lista = session.createCriteria(classe).list();
        fecharSessao();
        return lista;
    }

    public List<T> restricaoEqual(String campo, Object valor) {
        List<T> list = session.createCriteria(classe).add(Restrictions.eq(campo, valor)).list();
        fecharSessao();
        return list;
    }

}
