/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author 
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);
    
   // private static final ServiceRegistry serviceRegistry;

    
   /* final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    .configure() // configures settings from hibernate.cfg.xml
    .build();*/

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
        	
        	//sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        	
        	/*Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    configuration.getProperties()). buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);*/
             //Session session = sessionFactory.openSession();
        	
           /* Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            
            
            configuration.setSessionFactoryObserver(
                    new SessionFactoryObserver() {
                        @Override
                        public void sessionFactoryCreated(SessionFactory factory) {}
                        @Override
                        public void sessionFactoryClosed(SessionFactory factory) {
                            ((StandardServiceRegistryImpl) serviceRegistry).destroy();
                        }
                    }
            );*/
        	StandardServiceRegistry standardRegistry = 
        		       new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        			Metadata metaData = 
        		        new MetadataSources(standardRegistry).getMetadataBuilder().build();
        			sessionFactory = metaData.getSessionFactoryBuilder().build();
        			
             //sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            logger.error("Initial SessionFactory creation failed." + ex);
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
        logger.info("Close session");
        sessionFactory.getCurrentSession().close();

    }
    /**
     *
     * @return
     */
    public static Session openSession(){
        return Session.getInstance(getSessionFactory()
        		.openSession());
        //.withOptions().tenantIdentifier( "school" )
    }
    /**
     *
     * @return
     */
    public static Session openCurrentSession() {
        if(getSessionFactory().getCurrentSession().isOpen()){
            return Session.getInstance(getSessionFactory().getCurrentSession());

        }
        else{
            return Session.getInstance(getSessionFactory().openSession());
        }

        //return getSessionFactory().getCurrentSession();
    }
}
