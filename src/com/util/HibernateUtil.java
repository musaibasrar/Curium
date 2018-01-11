/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;
 
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author 
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     *
     * @return
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     *
     */
    public static void closeSession() {
        sessionFactory.getCurrentSession().close();

    }
    /**
     *
     * @return
     */
    public static Session openSession(){
        return getSessionFactory().openSession();

    }
    /**
     *
     * @return
     */
    public static Session openCurrentSession() {
        if(getSessionFactory().getCurrentSession().isOpen()){
            return getSessionFactory().getCurrentSession();

        }
        else{
            return getSessionFactory().openSession();
        }

        //return getSessionFactory().getCurrentSession();
    }
}
