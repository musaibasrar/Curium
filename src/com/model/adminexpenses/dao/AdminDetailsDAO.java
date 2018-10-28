package com.model.adminexpenses.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import com.util.Session;
import org.hibernate.SessionFactory;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.adminexpenses.dto.Adminexpenses;
import com.util.HibernateUtil;

public class AdminDetailsDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	Transaction transaction1;
	SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(AdminDetailsDAO.class);

	public AdminDetailsDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public Adminexpenses create(Adminexpenses adminexpenses) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(adminexpenses);

			transaction.commit();
			System.out.println("in add3");
		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
			return adminexpenses;
		}
	}

	


	@SuppressWarnings({ "unchecked", "finally" })
	public List<Adminexpenses> readListOfExpenses(int branchId) {
		List<Adminexpenses> results = new ArrayList<Adminexpenses>();

		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Adminexpenses>) session.createQuery("From Adminexpenses where branchid="+branchId)
					.list();
			System.out.println("Adminexpenses " + results.size());
			transaction.commit();

		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
			// session.close();
			return results;
		}
	}

	


	public void deleteMultiple(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from Adminexpenses where idAdminExpenses IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}

	}

	
	

}
