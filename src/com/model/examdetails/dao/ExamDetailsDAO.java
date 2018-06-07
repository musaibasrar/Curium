package com.model.examdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.adminexpenses.dto.Adminexpenses;
import com.model.examdetails.dto.Exams;
import com.model.examdetails.dto.Examschedule;
import com.model.student.dto.Student;
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



	public List<Exams> readListOfExams(int branchId) {
		List<Exams> results = new ArrayList<Exams>();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Exams>) session.createQuery("From Exams where branchid="+branchId)
					.list();
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



	public boolean addExamSchedule(List<Examschedule> examScheduleList) {

			try {
				transaction = session.beginTransaction();
				for (Examschedule examschedule : examScheduleList) {
					session.save(examschedule);
				}
				transaction.commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
			return false;
	}



	public List<Examschedule> readListOfExamSchedule(int branchId) {
		
		List<Examschedule> results = new ArrayList<Examschedule>();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Examschedule>) session.createQuery("From Examschedule where branchid"+branchId)
					.list();
			transaction.commit();

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();

		} finally {
			// session.close();
			return results;
		}
	}



	public void deleteExamSchedule(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from Examschedule where idexamschedule IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
		}finally{
			session.close();
		}
		
	}

	public List<Examschedule> getExamScheduleDetails(String academicYear,
			String classH, String exam, int branchId) {
		List<Examschedule> listExamSchedule = new ArrayList<Examschedule>();
		try {
			transaction = session.beginTransaction();
			listExamSchedule = session.createQuery("from Examschedule where classes like '"+classH+"-%' and academicyear = '"+academicYear+"' and examname = '"+exam+"' and branchid="+branchId+" ORDER BY date ASC").list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return listExamSchedule;
	}
}
