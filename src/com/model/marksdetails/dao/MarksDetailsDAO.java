package com.model.marksdetails.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.marksdetails.dto.Marks;
import com.util.HibernateUtil;

public class MarksDetailsDAO {

	Session session;
	Transaction transaction;
	
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
			
		}  catch (HibernateException hibernateException) {
			transaction.rollback();
			hibernateException.printStackTrace();
		} 
		finally {
			//session.close();
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
			 //session.close();
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
				 //session.close();
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
				//session.close();
				return result;
			}
		}

		public boolean deleteMultiple(List ids) {
			boolean result= false;
			try {
				transaction = session.beginTransaction();
				Query query = session.createQuery("delete from Marks  where marksid IN (:ids)");
				query.setParameterList("ids", ids);
				query.executeUpdate();
				transaction.commit();
				result=true;
			} catch (HibernateException hibernateException) {
				hibernateException.printStackTrace();
				result=false;
			}
			return result;
		}

		public List<Marks> readMarksforStudent(int id, String currentAcademicYear) {
			List<Marks> results = new ArrayList<Marks>();
			try {

				transaction = session.beginTransaction();
				Query query = session.createQuery("From Marks where sid = '"+id+"' and academicyear = '"+currentAcademicYear+"' ORDER BY examid ASC");
				results = query.list();
				transaction.commit();
			} catch (HibernateException hibernateException) {
				transaction.rollback();
				hibernateException.printStackTrace();
			} finally {
				 //session.close();
				return results;
			}
		}

        public Marks readMarks(Integer sid, Integer subjectId, Integer examId, String academicYear) {

                Marks marks = new Marks();
                try {

                    transaction = session.beginTransaction();
                    Query query = session.createQuery("From Marks where sid = '"+sid+"' and subid = "+subjectId+" and examid = "+examId+" and academicyear = '"+academicYear+"' ");
                    marks = (Marks) query.uniqueResult();
                    transaction.commit();
            } catch (HibernateException hibernateException) {
                    transaction.rollback();
                    hibernateException.printStackTrace();
            } finally {
                     //session.close();
                    return marks;
            }
        }

        public boolean updateMarks(Map<Integer, Integer> idsMarks) {
            boolean result = false;         
            
            try{
                    transaction = session.beginTransaction();
                    for (Map.Entry<Integer, Integer> mapIdsMarks: idsMarks.entrySet()) {
                            Query query = session.createQuery("update Marks set marksobtained = "+mapIdsMarks.getValue()+" where marksid="+mapIdsMarks.getKey()+"");
                            query.executeUpdate();
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
                    //session.close();
                    return result;
            }
    }
	
	
	
	
}
