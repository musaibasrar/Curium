/**
 * 
 */
package org.ideoholic.curium.model.subjectdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.marksdetails.dto.SubjectGrade;
import org.ideoholic.curium.model.subjectdetails.dto.SubSubject;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;
import org.ideoholic.curium.model.subjectdetails.dto.Subjectmaster;
import org.ideoholic.curium.util.HibernateUtil;

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
	
	private static final Logger logger = LogManager.getLogger(SubjectDetailsDAO.class);
	
	public SubjectDetailsDAO() {
		session = HibernateUtil.openCurrentSession();
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public List<Subject> readListOfSubjects(int branchId, String examClass) {
		
		List<Subject> results = new ArrayList<Subject>();
		try {

			transaction = session.beginTransaction();
			results = (List<Subject>) session.createQuery("From Subject where examclass = '"+examClass+"' and branchid="+branchId)
					.list();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public Subject addSubject(Subject subject) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(subject);

			transaction.commit();
			
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
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
					.createQuery("delete from Subject where subid IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		
	}

	public Subject getSubjectDetails(Integer subid) {
		
		Subject subject = new Subject();
		try {

			transaction = session.beginTransaction();
			Query query =  session.createQuery("From Subject where id="+subid);
			subject = (Subject) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) {
			transaction.rollback(); 
			logger.error(hibernateException);
		} finally {
				HibernateUtil.closeSession();
			return subject;
		}
	}

	public Subjectmaster addSubjectMaster(Subjectmaster subject) {
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			session.save(subject);

			transaction.commit();
			
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return subject;
		}
		
	}

	public List<Subjectmaster> readListOfSubjectNames(int branchId) {
		
		List<Subjectmaster> results = new ArrayList<Subjectmaster>();
		try {

			transaction = session.beginTransaction();
			results = (List<Subjectmaster>) session.createQuery("From Subjectmaster where branchid="+branchId)
					.list();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public void deleteMultipleSubjects(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from Subjectmaster where subjectid IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		
	}

	public List<Subject> readAllSubjects(int branchId) {
		
		List<Subject> results = new ArrayList<Subject>();
		try {

			transaction = session.beginTransaction();
			results = (List<Subject>) session.createQuery("From Subject where branchid="+branchId)
					.list();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}
	
	
	public List<Subject> readAllSubjectsClassWise(int branchId, String examClass, String examName) {
		
		List<Subject> results = new ArrayList<Subject>();
		try {

			transaction = session.beginTransaction();
			results = (List<Subject>) session.createQuery("From Subject where examclass='"+examClass+"' and examname='"+examName+"' and branchid="+branchId)
					.list();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public SubSubject readSubSubject(int branchId, int subjectId, String subSubject) {
		SubSubject subsubject = new SubSubject();
		try {

			transaction = session.beginTransaction();
			Query query = session.createQuery("From SubSubject where subjectid='"+subjectId+"' and subsubjectname='"+subSubject+"'  and branchid = "+branchId+"");
			subsubject = (SubSubject) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return subsubject;
		}
	}

	public List<SubSubject> readListOfSubSubject(int branchId) {
		
		List<SubSubject> results = new ArrayList<SubSubject>();
		try {

			transaction = session.beginTransaction();
			results = (List<SubSubject>) session.createQuery("From SubSubject where branchid="+branchId)
					.list();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}

	public boolean addSubSubject(List<SubSubject> subSubjectList) {
		boolean result=false;
		try {
			// this.session = sessionFactory.openCurrentSession();
			transaction = session.beginTransaction();
			
			for (SubSubject subSubject : subSubjectList) {
				session.save(subSubject);
			}
			transaction.commit();
			result=true;
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return result;
		}
		
	}

	public void deleteMultipleSubSubject(List<Integer> ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from SubSubject where subjectid IN (:ids)");
			query.setParameterList("ids", ids);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		
	}

}
