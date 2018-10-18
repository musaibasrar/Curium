package com.model.referencebooks.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import com.util.Session;
import org.hibernate.SessionFactory;
import com.util.Session.Transaction;
import org.hibernate.query.Query;

import com.model.qualification.dto.Qualification;
import com.model.referencebooks.dto.Referencebooks;
import com.util.HibernateUtil;

public class ReferenceBooksDAO {

	
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    

	public ReferenceBooksDAO() {
		session = HibernateUtil.openCurrentSession();
	}


	

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Referencebooks> readListOfObjects() {
		
		List<Referencebooks> results = new ArrayList<Referencebooks>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Referencebooks>) session.createQuery("From Referencebooks").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return results;
        }
	}


	public boolean deleteMultiple(List ids) {
		try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Referencebooks where idreferencebooks IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }
		return false;
	}


    public boolean addReferenceBooks(Referencebooks ref) {
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            session.save(ref);
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return result;
        }
    }


    public boolean updateMultipleReferenceBooks(List<Referencebooks> refbooksList) {

        boolean result = false;
        try {
            transaction = session.beginTransaction();
            for (Referencebooks refBooks : refbooksList) {
                session.update(refBooks);
            }
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        }
        return result;
    
    }




    public List<Referencebooks> getReferenceBooks(String examLevelCode, String language) {
        List<Referencebooks> refBooksList = new ArrayList<Referencebooks>();
        try {
            
            transaction = session.beginTransaction();
            if(language!="") {
                refBooksList = (List<Referencebooks>) session.createQuery("From Referencebooks where examlevelcode='"+examLevelCode+"' and language='"+language+"'").list();
            }else {
                refBooksList = (List<Referencebooks>) session.createQuery("From Referencebooks where examlevelcode='"+examLevelCode+"'").list();
            }
            
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return refBooksList;
        }
    }




    public List<Referencebooks> getReferenceBooks(String examLevel, String subject, String languageopted) {
        List<Referencebooks> refBooksList = new ArrayList<Referencebooks>();
        try {
            
            transaction = session.beginTransaction();
            refBooksList = (List<Referencebooks>) session.createQuery("From Referencebooks where examlevelcode='"+examLevel+"' and language='"+languageopted+"' and subject='"+subject+"'").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return refBooksList;
        }
    }
}
