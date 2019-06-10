package com.model.marksdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Session;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.marksdetails.dto.Marks;
import com.util.HibernateUtil;

public class MarksDetailsDAO {

	Session session;
	Transaction transaction;
	
	private static final Logger logger = LogManager.getLogger(MarksDetailsDAO.class);
	
	public MarksDetailsDAO() {
		session=HibernateUtil.openCurrentSession();
	}

	public String addMarks(List<Marks> marksList) {
		
		boolean result = false;	
		String output = "success";
		
		try{
			transaction = session.beginTransaction();
			
			for (Marks marks : marksList) {
				
				session.save(marks);
				
			}
			
			transaction.commit();
			
		}  catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
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

		public List<Marks> readMarksforStudent(int id, String currentAcademicYear) {
			List<Marks> results = new ArrayList<Marks>();
			try {

				transaction = session.beginTransaction();
				Query query = session.createQuery("From Marks where sid = '"+id+"' and academicyear = '"+currentAcademicYear+"' ORDER BY examid,subid ASC");
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
	
	
	
	
}
