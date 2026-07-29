package org.ideoholic.curium.model.assessmentdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

import org.ideoholic.curium.model.assessmentdetails.dto.HolisticAssessment;
import org.ideoholic.curium.model.assessmentdetails.dto.HolisticAssessmentSchedule;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

/**
 * DAO for Holistic Development Assessment
 * Duplicated from ExamDetailsDAO for independent assessment module
 */
public class HolisticAssessmentDAO {

	Session session;
	Transaction transaction;
	
	private static final Logger logger = LogManager.getLogger(HolisticAssessmentDAO.class);
	
	public HolisticAssessmentDAO() {
		session = HibernateUtil.openCurrentSession();
	}
	
	public HolisticAssessment addAssessment(HolisticAssessment assessment) {
		try {
			transaction = session.beginTransaction();
			session.save(assessment);
			transaction.commit();
		} catch (Exception hibernateException) { 
			transaction.rollback(); 
			logger.error(hibernateException);
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return assessment;
		}
	}

	public List<HolisticAssessment> readListOfAssessments(int branchId) {
		List<HolisticAssessment> results = new ArrayList<HolisticAssessment>();
		try {
			transaction = session.beginTransaction();
			results = (List<HolisticAssessment>) session.createQuery("From HolisticAssessment where branchid="+branchId)
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

	public void deleteMultiple(List<Integer> ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from HolisticAssessment where assessmentid IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { 
			transaction.rollback(); 
			logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
	}

	public boolean addAssessmentSchedule(List<HolisticAssessmentSchedule> assessmentScheduleList) {
		try {
			transaction = session.beginTransaction();
			for (HolisticAssessmentSchedule assessmentschedule : assessmentScheduleList) {
				session.save(assessmentschedule);
			}
			transaction.commit();
			return true;
		} catch (Exception e) { 
			transaction.rollback(); 
			logger.error(e);
			e.printStackTrace();
			return false;
		}finally {
			HibernateUtil.closeSession();
		}
	}

	public List<HolisticAssessmentSchedule> readListOfAssessmentSchedule(String assessmentName, String classes, String academicYear, int branchId) {
		List<HolisticAssessmentSchedule> results = new ArrayList<HolisticAssessmentSchedule>();
		try {
			transaction = session.beginTransaction();
			if ((assessmentName == null || assessmentName.trim().isEmpty())
					&& (classes == null || classes.trim().isEmpty())
					&& (academicYear == null || academicYear.trim().isEmpty())) {
				results = (List<HolisticAssessmentSchedule>) session
						.createQuery("From HolisticAssessmentSchedule where branchid=" + branchId)
						.list();
			} else {
				results = (List<HolisticAssessmentSchedule>) session
						.createQuery("From HolisticAssessmentSchedule where assessmentname='" + assessmentName
								+ "' and classes='" + classes + "' and academicyear='" + academicYear + "' and branchid="
								+ branchId)
						.list();
			}
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

	public void deleteMultipleSchedule(List<Integer> ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from HolisticAssessmentSchedule where idassessmentschedule IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { 
			transaction.rollback(); 
			logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}
	}

	public List<HolisticAssessmentSchedule> getAssessmentSchedule(String assessmentName, String examClass, String academicYear, int branchId) {
		List<HolisticAssessmentSchedule> results = new ArrayList<HolisticAssessmentSchedule>();
		try {
			transaction = session.beginTransaction();
			results = (List<HolisticAssessmentSchedule>) session.createQuery("From HolisticAssessmentSchedule where assessmentname='"+assessmentName+"' and classes='"+examClass+"' and academicyear='"+academicYear+"' and branchid="+branchId)
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

	public List<HolisticAssessmentSchedule> getAssessmentScheduleDetails(String academicYear, String classH, String assessment,
			int branchId) {
		List<HolisticAssessmentSchedule> listAssessmentSchedule = new ArrayList<HolisticAssessmentSchedule>();
		try {
			transaction = session.beginTransaction();
			listAssessmentSchedule = session.createQuery(
					"from HolisticAssessmentSchedule where classes = '" + classH + "' and academicyear = '" + academicYear
							+ "' and assessmentname = '" + assessment + "' and branchid=" + branchId + " ORDER BY date ASC")
					.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e);
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

		return listAssessmentSchedule;
	}

}
