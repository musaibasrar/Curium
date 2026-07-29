package org.ideoholic.curium.model.ratingdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.ratingdetails.dto.AssessmentRank;
import org.ideoholic.curium.model.ratingdetails.dto.HolisticRating;
import org.ideoholic.curium.util.HibernateUtil;

/**
 * DAO for Holistic Development Assessment Ratings
 * Duplicated from MarksDetailsDAO for independent assessment module
 */
public class RatingDetailsDAO {

	Session session;
	Transaction transaction;
	
	private static final Logger logger = LogManager.getLogger(RatingDetailsDAO.class);
	
	public RatingDetailsDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	public String addRatings(List<HolisticRating> ratingList) {
		String output = "success";
		try{
			transaction = session.beginTransaction();
			for (HolisticRating rating : ratingList) {
				session.save(rating);
			}
			transaction.commit();
		} catch (Exception hibernateException) { 
			transaction.rollback(); 
			logger.error(hibernateException);
			hibernateException.printStackTrace();
			output="Duplicate";
		}finally {
			HibernateUtil.closeSession();
			return output;
		}
	}

	public List<HolisticRating> readListOfRatings(List<Integer> ids) {
		List<HolisticRating> results = new ArrayList<HolisticRating>();
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("From HolisticRating where sid IN (:ids)");
			query.setParameterList("ids", ids);
			results = query.list();
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
		
	public List<HolisticRating> readListOfRatings(Integer id, int subjectId, int assessmentId, String academicYear) {
		List<HolisticRating> results = new ArrayList<HolisticRating>();
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("From HolisticRating where assessmentsubjectid="+subjectId+" and assessmentid="+assessmentId+" and academicyear='"+academicYear+"' and sid IN (:ids)");
			query.setParameter("ids", id);
			results = query.list();
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

	public List<HolisticRating> readListOfRatingsForAllAssessments(List<Integer> ids, String academicYear, int branchId) {
		List<HolisticRating> results = new ArrayList<HolisticRating>();
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("From HolisticRating where academicyear='"+academicYear+"' and branchid="+branchId+" and sid IN (:ids)");
			query.setParameterList("ids", ids);
			results = query.list();
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

	public void deleteMultiple(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from HolisticRating where ratingid IN (:ids)");
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

	public boolean updateRatings(List<HolisticRating> ratingsList) {
		try{
			transaction = session.beginTransaction();
			for (HolisticRating rating : ratingsList) {
				Query query = session.createQuery(
						"update HolisticRating set ratinggrade = :ratinggrade, ratingvalue = :ratingvalue where ratingid = :ratingid");
				query.setParameter("ratinggrade", rating.getRatinggrade());
				query.setParameter("ratingvalue", rating.getRatingvalue());
				query.setParameter("ratingid", rating.getRatingid());
				query.executeUpdate();
			}
			transaction.commit();
			return true;
		} catch (Exception hibernateException) { 
			transaction.rollback(); 
			logger.error(hibernateException);
			hibernateException.printStackTrace();
			return false;
		}finally {
			HibernateUtil.closeSession();
		}
	}

	public List<HolisticRating> getAssessmentSubjectDetails(String assessmentId, String classStudying, int branchId) {
		List<HolisticRating> results = new ArrayList<HolisticRating>();
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("Select distinct assessmentsubjectid From HolisticRating where assessmentid="+assessmentId+" and branchid="+branchId);
			results = query.list();
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

	public boolean addAssessmentRank(List<AssessmentRank> assessmentRankList) {
		try{
			transaction = session.beginTransaction();
			for (AssessmentRank assessmentRank : assessmentRankList) {
				session.save(assessmentRank);
			}
			transaction.commit();
			return true;
		} catch (Exception hibernateException) { 
			transaction.rollback(); 
			logger.error(hibernateException);
			hibernateException.printStackTrace();
			return false;
		}finally {
			HibernateUtil.closeSession();
		}
	}

	public List<AssessmentRank> readListOfAssessmentRankStudent(Integer ids, String academicYear, int branchId) {
		List<AssessmentRank> results = new ArrayList<AssessmentRank>();
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("From AssessmentRank where academicyear='"+academicYear+"' and branchid="+branchId+" and sid="+ids);
			results = query.list();
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

	public List<AssessmentRank> readListOfAssessmentRankAll(String academicYear, int assessmentId, String classSearch, int branchId) {
		List<AssessmentRank> results = new ArrayList<AssessmentRank>();
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("From AssessmentRank where academicyear='"+academicYear+"' and branchid="+branchId+" and assessmentid="+assessmentId);
			results = query.list();
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

	public void deleteMultipleAssessmentRank(List ids) {
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("delete from AssessmentRank where id IN (:ids)");
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

	/**
	 * Fetch all ratings for a student in an academic year
	 * Joined with subject master to get category and subject details
	 * Used for Assessment Progress Report generation
	 */
	public List<Object[]> fetchStudentProgressData(Integer studentId, String academicYear, int branchId) {
		List<Object[]> results = new ArrayList<>();
		try {
			transaction = session.beginTransaction();
			String hql = "SELECT hr, asm.subjectname, asm.category " +
					"FROM HolisticRating hr " +
					"LEFT JOIN AssessmentSubjectMaster asm ON hr.assessmentsubjectid = asm.subjectid " +
					"WHERE hr.sid = :studentId AND hr.academicyear = :academicYear AND hr.branchid = :branchId " +
					"ORDER BY asm.category ASC, asm.subjectname ASC";
			
			Query query = session.createQuery(hql);
			query.setParameter("studentId", studentId);
			query.setParameter("academicYear", academicYear);
			query.setParameter("branchId", branchId);
			
			results = query.list();
			transaction.commit();
		} catch (Exception hibernateException) { 
			transaction.rollback(); 
			logger.error("Error fetching student progress data for studentId=" + studentId, hibernateException);
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return results;
		}
	}

	/**
	 * Fetch distinct categories with their subjects for dynamic grouping
	 * Used to build the category list for Assessment Progress Report
	 */
	public List<Object[]> getCategoriesWithSubjects(int branchId) {
		List<Object[]> results = new ArrayList<>();
		try {
			transaction = session.beginTransaction();
			String hql = "SELECT DISTINCT asm.category, asm.subjectid, asm.subjectname " +
					"FROM AssessmentSubjectMaster asm " +
					"WHERE asm.branchid = :branchId AND asm.category IS NOT NULL " +
					"ORDER BY asm.category ASC, asm.subjectname ASC";
			
			Query query = session.createQuery(hql);
			query.setParameter("branchId", branchId);
			
			results = query.list();
			transaction.commit();
		} catch (Exception hibernateException) { 
			transaction.rollback(); 
			logger.error("Error fetching categories with subjects for branchId=" + branchId, hibernateException);
			hibernateException.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return results;
		}
	}

}