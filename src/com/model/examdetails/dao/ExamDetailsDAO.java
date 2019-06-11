package com.model.examdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

import com.model.examdetails.dto.Exams;
import com.model.examdetails.dto.Examschedule;
import com.model.subjectdetails.dto.Subject;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class ExamDetailsDAO {

	Session session;
	Transaction transaction;
	
	private static final Logger logger = LogManager.getLogger(ExamDetailsDAO.class);
	
	public ExamDetailsDAO() {
		session = HibernateUtil.openCurrentSession();
	}
	
	

	public Exams addExams(Exams exams) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(exams);

			transaction.commit();
			System.out.println("in add3");
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
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

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
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
					.createQuery("delete from Exams where exid IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
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
			} catch (Exception e) { transaction.rollback(); logger.error(e);
				e.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			}
			return false;
	}



	public List<Examschedule> readListOfExamSchedule(int branchId) {
		
		List<Examschedule> results = new ArrayList<Examschedule>();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();
			transaction = session.beginTransaction();

			results = (List<Examschedule>) session.createQuery("From Examschedule where branchid = "+branchId)
					.list();
			transaction.commit();

		} catch (Exception hibernateException) {
			transaction.rollback(); 
			logger.error(hibernateException);
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
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
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
	}

	public List<Examschedule> getExamScheduleDetails(String academicYear,
			String classH, String exam, int branchId) {
		List<Examschedule> listExamSchedule = new ArrayList<Examschedule>();
		try {
			transaction = session.beginTransaction();
			listExamSchedule = session.createQuery("from Examschedule where classes = '"+classH+"' and academicyear = '"+academicYear+"' and examname = '"+exam+"' and branchid="+branchId+" ORDER BY date ASC").list();
			transaction.commit();
		} catch (Exception e) { transaction.rollback(); logger.error(e);
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
		
		return listExamSchedule;
	}



	public Exams getExamDetails(Integer examid) {
		
		Exams exam = new Exams();
		try {

			transaction = session.beginTransaction();
			Query query =  session.createQuery("From Exams where id="+examid);
			exam = (Exams) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) {
			transaction.rollback(); 
			logger.error(hibernateException);
		} finally {
				HibernateUtil.closeSession();
			return exam;
		}
	}
}
