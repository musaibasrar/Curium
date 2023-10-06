package org.ideoholic.curium.model.diary.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.diary.dto.Diary;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;

public class diaryDAO {
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(diaryDAO.class);
    
    public diaryDAO() {
		session = HibernateUtil.openCurrentSession();
	}

	@SuppressWarnings("finally")
	public Diary create(Diary diary) {
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
            Query query = session.createQuery("select d.id,d.classsec,d.academicyear,d.branchid,d.subject,d.message,d.startdate,d.enddate,d.createddate,d.userid from Diary d where  branchid="+branchId);
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
	public  List<Object[]>  readListOfParentObjects(int offset, int noOfRecords, int branchId, String classsec) {
		List<Object[]> results = new ArrayList<Object[]>();
        try {
            
            transaction = session.beginTransaction();
            Query query = session.createQuery("select d.id,d.classsec,d.academicyear,d.branchid,d.subject,d.message,d.startdate,d.enddate,d.createddate,d.userid from Diary d where  branchid="+branchId+" and classsec='"+classsec+"'");
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
	public int getNoOfRecords(int branchId) {
		// TODO Auto-generated method stub
		List<Diary> results = new ArrayList<Diary>();
		int noOfRecords = 0;
		try {
			transaction = session.beginTransaction();

						Query query = session.createQuery("select count(*) from Diary where branchid="+branchId);
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
					.createQuery("delete from Diary as diary where diary.id IN (:ids)");
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
