/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ideoholic.curium.util;

import java.util.Set;

import javax.persistence.Entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.ideoholic.curium.model.multitenant.MultiTenantConnectionProviderImpl;
import org.ideoholic.curium.model.multitenant.SchemaResolver;
import org.reflections.Reflections;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

	static {
		try {
			Configuration configuration = new Configuration();

			// Get the Reflections object for scanning the package
			Reflections reflections = new Reflections("org.ideoholic.curium");

			// Scan for all classes annotated with @Entity
			Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);

			// Add all found classes to Hibernate configuration
			for (Class<?> entityClass : entityClasses) {
				configuration.addAnnotatedClass(entityClass);
			}

			// Set multi-tenancy properties
			configuration.setCurrentTenantIdentifierResolver(new SchemaResolver());
			configuration.setProperty("hibernate.multiTenancy", "SCHEMA");
			configuration.setProperty("hibernate.multi_tenant_connection_provider",
					MultiTenantConnectionProviderImpl.class.getName());

			// Other Hibernate properties (dialect, hbm2ddl, etc.)
			configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
			configuration.setProperty("hibernate.show_sql", "true");
			// Uncomment if you want Hibernate to manage schema updates
			// configuration.setProperty("hibernate.hbm2ddl.auto", "update");

			// Build the SessionFactory
			configuration.configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception ex) {
			// Log the exception if session factory creation fails
			logger.error("Initial SessionFactory creation failed:", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Get the SessionFactory for Hibernate sessions.
	 *
	 * @return the SessionFactory object.
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Safely closes the current session if open.
	 */
	public static void closeSession() {
		org.hibernate.Session session = getSessionFactory().getCurrentSession();
		if (session != null && session.isOpen()) {
			try {
				session.close();
				if(session.isConnected()) session.disconnect();
			} catch (Exception e) {
				logger.error("Failed to close session", e);
			}
		}
	}

	/**
	 *
	 * @return
	 */
	public static Session openSession() {
		return openCurrentSession();
	}

	/**
	 *
	 * @return
	 */
	public static Session openCurrentSession() {
		if (getSessionFactory().getCurrentSession().isOpen()) {
			return Session.getInstance(getSessionFactory().getCurrentSession());
		} else {
			return Session.getInstance(getSessionFactory().openSession());
		}
	}
}