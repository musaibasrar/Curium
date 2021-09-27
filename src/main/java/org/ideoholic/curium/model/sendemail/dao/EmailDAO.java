package com.model.sendemail.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Session;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.parents.dto.Parents;
import com.util.HibernateUtil;

public class EmailDAO {
	Session session = null;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction = null;
	/**
	 * * Hibernate Transaction Variable
	 */
	Transaction transaction1;
	
	private static final Logger logger = LogManager.getLogger(EmailDAO.class);
	
	public EmailDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	
	public long countEmails(String queryMain) {
		long totalNumbers = 0;
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			
			transaction = session.beginTransaction();
			Query query = session.createQuery("select count(*)" +queryMain+ "AND email IS NOT NULL AND email <> '' ");
			totalNumbers = (long) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
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

			Query query = session
					.createQuery(queryMain);
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
