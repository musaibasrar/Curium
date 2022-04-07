package com.model.academicyear.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.model.academicyear.dto.Currentacademicyear;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

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
			
		} catch (HibernateException hibernateException) {transaction.rollback();
			
			error=hibernateException.getMessage();
		} finally {
			HibernateUtil.closeSession();
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
		} catch (HibernateException hibernateException) {transaction.rollback();
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return currentacademicyear;
	}

	
	

}