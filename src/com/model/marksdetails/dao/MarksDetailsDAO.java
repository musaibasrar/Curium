package com.model.marksdetails.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.marksdetails.dto.Marks;
import com.util.HibernateUtil;

public class MarksDetailsDAO {

	Session session;
	Transaction transaction;
	
	public MarksDetailsDAO() {
		session=HibernateUtil.openSession();
	}

	public String addMarks(List<Marks> marksList) {
		
		boolean result = false;	
		String output = "success";
		
		try{
			transaction = session.beginTransaction();
			
			for (Marks marks : marksList) {
				try{
				session.save(marks);
				}catch(HibernateException e){
					//e.printStackTrace();
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					e.printStackTrace(pw);
					output=sw.toString();
					//output=e.getMessage();
				}
			}
			
			transaction.commit();
			
		}  catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			hibernateException.printStackTrace(pw);
			output=sw.toString();
		} catch(Exception e){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			output=sw.toString();
		}
		finally {
			session.close();
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
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} finally {
			 session.close();
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
			} catch (HibernateException hibernateException) {
				transaction.rollback();
				hibernateException.printStackTrace();
			} finally {
				 session.close();
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
				
			}  catch (HibernateException hibernateException) {
				transaction.rollback();
				hibernateException.printStackTrace();
			} catch(Exception e){
				e.printStackTrace();
			}
			finally {
				session.close();
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
			} catch (HibernateException hibernateException) {
				hibernateException.printStackTrace();
				result=false;
			}
			return result;
		}

		public List<Marks> readMarksforStudent(int id) {
			List<Marks> results = new ArrayList<Marks>();
			try {

				transaction = session.beginTransaction();
				Query query = session
						.createQuery("From Marks where sid = '"+id+"' ORDER BY examid ASC");
				
				/*query.setParameter("subject", subject);
				query.setParameter("exam", exam);*/
				//query.executeUpdate();
				results = query.list();
				transaction.commit();
			} catch (HibernateException hibernateException) {
				transaction.rollback();
				hibernateException.printStackTrace();
			} finally {
				 session.close();
				return results;
			}
		}
	
	
	
	
}
