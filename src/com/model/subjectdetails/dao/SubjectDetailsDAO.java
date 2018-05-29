/**
 * 
 */
package com.model.subjectdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.subjectdetails.dto.Subject;
import com.util.HibernateUtil;

/**
 * @author Musaib_2
 *
 */
public class SubjectDetailsDAO {

	Session session;
	/**
	 * * Hibernate Session Variable
	 */
	Transaction transaction;
	
	
	public SubjectDetailsDAO() {
		session = HibernateUtil.openSession();
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public List<Subject> readListOfSubjects() {
		
		List<Subject> results = new ArrayList<Subject>();
		try {

			transaction = session.beginTransaction();
			results = (List<Subject>) session.createQuery("From Subject")
					.list();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			 session.close();
			return results;
		}
	}

	public Subject addSubject(Subject subject) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(subject);

			transaction.commit();
			
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			session.close();
			return subject;
		}
		
	}

	public void deleteMultiple(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from Subject where subid IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}
		
	}

}
