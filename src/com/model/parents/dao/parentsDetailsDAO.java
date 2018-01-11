package com.model.parents.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.parents.dto.Parents;
import com.model.student.dto.Student;
import com.util.HibernateUtil;

public class parentsDetailsDAO {
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
	    
	    public parentsDetailsDAO() {
	    	   session = HibernateUtil.openSession();
		}

	@SuppressWarnings("finally")
	public Parents create(Parents parents) {
		 try {
	            //this.session = sessionFactory.openCurrentSession();
	            transaction = session.beginTransaction();
	            session.save(parents);


	            transaction.commit();
	           
	        } catch (HibernateException hibernateException) {
	            transaction.rollback();
	            hibernateException.printStackTrace();
	        } finally {
	            session.close();
	            return parents;
	        }
	}

	public Parents readUniqueObject(long id) {
		Parents parents = new Parents();
        try {
            //this.session = HibernateUtil.getSessionFactory().openCurrentSession();

            transaction = session.beginTransaction();
            Query query = session.createQuery("from Parents as parents where parents.Student.sid=" + id);
            parents = (Parents) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        }
        //session.close();
        return parents;
	}

	@SuppressWarnings("finally")
	public Parents update(Parents parents) {
		
		try {
            //this.session = sessionFactory.openCurrentSession();
            transaction = session.beginTransaction();
            session.update(parents);
            transaction.commit();
            System.out.println("in update parents");
        } catch (HibernateException hibernateException) {
            transaction.rollback();
            hibernateException.printStackTrace();
        } finally {
            //session.close();
            return parents;
        }
	}

}
