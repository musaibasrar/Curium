package com.model.adminexpenses.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.model.adminexpenses.dto.Adminexpenses;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

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
		} catch (HibernateException hibernateException) {transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
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
			transaction.commit();

		} catch (HibernateException hibernateException) {transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			 HibernateUtil.closeSession();
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
		} catch (HibernateException hibernateException) {transaction.rollback();
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}

	}

	
	

}
