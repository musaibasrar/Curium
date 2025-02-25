package org.ideoholic.curium.model.diary.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dao.YearRepository;
import org.ideoholic.curium.model.diary.dto.Diary;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.util.HibernateUtil;
import org.ideoholic.curium.util.QueryUtil;
import org.ideoholic.curium.util.Session;
import org.ideoholic.curium.util.Session.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class diaryDAO {
	@Autowired
    private DiaryRepository diaryRepo;

    @Autowired
    private QueryUtil queryUtil;
    
    @Transactional
	public Diary create(Diary diary) {
		try {
			diaryRepo.save(diary);
            
        } catch (Exception hibernateException) { 
        	log.error(hibernateException.getMessage(), hibernateException);
            hibernateException.printStackTrace();
            throw hibernateException;
        } 
		 return diary;
	}
	@SuppressWarnings({ "finally", "unchecked" })
	public  List<Object[]>  readListOfObjects(int offset, int noOfRecords, int branchId) {
		// TODO Auto-generated method stub
		List<Object[]> results = new ArrayList<Object[]>();
		Session session = HibernateUtil.openCurrentSession();
		Transaction transaction = null;
        try {
            
            transaction = session.beginTransaction();
            Query query = session.createQuery("select d.id,d.classsec,d.academicyear,d.branchid,d.subject,d.message,d.startdate,d.enddate,d.createddate,d.userid from Diary d where  branchid="+branchId);
            query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			results = query.list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(),hibernateException);
            
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
		Session session = HibernateUtil.openCurrentSession();
		Transaction transaction = null;
        try {
            
            transaction = session.beginTransaction();
            Query query = session.createQuery("select d.id,d.classsec,d.academicyear,d.branchid,d.subject,d.message,d.startdate,d.enddate,d.createddate,d.userid from Diary d where  branchid="+branchId+" and classsec='"+classsec+"'");
            query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			results = query.list();
            transaction.commit();
        } catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(),hibernateException);
            
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
		Session session = HibernateUtil.openCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

						Query query = session.createQuery("select count(*) from Diary where branchid="+branchId);
			noOfRecords = Integer.parseInt(query.uniqueResult().toString()); 
			
			transaction.commit();

		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(),hibernateException);
			
			hibernateException.printStackTrace();

		} finally {
				HibernateUtil.closeSession();
			return noOfRecords;
		}
	}

	public void deleteRecord(List<Integer> ids) {
		Session session = HibernateUtil.openCurrentSession();
		Transaction transaction = null;
		// TODO Auto-generated method stub
		try {
			transaction = session.beginTransaction();
			
			
			Query query = session
					.createQuery("delete from Diary as diary where diary.id IN (:ids)");
			query.setParameterList("ids", ids);
			
			query.executeUpdate();
			
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(),hibernateException);
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		}	
	}

	public Diary getMessage(long id) {
		Diary diary = new Diary();
		Session session = HibernateUtil.openCurrentSession();
		Transaction transaction = null;
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Diary as diary where diary.id="
							+ id);
			diary = (Diary) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException.getMessage(),hibernateException);
			
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return diary;
	}
	/*public Diary getMessage(String id) {
		Diary diary = new Diary();
		try {
			// this.session =
			// HibernateUtil.getSessionFactory().openCurrentSession();

			transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Diary as diary where diary.id="
							+ id);
			diary = (Diary) query.uniqueResult();
			transaction.commit();
		} catch (Exception hibernateException) { transaction.rollback(); log.error(hibernateException);
			
			hibernateException.printStackTrace();
		}finally {
			HibernateUtil.closeSession();
		 }
		return diary;
	}*/

}
