package com.model.academicyear.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.academicyear.dto.Currentacademicyear;
import com.model.adminexpenses.dto.Adminexpenses;
import com.model.student.dto.Student;
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
			//session.close();
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
		}
		// //session.close();
		return currentacademicyear;
	}

	
	

}
