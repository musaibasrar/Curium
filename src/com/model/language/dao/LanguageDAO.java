package com.model.language.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.language.dto.Language;
import com.util.HibernateUtil;

public class LanguageDAO {

	
	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
  
    SessionFactory sessionFactory;
    

	public LanguageDAO() {
		session = HibernateUtil.openSession();
	}


	

	@SuppressWarnings({ "unchecked", "finally" })
	public List<Language> readListOfObjects() {
		
		List<Language> results = new ArrayList<Language>();
        try {
            
            transaction = session.beginTransaction();
            results = (List<Language>) session.createQuery("From Language").list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return results;
        }
	}

    public boolean addLanguage(Language language) {
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            session.save(language);
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return result;
        }
    }




    public boolean deleteMultipleLanguage(List<Integer> ids) {
        
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Language where idlanguage IN (:ids)");
            query.setParameterList("ids", ids);
            query.executeUpdate();
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }
        return result;
    }




    public boolean updateMultipleLanguage(List<Language> languageList) {

        boolean result = false;
        try {
            transaction = session.beginTransaction();
            for (Language lang : languageList) {
                session.update(lang);
            }
            transaction.commit();
            result = true;
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }
        return result;
    
    }
}
