package com.model.sendsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Session;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.parents.dto.Parents;
import com.util.HibernateUtil;

public class SmsDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	Transaction transaction1;
	
	private static final Logger logger = LogManager.getLogger(SmsDAO.class);	

	public SmsDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	public long countNumbers(String queryMain) {
		long totalNumbers = 0;
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();

			transaction = session.beginTransaction();
			Query query = session.createQuery("select count(*)" + queryMain
					+ "AND contactnumber IS NOT NULL and contactnumber <> '' ");
			totalNumbers = (long) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		return totalNumbers;
	}

	public List<Parents> getContactNumbers(int offset, int noOfRecords) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object> readListOfObjectsPaginationALL(int offset,
			int noOfRecords, String queryMain) {
		List<Object> results = new ArrayList<Object>();

		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery(queryMain);
			query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			results = query.list();

			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

}
