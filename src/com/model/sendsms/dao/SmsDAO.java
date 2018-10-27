package com.model.sendsms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
		session = HibernateUtil.openSession();
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
		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		}
		// session.close();
		return totalNumbers;
	}

	public List<Parents> getContactNumbers(int offset, int noOfRecords) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Parents> readListOfObjectsPaginationALL(int offset,
			int noOfRecords, String queryMain) {
		List<Parents> results = new ArrayList<Parents>();

		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery(queryMain);
			query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			results = query.list();

			transaction.commit();

		} catch (HibernateException hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
			// session.close();
			return results;
		}
	}

}
