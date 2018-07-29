/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.util;

import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Hugo
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().setProperty("hibernate.connection.password", Config.senhaBD).setProperty("hibernate.connection.username", Config.usuarioBD).setProperty("hibernate.connection.url", "jdbc:mysql://" + Config.urlBD + "/" + Config.NOME_BANCO_DE_DADOS + "?zeroDateTimeBehavior=convertToNull").
                    buildSessionFactory();

        } catch (HibernateException ex) {
            Config.carregarTema();

//            System.err.println("Initial SessionFactory creation failed." + ex);
//            Config.mensagemDeErro("Falha ao conectar a rede. Contate o administrador.");
//            new OpcoesBanco().setVisible(true);

            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
