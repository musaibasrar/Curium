package org.ideoholic.curium.model.marksdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.marksdetails.dto.ExamRank;
import org.ideoholic.curium.model.marksdetails.dto.Marks;
import org.ideoholic.curium.model.marksdetails.dto.MarksGrade;
import org.ideoholic.curium.model.marksdetails.dto.SubjectGrade;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class MarksDetailsDAO {

	Session session;
	Transaction transaction;
	
	private static final Logger logger = LogManager.getLogger(MarksDetailsDAO.class);
	
	public MarksDetailsDAO() {
		session=HibernateUtil.openCurrentSession();
	}

	public String addMarks(List<Marks> marksList) {
		
		String output = "success";
		
		try{
			transaction = session.beginTransaction();
			
			for (Marks marks : marksList) {
				
				session.save(marks);
				
			}
			
			transaction.commit();
			
		}  catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
			output="Duplicate";
		}finally {
				HibernateUtil.closeSession();
			return output;
		}
		
		
	}

	public List<Marks> readListOfMarks(List<Integer> ids) {
		List<Marks> results = new ArrayList<Marks>();
		try {

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("From Marks where sid IN (:ids)");
			query.setParameterList("ids", ids);
			/*query.setParameter("subject", subject);
			query.setParameter("exam", exam);*/
			//query.executeUpdate();
			results = query.list();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		} finally {
				HibernateUtil.closeSession();
			return results;
		}
	}
		
		public List<Marks> readListOfMarks(Integer id) {
			List<Marks> results = new ArrayList<Marks>();
			try {

				transaction = session.beginTransaction();
				Query query = session
						.createQuery("From Marks where sid IN (:ids)");
				query.setParameter("ids", id);
				/*query.setParameter("subject", subject);
				query.setParameter("exam", exam);*/
				//query.executeUpdate();
				results = query.list();
				transaction.commit();
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();
			} finally {
					HibernateUtil.closeSession();
				return results;
			}
	}

		public boolean updateMarks(List<Marks> marksList) {
			boolean result = false;		
			
			try{
				transaction = session.beginTransaction();
				for (Marks marks : marksList) {
					session.update(marks);
				}
	        	result = true;
				transaction.commit();
				
			}  catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();
			} 
			finally {
					HibernateUtil.closeSession();
				return result;
			}
		}

		public boolean deleteMultiple(List ids, List studentListids) {
			boolean result= false;
			try {
				transaction = session.beginTransaction();
				Query query = session.createQuery("delete from Marks  where marksid IN (:ids) and sid IN (:studentids)");
				query.setParameterList("ids", ids);
				query.setParameterList("studentids", studentListids);
				query.executeUpdate();
				transaction.commit();
				result=true;
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				hibernateException.printStackTrace();
				result=false;
			}finally {
				HibernateUtil.closeSession();
			}
			return result;
		}

		public List<Marks> readMarksforStudent(int id, String currentAcademicYear, int examId) {
			List<Marks> results = new ArrayList<Marks>();
			try {

				transaction = session.beginTransaction();
				Query query = session.createQuery("From Marks where sid = '"+id+"' and academicyear = '"+currentAcademicYear+"' and examid = '"+examId+"' ORDER BY examid,subid ASC");
				results = query.list();
				transaction.commit();
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();
			} finally {
					HibernateUtil.closeSession();
				return results;
			}
		}

		public List<Marks> readMarksPerExam(Integer sid, Integer exid, String currentAcademicYear) {
			
			List<Marks> results = new ArrayList<Marks>();
			try {

				transaction = session.beginTransaction();
				Query query = session.createQuery("From Marks where sid = "+sid+" and examid = "+exid+" and academicyear = '"+currentAcademicYear+"' ORDER BY subid ASC");
				results = query.list();
				transaction.commit();
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();
			} finally {
					HibernateUtil.closeSession();
				return results;
			}
			
		}

		public List<MarksGrade> readMarksGrade(int branchid) {
			List<MarksGrade> results = new ArrayList<MarksGrade>();
			try {

				transaction = session.beginTransaction();
				Query query = session.createQuery("From MarksGrade where branchid = "+branchid+"");
				results = query.list();
				transaction.commit();
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();
			} finally {
					HibernateUtil.closeSession();
				return results;
			}
		}
	
		public boolean saveMarks(List<ExamRank> examRankList) {
			boolean result = false;		
			
			try{
				transaction = session.beginTransaction();
				for (ExamRank examrank : examRankList) {
					Query query = session.createQuery("From ExamRank where sid="+examrank.getSid()+" and examid="+examrank.getExamid()+" and academicyear="+examrank.getAcademicyear()+" and branchid = "+examrank.getBranchid()+"");
					ExamRank results = (ExamRank) query.uniqueResult();
					if(results==null) {
						session.save(examrank);	
					}else {
						Query queryUpdate = session.createSQLQuery("update ExamRank set marksobtained="+examrank.getMarksobtained()+" where id = "+examrank.getId()+"");
						queryUpdate.executeUpdate();
					}
					
				}
	        	result = true;
				transaction.commit();
				
			}  catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();
			} 
			finally {
					HibernateUtil.closeSession();
				return result;
			}
		}

		public ExamRank getExamRank(int sid, Integer exid, String currentAcademicYear, int branchid) {
			ExamRank ex= new ExamRank();
			try {
				transaction = session.beginTransaction();
				Query query = session.createQuery("From ExamRank where sid="+sid+" and examid="+exid+" and academicyear='"+currentAcademicYear+"' and branchid="+branchid+"");
				ex = (ExamRank) query.uniqueResult(); 
				transaction.commit();
			} catch (Exception e) { transaction.rollback(); logger.error(e);
				e.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			}
			return ex;
		}

		public List<SubjectGrade> readSubjectGrade(int branchid, int examid, String classSelected) {
			List<SubjectGrade> results = new ArrayList<SubjectGrade>();
			try {

				transaction = session.beginTransaction();
				Query query = session.createQuery("From SubjectGrade where classsec='"+classSelected+"' and branchid = "+branchid+"");
				results = query.list();
				transaction.commit();
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();
			} finally {
					HibernateUtil.closeSession();
				return results;
			}
		}

		public List<ExamRank> getListExamRank(List<Integer> studentsIds, Integer exid, String currentAcademicYear, int branchId) {
			List<ExamRank> exmaRankList = new  ArrayList<ExamRank>();
			try {
				transaction = session.beginTransaction();
				Query query = session.createQuery("From ExamRank where examid="+exid+" and academicyear='"+currentAcademicYear+"' and branchid="+branchId+" and sid IN (:ids)");
				query.setParameterList("ids", studentsIds);
				exmaRankList = query.getResultList();
				transaction.commit();
			} catch (Exception e) { transaction.rollback(); logger.error(e);
				e.printStackTrace();
			}finally {
				HibernateUtil.closeSession();
			}
			return exmaRankList;
			
		}

		public boolean updateExamRank(List<ExamRank> listExamRank) {
			boolean result = false;		
			
			try{
				transaction = session.beginTransaction();
				for (ExamRank examrank : listExamRank) {
						Query queryUpdate = session.createSQLQuery("update ExamRank set rank="+examrank.getRank()+" where id = "+examrank.getId()+"");
						queryUpdate.executeUpdate();
				}
	        	result = true;
				transaction.commit();
				
			}  catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();
			} 
			finally {
					HibernateUtil.closeSession();
				return result;
			}
		}
		
		public List<Marks> readListOfMarksPerSubject(List<Integer> ids, int subjectId,int exid) {
			List<Marks> results = new ArrayList<Marks>();
			try {

				transaction = session.beginTransaction();
				Query query = session
						.createQuery("From Marks where subid="+subjectId+" and examid="+exid+" and sid IN (:ids)");
				query.setParameterList("ids", ids);
				/*query.setParameter("subject", subject);
				query.setParameter("exam", exam);*/
				//query.executeUpdate();
				results = query.list();
				transaction.commit();
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();
			} finally {
					HibernateUtil.closeSession();
				return results;
			}
		}
		
	public List<Marks> readMarksforStudentPerSubject(int sid, String currentAcademicYear, int subid) {
			
			List<Marks> results = new ArrayList<Marks>();
			try {

				transaction = session.beginTransaction();
				Query query = session.createQuery("From Marks where sid = "+sid+" and subid = "+subid+" and academicyear = '"+currentAcademicYear+"' ORDER BY subid ASC");
				results = query.list();
				transaction.commit();
			} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
				
				hibernateException.printStackTrace();
			} finally {
					HibernateUtil.closeSession();
				return results;
			}
			
		}
	
	public List<Marks> readMarksPerExamPerSubject(Integer sid, String currentAcademicYear, List<Integer> exid) {
		
		List<Marks> results = new ArrayList<Marks>();
		try {

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("From Marks where sid = "+sid+" and academicyear = '"+currentAcademicYear+"' and examid IN (:ids) ORDER BY subid ASC");
			query.setParameterList("ids", exid);
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
