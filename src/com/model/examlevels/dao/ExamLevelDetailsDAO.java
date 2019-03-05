package com.model.examlevels.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.model.examlevels.dto.Examleveldetails;
import com.model.examlevels.dto.Subexamlevel;
import com.util.HibernateUtil;
import com.util.Session;
import com.util.Session.Transaction;

public class ExamLevelDetailsDAO {

	
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    

	public ExamLevelDetailsDAO() {
		session = HibernateUtil.openCurrentSession();
	    //session = HibernateUtil.openSession();
	}


	

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Examleveldetails> readListOfObjects() {
		
		List<Examleveldetails> results = new ArrayList<Examleveldetails>();
        try {
            transaction = session.beginTransaction();
            results = (List<Examleveldetails>) session.createQuery("From Examleveldetails").setCacheable(true).setCacheRegion("commonregion").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        }catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
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
        }finally {
			HibernateUtil.closeSession();
		}
		
	}


    public boolean addExamLevels(Examleveldetails examLevel) {
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            session.save(examLevel);
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
            return result;
        }
    }




    public boolean deleteMultipleExamLevels(List<Integer> ids) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Examleveldetails where idexamlevel IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    }




    public boolean updateMultipleExamLevels(List<Examleveldetails> examLevelList) {

        boolean result = false;
        try {
            transaction = session.beginTransaction();
            for (Examleveldetails exLevel : examLevelList) {
                session.update(exLevel);
            }
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }finally {
			HibernateUtil.closeSession();
		}
        return result;
    
    }




    public List<Subexamlevel> getSubExamLevelSubject(String examLevel) {
        List<Subexamlevel> results = new ArrayList<Subexamlevel>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Subexamlevel>) session.createQuery("From Subexamlevel where examlevel='"+examLevel+"'").setCacheable(true).setCacheRegion("commonregion").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
            return results;
        }
    }




    public List<Examleveldetails> getExamLevelDetails(String examLevel) {
        
        List<Examleveldetails> results = new ArrayList<Examleveldetails>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Examleveldetails>) session.createQuery("From Examleveldetails where levelcode='"+examLevel+"'").setCacheable(true).setCacheRegion("commonregion").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
            return results;
        }
    }
}
