package com.model.examdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.adminexpenses.dto.Adminexpenses;
import com.model.examdetails.dto.Exams;
import com.util.HibernateUtil;

public class ExamDetailsDAO {

	Session session;
	Transaction transaction;
	
	public ExamDetailsDAO() {
		session = HibernateUtil.openSession();
	}
	
	

	public Exams addExams(Exams exams) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(exams);

			transaction.commit();
			System.out.println("in add3");
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			session.close();
			return exams;
		}
		
	}



	public List<Exams> readListOfExams() {
		List<Exams> results = new ArrayList<Exams>();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Exams>) session.createQuery("From Exams")
					.list();
			System.out.println("Exams " + results.size());
			transaction.commit();

		} catch (HibernateException hibernateException) {
			transaction.rollback();
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
					.createQuery("delete from Exams where exid IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}

	}
		
	

}
