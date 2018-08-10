package com.model.examlevels.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.branch.dto.Branch;
import com.model.department.dto.Department;
import com.model.examlevels.dto.Examleveldetails;
import com.model.examlevels.dto.Subexamlevel;
import com.util.HibernateUtil;

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
		session = HibernateUtil.openSession();
	}


	

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Examleveldetails> readListOfObjects() {
		
		List<Examleveldetails> results = new ArrayList<Examleveldetails>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Examleveldetails>) session.createQuery("From Examleveldetails").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
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
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }
		
	}


    public boolean addExamLevels(Examleveldetails examLevel) {
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            session.save(examLevel);
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            session.close();
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
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
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
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }
        return result;
    
    }




    public List<Subexamlevel> getSubExamLevelSubject(String examLevel) {
        List<Subexamlevel> results = new ArrayList<Subexamlevel>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Subexamlevel>) session.createQuery("From Subexamlevel where examlevel='"+examLevel+"'").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return results;
        }
    }




    public List<Examleveldetails> getExamLevelDetails(String examLevel) {
        
        List<Examleveldetails> results = new ArrayList<Examleveldetails>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Examleveldetails>) session.createQuery("From Examleveldetails where levelcode='"+examLevel+"'").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return results;
        }
    }
}
