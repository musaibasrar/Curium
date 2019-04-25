package com.model.academicyear.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Session;
import com.util.Session.Transaction;
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
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public String create(Currentacademicyear currentacademicyear) {
		String error=null;
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(currentacademicyear);
			transaction.commit();
			
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			error=hibernateException.getMessage();
		} finally {
					HibernateUtil.closeSession();
			return error;
		}
	}

	

	public Currentacademicyear showYear() {
		Currentacademicyear currentacademicyear = new Currentacademicyear();
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Currentacademicyear as ca where ca.cayid = (select max(cayid) from Currentacademicyear) ");
			currentacademicyear = (Currentacademicyear) query.setCacheable(true).setCacheRegion("commonregion").uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return currentacademicyear;
	}

	
	

}
