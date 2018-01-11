package com.model.std.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.std.dto.Classsec;
import com.util.HibernateUtil;

public class standardDetailsDAO {

	Session session = null;
    /**
     * * Hibernate Session Variable
     */
    Transaction transaction = null;
    /**
     * * Hibernate Transaction Variable
     */
    Transaction transaction1;
    SessionFactory sessionFactory;
	
	public standardDetailsDAO() {
		session = HibernateUtil.openSession();
	}

	@SuppressWarnings("finally")
	public Classsec create(Classsec classsec) {
		 try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
	            session.save(classsec);


	            transaction.commit();
	            System.out.println("in STDRD ");
	        } catch (HibernateException hibernateException) {
	            transaction.rollback();
	            hibernateException.printStackTrace();
	        } finally {
	            session.close();
	            return classsec;
	        }
	}

}
