package com.model.sendemail.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.academicyear.dto.Currentacademicyear;
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
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		}
		// //session.close();
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
			

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// //session.close();
			return results;
		}
	}

	
	

}
