package org.ideoholic.curium.model.studentdiary.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.diary.dto.Diary;
import org.ideoholic.curium.model.studentdiary.dto.StudentDiary;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class StudentDiaryDAO {
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(StudentDiaryDAO.class);
    
    public StudentDiaryDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public StudentDiary create(StudentDiary diary) {
		// TODO Auto-generated method stub
		try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.save(diary);
            transaction.commit();
            
        } catch (Exception hibernateException) { transaction.rollback();
        logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return diary;
        }
	}
	@SuppressWarnings({ "finally", "unchecked" })
	public  List<Object[]>  readListOfObjects(int offset, int noOfRecords, int branchId) {
		// TODO Auto-generated method stub
		List<Object[]> results = new ArrayList<Object[]>();
        try {
            
            transaction = session.beginTransaction();
            Query query = session.createQuery("select d.id,d.sid,s.name,d.classsec,d.academicyear,d.branchid,d.subject,d.message,d.createddate,d.userid from StudentDiary d JOIN Student s ON d.sid=s.sid where  d.branchid="+branchId+" order by d.createddate DESC");
            query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			results = query.list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
	}
	//readListOfParentObjects
	@SuppressWarnings({ "finally", "unchecked" })
	public  List<Object[]>  readListOfParentObjects(int offset, int noOfRecords, int branchId, int sid) {
		List<Object[]> results = new ArrayList<Object[]>();
        try {
            
            transaction = session.beginTransaction();
            Query query = session.createQuery("select d.id,d.sid,s.name,d.classsec,d.academicyear,d.branchid,d.subject,d.message,d.createddate,d.userid from StudentDiary d JOIN Student s ON d.sid=s.sid where  d.branchid="+branchId+" and d.sid='"+sid+"' order by d.createddate desc");
            query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			results = query.list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
            
            hibernateException.printStackTrace();
        } finally {
    			HibernateUtil.closeSession();
            return results;
        }
	}
	@SuppressWarnings({ "finally", "unchecked" })
	public int getNoOfRecords(int branchId, int sid) {
		// TODO Auto-generated method stub
		List<StudentDiary> results = new ArrayList<StudentDiary>();
		int noOfRecords = 0;
		try {
			transaction = session.beginTransaction();

						Query query = session.createQuery("select count(*) from StudentDiary where branchid="+branchId+" and sid='"+sid+"'");
			noOfRecords = Integer.parseInt(query.uniqueResult().toString()); 
			
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return noOfRecords;
		}
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public int getNoOfRecords(int branchId) {
		// TODO Auto-generated method stub
		List<StudentDiary> results = new ArrayList<StudentDiary>();
		int noOfRecords = 0;
		try {
			transaction = session.beginTransaction();

						Query query = session.createQuery("select count(*) from StudentDiary where branchid="+branchId+" ");
			noOfRecords = Integer.parseInt(query.uniqueResult().toString()); 
			
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return noOfRecords;
		}
	}

	public void deleteRecord(List<Integer> ids) {
		// TODO Auto-generated method stub
		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from StudentDiary as diary where diary.id IN (:ids)");
			query.setParameterList("ids", ids);
			
			query.executeUpdate();
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}	
	}

	public StudentDiary getMessage(long id) {
		StudentDiary diary = new StudentDiary();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from StudentDiary as diary where diary.id="
							+ id);
			diary = (StudentDiary) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); logger.error(hibernateException);
			
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return diary;
	}

}
