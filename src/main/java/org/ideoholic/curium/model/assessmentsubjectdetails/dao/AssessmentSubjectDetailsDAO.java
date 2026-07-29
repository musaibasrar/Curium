package org.ideoholic.curium.model.assessmentsubjectdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubject;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectMaster;
import org.ideoholic.curium.util.HibernateUtil;

/**
 * DAO for Assessment Subject Details
 * Duplicated from SubjectDetailsDAO for independent assessment module
 */
public class AssessmentSubjectDetailsDAO {

	Session session;
	Transaction transaction;
	
	private static final Logger logger = LogManager.getLogger(AssessmentSubjectDetailsDAO.class);
	
	public AssessmentSubjectDetailsDAO() {
		session = HibernateUtil.openCurrentSession();
	}
	
	public List<AssessmentSubject> readListOfAssessmentSubjects(int branchId, String assessmentClass) {
		List<AssessmentSubject> results = new ArrayList<AssessmentSubject>();
		try {
			transaction = session.beginTransaction();
			results = (List<AssessmentSubject>) session.createQuery("From AssessmentSubject where assessmentclass = '"+assessmentClass+"' and branchid="+branchId)
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

	public AssessmentSubject addAssessmentSubject(AssessmentSubject subject) {
		try {
			transaction = session.beginTransaction();
			session.save(subject);
			transaction.commit();
		} catch (Exception hibernateException) { 
			transaction.rollback(); 
			logger.error(hibernateException);
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return subject;
		}
	}

	public void deleteMultiple(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from AssessmentSubject where assessmentsubjectid IN (:ids)");
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

	public AssessmentSubject getAssessmentSubjectDetails(Integer subid) {
		AssessmentSubject subject = new AssessmentSubject();
		try {
			transaction = session.beginTransaction();
			Query query =  session.createQuery("From AssessmentSubject where assessmentsubjectid="+subid);
			subject = (AssessmentSubject) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { 
			transaction.rollback(); 
			logger.error(hibernateException);
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return subject;
		}
	}

	public List<AssessmentSubject> readAllAssessmentSubjects(int branchId) {
		List<AssessmentSubject> results = new ArrayList<AssessmentSubject>();
		try {
			transaction = session.beginTransaction();
			results = (List<AssessmentSubject>) session.createQuery("From AssessmentSubject where branchid="+branchId)
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

	public AssessmentSubjectMaster addSubjectMaster(AssessmentSubjectMaster subjectMaster) {
		try {
			transaction = session.beginTransaction();
			session.save(subjectMaster);
			transaction.commit();
		} catch (Exception hibernateException) { 
			transaction.rollback(); 
			logger.error(hibernateException);
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return subjectMaster;
		}
	}

	public List<AssessmentSubjectMaster> readListOfSubjectNames(int branchId) {
		List<AssessmentSubjectMaster> results = new ArrayList<AssessmentSubjectMaster>();
		try {
			transaction = session.beginTransaction();
			results = (List<AssessmentSubjectMaster>) session.createQuery("From AssessmentSubjectMaster where branchid="+branchId)
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

	public void deleteMultipleSubjectMaster(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from AssessmentSubjectMaster where subjectid IN (:ids)");
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

}
