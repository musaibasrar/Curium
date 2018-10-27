package com.model.academicyear.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.academicyear.dto.Currentacademicyear;
import com.util.HibernateUtil;

public class YearDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	Transaction transaction1;
	
	private static final Logger logger = LogManager.getLogger(YearDAO.class);
	

	public YearDAO() {
		session = HibernateUtil.openSession();
	}

	@SuppressWarnings("finally")
	public String create(Currentacademicyear currentacademicyear) {
		String error=null;
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(currentacademicyear);
			transaction.commit();
			
		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			error=hibernateException.getMessage();
		} finally {
			session.close();
			return error;
		}
	}

	

	public Currentacademicyear showYear() {
		Currentacademicyear currentacademicyear = new Currentacademicyear();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Currentacademicyear as ca where ca.cayid = (select max(cayid) from Currentacademicyear) ");
			currentacademicyear = (Currentacademicyear) query.uniqueResult();
			transaction.commit();
		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		}
		// session.close();
		return currentacademicyear;
	}

	
	

}
