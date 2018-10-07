package com.model.qualification.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.examlevels.dto.Examleveldetails;
import com.model.qualification.dto.Qualification;
import com.util.HibernateUtil;

public class QualificationDAO {

	
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    

	public QualificationDAO() {
		session = HibernateUtil.openCurrentSession();
	}


	

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Qualification> readListOfObjects() {
		
		List<Qualification> results = new ArrayList<Qualification>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Qualification>) session.createQuery("From Qualification").setCacheable(true).setCacheRegion("commonregion").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return results;
        }
	}


	public void deleteMultiple(List ids) {
		try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Department where depid IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }
		
	}


    public boolean addQualification(Qualification qual) {
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            session.save(qual);
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return result;
        }
    }




    public boolean deleteMultipleQualification(List<Integer> ids) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Qualification where idqualification IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }
        return result;
    }




    public boolean updateMultipleQualification(List<Qualification> qualList) {

        boolean result = false;
        try {
            transaction = session.beginTransaction();
            for (Qualification qualLevel : qualList) {
                session.update(qualLevel);
            }
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }
        return result;
    
    }
}
