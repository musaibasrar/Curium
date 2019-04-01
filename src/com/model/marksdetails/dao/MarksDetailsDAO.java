package com.model.marksdetails.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import com.model.marksdetails.dto.Marks;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class MarksDetailsDAO {

	Session session;
	Transaction transaction;
	
	public MarksDetailsDAO() {
		session=HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
    public String addMarksMap(Map<String, List<Marks>> marksSubjectMap) {
		
		String output = "success";
		
		try{

			transaction = session.beginTransaction();
			for (Map.Entry<String,List<Marks>> entry : marksSubjectMap.entrySet()) {
				
				for (Marks marks : entry.getValue()) {
					session.save(marks);
				}
			}
			transaction.commit();
		}  catch(ConstraintViolationException  e){                                                       
		    transaction.rollback();
		    output = "Duplicate";
                }  catch (HibernateException hibernateException) {
                    transaction.rollback();
                    hibernateException.printStackTrace();
                   
            }
		finally {
			HibernateUtil.closeSession();
			return output;
		}
	}
	
	@SuppressWarnings("finally")
    public String addMarks(Marks marks) {
		
		boolean result = false;	
		String output = "success";
		
		try{
			transaction = session.beginTransaction();
				session.save(marks);
			transaction.commit();
		}  catch(ConstraintViolationException  e){                                                       
		    transaction.rollback();
		    output = "Duplicate";
                }  catch (HibernateException hibernateException) {
                    transaction.rollback();
                    hibernateException.printStackTrace();
            }
		finally {
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
		} catch (HibernateException hibernateException) {transaction.rollback();
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
			} catch (HibernateException hibernateException) {transaction.rollback();
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
				
			} catch (Exception e) {transaction.rollback();
				e.printStackTrace();
			}
			finally {
				HibernateUtil.closeSession();
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
			} catch (HibernateException hibernateException) {transaction.rollback();
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
				Query query = session.createQuery("From Marks where sid = '"+id+"' and academicyear = '"+currentAcademicYear+"' ORDER BY examid ASC");
				results = query.list();
				transaction.commit();
			} catch (HibernateException hibernateException) {transaction.rollback();
				hibernateException.printStackTrace();
			} finally {
				 HibernateUtil.closeSession();
				return results;
			}
		}

        public List<Marks> readMarks(List<Integer> studentIds, Integer subjectId, Integer examId, String academicYear) {

                List<Marks> marks = new ArrayList<Marks>();
                try {

                    transaction = session.beginTransaction();
                    Query query = session.createQuery("From Marks where subid = "+subjectId+" and examid = "+examId+" and academicyear = '"+academicYear+"' and sid IN (:sids)");
                    query.setParameterList("sids", studentIds);
                    marks = query.getResultList();
                    transaction.commit();
            } catch (HibernateException hibernateException) {transaction.rollback();
                    hibernateException.printStackTrace();
            } finally {
                     HibernateUtil.closeSession();
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
            
                    transaction.commit();
                    result = true;
                    
            } catch (Exception e) {transaction.rollback();
                    e.printStackTrace();
            }
            finally {
                    HibernateUtil.closeSession();
                    return result;
            }
    }

		public String addUpdateMarks(List<Marks> marksList) {
			
			boolean result = false;	
			String output = "success";
			
			try{
				transaction = session.beginTransaction();
				for (Marks marks : marksList) {
					session.saveOrUpdate(marks);
				}
				transaction.commit();
			}  catch(ConstraintViolationException  e){                                                       
			    transaction.rollback();
			    output = "Duplicate";
	                }  catch (HibernateException hibernateException) {
	                    transaction.rollback();
	                    hibernateException.printStackTrace();
	                   
	            }
			finally {
				HibernateUtil.closeSession();
				return output;
			}
		}

		public Marks readMarksPerStudent(Integer sid, Integer subjectId, Integer examId, String academicYear) {

            Marks marks = new Marks();
            try {

                transaction = session.beginTransaction();
                Query query = session.createQuery("From Marks where subid = "+subjectId+" and examid = "+examId+" and academicyear = '"+academicYear+"' and sid = "+sid+" ");
                marks = (Marks) query.uniqueResult();
                transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
                hibernateException.printStackTrace();
        } finally {
                 HibernateUtil.closeSession();
                return marks;
        }
    }
	
	
	
	
}
